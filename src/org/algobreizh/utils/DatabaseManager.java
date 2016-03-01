package org.algobreizh.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.Statement;

/**
 * @author Alex
 * 
 * Classe servant d'interface ï¿½ la base de donnï¿½es
 *
 */
public class DatabaseManager {

	private static DatabaseManager instance = null;
	private Properties properties;
	private Connection connection;
	
	
	private DatabaseManager()
	{
		properties = new Properties();
		
		loadProperties();
		
		connect();
	}
	
	/**
	 * Récupère l'instance du DatabaseManager
	 * @return L'instance du DatabaseManager
	 */
	public static DatabaseManager getInstance()
	{
		if(instance == null)
			instance =new DatabaseManager();
		
		return instance;
	}
	
	/**
	 * Connect ï¿½ la base de donnï¿½e
	 */
	private void connect()
	{ 
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();	
			String url = "jdbc:"+properties.getProperty("databaseUrl")+properties.getProperty("database");
			String username = properties.getProperty("databaseUser");
			String password = properties.getProperty("databasePassword");
	
			connection = DriverManager.getConnection(url, username, password);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Charge les configurations
	 */
	private void loadProperties()
	{
		try {
			properties.load(new FileInputStream("config/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Execute une requï¿½te sur la base de donnï¿½es
	 * @param request La requï¿½te SQL ï¿½ exï¿½cuter
	 * @return Le rï¿½sultat de la requï¿½te
	 * @throws Exception
	 */
	public ResultSet execute(String request) throws Exception
	{
		Statement stmt = connection.createStatement();
		
		ResultSet res = stmt.executeQuery(request);
		
		return res;
	}
	
	public void executeUpdate(String request) throws Exception
	{
	    Statement stmt = connection.createStatement();
	    
	    stmt.executeUpdate(request);
	}
	
	public PreparedStatement prepareStatement(String sql) throws Exception
	{
		return connection.prepareStatement(sql);
	}
}
