package br.univille.poo.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FabricaDeConexoes {
	
	private static FabricaDeConexoes instance;

	private static String banco = "bancomatheus";
	private static String usuario = "root";
	private static String senha = "root";
	
	private FabricaDeConexoes() {}

	public static FabricaDeConexoes getInstance() {
		if(instance == null) {
			instance = new FabricaDeConexoes();
		}
		return instance;
	}
	
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/"+banco+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",usuario,senha);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
