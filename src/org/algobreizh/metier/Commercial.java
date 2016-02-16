package org.algobreizh.metier;
import java.util.*;
public class Commercial {
	private  int idCommercial;	
	private  ArrayList<Client> clients;
	private String email;
	private String nom;
	private String prenom;	
	/**
	 * @param nom Nom du Commercial
	 * @param prenom Prenom du Commercial
	 * @param email Email du Commercial
	 */
	public Commercial(String nom ,String prenom ,String email){
		this.setNom(nom) ;
		this.setPrenom(prenom);
		this.setEmail(email);
		
		clients = new ArrayList<Client>();
	}

	/**
	 * @return l'idCommercial
	 */
	public int getIdCommercial() {
		return idCommercial;
	}

	/**
	 * @param idCommercial
	 */
	public void setIdCommercial(int idCommercial) {
		this.idCommercial = idCommercial;
	}

	/**
	 * retourne l'email du commercial
	 * @return l'email du commercial
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Modifie l'email du commercial
	 * @param email du commercial
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * retourne le nom du Commercial
	 * @return nom Commercial
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Modifie le nom du Commercial
	 * @param nom du Commercial
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Retourne le prenom du Commercial
	 * @return prenom du Commercial
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * modifie le prenom du Commercial
	 * @param prenom du Commercial
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}