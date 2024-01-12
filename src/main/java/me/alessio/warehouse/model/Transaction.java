package me.alessio.warehouse.model;

import java.util.Date;

/*
CREATE TABLE transaction (
		id INT PRIMARY KEY AUTO_INCREMENT,
	    quantity INT NOT NULL,
	    description VARCHAR(200) DEFAULT "Description not available",
	    type ENUM('OUT','IN') NOT NULL,
	    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	    warehouse_id INT NOT NULL REFERENCES warehouse(id)
	);
*/

public class Transaction {

	private int id;
	private int quantity;
	private String description;
	private MyType type;
	private Date transactionDate;
	private Warehouse warehouse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MyType getType() {
		return type;
	}

	public void setType(MyType type) {
		this.type = type;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

}
