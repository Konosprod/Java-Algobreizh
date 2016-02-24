package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import org.algobreizh.ui.FenetreFicheClient;
import org.algobreizh.ui.FenetrePrincipale;

public class EditHandler implements ActionListener {

	private FenetrePrincipale parent = null;
	private FenetreFicheClient fenetreEdit = null;
	
	public EditHandler(FenetrePrincipale parent) {
		this.parent = parent;
		
		fenetreEdit = new FenetreFicheClient();
		fenetreEdit.setLocationRelativeTo(parent);
		fenetreEdit.setModal(true);
	
		fenetreEdit.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				insertBdd();
				refreshTabClient();
				super.windowDeactivated(e);
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fenetreEdit.setVisible(true);
	}
	
	private void insertBdd()
	{
		
	}
	
	private void refreshTabClient()
	{
		
	}
}
