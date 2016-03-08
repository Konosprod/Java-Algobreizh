package org.algobreizh.ui;


import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.algobreizh.metier.Commercial;
import org.algobreizh.ui.actions.AjoutHandler;
import org.algobreizh.ui.actions.EditHandler;
import org.algobreizh.ui.actions.RdvHandler;
import org.algobreizh.utils.DatabaseManager;

public class FenetrePrincipale extends JFrame {
	
	static final long serialVersionUID = -2620432862549001611L;

	private JPanel mainPane;
	private SpringLayout layout;
	private JLabel nom;
	private JLabel email;
	private JLabel prenom;
	private JLabel numero;
	private JLabel labelBonjour;
	private JLabel labelNumero;
	private JLabel labelRegion;
	private JLabel region;
	private JTable tabClient;
	private JButton buttonEdit;
	private JButton buttonRdv;
	private JButton buttonAjout;
	private JButton buttonSuppr;
	private Commercial commercial;
	
	public FenetrePrincipale(int idCommercial) {
		
		mainPane = new JPanel();
		commercial = new Commercial();
		layout = new SpringLayout();
		labelBonjour = new JLabel("Bonjour,");
		labelNumero = new JLabel("Tel :");
		labelRegion = new JLabel("Region :");
		tabClient = new JTable();
		buttonEdit = new JButton("Editer");
		buttonRdv = new JButton("RDV");
		buttonAjout = new JButton("Ajout");
		buttonSuppr = new JButton("Suppr");
		
		buttonRdv.setEnabled(false);
		buttonSuppr.setEnabled(false);
		buttonEdit.setEnabled(false);
		
		ImageIcon icon = new ImageIcon("ressources/icon.png");
		this.setIconImage(icon.getImage());
		
		commercial.getInfoCommercial(idCommercial);
		
		nom = new JLabel(commercial.getNom());
		prenom = new JLabel(commercial.getPrenom());
		email = new JLabel(commercial.getEmail());
		numero = new JLabel(commercial.getNumero());
		region = new JLabel(commercial.getLibelleZone());
		
		setupLayout();
		connectButtons();
		
		fillTabClient();
		
		setContentPane(mainPane);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Algobreizh - Prise de rendez-vous");
	}
	
	/**
	 * Récupère les informations dans la base de donénes
	 * et remplis la table client avec ces dernières
	 */
	private void fillTabClient()
	{
		DatabaseManager db = DatabaseManager.getInstance();
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		String sql = "select nomClient, prenomClient, numeroClient, emailClient, dateRdv, dateDernierRdv, idClient from client where `idZoneGeo` = " + commercial.getIdZone();
		
		try {
			ResultSet res = db.execute(sql);
			
			columnNames.add("Nom");
			columnNames.add("Prenom");
			columnNames.add("Téléphone");
			columnNames.add("Email");
			columnNames.add("RDV");
			columnNames.add("Dernier RDV");
			columnNames.add("ID");
			
			while(res.next())
			{
				Vector<Object> vector = new Vector<Object>();
				
				for(int i = 1; i <= columnNames.size(); i++)
				{
					Object o = res.getObject(i);
					
					if(i == 5 || i == 6)
					{
						if(!o.equals(0))
						{
							sql = "select dateRendezVous from rendezvous where `idRendezvous` =" + o;
							ResultSet date = db.execute(sql);
							date.next();
							o = date.getObject(1);
						}
						else
						{
							o = "Aucun";
						}
					}
					
					vector.add(o);
				}
				
				data.add(vector);
			}
			
			TableModel model = new DefaultTableModel(data, columnNames) {

			    private static final long serialVersionUID = 965018577721069100L;

				//Desactive l'édition de la cellule au double click
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			setupSelectionModel();
			
			tabClient.setModel(model);
			//Double sort pour ranger par ordre décroissant
			tabClient.getRowSorter().toggleSortOrder(4);
			tabClient.getRowSorter().toggleSortOrder(4);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Connecte les boutons aux différents listeners
	 */
	private void connectButtons() 
	{
		buttonRdv.addActionListener(new RdvHandler(this));
		buttonEdit.addActionListener(new EditHandler(this));
		buttonAjout.addActionListener(new AjoutHandler(this));
	}
	
	/**
	 * Créer le model de la table client, permet d'activer
	 * ou non certains boutons en fonction de la sélection
	 * d'une ligne
	 */
	private void setupSelectionModel()
	{
		tabClient.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if(tabClient.getSelectedRow() != -1)
				{
					buttonEdit.setEnabled(true);
					//buttonSuppr.setEnabled(true);
					buttonRdv.setEnabled(true);
				}
				else
				{
					buttonEdit.setEnabled(false);
					//buttonSuppr.setEnabled(false);
					buttonRdv.setEnabled(false);
				}
			}
		});
		
		tabClient.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}
	
