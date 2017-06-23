package editdialogs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefStore;

public class RefStoreED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtName, txtSurname;
	
	public RefStoreED(Entity ent) {
		super();
		
		setTitle("Add / edit store");
		setBounds(100, 100, 325, 165);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 35, 60, 25);
		panelFields.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(12, 65, 60, 25);
		panelFields.add(lblSurname);
		
		txtName = new JTextField();
		txtName.setBounds(80, 35, 230, 25);
		txtName.setColumns(10);
		panelFields.add(txtName);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(80, 65, 230, 25);
		txtSurname.setColumns(10);
		panelFields.add(txtSurname);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		RefStore store = (RefStore) ent;
		
        if (store != null) {
            id = store.getId();
            
            super.txtId.setText("" + id);
            txtName.setText(store.getName());
            txtSurname.setText(store.getSurname());
        }
    }
	
	@Override
	protected Entity entityFromFields() {
		return 
			new RefStore(
				id, 
				txtName.getText().trim(), 
				txtSurname.getText().trim() 
			);
	}

}