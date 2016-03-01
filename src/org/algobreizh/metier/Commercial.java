package org.algobreizh.metier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.algobreizh.utils.DatabaseManager;

public class Commercial {
	
	private String nom = "";
	private String prenom = "";
	private String numero = "";
	private String email = "";
	private String idZone = "";
	private String id = "";
	
	public Commercial() {
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIdZone() {
		return idZone;
	}

	public void setIdZone(String idZone) {
		this.idZone = idZone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void getInfoCommercial(int idCommercial)
	{
		DatabaseManager db = DatabaseManager.getInstance();
		
		String sql = "select idCommercial, idZoneGeo, emailCommercial, " +
				"numeroCommercial, nomCommercial, prenomCommercial from commercial where"+
				"`idCommercial` = ? ";
		
		try {
			PreparedStatement stmt = db.prepareStatement(sql);
			
			stmt.setInt(1, idCommercial);
			
			ResultSet res = stmt.executeQuery();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
