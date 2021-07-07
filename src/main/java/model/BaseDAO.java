package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public static Connection getConnection() {
		try {
		  String url = "jdbc:mariadb://localhost:3306/leilao";
		  return DriverManager.getConnection(url, "matheus", "123456");
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(BaseDAO.getConnection());
	}

}
