package tablemodels;

import java.util.ArrayList;

import entities.DocSalary;
import superclasses.Entity;
import superclasses.TableModel;

public class DocSalaryTM extends TableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] headers = {"ID", "Date", "#", "Period", "Work days", "Summa"};
    
    public DocSalaryTM(ArrayList<Entity> entities) {
        super(entities, headers);
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        DocSalary salary = (DocSalary) super.getValueAt(row, col);
        
        switch (col) {
            case 0:
                return salary.getId();
            case 1:
                return salary.getDate();
            case 2:
            	return salary.getNumber();
            case 3:
            	return salary.getPeriod();
            case 4:
            	return salary.getDaysWork();
            default: 
            	return salary.getSumma();
        }
    }

}