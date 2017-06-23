package tablemodels;

import java.util.ArrayList;

import entities.RefSeller;
import superclasses.Entity;
import superclasses.TableModel;

public class RefSellerTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Name", "Surname"};
    
    public RefSellerTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefSeller seller = (RefSeller) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return seller.getId();
            case 1:
                return seller.getName();
            default:
            	return seller.getSurname();
        }
    }

}