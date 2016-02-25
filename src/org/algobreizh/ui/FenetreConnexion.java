package org.algobreizh.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.algobreizh.ui.actions.ActionConnect;

public class FenetreConnexion extends JFrame {
	
	private static final long serialVersionUID = -1205943752438446635L;

	private JPasswordField passEntry;
	private JTextField idEntry;
	private JPanel pan;
	private JButton button;
	
	public FenetreConnexion() {
		super("Algobreizh - Connexion");
		
		ImageIcon icon = new ImageIcon("ressources/icon.png");
		this.setIconImage(icon.getImage());
		
		pan = new JPanel();
		button = new JButton("Connexion");
		idEntry = new JTextField(15);
		passEntry = new JPasswordField(15);
		SpringLayout layout = new SpringLayout();
		pan.setLayout(layout);
		
		JLabel labelId = new JLabel("Identifiant : ");
		JLabel labelPass = new JLabel("Mot de passe :");
		
		pan.add(labelId);
		pan.add(idEntry);
		pan.add(labelPass);
		pan.add(passEntry);
		pan.add(button);
		
		
		layout.putConstraint(SpringLayout.WEST, labelId, 10, SpringLayout.WEST, pan);
		layout.putConstraint(SpringLayout.NORTH, labelId, 10, SpringLayout.NORTH, pan);
		layout.putConstraint(SpringLayout.NORTH, idEntry, 10, SpringLayout.NORTH, pan);
		layout.putConstraint(SpringLayout.WEST, idEntry, 20, SpringLayout.EAST, labelId);
		
		layout.putConstraint(SpringLayout.WEST, labelPass, 10, SpringLayout.WEST, pan);
		layout.putConstraint(SpringLayout.NORTH, labelPass, 30, SpringLayout.NORTH, labelId);
		layout.putConstraint(SpringLayout.NORTH, passEntry, 30, SpringLayout.NORTH, labelId);
		layout.putConstraint(SpringLayout.WEST, passEntry, 20, SpringLayout.EAST, labelId);
	
        layout.putConstraint(SpringLayout.NORTH, button, 75, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, button, 100, SpringLayout.WEST, pan);
		
		this.setContentPane(pan);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		button.addActionListener(new ActionConnect(this,passEntry, idEntry));
	}
	
}
