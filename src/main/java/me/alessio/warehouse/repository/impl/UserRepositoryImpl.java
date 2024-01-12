package me.alessio.warehouse.repository.impl;

import me.alessio.warehouse.model.User;
import me.alessio.warehouse.repository.util.CrudRepo;

public class UserRepositoryImpl extends CrudRepo<User,Integer>{
	
	private static UserRepositoryImpl instance = new UserRepositoryImpl();

	private UserRepositoryImpl() {
		super(User.class);
	}

	public static UserRepositoryImpl getInstance() {
		return instance;
	}
}
