package me.alessio.warehouse.model;

/*
CREATE TABLE warehouse (
		 id INT PRIMARY KEY AUTO_INCREMENT,
		 location VARCHAR(200) NOT NULL UNIQUE,
		 user_id INT NOT NULL REFERENCES user(id)
		);
*/

public class Warehouse {

	private int id;
	private String location;
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
