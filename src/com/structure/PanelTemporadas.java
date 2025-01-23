package com.structure;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.logic.Equipo;
import com.logic.GeneradorTemporada;
import com.logic.Jornada;
import com.logic.Partido;
import com.logic.Temporada;
import com.structure.PanelTemporadas.TemporadaTableModel;
import java.awt.BorderLayout;

public class PanelTemporadas extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	private main parentFrame;

	private JTextField txtNombre;
	private JTextField txtCantidadEquipos;
	private JList<Temporada> jListTemporada;
	private ArrayList <Temporada> listTemporadas = new ArrayList<Temporada>();
	private DefaultListModel<Temporada> dlm = new DefaultListModel<>();
	private DefaultTableModel tableModel;
    private JTable tableTemporadas; // Declarar la tabla como variable de instancia
    private GridBagConstraints gbc = new GridBagConstraints();
    
    
    
    private JButton btnCrearTemporada = new JButton("Crear Temporada");
    private JButton btnAnadirEquipo = new JButton("Añadir Equipo");
    private JButton btnIniciarTemporada = new JButton("Iniciar Temporada");
    private JButton btnFinalizarTemporada = new JButton("Finalizar Temporada");



	


	/**
	 * Create the panel.
	 */
	public PanelTemporadas(Color colorbg, Color colortxt, int userType, String userName, main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.userType = userType;
		this.colorbg = colorbg;
		this.colortxt = colortxt;
		this.userName = userName;
	    this.parentFrame = parentFrame;


        // Configuración del panel principal
        this.setLayout(new GridBagLayout());
        this.setBackground(colorbg);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaciado entre componentes

        // Etiqueta Nombre
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(colortxt);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        this.add(lblNombre, gbc);

        // Campo de texto para el nombre
        txtNombre = new JTextField(20);
        txtNombre.setBackground(colorbg);
        txtNombre.setForeground(colortxt);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        this.add(txtNombre, gbc);

        // Etiqueta Cantidad de Equipos
        JLabel lblCantidadEquipos = new JLabel("Cantidad de Equipos:");
        lblCantidadEquipos.setForeground(colortxt);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(lblCantidadEquipos, gbc);

        // Campo de texto para la cantidad de equipos
        txtCantidadEquipos = new JTextField(10);
        txtCantidadEquipos.setBackground(colorbg);
        txtCantidadEquipos.setForeground(colortxt);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        this.add(txtCantidadEquipos, gbc);



        // Botón Crear Temporada
        buttonFormat(btnCrearTemporada);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        this.add(btnCrearTemporada, gbc);

        // Botón Añadir Equipo
        buttonFormat(btnAnadirEquipo);
        gbc.gridx = 1;
        gbc.gridy = 2;
        this.add(btnAnadirEquipo, gbc);

        // Botón Iniciar Temporada
        buttonFormat(btnIniciarTemporada);
        gbc.gridx = 2;
        gbc.gridy = 2;
        this.add(btnIniciarTemporada, gbc);

        // Botón Finalizar Temporada
        buttonFormat(btnFinalizarTemporada);
        gbc.gridx = 3;
        gbc.gridy = 2;
        this.add(btnFinalizarTemporada, gbc);

        // Tabla de Temporadas con ScrollPane
        TemporadaTableModel tableModel = new TemporadaTableModel(listTemporadas);
        tableTemporadas = new JTable(tableModel);

        tableTemporadas.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setText((Boolean) value ? "Sí" : "No");
            }
        });

        tableTemporadas.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public void setValue(Object value) {
                setText((Boolean) value ? "Sí" : "No");
            }
        });
        
        parentFrame.formatearTabla(tableTemporadas);
        
        JScrollPane scrollPane = new JScrollPane(tableTemporadas);
        scrollPane.getViewport().setBackground(colorbg);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        this.add(scrollPane, gbc);

        // Cargar datos desde archivo
        cargarTemporadasDesdeArchivo();
    }

    public void buttonFormat(JButton button) { 
	    button.setFont(new Font("SansSerif", Font.BOLD, 16));
	    button.addActionListener(this);
	    button.setForeground(colortxt);
	    button.setBackground(colorbg);
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
	        ((TemporadaTableModel) tableTemporadas.getModel()).fireTableRowsInserted(listTemporadas.size() - 1, listTemporadas.size() - 1);

	        // Mostrar mensaje de éxito
			parentFrame.mensaje("Temporada creada exitosamente.");
	        main.changes=true;
	    } catch (NumberFormatException e) {
	        // Manejar errores de conversión
			parentFrame.mensaje("Por favor, introduce valores válidos.");
	    }
	}

	private void iniciarTemporada() {
	    boolean error = false;

	    // Obtener el índice de la fila seleccionada en el JTable
	    int seleccion = tableTemporadas.getSelectedRow();

	    if (seleccion == -1) {
	        // Si no hay ninguna fila seleccionada, mostrar mensaje de error
			parentFrame.mensaje("Error, no hay ninguna temporada seleccionada");

	        return;
	    }

	    // Obtener el modelo de la tabla
	    TemporadaTableModel modeloTabla = (TemporadaTableModel) tableTemporadas.getModel();

	    // Obtener el ID de la temporada seleccionada desde la tabla
	    int idTemporada = (int) modeloTabla.getValueAt(seleccion, 0); // Supongamos que el ID está en la columna 0

	    // Recorrer todas las filas de la tabla para verificar si ya hay una temporada iniciada
	    for (int i = 0; i < modeloTabla.getRowCount(); i++) {
	        boolean isIniciado = (boolean) modeloTabla.getValueAt(i, 2); // Supongamos que "iniciado" está en la columna 2
	        boolean isFinalizado = (boolean) modeloTabla.getValueAt(i, 3); // Supongamos que "finalizado" está en la columna 3

	        if (isIniciado && !isFinalizado) {
				parentFrame.mensaje("Error, una temporada ya está iniciada");

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
	        
			parentFrame.mensaje("Temporada iniciada correctamente.");


	        // Imprimir el ID de la temporada iniciada (puedes usarlo según sea necesario)
	        System.out.println("Temporada iniciada. ID: " + idTemporada);

	        // Aquí puedes llamar a otro método con el ID, si es necesario
	        // GenerarAlgoritmo(idTemporada);
	    }
	}

	private void finalizarTemporada() {
	    // Obtener el índice de la fila seleccionada en el JTable
	    int seleccion = tableTemporadas.getSelectedRow();

	    if (seleccion == -1) {
	        // Si no hay ninguna fila seleccionada, mostrar mensaje de error
			parentFrame.mensaje("Error, no hay ninguna temporada seleccionada");


	        return;
	    }

	    // Obtener la temporada seleccionada de la lista
	    Temporada temporadaSeleccionada = listTemporadas.get(seleccion);

	    if (!temporadaSeleccionada.isIniciado()) {
	    	parentFrame.mensaje("Error, la temporada no está iniciada");

	    } else if (temporadaSeleccionada.isFinalizado()) {
	    	parentFrame.mensaje("Error, la temporada ya está finalizada");

	    } else {
	        // Marcar la temporada como finalizada
	        temporadaSeleccionada.setFinalizado(true);

	        // Notificar al TableModel que los datos han cambiado
	        ((TemporadaTableModel) tableTemporadas.getModel()).fireTableRowsUpdated(seleccion, seleccion);

	        // Mostrar mensaje de éxito
	        parentFrame.mensaje("Temporada finalizada correctamente.");
	        main.changes=true;
	    }
	}
	
	private void anadirEquipo() {
	    // Verificar si hay una temporada seleccionada
	    int seleccion = tableTemporadas.getSelectedRow();

	    if (seleccion == -1) {
	    	parentFrame.mensaje("Selecciona una temporada antes de añadir un equipo.");
	        return;
	    }

	    // Crear cuadro de diálogo para ingresar datos del equipo
	    JTextField txtIdEquipo = new JTextField();
	    JTextField txtNombreEquipo = new JTextField();

    	parentFrame.formatearPanelDeOpcion();

	    Object[] inputs = {
	        "ID del Equipo:", txtIdEquipo,
	        "Nombre del Equipo:", txtNombreEquipo
	    };
	    txtIdEquipo.setBackground(colorbg);
	    txtIdEquipo.setForeground(colortxt);
	    txtNombreEquipo.setBackground(colorbg);
	    txtNombreEquipo.setForeground(colortxt);

	    int opcion = JOptionPane.showConfirmDialog(this, inputs, "Añadir Equipo", JOptionPane.OK_CANCEL_OPTION);

	    if (opcion == JOptionPane.OK_OPTION) {
	        try {

	            int idEquipo = Integer.parseInt(txtIdEquipo.getText());
	            String nombreEquipo = txtNombreEquipo.getText();

	            // Verificar datos válidos
	            if (nombreEquipo.isEmpty()) {
	                throw new IllegalArgumentException("El nombre del equipo no puede estar vacío.");
	            }

	            // Agregar equipo a la temporada seleccionada
	            Temporada temporadaSeleccionada = listTemporadas.get(seleccion);
	            Equipo nuevoEquipo = new Equipo(idEquipo, nombreEquipo);
	            temporadaSeleccionada.getListEquipos().add(nuevoEquipo);

	            parentFrame.mensaje("Equipo añadido exitosamente.");

	            // Actualizar la tabla
	            ((TemporadaTableModel) tableTemporadas.getModel()).fireTableRowsUpdated(seleccion, seleccion);
	        } catch (NumberFormatException e) {
	        	parentFrame.mensaje("El ID del equipo debe ser un número válido.");
	        } catch (IllegalArgumentException e) {
	        	parentFrame.mensaje(e.getMessage());
	        }
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
        		parentFrame.mensaje("Cambios guardados.");
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
        	parentFrame.mensaje("No se encontró el archivo de usuarios. Se creará uno nuevo al guardar cambios.");
        } catch (IOException | ClassNotFoundException ex) {
        	parentFrame.mensaje("Error al cargar usuarios: " + ex.getMessage());
        }
    }
   
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

            if (o == btnCrearTemporada) {
            	crearTemporada();
            } else if (o == btnAnadirEquipo) {
            	anadirEquipo();
            } else if (o == btnIniciarTemporada) {
            	iniciarTemporada();
            } else if (o == btnFinalizarTemporada) {
            	finalizarTemporada();
            }
	}
}