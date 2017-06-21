package datamanagers;

import java.sql.SQLException;
import java.util.ArrayList;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefAccount;

public class RefAccountDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		int id = rs.getInt("id");
		String login = rs.getString("login");
		String password = rs.getString("haslo");
		String role = rs.getString("uprawnienia");
		
		return new RefAccount(id, login, password, role);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		return super.getListFromRS("SELECT * FROM accounts");
	}

	@Override
	public void addEntity(Entity ent) {
		RefAccount acc = (RefAccount) ent;
		
		String sql = "INSERT INTO accounts ("
				+ "login, "
				+ "haslo, "
				+ "uprawnienia"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s' "
				+ ")",
				
				acc.getLogin(),
				acc.getPassword(),
				acc.getRole()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefAccount acc = (RefAccount) ent;
		
		String sql = "UPDATE accounts SET "
				+ "login='%s', "
				+ "haslo='%s', "
				+ "uprawnienia='%s' ";
		
		sql = String.format(
				sql, 
				acc.getLogin(),
				acc.getPassword(),
				acc.getRole()
			);
		
		sql += "WHERE id=" + acc.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM accounts WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
	/**
	 * If account already exists
	 * @param id account id
	 * @param login account login
	 * @return result of search
	 */
	public boolean isAccountExists(int id, String login) {
		String sql = String.format("SELECT * FROM accounts WHERE id!=%d AND login LIKE '%s'", id, login);
		return (super.getResultSetSize(sql) != 0);
	}
	
	/**
	 * For login dialog - find the account object with given login and password
	 * @param login login
	 * @param password password
	 * @return found account object (or null)
	 */
	public RefAccount getAccountByLoginPassword(String login, String password) {
		String sql = String.format("SELECT * FROM accounts WHERE login LIKE '%s' AND haslo LIKE '%s'", login, password);
		return (RefAccount) super.getFirstFromRS(sql);
	}

}