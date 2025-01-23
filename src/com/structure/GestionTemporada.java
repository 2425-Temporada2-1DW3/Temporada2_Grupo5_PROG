package com.structure;

import java.awt.EventQueue;
import javax.swing.*;

import com.logic.Temporada;

import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import com.logic.GeneradorTemporada;
import com.logic.Jornada;
import com.logic.Equipo;
import com.logic.Partido;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
public class GestionTemporada {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtCantidadEquipos;
	private JList<Temporada> jListTemporada;
	private ArrayList <Temporada> listTemporadas = new ArrayList<Temporada>();
	private DefaultListModel<Temporada> dlm = new DefaultListModel<>();
	private DefaultTableModel tableModel;
    private JTable jTableTemporada; // Declarar la tabla como variable de instancia

	
	//METODO PARA USAR LA LISTTEMPORADAS EN OTRA SECCIONES
	public ArrayList<Temporada> getListTemporadas() {
	    return listTemporadas;
	}
	public void setListTemporadas(ArrayList<Temporada> listTemporadas) {
	    if (listTemporadas == null) {
	        throw new IllegalArgumentException("La lista de temporadas no puede ser nula");
	    }
	    this.listTemporadas = listTemporadas;
	}
	
	// Método para buscar una temporada por ID
	public Temporada buscarTemporadaPorId(int temporadaId) {
	    for (Temporada temporada : listTemporadas) {
	        if (temporada.getIdTemporada() == temporadaId) {
	            return temporada; // Temporada encontrada
	        }
	    }
	    return null; // Si no se encuentra la temporada
	}

	
	// Método para buscar un equipo por ID en una temporada específica
	public Equipo buscarEquipoPorId(int temporadaId, int equipoId) {
	    for (Temporada temporada : listTemporadas) {
	        if (temporada.getIdTemporada() == temporadaId) {
	            for (Equipo equipo : temporada.getListEquipos()) { // Supongamos que tienes un método getListEquipos
	                if (equipo.getId() == equipoId) {
	                    return equipo; // Equipo encontrado
	                }
	            }
	        }
	    }
	    return null; // Si no se encuentra el equipo
	}
	
	// Método para buscar una jornada por ID en una temporada específica
	public Jornada buscarJornada(int temporadaId, int jornadaId) {
	    for (Temporada temporada : listTemporadas) {
	        if (temporada.getIdTemporada() == temporadaId) {
	            for (Jornada jornada : temporada.getListJornadas()) { // Supongamos que tienes getListJornadas
	                if (jornada.getid_jornada() == jornadaId) {
	                    return jornada; // Jornada encontrada
	                }
	            }
	        }
	    }
	    return null; // Si no se encuentra la jornada
	}

	// Método para buscar un partido por ID en una jornada específica
	public Partido buscarPartido(int temporadaId, int jornadaId, int partidoId) {
	    Jornada jornada = buscarJornada(temporadaId, jornadaId);
	    if (jornada != null) {
	        for (Partido partido : jornada.getListPartidos()) { // Supongamos que tienes getListPartidos
	            if (partido.getId() == partidoId) {
	                return partido; // Partido encontrado
	            }
	        }
	    }
	    return null; // Si no se encuentra el partido
	}

	
	
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
	    lblNombre.setBounds(10, 48, 80, 14);
	    frame.getContentPane().add(lblNombre);

	    txtNombre = new JTextField();
	    txtNombre.setBounds(65, 46, 186, 20);
	    frame.getContentPane().add(txtNombre);
	    txtNombre.setColumns(10);

	    JLabel lblCantidadEquipos = new JLabel("Cantidad de Equipos:");
	    lblCantidadEquipos.setBounds(10, 76, 150, 14);
	    frame.getContentPane().add(lblCantidadEquipos);

	    txtCantidadEquipos = new JTextField();
	    txtCantidadEquipos.setBounds(168, 74, 83, 20);
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
	    JButton btnNuevoBoton = new JButton("Guardar");
	    
	    btnNuevoBoton.setBounds(490, 135, 90, 23); // Ajusta la posición y tamaño
	    btnNuevoBoton.addActionListener(e -> actualizarArchivo());
	    frame.getContentPane().add(btnNuevoBoton);

	    // Crear el TableModel con el ArrayList
	    TemporadaTableModel tableModel = new TemporadaTableModel(listTemporadas);

	    // Crear el JTable con el TableModel
	    jTableTemporada = new JTable(tableModel);

