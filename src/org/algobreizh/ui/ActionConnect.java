package org.algobreizh.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ActionConnect implements ActionListener {

	private JFrame parent;
	private JFrame nextFrame = null;
	
	public ActionConnect(JFrame parent, JPasswordField passEntry, JTextField idEntry)
	{
		this.parent = parent;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		parent.setVisible(false);
		nextFrame = new FenetrePrincipale();
	}

}
