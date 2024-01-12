package me.alessio.warehouse.controller;

import me.alessio.warehouse.repository.impl.UserRepositoryImpl;

//That's a test to see if i can access the data from db

public class Main {

	public static void main(String[] args) {
		UserRepositoryImpl userRepo = UserRepositoryImpl.getInstance();
		
		System.out.println(userRepo.findAll());
		
		System.out.println(userRepo.findById(1));
		
		boolean check = userRepo.insert(new String[] {"email5","username5","password5"});
		System.out.println(check);
	}
}
