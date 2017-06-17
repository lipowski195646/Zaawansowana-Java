package superclasses;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import java.util.ArrayList;

import main.Common;
import main.Constants;

/**
 * Abstract class - subclasses parametrize it
 * (package "listpanels")
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class ListPanel extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private static final String ADD = "ADD", EDIT = "EDIT", DELETE = "DELETE", PRINT = "PRINT";
	
	private int selectedRow = 0;
	private boolean readOnly = false;
	private ArrayList<Entity> entities;
    private JButton btnAdd, btnEdit, btnDelete, btnPrint;
    
    private static Entity entityFromDialog;		// for recieve entity from edit dialog
	
	protected final JTable entityTable = new JTable();
	protected EditDialogDoc parentDialog = null;
	
	/**
	 * The subclass sets a specific data manager
	 */
	protected DataManager entityDataManager;

	/**
	 * The subclass sets a specific table model 
	 * @param entities array list of the entities 
	 * @return table model
	 */
	protected abstract TableModel getTableModel(ArrayList<Entity> entities);
    
	/**
	 * The subclass sets a specific edit dialog
	 * @param entity array list of the entities
	 * @return edit dialog
	 */
	protected abstract EditDialog getEditDialog(Entity entity);
    
    public ListPanel(String title, int width) {
    	this(title);
    	setBounds(100, 100, width, 400);
    }
	
	public ListPanel(String title) {
		setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), " " + title + ": ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelButtons = new JPanel();
		panelButtons.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		add(panelButtons, BorderLayout.NORTH);
		GridBagLayout gbl_panelButtons = new GridBagLayout();
		gbl_panelButtons.columnWidths = new int[] {80, 80, 80, 80, 0};
		gbl_panelButtons.rowWeights = new double[] {0.0};
		gbl_panelButtons.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelButtons.setLayout(gbl_panelButtons);
		
		btnAdd = new JButton("Add");
		btnAdd.setActionCommand(ADD);
		btnAdd.addActionListener(this);
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(6, 6, 6, 0);
		gbc_btnAdd.anchor = GridBagConstraints.WEST;
		gbc_btnAdd.gridheight = GridBagConstraints.REMAINDER;
		gbc_btnAdd.fill = GridBagConstraints.BOTH;
		panelButtons.add(btnAdd, gbc_btnAdd);
		
		btnEdit = new JButton("Edit");
		btnEdit.setActionCommand(EDIT);
		btnEdit.addActionListener(this);
		GridBagConstraints gbc_btnEdit = new GridBagConstraints();
		gbc_btnEdit.insets = new Insets(6, 6, 6, 0);
		gbc_btnEdit.anchor = GridBagConstraints.NORTH;
		gbc_btnEdit.gridheight = GridBagConstraints.REMAINDER;
		gbc_btnEdit.fill = GridBagConstraints.BOTH;
		panelButtons.add(btnEdit, gbc_btnEdit);
		
		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand(DELETE);
		btnDelete.addActionListener(this);
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(6, 6, 6, 0);
		gbc_btnDelete.anchor = GridBagConstraints.NORTH;
		gbc_btnDelete.gridheight = GridBagConstraints.REMAINDER;
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		panelButtons.add(btnDelete, gbc_btnDelete);
		
		btnPrint = new JButton("Print");
		btnPrint.setActionCommand(PRINT);
		btnPrint.addActionListener(this);
		btnPrint.setVisible(getPrintForm() != null);
		GridBagConstraints gbc_btnPrint = new GridBagConstraints();
		gbc_btnPrint.insets = new Insets(6, 6, 6, 0);
		gbc_btnPrint.anchor = GridBagConstraints.NORTH;
		gbc_btnPrint.gridheight = GridBagConstraints.REMAINDER;
		gbc_btnPrint.fill = GridBagConstraints.BOTH;
		panelButtons.add(btnPrint, gbc_btnPrint);
		
		entityTable.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					editEntity();
			}
		});
		
		entityTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2)	// double click
		            editEntity();
			}
		});
		
		JScrollPane panelTable = new JScrollPane(entityTable);
		add(panelTable, BorderLayout.CENTER);
	}
	
	/**
	 * To receive entity from edit dialog
	 * @param ent entity
	 */
	public static void setEntityFromDialog(Entity ent) {
		entityFromDialog = ent;
	}
	
	/**
	 * Manage the ability to enter values into fields
	 * @param readOnly flag to set or remove read only mode
	 */
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
		
		btnAdd.setVisible(!readOnly);
		btnDelete.setVisible(!readOnly);
		btnEdit.setText(readOnly ? "View" : "Edit");
	}
	
	/**
	 * Hide the button
	 * @param btnName button name 
	 */
	public void disableButton(String btnName) {
		switch (btnName.toUpperCase()) {
		case "ADD":
			btnAdd.setVisible(false);
			break;
		case "EDIT":
			btnEdit.setVisible(false);
			break;
		case "DELETE":
			btnDelete.setVisible(false);
			break;
		default:
			break;
		}
	}
	
	/**
	 * The method can be overrided by subclass.
	 * Subclass can set the class name of print form.
	 * If set - the print button is visible
	 * @return the class name of print form
	 */
	protected PrintForm getPrintForm() {
    	return null;
    }
	
	private void printEntity() {
		PrintForm printForm = getPrintForm();
		
		if (printForm != null) {
			selectedRow = entityTable.getSelectedRow();
	        if (selectedRow != -1) {
	        	int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
	        	Entity selectedEntity = getEntity(id);
	        	
	        	if (selectedEntity != null)
	        		printForm.print(selectedEntity);
	        }
		}
	}
	
	/**
	 * Buttons handlers
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		
		switch (action) {
        case ADD:
            addEntity();
            break;
        case EDIT:
            editEntity();
            break;
        case DELETE:
            deleteEntity();
            break;
        case PRINT:
            printEntity();
            break;
		}
	}
	
	/**
	 * Get the entity from the array list
	 * @param id entity id
	 * @return entity object
	 */
	private Entity getEntity(int id) {
		Entity res = null;
    	for (Entity ent : entities)
			if (ent.getId() == id) {
				res = ent;
				break;
			}
    	return res;
	}
	
    /**
     * Set JTable column width and alignment
     * @param table JTable to process
     * @param colummnIndex column index
     * @param width width
     * @param alignment alignment
     */
	protected void decorateTableColumn(JTable table, int colummnIndex, int width, int alignment) {
		TableColumn column = table.getColumnModel().getColumn(colummnIndex);
		DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
		renderer.setHorizontalAlignment(alignment);
		column.setCellRenderer(renderer);
		if (width != 0)
			column.setMaxWidth(width);
	}
    
    /**
     * The subclass can override the method 
     */
	protected void decorateTable() {
    	decorateTableColumn(entityTable, 0, 30, SwingConstants.CENTER);
    }
	
	/**
	 * Get the entity list from the data manager and put it into JTable
	 */
	protected void loadEntities() {
		entities = entityDataManager.getEntityList();
		TableModel tm = getTableModel(entities);
		
        entityTable.setModel(tm);
        decorateTable();
        
        if (entities.size() != 0) {
        	entityTable.setRowSelectionInterval(selectedRow, selectedRow);
        	
        	if (parentDialog != null)
        		parentDialog.txtSumma.setText("" + calcSumma());
        }
        	
	}
	
	/**
	 * Store entity recieved from edit dialog to database (add or update)
	 */
    private void saveEntity() {
    	if (entityFromDialog == null) return;
    		
        if (entityFromDialog.getId() != 0) {
            entityDataManager.updateEntity(entityFromDialog);
        } else {
            entityDataManager.addEntity(entityFromDialog);
            selectedRow = entityTable.getRowCount();
        }
        loadEntities();
        entityFromDialog = null;
    }
	
    /**
     * Invoke dialog class for add entity 
     */
	private void addEntity() {
		EditDialog dialog = getEditDialog(null);
		dialog.setReadOnly(readOnly);
		Common.getCommonInstance().showFrame(dialog);
		saveEntity();
	}
	
	/**
	 * Invoke dialog class for edit entity fields
	 */
	private void editEntity() {
		if (!btnEdit.isVisible()) return;
		
		selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
        	int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
        	
        	Entity selectedEntity = getEntity(id);
        	
        	if (selectedEntity != null) {
        		EditDialog dialog = getEditDialog(selectedEntity);
        		dialog.setReadOnly(readOnly);
        		Common.getCommonInstance().showFrame(dialog);
        		saveEntity();	
        	}
            
        } else {
        	Common.getCommonInstance().showErrorMessage(this, "No row is selected!");
        }
	}
	
	/**
	 * The method can be overrided by subclass to block deleting
	 * @param ent entity linked to form
	 * @return decision 
	 */
	protected boolean extraDeleteCheck(Entity ent) {
    	return true;
    }
	
	/**
	 * Delete the entity from the database
	 */
	private void deleteEntity() {
        selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
            
            Entity selectedEntity = getEntity(id);
            if ( !extraDeleteCheck(selectedEntity) ) return;
            
    		if (Common.getCommonInstance().showConfirmDialog(this, "Delete record: \n" + selectedEntity + "?", "Delete record") != Constants.YES) 
    			return;
            
            entityDataManager.deleteEntity(id);
            selectedRow = 0;
            loadEntities();
        } else {
        	Common.getCommonInstance().showErrorMessage(this, "No row is selected!");
        }
	}
	
	/**
	 * Get entity from JTable
	 * @return
	 */
	public Entity getSelectedEntity() {
        selectedRow = entityTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) entityTable.getModel().getValueAt(selectedRow,0);
            return getEntity(id);
        } else {
        	return null;
        }
	}
	
	/**
	 * Get array list of the entities
	 * @return
	 */
	public ArrayList<Entity> getList() {
        return entities;
	}
	
	/**
	 * For documents - summa of document items
	 * @return summa
	 */
	private float calcSumma() {
		if (entities.size() == 0) return 0;
		
		float sum = 0;

		for (Entity entity : entities) {
			EntityItem item = (EntityItem) entity;
			sum += item.getSumma();
		}
		
		return Common.Round(sum, 2);
	}

}