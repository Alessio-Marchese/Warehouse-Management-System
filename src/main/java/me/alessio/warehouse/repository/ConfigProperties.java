package me.alessio.warehouse.repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

//This class is used to retrieve data from the "data.properties" file stored in src/main/resources.
 
public class ConfigProperties {

	//I declare a private instance and initialize it immediately since this object isn't optional
	private static final ConfigProperties instance = new ConfigProperties();

	//I declare properties object here so it's visible to the other methods
	private Properties properties;

	//When i call the constructor i'm also initializing the properties
	private ConfigProperties() {
		this.properties = new Properties();
		loadProperties();
	}
	
	//This is the method that i need to load the file inside properties
	private void loadProperties() {
		try (FileInputStream input = new FileInputStream("src/main/resources/data.properties")) {
			properties.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//This is the method that return the private attribute of ConfigProperties
	public static ConfigProperties getInstance() {
		return instance;
	}

	//These are the methods that i use to get the properties stored inside data.properties file
	public String getDatabaseUrl() {
		return properties.getProperty("database.url");
	}

	public String getDatabaseUsername() {
		return properties.getProperty("database.username");
	}

	public String getDatabasePassword() {
		return properties.getProperty("database.password");
	}
	
	public String getDatabaseDcn() {
		return properties.getProperty("database.dcn");
	}
}
