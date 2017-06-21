package tablemodels;

import java.util.ArrayList;

import entities.RefAccount;
import superclasses.Entity;
import superclasses.TableModel;

public class RefAccountTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Login", "Role"};
    
    public RefAccountTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefAccount account = (RefAccount) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return account.getId();
            case 1:
                return account.getLogin();
            default:
            	return account.getRole();
        }
    }

}