package me.alessio.warehouse.repository.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CrudRepo<T,ID> {

	QueryTemplate qt = QueryTemplate.getInstance();
	
	protected Class<T> clazz;

	public CrudRepo(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public Map<String,String> findById(ID id) {
		Map<String,String> row = null;
		String sql = "SELECT * FROM "+ clazz.getSimpleName().toLowerCase()+" WHERE id = (?)";
		try {
			row = qt.row(sql, new String[] {""+id});
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
	
	public boolean insert(String[] parameters) {
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
		String sql = "INSERT INTO " + clazz.getSimpleName().toLowerCase() + "(";
		for(String att : translatedFields) {
			sql += att;
		}
		sql += ") VALUES (";
		for(int i = 0; i < parameters.length; i++) {
			if(i != parameters.length-1) {
				sql += "?,";
			} else {
				sql += "?)";
			}
		}
		
		try {
			result = qt.execute(sql, parameters);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(e.getMessage().endsWith("'user.email'")) {
				System.out.println("Email already used, try with another");
			}
		}
		return result;
	}
}
