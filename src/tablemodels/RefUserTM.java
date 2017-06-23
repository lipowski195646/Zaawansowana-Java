package tablemodels;

import java.util.ArrayList;
import entities.RefUser;
import superclasses.Entity;
import superclasses.TableModel;

public class RefUserTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Name", "Surname", "Login", "State", "Salary", "Bonus (%)", "Hired", "Fired"};
    
    public RefUserTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefUser user = (RefUser) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return user.getId();
            case 1:
                return user.getName();
            case 2:
            	return user.getSurname();
            case 3:
            	return user.getLogin();
            case 4:
            	return user.getState();
            case 5:
            	return user.getSalary();
            case 6:
            	return user.getPremia();
            case 7:
            	return user.getDateIn();
            default: 
            	return user.getDateOut();
        }
    }

}