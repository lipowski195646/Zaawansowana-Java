package listpanels;

import java.util.ArrayList;

import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefProductDM;
import editdialogs.RefProductED;
import tablemodels.RefProductTM;

public class RefProductLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Products list";
	
	public RefProductLP() {
		super(TITLE);
		init();
	}
	
	public RefProductLP(int widht) {
		super(TITLE, widht);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefProductDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefProductTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefProductED(ent);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 2, 70, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 3, 70, SwingConstants.RIGHT);
		super.decorateTableColumn(entityTable, 4, 70, SwingConstants.RIGHT);
    }
	
}
