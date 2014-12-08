package br.udc.edu.sistemas.util;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;


public class Database {

	private Connection connection;
	private String host;	
	private String port;
	private String databaseName;
	private String user;
	private String password;
	private String url;
	private String driverClassName;
	
	public Database() {
		this.connection = null;
		this.host = "localhost";
		this.port = "5432";
		this.databaseName = "ia5";
		this.user = "postgres";
		this.password = "haroldo";
		this.driverClassName = "org.postgresql.Driver";
		this.url = "jdbc:postgresql://" + this.host + ":" + this.port	+ "/" + this.databaseName;
	}
	
	private void connect() throws SQLException {
		try {
			Class.forName(this.driverClassName);
			this.connection = DriverManager.getConnection(this.url, this.user, this.password);
			this.connection.setAutoCommit(false);
		} catch (ClassNotFoundException e) {
			System.out.println("Classe " + this.driverClassName + " não encontrada");
		} catch (SQLException e) {
			System.out.println("Não foi possível concetar ao banco de dados");
			throw e;
		}
	}
	
	private void disconnect() {
		if (this.connection != null) {
			try {
				this.connection.close();
			} catch (SQLException e) {
			}
		}
	}
	
	public Connection getConnection() throws SQLException {
		if (this.connection == null) {
			this.connect();
		}
		return this.connection;
	}
}
