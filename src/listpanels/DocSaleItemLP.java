package listpanels;

import java.util.ArrayList;

import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.EditDialogDoc;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.DocSaleItemDM;
import editdialogs.DocSaleItemED;
import tablemodels.DocSaleItemTM;

public class DocSaleItemLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Sale data";
	
	public DocSaleItemLP(int docId, EditDialogDoc parentDialog) {
		super(TITLE);
		super.parentDialog = parentDialog;
		init(docId);
	}
	
	private void init(int docId) {
		super.entityDataManager = new DocSaleItemDM(docId);
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new DocSaleItemTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		EditDialogDoc parentDialog = super.parentDialog;
		return new DocSaleItemED(ent, parentDialog);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 3, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 4, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 5, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 6, 90, SwingConstants.CENTER);
    }

}