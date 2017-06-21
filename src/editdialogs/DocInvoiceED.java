package editdialogs;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import datamanagers.RefClientDM;
import datamanagers.RefStoreDM;
import superclasses.EditDialogDoc;
import superclasses.Entity;
import entities.DocInvoice;
import entities.RefClient;
import entities.RefStore;
import listpanels.DocInvoiceItemLP;

public class DocInvoiceED extends EditDialogDoc {
	private static final long serialVersionUID = 1L;
	
	private int id = 0;
	private JComboBox<Entity> comboClient, comboStore;
	
	public DocInvoiceED(Entity ent) {
		super(100, 100, 600, 600, 95);
		
		setTitle("Add / edit invoice");
		
		JLabel lblClient = new JLabel("Client:");
		lblClient.setBounds(210, 5, 60, 25);
		panelFields.add(lblClient);
		
		JLabel lblStore = new JLabel("Store:");
		lblStore.setBounds(210, 35, 60, 25);
		panelFields.add(lblStore);
		
		comboClient = new JComboBox<Entity>();
		comboClient.setBounds(265, 5, 200, 25);
		panelFields.add(comboClient);
		
		comboStore = new JComboBox<Entity>();
		comboStore.setBounds(265, 35, 200, 25);
		panelFields.add(comboStore);
		
		initFields(ent);
	}
	
	private void initFields(Entity ent) {
		DocInvoice invoice = (DocInvoice) ent;
		int clientId = 0;
		int storeId = 0;
		
        if (invoice != null) {
            id = invoice.getId();
            clientId = invoice.getClientId();
            storeId = invoice.getStoreId();
            
            super.txtId.setText("" + id);
            super.dpDate.setDate(invoice.getDate());
            super.txtNumber.setText(invoice.getNumber());
            
            panelTable.removeAll();
            super.items = new DocInvoiceItemLP(id, this);
            super.items.setReadOnly(super.readOnly);
            panelTable.add(super.items, BorderLayout.CENTER);
        }
        
        super.setComboItems(comboClient, new RefClientDM(), clientId);
        super.setComboItems(comboStore, new RefStoreDM(), storeId);
    }
	
	@Override
	protected Entity entityFromFields() {
		RefClient client = (RefClient) comboClient.getSelectedItem();
		RefStore store = (RefStore) comboStore.getSelectedItem();
		
		return
			new DocInvoice(
				id, 
				super.dpDate.getDate(), 
				super.txtNumber.getText().trim(),
				client.getId(),
				client.getName(),
				client.getSurname(), 
				store.getId(), 
				store.getName(),
				store.getSurname(),
				0
			);
	}
	
}