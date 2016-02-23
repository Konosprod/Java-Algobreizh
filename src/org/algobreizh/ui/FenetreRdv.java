package org.algobreizh.ui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class FenetreRdv extends JFrame {
    
	private static final long serialVersionUID = 6536480305206836568L;
	
	private static JLabel labellieurdv;
    private static JLabel labeldaterdv;
    private static JLabel labelcontactrdv;
    private static JTextField lieurdv;
    private static JTextField daterdv;
    private static JTextField contactrdv;
    private static JButton bouttonValide;
    public FenetreRdv(){
        //renomme la fenetre
    
        this.setTitle("Fiche de prise de Rendez-vous");
        //Redimensionne la fenetre
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //affecte des valeurs au Panel ainsi qu'au JTextField et le boutton
        labellieurdv= new JLabel("Lieu du rendez vous");
        labeldaterdv = new JLabel("Date du rendez vous");
        labelcontactrdv = new JLabel("Personne a contacte");
        bouttonValide = new JButton("Valider");
        contactrdv= new JTextField (20);
        contactrdv.setText("Contact");//Modifie le text
        daterdv = new JTextField(20);
        daterdv.setText("Date");
        lieurdv = new JTextField(20);
        lieurdv.setText("Lieu");
        
        //Instanciation d'un objet JPanel
        JPanel panel = new JPanel();
        SpringLayout layout = new SpringLayout();
        panel.setLayout(layout);//permet de d�couper le panel en plusieur partie
        
        
        //Ajoute Les �l�ments lablleieurdv et lieurdv dans le Frame
        panel.add(bouttonValide);
        panel.add(labellieurdv);
        panel.add(lieurdv);
        panel.add(labeldaterdv);
        panel.add(daterdv);
        panel.add(labelcontactrdv);
        panel.add(contactrdv);
        
        layout.putConstraint(SpringLayout.WEST, labellieurdv, 11, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, labellieurdv, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, lieurdv, 10, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, lieurdv, 11, SpringLayout.EAST, labellieurdv);
        
        layout.putConstraint(SpringLayout.WEST, labeldaterdv, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, labeldaterdv, 30, SpringLayout.NORTH, labellieurdv);
        layout.putConstraint(SpringLayout.NORTH, daterdv, 30, SpringLayout.NORTH, lieurdv);
        layout.putConstraint(SpringLayout.WEST, daterdv, 10, SpringLayout.EAST, labeldaterdv);
        
        layout.putConstraint(SpringLayout.WEST, labelcontactrdv, 10, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, labelcontactrdv, 30, SpringLayout.NORTH, labeldaterdv);
        layout.putConstraint(SpringLayout.NORTH, contactrdv, 10, SpringLayout.SOUTH, daterdv);
        layout.putConstraint(SpringLayout.WEST, contactrdv, 8, SpringLayout.EAST, labelcontactrdv);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bouttonValide, 0, SpringLayout.HORIZONTAL_CENTER, panel);
        layout.putConstraint(SpringLayout.NORTH, bouttonValide, 10, SpringLayout.SOUTH, labelcontactrdv);
        
        
        
        
        
        this.setContentPane(panel);
        this.setEnabled(true);
        this.setVisible(true);
    }
}