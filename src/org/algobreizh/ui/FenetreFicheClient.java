package org.algobreizh.ui;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class FenetreFicheClient extends JFrame {
	
	private JTextField nomClient;
	private JTextField prenomClient;
	private JTextField emailClient;
	private JLabel labelNomClient;
	private JLabel labelPrenomClient;
	private JLabel labelEmailCLient;
	
	public FenetreFicheClient() {
		
	
	this.setTitle("Fiche Client");
    this.setSize(800, 800);
    this.setLocationRelativeTo(null); 
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
    JLabel labelNomClient = new JLabel("Nom : ");
    JLabel labelPrenomClient = new JLabel("Prénom : ");
    JLabel labelEmailClient = new JLabel("Email : ");
    
    JTextField nomClient = new JTextField(20);
    nomClient.setText("José");
    JTextField prenomClient = new JTextField(20);
    prenomClient.setText("Martinez");
    
    
    //Instanciation d'un objet JPanel
    JPanel pan = new JPanel();
    SpringLayout layout = new SpringLayout();
    pan.setLayout(layout);
    
    pan.add(labelNomClient);
    pan.add(nomClient);
    pan.add(labelPrenomClient);
    pan.add(prenomClient);
    
    
    layout.putConstraint(SpringLayout.WEST, labelNomClient, 10, SpringLayout.WEST, pan);
    layout.putConstraint(SpringLayout.NORTH, labelNomClient, 10, SpringLayout.NORTH, pan);
    layout.putConstraint(SpringLayout.NORTH, nomClient, 10, SpringLayout.NORTH, pan);
    layout.putConstraint(SpringLayout.WEST, nomClient, 39, SpringLayout.EAST, labelNomClient);
    
    layout.putConstraint(SpringLayout.WEST, labelPrenomClient, 10, SpringLayout.WEST, pan);
    layout.putConstraint(SpringLayout.NORTH, labelPrenomClient, 30, SpringLayout.NORTH, labelNomClient);
    layout.putConstraint(SpringLayout.NORTH, prenomClient, 30, SpringLayout.NORTH, nomClient);
    layout.putConstraint(SpringLayout.WEST, prenomClient, 20, SpringLayout.EAST, labelPrenomClient);

    
    
    //On prévient notre JFrame que notre JPanel sera son content pane
    this.setContentPane(pan);               
    this.setVisible(true);
	
	
	
}
}
