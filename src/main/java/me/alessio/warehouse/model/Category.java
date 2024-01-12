package me.alessio.warehouse.model;

/*
CREATE TABLE category (
		id INT PRIMARY KEY AUTO_INCREMENT,
	    name VARCHAR(200) NOT NULL UNIQUE,
		warehouse_id INT NOT NULL REFERENCES warehouse(id)
	);
*/

public class Category {

	private int id;
	private String name;
	private Warehouse warehouse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}
