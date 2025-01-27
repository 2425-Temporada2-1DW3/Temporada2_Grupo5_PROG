package com.structure;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.logic.CreadorXML;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class PanelInicio extends JPanel implements ActionListener {
	// creamos los maps para hacer que el contenido de la pagina sea dinamico
	private Map<String, JLabel> labelsMap = new HashMap<>();
	private Map<String, JTextField> textFieldMap = new HashMap<>();
	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
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
	private JPanel panel_7;
	private JLabel partidoELoc0;
	//private JTextField partido1LocPoints;
	private JPanel panel_8;
	private JPanel panel_9;
	private JLabel partidoELoc1;
	//private JTextField partido2LocPoints;
	private JPanel panel_10;
	private JPanel panel_11;
	private JLabel partidoELoc2;
	//private JTextField partido3LocPoints;
	private JPanel panel_12;
	private JTable table;
	private JPanel panel_13;
	private JPanel panel_14;
	private JButton prevButton;
	private JLabel numJornada;
	private JButton nextButton;
	private JLabel partidoEVis0,partidoEVis1,partidoEVis2;

	
	


	private JTextField pointsEVis1,pointsELoc1, pointsELoc0,pointsEVis0,pointsELoc2,pointsEVis2 ;
	
	
	
	
	
	private int jornadaSelect;
	private JButton btnSave;
	private JButton btnUpdateApp;
	private DefaultTableModel modelClasificacion;
	private ArrayList<Partido> partidos;
	private ArrayList<Temporada> listTemporadas;
	private ArrayList<Jornada> listJornadas;
	private ArrayList<Equipo> nombre;
	private JComboBox<Temporada> comboBox;
	private ArrayList<Equipo> Clasificacion;
	private Temporada TemporadaSeleccionada;
	private main parentFrame;
	private JButton btnNewButton;
	private JPanel panel_15;

	/**
	 * Create the panel.
	 */

	public PanelInicio(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro
		// sitio
		this.parentFrame = parentFrame;
		userType = parentFrame.userType;
		colorbg = parentFrame.colorbg;
		colortxt = parentFrame.colortxt;
		userName = parentFrame.userName;

		this.listTemporadas = new ArrayList<>();
		this.listJornadas = new ArrayList<>();
		cargarTemporadasDesdeArchivo();

		// Cambia color del JPanel
		setBackground(colorbg);
		setLayout(new BorderLayout(0, 0));

		panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		lblNewLabel = new JLabel("CLASIFICACION");
		lblNewLabel.setFont(parentFrame.fuenteHeader);
		lblNewLabel.setForeground(colortxt);

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
		partidoELoc0 = new JLabel();

		labelsMap.put("partidoELoc0", partidoELoc0);
		panel_7.add(partidoELoc0);

		pointsELoc0 = new JTextField();

		textFieldMap.put("pointsELoc0", pointsELoc0);
		panel_7.add(pointsELoc0);
		pointsELoc0.setColumns(10);

		partidoEVis0 = new JLabel();
		labelsMap.put("partidoEVis0", partidoEVis0);
		panel_7.add(partidoEVis0);

		pointsEVis0 = new JTextField();

		textFieldMap.put("pointsEVis0", pointsEVis0);
		panel_7.add(pointsEVis0);
		pointsEVis0.setColumns(10);

		panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));

		panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);

		// Inicializar campos para Partido 2
		partidoELoc1 = new JLabel();

		labelsMap.put("partidoELoc1", partidoELoc1);
		panel_9.add(partidoELoc1);

		pointsELoc1 = new JTextField();

		textFieldMap.put("pointsELoc1", pointsELoc1);
		panel_9.add(pointsELoc1);
		pointsELoc1.setColumns(10);

		partidoEVis1 = new JLabel();

		labelsMap.put("partidoEVis1", partidoEVis1);
		panel_9.add(partidoEVis1);

		pointsEVis1 = new JTextField();

		textFieldMap.put("pointsEVis1", pointsEVis1);
		panel_9.add(pointsEVis1);
		pointsEVis1.setColumns(10);

		panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));

		panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.NORTH);
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Inicializar campos para Partido 3
		partidoELoc2 = new JLabel();

		labelsMap.put("partidoELoc2", partidoELoc2);
		panel_11.add(partidoELoc2);

		pointsELoc2 = new JTextField();

		textFieldMap.put("pointsELoc2", pointsELoc2);
		panel_11.add(pointsELoc2);
		pointsELoc2.setColumns(10);

		partidoEVis2 = new JLabel();

		labelsMap.put("partidoEVis2", partidoEVis2);
		panel_11.add(partidoEVis2);

		pointsEVis2 = new JTextField();

		textFieldMap.put("pointsEVis2", pointsEVis2);
		panel_11.add(pointsEVis2);
		pointsEVis2.setColumns(10);

		panel_13 = new JPanel();
		panel_10.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));

		panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);

		prevButton = new JButton("<");

		prevButton.addActionListener(this);
		panel_14.add(prevButton);

		numJornada = new JLabel("1");

		panel_14.add(numJornada);

		nextButton = new JButton(">");

		panel_14.add(nextButton);
		
		panel_15 = new JPanel();
		panel_13.add(panel_15, BorderLayout.SOUTH);
		
		btnNewButton = new JButton("Exportar");
		panel_15.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		nextButton.addActionListener(this);
		panel_12 = new JPanel();
		panel_1.add(panel_12, BorderLayout.SOUTH);
 
		btnSave = new JButton("Finalizar Jornada");

		btnSave.addActionListener(this);
		panel_12.add(btnSave);

		btnUpdateApp = new JButton("Aplicar cambios en el sistema");

		btnUpdateApp.addActionListener(this);
		panel_12.add(btnUpdateApp);

		panel_2 = new JPanel();
		add(panel_2, BorderLayout.EAST);

		table = new JTable();

		table.setEnabled(false);
		modelClasificacion = new DefaultTableModel();
		modelClasificacion.addColumn("Posición");
		modelClasificacion.addColumn("Equipo");
		modelClasificacion.addColumn("Pts Total");
		modelClasificacion.addColumn("PJ");
		modelClasificacion.addColumn("PG");
		modelClasificacion.addColumn("PP");

		table.setModel(modelClasificacion);

		// Agregar JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		panel_2.add(scrollPane);

		JComponent labelFormat[] = { partidoELoc0, pointsELoc0, partidoEVis0, pointsEVis0, partidoELoc1, pointsELoc1,
				partidoEVis1, pointsEVis1, partidoELoc2, pointsELoc2, partidoEVis2, pointsEVis2, lblNewLabel_1,
				comboBox, prevButton, numJornada, nextButton, btnSave, btnUpdateApp };
		JComponent panelFormat[] = { panel, panel_1, panel_2, panel_3, panel_4, panel_5, panel_6, panel_7, panel_8,
				panel_9, panel_10, panel_11, panel_12, panel_13, panel_14 };

		for (int i = 0; i < labelFormat.length; i++) {
			labelFormat[i].setBackground(colorbg);
			labelFormat[i].setForeground(colortxt);
			labelFormat[i].setFont(parentFrame.fuenteDefecto);
		}

		// Loop through panelFormat and apply properties
		for (int i = 0; i < panelFormat.length; i++) {
			panelFormat[i].setBackground(colorbg);
		}
		parentFrame.formatearTabla(table);
		scrollPane.getViewport().setBackground(colorbg);

		SwichDatosJornada();
		cargarTabla();
		TemporadasIniciadas();
		actualizarTabla();
		exportacion();

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
			parentFrame.mensaje("No se encontró el archivo de temporadas. Se creará uno nuevo al guardar cambios.", 2);

		} catch (IOException | ClassNotFoundException ex) {
			parentFrame.mensaje("Error al cargar temporadas: " + ex.getMessage(), 0);

		}
	}

	private void actualizarComboBox() {
		comboBox.removeAllItems();
		for (Temporada temporada : listTemporadas) {
			comboBox.addItem(temporada);
		}
		// temporada por defecto
		if (!listTemporadas.isEmpty()) {
			comboBox.setSelectedIndex(0);
			actualizarTemporada();
		}

		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarTemporada();
				SwichDatosJornada();
				TemporadasIniciadas();
				cargarTabla();
				actualizarTabla();
			}
		});
	}

	private void actualizarTemporada() {
		TemporadaSeleccionada = (Temporada) comboBox.getSelectedItem();
		if (TemporadaSeleccionada != null) {
			listJornadas = TemporadaSeleccionada.getListJornadas();
			nombre = TemporadaSeleccionada.getListEquipos();
		} else {
			parentFrame.mensaje("error al cargar la temporada", 0);

		}

	}

	private void SwichDatosJornada() {
		jornadaSelect = Integer.parseInt(numJornada.getText());
		Jornada jornadaActual = listJornadas.get(jornadaSelect - 1);
		partidos = jornadaActual.getListPartidos();
		String locales;
		String visitantes;
		String pointsLoc;
		String pointsVis;
		for (int counter = 0; counter < partidos.size(); counter++) {
			Partido partido = partidos.get(counter);
			locales = "partidoELoc" + counter;
			visitantes = "partidoEVis" + counter;
			pointsLoc = "pointsELoc" + counter;
			pointsVis = "pointsEVis" + counter;

			// Actualizar los nombres de los equipos
			if (labelsMap.containsKey(locales)) {
				int idLocal = partido.getEquipoLoc();
				labelsMap.get(locales).setText(nombre.get(idLocal).getNombre());
			}
			if (labelsMap.containsKey(visitantes)) {
				int idVis = partido.getEquipoVis();
				labelsMap.get(visitantes).setText(nombre.get(idVis).getNombre());
			}

			// Actualizar los valores en los textFields
			if (textFieldMap.containsKey(pointsLoc) && textFieldMap.containsKey(pointsVis)) {
				// Si el partido está jugado, mostrar los resultados
				if (partido.isJugado()) {
					if (partido.getpuntuajeLoc() == -1) {
						textFieldMap.get(pointsLoc).setText("");
					} else {
						textFieldMap.get(pointsLoc).setText(String.valueOf(partido.getpuntuajeLoc()));
					}

					if (partido.getpuntuajeVis() == -1) {
						textFieldMap.get(pointsVis).setText("");
					} else {
						textFieldMap.get(pointsVis).setText(String.valueOf(partido.getpuntuajeVis()));
					}

					// Si el partido está jugado, siempre deshabilitar los campos
					textFieldMap.get(pointsLoc).setEditable(false);
					textFieldMap.get(pointsVis).setEditable(false);
				} else {
					// Si el partido no está jugado, limpiar los campos
					textFieldMap.get(pointsLoc).setText("");
					textFieldMap.get(pointsVis).setText("");

					// Controlar habilitación según el estado de la temporada
					if (TemporadaSeleccionada.isFinalizado() || !TemporadaSeleccionada.isIniciado()) {
						textFieldMap.get(pointsLoc).setEditable(false);
						textFieldMap.get(pointsVis).setEditable(false);
					} else {
						textFieldMap.get(pointsLoc).setEditable(true);
						textFieldMap.get(pointsVis).setEditable(true);
					}
				}
			}
		}
	}

	private void cambioEstadoTextFields() {
		jornadaSelect = Integer.parseInt(numJornada.getText());
		Jornada jornadaActual = listJornadas.get(jornadaSelect - 1);
		partidos = jornadaActual.getListPartidos();
		String locales;
		String visitantes;
		String pointsLoc;
		String pointsVis;
		for (int counter = 0; counter < partidos.size(); counter++) {
			Partido partido = partidos.get(counter);
			locales = "partidoELoc" + counter;
			visitantes = "partidoEVis" + counter;
			pointsLoc = "pointsELoc" + counter;
			pointsVis = "pointsEVis" + counter;

			// Actualizar los valores en los textFields
			if (textFieldMap.containsKey(pointsLoc) && textFieldMap.containsKey(pointsVis)) {
				// Si el partido está jugado, mostrar los resultados
				if (partido.isJugado()) {

					// Si el partido está jugado, siempre deshabilitar los campos
					textFieldMap.get(pointsLoc).setEditable(false);
					textFieldMap.get(pointsVis).setEditable(false);
				} else {
					// Si el partido no está jugado, limpiar los campos
					textFieldMap.get(pointsLoc).setText("");
					textFieldMap.get(pointsVis).setText("");

					// Controlar habilitación según el estado de la temporada
					if (TemporadaSeleccionada.isFinalizado() || !TemporadaSeleccionada.isIniciado()) {
						textFieldMap.get(pointsLoc).setEditable(false);
						textFieldMap.get(pointsVis).setEditable(false);
					} else {
						textFieldMap.get(pointsLoc).setEditable(true);
						textFieldMap.get(pointsVis).setEditable(true);
					}
				}
			}
		}

	}

	private void ActualizarPuntuaciones() {
		// variables para almacenar los id de el map
		String locales;
		String visitantes;
		String localesNom;
		String visitanteNom;
		// variables para almacenar los puntos de el partido
		int puntuacionLoc;
		int puntuacionVis;
		Partido partido;
		// cada vez que se ejecuta el for pasa al siguiente partido
		for (int counter = 0; counter < partidos.size(); counter++) {
			// id de los map
			locales = "pointsELoc" + counter;
			visitantes = "pointsEVis" + counter;
			localesNom = "partidoELoc" + counter;
			visitanteNom = "partidoEVis" + counter;
			// rescatamos el partido de el array
			partido = partidos.get(counter);
			// rescatamos el id de el equipo local
			int idLocal = partido.getEquipoLoc();
			// guardamos el nombre de el equipo local
			localesNom = nombre.get(idLocal).getNombre();
			// rescatamos el id de el equipo Visitante
			int idVisitante = partido.getEquipoVis();
			// guardamos el nombre de el equipo visitante
			visitanteNom = nombre.get(idVisitante).getNombre();
			// verifiacamos que contenga la id y que este vacio para asignarle un valor nulo
			if (textFieldMap.containsKey(locales) && textFieldMap.get(locales).getText().isEmpty()) {
				puntuacionLoc = -1;

				partido.setpuntuajeLoc(puntuacionLoc);

			} else {
				// guardamos la puntuacion si es valida
				puntuacionLoc = Integer.parseInt(textFieldMap.get(locales).getText());

				partido.setpuntuajeLoc(puntuacionLoc);
			}
			// verifiacamos que contenga la id y que este vacio para asignarle un valor nulo
			if (textFieldMap.containsKey(visitantes) && textFieldMap.get(visitantes).getText().isEmpty()) {
				puntuacionVis = -1;

				partido.setpuntuajeVis(puntuacionVis);

			} else {
				// guardamos la puntuacion si es valida
				puntuacionVis = Integer.parseInt(textFieldMap.get(visitantes).getText());

				partido.setpuntuajeVis(puntuacionVis);
				partido.setJugado(true);
			}

			// sistema para añadir los diferentes puntuajes
			if (puntuacionLoc < puntuacionVis) {
				// asignamos quien ha ganado el partido en el objeto de partido
				partido.setGanadorVis(true);

				// recorremos la lista de equipos de la temporada buscando el nombre
				for (int contador = 0; contador < nombre.size(); contador++) {
					// comparacion para ver que coincide el nombre
					if (localesNom.equals(nombre.get(contador).getNombre())) {
						// funcion para que agrege el total de partidos perdidos
						nombre.get(contador).incrementarPartidosPerdido();
						// funcion para que agrege el total de partidos
						nombre.get(contador).incrementarPartidosTotales();
						nombre.get(contador).addPuntosTotal(1);
					}
				}
				for (int contador = 0; contador < nombre.size(); contador++) {
					if (visitanteNom.equals(nombre.get(contador).getNombre())) {
						// funcion para que agrege el total de partidos Ganados
						nombre.get(contador).incrementarPartidosGanados();
						// funcion para que agrege el total de partidos
						nombre.get(contador).incrementarPartidosTotales();
						nombre.get(contador).addPuntosTotal(3);
					}
				}

				// este codigo implementa lo mismo que el de arriba invertido
			} else {

				partido.setGanadorLoc(true);

				for (int contador = 0; contador < nombre.size(); contador++) {
					if (localesNom.equals(nombre.get(contador).getNombre())) {
						nombre.get(contador).incrementarPartidosGanados();
						nombre.get(contador).incrementarPartidosTotales();
						nombre.get(contador).addPuntosTotal(3);
					}
				}
				for (int contador = 0; contador < nombre.size(); contador++) {
					if (visitanteNom.equals(nombre.get(contador).getNombre())) {
						nombre.get(contador).incrementarPartidosPerdido();
						nombre.get(contador).incrementarPartidosTotales();
						nombre.get(contador).addPuntosTotal(1);
					}
				}

			}

			parentFrame.changes = true;

		}

	}

	private void TemporadasIniciadas() {
		TemporadaSeleccionada = (Temporada) comboBox.getSelectedItem();
		if (TemporadaSeleccionada.isIniciado() == false
				|| (TemporadaSeleccionada.isIniciado() && TemporadaSeleccionada.isFinalizado())) {
			btnSave.setVisible(false);
			btnUpdateApp.setVisible(false);
		} else {
			btnSave.setVisible(true);
			btnUpdateApp.setVisible(true);
		}

	}

	private void guardarDatos() {
		if (parentFrame.changes == true) {
			try (FileOutputStream fos = new FileOutputStream("Temporada.ser");
					ObjectOutputStream oos = new ObjectOutputStream(fos)) {
				int length = listTemporadas.size();
				int counter = 0;
				while (counter < length) {
					oos.writeObject(listTemporadas.get(counter));
					counter++;
				}
				parentFrame.mensaje("Cambios guardados.", 2);

				parentFrame.changes = false;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				parentFrame.mensaje("Error al guardar", 0);

			}
		}
	}

	private void cargarTabla() {
		modelClasificacion.setRowCount(0);
		for (int counter = 0; nombre.size() > counter; counter++) {
			modelClasificacion.addRow(new Object[] { counter + 1, // Posición
					nombre.get(counter).getNombre(), // Nombre del equipo
					0 // Puntuación inicial
			});
			TemporadaSeleccionada = (Temporada) comboBox.getSelectedItem();

			TemporadaSeleccionada.setClasificacion(nombre);

		}
		TemporadaSeleccionada.setClasificacion(nombre);

	}

	private void actualizarTabla() {

		TemporadaSeleccionada = (Temporada) comboBox.getSelectedItem();
		modelClasificacion.setRowCount(0);
		Clasificacion = TemporadaSeleccionada.getClasificacion();

		Clasificacion.sort(
				Comparator.comparingInt(Equipo::getPuntosTotales).reversed().thenComparingInt(Equipo::getFechaFundEq));

		for (int counter = 0; Clasificacion.size() > counter; counter++) {
			modelClasificacion.addRow(new Object[] { counter + 1, // Posición
					Clasificacion.get(counter).getNombre(), // Nombre del equipo
					Clasificacion.get(counter).getPuntosTotales(), Clasificacion.get(counter).getTotalPartidos(),
					Clasificacion.get(counter).getVictorias(), Clasificacion.get(counter).getDerrotas(),

			});
		}

	}

	private void exportacion() {
	    CreadorXML XML = new CreadorXML();
	    
	    // Ruta base donde se guardarán los archivos XML
	    String rutaBase = "C:\\xampp\\htdocs\\xml\\";
	    
	    for (int t = 0; t < listTemporadas.size(); t++) {
	        Temporada temporada = listTemporadas.get(t);
	        XML = new CreadorXML();

	        ArrayList<Jornada> listJornadas = temporada.getListJornadas();
	        XML.add("temporada", false, 0);
	            XML.add("nombre", false, 1);
	                XML.add(temporada.getNombre(), true, 2);
	            XML.add("nombre", false, 1);
	            XML.add("iniciado", false, 1);
	                XML.add(Boolean.toString(temporada.isIniciado()), true, 2);
	            XML.add("iniciado", false, 1);
	            XML.add("finalizado", false, 1);
	                XML.add(Boolean.toString(temporada.isFinalizado()), true, 2);
	            XML.add("finalizado", false, 1);
	            XML.add("jornadas", false, 1);
	            
	            for (int i = 0; i < temporada.getCantidadJornadas(); i++) {
	                Jornada jornada = listJornadas.get(i);
	                XML.add("jornada", false, 2);
	                    XML.add("id_jornada", false, 3);
	                        XML.add(Integer.toString(jornada.getid_jornada()), true, 4);
	                    XML.add("id_jornada", false, 3);
	                    XML.add("partidos", false, 3);
	                    for (Partido partido : jornada.getListPartidos()) {
	                        XML.add("partido", false, 4);
	                           


	                            XML.add("equipos", false, 5);
	                                XML.add("equipo_local", false, 6);
	                                    XML.add("NombreLocal", false, 7);
	                                    int idLocal = partido.getEquipoLoc();
	                                        XML.add(nombre.get(idLocal).getNombre(), true, 8);
	                                    XML.add("NombreLocal", false, 7);
	                                    XML.add("puntuacion", false, 7);
	                                        XML.add(Integer.toString(partido.getPuntuajeLoc()), true, 8);
	                                    XML.add("puntuacion", false, 7);
	                                XML.add("equipo_local", false, 6);

	                                XML.add("equipo_visitante", false, 6);
	                                int idVis = partido.getEquipoVis();
	                                    XML.add("nombreVisitante", false, 7);
	                                        XML.add(nombre.get(idVis).getNombre(), true, 8);
	                                    XML.add("nombreVisitante", false, 7);
	                                    XML.add("puntuacion", false, 7);
	                                        XML.add(Integer.toString(partido.getPuntuajeVis()), true, 8);
	                                    XML.add("puntuacion", false, 7);
	                                XML.add("equipo_visitante", false, 6);
	                            XML.add("equipos", false, 5);

	                        XML.add("partido", false, 4);
	                    }
	                    XML.add("partidos", false, 3);
	                XML.add("jornada", false, 2);
	            }
	            XML.add("jornadas", false, 1);
	            
	            // Añadir la clasificación de la temporada
	            ArrayList<Equipo> clasificacion = temporada.getClasificacion();
	            XML.add("clasificacion", false, 1);
	            for (int i = 0; i < clasificacion.size(); i++) {
	                Equipo equipo = clasificacion.get(i);
	                XML.add("equipo", false, 2);
	                    XML.add("posicion", false, 3);
	                        XML.add(Integer.toString(i + 1), true, 4);  // La posición es i + 1
	                    XML.add("posicion", false, 3);
	                    
	                    XML.add("nombre", false, 3);
	                        XML.add(equipo.getNombre(), true, 4);
	                    XML.add("nombre", false, 3);
	                XML.add("equipo", false, 2);
	            }
	            XML.add("clasificacion", false, 1);
	            
	        XML.add("temporada", false, 0);

	        // Crear un archivo para cada temporada en la ruta especificada
	        XML.file(rutaBase + listTemporadas.get(t).getNombre());
	    }
	}


	
	@Override
 	public void actionPerformed(ActionEvent ae) {
		Object o = ae.getSource();
		if (o == nextButton) {
			jornadaSelect = Integer.parseInt(numJornada.getText());

			// Si el índice actual es menor al tamaño de la lista, se puede avanzar
			if (jornadaSelect < listJornadas.size()) {
				jornadaSelect++;
				numJornada.setText(String.valueOf(jornadaSelect));
				SwichDatosJornada();
				cambioEstadoTextFields();
			} else {
				parentFrame.mensaje("No hay más jornadas disponibles.", 1);
			}

		} else if (o == prevButton) {
			jornadaSelect = Integer.parseInt(numJornada.getText());

			// Si el índice actual es mayor a 1, se puede retroceder
			if (jornadaSelect > 1) {
				jornadaSelect--;
				numJornada.setText(String.valueOf(jornadaSelect));
				SwichDatosJornada();
				cambioEstadoTextFields();

			} else {
				parentFrame.mensaje("No hay jornadas anteriores", 1);
			}

		} else if (o == btnSave) {
			if (pointsELoc0.getText().isEmpty() && pointsEVis0.getText().isEmpty() && pointsELoc1.getText().isEmpty()
					&& pointsEVis1.getText().isEmpty() && pointsELoc2.getText().isEmpty()
					&& pointsEVis2.getText().isEmpty()) {
				parentFrame.mensaje("No puede haber ningun campo vacio", 1);

			} else {
				if (userType < 2) {
					int seleccion = JOptionPane.showConfirmDialog(null,
							// Mensaje que se mostrará en el cuadro de diálogo
							"¿Deseas guardar la jornada? si lo hace la jornada y los partidos quedaran finalizados y no se podran editar a no ser de que los edite el administrador ",
							"Confirmar Acción", // Título de la ventana
							JOptionPane.YES_NO_OPTION, // Botones de Sí y No
							JOptionPane.QUESTION_MESSAGE // Icono de pregunta
					);

					// Comprobar la opción seleccionada
					if (seleccion == JOptionPane.YES_OPTION) {
						ActualizarPuntuaciones(); // Si el usuario selecciona "Sí", ejecuta el método
						cambioEstadoTextFields();
						actualizarTabla();
					}
				} else {
					int seleccion = JOptionPane.showConfirmDialog(null, "¿Deseas guardar la jornada?", // Mensaje que se
																										// mostrará en
																										// el
																										// cuadro de
																										// diálogo
							"Confirmar Acción", // Título de la ventana
							JOptionPane.YES_NO_OPTION, // Botones de Sí y No
							JOptionPane.QUESTION_MESSAGE // Icono de pregunta
					);

					// Comprobar la opción seleccionada
					if (seleccion == JOptionPane.YES_OPTION) {
						ActualizarPuntuaciones(); // Si el usuario selecciona "Sí", ejecuta el método
						cambioEstadoTextFields();
						actualizarTabla();
					}
				}
			}

		} else if (o == btnUpdateApp) {
			guardarDatos();
		}

	}

}