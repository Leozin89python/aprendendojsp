package br.com.aocp.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnetion {

	private static String banco = "jdbc:postgresql://localhost:5432/aprendendojsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "postgres";
	private static Connection connection = null;

	static {
		conectar();
	}

	public SingletonConnetion() {
		conectar();
	}

	private static void conectar() {
		try {
			/* 
			  //******************POSTGRESQL*****************
			if (connection == null) {
				Class.forName("org.postgresql.Driver");
				connection = DriverManager.getConnection(banco, user, password);
				connection.setAutoCommit(false);
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			}
			//***********************************************
			 */
			
			
			//******************MYSQL*****************
			if (connection == null) {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/aprendendojsp", "admin", "admin");
				connection.setAutoCommit(false);
				connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
			}
			//***********************************************
		} catch (Exception e) {
			throw new RuntimeException("Erro ao conectar com a base de dados."
					+ e);
		}

	}

	public static Connection getConnection() {
		return connection;
	}

}
