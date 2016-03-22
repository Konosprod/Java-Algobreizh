package org.algobreizh.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

public class ValidateListener implements ActionListener {

	private JDialog parent;
	
	public ValidateListener(JDialog parent) {
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		parent.dispose();
	}

}
