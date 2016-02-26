package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.algobreizh.ui.FenetreFicheClient;
import org.algobreizh.ui.FenetrePrincipale;
import org.algobreizh.utils.DatabaseManager;

public class EditHandler implements ActionListener {

	private FenetrePrincipale parent = null;
	private FenetreFicheClient fenetreEdit = null;
	
	public EditHandler(FenetrePrincipale parent) {
		this.parent = parent;
		
		fenetreEdit = new FenetreFicheClient();
		fenetreEdit.setLocationRelativeTo(parent);
		fenetreEdit.setModal(true);
	
		fenetreEdit.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				insertBdd();
				refreshTabClient();
				super.windowDeactivated(e);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		int index = parent.getSelectedIndex();	
		fenetreEdit.chargerClient(index);
		fenetreEdit.setVisible(true);
	}
	
	private void insertBdd()
	{
	    DatabaseManager db = DatabaseManager.getInstance();
	    
	    String nom = fenetreEdit.getNomClient();
	    String prenom = fenetreEdit.getPrenomClient();
	    String particularite = fenetreEdit.getParticulariteClient();
	    String email = fenetreEdit.getEmailClient();
	    String codeClient = fenetreEdit.getIdClient();
	    String numero = fenetreEdit.getTelephoneClient();
	    
	    String sql = "update  `client`"
		    + "set `nomClient`='" + nom
		    +"',`prenomClient`='" + prenom
		    +"',`particulariteClient`='" + particularite
		    +"',`emailClient`='" + email
		    +"',`numeroClient`='" + numero
		    +"' where `idClient`=" + codeClient;
	    
	    try
	    {
		db.executeUpdate(sql);
	    }
	    catch (Exception e)
	    {
		e.printStackTrace();
	    }
	}
	
	private void refreshTabClient()
	{
	    parent.refreshTab();
	}
}
