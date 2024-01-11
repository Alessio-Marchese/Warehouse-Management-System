package me.alessio.warehouse.controller;


import java.sql.SQLException;
import java.util.Map;

import me.alessio.warehouse.repository.Database;
import me.alessio.warehouse.repository.impl.DatabaseImpl;

//That's a test to see if i can access the data from db

public class Main {

	public static void main(String[] args) {
		Database db = DatabaseImpl.getInstance();
		try {
			Map<String, String> row = db.row("select * from test where id = (?)",new String[] {"4"});
			System.out.println(row.get("id"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
