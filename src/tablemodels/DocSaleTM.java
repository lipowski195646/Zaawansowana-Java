package tablemodels;

import java.util.ArrayList;

import entities.DocSale;
import superclasses.Entity;
import superclasses.TableModel;

public class DocSaleTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Date", "#", "Seller", "Summa"};
    
    public DocSaleTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        DocSale sale = (DocSale) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return sale.getId();
            case 1:
                return sale.getDate();
            case 2:
            	return sale.getNumber();
            case 3:
            	return sale.getSellerName();
            default: 
            	return sale.getSumma();
        }
    }

}