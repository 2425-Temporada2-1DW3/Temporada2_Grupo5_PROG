package com.structure;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
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

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import com.logic.Equipo;
import com.logic.GeneradorTemporada;
import com.logic.Jornada;
import com.logic.Partido;
import com.logic.Temporada;
import java.awt.BorderLayout;

public class PanelTemporadas extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
 	private main parentFrame;

	
	private ArrayList <Temporada> listTemporadas = new ArrayList<Temporada>();
	private DefaultListModel<Temporada> dlm = new DefaultListModel<>();
	private JButton btnCrearTemporada = 	new JButton("Crear Temporada");
    private JButton btnAnadirEquipo = 		new JButton("Añadir Equipo");
//    private JButton btnGestionEquipos = 	new JButton("Gest. Equipos");
    private JButton btnIniciarTemporada = 	new JButton("Iniciar Temporada");
    private JButton btnFinalizarTemporada = new JButton("Fin Temporada");
    private JButton btnEliminarTemporada = new JButton("Eliminar Temporada");

    private JTable tableTemporadas; // Declarar la tabla como variable de instancia
	private JTextField txtNombre,txtCantidadEquipos;
    private JScrollPane scrollPane;
    private JPanel topPanel,formPanel,buttonPanel;
    private JLabel lblTitle,lblNombre,lblCantidadEquipos;

	/**
	 * Create the panel.
	 */
    public PanelTemporadas(main parentFrame) {
        this.parentFrame = parentFrame;
        
        // Set up the main panel using BorderLayout
        this.setLayout(new BorderLayout());


        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout()); 

        // Label para titulo de temporadas
        lblTitle = new JLabel("TEMPORADAS", JLabel.CENTER);
        topPanel.add(lblTitle, BorderLayout.NORTH);  

        // Panel para el formulario de datos (Nombre,Cantidad de equipis)
        formPanel = new JPanel();
        formPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
 
        // DE MOMENTO LO OCULTO PARA EVITAR CONFLICTOS EN LOS NOMBRES DE LAS TEMPORADAS
//        lblNombre = new JLabel("Nombre:");
//        formPanel.add(lblNombre);
//        txtNombre = new JTextField(20);
//        formPanel.add(txtNombre);

        lblCantidadEquipos = new JLabel("Cantidad de Equipos:");
        formPanel.add(lblCantidadEquipos);

        txtCantidadEquipos = new JTextField(10);
        formPanel.add(txtCantidadEquipos);

        topPanel.add(formPanel, BorderLayout.CENTER);
        this.add(topPanel, BorderLayout.NORTH);

        // Panel con botones
         
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
 
        parentFrame.buttonCreate(btnCrearTemporada, 	buttonPanel, parentFrame.colorGreen);
        if(parentFrame.userType == 4) {
            parentFrame.buttonCreate(btnEliminarTemporada, buttonPanel, parentFrame.colorRed);
        }
//        parentFrame.buttonCreate(btnAnadirEquipo, 		buttonPanel, parentFrame.colorBlue);
//        parentFrame.buttonCreate(btnGestionEquipos, 	buttonPanel, parentFrame.colorBlue);
        parentFrame.buttonCreate(btnIniciarTemporada, 	buttonPanel, parentFrame.colorYellow);
        parentFrame.buttonCreate(btnFinalizarTemporada, buttonPanel, parentFrame.colorRed);

        // cambio el actionlistener de los botones a el de esta clase
 
        btnCrearTemporada.addActionListener(this);
        btnEliminarTemporada.addActionListener(this);
        btnAnadirEquipo.addActionListener(this);
