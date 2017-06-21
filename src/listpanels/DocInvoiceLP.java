package listpanels;

import java.util.ArrayList;

import javax.swing.SwingConstants;

import superclasses.EditDialog;
import superclasses.Entity;
import superclasses.ListPanel;
import superclasses.TableModel;
import datamanagers.DocInvoiceDM;
import editdialogs.DocInvoiceED;
import tablemodels.DocInvoiceTM;

public class DocInvoiceLP extends ListPanel {
	private static final long serialVersionUID = 1L;
	private static String TITLE = "Invoices";
	
	public DocInvoiceLP() {
		super(TITLE);
		init();
	}
	
	public DocInvoiceLP(int width) {
		super(TITLE, width);
		init();
	}
	
	private void init() {
		super.entityDataManager = new DocInvoiceDM();
		super.loadEntities();
	}
	
	@Override
	protected TableModel getTableModel(ArrayList<Entity> entities) {
		return new DocInvoiceTM(entities);
	}
	
	@Override
	protected EditDialog getEditDialog(Entity ent) {
		return new DocInvoiceED(ent);
	}
	
	@Override
	protected void decorateTable() {
		super.decorateTable();
		super.decorateTableColumn(entityTable, 1, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 2, 90, SwingConstants.CENTER);
		super.decorateTableColumn(entityTable, 5, 110, SwingConstants.RIGHT);
    }
	
}
