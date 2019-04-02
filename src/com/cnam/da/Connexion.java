package com.cnam.da;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {

	public static Connection laConnexion() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/bd_cnam?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";
		String login = "root";
		String password = "test";

		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,login,password);//donne moi une connection url de la base de donnee
			System.out.println("Connexion ok"); 
		}
		catch (SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState:" + ((SQLException)ex).getSQLState());
			System.out.println("VendorError:" + ((SQLException)ex).getErrorCode());

		}

		catch(ClassNotFoundException e){
			System.out.println(e.getMessage());
		}
		
		return conn;

	}
}

