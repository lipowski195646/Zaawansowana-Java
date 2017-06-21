package listpanels;

import java.util.ArrayList;
import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.DocSalaryDM;
import editdialogs.DocSalaryED;
import tablemodels.DocSalaryTM;

public class DocSalaryLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Salaries";
	
	public DocSalaryLP() {
		super(TITLE);
		init();
	}
	
	public DocSalaryLP(int width) {
		super(TITLE, width);
		init();
	}
	
	private void init() {
		super.entityDataManager = new DocSalaryDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new DocSalaryTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new DocSalaryED(ent);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 1, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 2, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 3, 0, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 4, 90, SwingConstants.RIGHT);
		super.decorateTableColumn(entityTable, 5, 120, SwingConstants.RIGHT);
    }
	
}