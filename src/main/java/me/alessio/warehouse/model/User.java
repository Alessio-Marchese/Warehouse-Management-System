package me.alessio.warehouse.model;

/*
CREATE TABLE user (
		id INT PRIMARY KEY AUTO_INCREMENT,
	    email VARCHAR(200) NOT NULL UNIQUE,
	    username VARCHAR(20) NOT NULL UNIQUE,
	    password VARCHAR(20) NOT NULL
	);
*/

public class User {

	private int id;
	private String email;
	private String username;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
