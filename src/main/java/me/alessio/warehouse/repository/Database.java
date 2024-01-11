package me.alessio.warehouse.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface Database {
	
	List<Map <String,String>> rows (String sql) throws SQLException;
	
	List<Map<String, String>> rows(String sql,String[] parameters) throws SQLException;

	boolean execute (String sql, String[] parameters) throws SQLException;
	
	Map <String,String> row (String sql, String[] parameters) throws SQLException;
	
}
