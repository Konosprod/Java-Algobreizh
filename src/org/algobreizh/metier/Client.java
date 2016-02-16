package org.algobreizh.metier;

public class Client {
	
	private String nom;
	private String prenom;
	private String particularite;
	private String email;
	private RendezVous dateRDV;
	private RendezVous dateDernierRDV;
	
	public Client() {
		
	}
	
	
	/**
	 * @return le nom du client
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom du client
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return le prénom du client
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * @param prenom du client
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return la particularite du client
	 */
	public String getParticularite() {
		return particularite;
	}
	/**
	 * @param particularite du client
	 */
	public void setParticularite(String particularite) {
		this.particularite = particularite;
	}
	/**
	 * @return le mail du client
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email du client
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return la date de rendez-vous
	 */
	public RendezVous getDateRDV() {
		return dateRDV;
	}
	/**
	 * @param dateRDV date de rendez-vous
	 */
	public void setDateRDV(RendezVous dateRDV) {
		this.dateRDV = dateRDV;
	}
	/**
	 * @return la date du dernier rendez-vous
	 */
	public RendezVous getDateDernierRDV() {
		return dateDernierRDV;
	}
	/**
	 * @param dateDernierRDV date du dernier rendez-vous
	 */
	public void setDateDernierRDV(RendezVous dateDernierRDV) {
		this.dateDernierRDV = dateDernierRDV;
	}
	
	
	
}
