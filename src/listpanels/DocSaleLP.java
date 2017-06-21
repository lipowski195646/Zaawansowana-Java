package listpanels;

import java.util.ArrayList;

import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.DocSaleDM;
import editdialogs.DocSaleED;
import tablemodels.DocSaleTM;

public class DocSaleLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Sales";
	
	public DocSaleLP() {
		super(TITLE);
		init();
	}
	
	public DocSaleLP(int width) {
		super(TITLE, width);
		init();
	}
	
	private void init() {
		super.entityDataManager = new DocSaleDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new DocSaleTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new DocSaleED(ent);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 1, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 2, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 4, 110, SwingConstants.RIGHT);
    }
	
}
