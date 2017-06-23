package tablemodels;

import java.util.ArrayList;

import entities.RefProduct;
import superclasses.Entity;
import superclasses.TableModel;

public class RefProductTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Name", "Unit", "Purchase", "Selling"};
    
    public RefProductTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefProduct product = (RefProduct) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return product.getId();
            case 1:
                return product.getName();
            case 2:
                return product.getUnit();
            case 3:
            	return product.getPriceIn();
            default:
            	return product.getPriceOut();
        }
    }

}