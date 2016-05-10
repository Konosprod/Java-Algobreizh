package org.algobreizh.ui;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpringLayout;

import org.algobreizh.ui.actions.ValidateListener;
import org.algobreizh.utils.DatabaseManager;
import org.algobreizh.utils.DateLabelFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class FenetreRdv extends JDialog {

    private static final long serialVersionUID = 6536480305206836568L;

    private static JLabel labellieurdv;
    private static JLabel labeldaterdv;
    private static JLabel labelcontactrdv;
    private static JLabel labelHeure;
    private static JTextField lieurdv;
    private static JTextField contactrdv;
    private static JButton bouttonValide;
    private static JDatePickerImpl daterdv;
    private static JSpinner heureRdv;
    
    public FenetreRdv()
    {
    	setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	// renomme la fenetre

	this.setTitle("Algobreizh - Ajout rendez-vous");
	// Redimensionne la fenetre
	this.setSize(500, 200);

	ImageIcon icon = new ImageIcon("ressources/icon.png");
	this.setIconImage(icon.getImage());

	// affecte des valeurs au Panel ainsi qu'au JTextField et le boutton
	labellieurdv = new JLabel("Lieu du rendez vous : ");
	labeldaterdv = new JLabel("Date du rendez vous : ");
	labelcontactrdv = new JLabel("Personne � contacter : ");
	bouttonValide = new JButton("Valider");
	labelHeure = new JLabel("Heure :");
	contactrdv = new JTextField(20);
	lieurdv = new JTextField(20);

	// Instanciation d'un objet JPanel
	JPanel panel = new JPanel();
	SpringLayout layout = new SpringLayout();
	panel.setLayout(layout);

	UtilDateModel model = new UtilDateModel();
	JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
	daterdv = new JDatePickerImpl(datePanel, new DateLabelFormatter());

	heureRdv = new JSpinner(new SpinnerDateModel());
	JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(heureRdv, "HH:mm");

	heureRdv.setEditor(timeEditor);
	heureRdv.setValue(new Date());

	// Ajoute Les elements lablleieurdv et lieurdv dans le Frame
	
	// Lieu du rendez-vous
	panel.add(labellieurdv);
	panel.add(lieurdv);
	layout.putConstraint(SpringLayout.WEST, labellieurdv, 11,
		SpringLayout.WEST, panel);
	layout.putConstraint(SpringLayout.NORTH, labellieurdv, 10,
		SpringLayout.NORTH, panel);
	layout.putConstraint(SpringLayout.NORTH, lieurdv, 10,
		SpringLayout.NORTH, panel);
	layout.putConstraint(SpringLayout.WEST, lieurdv, 11, SpringLayout.EAST,
		labellieurdv);

	// Date du rendez-vous
	panel.add(labeldaterdv);
	panel.add(daterdv);
	layout.putConstraint(SpringLayout.WEST, labeldaterdv, 10,
		SpringLayout.WEST, panel);
	layout.putConstraint(SpringLayout.NORTH, labeldaterdv, 10,
		SpringLayout.SOUTH, labellieurdv);
	layout.putConstraint(SpringLayout.NORTH, daterdv, 10,
		SpringLayout.SOUTH, lieurdv);
	layout.putConstraint(SpringLayout.WEST, daterdv, 10, SpringLayout.EAST,
		labeldaterdv);
	
	//Heure du rendez-vous
	panel.add(labelHeure);
	panel.add(heureRdv);
	layout.putConstraint(SpringLayout.WEST, labelHeure, 10, SpringLayout.WEST, panel);
	layout.putConstraint(SpringLayout.NORTH, labelHeure, 10, SpringLayout.SOUTH, daterdv);
	layout.putConstraint(SpringLayout.WEST, heureRdv, 10, SpringLayout.EAST, labelHeure);
	layout.putConstraint(SpringLayout.NORTH, heureRdv, 10, SpringLayout.SOUTH, daterdv);
	
	// Champ personne à contacter
	panel.add(labelcontactrdv);
	panel.add(contactrdv);
	layout.putConstraint(SpringLayout.WEST, labelcontactrdv, 10,
		SpringLayout.WEST, panel);
	layout.putConstraint(SpringLayout.NORTH, labelcontactrdv, 10,
		SpringLayout.SOUTH, heureRdv);
	layout.putConstraint(SpringLayout.NORTH, contactrdv, 10,
		SpringLayout.SOUTH, heureRdv);
	layout.putConstraint(SpringLayout.WEST, contactrdv, 1,
		SpringLayout.EAST, labelcontactrdv);

	// Bouton valider
	panel.add(bouttonValide);
	layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, bouttonValide, 0,
		SpringLayout.HORIZONTAL_CENTER, panel);
	layout.putConstraint(SpringLayout.NORTH, bouttonValide, 10,
		SpringLayout.SOUTH, labelcontactrdv);
	
	bouttonValide.addActionListener(new ValidateListener(this));
	

	this.setContentPane(panel);
    }
    
    public Date getDate()
    {
    	Date d = (Date) daterdv.getModel().getValue();
    	Date heure = (Date) heureRdv.getModel().getValue();
	
    	d.setHours(heure.getHours());
    	d.setMinutes(heure.getMinutes());
	
    	return d;
    }
    
    public String getContact()
    {
    	return contactrdv.getText();
    }
    
    public String getLieu()
    {
    	return lieurdv.getText();
    }
    
    public void chargerRdv(long index)
    {
    	DatabaseManager db = DatabaseManager.getInstance();
    	
    	String sql = "select * from client, rendezvous where client.idClient = ? and client.dateRdv = rendezvous.idRendezVous";
    	
    	try {
			PreparedStatement stmt = db.prepareStatement(sql);
			
			stmt.setLong(1, index);
			
			ResultSet res = stmt.executeQuery();
			res.next();
			
			contactrdv.setText(res.getString("contactRendezvous"));
			lieurdv.setText(res.getString("lieuRendezvous"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}