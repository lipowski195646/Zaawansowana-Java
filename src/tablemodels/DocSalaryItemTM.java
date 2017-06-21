package tablemodels;

import java.util.ArrayList;

import entities.DocSalaryItem;
import superclasses.Entity;
import superclasses.TableModel;

public class DocSalaryItemTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "User", "Work days", "Salary", "Premia (%)", "Premia", "Summa"};
    
    public DocSalaryItemTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        DocSalaryItem salaryItem = (DocSalaryItem) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return salaryItem.getId();
            case 1:
                return salaryItem.getUserName();
            case 2:
                return salaryItem.getWorkDays();
            case 3:
            	return salaryItem.getSalaryCalc();
            case 4:
            	return salaryItem.getPremiaRef();
            case 5:
            	return salaryItem.getPremiaCalc();
            default: 
            	return salaryItem.getSumma();
        }
    }

}