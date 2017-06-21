package editdialogs;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import superclasses.EditDialog;
import superclasses.Entity;
import entities.RefProduct;
import main.Constants;
import main.FloatTextField;
import main.IntTextField;

public class RefProductED extends EditDialog {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JTextField txtName;
	private IntTextField  txtPoints, txtWarranty;
	private FloatTextField txtPurchase, txtSelling;
	private JComboBox<String> comboUnit;
	
	public RefProductED(Entity ent) {
		super();
		
		setTitle("Add / edit product");
		setBounds(100, 100, 345, 285);
		
		JLabel lblName  = new JLabel("Name:");
		lblName.setBounds(12, 35, 50, 25);
		panelFields.add(lblName);
		
		JLabel lblUnit = new JLabel("Unit:");
		lblUnit.setBounds(12, 65, 50, 25);
		panelFields.add(lblUnit);
		
		JLabel lblPoints = new JLabel("Points:");
		lblPoints.setBounds(12, 95, 80, 25);
		panelFields.add(lblPoints);
		
		JLabel lblWarranty = new JLabel("Warranty:");
		lblWarranty.setBounds(12, 125, 80, 25);
		panelFields.add(lblWarranty);
		
		JLabel lblPurchase = new JLabel("Purchase:");
		lblPurchase.setBounds(12, 155, 80, 25);
		panelFields.add(lblPurchase);
		
		JLabel lblSelling = new JLabel("Selling:");
		lblSelling.setBounds(12, 185, 80, 25);
		panelFields.add(lblSelling);
		
		txtName = new JTextField();
		txtName.setBounds(80, 35, 250, 25);
		txtName.setColumns(10);
		panelFields.add(txtName);
		
		comboUnit = new JComboBox<String>(Constants.UNITS);
		comboUnit.setBounds(80, 65, 60, 25);
		panelFields.add(comboUnit);
		
		txtPoints = new IntTextField();
		txtPoints.setBounds(80, 95, 80, 25);
		panelFields.add(txtPoints);
		
		txtWarranty = new IntTextField();
		txtWarranty.setBounds(80, 125, 80, 25);
		panelFields.add(txtWarranty);
		
		txtPurchase = new FloatTextField();
		txtPurchase.setBounds(80, 155, 80, 25);
		panelFields.add(txtPurchase);
		
		txtSelling = new FloatTextField();
		txtSelling.setBounds(80, 185, 80, 25);
		panelFields.add(txtSelling);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		RefProduct product = (RefProduct) ent;
		String unit = "";
		
        if (product != null) {
            id = product.getId();
            
            super.txtId.setText("" + id);
            txtName.setText(product.getName());
            unit = product.getUnit();
            txtPoints.setText(String.valueOf(product.getPoints()));
            txtWarranty.setText(String.valueOf(product.getWarranty()));
            txtPurchase.setText(String.valueOf(product.getPriceIn()));
            txtSelling.setText(String.valueOf(product.getPriceOut()));
        }
        
        comboUnit.setSelectedItem(unit);
    }
	
	@Override
	protected Entity entityFromFields() {
		return 
			new RefProduct(
				id, 
				txtName.getText().trim(), 
				(String) comboUnit.getSelectedItem(), 
				txtPoints.getInt(),
				txtWarranty.getInt(),
				txtPurchase.getFloat(),
				txtSelling.getFloat()
			);
	}

}