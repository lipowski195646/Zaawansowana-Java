package tablemodels;

import java.util.ArrayList;

import entities.DocInvoiceItem;
import superclasses.Entity;
import superclasses.TableModel;

public class DocInvoiceItemTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Product", "Unit", "Quantity", "Price", "Summa"};
    
    public DocInvoiceItemTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        DocInvoiceItem invoiceItem = (DocInvoiceItem) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return invoiceItem.getId();
            case 1:
                return invoiceItem.getProductName();
            case 2:
            	return invoiceItem.getProductUnit();
            case 3:
            	return invoiceItem.getQuantity();
            case 4:
            	return invoiceItem.getPrice();
            default: 
            	return invoiceItem.getSumma();
        }
    }

}