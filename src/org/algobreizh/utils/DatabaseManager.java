package org.algobreizh.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;
import java.sql.Statement;

/**
 * @author Alex
 * 
 * Classe servant d'interface � la base de donn�es
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
	 * R�cup�re l'instance du DatabaseManager
	 * @return L'instance du DatabaseManager
	 */
	public static DatabaseManager getInstance()
	{
		if(instance == null)
			instance =new DatabaseManager();
		
		return instance;
	}
	
	/**
	 * Connect � la base de donn�e
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
	 * Execute une requ�te sur la base de donn�es
	 * @param request La requ�te SQL � ex�cuter
	 * @return Le r�sultat de la requ�te
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
}
