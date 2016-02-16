package org.algobreizh.metier;

/**
 * @author Flavien
 * 
 *
 */
public class RendezVous {
	
	private String contactRDV;
	private String lieuRDV;
	private long dateRDV;
	
	public RendezVous() {
		
	}
	
	
	/**
	 * @return le nom du contact pour le rendez-vous
	 */
	public String getContactRDV() {
		return contactRDV;
	}

	/**
	 * 
	 * @param contactRDV nom du contact pour le rendez-vous
	 */
	public void setContactRDV(String contactRDV) {
		this.contactRDV = contactRDV;
	}

	/**
	 * @return le lieu du rendez-vous
	 */
	public String getLieuRDV() {
		return lieuRDV;
	}

	/**
	 * @param lieuRDV lieu du rendez-vous
	 */
	public void setLieuRDV(String lieuRDV) {
		this.lieuRDV = lieuRDV;
	}

	/**
	 * @return la date du rendez-vous
	 */
	public long getDateRDV() {
		return dateRDV;
	}

	/**
	 * @param dateRDV date du rendez-vous
	 */
	public void setDateRDV(long dateRDV) {
		this.dateRDV = dateRDV;
	}

}
