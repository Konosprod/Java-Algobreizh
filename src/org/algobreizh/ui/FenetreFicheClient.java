package org.algobreizh.ui;


import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.algobreizh.ui.actions.ValidateListener;
import org.algobreizh.utils.DatabaseManager;
import org.algobreizh.utils.Utils;

public class FenetreFicheClient extends JDialog {
	
	private static final long serialVersionUID = 491780379919375191L;
	
	private JTextField nomClient;
	private JTextField prenomClient;
	private JTextField emailClient;
	private JTextField particularite;
	private JTextField telephoneClient;
	private JLabel labelTelephoneClient;
	private JLabel prochainRDVClient;
	private JLabel labelParticularite;
	private JLabel labelNomClient;
	private JLabel labelPrenomClient;
	private JLabel labelEmailClient;
	private JLabel labelCodeClient;
	private JLabel codeClient;
	private JLabel dernierRDVClient;
	private JLabel labelProchainRDVClient;
	private JLabel labelDernierRDVClient;
	private JButton valider;
	private boolean etat = true;
	
	public FenetreFicheClient() {
		
	
	this.setTitle("Algobreizh - Fiche Client");
    this.setSize(500, 400);
    
	ImageIcon icon = new ImageIcon("ressources/icon.png");
	this.setIconImage(icon.getImage());
	this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
 
    // Création des JLabel
    labelNomClient = new JLabel("Nom : ");
    labelPrenomClient = new JLabel("Prénom : ");
    labelEmailClient = new JLabel("Email : ");
    labelTelephoneClient = new JLabel("Téléphone : ");
    labelCodeClient = new JLabel("Code Client : ");
    codeClient = new JLabel("");
    labelParticularite = new JLabel("Particularite : ");
    labelDernierRDVClient = new JLabel("Dernier rendez-vous : ");
    labelProchainRDVClient = new JLabel("Prochain rendez-vous : ");
    dernierRDVClient = new JLabel("");
    prochainRDVClient = new JLabel("");
    
    // Création des JtextField
    nomClient = new JTextField(20);
    prenomClient = new JTextField(20);
    emailClient = new JTextField(20);
    particularite = new JTextField(30);
    telephoneClient = new JTextField(8);
    
    if (etat == true)
    {
    	nomClient.setEditable(true);
    	prenomClient.setEditable(true);
    	emailClient.setEditable(true);
    	particularite.setEditable(true);
    }
    else
    {
    	nomClient.setEditable(false);
    	prenomClient.setEditable(false);
    	emailClient.setEditable(false);
    	particularite.setEditable(false);
    }
    
    valider = new JButton("Valider");
    
    buildLayout();
    
    
    valider.addActionListener(new ValidateListener(this));
    
}
	
	public String getNomClient()
	{
		return nomClient.getText();
	}
	
	public String getPrenomClient()
	{
		return prenomClient.getText();
	}
	
	public String getEmailClient()
	{
		return emailClient.getText();
	}
	
	public String getParticulariteClient()
	{
		return particularite.getText();
	}
	
	public String getIdClient()
	{
	    return codeClient.getText();
	}
	
	public String getTelephoneClient()
	{
	    return telephoneClient.getText();
	}
	
