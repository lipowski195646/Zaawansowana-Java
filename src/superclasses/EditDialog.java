package superclasses;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import main.Common;
import main.Constants;
import main.DatePicker;
import main.IntTextField;
import superclasses.Entity;
import superclasses.ListPanel;

/**
 * Abstract class - the base for the dialogs (references and document items)
 * (package "editdialogs") and abstract EditDialogDoc class
 * @author Vlad
 *
 */
public abstract class EditDialog extends Dialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final String SAVE = "SAVE", CANCEL = "CANCEL", PRINT = "PRINT";
	
	protected abstract Entity entityFromFields();
	protected IntTextField txtId;
	protected boolean readOnly = false;
	protected EditDialogDoc parentDialog = null;	// for document items
	protected JButton btnPrint;
	
	/**
	 * For references
	 * (set listeners for buttons and add print button)
	 */
	public EditDialog() {
		super();
		
		super.btnOK.setText("Save");
		super.btnOK.setActionCommand(SAVE);
		super.btnOK.addActionListener(this);
		
		super.btnCancel.addActionListener(this);
		super.btnCancel.setActionCommand(CANCEL);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(12, 5, 50, 25);
		super.panelFields.add(lblId);
		
		txtId = new IntTextField();
		txtId.setBounds(80, 5, 50, 25);
		txtId.setEditable(false);
		super.panelFields.add(txtId);
		
		btnPrint = new JButton("Print");
		btnPrint.setActionCommand(PRINT);
		btnPrint.addActionListener(this);
		btnPrint.setVisible(!printFormClassName().isEmpty());
		super.panelButtons.add(btnPrint);
	}
	
	/**
	 * For document items
	 * @param parentDialog document main form
	 */
	public EditDialog(EditDialogDoc parentDialog) {
		this();
		this.parentDialog = parentDialog;
	}
	
	/**
	 * Manage the ability to enter values into fields
	 * @param readOnly flag to set or remove read only mode
	 */
	void setReadOnly(boolean readOnly) {
		boolean isEnabled = !readOnly;
		this.readOnly = readOnly;
		
		Component[] components = panelFields.getComponents();
		
		for (Component component : components) {
			if (component instanceof JButton)
				((JButton) component).setEnabled(isEnabled);
			else if (component instanceof JTextField) {
				JTextField text = (JTextField) component;
				if (text.isEditable())
					text.setEditable(isEnabled);
			}
			else if (component instanceof DatePicker)
				((DatePicker) component).setEditable(isEnabled);
			else if (component instanceof JComboBox)
				((JComboBox<?>) component).setEnabled(isEnabled);
		}
		
		btnOK.setEnabled(isEnabled);
	}
	
	/**
	 * The method can be overrided by subclass to block saving
	 * @param ent entity linked to form
	 * @return decision 
	 */
	protected boolean extraSaveCheck(Entity ent) {
    	return true;
    }
	
	/**
	 * The method can be overrided by subclass.
	 * Subclass can set the class name of print form.
	 * If set - the print button is visible
	 * @return the class name of print form
	 */
	protected String printFormClassName() {
    	return "";
    }
	
	/**
	 * Buttons handlers
	 */
	@Override
	public void actionPerformed(ActionEvent ae) {
		ListPanel.setEntityFromDialog(null);
		Entity ent = entityFromFields();
		
		switch (ae.getActionCommand()) {
		case SAVE:
			if (extraSaveCheck(ent)) {
				if (Common.getCommonInstance().showConfirmDialog(this, "Are you really want to save", "Save data") == Constants.YES)
					ListPanel.setEntityFromDialog(ent);
					
				dispose();
			}
			
			break;
		
		case CANCEL:
			dispose();
			break;
			
		case PRINT:
			String printFormName = printFormClassName();
			
			if (!printFormName.isEmpty()) {
				// class reflection - creating print form
				try {
					Class<?> cl = Class.forName(printFormName);
					PrintForm printFormClass = (PrintForm) cl.newInstance();
					printFormClass.print(ent);
				} catch (ClassNotFoundException e) {
					Common.getCommonInstance().showErrorMessage(this, "Class <" + printFormName + "> is not found!");
				} catch (InstantiationException | IllegalAccessException e) {
					Common.getCommonInstance().showErrorMessage(this, "Error creating object of class <" + printFormName + ">!");
				}
			}
			
			break;

		default:
			break;
		}
	}
	
	/**
	 * For item dialog - get parent document id
	 * @return id value from dialog
	 */
	public int getId() {
		return txtId.getInt();
	}	
	
	/**
	 * Fill the lookup ComboBox with values from the table
	 * @param combo combo box to fill
	 * @param dataManager class for database connect
	 * @param indexId selected index
	 */
	protected void setComboItems(JComboBox<Entity> combo, DataManager dataManager, int indexId) {
		List<Entity> entities = dataManager.getEntityList();
		
		int index = 0;
		for (int i = 0; i < entities.size(); i++) {
			Entity ent = entities.get(i);
			if (ent.getId() == indexId)
				index = i;
			combo.addItem(ent);	
		}
		
		combo.setSelectedIndex(index);
	}

}