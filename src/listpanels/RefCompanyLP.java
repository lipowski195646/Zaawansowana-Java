package listpanels;

import java.util.ArrayList;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefCompanyDM;
import editdialogs.RefCompanyED;
import tablemodels.RefCompanyTM;

public class RefCompanyLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Companies list";
	
	public RefCompanyLP() {
		super(TITLE);
		init();
	}
	
	public RefCompanyLP(int widht) {
		super(TITLE, widht);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefCompanyDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefCompanyTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefCompanyED(ent);
	}
	
}