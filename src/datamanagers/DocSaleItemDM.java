package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocSaleItem;

public class DocSaleItemDM extends DataManager {
	private int saleId;
	
	public DocSaleItemDM() {
		this.saleId = 0;
	}
	
	public DocSaleItemDM(int saleId) {
		this.saleId = saleId;
	}
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		int storeId = rs.getInt("id_store");
		String storeName = super.getString("storeName");		// From INNER JOIN
		String storeSurname = super.getString("storeSurname");	// From INNER JOIN
		int productId = rs.getInt("id_product");
		String productName = super.getString("productName");	// From INNER JOIN
		String productUnit = super.getString("productUnit");	// From INNER JOIN
		float quantity = rs.getFloat("quantity");
		float price = rs.getFloat("price");
		float summa = rs.getFloat("summa");
		
		return new DocSaleItem(id, storeId, storeName, storeSurname, productId, productName, productUnit, quantity, price, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT sales_tables.*, "
				+ "stores.imie AS storeName, stores.nazwisko AS storeSurname, "
				+ "products.nazwa AS productName, products.unit AS productUnit "
				+ "FROM sales_tables "
				+ "INNER JOIN stores ON sales_tables.id_store = stores.idMagazynier "
				+ "INNER JOIN products ON sales_tables.id_product = products.id "
				+ "WHERE sales_tables.id_sale=%d ORDER BY sales_tables.id";
		
		sql = String.format(sql, saleId);
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocSaleItem saleItem = (DocSaleItem) ent;
		
		String sql = "INSERT INTO sales_tables ("
				+ "id_sale, "
				+ "id_store, "
				+ "id_product, "
				+ "quantity, "
				+ "price, "
				+ "summa"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				saleId,
				saleItem.getStoreId(),
				saleItem.getProductId(),
				saleItem.getQuantity(),
				saleItem.getPrice(), 
				saleItem.getSumma()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocSaleItem saleItem = (DocSaleItem) ent;
		
		String sql = "UPDATE sales_tables SET "
				+ "id_store='%s', "
				+ "id_product='%s', "
				+ "quantity='%s' ,"
				+ "price='%s' ,"
				+ "summa='%s' ";
		
		sql = String.format(
				sql, 
				saleItem.getStoreId(),
				saleItem.getProductId(),
				saleItem.getQuantity(),
				saleItem.getPrice(), 
				saleItem.getSumma()
			);
		
		sql += "WHERE id=" + saleItem.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM sales_tables WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}