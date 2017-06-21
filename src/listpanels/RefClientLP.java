package listpanels;

import java.util.ArrayList;

import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefClientDM;
import editdialogs.RefClientED;
import tablemodels.RefClientTM;

public class RefClientLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Clients list";
	
	public RefClientLP() {
		super(TITLE);
		init();
	}
	
	public RefClientLP(int widht) {
		super(TITLE, widht);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefClientDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefClientTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefClientED(ent);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 3, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 4, 90, SwingConstants.CENTER);
    }
	
}