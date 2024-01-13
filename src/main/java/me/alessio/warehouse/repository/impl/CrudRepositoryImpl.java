package me.alessio.warehouse.repository.impl;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import me.alessio.warehouse.repository.CrudRepository;
import me.alessio.warehouse.repository.util.QueryTemplate;

public abstract class CrudRepositoryImpl<T,ID> implements CrudRepository<T,ID>{

	QueryTemplate qt = QueryTemplate.getInstance();
	
	private static StringBuilder sqlBuilder = new StringBuilder();
	
	protected Class<T> clazz;

	public CrudRepositoryImpl(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	@Override
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
	
	@Override
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
	
	@Override
	public boolean insert(T entity) {
		Field[] classFields = clazz.getDeclaredFields();
		List<Field> entityFields = Arrays.stream(classFields).peek(field -> field.setAccessible(true)).collect(Collectors.toList());
		List<String> values = new ArrayList<String>();
		//This starts from 1 because it want to skip the ID since it will be auto_increment in MySQL
		for(int i = 1; i < entityFields.size(); i++) {
			try {
				values.add(entityFields.get(i).get(entity).toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		sqlBuilder.append("INSERT INTO " + clazz.getSimpleName().toLowerCase() + "(");
		//Starts from 1 becouse it want to skit the ID
		for(int i = 1; i < classFields.length; i++) {
			sqlBuilder.append(classFields[i].getName());
			if(i != classFields.length-1) {
				sqlBuilder.append(",");
			}
		}
		sqlBuilder.append(") VALUES ("); 
		for(int i = 0; i < values.size(); i++) {
			if(i != values.size()-1) {
				sqlBuilder.append("?,");
			} else {
				sqlBuilder.append("?)");
			}
		}
		
		try {
			return qt.execute(sqlBuilder.toString(), values);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
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
	
	@Override
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