	public void chargerClient(long id)
	{
		DatabaseManager db = DatabaseManager.getInstance();
		
		try {
			ResultSet res = db.execute("SELECT nomClient, prenomClient, emailClient, particulariteClient, numeroClient, dateRdv, dateDernierRdv FROM client WHERE idClient = " + String.valueOf(id));
			res.next();
			nomClient.setText(res.getString("nomClient"));
			prenomClient.setText(res.getString("prenomClient"));
			emailClient.setText(res.getString("emailClient"));
			particularite.setText(res.getString("particulariteClient"));
			codeClient.setText(String.format("%05d", id));
			telephoneClient.setText(res.getString("numeroClient"));
			
			if(res.getInt("dateRdv") != 0)
			{
				ResultSet dateRdv = db.execute("select dateRendezVous from rendezvous where `idRendezvous` = " + String.valueOf(res.getInt("dateRdv")));
				dateRdv.next();
				prochainRDVClient.setText(Utils.formatDateFromSql(dateRdv.getString("dateRendezVous")));
			}
			else
			{
				prochainRDVClient.setText("Aucun");
			}
			
			if(res.getInt("dateDernierRdv") != 0)
			{
				ResultSet dateDernierRdv = db.execute("select dateRendezVous from rendezvous where `idRendezvous` = " + String.valueOf(res.getInt("dateDernierRdv")));
				dateDernierRdv.next();
				dernierRDVClient.setText(Utils.formatDateFromSql(dateDernierRdv.getString("dateRendezVous")));
			}
			else
			{
				dernierRDVClient.setText("Aucun");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void buildLayout()
	{
	    JPanel pan = new JPanel();
	    SpringLayout layout = new SpringLayout();
	    
	    pan.add(codeClient);
	    pan.add(labelCodeClient);
	    
	    // Prenom du client
	    pan.add(labelNomClient);
	    pan.add(nomClient);
	    layout.putConstraint(SpringLayout.WEST, labelNomClient, 10, SpringLayout.WEST, pan);
	    layout.putConstraint(SpringLayout.NORTH, labelNomClient, 10, SpringLayout.NORTH, pan);
	    layout.putConstraint(SpringLayout.NORTH, nomClient, 10, SpringLayout.NORTH, pan);
	    layout.putConstraint(SpringLayout.WEST, nomClient, 39, SpringLayout.EAST, labelNomClient);
	    
	    // Prenom du client
	    pan.add(labelPrenomClient);
	    pan.add(prenomClient);
	    layout.putConstraint(SpringLayout.WEST, labelPrenomClient, 10, SpringLayout.WEST, pan);
	    layout.putConstraint(SpringLayout.NORTH, labelPrenomClient, 30, SpringLayout.NORTH, labelNomClient);
	    layout.putConstraint(SpringLayout.NORTH, prenomClient, 30, SpringLayout.NORTH, nomClient);
	    layout.putConstraint(SpringLayout.WEST, prenomClient, 20, SpringLayout.EAST, labelPrenomClient);

	    // Email du client
	    pan.add(labelEmailClient);
	    pan.add(emailClient);
	    layout.putConstraint(SpringLayout.WEST, labelEmailClient, 10, SpringLayout.WEST, pan);
	    layout.putConstraint(SpringLayout.NORTH, labelEmailClient, 30, SpringLayout.NORTH, labelPrenomClient);
	    layout.putConstraint(SpringLayout.NORTH, emailClient, 30, SpringLayout.NORTH, prenomClient);
	    layout.putConstraint(SpringLayout.WEST, emailClient, 34, SpringLayout.EAST, labelEmailClient);
	    
	 // Telephone du client
	    pan.add(labelTelephoneClient);
	    pan.add(telephoneClient);
	    layout.putConstraint(SpringLayout.WEST, labelTelephoneClient, 10, SpringLayout.WEST, pan);
	    layout.putConstraint(SpringLayout.NORTH, labelTelephoneClient, 30, SpringLayout.NORTH, labelEmailClient);
	    layout.putConstraint(SpringLayout.NORTH, telephoneClient, 30, SpringLayout.NORTH, emailClient);
	    layout.putConstraint(SpringLayout.WEST, telephoneClient, 0, SpringLayout.WEST, emailClient);
	    
	    // Code du client
	    pan.add(labelCodeClient);
	    pan.add(codeClient);
	    layout.putConstraint(SpringLayout.EAST, labelCodeClient, -5, SpringLayout.WEST, codeClient);
	    layout.putConstraint(SpringLayout.NORTH, labelCodeClient, 10, SpringLayout.NORTH, pan);
	    layout.putConstraint(SpringLayout.NORTH, codeClient, 10, SpringLayout.NORTH, pan);
	    layout.putConstraint(SpringLayout.EAST, codeClient, -20, SpringLayout.EAST, pan);
	    
	    // Particularite du client
	    pan.add(labelParticularite);
	    pan.add(particularite);
	    layout.putConstraint(SpringLayout.WEST, labelParticularite, 10, SpringLayout.WEST, pan);
	    layout.putConstraint(SpringLayout.NORTH, labelParticularite, 60, SpringLayout.NORTH, labelTelephoneClient);
	    layout.putConstraint(SpringLayout.NORTH, particularite, 60, SpringLayout.NORTH, telephoneClient);
	    layout.putConstraint(SpringLayout.WEST, particularite, 34, SpringLayout.EAST, labelTelephoneClient);
	    
	    // Dernier Rendez-vous
	    pan.add(labelDernierRDVClient);
	    pan.add(dernierRDVClient);
	    layout.putConstraint(SpringLayout.WEST, labelDernierRDVClient, 10, SpringLayout.WEST, pan);
	    layout.putConstraint(SpringLayout.NORTH, labelDernierRDVClient, 60, SpringLayout.NORTH, labelParticularite);
	    layout.putConstraint(SpringLayout.NORTH, dernierRDVClient, 60, SpringLayout.NORTH, particularite);
	    layout.putConstraint(SpringLayout.WEST, dernierRDVClient, 34, SpringLayout.EAST, labelDernierRDVClient);
	    
	    // Prochain Rendez-vous
	    pan.add(labelProchainRDVClient);
	    pan.add(prochainRDVClient);
	    layout.putConstraint(SpringLayout.WEST, labelProchainRDVClient, 10, SpringLayout.WEST, pan);
	    layout.putConstraint(SpringLayout.NORTH, labelProchainRDVClient, 30, SpringLayout.NORTH, labelDernierRDVClient);
	    layout.putConstraint(SpringLayout.NORTH, prochainRDVClient, 30, SpringLayout.NORTH, dernierRDVClient);
	    layout.putConstraint(SpringLayout.WEST, prochainRDVClient, 25, SpringLayout.EAST, labelProchainRDVClient);
	    
	    
	    // bouton valider
	    pan.add(valider);
	    layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, valider, 10, SpringLayout.HORIZONTAL_CENTER, pan);
	    layout.putConstraint(SpringLayout.NORTH, valider, 60, SpringLayout.NORTH, prochainRDVClient);
	    
	    
	    //On prévient notre JFrame que notre JPanel sera son content pane
	    pan.setLayout(layout);
	    this.setContentPane(pan); 
	}
}
