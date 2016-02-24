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
 * Classe qui affiche la fen�tre de selection de rendez-vous.
 * Elle s'occupe aussi de l'insertion danse la base de donn�es
 * du rendez-vous s�lectionn�.
 *
 */
public class RdvHandler implements ActionListener {

	private FenetreRdv fenetreRdv = null;
	
	public RdvHandler(FenetrePrincipale parent) {
		
		fenetreRdv = new FenetreRdv();
		
		fenetreRdv.setModal(true);
		fenetreRdv.setLocationRelativeTo(parent);
		
		//Ajoute un listener pour effectuer une action � la
		//fermeture de la fenetreRDV
		fenetreRdv.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				//Recup�rer les infos
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
		String sql = "";
		
		try {
			db.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Actualise le tableau de la fen�tre principale des clients
	 * avec le nouveau rendez-vous
	 */
	private void refreshTabClient()
	{
		
	}
}
