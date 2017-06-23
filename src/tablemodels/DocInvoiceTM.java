package tablemodels;

import java.util.ArrayList;

import entities.DocInvoice;
import superclasses.Entity;
import superclasses.TableModel;

public class DocInvoiceTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Date", "#", "Client", "Store", "Summa"};
    
    public DocInvoiceTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        DocInvoice invoice = (DocInvoice) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return invoice.getId();
            case 1:
                return invoice.getDate();
            case 2:
            	return invoice.getNumber();
            case 3:
            	return invoice.getClientName();
            case 4:
            	return invoice.getStoreName();
            default: 
            	return invoice.getSumma();
        }
    }

}