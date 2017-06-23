package editdialogs;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import datamanagers.RefSellerDM;
import superclasses.EditDialogDoc;
import superclasses.Entity;
import entities.DocSale;
import entities.RefSeller;
import listpanels.DocSaleItemLP;

public class DocSaleED extends EditDialogDoc {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JComboBox<Entity> comboSeller;
	
	public DocSaleED(Entity ent) {
		super(100, 100, 600, 600, 95);
		
		setTitle("Add / edit sale");
		
		JLabel lblClient = new JLabel("Seller:");
		lblClient.setBounds(210, 5, 60, 25);
		panelFields.add(lblClient);
		
		comboSeller = new JComboBox<Entity>();
		comboSeller.setBounds(265, 5, 200, 25);
		panelFields.add(comboSeller);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		DocSale sale = (DocSale) ent;
		int sellerId = 0;
		
        if (sale != null) {
            id = sale.getId();
            sellerId = sale.getSellerId();
            
            super.txtId.setText("" + id);
            super.dpDate.setDate(sale.getDate());
            super.txtNumber.setText(sale.getNumber());
            
            panelTable.removeAll();
            super.items = new DocSaleItemLP(id, this);
            super.items.setReadOnly(super.readOnly);
            panelTable.add(super.items, BorderLayout.CENTER);
        }
        
        super.setComboItems(comboSeller, new RefSellerDM(), sellerId);
    }
	
	@Override
	protected Entity entityFromFields() {
		RefSeller seller = (RefSeller) comboSeller.getSelectedItem();
		
		return
			new DocSale(
				id, 
				super.dpDate.getDate(), 
				super.txtNumber.getText().trim(),
				seller.getId(),
				seller.getName(),
				seller.getSurname(), 
				0
			);
	}
	
}