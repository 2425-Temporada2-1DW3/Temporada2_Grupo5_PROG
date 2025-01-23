package com.structure;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.logic.Equipo;
import com.logic.Jornada;
import com.logic.Partido;
import com.logic.Temporada;

import java.awt.FlowLayout;
import java.awt.ScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class PanelInicio extends JPanel implements ActionListener {
	// creamos los maps para hacer que el contenido de la pagina sea dinamico
	private Map<String, JLabel> labelsMap = new HashMap<>();
	private Map<String, JTextField> textFieldMap = new HashMap<>();
	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
	/**
	 * Create the panel.
	 */
	public PanelInicio(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.userType = userType;
		this.colorbg = colorbg;
		this.colortxt = colortxt;

		// Cambia color del Jpanel
		setBackground(colorbg);
        
        // Codigo Temporal 
		JLabel lblNewLabel = new JLabel("PanelInicio");
		lblNewLabel.setForeground(colortxt);
		add(lblNewLabel);

	}

}
