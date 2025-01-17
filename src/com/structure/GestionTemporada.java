package com.structure;

import java.awt.EventQueue;
import javax.swing.*;

import com.logic.Temporada;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.logic.GeneradorTemporada;

public class GestionTemporada {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtCantidadEquipos;
	private JList<Temporada> jListTemporada;
	private ArrayList <Temporada> listTemporadas = new ArrayList<Temporada>();
	private DefaultListModel<Temporada> dlm = new DefaultListModel<>();

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				GestionTemporada window = new GestionTemporada();
				window.frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public GestionTemporada() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Gestor de Temporadas");
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 80, 14);
		frame.getContentPane().add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(100, 8, 200, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblCantidadEquipos = new JLabel("Cantidad de Equipos:");
		lblCantidadEquipos.setBounds(10, 73, 150, 14);
		frame.getContentPane().add(lblCantidadEquipos);

		txtCantidadEquipos = new JTextField();
		txtCantidadEquipos.setBounds(160, 70, 140, 20);
		frame.getContentPane().add(txtCantidadEquipos);
		txtCantidadEquipos.setColumns(10);

		JButton btnCrearTemporada = new JButton("Crear Temporada");
		btnCrearTemporada.addActionListener(e -> crearTemporada());
		btnCrearTemporada.setBounds(10, 135, 150, 23);
		frame.getContentPane().add(btnCrearTemporada);

		JButton btnIniciarTemporada = new JButton("Iniciar Temporada");
		btnIniciarTemporada.setBounds(170, 135, 150, 23);
		btnIniciarTemporada.addActionListener(e -> iniciarTemporada());
		frame.getContentPane().add(btnIniciarTemporada);

		JButton btnFinalizarTemporada = new JButton("Finalizar Temporada");
		btnFinalizarTemporada.setBounds(330, 135, 150, 23);
		frame.getContentPane().add(btnFinalizarTemporada);
		btnFinalizarTemporada.addActionListener(e -> finalizarTemporada());
		// JList para mostrar las temporadas
		jListTemporada = new JList<>(dlm);
		jListTemporada.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(jListTemporada); // Envolver en JScrollPane
		scrollPane.setBounds(10, 180, 570, 170); // Ajustar el tamaño y posición
		frame.getContentPane().add(scrollPane);
	}

	int idTemp = 0;

	private void crearTemporada() {
		try {
//			GeneradorTemporada generador = new GeneradorTemporada();
			int id = idTemp; // Generar un ID único para la temporada
	        String nombre = txtNombre.getText(); // Obtener el nombre de la temporada desde el input
	        int cantidadEquipos = Integer.parseInt(txtCantidadEquipos.getText()); // Convertir el texto en número
//	        int cantidadJornadas = 30; // Fijar el número de jornadas

	        // Crear una nueva instancia de Temporada
	        Temporada nueva = new Temporada(id, nombre, cantidadEquipos);

	        // Agregar la nueva temporada a la lista
	        listTemporadas.add(nueva);

	        // Agregar al modelo (si usas DefaultListModel para actualizar un JList, por ejemplo)
	        dlm.addElement(nueva);

	        // Incrementar el ID para la próxima temporada
	        idTemp++;
//	        generador.GenerarTemporada(id, cantidadEquipos);
	        // Mostrar mensaje de éxito
	        JOptionPane.showMessageDialog(frame, "Temporada creada exitosamente.", "Éxito",
	                JOptionPane.INFORMATION_MESSAGE);
	    } catch (NumberFormatException e) {
	        // Manejar errores de conversión
	        JOptionPane.showMessageDialog(frame, "Por favor, introduce valores válidos.", "Error",
	                JOptionPane.ERROR_MESSAGE);
	    }
	}
	private void iniciarTemporada() {
		boolean error = false;
		int counter = 0;
		
		
		// si la no hay nada seleccionado
		
		int seleccion = jListTemporada.getSelectedIndex();
		
		if (seleccion == -1) {
			JOptionPane.showMessageDialog(frame, "Error, no hay ninguna temporada seleccionada", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			// mientras que contador sea menor a la lista
			Temporada temporadaSeleccionada = dlm.getElementAt(seleccion);
			while (counter < dlm.getSize()) {
				// compara si el estado de isIniciado= true y isFinalizado = false error

				if (dlm.getElementAt(counter).isIniciado() == true
						&& dlm.getElementAt(counter).isFinalizado() == false) {
					JOptionPane.showMessageDialog(frame, "Error, una temporada ya esta iniciada", "Error",
							JOptionPane.ERROR_MESSAGE);
					error = true;
					break;
				}
				counter++;
			}
			// cuando acaba de recorrer el dlm edita el estado de la temporada seleccionada
			// si no ha habido error
			if (!error) {
				dlm.getElementAt(seleccion).setIniciado(true);
				dlm.set(seleccion, temporadaSeleccionada);
				
				// Obtener el ID de la temporada seleccionada
	            int idTemporada = temporadaSeleccionada.getIdTemporada();
	            System.out.println("Temporada iniciada. ID: " + idTemporada);

	            // Aquí puedes llamar a otro método con el ID, si es necesario
//	            GenerarAlgoritmo(idTemporada);
			}
		}

	}

	private void finalizarTemporada() {
		int seleccion = jListTemporada.getSelectedIndex();
		if (seleccion == -1) {
			JOptionPane.showMessageDialog(frame, "Error, no hay ninguna temporada seleccionada", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		} else {
			Temporada temporadaSeleccionada = dlm.getElementAt(seleccion);
			if (temporadaSeleccionada.isIniciado() == false) {
				JOptionPane.showMessageDialog(frame, "Error, la temporada no esta iniciada", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else if (temporadaSeleccionada.isIniciado() == true && temporadaSeleccionada.isFinalizado() == true) {
				JOptionPane.showMessageDialog(frame, "Error, La temporada ya esta finalizada", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				temporadaSeleccionada.setFinalizado(true);
				dlm.set(seleccion, temporadaSeleccionada);
			}
		}
	}
}
