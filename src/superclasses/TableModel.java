package superclasses;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 * Abstract class - the base for the table models, for JTable component of list panels
 * (package "tablemodels")
 * @author vdidukh
 * @since CORE_1
 * @id
 */
public abstract class TableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private String[] headers;
	private ArrayList<Entity> list;
    
    public TableModel(ArrayList<Entity> list, String[] headers) {
        this.list = list;
        this.headers = headers;
    }
    
    public ArrayList<Entity> getList() {
        return list;
    }
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return headers.length;
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }
    
    @Override
    public Object getValueAt(int row, int col) {
        return list.get(row);
    }

}