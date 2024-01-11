package me.alessio.warehouse.repository.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import me.alessio.warehouse.model.Test;
import me.alessio.warehouse.repository.TestRepository;
import me.alessio.warehouse.repository.util.DBUtil;
import me.alessio.warehouse.repository.util.DataSource;

//That's a test to see if i can access the data from db

public class TestRepositoryImpl implements TestRepository{

	private static DataSource ds = DataSource.getInstance();

	@Override
	public List<Test> findAll() {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Test> tests = new ArrayList<Test>();
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement("SELECT * FROM test");
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Test test = new Test();
				test.setId(resultSet.getInt(1));
				tests.add(test);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(resultSet);
			DBUtil.close(preparedStatement);
			DBUtil.close(connection);
		}
		return tests;
	}
	
	
	
}
