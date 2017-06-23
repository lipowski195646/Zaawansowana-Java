package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefUser;

public class RefUserDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id_pracownika");
		int accountId = super.getInt("id_konta");
		String login = super.getString("login");	// From INNER JOIN
		String name = rs.getString("imie");
		String surname = rs.getString("nazwisko");
		String socialId = rs.getString("PESEL");
		String state = rs.getString("stanowisko");
		float salary = rs.getFloat("salary");
		float premia = rs.getFloat("premia");
		Date dateIn = super.getDate("data_zatrudnienia");
		Date dateOut = super.getDate("data_zwolnienia");
		String address = rs.getString("adres");
		
		return new RefUser(id, accountId, login, name, surname, socialId, state, salary, premia, dateIn, dateOut, address);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT users.*, "
				+ "accounts.login AS login "
				+ "FROM users "
				+ "LEFT JOIN accounts ON users.id_konta = accounts.id "
				+ "ORDER BY users.id_pracownika";
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		RefUser user = (RefUser) ent;
		
		String sql = "INSERT INTO users ("
				+ "id_konta, "
				+ "imie, "
				+ "nazwisko, "
				+ "PESEL, "
				+ "stanowisko, "
				+ "salary, "
				+ "premia, "
				+ "data_zatrudnienia, "
				+ "data_zwolnienia, "
				+ "adres"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "%s, "	// without <'>
				+ "%s, "	// without <'>
				+ "'%s'"
				+ ")",
				
				user.getAccountId(),
				user.getName(),
				user.getSurname(),
				user.getSocialId(),
				user.getState(),
				user.getSalary(),
				user.getPremia(),
				super.sqlDateFormat(user.getDateIn(), false),
				super.sqlDateFormat(user.getDateOut(), true),
				user.getAddress()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefUser user = (RefUser) ent;
		
		String sql = "UPDATE users SET "
				+ "id_konta='%s', "
				+ "imie='%s', "
				+ "nazwisko='%s', "
				+ "PESEL='%s', "
				+ "stanowisko='%s', "
				+ "salary='%s', "
				+ "premia='%s', "
				+ "data_zatrudnienia=%s, "	// without <'>
				+ "data_zwolnienia=%s, "	// without <'>
				+ "adres='%s' ";
		
		sql = String.format(
				sql, 
				user.getAccountId(),
				user.getName(),
				user.getSurname(),
				user.getSocialId(),
				user.getState(),
				user.getSalary(),
				user.getPremia(),
				super.sqlDateFormat(user.getDateIn(), false),
				super.sqlDateFormat(user.getDateOut(), true),
				user.getAddress()
			);
		
		sql += "WHERE id_pracownika=" + user.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM users WHERE id_pracownika=%d", id);
		super.executeUpdate(sql);
	}
	
	/**
	 * If account is already in use for another user
	 * @param id user id
	 * @param accountId account id
	 * @return result of search
	 */
	public boolean isAccountInUse(int id, int accountId) {
		String sql = String.format("SELECT * FROM users WHERE id_pracownika!=%d AND id_konta=%d", id, accountId);
		return (super.getResultSetSize(sql) != 0);
	}
	
	/**
	 * If account is already in use
	 * @param accountId account id
	 * @return result of search
	 */
	public boolean isAccountInUse(int accountId) {
		String sql = String.format("SELECT * FROM users WHERE id_konta=%d", accountId);
		return (super.getResultSetSize(sql) != 0);
	}
	
	/**
	 * Find the user for the selected account
	 * @param accountId account id
	 * @return user for the selected account (or null)
	 */
	public RefUser getUserByAccount(int accountId) {
		String sql = String.format("SELECT * FROM users WHERE id_konta=%d", accountId);
		return (RefUser) super.getFirstFromRS(sql);
	}
	
}