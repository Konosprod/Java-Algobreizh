package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.algobreizh.ui.FenetreConnexion;
import org.algobreizh.ui.FenetrePrincipale;
import org.algobreizh.utils.DatabaseManager;
import org.algobreizh.utils.Utils;

public class ActionConnect implements ActionListener {

	private FenetreConnexion parent;
	private JFrame nextFrame = null;
	private String password;
	private String idCommercial;
	private JPasswordField passEntry;
	private JTextField idLogin;
	
	DatabaseManager db = DatabaseManager.getInstance();
	
	public ActionConnect(FenetreConnexion parent, JPasswordField passEntry, JTextField idEntry)
	{
		this.parent = parent;
		this.idLogin = idEntry;
		this.passEntry = passEntry;
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		this.password = new String(passEntry.getPassword());
		this.idCommercial = new String(idLogin.getText());
		
		 try {
			ResultSet hash = db.execute("SELECT hash FROM connexion WHERE idCommercial = " + idCommercial);
			hash.next();
			
			password = Utils.calculStringSHA256(password);
			
			if (hash.getString("hash").equals(password))
			{
				parent.setVisible(false);
				nextFrame = new FenetrePrincipale(Integer.valueOf(idCommercial));
				nextFrame.setLocationRelativeTo(parent);
				nextFrame.setVisible(true);
			}
			
		} catch (Exception e1) {
			parent.afficherMsgErreur(true);
			e1.printStackTrace();
		}
	}
}