//        btnGestionEquipos.addActionListener(this);
        btnIniciarTemporada.addActionListener(this);
        btnFinalizarTemporada.addActionListener(this);

        this.add(buttonPanel, BorderLayout.SOUTH);

        // Tabla con scrollpane para cargar las temporadas
        TemporadaTableModel tableModel = new TemporadaTableModel(listTemporadas);
        tableTemporadas = new JTable(tableModel);
        tableTemporadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        parentFrame.formatearTabla(tableTemporadas);

        scrollPane = new JScrollPane(tableTemporadas);
        parentFrame.formatearScrollPane(scrollPane);

        this.add(scrollPane, BorderLayout.CENTER);

        // Cargar datos
        cargarTemporadasDesdeArchivo();
    }



	public class TemporadaTableModel extends AbstractTableModel {
		private static final long serialVersionUID = -7057022157753394655L;
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
	        // Buscar el ID más alto existente en la lista, Garantizando Unicidad
	        int maxId = -1;
	        for (Temporada t : listTemporadas) {
	            if (t.getIdTemporada() > maxId) {
	                maxId = t.getIdTemporada();
	            }
	        }

	        // El nuevo ID será siempre mayor al más alto
	        int id = maxId + 1;

	        // Generar un nombre único basado en el ID
	        String nombre = "Temporada" + (id+1);

	        // Validar la cantidad de equipos
	        int cantidadEquipos = Integer.parseInt(txtCantidadEquipos.getText());
	        if (cantidadEquipos < 6 || cantidadEquipos % 2 != 0) {
	            parentFrame.mensaje("❌ Error: La cantidad de equipos debe ser un número par y mayor o igual a 6.", 0);
	            return;
	        }

	        // Crear la nueva instancia de Temporada
	        Temporada nueva = new Temporada(id, nombre, cantidadEquipos);

	        // Generar Jornadas y Equipos
	        GeneradorTemporada generador = new GeneradorTemporada();
	        generador.GenerarTemporada(nueva);
	        generador.MostrarVentanaEquipos(nueva);

	        // Agregar los equipos a la clasificación
	        ArrayList<Equipo> nombres = nueva.getListEquipos();
	        if (nueva.getClasificacion() == null) {
	            nueva.setClasificacion(new ArrayList<>());
	        }
	        nueva.getClasificacion().addAll(nombres);

	        // Agregar la temporada a la lista
	        listTemporadas.add(nueva);

	        // Actualizar la tabla
	        ((TemporadaTableModel) tableTemporadas.getModel()).fireTableRowsInserted(listTemporadas.size() - 1, listTemporadas.size() - 1);

	        // Mostrar mensaje de éxito
	        parentFrame.mensaje("✅ Temporada creada exitosamente: " + nombre, 2);
	        parentFrame.changes = true;

	        // Guardar cambios en el archivo
	        actualizarArchivo();
	    } catch (NumberFormatException e) {
	        parentFrame.mensaje("❌ Error: Introduce un número válido para la cantidad de equipos.", 0);
	    }
	}

	private void modificarTemporada() {
	    try {
	        // Obtener la fila seleccionada en la tabla
	        int rowIndex = tableTemporadas.getSelectedRow();
	        if (rowIndex == -1) {
	            parentFrame.mensaje("⚠️ Debe seleccionar una temporada para modificar.", 0);
	            return;
	        }

	        // Obtener la temporada seleccionada
	        Temporada temporadaSeleccionada = listTemporadas.get(rowIndex);

	        // Obtener y validar la nueva cantidad de equipos
	        int nuevaCantidadEquipos = Integer.parseInt(txtCantidadEquipos.getText());
	        if (nuevaCantidadEquipos < 6 || nuevaCantidadEquipos % 2 != 0) {
	            parentFrame.mensaje("❌ Error: La cantidad de equipos debe ser un número par y mayor o igual a 6.", 0);
	            return;
	        }

	        // Confirmar la modificación con el usuario
	        int confirmacion = JOptionPane.showConfirmDialog(
	            this,
	            "¿Está seguro de modificar la cantidad de equipos en la temporada '" + temporadaSeleccionada.getNombre() + "'?\n" +
	            "Se eliminarán todas las jornadas, partidos y equipos anteriores.",
	            "Confirmar Modificación",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.WARNING_MESSAGE
	        );

	        if (confirmacion != JOptionPane.YES_OPTION) {
	            return; // Si el usuario cancela, no hacer cambios
	        }

	        // Vaciar las listas anteriores
	        temporadaSeleccionada.setCantidadEquipos(nuevaCantidadEquipos);
	        temporadaSeleccionada.setListEquipos(new ArrayList<>()); // Vaciar lista de equipos
	        temporadaSeleccionada.setListJornadas(new ArrayList<>()); // Vaciar lista de jornadas
	        for (Jornada jornada : temporadaSeleccionada.getListJornadas()) {
	            jornada.setListPartidos(new ArrayList<>()); // Vaciar partidos de cada jornada
	        }

	        // Regenerar los equipos, jornadas y partidos con la nueva cantidad de equipos
	        GeneradorTemporada generador = new GeneradorTemporada();
	        generador.GenerarTemporada(temporadaSeleccionada);
	        generador.MostrarVentanaEquipos(temporadaSeleccionada);

	        // Actualizar la tabla de temporadas
	        ((TemporadaTableModel) tableTemporadas.getModel()).fireTableDataChanged();

	        // Guardar los cambios en el archivo
	        actualizarArchivo();

	        // Mostrar mensaje de éxito
	        parentFrame.mensaje("✅ Temporada modificada correctamente.", 2);
	    } catch (NumberFormatException e) {
	        parentFrame.mensaje("❌ Error: Introduce un número válido para la cantidad de equipos.", 0);
	    }
	}



	private void iniciarTemporada() {
	    boolean error = false;

	    // Obtener el índice de la fila seleccionada en el JTable
	    int seleccion = tableTemporadas.getSelectedRow();

	    if (seleccion == -1) {
	        // Si no hay ninguna fila seleccionada, mostrar mensaje de error
			parentFrame.mensaje("No hay ninguna temporada seleccionada",0);

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
				parentFrame.mensaje("Una temporada ya está iniciada",0);

	            error = true;
	            break;
	        }
	    }

	    // Si no hay error, iniciar la temporada seleccionada
	    if (!error) {
	        // Actualizar el estado de la temporada en el modelo
	        Temporada temporada = listTemporadas.get(seleccion);
	        temporada.setIniciado(true);
	        parentFrame.changes = true;
	        // Notificar al modelo del cambio
	        modeloTabla.fireTableRowsUpdated(seleccion, seleccion);

	        // Mostrar mensaje de éxito
	        
			parentFrame.mensaje("Temporada iniciada correctamente",2);


	        // Imprimir el ID de la temporada iniciada (puedes usarlo según sea necesario)
	        System.out.println("Temporada iniciada. ID: " + idTemporada);

	        // Aquí puedes llamar a otro método con el ID, si es necesario
	        // GenerarAlgoritmo(idTemporada);
	    }
	    actualizarArchivo();
	}

	private void finalizarTemporada() {
	    // Obtener el índice de la fila seleccionada en el JTable
	    int seleccion = tableTemporadas.getSelectedRow();

	    if (seleccion == -1) {
	        // Si no hay ninguna fila seleccionada, mostrar mensaje de error
			parentFrame.mensaje("No hay ninguna temporada seleccionada",0);


	        return;
	    }

	    // Obtener la temporada seleccionada de la lista
	    Temporada temporadaSeleccionada = listTemporadas.get(seleccion);

	    if (!temporadaSeleccionada.isIniciado()) {
	    	parentFrame.mensaje("La temporada no está iniciada",0);

	    } else if (temporadaSeleccionada.isFinalizado()) {
	    	parentFrame.mensaje("La temporada ya está finalizada",0);

	    } else {
	        // Marcar la temporada como finalizada
	        temporadaSeleccionada.setFinalizado(true);

	        // Notificar al TableModel que los datos han cambiado
	        ((TemporadaTableModel) tableTemporadas.getModel()).fireTableRowsUpdated(seleccion, seleccion);

	        // Mostrar mensaje de éxito
	        parentFrame.mensaje("Temporada finalizada correctamente",2);
	        parentFrame.changes = true;
	    }
	    actualizarArchivo();
	}
	
	private void eliminarTemporada() {
	    // Get the selected row index
	    int selectedRow = tableTemporadas.getSelectedRow();

	    if (selectedRow == -1) {
	        parentFrame.mensaje("No hay ninguna temporada seleccionada", 0);
	        return;
	    }

	    // Confirm deletion with the user
 
        listTemporadas.remove(selectedRow);

        // Notify the table model about the removed row
        ((TemporadaTableModel) tableTemporadas.getModel()).fireTableRowsDeleted(selectedRow, selectedRow);

        // Update the persistent file
        actualizarArchivo();

        // Show success message
        parentFrame.mensaje("Temporada eliminada", 1);
        parentFrame.changes = true;
  
	}
	
	private void anadirEquipo() {
	    // Verificar si hay una temporada seleccionada
	    int seleccion = tableTemporadas.getSelectedRow();

	    if (seleccion == -1) {
	        parentFrame.mensaje("Selecciona una temporada antes de añadir un equipo", 1);
	        return;
	    }

	    // Obtener la temporada seleccionada
	    Temporada temporadaSeleccionada = listTemporadas.get(seleccion);
	    GeneradorTemporada ObtenerMetodo = new GeneradorTemporada(); // Pasar la instancia actual de GestionTemporadav2

	    // Llamar al método MostrarVentanaEquipos para mostrar la ventana de ingreso de equipos
	    ObtenerMetodo.MostrarVentanaEquipos(temporadaSeleccionada);

	    // Actualizar archivo después de añadir los equipos
	    actualizarArchivo();
	}

	
	private void actualizarArchivo() {
    	if (parentFrame.changes== true) {
//    		try (FileOutputStream fos = new FileOutputStream("Temporada.ser");
    		try (FileOutputStream fos = new FileOutputStream(parentFrame.temporadasFile);

    				ObjectOutputStream oos = new ObjectOutputStream(fos)){
        		int length =listTemporadas.size();
        		int counter = 0;
        		while (counter <length) {
        			oos.writeObject(listTemporadas.get(counter));
        			counter ++;
        		}
        		parentFrame.mensaje("Cambios guardados",2);
        		parentFrame.changes = false;
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
	}
    
	//metodo para cargar las temporadas
    private void cargarTemporadasDesdeArchivo() {
//    	try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Temporada.ser"))) {
            
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(parentFrame.temporadasFile))) {
			while (true) {
                try {
                    Temporada temp = (Temporada) ois.readObject();
                    listTemporadas.add(temp);
                } catch (EOFException ex) {
                    break; // Fin del archivo
                }
            }
        } catch (FileNotFoundException ex) {
        	parentFrame.mensaje("No se encontró el archivo de usuarios. Se creará uno nuevo al guardar cambios",1);
        } catch (IOException | ClassNotFoundException ex) {
        	parentFrame.mensaje("Error al cargar usuarios: " + ex.getMessage(),0);
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
//            	anadirEquipo();
//            	modificarTemporada();
            } else if (o == btnIniciarTemporada) {
            	iniciarTemporada();
            } else if (o == btnFinalizarTemporada) {
            	finalizarTemporada();
            } else if (o == btnEliminarTemporada) {
            	eliminarTemporada();
            }
	}
}