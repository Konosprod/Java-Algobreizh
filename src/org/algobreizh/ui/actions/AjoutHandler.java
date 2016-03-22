package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;

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
		    public void windowClosed(WindowEvent e)
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
	    + "values(0,0,?,?,?,?,?,?)";
	    
	    try
	    {
	    	PreparedStatement stmt = db.prepareStatement(sql);
	    	
	    	stmt.setString(1, parent.getCommercial().getIdZone());
	    	stmt.setString(2, nom);
	    	stmt.setString(3, prenom);
	    	stmt.setString(4, particularite);
	    	stmt.setString(5, email);
	    	stmt.setString(6, numero);
	    	
	    	stmt.execute();
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
