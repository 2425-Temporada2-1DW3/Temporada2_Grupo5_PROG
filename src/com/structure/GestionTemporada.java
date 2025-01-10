package com.structure;

import java.awt.EventQueue;
import javax.swing.*;

import com.logic.Temporada;

import java.awt.event.ActionListener;

public class GestionTemporada {

    private JFrame frame;
    private JTextField txtNombre;
    private JTextField txtCantidadEquipos;
    private JList<Temporada> jListTemporada;
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
            int id = idTemp;
            String nombre = txtNombre.getText();
            int cantidadEquipos = Integer.parseInt(txtCantidadEquipos.getText());
            int cantidadJornadas = 30;

            Temporada nueva = new Temporada(id, nombre, cantidadEquipos, cantidadJornadas);

            dlm.addElement(nueva); // Agregar a la lista
            idTemp++;
           
            JOptionPane.showMessageDialog(frame, "Temporada creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, introduce valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void iniciarTemporada() {
    	int counter =0;
    	
    	if (dlm.getSize()==1) {
    		Temporada temporada = dlm.getElementAt(0);
    		dlm.getElementAt(0).setIniciado(true);
    		dlm.set(0, temporada);
    		
    	}else {
    		int seleccion = jListTemporada.getSelectedIndex();
    		while (counter < dlm.getSize()) {
    			System.out.println(counter);
				if( dlm.getElementAt(counter).isIniciado() == true && dlm.getElementAt(counter).isFinalizado()==false) {
					 JOptionPane.showMessageDialog(frame, "Error, una temporada ya esta iniciada", "Error", JOptionPane.ERROR_MESSAGE);
    				break;
    			}
				counter++;
    		}
    		
    		dlm.getElementAt(seleccion).setIniciado(true);
    	}
    	
    }
}
