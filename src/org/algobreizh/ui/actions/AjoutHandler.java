package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import org.algobreizh.ui.FenetreFicheClient;
import org.algobreizh.ui.FenetrePrincipale;

public class AjoutHandler implements ActionListener {

	private FenetrePrincipale parent = null;
	private FenetreFicheClient fenetreAjout = null;
	
	public AjoutHandler(FenetrePrincipale parent) {
		this.parent = parent;
		fenetreAjout = new FenetreFicheClient();
		fenetreAjout.setModal(true);
		fenetreAjout.setLocationRelativeTo(parent);
		
		fenetreAjout.addWindowListener(new WindowAdapter() {
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		fenetreAjout.setVisible(true);
	}

}
