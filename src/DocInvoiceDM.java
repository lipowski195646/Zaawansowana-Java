package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocInvoice;
import main.Common;

public class DocInvoiceDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		Date date = super.getDate("date");
		String number = rs.getString("number");
		int clientId = rs.getInt("id_client");
		String clientName = super.getString("clientName");			// From INNER JOIN
		String clientSurname = super.getString("clientSurname");	// From INNER JOIN
		int storeId = rs.getInt("id_store");
		String storeName = super.getString("storeName");		// From INNER JOIN
		String storeSurname = super.getString("storeSurname");		// From INNER JOIN
		float summa = Common.Round(super.getFloat("summa"), 2);		// From INNER JOIN
		
		return new DocInvoice(id, date, number, clientId, clientName, clientSurname, storeId, storeName, storeSurname, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT invoices.*, "
				+ "clients.imie AS clientName, clients.nazwisko AS clientSurname, "
				+ "stores.imie AS storeName, stores.nazwisko AS storeSurname, "
				+ "SUM(invoices_tables.summa) AS summa FROM invoices "
				+ "INNER JOIN clients ON invoices.id_client = clients.idKlient "
				+ "INNER JOIN stores ON invoices.id_store = stores.idMagazynier "
				+ "LEFT JOIN invoices_tables ON invoices.id = invoices_tables.id_invoice "
				+ "GROUP BY invoices.id";
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocInvoice invoice = (DocInvoice) ent;
		
		String sql = "INSERT INTO invoices ("
				+ "date, "
				+ "number, "
				+ "id_client, "
				+ "id_store"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "%s, "	// without <'>
				+ "'%s', "
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				super.sqlDateFormat(invoice.getDate(), false),
				invoice.getNumber(),
				invoice.getClientId(),
				invoice.getStoreId()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocInvoice invoice = (DocInvoice) ent;
		
		String sql = "UPDATE invoices SET "
				+ "date=%s, "	// without <'>
				+ "number='%s', "
				+ "id_client='%s', "
				+ "id_store='%s' ";
		
		sql = String.format(
				sql, 
				super.sqlDateFormat(invoice.getDate(), false),
				invoice.getNumber(),
				invoice.getClientId(),
				invoice.getStoreId()
			);
		
		sql += "WHERE id=" + invoice.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM invoices WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}