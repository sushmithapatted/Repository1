package utils;

import java.sql.Connection;

import javax.inject.Inject;

import com.typesafe.config.Config;

public class DBConnection {
	private  static Config config;
	static Connection connection;
	@Inject
	public DBConnection(Config config) {
		this.config = config;
	}
	
	public static Connection getSqlConnectionDB() {
		return connection;
	}
}
