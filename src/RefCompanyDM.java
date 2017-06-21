package datamanagers;

import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.RefCompany;

public class RefCompanyDM extends DataManager {
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		String name = rs.getString("name");
		String fullname = rs.getString("fullname");
		String code = rs.getString("code");
		Date registered = super.getDate("registered");
		String address = rs.getString("address");
		String phones = rs.getString("phones");
		String emails = rs.getString("emails");
		int bossId = rs.getInt("id_user_boss");
		String bossName = super.getString("boss_name");			// From INNER JOIN
		String bossSurname = super.getString("boss_surname");	// From INNER JOIN
		int accId = rs.getInt("id_user_acc");
		String accName = super.getString("acc_name");			// From INNER JOIN
		String accSurname = super.getString("acc_surname");		// From INNER JOIN
		
		return new RefCompany(id, name, fullname, code, registered, address, phones, emails, bossId, bossName, bossSurname, accId, accName, accSurname);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT companies.*, "
				+ "bosses.imie AS boss_name, bosses.nazwisko AS boss_surname, "
				+ "accs.imie AS acc_name, accs.nazwisko AS acc_surname "
				+ "FROM companies "
				+ "INNER JOIN users AS bosses ON companies.id_user_boss = bosses.id_pracownika "
				+ "INNER JOIN users AS accs ON companies.id_user_acc = accs.id_pracownika";
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		RefCompany company = (RefCompany) ent;
		
		String sql = "INSERT INTO companies ("
				+ "name, "
				+ "fullname, "
				+ "code, "
				+ "registered, "
				+ "address, "
				+ "phones, "
				+ "emails, "
				+ "id_user_boss, "
				+ "id_user_acc"
				+ ") ";
		
		sql += String.format(
				"VALUES ("
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "%s, "	// without <'>
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s', "
				+ "'%s'"
				+ ")",
				
				company.getName(),
				company.getSurname(),
				company.getCode(),
				super.sqlDateFormat(company.getRegistered(), true),
				company.getAddress(),
				company.getPhones(),
				company.getEmails(),
				company.getBossId(),
				company.getAccId()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		RefCompany company = (RefCompany) ent;
		
		String sql = "UPDATE companies SET "
				+ "name='%s', "
				+ "fullname='%s', "
				+ "code='%s', "
				+ "registered=%s, "	// without <'>
				+ "address='%s', "
				+ "phones='%s', "
				+ "emails='%s', "
				+ "id_user_boss='%s', "
				+ "id_user_acc='%s' ";
				
		sql = String.format(
				sql, 
				company.getName(),
				company.getSurname(),
				company.getCode(),
				super.sqlDateFormat(company.getRegistered(), true),
				company.getAddress(),
				company.getPhones(),
				company.getEmails(),
				company.getBossId(),
				company.getAccId()
			);
		
		sql += "WHERE id=" + company.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM companies WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
	/**
	 * Get default company
	 * @return company object
	 */
	public RefCompany getCompany() {
		String sql = String.format("SELECT * FROM companies");
		return (RefCompany) super.getFirstFromRS(sql);
	}
	
}