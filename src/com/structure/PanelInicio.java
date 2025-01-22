package com.structure;

import javax.swing.JPanel;

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
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;

public class PanelInicio extends JPanel {
	private Map<String, JTextField> textFieldsMap = new HashMap<>();
	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
<<<<<<< Updated upstream
	/**
	 * Create the panel.
	 */
	public PanelInicio(Color colorbg, Color colortxt, int userType) {
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
=======
	private String userName;
	private JPanel panel;
	JPanel panel_1;
	JPanel panel_2;
	private JLabel lblNewLabel;
	private JPanel panel_3;
	private JLabel lblNewLabel_1;
	private JPanel panel_4;
	private JPanel panel_5;
	private JPanel panel_6;
	private JComboBox<Temporada> comboBox;
	private JPanel panel_7;
	private JLabel partidoELoc1;
	private JTextField partido1LocPoints;
	private JPanel panel_8;
	private JPanel panel_9;
	private JLabel partidoELoc2;
	private JTextField partido2LocPoints;
	private JPanel panel_10;
	private JPanel panel_11;
	private JLabel partidoELoc3;
	private JTextField partido3LocPoints;
	private JPanel panel_12;
	private ScrollPane scrollPane;
	private JTable table;
	private JPanel panel_13;
	private JPanel panel_14;
	private JButton btnNewButton;
	private JLabel numJornada;
	private JButton btnNewButton_1;
	private ArrayList<Temporada> listTemporadas;
	private ArrayList<Jornada> listJornadas;
	private JLabel partidoEVis1;
	private JTextField Partido1VisPoints;
	private JLabel partidoEVis2;
	private JTextField partido2VisPoints;
	private JLabel partidoEVis3;
	private JTextField Partido3;
	private ArrayList<Equipo>nombre;
	/**
	 * Create the panel.
	 */
	public PanelInicio(Color colorbg, Color colortxt, int userType, String userName) {
		
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.userType = userType;
	    this.colorbg = colorbg;
	    this.colortxt = colortxt;
	    this.userName = userName;
	    this.listTemporadas = new ArrayList<>();
	    this.listJornadas = new ArrayList<>();
	    cargarTemporadasDesdeArchivo();

	    // Cambia color del JPanel
	    setBackground(colorbg);
	    setLayout(new BorderLayout(0, 0));

	    panel = new JPanel();
	    add(panel, BorderLayout.NORTH);

	    lblNewLabel = new JLabel("CLASIFICACION");
	    panel.add(lblNewLabel);

	    panel_1 = new JPanel();
	    add(panel_1, BorderLayout.WEST);
	    panel_1.setLayout(new BorderLayout(0, 0));

	    panel_3 = new JPanel();
	    panel_1.add(panel_3, BorderLayout.NORTH);

	    lblNewLabel_1 = new JLabel("Jornadas");
	    panel_3.add(lblNewLabel_1);

	    panel_4 = new JPanel();
	    panel_1.add(panel_4, BorderLayout.CENTER);
	    panel_4.setLayout(new BorderLayout(0, 0));

	    panel_5 = new JPanel();
	    panel_4.add(panel_5, BorderLayout.NORTH);

	    comboBox = new JComboBox<>();
	    panel_5.add(comboBox);
	    actualizarComboBox();

	    panel_6 = new JPanel();
	    panel_4.add(panel_6, BorderLayout.CENTER);
	    panel_6.setLayout(new BorderLayout(0, 0));

	    panel_7 = new JPanel();
	    panel_6.add(panel_7, BorderLayout.NORTH);

	    // Inicializar campos para Partido 1
	    partidoELoc1 = new JLabel("Partido1Loc");
	    panel_7.add(partidoELoc1);
	    textFieldsMap.put("partidoELoc1", new JTextField(3));
	    panel_7.add(textFieldsMap.get("partidoELoc1"));

	    partidoEVis1 = new JLabel("Partido1Vis");
	    panel_7.add(partidoEVis1);
	    textFieldsMap.put("partidoEVis1", new JTextField(3));
	    panel_7.add(textFieldsMap.get("partidoEVis1"));

	    panel_8 = new JPanel();
	    panel_6.add(panel_8, BorderLayout.CENTER);
	    panel_8.setLayout(new BorderLayout(0, 0));

	    panel_9 = new JPanel();
	    panel_8.add(panel_9, BorderLayout.NORTH);

	    // Inicializar campos para Partido 2
	    partidoELoc2 = new JLabel("Partido2Loc");
	    panel_9.add(partidoELoc2);
	    textFieldsMap.put("partidoELoc2", new JTextField(3));
	    panel_9.add(textFieldsMap.get("partidoELoc2"));

	    partidoEVis2 = new JLabel("Partido2Vis");
	    panel_9.add(partidoEVis2);
	    textFieldsMap.put("partidoEVis2", new JTextField(3));
	    panel_9.add(textFieldsMap.get("partidoEVis2"));

	    panel_10 = new JPanel();
	    panel_8.add(panel_10, BorderLayout.CENTER);
	    panel_10.setLayout(new BorderLayout(0, 0));

	    panel_11 = new JPanel();
	    panel_10.add(panel_11, BorderLayout.NORTH);
	    panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

	    // Inicializar campos para Partido 3
	    partidoELoc3 = new JLabel("Partido3Loc");
	    panel_11.add(partidoELoc3);
	    textFieldsMap.put("partidoELoc3", new JTextField(3));
	    panel_11.add(textFieldsMap.get("partidoELoc3"));

	    partidoEVis3 = new JLabel("Partido3Vis");
	    panel_11.add(partidoEVis3);
	    textFieldsMap.put("partidoEVis3", new JTextField(3));
	    panel_11.add(textFieldsMap.get("partidoEVis3"));

	    panel_13 = new JPanel();
	    panel_10.add(panel_13, BorderLayout.CENTER);
	    panel_13.setLayout(new BorderLayout(0, 0));

	    panel_14 = new JPanel();
	    panel_13.add(panel_14, BorderLayout.NORTH);

	    btnNewButton = new JButton("<");
	    btnNewButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            // Acción del botón
	        }
	    });
	    panel_14.add(btnNewButton);

	    numJornada = new JLabel("1");
	    panel_14.add(numJornada);

	    btnNewButton_1 = new JButton(">");
	    panel_14.add(btnNewButton_1);

	    panel_12 = new JPanel();
	    panel_1.add(panel_12, BorderLayout.SOUTH);

	    panel_2 = new JPanel();
	    add(panel_2, BorderLayout.EAST);

	    scrollPane = new ScrollPane();
	    panel_2.add(scrollPane);

	    table = new JTable();
	    panel_2.add(table);
		
		 
		 
        
        // Codigo Temporal 
		
