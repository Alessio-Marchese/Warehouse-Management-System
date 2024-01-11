package me.alessio.warehouse.repository;

import java.util.List;

import me.alessio.warehouse.model.Test;

//That's a test to see if i can access the data from db

public interface TestRepository {

	List<Test> findAll();
}
