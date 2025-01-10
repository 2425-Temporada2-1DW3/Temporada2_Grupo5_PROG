package com.structure;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import com.logic.Temporada;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionTemporada {

    private JFrame frame;
    private JTextField txtNombre;
    private JTextField txtId;
    private JTextField txtCantidadEquipos;
    private JTextField txtCantidadJornadas;
    /*
    private JList<Temporada> temporadas = new JList<>();
    private DefaultListModel<Temporada> dlm = new DefaultListModel<>();
*/
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
        btnCrearTemporada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                crearTemporada();
            }
        });
        btnCrearTemporada.setBounds(10, 135, 150, 23);
        frame.getContentPane().add(btnCrearTemporada);

        JButton btnIniciarTemporada = new JButton("Iniciar Temporada");
        btnIniciarTemporada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarTemporada();
            }
        });
        btnIniciarTemporada.setBounds(170, 135, 150, 23);
        frame.getContentPane().add(btnIniciarTemporada);

        JButton btnFinalizarTemporada = new JButton("Finalizar Temporada");
        btnFinalizarTemporada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                finalizarTemporada();
            }
        });
        btnFinalizarTemporada.setBounds(330, 135, 150, 23);
        frame.getContentPane().add(btnFinalizarTemporada);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 180, 560, 170);
        frame.getContentPane().add(scrollPane);
        
        JList JlistTemporada = new JList();
        JlistTemporada.setBounds(0, 0, 1, 1);
        frame.getContentPane().add(JlistTemporada);
    }
    int idTemp = 0;
    private void crearTemporada() {
    	
        try {
            int id =idTemp ;
            String nombre = txtNombre.getText();
            int cantidadEquipos = Integer.parseInt(txtCantidadEquipos.getText());
            int cantidadJornadas = Integer.parseInt(txtCantidadJornadas.getText());

            Temporada nueva = new Temporada(id, nombre, cantidadEquipos, cantidadJornadas);
            
            
            idTemp ++;
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(frame, "Por favor, introduce valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void iniciarTemporada() {
        if (temporadas.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay temporadas creadas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Temporada ultima = temporadas.get(temporadas.size() - 1);
        ultima.iniciarTemporada();
        textAreaOutput.append("Temporada iniciada: " + ultima + "\n");
    }

    private void finalizarTemporada() {
        if (temporadas.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No hay temporadas creadas.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Temporada ultima = temporadas.get(temporadas.size() - 1);
        ultima.finalizarTemporada();
        textAreaOutput.append("Temporada finalizada: " + ultima + "\n");
    }
}
