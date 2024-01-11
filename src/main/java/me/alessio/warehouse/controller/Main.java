package me.alessio.warehouse.controller;

import java.util.List;

import me.alessio.warehouse.model.Test;
import me.alessio.warehouse.repository.TestRepository;
import me.alessio.warehouse.repository.impl.TestRepositoryImpl;

//That's a test to see if i can access the data from db

public class Main {

	public static void main(String[] args) {
		TestRepository testRepo = new TestRepositoryImpl();
		List<Test> tests = testRepo.findAll();
		for(Test test : tests) {
			System.out.println(test.getId());
		}
	}

}