	/**
	 * Récupère l'id du client de la ligne sélectionnée
	 * @return id du client de la ligne sélectionnée
	 */
	public int getSelectedIndex()
	{
		return (int) tabClient.getValueAt(tabClient.getSelectedRow(), 6);
	}
	
	/**
	 * Met à jour le tableau de client
	 */
	public void refreshTab()
	{
	    fillTabClient();
	}
	
	/**
	 * Mets en place les différents composants de la fenêtre
	 * et les place sur cette dernière
	 */
	private void setupLayout()
	{
		//Nom Prenom commercial
		mainPane.add(labelBonjour);
		mainPane.add(nom);
		mainPane.add(prenom);
		layout.putConstraint(SpringLayout.NORTH, labelBonjour, 10, SpringLayout.NORTH, mainPane);
		layout.putConstraint(SpringLayout.WEST, labelBonjour, 10, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.WEST, nom, 10, SpringLayout.EAST, labelBonjour);
		layout.putConstraint(SpringLayout.NORTH, prenom, 10, SpringLayout.NORTH, mainPane);
		layout.putConstraint(SpringLayout.NORTH, nom, 10, SpringLayout.NORTH, mainPane);
		layout.putConstraint(SpringLayout.WEST, prenom, 5, SpringLayout.EAST, nom);
		
		//Email
		mainPane.add(email);
		layout.putConstraint(SpringLayout.WEST, email, 10, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.NORTH, email, 10, SpringLayout.SOUTH, prenom);
		
		//Region
		mainPane.add(labelRegion);
		mainPane.add(region);
		layout.putConstraint(SpringLayout.NORTH, region, 10, SpringLayout.NORTH, mainPane);
		layout.putConstraint(SpringLayout.EAST, region, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.EAST, labelRegion, -5, SpringLayout.WEST, region);
		layout.putConstraint(SpringLayout.NORTH, labelRegion, 10, SpringLayout.NORTH, mainPane);
		
		//Numero
		mainPane.add(labelNumero);
		mainPane.add(numero);
		layout.putConstraint(SpringLayout.NORTH, numero, 10, SpringLayout.SOUTH, region);
		layout.putConstraint(SpringLayout.EAST, numero, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.EAST, labelNumero, -5, SpringLayout.WEST, numero);
		layout.putConstraint(SpringLayout.NORTH, labelNumero, 10, SpringLayout.SOUTH, region);
		
		//Tableau client
		tabClient.setFillsViewportHeight(true);
		tabClient.setAutoCreateRowSorter(true);
		JScrollPane scrollPan = new JScrollPane(tabClient);
		mainPane.add(scrollPan);
		
		layout.putConstraint(SpringLayout.WEST, scrollPan, 10, SpringLayout.WEST, mainPane);
		layout.putConstraint(SpringLayout.NORTH, scrollPan, 20, SpringLayout.SOUTH, email);
		layout.putConstraint(SpringLayout.SOUTH, scrollPan, -10, SpringLayout.SOUTH, mainPane);
		layout.putConstraint(SpringLayout.EAST, scrollPan, -100, SpringLayout.EAST, mainPane);
		
		//Boutton Rendez-vous
		mainPane.add(buttonRdv);
		layout.putConstraint(SpringLayout.EAST, buttonRdv, -10, SpringLayout.EAST, mainPane);
		layout.putConstraint(SpringLayout.WEST, buttonRdv, 10, SpringLayout.EAST, scrollPan);
		layout.putConstraint(SpringLayout.NORTH, buttonRdv, 20, SpringLayout.SOUTH, email);
		
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
	}
}
