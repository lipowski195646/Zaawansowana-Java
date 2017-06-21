package editdialogs;

import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefClient;
import main.IntTextField;

public class RefClientED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtName, txtSurname;
	private IntTextField txtPoints, txtPostalCode;
	
	public RefClientED(Entity ent) {
		super();
		
		setTitle("Add / edit client");
		setBounds(100, 100, 325, 225);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(12, 35, 60, 25);
		panelFields.add(lblName);
		
		JLabel lblSurname = new JLabel("Surname:");
		lblSurname.setBounds(12, 65, 60, 25);
		panelFields.add(lblSurname);
		
		JLabel lblPoints = new JLabel("Points:");
		lblPoints.setBounds(12, 95, 100, 25);
		panelFields.add(lblPoints);
		
		JLabel lblPostalCode = new JLabel("Postal code:");
		lblPostalCode.setBounds(12, 125, 100, 25);
		panelFields.add(lblPostalCode);
		
		super.txtId.setBounds(110, 5, 50, 25);
		
		txtName = new JTextField();
		txtName.setBounds(110, 35, 200, 25);
		txtName.setColumns(10);
		panelFields.add(txtName);
		
		txtSurname = new JTextField();
		txtSurname.setBounds(110, 65, 200, 25);
		txtSurname.setColumns(10);
		panelFields.add(txtSurname);
		
		txtPoints = new IntTextField();
		txtPoints.setBounds(110, 95, 80, 25);
		panelFields.add(txtPoints);
		
		txtPostalCode = new IntTextField();
		txtPostalCode.setBounds(110, 125, 80, 25);
		panelFields.add(txtPostalCode);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		RefClient client = (RefClient) ent;
		
        if (client != null) {
            id = client.getId();
            
            super.txtId.setText("" + id);
            txtName.setText(client.getName());
            txtSurname.setText(client.getSurname());
            txtPoints.setText("" + client.getPoints());
            txtPostalCode.setText(client.getPostalCode());
        }
    }
	
	@Override
	protected Entity entityFromFields() {
		return 
			new RefClient(
				id, 
				txtName.getText().trim(), 
				txtSurname.getText().trim(), 
				txtPoints.getInt(), 
				txtPostalCode.getText().trim() 
			);
	}

}