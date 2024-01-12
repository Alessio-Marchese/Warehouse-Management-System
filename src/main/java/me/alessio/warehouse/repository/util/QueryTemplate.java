package me.alessio.warehouse.repository.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import me.alessio.warehouse.repository.Database;

/*	This Singleton class wants to be a Facade pattern to simplify the creation of queries my idea is that
 *  i will use this template inside the entity repositories so i can develop the project much faster
 *  i have only described the first method since the other ones are similar to it
 *  thanks to Prof. Ferdinando Primerano and Stefano Rubinetti who provided the base of this code inside their book
 */

public class QueryTemplate implements Database {
	
	private static QueryTemplate instance = new QueryTemplate();

	DataSource ds = DataSource.getInstance();
	
	public static QueryTemplate getInstance() {
		return instance;
	}
	
	protected QueryTemplate() {
		
	}
	
	/*	Examples of query that work with this method
	 * 	List<Map<String, String>> rows = db.rows("select * from test where name = (?)", new String[] {"name1"});
			 for(Map<String,String> row : rows) {
				 System.out.println(row.get("id"));
			 }
	 */
	@Override
	public List<Map<String, String>> rows(String sql, String[] parameters) throws SQLException {
		//Initialize a list of map String(column(key),value) wich is going to be the result of this method
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		//get the connection from DataSource and get ps from the connection 
		Connection connection = ds.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		//After the sql query is prepared inside the ps we need to add parameters to it
		for (int i = 0; i < parameters.length; i++)
			//for how many parameters we have we set the string parameter of the i+1 index(becouse parameters set starts from 1)
			ps.setString(i + 1, parameters[i]);
		//at the end we can executeQuery(); if we give to the method more or less parameters than the ones that we declare
		//inside the sql(query) we will get an error index out of bound
		ResultSet rs = ps.executeQuery();
		//As long as there is a new result inside the rs
		while (rs.next()) {
			//we create a temporary Map (still column(key),value)
			Map<String, String> row = new LinkedHashMap<String, String>();
			//for every column inside the result set
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				//we put inside the temporary Map the columnLabel of the index i+1(still becouse the pointer of rs starts from 1)
				//and for that key we set the value wich is given from rs.getString(i+1)
				//and then we add to the list of Map this single temporary Map
				//basically we translate the key : value of the sql table (Column : Value) into a Map
				row.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getString(i + 1));
			}
			res.add(row);
		}
		DBUtil.close(rs);
		DBUtil.close(ps);
		DBUtil.close(connection);
		return res;
	}

	/*  Examples of queries that work with this method
	 *  execute("delete from test where id = (?)",new String[] {"3"});
	 *  execute("update test set name = (?) where id = (?)",new String[] {"Name1","37"});
	 *  execute("insert into test values(?,?,?)",new String[] {"1","Name1","Surname1"});
	 *  It return a boolean so we can check on that to print the success execution of the method or the failure
	 */
	
	@Override
	public boolean execute(String sql,String[] parameters) throws SQLException {
		Connection connection = ds.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		for(int i=0;i<parameters.length;i++)
			ps.setString(i+1,parameters[i]);
		int check = ps.executeUpdate();
		DBUtil.close(ps);
		DBUtil.close(connection);
		if (check == 1)
		return true;
		
		return false;
	}

	/*	Examples of query that work with this method
	 * 	List<Map<String, String>> rows = db.rows("select * from test");
			 for(Map<String,String> row : rows) {
				 System.out.println(row.get("id"));
			 }
	 */
	@Override
	public List<Map<String, String>> rows(String sql) throws SQLException {
		List<Map<String, String>> res = new ArrayList<Map<String, String>>();
		Connection connection = ds.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Map<String, String> row = new LinkedHashMap<String, String>();
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				row.put(rs.getMetaData().getColumnLabel(i + 1).toLowerCase(), rs.getString(i + 1));
			}
			res.add(row);
		}
		DBUtil.close(rs);
		DBUtil.close(ps);
		DBUtil.close(connection);
		return res;
	}

	//I wanted to add this method so that the developer could use a more intuitive method when he just need a single row
	@Override
	public Map<String, String> row(String sql, String[] parameters) throws SQLException {
		List<Map<String, String>> rows = rows(sql,parameters);
		return rows.size() == 1 ? rows.get(0) : null;
	}

}
