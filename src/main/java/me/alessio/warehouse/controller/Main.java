package me.alessio.warehouse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.alessio.warehouse.model.User;
import me.alessio.warehouse.repository.impl.UserRepositoryImpl;

//That's a test to see if i can access the data from db

public class Main {

	public static void main(String[] args) {
	UserRepositoryImpl userRepo = UserRepositoryImpl.getInstance();
		
	//FINDALL TEST
	List<Map<String, String>> users = userRepo.findAll();
	for(Map<String,String> row : users) {
		System.out.println(row.toString());
	}
	
	//FINDBYID TEST
	if(userRepo.findById(2) != null) {
		Map<String,String> user2 = userRepo.findById(1);
		System.out.println(user2.toString());
	} else {
		System.out.println("user not found");
	}
	
		
	//INSERT TEST
		System.out.println(userRepo.findAll());
		
		System.out.println(userRepo.findById(1));
		List<String> parameters = new ArrayList<String>();
		parameters.add("email10");
		parameters.add("username10");
		parameters.add("password10");
		
		if(userRepo.insert(parameters)) {
			System.out.println("added");
		} else {
			System.out.println("not added");
		}

		
	//UPDATE TEST	
	User user = new User();
	user.setId(2);
	user.setEmail("emai19");
	user.setUsername("username19");
	user.setPassword("password19");
	
		if(userRepo.update(user)) {
			System.out.println("updated");
		} else {
			System.out.println("not updated");
		}
		
	//DELETE TEST
		if(userRepo.delete(user)) {
			System.out.println("deleted");
		} else {
			System.out.println("not deleted");
		}
	}
	
	public static List<String> convert(List<Map<String,String>> parameters) {
		List<String> keys = new ArrayList<String>();
		List<String> values = new ArrayList<String>();
		for(Map<String,String> row : parameters) {
			String key = row.keySet().iterator().next();
			keys.add(key);
			String value = row.get(key);
			values.add(value);
		}
		return values;
	}
}
