package tablemodels;

import java.util.ArrayList;

import entities.RefClient;
import superclasses.Entity;
import superclasses.TableModel;

public class RefClientTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Name", "Surname", "Points", "Post"};
    
    public RefClientTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefClient client = (RefClient) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return client.getId();
            case 1:
                return client.getName();
            case 2:
            	return client.getSurname();
            case 3:
            	return client.getPoints();
            default: 
            	return client.getPostalCode();
        }
    }

}