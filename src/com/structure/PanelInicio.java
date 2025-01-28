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
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class PanelInicio extends JPanel implements ActionListener {
	// creamos los maps para hacer que el contenido de la pagina sea dinamico
	private Map<String, JLabel> labelsMap = new HashMap<>();
	private Map<String, JTextField> textFieldMap = new HashMap<>();
	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	JPanel PanelJornadas;
	JPanel PanelClasificacion;
	private JPanel PanelTituloJornadas;
	private JLabel lblNewLabel_1;
	private JPanel PanelContenidoPartidos;
	private JPanel PanelSelectTemporada;
	private JPanel PanelTitulo;
	private JPanel PanelMostrarPartidos;
	private JPanel PanelPartido1;
	private JLabel partidoELoc0;
	//private JTextField partido1LocPoints;
	private JPanel PanelPartido3;
	//private JTextField partido3LocPoints;
	private JPanel PanelBotones;
	private JTable table;
	private JLabel partidoEVis0;

	private JTextField pointsELoc0,pointsEVis0 ;
	private int jornadaSelect = 1;
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

		
		PanelContenedor = new JPanel();
		add(PanelContenedor, BorderLayout.SOUTH);
		PanelContenedor.setLayout(new BorderLayout(0, 0));
		
		PanelTitulo = new JPanel();
		PanelContenedor.add(PanelTitulo, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("CLASIFICACION");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(parentFrame.fuenteHeader);
		PanelTitulo.add(lblNewLabel);
		
		PanelContenido = new JPanel();
		PanelContenedor.add(PanelContenido, BorderLayout.SOUTH);
		PanelContenido.setLayout(new BoxLayout(PanelContenido, BoxLayout.X_AXIS));

		// Inicializar PanelJornadas
		PanelJornadas = new JPanel();
		PanelJornadas.setLayout(new BorderLayout(0, 0));
		// Agregar PanelJornadas dentro de PanelContenido en la zona izquierda
		PanelContenido.add(PanelJornadas);

		PanelTituloJornadas = new JPanel();
		PanelJornadas.add(PanelTituloJornadas, BorderLayout.NORTH);

		lblNewLabel_1 = new JLabel("Jornadas");

		PanelTituloJornadas.add(lblNewLabel_1);

		PanelContenidoPartidos = new JPanel();
		PanelJornadas.add(PanelContenidoPartidos, BorderLayout.CENTER);
		PanelContenidoPartidos.setLayout(new BorderLayout(0, 0));

		PanelSelectTemporada = new JPanel();
		PanelContenidoPartidos.add(PanelSelectTemporada, BorderLayout.NORTH);

		comboBox = new JComboBox<>();

		PanelSelectTemporada.add(comboBox);
		actualizarComboBox();

		PanelMostrarPartidos = new JPanel();
		PanelContenidoPartidos.add(PanelMostrarPartidos, BorderLayout.CENTER);
		PanelMostrarPartidos.setLayout(new GridLayout(0, 1, 0, 0));

		PanelPartido1 = new JPanel();
		PanelMostrarPartidos.add(PanelPartido1);

		// Inicializar campos para Partido 1
		partidoELoc0 = new JLabel();

		labelsMap.put("partidoELoc0", partidoELoc0);
		PanelPartido1.add(partidoELoc0);

		pointsELoc0 = new JTextField();

		textFieldMap.put("pointsELoc0", pointsELoc0);
		PanelPartido1.add(pointsELoc0);
		pointsELoc0.setColumns(10);

		partidoEVis0 = new JLabel();
		labelsMap.put("partidoEVis0", partidoEVis0);
		PanelPartido1.add(partidoEVis0);

		pointsEVis0 = new JTextField();

		textFieldMap.put("pointsEVis0", pointsEVis0);
		PanelPartido1.add(pointsEVis0);
		pointsEVis0.setColumns(10);

		
		panelPartido2 = new JPanel();
		PanelMostrarPartidos.add(panelPartido2);
		
		partidoELoc1 = new JLabel();
		labelsMap.put("partidoELoc1", partidoELoc1);
		panelPartido2.add(partidoELoc1);
		
		pointsELoc1 = new JTextField();
		textFieldMap.put("pointsELoc1", pointsELoc1);
		pointsELoc1.setColumns(10);
		panelPartido2.add(pointsELoc1);
		
		partidoEVis1 = new JLabel();
		labelsMap.put("partidoEVis1", partidoEVis1);
		panelPartido2.add(partidoEVis1);
		
		pointsEVis1 = new JTextField();
		textFieldMap.put("pointsEVis1", pointsEVis1);
		pointsEVis1.setColumns(10);
		panelPartido2.add(pointsEVis1);

		PanelPartido3 = new JPanel();
		PanelMostrarPartidos.add(PanelPartido3);
		PanelPartido3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		partidoELoc2 = new JLabel();
		labelsMap.put("partidoELoc2", partidoELoc2);
		PanelPartido3.add(partidoELoc2);
		
		pointsELoc2 = new JTextField();
		textFieldMap.put("pointsELoc2", pointsELoc2);
		pointsELoc2.setColumns(10);
		PanelPartido3.add(pointsELoc2);
		
		partidoEVis2 = new JLabel();
		labelsMap.put("partidoEVis2", partidoEVis2);
		PanelPartido3.add(partidoEVis2);
		
		pointsEVis2 = new JTextField();
		textFieldMap.put("pointsEVis2", pointsEVis2);
		pointsEVis2.setColumns(10);
		PanelPartido3.add(pointsEVis2);
		
		panelMoverJornadas = new JPanel();
		PanelContenidoPartidos.add(panelMoverJornadas, BorderLayout.SOUTH);
		
		prevButton = new JButton("<");
		prevButton.addActionListener(this);
		panelMoverJornadas.add(prevButton);
		
		numJornada = new JLabel("1");
		panelMoverJornadas.add(numJornada);
		
		nextButton = new JButton(">");

		panel_14.add(nextButton);
		nextButton.addActionListener(this);
		panel_12 = new JPanel();
		panel_1.add(panel_12, BorderLayout.SOUTH);
 
		btnSave = new JButton("Finalizar Jornada");

		btnSave.addActionListener(this);
		PanelBotones.add(btnSave);

		btnUpdateApp = new JButton("Aplicar cambios en el sistema");

		btnUpdateApp.addActionListener(this);
		PanelBotones.add(btnUpdateApp);

		// Inicializar PanelClasificacion
		PanelClasificacion = new JPanel();
		// Agregar panel_2 dentro de PanelContenido en la zona central
		PanelContenido.add(PanelClasificacion);

		table = new JTable();

		table.setEnabled(false);
		modelClasificacion = new DefaultTableModel();
		modelClasificacion.addColumn("Posición");
		modelClasificacion.addColumn("Equipo");
		modelClasificacion.addColumn("Pts Total");
		modelClasificacion.addColumn("PJ");
		modelClasificacion.addColumn("PG");
		modelClasificacion.addColumn("PP");
		PanelClasificacion.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		table.setModel(modelClasificacion);

		// Agregar JScrollPane
		JScrollPane scrollPane = new JScrollPane(table);
		PanelClasificacion.add(scrollPane);

		JComponent labelFormat[] = { partidoELoc0, pointsELoc0, partidoEVis0, pointsEVis0, partidoELoc1, pointsELoc1,
				partidoEVis1, pointsEVis1, partidoELoc2, pointsELoc2, partidoEVis2, pointsEVis2, lblNewLabel_1,
				comboBox, prevButton, numJornada, nextButton, btnSave, btnUpdateApp, };
		JComponent panelFormat[] = { PanelTitulo, PanelContenido, PanelJornadas, PanelClasificacion, PanelTituloJornadas, PanelContenidoPartidos, PanelSelectTemporada, PanelMostrarPartidos, PanelPartido1, PanelPartido3,
				panelPartido2, PanelPartido3, PanelBotones, panelMoverJornadas };

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
		

	}

	private void cargarTemporadasDesdeArchivo() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(parentFrame.temporadasFile))) {
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
			try (FileOutputStream fos = new FileOutputStream(parentFrame.temporadasFile);
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
	                    XML.add("puntosTotales", false, 3);
	                    XML.add(Integer.toString(equipo.getPuntosTotales()), true, 4);
	                    XML.add("puntosTotales", false, 3);
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
			exportacion();
		}

	}

}