package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefClient;

public class RefClientDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("idKlient");
		String name = rs.getString("imie");
		String surname = rs.getString("nazwisko");
		int points = rs.getInt("iloscPunktow");
		String postalCode = rs.getString("kodPocztowy");
		
		return new RefClient(id, name, surname, points, postalCode);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM clients");
	}

	@Override
	public void addEntity(Entity ent) {
		RefClient client = (RefClient) ent;
		
		String sql = "INSERT INTO clients ("
				+ "iloscPunktow, "
				+ "imie, "
				+ "nazwisko, "
				+ "kodPocztowy"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				client.getPoints(),
				client.getName(),
				client.getSurname(),
				client.getPostalCode()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefClient client = (RefClient) ent;
		
		String sql = "UPDATE clients SET "
				+ "iloscPunktow='%s', "
				+ "imie='%s', "
				+ "nazwisko='%s', "
				+ "kodPocztowy='%s' ";
		
		sql = String.format(
				sql, 
				client.getPoints(),
				client.getName(),
				client.getSurname(),
				client.getPostalCode()
			);
		
		sql += "WHERE idKlient=" + client.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM clients WHERE idKlient=%d", id);
		super.executeUpdate(sql);
	}
	
}