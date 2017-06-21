package listpanels;

import java.util.ArrayList;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefSellerDM;
import editdialogs.RefSellerED;
import tablemodels.RefSellerTM;

public class RefSellerLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Sellers list"; 
	
	public RefSellerLP() {
		super(TITLE);
		init();
	}
	
	public RefSellerLP(int widht) {
		super(TITLE, widht);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefSellerDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefSellerTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefSellerED(ent);
	}
	
}
