package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import org.algobreizh.ui.FenetrePrincipale;
import org.algobreizh.ui.FenetreRdv;
import org.algobreizh.utils.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Alex
 * 
 * Classe qui affiche la fen�tre de selection de rendez-vous.
 * Elle s'occupe aussi de l'insertion danse la base de donn�es
 * du rendez-vous s�lectionn�.
 *
 */
public class RdvHandler implements ActionListener {

	private FenetreRdv fenetreRdv = null;
	private FenetrePrincipale fenetrePrincipale = null;
	
	public RdvHandler(FenetrePrincipale parent) {
		
		fenetrePrincipale = parent;
		
		fenetreRdv = new FenetreRdv();
		
		fenetreRdv.setModal(true);
		fenetreRdv.setLocationRelativeTo(parent);
		
		//Ajoute un listener pour effectuer une action � la
		//fermeture de la fenetreRDV
		fenetreRdv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				insertBdd();
				refreshTabClient();
				super.windowDeactivated(e);			
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		fenetreRdv.setVisible(true);
	}
	
	/**
	 * Insert un RDV dans la base de donn�es
	 */
	private void insertBdd()
	{
	    DatabaseManager db = DatabaseManager.getInstance();
	   
	    Date date = fenetreRdv.getDate();
	    String lieu = fenetreRdv.getLieu();
	    String contact = fenetreRdv.getContact();
	    int idCommercial = Integer.valueOf(fenetrePrincipale.getCommercial().getId());
	    int idClient = fenetrePrincipale.getSelectedIndex();
	    
	    String sql = "insert into `rendezvous` (`idClient`, `contactRendezVous`, "
	    + "`lieuRendezVous`, `idCommercial`, `dateRendezVous`)"
	    + "values(?, ?, ?, ?, ?)";
	    
	    try
	    {
		PreparedStatement stmt = db.prepareStatement(sql);
		
		stmt.setInt(1, idClient);
		stmt.setString(2, contact);
		stmt.setString(3, lieu);
		stmt.setInt(4, idCommercial);
		stmt.setDate(5, new java.sql.Date(date.getTime()));
		
		stmt.executeUpdate();
		
		sql = "select dateRdv from client where idClient = " + String.valueOf(fenetrePrincipale.getSelectedIndex());

		ResultSet res = db.execute(sql);
		res.next();
		
		int dateRdv = res.getInt("dateRdv");
			
		sql = "update client set dateRdv =";
		
	    }
	    catch (Exception e)
	    {
		e.printStackTrace();
	    }
	    
	}
	
	/**
	 * Actualise le tableau de la fen�tre principale des clients
	 * avec le nouveau rendez-vous
	 */
	private void refreshTabClient()
	{
		fenetrePrincipale.refreshTab();
	}
	
}
