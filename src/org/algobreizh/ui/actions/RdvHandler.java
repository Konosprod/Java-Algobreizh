package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.algobreizh.ui.FenetrePrincipale;
import org.algobreizh.ui.FenetreRdv;
import org.algobreizh.utils.DatabaseManager;

/**
 * @author Alex
 * 
 * Classe qui affiche la fenêtre de selection de rendez-vous.
 * Elle s'occupe aussi de l'insertion danse la base de données
 * du rendez-vous sélectionné.
 *
 */
public class RdvHandler implements ActionListener {

	private FenetreRdv fenetreRdv = null;
	
	public RdvHandler(FenetrePrincipale parent) {
		
		fenetreRdv = new FenetreRdv();
		
		fenetreRdv.setModal(true);
		fenetreRdv.setLocationRelativeTo(parent);
		
		//Ajoute un listener pour effectuer une action à la
		//fermeture de la fenetreRDV
		fenetreRdv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				//Recupérer les infos
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
	 * Insert un RDV dans la base de données
	 */
	private void insertBdd()
	{
		DatabaseManager db = DatabaseManager.getInstance();
		String sql = "";
		
		try {
			db.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Actualise le tableau de la fenêtre principale des clients
	 * avec le nouveau rendez-vous
	 */
	private void refreshTabClient()
	{
		
	}
}
