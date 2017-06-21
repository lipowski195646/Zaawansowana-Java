package datamanagers;

import java.util.ArrayList;
import java.sql.SQLException;

import superclasses.DataManager;
import superclasses.Entity;
import entities.DocSalaryItem;

public class DocSalaryItemDM extends DataManager {
	private int salaryId;
	
	public DocSalaryItemDM() {
		this.salaryId = 0;
	}
	
	public DocSalaryItemDM(int salaryId) {
		this.salaryId = salaryId;
	}
	
	@Override
	protected Entity getEntityByFields() throws SQLException {
		// rs - resource set from superclass DataManager
		
		int id = rs.getInt("id");
		int userId = rs.getInt("id_user");
		String userName = super.getString("userName");			// From INNER JOIN
		String userSurname = super.getString("userSurname");	// From INNER JOIN
		float salaryRef = rs.getFloat("salary_user");
		int workDays = rs.getInt("work_days");
		float salaryCalc = rs.getFloat("salary");
		float premiaRef = rs.getFloat("premia_percent");
		float premiaCalc = rs.getFloat("premia_summa");
		float summa = rs.getFloat("summa");
		
		return new DocSalaryItem(id, userId, userName, userSurname, salaryRef, workDays, salaryCalc, premiaRef, premiaCalc, summa);
	}
	
	@Override
	public ArrayList<Entity> getEntityList() {
		String sql = "SELECT salaries_tables.*, "
				+ "users.imie AS userName, users.nazwisko AS userSurname "
				+ "FROM salaries_tables "
				+ "INNER JOIN users ON salaries_tables.id_user = users.id_pracownika "
				+ "WHERE salaries_tables.id_salary=%d ORDER BY salaries_tables.id";
		
		sql = String.format(sql, salaryId);
		
		return super.getListFromRS(sql);
	}

	@Override
	public void addEntity(Entity ent) {
		DocSalaryItem salaryItem = (DocSalaryItem) ent;
		
		String sql = "INSERT INTO salaries_tables ("
				+ "id_salary, "
				+ "id_user, "
				+ "salary_user, "
				+ "work_days, "
				+ "salary, "
				+ "premia_percent, "
				+ "premia_summa, "
				+ "summa"
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
				+ "'%s'"
				+ ")",
				
				salaryId,
				salaryItem.getUserId(),
				salaryItem.getSalaryRef(),
				salaryItem.getWorkDays(),
				salaryItem.getSalaryCalc(),
				salaryItem.getPremiaRef(),
				salaryItem.getPremiaCalc(),
				salaryItem.getSumma()
			);
		
		super.executeUpdate(sql);
	}

	@Override
	public void updateEntity(Entity ent) {
		DocSalaryItem salaryItem = (DocSalaryItem) ent;
		
		String sql = "UPDATE salaries_tables SET "
				+ "id_user='%s', "
				+ "salary_user='%s', "
				+ "work_days='%s' ,"
				+ "salary='%s' ,"
				+ "premia_percent='%s' ,"
				+ "premia_summa='%s' ,"
				+ "summa='%s' ";
		
		sql = String.format(
				sql, 
				salaryItem.getUserId(),
				salaryItem.getSalaryRef(),
				salaryItem.getWorkDays(),
				salaryItem.getSalaryCalc(),
				salaryItem.getPremiaRef(),
				salaryItem.getPremiaCalc(),
				salaryItem.getSumma()
			);
		
		sql += "WHERE id=" + salaryItem.getId();
		
		super.executeUpdate(sql);
	}

	@Override
	public void deleteEntity(int id) {
		String sql = String.format("DELETE FROM salaries_tables WHERE id=%d", id);
		super.executeUpdate(sql);
	}
	
	/**
	 * If salary document contains data for selected user
	 * @param id salary id
	 * @param userId user id
	 * @return result of the search
	 */
	public boolean isUserSalaryExists(int id, int userId) {
		String sql = String.format("SELECT * FROM salaries_tables WHERE id_salary=%d AND id!=%d AND id_user=%d", salaryId, id, userId);
		return (super.getResultSetSize(sql) != 0);
	}
	
}