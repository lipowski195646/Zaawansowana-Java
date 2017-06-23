package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefSeller;

public class RefSellerDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("idSprzedawca");
		String name = rs.getString("imie");
		String surname = rs.getString("nazwisko");
		
		return new RefSeller(id, name, surname);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM sellers");
	}

	@Override
	public void addEntity(Entity ent) {
		RefSeller seller = (RefSeller) ent;
		
		String sql = "INSERT INTO sellers ("
				+ "imie, "
				+ "nazwisko"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				seller.getName(),
				seller.getSurname()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefSeller seller = (RefSeller) ent;
		
		String sql = "UPDATE sellers SET "
				+ "imie='%s', "
				+ "nazwisko='%s' ";
		
		sql = String.format(
				sql, 
				seller.getName(),
				seller.getSurname()
			);
		
		sql += "WHERE idSprzedawca=" + seller.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM sellers WHERE idSprzedawca=%d", id);
		super.executeUpdate(sql);
	}
	
}