package listpanels;

import java.util.ArrayList;

import main.Common;
import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.RefAccountDM;
import datamanagers.RefUserDM;
import editdialogs.RefAccountED;
import entities.RefAccount;
import tablemodels.RefAccountTM;

public class RefAccountLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Accounts list";
	
	public RefAccountLP() {
		super(TITLE);
		init();
	}
	
	public RefAccountLP(int widht) {
		super(TITLE, widht);
		init();
	}
	
	private void init() {
		super.entityDataManager = new RefAccountDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new RefAccountTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new RefAccountED(ent);
	}
	
	/**
	 * Check if user can delete the selected account
	 */
	@Override
	protected boolean extraDeleteCheck(Entity ent) {
		int id = ent.getId();
		
		if (id == 1) {
	    	Common.showErrorMessage(this, "You can't delete default account!");
	    	return false;
	    }
		
		if (Common.getRegisteredAccount().getId() == id) {
	    	Common.showErrorMessage(this, "You can't delete yours own account!");
	    	return false;
	    }
		
		if (new RefUserDM().isAccountInUse(id)) {
			String login = ((RefAccount) ent).getLogin();
	    	Common.showErrorMessage(this, "Account '" + login + "' is already in use!");
	    	return false;
	    }
		
		return true;
	}

}
