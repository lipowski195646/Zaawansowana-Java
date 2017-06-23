package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefStore;

public class RefStoreDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("idMagazynier");
		String name = rs.getString("imie");
		String surname = rs.getString("nazwisko");
		
		return new RefStore(id, name, surname);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM stores");
	}

	@Override
	public void addEntity(Entity ent) {
		RefStore store = (RefStore) ent;
		
		String sql = "INSERT INTO stores ("
				+ "imie, "
				+ "nazwisko"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				store.getName(),
				store.getSurname()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefStore store = (RefStore) ent;
		
		String sql = "UPDATE stores SET "
				+ "imie='%s', "
				+ "nazwisko='%s' ";
		
		sql = String.format(
				sql, 
				store.getName(),
				store.getSurname()
			);
		
		sql += "WHERE idMagazynier=" + store.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM stores WHERE idMagazynier=%d", id);
		super.executeUpdate(sql);
	}
	
}