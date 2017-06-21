package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocSale;
import main.Common;

public class DocSaleDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		Date date = super.getDate("date");
		String number = rs.getString("number");
		int sellerId = rs.getInt("id_seller");
		String sellerName = super.getString("sellerName");			// From INNER JOIN
		String sellerSurname = super.getString("sellerSurname");	// From INNER JOIN
		float summa = Common.Round(super.getFloat("summa"), 2);		// From INNER JOIN
		
		return new DocSale(id, date, number, sellerId, sellerName, sellerSurname, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT sales.*, "
				+ "sellers.imie AS sellerName, sellers.nazwisko AS sellerSurname, "
				+ "SUM(sales_tables.summa) AS summa FROM sales "
				+ "INNER JOIN sellers ON sales.id_seller = sellers.idSprzedawca "
				+ "LEFT JOIN sales_tables ON sales.id = sales_tables.id_sale "
				+ "GROUP BY sales.id";
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocSale sale = (DocSale) ent;
		
		String sql = "INSERT INTO sales ("
				+ "date, "
				+ "number, "
				+ "id_seller"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "%s, "	// without <'>
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				super.sqlDateFormat(sale.getDate(), false),
				sale.getNumber(),
				sale.getSellerId()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocSale sale = (DocSale) ent;
		
		String sql = "UPDATE sales SET "
				+ "date=%s, "	// without <'>
				+ "number='%s', "
				+ "id_seller='%s' ";
		
		sql = String.format(
				sql, 
				super.sqlDateFormat(sale.getDate(), false),
				sale.getNumber(),
				sale.getSellerId()
			);
		
		sql += "WHERE id=" + sale.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM sales WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}