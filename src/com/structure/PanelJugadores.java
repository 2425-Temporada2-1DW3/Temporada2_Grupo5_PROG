package com.structure;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelJugadores extends JPanel {

	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	private main parentFrame;

	/**
	 * Create the panel.
	 */
	public PanelJugadores(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.parentFrame = parentFrame;
	    userType = parentFrame.userType;
	    colorbg = parentFrame.colorbg;
	    colortxt = parentFrame.colortxt;
	    userName = parentFrame.userName;

		// Cambia color del Jpanel
		setBackground(colorbg);
        
        // Codigo Temporal 
		JLabel lblNewLabel = new JLabel("PanelJugadores");
		lblNewLabel.setForeground(colortxt);
		add(lblNewLabel);

	}

}