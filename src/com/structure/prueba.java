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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import net.miginfocom.swing.MigLayout;

public class prueba extends JPanel implements ActionListener {
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
	//private JTextField partido1LocPoints;
	private JPanel panel_8;
	private JPanel panel_9;
	//private JTextField partido2LocPoints;
	private JPanel panel_10;
	private JPanel panel_11;
	//private JTextField partido3LocPoints;
	private JPanel panel_12;
	private JTable table;
	private JPanel panel_13;
	private JPanel panel_14;
	private JButton prevButton;
	private JLabel numJornada;
	private JButton nextButton;
	
	
	
	
	
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
	private JPanel ContenidoPrincipal;
	private JPanel PanelJornadas;
	private JPanel PanelClasificacion;
	private JPanel Cabecera;
	private JPanel SeccionBotones;
	private JPanel ContenidoJornadas;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	private JPanel panel_20;
	private JPanel panel_21;
	private JLabel partidoELoc0;
	private JTextField pointsELoc0;
	private JLabel partidoEVis0;
	private JTextField pointsEVis0;
	private JLabel partidoELoc1;
	private JTextField pointsELoc1;
	private JLabel partidoEVis1;
	private JTextField pointsEVis1;
	private JLabel partidoELoc2;
	private JTextField pointsELoc2;
	private JLabel partidoEVis2;
	private JTextField pointsEVis2;

	/**
	 * Create the panel.
	 */

	public prueba(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro
		// sitio
		this.parentFrame = parentFrame;
		userType = parentFrame.userType;
		colorbg = parentFrame.colorbg;
		colortxt = parentFrame.colortxt;
		userName = parentFrame.userName;
		comboBox = new JComboBox<>();
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

		add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));

		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.NORTH);

		panel_4 = new JPanel();
		panel_1.add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));

		panel_5 = new JPanel();
		panel_4.add(panel_5, BorderLayout.NORTH);
//		actualizarComboBox();

		panel_6 = new JPanel();
		panel_4.add(panel_6, BorderLayout.CENTER);
		panel_6.setLayout(new BorderLayout(0, 0));

		panel_7 = new JPanel();
		panel_6.add(panel_7, BorderLayout.NORTH);

		panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.setLayout(new BorderLayout(0, 0));

		panel_9 = new JPanel();
		panel_8.add(panel_9, BorderLayout.NORTH);

		panel_10 = new JPanel();
		panel_8.add(panel_10, BorderLayout.CENTER);
		panel_10.setLayout(new BorderLayout(0, 0));

		panel_11 = new JPanel();
		panel_10.add(panel_11, BorderLayout.NORTH);
		panel_11.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		panel_13 = new JPanel();
		panel_10.add(panel_13, BorderLayout.CENTER);
		panel_13.setLayout(new BorderLayout(0, 0));

		panel_14 = new JPanel();
		panel_13.add(panel_14, BorderLayout.NORTH);
		panel_12 = new JPanel();
		panel_1.add(panel_12, BorderLayout.SOUTH);
		modelClasificacion = new DefaultTableModel();
		modelClasificacion.addColumn("Posición");
		modelClasificacion.addColumn("Equipo");
		modelClasificacion.addColumn("Pts Total");
		modelClasificacion.addColumn("PJ");
		modelClasificacion.addColumn("PG");
		modelClasificacion.addColumn("PP");

		
		
		ContenidoPrincipal = new JPanel();
		add(ContenidoPrincipal, BorderLayout.CENTER);
		ContenidoPrincipal.setLayout(new BoxLayout(ContenidoPrincipal, BoxLayout.X_AXIS));
		
		PanelJornadas = new JPanel();
		ContenidoPrincipal.add(PanelJornadas);
		PanelJornadas.setLayout(new BorderLayout(0, 0));
		
		Cabecera = new JPanel();
		PanelJornadas.add(Cabecera, BorderLayout.NORTH);
		
				lblNewLabel_1 = new JLabel("Jornadas");
				Cabecera.add(lblNewLabel_1);
		
		SeccionBotones = new JPanel();
		PanelJornadas.add(SeccionBotones, BorderLayout.SOUTH);
		
				btnSave = new JButton("Finalizar Jornada");
				SeccionBotones.add(btnSave);
				
						btnUpdateApp = new JButton("Aplicar cambios en el sistema");
						SeccionBotones.add(btnUpdateApp);
						
						ContenidoJornadas = new JPanel();
						PanelJornadas.add(ContenidoJornadas, BorderLayout.CENTER);
						
						panel_15 = new JPanel();
						
						panel_16 = new JPanel();
						ContenidoJornadas.setLayout(new MigLayout("", "[393px]", "[20px][362px]"));
						ContenidoJornadas.add(panel_15, "cell 0 0,grow");
						
								
								actualizarComboBox();
								panel_15.add(comboBox);
								
						ContenidoJornadas.add(panel_16, "cell 0 1,grow");
						panel_16.setLayout(new BorderLayout(0, 0));
						
						panel_17 = new JPanel();
						panel_16.add(panel_17, BorderLayout.SOUTH);
						
								prevButton = new JButton("<");
								panel_17.add(prevButton);
								
										numJornada = new JLabel("1");
										panel_17.add(numJornada);
										
												nextButton = new JButton(">");
												panel_17.add(nextButton);
												
												panel_18 = new JPanel();
												panel_16.add(panel_18, BorderLayout.CENTER);
												panel_18.setLayout(new GridLayout(0, 1, 0, 0));
												
												panel_19 = new JPanel();
												panel_18.add(panel_19);
												
												partidoELoc0 = new JLabel();
												panel_19.add(partidoELoc0);
												
												pointsELoc0 = new JTextField();
												pointsELoc0.setColumns(10);
												panel_19.add(pointsELoc0);
												
												partidoEVis0 = new JLabel();
												panel_19.add(partidoEVis0);
												
												pointsEVis0 = new JTextField();
												pointsEVis0.setColumns(10);
												panel_19.add(pointsEVis0);
												
												panel_20 = new JPanel();
												panel_18.add(panel_20);
												
												partidoELoc1 = new JLabel();
												panel_20.add(partidoELoc1);
												
												pointsELoc1 = new JTextField();
												pointsELoc1.setColumns(10);
												panel_20.add(pointsELoc1);
												
												partidoEVis1 = new JLabel();
												panel_20.add(partidoEVis1);
												
												pointsEVis1 = new JTextField();
												pointsEVis1.setColumns(10);
												panel_20.add(pointsEVis1);
												
												panel_21 = new JPanel();
												panel_18.add(panel_21);
												
												partidoELoc2 = new JLabel();
												panel_21.add(partidoELoc2);
												
												pointsELoc2 = new JTextField();
												pointsELoc2.setColumns(10);
												panel_21.add(pointsELoc2);
												
												partidoEVis2 = new JLabel();
												panel_21.add(partidoEVis2);
												
												pointsEVis2 = new JTextField();
												pointsEVis2.setColumns(10);
												panel_21.add(pointsEVis2);
												nextButton.addActionListener(this);
								
										prevButton.addActionListener(this);
						
								btnUpdateApp.addActionListener(this);
				
						btnSave.addActionListener(this);
		
		PanelClasificacion = new JPanel();
		ContenidoPrincipal.add(PanelClasificacion);
		
				panel_2 = new JPanel();
				PanelClasificacion.add(panel_2);
				
						table = new JTable();
						
								table.setEnabled(false);
								
										table.setModel(modelClasificacion);
										
										// Agregar JScrollPane
										JScrollPane scrollPane = new JScrollPane(table);
										PanelClasificacion.add(scrollPane);

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