package superclasses;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import main.Common;

/**
 * Abstract class - the base for the database connect classes
 * (package "datamanagers") 
 * @author Vlad
 *
 */
public abstract class DataManager {
	final private static String DB_DRIVER = "com.mysql.jdbc.Driver";
	final private static String DB_URL = "jdbc:mysql://localhost:3306/gui_base";
	final private static String DB_USER = "root";
	final private static String DB_PASSWORD = "";
	
	/**
	 * Subclasses fill this result set 
	 */
	protected ResultSet rs;
	
	/**
	 * Create the entity object from database record
	 * @return entity object
	 * @throws SQLException if sql error
	 */
	protected abstract Entity getEntityByFields() throws SQLException;
	
	/**
	 * Add entity to the database
	 * @param ent entity to add
	 */
	protected abstract void addEntity(Entity ent);  
	
	/**
	 * Update the database data for the entity
	 * @param ent entity to update
	 */
	protected abstract void updateEntity(Entity ent); 
	
	/**
	 * Delete entity from the database
	 * @param id entity id
	 */
	protected abstract void deleteEntity(int id); 
	
	/**
	 * Get array list of the entities from the database
	 * @return entity array list as result of the sql query
	 */
	public abstract ArrayList<Entity> getEntityList();  
	
	/**
	 * Set database connection
	 * @return database connection
	 */
	private static Connection getDBConnection() {
	    Connection dbConnection = null;
	    try {
	        Class.forName(DB_DRIVER);
	    } catch (ClassNotFoundException e) {
	    	Common.getCommonInstance().showErrorMessage(null, e.getMessage());
	    }
	    try {
	        dbConnection = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
	        return dbConnection;
	    } catch (SQLException e) {
	    	Common.getCommonInstance().showErrorMessage(null, e.getMessage());
	    }
	    return dbConnection;
	}
	
	/**
	 * Update database 
	 * @param sql query to execute
	 */
	protected void executeUpdate(String sql) {
		Connection dbConnection = null;
	    Statement st = null;
		
		try {
	        dbConnection = getDBConnection();
	        st = (Statement) dbConnection.createStatement();
	        st.executeUpdate(sql);
	    } catch (SQLException e) {
	    	Common.getCommonInstance().showErrorMessage(null, e.getMessage());
	    } finally {
	    	try { dbConnection.close(); } catch(SQLException se) {se.printStackTrace();}
            try { st.close(); } catch(SQLException se) {se.printStackTrace();}
            try { rs.close(); } catch(SQLException se) {se.printStackTrace();}
	    }

	}
	
	/**
	 * Execute SQL query, convert result set to array list of entities
	 * @param sql query to execute
	 * @return array list of entities
	 */
	protected ArrayList<Entity> getListFromRS(String sql) {
		Connection dbConnection = null;
	    Statement st = null;
	    rs = null;
	    
		ArrayList<Entity> list = new ArrayList<Entity>();
		
		try {
	        dbConnection = getDBConnection();
	        st = (Statement) dbConnection.createStatement();
	        rs = st.executeQuery(sql);
	        
	        if (rs != null) {
				try {
					while (rs.next()) {
					 	list.add( getEntityByFields() );
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
	        }
	        
	    } catch (SQLException e) {
	    	Common.getCommonInstance().showErrorMessage(null, e.getMessage());
	    } finally {
	    	try { dbConnection.close(); } catch(SQLException se) {se.printStackTrace();}
            try { st.close(); } catch(SQLException se) {se.printStackTrace();}
            try { rs.close(); } catch(SQLException se) {se.printStackTrace();}
	    }
		
		return list;
	}
	
	/**
	 * Get the size of the result set
	 * @param sql query to execute
	 * @return size of the result set
	 */
	protected int getResultSetSize(String sql) {
		Connection dbConnection = null;
	    Statement st = null;
	    rs = null;
	    int size = 0;
		
		try {
	        dbConnection = getDBConnection();
	        st = (Statement) dbConnection.createStatement();
	        rs = st.executeQuery(sql);
	        
	        if (rs != null)
	        	if (rs.last())
	        		  size = rs.getRow();
	        
	    } catch (SQLException e) {
	    	Common.getCommonInstance().showErrorMessage(null, e.getMessage());
	    } finally {
	    	try { dbConnection.close(); } catch(SQLException se) {se.printStackTrace();}
            try { st.close(); } catch(SQLException se) {se.printStackTrace();}
            try { rs.close(); } catch(SQLException se) {se.printStackTrace();}
	    }
		
		return size;
	}
	
	/**
	 * Get first element from the query result
	 * @param sql query to execute
	 * @return first entity of the result
	 */
	protected Entity getFirstFromRS(String sql) {
		ArrayList<Entity> list = getListFromRS(sql);
		return list.size() == 0 ? null : list.get(0);
	}
	
	/**
	 * Try to get date value from the result set field
	 * @param field result set field
	 * @return date value or null
	 */
	protected Date getDate(String field) {
		Date dt = null;
		try {
			dt = rs.getDate(field);
		} catch (Exception e) {}
		return dt;
	}
	
	/**
	 * Try to get string value from the result set field
	 * @param field result set field
	 * @return string value or empty string
	 */
	protected String getString(String field) {
		String s = "";
		try {
			s = rs.getString(field);
		} catch (Exception e) {}
		return s;
	}
	
	/**
	 * Try to get int value from the result set field
	 * @param field result set field
	 * @return int value or zero
	 */
	protected int getInt(String field) {
		int n = 0;
		try {
			n = rs.getInt(field);
		} catch (Exception e) {}
		return n;
	}
	
	/**
	 * Try to get float value from the result set field
	 * @param field result set field
	 * @return float value or zero
	 */
	protected float getFloat(String field) {
		float f = 0;
		try {
			f = rs.getFloat(field);
		} catch (Exception e) {}
		return f;
	}
	
	/**
	 * Convert date value to string
	 * @param d date to convert
	 * @param nullMode type of display null date (for NotNull database fields) 
	 * @return string with date
	 */
	protected String sqlDateFormat(Date d, boolean nullMode) {
		String res;
		if (d == null)
			res = nullMode ? "NULL" : "'0000-00-00'"; 
		else
			res = "'" + (new SimpleDateFormat("yyyy-MM-dd")).format(d) + "'";
		return res;
	}
	
}