>>>>>>> Stashed changes

	}
	private void cargarTemporadasDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Temporada.ser"))) {
            while (true) {
                try {
                    Temporada temp = (Temporada) ois.readObject();
                    listTemporadas.add(temp);
                } catch (EOFException ex) {
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de temporadas. Se creará uno nuevo al guardar cambios.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar temporadas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
	private void actualizarComboBox() {
		comboBox.removeAllItems();
        for (Temporada temporada : listTemporadas) {
        	comboBox.addItem(temporada);
        }
        //temporada por defecto 
        if (!listTemporadas.isEmpty()) {
        	comboBox.setSelectedIndex(0);
        	actualizarTemporada();
        }

        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	actualizarTemporada();
            }
        });
    }
	
	private void actualizarTemporada() {
		Temporada TemporadaSeleccionada =(Temporada) comboBox.getSelectedItem();
		if(TemporadaSeleccionada !=null) {
			listJornadas = TemporadaSeleccionada.getListJornadas();
			nombre= TemporadaSeleccionada.getListEquipos();
		} else {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);

		}
	}
	private void SwichDatosJornada() {
		int jornadaSelect = Integer.parseInt(numJornada.getText()); 
		Jornada jornadaActual = listJornadas.get(jornadaSelect);
		ArrayList <Partido> partidos = jornadaActual.getListPartidos();
		String locales;
		String visitantes;
		for (int counter = 1; counter < partidos.size(); counter++) {
			Partido partido= partidos.get(counter);
			locales = "partidoELoc"+counter;
			visitantes="partidoEVis"+counter;
			 if (textFieldsMap.containsKey(locales)) {
		            int idLocal = partido.getEquipoLoc();
		            textFieldsMap.get(locales).setText(nombre.get(idLocal).getNombre());
		        }
			 if (textFieldsMap.containsKey(visitantes)) {
		            int idVis = partido.getEquipoVis();
		            textFieldsMap.get(visitantes).setText(nombre.get(idVis).getNombre());
		        }
			
			
		}
		
		
		
	}
	
	private void iniciarTabla() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		
		
	}
}
