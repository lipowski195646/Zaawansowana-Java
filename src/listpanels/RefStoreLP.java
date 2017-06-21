package listpanels;

import java.util.ArrayList;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefStoreDM;
import editdialogs.RefStoreED;
import tablemodels.RefStoreTM;

public class RefStoreLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Stores list"; 
	
	public RefStoreLP() {
		super(TITLE);
		init();
	}
	
	public RefStoreLP(int widht) {
		super(TITLE, widht);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefStoreDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefStoreTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefStoreED(ent);
	}
	
}
