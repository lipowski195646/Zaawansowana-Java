package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefProduct;

public class RefProductDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		String name = rs.getString("nazwa");
		String unit = rs.getString("unit");
		int points = rs.getInt("punkty");
		int warranty = rs.getInt("dlugosc_gwarancji");
		float priceIn = rs.getFloat("cena_zakupu");
		float priceOut = rs.getFloat("cena_sprzedazy");
		
		return new RefProduct(id, name, unit, points, warranty, priceIn, priceOut);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM products");
	}

	@Override
	public void addEntity(Entity ent) {
		RefProduct product = (RefProduct) ent;
		
		String sql = "INSERT INTO products ("
				+ "nazwa, "
				+ "unit, "
				+ "punkty, "
				+ "dlugosc_gwarancji, "
				+ "cena_zakupu, "
				+ "cena_sprzedazy"
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
				
				product.getName(),
				product.getUnit(),
				product.getPoints(),
				product.getWarranty(),
				product.getPriceIn(),
				product.getPriceOut()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefProduct product = (RefProduct) ent;
		
		String sql = "UPDATE products SET "
				+ "nazwa='%s', "
				+ "unit='%s' , "
				+ "punkty='%s' , "
				+ "dlugosc_gwarancji='%s', "
				+ "cena_zakupu='%s' , "
				+ "cena_sprzedazy='%s'";
		
		sql = String.format(
				sql, 
				product.getName(),
				product.getUnit(),
				product.getPoints(),
				product.getWarranty(),
				product.getPriceIn(),
				product.getPriceOut()
			);
		
		sql += "WHERE id=" + product.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM products WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
}