	    // Opcional: Establecer renderizadores para las columnas booleanas
	    jTableTemporada.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
	        @Override
	        public void setValue(Object value) {
	            setText((Boolean) value ? "Sí" : "No");
	        }
	    });

	    jTableTemporada.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
	        @Override
	        public void setValue(Object value) {
	            setText((Boolean) value ? "Sí" : "No");
	        }
	    });

	    // Crear un JScrollPane para hacer scroll en el JTable
	    JScrollPane scrollPane = new JScrollPane(jTableTemporada);
	    scrollPane.setBounds(10, 180, 570, 170);
	    frame.getContentPane().add(scrollPane);

	    // Nuevo botón para guardar datos
	    JButton btnGuardarDatos = new JButton("Guardar Datos");
	    btnGuardarDatos.setBounds(10, 360, 150, 23);
	    btnGuardarDatos.addActionListener(e -> actualizarArchivo());
	    frame.getContentPane().add(btnGuardarDatos);
	    
	    
	    cargarTemporadasDesdeArchivo();
	  
	}

	
	public class TemporadaTableModel extends AbstractTableModel {
	    private ArrayList<Temporada> listaTemporadas;  // Tu ArrayList de Temporada
	    private String[] columnNames = { "ID", "Nombre", "Iniciado", "Finalizado",
	    		"Nº Equipos", "Nº Jornadas" ,"Nº Partidos"};
	    // Constructor que recibe el ArrayList de Temporadas
	    public TemporadaTableModel(ArrayList<Temporada> listaTemporadas) {
	        this.listaTemporadas = listaTemporadas;
	    }

	    @Override
	    public int getRowCount() {
	        return listaTemporadas.size();  // Número de filas es el tamaño del ArrayList
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNames.length;  // Número de columnas (en este caso 6)
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Temporada temporada = listaTemporadas.get(rowIndex);

	        switch (columnIndex) {
	            case 0: return temporada.getIdTemporada();  // ID
	            case 1: return temporada.getNombre();  // Nombre
	            case 2: return temporada.isIniciado();  // Iniciado
	            case 3: return temporada.isFinalizado();  // Finalizado
	            case 4: return temporada.getCantidadEquipos();  // Cantidad Equipos
	            case 5: return temporada.getCantidadJornadas();  // Cantidad Jornadas
	            case 6: { // Cantidad Partidos
	            	if (temporada.getListJornadas() == null) return 0;
	                int totalPartidos = 0;
	                for (Jornada jornada : temporada.getListJornadas()) {
	                    if (jornada.getListPartidos() != null) {
	                        totalPartidos += jornada.getListPartidos().size();
	                    }
	                }

	                return totalPartidos;
	            }
	            default: return null;
	        }
	    }

	    @Override
	    public String getColumnName(int column) {
	        return columnNames[column];  // Nombres de las columnas
	    }

	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false;  // Las celdas no son editables
	    }
	}
	

	private void crearTemporada() {
	    try {
	        // Generar un ID único para la temporada
	        int id = listTemporadas.size(); // Puedes usar el tamaño de la lista para generar IDs consecutivos

	        // Obtener los valores desde los inputs
	        String nombre = txtNombre.getText();
	        int cantidadEquipos = Integer.parseInt(txtCantidadEquipos.getText());

		    // Crear una nueva instancia de Temporada
		    Temporada nueva = new Temporada(id, nombre, cantidadEquipos);
	        
	        //CREAR ALGORITMO JORNADAS Y MUESTRA POR CONSOLA
	        GeneradorTemporada generador = new GeneradorTemporada(); // Pasar la instancia actual de GestionTemporadav2

	        generador.GenerarTemporada(nueva);
	        
	        // Agregar la nueva temporada a la lista
	        listTemporadas.add(nueva);

	        
	        // Notificar al TableModel del cambio
	        ((TemporadaTableModel) jTableTemporada.getModel()).fireTableRowsInserted(listTemporadas.size() - 1, listTemporadas.size() - 1);

	        // Mostrar mensaje de éxito
	        JOptionPane.showMessageDialog(frame, "Temporada creada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        main.changes=true;
	        actualizarArchivo();
	    } catch (NumberFormatException e) {
	        // Manejar errores de conversión
	        JOptionPane.showMessageDialog(frame, "Por favor, introduce valores válidos.", "Error", JOptionPane.ERROR_MESSAGE);
	    }
	}

	private void iniciarTemporada() {
	    boolean error = false;

	    // Obtener el índice de la fila seleccionada en el JTable
	    int seleccion = jTableTemporada.getSelectedRow();

	    if (seleccion == -1) {
	        // Si no hay ninguna fila seleccionada, mostrar mensaje de error
	        JOptionPane.showMessageDialog(frame, "Error, no hay ninguna temporada seleccionada", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Obtener el modelo de la tabla
	    TemporadaTableModel modeloTabla = (TemporadaTableModel) jTableTemporada.getModel();

	    // Obtener el ID de la temporada seleccionada desde la tabla
	    int idTemporada = (int) modeloTabla.getValueAt(seleccion, 0); // Supongamos que el ID está en la columna 0

	    // Recorrer todas las filas de la tabla para verificar si ya hay una temporada iniciada
	    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	        boolean isIniciado = (boolean) modeloTabla.getValueAt(i, 2); // Supongamos que "iniciado" está en la columna 2
	        boolean isFinalizado = (boolean) modeloTabla.getValueAt(i, 3); // Supongamos que "finalizado" está en la columna 3

	        if (isIniciado && !isFinalizado) {
	            JOptionPane.showMessageDialog(frame, "Error, una temporada ya está iniciada", "Error",
	                    JOptionPane.ERROR_MESSAGE);
	            error = true;
	            break;
	        }
	    }

	    // Si no hay error, iniciar la temporada seleccionada
	    if (!error) {
	        // Actualizar el estado de la temporada en el modelo
	        Temporada temporada = listTemporadas.get(seleccion);
	        temporada.setIniciado(true);
	        main.changes=true;
	        // Notificar al modelo del cambio
	        modeloTabla.fireTableRowsUpdated(seleccion, seleccion);

	        // Mostrar mensaje de éxito
	        JOptionPane.showMessageDialog(frame, "Temporada iniciada correctamente.", "Éxito",
	                JOptionPane.INFORMATION_MESSAGE);

	        // Imprimir el ID de la temporada iniciada (puedes usarlo según sea necesario)
	        System.out.println("Temporada iniciada. ID: " + idTemporada);

	        // Aquí puedes llamar a otro método con el ID, si es necesario
	        // GenerarAlgoritmo(idTemporada);
	    }
	}



	private void finalizarTemporada() {
	    // Obtener el índice de la fila seleccionada en el JTable
	    int seleccion = jTableTemporada.getSelectedRow();

	    if (seleccion == -1) {
	        // Si no hay ninguna fila seleccionada, mostrar mensaje de error
	        JOptionPane.showMessageDialog(frame, "Error, no hay ninguna temporada seleccionada", "Error",
	                JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    // Obtener la temporada seleccionada de la lista
	    Temporada temporadaSeleccionada = listTemporadas.get(seleccion);

	    if (!temporadaSeleccionada.isIniciado()) {
	        JOptionPane.showMessageDialog(frame, "Error, la temporada no está iniciada", "Error",
	                JOptionPane.ERROR_MESSAGE);
	    } else if (temporadaSeleccionada.isFinalizado()) {
	        JOptionPane.showMessageDialog(frame, "Error, la temporada ya está finalizada", "Error",
	                JOptionPane.ERROR_MESSAGE);
	    } else {
	        // Marcar la temporada como finalizada
	        temporadaSeleccionada.setFinalizado(true);

	        // Notificar al TableModel que los datos han cambiado
	        ((TemporadaTableModel) jTableTemporada.getModel()).fireTableRowsUpdated(seleccion, seleccion);

	        // Mostrar mensaje de éxito
	        JOptionPane.showMessageDialog(frame, "Temporada finalizada correctamente.", "Éxito",
	                JOptionPane.INFORMATION_MESSAGE);
	        main.changes=true;
	    }
	}
	private void actualizarArchivo() {
    	if (main.changes== true) {
    		try (FileOutputStream fos = new FileOutputStream("Temporada.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos)){
        		int length =listTemporadas.size();
        		int counter = 0;
        		while (counter <length) {
        			oos.writeObject(listTemporadas.get(counter));
        			counter ++;
        		}
        		JOptionPane.showMessageDialog(null, "cambios guardados.");
        		main.changes=false;
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}}
    //metodo para cargar las temporadas
    private void cargarTemporadasDesdeArchivo() {
    	try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Temporada.ser"))) {
            while (true) {
                try {
                    Temporada temp = (Temporada) ois.readObject();
                    listTemporadas.add(temp);
                } catch (EOFException ex) {
                    break; // Fin del archivo
                }
            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de usuarios. Se creará uno nuevo al guardar cambios.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar usuarios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
   
    

}
