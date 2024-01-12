package me.alessio.warehouse.model;

/*
CREATE TABLE article (
		id INT PRIMARY KEY AUTO_INCREMENT,
	    name VARCHAR(200) NOT NULL UNIQUE,
	    description VARCHAR(200) DEFAULT "Description not available",
	    available_quantity INT NOT NULL,
	    price DOUBLE NOT NULL,
	    discount DOUBLE DEFAULT 0,
	    category_id INT NOT NULL REFERENCES category(id)
	);
*/

public class Article {

	private int id;
	private String name;
	private String description;
	private int availableQuantity;
	private double price;
	private double discount;
	private Category categoryId;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Category getCategoryId() {
		return categoryId;
	}

	public void setCategory(Category category) {
		this.categoryId = category;
	}
}
