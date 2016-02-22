package org.algobreizh.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	public FenetrePrincipale() {
		
		mainPane = new JPanel();
		layout = new SpringLayout();
		nomCommercial = new JLabel("Rambo");
		emailCommercial = new JLabel("john.rambo@algobreizh.fr");
		prenomCommercial = new JLabel("John");
		numeroCommercial = new JLabel("0649784545");
		tabClient = new JTable();
		
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
		
		
		mainPane.setLayout(layout);
		
		setContentPane(mainPane);
		setSize(800, 600);
		setResizable(false);
	}

}
