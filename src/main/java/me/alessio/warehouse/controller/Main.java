package me.alessio.warehouse.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.alessio.warehouse.model.User;
import me.alessio.warehouse.repository.UserRepository;
import me.alessio.warehouse.repository.impl.UserRepositoryImpl;

//That's a test to see if i can access the data from db

public class Main {

	public static void main(String[] args) {
	UserRepository userRepo = UserRepositoryImpl.getInstance();
		
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
		User user = new User();
		//for now since the id is ignored inside the method the actual id that will be added is the one from
		user.setId(51);
		user.setEmail("email501");
		user.setUsername("username501");
		user.setPassword("password501");
		
		if(userRepo.insert(user)) {
			System.out.println("added");
		} else {
			System.out.println("not added");
		}

		
	//UPDATE TEST	
	User user3 = new User();
	user3.setId(4);
	user3.setEmail("20");
	user3.setUsername("20");
	user3.setPassword("20");
	
		if(userRepo.update(user3)) {
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
