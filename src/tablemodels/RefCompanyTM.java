package tablemodels;

import java.util.ArrayList;
import entities.RefCompany;
import superclasses.Entity;
import superclasses.TableModel;

public class RefCompanyTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Name", "Full Name", "Phones"};
    
    public RefCompanyTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        RefCompany company = (RefCompany) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return company.getId();
            case 1:
                return company.getName();
            case 2:
            	return company.getSurname();
            default:
            	return company.getPhones();
        }
    }

}