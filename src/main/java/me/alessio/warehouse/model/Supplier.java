package me.alessio.warehouse.model;

/*
CREATE TABLE supplier (
		id INT PRIMARY KEY AUTO_INCREMENT,
	    name VARCHAR(200) NOT NULL UNIQUE,
	    email VARCHAR(200) NOT NULL UNIQUE,
	    article_id INT NOT NULL REFERENCES article(id)
	);
*/

public class Supplier {

	private int id;
	private String name;
	private String email;
	private Article article;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

}
