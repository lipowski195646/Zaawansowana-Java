package superclasses;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import main.DatePicker;
import main.FloatTextField;
import superclasses.ListPanel;

/**
 * Abstract class - the base for the dialogs (documents)
 * (package "editdialogs")
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class EditDialogDoc extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	protected JPanel panelTable;
	protected JTextField txtNumber;
	protected FloatTextField txtSumma;
	protected DatePicker dpDate;
	protected ListPanel items = null;
	
	public EditDialogDoc() {
		super();
		
		JLabel lblNumber = new JLabel("Number:");
		lblNumber.setBounds(12, 35, 50, 25);
		panelFields.add(lblNumber);
		
		JLabel lblDate = new JLabel("Date:");
		lblDate.setBounds(12, 65, 50, 25);
		panelFields.add(lblDate);
		
		JLabel lblSumma = new JLabel("Summa:");
		lblSumma.setBounds(210, 65, 60, 25);
		panelFields.add(lblSumma);
		
		super.txtId.setBounds(80, 5, 80, 25);
		
		txtNumber = new JTextField();
		txtNumber.setBounds(80, 35, 80, 25);
		txtNumber.setHorizontalAlignment(SwingConstants.CENTER);
		txtNumber.setColumns(10);
		panelFields.add(txtNumber);
		
		dpDate = new DatePicker();
		dpDate.setBounds(80, 63, 115, 27);
		dpDate.getModel().setSelected(true);
		panelFields.add(dpDate);
		
		txtSumma = new FloatTextField();
		txtSumma.setBounds(265, 65, 70, 25);
		txtSumma.setEditable(false);
		panelFields.add(txtSumma);
		
		panelTable = new JPanel();
		panelTable.setBounds(10, 100, 100, 100);
		panelTable.setLayout(new BorderLayout(0, 0));
		JButton btnWarn = new JButton("First save to operate with table data!");
		panelTable.add(btnWarn, BorderLayout.CENTER);
		panelFields.add(panelTable);
	}
	
	public EditDialogDoc(int x, int y, int width, int height, int fieldsHeight) {
		this();
		setBounds(x, y, width, height);
		panelTable.setBounds(2, fieldsHeight, width-10, height-fieldsHeight-65);
	}
	
	@Override
	void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);
		if (items != null)
			items.setReadOnly(readOnly);
	}
	
}