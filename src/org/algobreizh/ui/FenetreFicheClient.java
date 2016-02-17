package org.algobreizh.ui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FenetreFicheClient extends JFrame {
	
	private JTextField nomClient;
	private JTextField prenomClient;
	private JTextField emailClient;
	private JTextField particularite;
	private JTextField prochainRDVClient;
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
	
	public FenetreFicheClient() {
		
	
	this.setTitle("Fiche Client");
    this.setSize(500, 350);
    this.setLocationRelativeTo(null); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    // Création des JLabel
    labelNomClient = new JLabel("Nom : ");
    labelPrenomClient = new JLabel("Prénom : ");
    labelEmailClient = new JLabel("Email : ");
    labelCodeClient = new JLabel("Code Client : ");
    codeClient = new JLabel("000761");
    labelParticularite = new JLabel("Particularite : ");
    labelDernierRDVClient = new JLabel("Dernier rendez-vous : ");
    labelProchainRDVClient = new JLabel("Prochain rendez-vous : ");
    dernierRDVClient = new JLabel("Mercredi 18 Octobre 2015");
    
    // Création des JtextField
    nomClient = new JTextField(20);
    nomClient.setText("José");
    prenomClient = new JTextField(20);
    prenomClient.setText("Martinez");
    emailClient = new JTextField(20);
    emailClient.setText("Jose.martinez@gmail.com");
    particularite = new JTextField("Le client est en retard sur ses factures n° 45620 et 45872");
    prochainRDVClient = new JTextField("Jeudi 6 Mars 2016");
    
    valider = new JButton("Valider");
    
    //Instanciation d'un objet JPanel
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
    layout.putConstraint(SpringLayout.NORTH, labelParticularite, 60, SpringLayout.NORTH, labelEmailClient);
    layout.putConstraint(SpringLayout.NORTH, particularite, 60, SpringLayout.NORTH, emailClient);
    layout.putConstraint(SpringLayout.WEST, particularite, 34, SpringLayout.EAST, labelParticularite);
    
    // Dernier Rendez-vous et prochain Rendez-vous
    pan.add(labelDernierRDVClient);
    pan.add(labelProchainRDVClient);
    pan.add(dernierRDVClient);
    pan.add(prochainRDVClient);
    layout.putConstraint(SpringLayout.WEST, labelDernierRDVClient, 10, SpringLayout.WEST, pan);
    layout.putConstraint(SpringLayout.NORTH, labelDernierRDVClient, 60, SpringLayout.NORTH, labelParticularite);
    layout.putConstraint(SpringLayout.NORTH, dernierRDVClient, 60, SpringLayout.NORTH, particularite);
    layout.putConstraint(SpringLayout.WEST, dernierRDVClient, 34, SpringLayout.EAST, labelDernierRDVClient);
    layout.putConstraint(SpringLayout.WEST, labelProchainRDVClient, 10, SpringLayout.WEST, pan);
    layout.putConstraint(SpringLayout.NORTH, labelProchainRDVClient, 30, SpringLayout.NORTH, labelDernierRDVClient);
    layout.putConstraint(SpringLayout.NORTH, prochainRDVClient, 30, SpringLayout.NORTH, dernierRDVClient);
    layout.putConstraint(SpringLayout.WEST, prochainRDVClient, 34, SpringLayout.EAST, labelProchainRDVClient);
    
    
    // bouton valider
    pan.add(valider);
    layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, valider, 10, SpringLayout.HORIZONTAL_CENTER, pan);
    layout.putConstraint(SpringLayout.NORTH, valider, 60, SpringLayout.NORTH, prochainRDVClient);
    
    
    //On prévient notre JFrame que notre JPanel sera son content pane
    pan.setLayout(layout);
    this.setContentPane(pan);               
    this.setVisible(true);
	
	
	
}
}
