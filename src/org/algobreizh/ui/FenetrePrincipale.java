package org.algobreizh.ui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

public class FenetrePrincipale extends JFrame {
	
	static final long serialVersionUID = -2620432862549001611L;

	private JPanel mainPane;
	private SpringLayout layout;
	private JLabel nomCommercial;
	private JLabel emailCommercial;
	private JLabel prenomCommercial;
	private JLabel numeroCommercial;
	private JTable tabClient;
	private JButton buttonEdit;
	private JButton buttonRdv;
	private JButton buttonAjout;
	private JButton buttonSuppr;
	
	public FenetrePrincipale() {
		
		mainPane = new JPanel();
		layout = new SpringLayout();
		nomCommercial = new JLabel("Rambo");
		emailCommercial = new JLabel("john.rambo@algobreizh.fr");
		prenomCommercial = new JLabel("John");
		numeroCommercial = new JLabel("0649784545");
		tabClient = new JTable();
		buttonEdit = new JButton("Editer");
		buttonRdv = new JButton("RDV");
		buttonAjout = new JButton("Ajout");
		buttonSuppr = new JButton("Suppr");
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Nom Prenom commercial
		mainPane.add(nomCommercial);
		mainPane.add(prenomCommercial);
		layout.putConstraint(SpringLayout.WEST, nomCommercial, 10, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.NORTH, mainPane, 10, SpringLayout.NORTH, prenomCommercial);
		layout.putConstraint(SpringLayout.NORTH, mainPane, 10, SpringLayout.NORTH, nomCommercial);
		layout.putConstraint(SpringLayout.WEST, prenomCommercial, 10, SpringLayout.EAST, nomCommercial);
		
		//Numero et email
		mainPane.add(emailCommercial);
		mainPane.add(numeroCommercial);
		layout.putConstraint(SpringLayout.WEST, emailCommercial, 10, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.NORTH, emailCommercial, 20, SpringLayout.SOUTH, prenomCommercial);
		layout.putConstraint(SpringLayout.NORTH, numeroCommercial, 20, SpringLayout.SOUTH, prenomCommercial);
		layout.putConstraint(SpringLayout.WEST, numeroCommercial, 10, SpringLayout.EAST, emailCommercial);
		
		//Tableau client
		tabClient = new JTable(new String[][]{{"Test", "test", "test"},{"Test1", "test2", "test3"}}, new String[] {"H1", "H2", "H3"});
		tabClient.setFillsViewportHeight(true);
		JScrollPane scrollPan = new JScrollPane(tabClient);
		mainPane.add(scrollPan);
		
		layout.putConstraint(SpringLayout.WEST, scrollPan, 10, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.NORTH, scrollPan, 20, SpringLayout.SOUTH, emailCommercial);
		layout.putConstraint(SpringLayout.SOUTH, scrollPan, -10, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.EAST, scrollPan, -100, SpringLayout.EAST, mainPane);
		
		//Boutton Rendez-vous
		mainPane.add(buttonRdv);
		layout.putConstraint(SpringLayout.EAST, buttonRdv, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.WEST, buttonRdv, 10, SpringLayout.EAST, scrollPan);
		layout.putConstraint(SpringLayout.NORTH, buttonRdv, 20, SpringLayout.SOUTH, emailCommercial);
		
		//Boutton ajout
		mainPane.add(buttonAjout);
		layout.putConstraint(SpringLayout.EAST, buttonAjout, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.WEST, buttonAjout, 10, SpringLayout.EAST, scrollPan);
		layout.putConstraint(SpringLayout.NORTH, buttonAjout, 10, SpringLayout.SOUTH, buttonRdv);
		
		//Button edit
		mainPane.add(buttonEdit);
		layout.putConstraint(SpringLayout.EAST, buttonEdit, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.WEST, buttonEdit, 10, SpringLayout.EAST, scrollPan);
		layout.putConstraint(SpringLayout.NORTH, buttonEdit, 10, SpringLayout.SOUTH, buttonAjout);
		
		//Button suppr
		mainPane.add(buttonSuppr);
		layout.putConstraint(SpringLayout.EAST, buttonSuppr, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.WEST, buttonSuppr, 10, SpringLayout.EAST, scrollPan);
		layout.putConstraint(SpringLayout.NORTH, buttonSuppr, 10, SpringLayout.SOUTH, buttonEdit);
		
		mainPane.setLayout(layout);
		
		setContentPane(mainPane);
		setSize(800, 600);
		setResizable(false);
	}

}
