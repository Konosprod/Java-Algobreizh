package org.algobreizh.ui;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.algobreizh.ui.actions.AjoutHandler;
import org.algobreizh.ui.actions.EditHandler;
import org.algobreizh.ui.actions.RdvHandler;
import org.algobreizh.utils.DatabaseManager;

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
		nomCommercial = new JLabel("Nom : Rambo");
		emailCommercial = new JLabel("Email : john.rambo@algobreizh.fr");
		prenomCommercial = new JLabel("Prénom : John");
		numeroCommercial = new JLabel("Téléphone : 0649784545");
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
		
		connectButtons();
		
		fillTabClient();
		
		setContentPane(mainPane);
		setSize(800, 600);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Algobreizh - Prise de rendez-vous");
	}
	
	private void fillTabClient()
	{
		DatabaseManager db = DatabaseManager.getInstance();
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		String sql = "select nomClient, prenomClient, numeroClient, emailClient, dateRdv, dateDernierRdv, idClient from client where `idZoneGeo` = 9";
		
		try {
			ResultSet res = db.execute(sql);
			
			ResultSetMetaData metaData = res.getMetaData();
			int columnCount = metaData.getColumnCount();
			
			for(int i = 1; i <= columnCount; i++)
			{
				columnNames.add(metaData.getColumnName(i));
			}
			
			while(res.next())
			{
				Vector<Object> vector = new Vector<Object>();
				
				for(int i = 1; i <= columnCount; i++)
				{
					vector.add(res.getObject(i));
				}
				
				data.add(vector);
			}
			
			TableModel model = new DefaultTableModel(data, columnNames) {
				
				//Desactive l'édition de la cellule au double click
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			
			setupSelectionModel();
			
			tabClient.setModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void connectButtons() 
	{
		buttonRdv.addActionListener(new RdvHandler(this));
		buttonEdit.addActionListener(new EditHandler(this));
		buttonAjout.addActionListener(new AjoutHandler(this));
	}
	
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
	}
	
	public int getSelectedIndex()
	{
		return (int) tabClient.getValueAt(tabClient.getSelectedRow(), 6);
	}
}
