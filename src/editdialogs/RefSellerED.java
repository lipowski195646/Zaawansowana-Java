package editdialogs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefSeller;

public class RefSellerED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtName, txtSurname;
	
	public RefSellerED(Entity ent) {
		super();
		
		setTitle("Add / edit seller");
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
		RefSeller seller = (RefSeller) ent;
		
        if (seller != null) {
            id = seller.getId();
            
            super.txtId.setText("" + id);
            txtName.setText(seller.getName());
            txtSurname.setText(seller.getSurname());
        }
    }
	
	@Override
	protected Entity entityFromFields() {
		return 
			new RefSeller(
				id, 
				txtName.getText().trim(), 
				txtSurname.getText().trim() 
			);
	}

}