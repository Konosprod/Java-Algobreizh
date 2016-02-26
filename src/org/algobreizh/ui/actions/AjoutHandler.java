package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.algobreizh.ui.FenetreFicheClient;
import org.algobreizh.ui.FenetrePrincipale;
import org.algobreizh.utils.DatabaseManager;

public class AjoutHandler implements ActionListener {

	private FenetrePrincipale parent = null;
	private FenetreFicheClient fenetreAjout = null;
	
	public AjoutHandler(FenetrePrincipale parent) {
		this.parent = parent;
		fenetreAjout = new FenetreFicheClient();
		fenetreAjout.setModal(true);
		fenetreAjout.setLocationRelativeTo(parent);
		
		fenetreAjout.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowDeactivated(WindowEvent e)
		    {
			insertBdd();
			refreshTabClient();
		        super.windowDeactivated(e);
		    }
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fenetreAjout.setVisible(true);
	}
	
	private void insertBdd()
	{
	    DatabaseManager db = DatabaseManager.getInstance();
	    
	    String nom = fenetreAjout.getNomClient();
	    String prenom = fenetreAjout.getPrenomClient();
	    String particularite = fenetreAjout.getParticulariteClient();
	    String email = fenetreAjout.getEmailClient();
	    String numero = fenetreAjout.getTelephoneClient();
	    
	    String sql = "insert into `client` (`dateRdv`, "
	    + "`dateDernierRdv`, `idzoneGeo`, `nomClient`, `prenomClient`,"
	    + " `particulariteClient`, `emailClient`, `numeroClient`)"
	    + "values(0,0,9,'"+ nom +"','"+ prenom + "','"
	    + particularite+ "','" + email +"','"+numero+"')";
	    
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
