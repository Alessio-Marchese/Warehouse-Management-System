package me.alessio.warehouse.repository.util;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public abstract class CrudRepo<T,ID> {

	QueryTemplate qt = QueryTemplate.getInstance();
	
	private static StringBuilder sqlBuilder = new StringBuilder();
	
	protected Class<T> clazz;

	public CrudRepo(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public Map<String,String> findById(ID id) {
		Map<String,String> row = null;
		String sql = "SELECT * FROM "+ clazz.getSimpleName().toLowerCase()+" WHERE id = (?)";
		List<String> parameters = new ArrayList<String>();
		parameters.add(""+id);
		try {
			row = qt.row(sql, parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
	
	public List<Map<String,String>> findAll() {
		List<Map<String,String>> rows = null;
		String sql = "SELECT * FROM "+ clazz.getSimpleName().toLowerCase();
		try {
			rows = qt.rows(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}
	
	public boolean insert(List<String> parameters) {
		boolean result = false;
		Field[] fields = clazz.getDeclaredFields();
		List<String> translatedFields = new ArrayList<String>();
		for(int i = 0; i < fields.length; i++) {
			if(fields[i].getName().equals("id")) {
				continue;
			}
			if (i != fields.length-1) {
				translatedFields.add(fields[i].getName()+",");
			} else {
				translatedFields.add(fields[i].getName());
			}
			
		}
		
		sqlBuilder.append("INSERT INTO " + clazz.getSimpleName().toLowerCase() + "(");
		for(String att : translatedFields) {
			sqlBuilder.append(att);
		}
		sqlBuilder.append(") VALUES ("); 
		for(int i = 0; i < parameters.size(); i++) {
			if(i != parameters.size()-1) {
				sqlBuilder.append("?,");
			} else {
				sqlBuilder.append("?)");
			}
		}
		
		try {
			result = qt.execute(sqlBuilder.toString(), parameters);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(e.getMessage().endsWith("'user.email'")) {
				System.out.println("Email already used, try with another");
			}
		}
		return result;
	}
	
	public boolean update(T entity) {
		Field[] classFields = clazz.getDeclaredFields();
		List<Field> entityFields = Arrays.stream(classFields).peek(field -> field.setAccessible(true)).collect(Collectors.toList());
		List<String> values = new ArrayList<String>();
		List<String> fieldNames = new ArrayList<String>();
		for(int i = 0; i < entityFields.size(); i++) {
			fieldNames.add(entityFields.get(i).getName());
			try {
				values.add(entityFields.get(i).get(entity).toString());
				if(i == entityFields.size()-1) {
					values.add(entityFields.get(0).get(entity).toString());
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append("UPDATE " + clazz.getSimpleName().toLowerCase() + " SET ");
		 for (int i = 0; i < entityFields.size(); i++) {
	            sqlBuilder.append(fieldNames.get(i)).append(" = ?");
	            if (i < entityFields.size() - 1) {
	                sqlBuilder.append(", ");
	            }
	        }

	        sqlBuilder.append(" WHERE ").append(fieldNames.get(0)).append(" = ?");
	        for(String string : values) {
	        	System.out.println(string);
	        }
	        try {
				qt.execute(sqlBuilder.toString(), values);
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return false;
	}
	
	public boolean delete(T entity) {
		Field[] classFields = clazz.getDeclaredFields();
		List<Field> entityFields = Arrays.stream(classFields).peek(field -> field.setAccessible(true)).collect(Collectors.toList());
		List<String> parameters = new ArrayList<String>();
		try {
			parameters.add(entityFields.get(0).get(entity).toString());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql = "DELETE FROM "+ clazz.getSimpleName().toLowerCase()+" WHERE id = (?)";
		try {
			qt.execute(sql, parameters);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
}
