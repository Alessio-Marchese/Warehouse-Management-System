package me.alessio.warehouse.repository.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This class is used to create a Connection object, just like the other one is based on the pattern singleton
//so i don't waste memory, it just get the properties from the class ConfigProperties and use these properties
//to get a connection from the DriverManager class

public class DataSource {

	private static DataSource instance = new DataSource();

	private static ConfigProperties properties = ConfigProperties.getInstance();

	private DataSource() {
	}

	public static synchronized DataSource getInstance() {
		return instance;
	}

	static {
		try {
			java.lang.Class.forName(properties.getDatabaseDcn());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(properties.getDatabaseUrl(), properties.getDatabaseUsername(),
				properties.getDatabasePassword());
	}
}
