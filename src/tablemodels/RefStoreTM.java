package tablemodels;

import java.util.ArrayList;

import entities.RefStore;
import superclasses.Entity;
import superclasses.TableModel;

public class RefStoreTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Name", "Surname"};
    
    public RefStoreTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefStore store = (RefStore) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return store.getId();
            case 1:
                return store.getName();
            default:
            	return store.getSurname();
        }
    }

}