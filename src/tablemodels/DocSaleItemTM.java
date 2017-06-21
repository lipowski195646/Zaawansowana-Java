package tablemodels;

import java.util.ArrayList;

import entities.DocSaleItem;
import superclasses.Entity;
import superclasses.TableModel;

public class DocSaleItemTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Store", "Product", "Unit", "Quantity", "Price", "Summa"};
    
    public DocSaleItemTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        DocSaleItem saleItem = (DocSaleItem) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return saleItem.getId();
            case 1:
                return saleItem.getStoreName();
            case 2:
                return saleItem.getProductName();
            case 3:
            	return saleItem.getProductUnit();
            case 4:
            	return saleItem.getQuantity();
            case 5:
            	return saleItem.getPrice();
            default: 
            	return saleItem.getSumma();
        }
    }

}