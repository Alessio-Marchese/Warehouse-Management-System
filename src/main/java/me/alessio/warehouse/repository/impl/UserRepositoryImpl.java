package me.alessio.warehouse.repository.impl;

import me.alessio.warehouse.model.User;
import me.alessio.warehouse.repository.UserRepository;
import me.alessio.warehouse.repository.util.QueryTemplate;

public class UserRepositoryImpl extends CrudRepositoryImpl<User,Integer> implements UserRepository{
	
	private static UserRepositoryImpl instance = new UserRepositoryImpl();

	private UserRepositoryImpl() {
		super(User.class);
	}

	public static UserRepositoryImpl getInstance() {
		return instance;
	}
}
