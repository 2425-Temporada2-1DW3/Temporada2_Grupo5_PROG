package com.structure;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.logic.Equipo;
import com.logic.Temporada;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class gestionEquiposTemporada extends JPanel {

    private static final long serialVersionUID = 1L;

    private JComboBox<Temporada> seasonSelector;
    private JTable dataTable;
    private JButton saveButton;
    private ArrayList<Temporada> temporadas;

    public gestionEquiposTemporada() {
        setLayout(new BorderLayout());
        temporadas = new ArrayList<>();

        // Panel superior con el selector de temporada
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel seasonLabel = new JLabel("Seleccionar Temporada:");
        topPanel.add(seasonLabel);

        seasonSelector = new JComboBox<>();
        cargarTemporadasDesdeArchivo(); // Cargar temporadas desde el archivo
        topPanel.add(seasonSelector);

        add(topPanel, BorderLayout.NORTH);

        // Modelo de la tabla
        String[] columnNames = {"Nombre Equipo", "Año fundación", "Entrenador"};
        Object[][] data = {}; // Datos vacíos inicialmente

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0:
                    case 2:
                        return String.class;
                    case 1:
                        return Integer.class; // Año de fundación como Integer
                    default:
                        return Object.class;
                }
            }
        };

        dataTable = new JTable(tableModel); // Inicialización de dataTable
        JScrollPane scrollPane = new JScrollPane(dataTable);
        add(scrollPane, BorderLayout.CENTER);

        // Botón de guardar cambios
        saveButton = new JButton("Guardar Cambios");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.add(saveButton);
        add(bottomPanel, BorderLayout.SOUTH);

        actualizarComboBox(); // Actualizar el combo box después de inicializar la tabla
    }

    private void guardarCambios() {
        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
        Temporada temporadaSeleccionada = (Temporada) seasonSelector.getSelectedItem();

        if (temporadaSeleccionada != null) {
            boolean allFieldsFilled = true;

            ArrayList<Equipo> equiposDeTemporada = temporadaSeleccionada.getListEquipos();

            // Actualizar objetos Equipo con los datos de la tabla
            for (int i = 0; i < model.getRowCount(); i++) {
                String equipoNombre = (String) model.getValueAt(i, 0);
                Integer anioFundacion = (Integer) model.getValueAt(i, 1);
                String entrenador = (String) model.getValueAt(i, 2);

                if (equipoNombre == null || equipoNombre.isEmpty() ||
                    anioFundacion == null || entrenador == null || entrenador.isEmpty()) {
                    allFieldsFilled = false;
                    break;
                }

                // Actualizar el equipo correspondiente
                Equipo equipo = equiposDeTemporada.get(i);
                equipo.setNombre(equipoNombre);
                equipo.setFechaFundEq(anioFundacion);
                equipo.setEntrenador(entrenador);
            }

            if (allFieldsFilled) {
                // Guardar los cambios en el archivo
                actualizarArchivo();

                // Construir mensaje con datos actualizados
                StringBuilder mensaje = new StringBuilder("Datos guardados correctamente:\n");
                for (Equipo equipo : equiposDeTemporada) {
                    mensaje.append("Nombre: ").append(equipo.getNombre())
                           .append(", Año Fundación: ").append(equipo.getFechaFundEq())
                           .append(", Entrenador: ").append(equipo.getEntrenador())
                           .append("\n");
                }

                // Mostrar mensaje en un cuadro de diálogo
                JOptionPane.showMessageDialog(this, mensaje.toString(), "Información", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Equipos por Temporada");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.add(new gestionEquiposTemporada());
        frame.setVisible(true);
    }

    private void actualizarArchivo() {
        try (FileOutputStream fos = new FileOutputStream("Temporada.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            for (Temporada temporada : temporadas) {
                oos.writeObject(temporada);
            }
            JOptionPane.showMessageDialog(null, "Cambios guardados.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarTemporadasDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Temporada.ser"))) {
            while (true) {
                try {
                    Temporada temp = (Temporada) ois.readObject();
                    temporadas.add(temp);
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
        seasonSelector.removeAllItems();
        for (Temporada temporada : temporadas) {
            seasonSelector.addItem(temporada);
        }

        if (!temporadas.isEmpty()) {
            seasonSelector.setSelectedIndex(0);
            actualizarTablaConEquipos();
        }

        seasonSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTablaConEquipos();
            }
        });
    }

    private void actualizarTablaConEquipos() {
        Temporada temporadaSeleccionada = (Temporada) seasonSelector.getSelectedItem();

        if (temporadaSeleccionada != null) {
            ArrayList<Equipo> equiposDeTemporada = temporadaSeleccionada.getListEquipos();

            String[] columnNames = {"Nombre Equipo", "Año Fundación", "Entrenador"};
            Object[][] data = new Object[equiposDeTemporada.size()][3];

            for (int i = 0; i < equiposDeTemporada.size(); i++) {
                Equipo equipo = equiposDeTemporada.get(i);
                data[i][0] = equipo.getNombre();
                data[i][1] = equipo.getFechaFundEq();
                data[i][2] = equipo.getEntrenador();
            }

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    switch (columnIndex) {
                        case 0:
                        case 2:
                            return String.class;
                        case 1:
                            return Integer.class;
                        default:
                            return Object.class;
                    }
                }
            };

            dataTable.setModel(tableModel);
        }
    }
}
