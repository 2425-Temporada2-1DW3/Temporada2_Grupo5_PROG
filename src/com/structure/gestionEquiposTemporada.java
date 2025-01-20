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
        boolean allFieldsFilled = true;  // Variable para verificar si todos los campos están rellenos

        // Verificamos si todos los campos de la tabla están completos
        for (int i = 0; i < model.getRowCount(); i++) {
            String equipo = (String) model.getValueAt(i, 0);
            Integer fundacion = (Integer) model.getValueAt(i, 1);
            String manager = (String) model.getValueAt(i, 2);

            if (equipo == null || equipo.isEmpty() || fundacion == null || manager == null || manager.isEmpty()) {
                allFieldsFilled = false;  // Si algún campo está vacío, no llamamos al método de guardar
                break;  // Salimos del bucle si encontramos un campo vacío
            }
        }

        if (allFieldsFilled) {
            // Si todos los campos están rellenados, se guardan los cambios y se actualiza el archivo
            actualizarArchivo();
            JOptionPane.showMessageDialog(this, "Cambios guardados correctamente en el sistema.", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Advertencia", JOptionPane.WARNING_MESSAGE);
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

    // Método para cargar las temporadas desde un archivo
    private void cargarTemporadasDesdeArchivo() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Temporada.ser"))) {
            while (true) {
                try {
                    Temporada temp = (Temporada) ois.readObject();
                    temporadas.add(temp);
                } catch (EOFException ex) {
                    break; // Fin del archivo
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de temporadas. Se creará uno nuevo al guardar cambios.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar temporadas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void actualizarComboBox() {
        // Limpia el JComboBox y agrega las temporadas disponibles
        seasonSelector.removeAllItems();
        for (Temporada temporada : temporadas) {
            seasonSelector.addItem(temporada);  // Añadir temporada al JComboBox
        }

        // Si hay temporadas, selecciona la primera y actualiza la tabla con los equipos
        if (!temporadas.isEmpty()) {
            seasonSelector.setSelectedIndex(0);  // Selecciona la primera temporada por defecto
            actualizarTablaConEquipos();  // Cargar los equipos de la temporada seleccionada
        }

        // Listener para actualizar la tabla al cambiar la selección de temporada
        seasonSelector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarTablaConEquipos();  // Cargar equipos cuando cambie la selección
            }
        });
    }

    private void actualizarTablaConEquipos() {
        Temporada temporadaSeleccionada = (Temporada) seasonSelector.getSelectedItem();

        if (temporadaSeleccionada != null) {
            // Obtener los equipos de la temporada seleccionada
            ArrayList<Equipo> equiposDeTemporada = temporadaSeleccionada.getListEquipos();
            
            // Si hay equipos en la temporada, actualiza la tabla
            if (!equiposDeTemporada.isEmpty()) {
                String[] columnNames = {"Nombre Equipo", "Año Fundación", "Entrenador"};
                Object[][] data = new Object[equiposDeTemporada.size()][3];  // Creamos el arreglo de datos

                // Cargar los datos de los equipos en el arreglo
                for (int i = 0; i < equiposDeTemporada.size(); i++) {
                    Equipo equipo = equiposDeTemporada.get(i);
                    data[i][0] = equipo.getNombre();  // Nombre del equipo
                    data[i][1] = equipo.getFechaFundEq();  // Año de fundación del equipo (Integer)
                    data[i][2] = equipo.getEntrenador();  // Nombre del entrenador
                }

                // Actualizar el modelo de la tabla con los nuevos datos
                DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
                    @Override
                    public Class<?> getColumnClass(int columnIndex) {
                        switch (columnIndex) {
                            case 0:
                            case 2:
                                return String.class;
                            case 1:
                                return Integer.class;  // Año de fundación como Integer
                            default:
                                return Object.class;
                        }
                    }
                };

                // Establecer el modelo actualizado en la tabla
                dataTable.setModel(tableModel);
            } else {
                // Si no hay equipos para la temporada seleccionada
                JOptionPane.showMessageDialog(this, "No hay equipos para esta temporada.", "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }


   
    
}
