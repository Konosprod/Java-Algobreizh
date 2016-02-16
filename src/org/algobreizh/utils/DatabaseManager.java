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
 * Classe servant d'interface à la base de données
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
	public static DatabaseManager gestInstance()
	{
		if(instance == null)
			instance =new DatabaseManager();
		
		return instance;
	}
	
	/**
	 * Connect à la base de donnée
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
	 * Execute une requête sur la base de données
	 * @param request La requête SQL à exécuter
	 * @return Le résultat de la requête
	 * @throws Exception
	 */
	public ResultSet execute(String request) throws Exception
	{
		Statement stmt = connection.createStatement();
		
		ResultSet res = stmt.executeQuery(request);
		
		return res;
	}
}
