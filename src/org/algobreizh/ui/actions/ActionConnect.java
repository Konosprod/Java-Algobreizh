package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
				updateRdv();
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
	
	private void updateRdv()
	{
		String sql = "select * from client where dateRdv != 0";
		
		DatabaseManager db = DatabaseManager.getInstance();
		
		try {
			ResultSet res = db.execute(sql);
			
			while(res.next())
			{
				long rdvId = res.getLong("dateRdv");
				
				sql = "select * from rendezvous where idRendezvous = '"+rdvId+"'";
				
				ResultSet res2 = db.execute(sql);
				res2.next();
				
				Date date = res2.getDate("dateRendezvous");
				
				if(date.before(new Date(System.currentTimeMillis())))
				{
					long idClient = res.getLong("idClient");
					sql = "update client set dateRdv = '0', dateDernierRdv='"+rdvId+"'where idClient = '"+idClient+"'";
					db.executeUpdate(sql);
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
