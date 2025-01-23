package com.structure;

import javax.swing.JPanel;

import java.awt.Color;
import javax.swing.JLabel;

public class PanelInicio extends JPanel {

	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	private main parentFrame;

	/**
	 * Create the panel.
	 */
	public PanelInicio(Color colorbg, Color colortxt, int userType, String userName, main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.userType = userType;
		this.colorbg = colorbg;
		this.colortxt = colortxt;
		this.userName = userName;
	    this.parentFrame = parentFrame;

		// Cambia color del Jpanel
		setBackground(colorbg);
        
        // Codigo Temporal 
		JLabel lblNewLabel = new JLabel("PanelInicio");
		lblNewLabel.setForeground(colortxt);
		add(lblNewLabel);
		
        
	}

}
