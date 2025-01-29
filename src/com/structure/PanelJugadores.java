package com.structure;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.CardLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
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

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import com.logic.Jugador;
import com.logic.Temporada;
import com.logic.Equipo;
import com.logic.Fecha;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelJugadores extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private int userType;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	private main parentFrame;
	JPanel PanelContenedor;
	JPanel PanelDatosJugador;
	JPanel Cabecera;
	JLabel lblTituloDatosJugador;
	JPanel panel;
	JButton btnModificarJugador;
	JButton btnCrearJugador;
	JPanel PanelContenidoJugador;
	JPanel PanelListJugadores;
	JPanel Cabecera2;
	JLabel lblNewLabel;
	JPanel panel_1;
	JButton btnEliminarJugador;
	JButton btnEliminarTodos;
	JButton btnGuardarCambios;
	JTextField txtNumFicha;
	JLabel lblNroFicha;
	private JLabel lblNombreJug;
	private JTextField txtNombre;
	private JTextField txtNacionalidad;
	private JLabel lblNacionalidad;
	private JLabel lblFechaNacimiento;
	private JTextField txtFechaNacimiento;
	private JLabel lblAltura;
	private JTextField txtAltura;
	private JLabel lblPeso;
	private JTextField txtPeso;
	private JTextField txtNroDorsal;
	private JLabel lblNroDorsal;
	private JLabel lblEquipo;
	JComboBox<String> combxEquipo;
	private JButton btnCambiarFoto;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_4;
	private JComboBox<Temporada> combxFiltrarTempo;
	private JLabel lblNewLabel_10;
	private JComboBox<String> combFiltrarJugador;
	private JButton btnBuscarJugador;
	JLabel labelImagen;
	ImageIcon icon;
	
	
    private static int contadorNumerico = 1001; // Inicializa la parte numérica
    private static int subContadorNumerico = 1001; // Inicializa el subcontador
    private static char letraActual = 'A'; // Inicializa la parte de la letra

    private ArrayList<Jugador> JTablelistaJugadores;
    private ArrayList<Jugador> listJugadores;
    private ArrayList<Temporada> listTemporadas; // Lista de temporadas
    private ArrayList<Equipo> listEquipos; // Lista de equipos para combobox 
    private JLabel lblPosicion;
    private JComboBox<String> combxPosicion;
	/**
	 * Create the panel.
	 */
    
 // Método para crear una lista de jugadores de ejemplo
    private ArrayList<Jugador> crearListaJugadores() {
        ArrayList<Jugador> jugadores = new ArrayList<>();
//        jugadores.add(new Jugador("001", "Lionel Messi", 10, "Delantero", "Argentina", 170, 72, 24, 6, 1987));
//        jugadores.add(new Jugador("002", "Cristiano Ronaldo", 7, "Delantero", "Portugal", 187, 83, 5, 2, 1985));
//        jugadores.add(new Jugador("003", "Neymar Jr.", 11, "Delantero", "Brasil", 175, 68, 5, 2, 1992));
        // Agrega más jugadores según sea necesario
        return jugadores;
    }

	public PanelJugadores(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.parentFrame = parentFrame;
	    userType = parentFrame.userType;
	    colorbg = parentFrame.colorbg;
	    colortxt = parentFrame.colortxt;
	    userName = parentFrame.userName;
	    this.listTemporadas = new ArrayList<>();
	    this.listEquipos = new ArrayList<>();
	    cargarTemporadasDesdeArchivo();
	    
		// Cambia color del Jpanel
		setBackground(colorbg);
		setLayout(new BorderLayout(0, 0));
		
		// Crear la lista de jugadores
        listJugadores = crearListaJugadores();
        
        
		
		PanelContenedor = new JPanel();
		add(PanelContenedor);
		PanelContenedor.setLayout(new BoxLayout(PanelContenedor, BoxLayout.X_AXIS));
		
		PanelDatosJugador = new JPanel();
		PanelDatosJugador.setPreferredSize(new Dimension(100, 600)); // Tamaño deseado
		PanelContenedor.add(PanelDatosJugador);
		PanelDatosJugador.setLayout(new BorderLayout(0, 0));
		
		Cabecera = new JPanel();
		PanelDatosJugador.add(Cabecera, BorderLayout.NORTH);
		
		lblTituloDatosJugador = new JLabel("Añadir Jugador");
		Cabecera.add(lblTituloDatosJugador);
		
		panel = new JPanel();
		PanelDatosJugador.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {10, 113, 131, 0}; // Primera columna de 10px como margen izquierdo
		gbl_panel.rowHeights = new int[]{21, 21, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		// Botón "Modificar Jugador"
		btnModificarJugador = new JButton("Modificar Jugador");
		GridBagConstraints gbc_btnModificarJugador = new GridBagConstraints();
		gbc_btnModificarJugador.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnModificarJugador.insets = new Insets(0, 10, 5, 5); // Margen izquierdo de 10px
		gbc_btnModificarJugador.gridx = 1; // Cambiado a la columna 1 (después de la separación)
		gbc_btnModificarJugador.gridy = 0;
		panel.add(btnModificarJugador, gbc_btnModificarJugador);
		btnModificarJugador.addActionListener(this);

		// Botón "Añadir Jugador"
		btnCrearJugador = new JButton("Crear Jugador");
		GridBagConstraints gbc_btnCrearJugador = new GridBagConstraints();
		gbc_btnCrearJugador.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCrearJugador.insets = new Insets(0, 0, 5, 0); // Sin margen adicional
		gbc_btnCrearJugador.gridx = 2; // Cambiado a la columna 2
		gbc_btnCrearJugador.gridy = 0;
		panel.add(btnCrearJugador, gbc_btnCrearJugador);
		btnCrearJugador.addActionListener(this);

		// Botón "Cambiar Fotografía"
		btnCambiarFoto = new JButton("Cambiar Fotografía");
		GridBagConstraints gbc_btnCambiarFoto = new GridBagConstraints();
		gbc_btnCambiarFoto.anchor = GridBagConstraints.NORTH;
		gbc_btnCambiarFoto.gridwidth = 2; // El botón ocupa 2 columnas
		gbc_btnCambiarFoto.insets = new Insets(5, 10, 0, 0); // Margen superior de 5px y margen izquierdo de 10px
		gbc_btnCambiarFoto.gridx = 1; // Inicia en la columna 1
		gbc_btnCambiarFoto.gridy = 1;
		panel.add(btnCambiarFoto, gbc_btnCambiarFoto);

		
		PanelContenidoJugador = new JPanel();
		PanelContenidoJugador.setPreferredSize(new Dimension(700, 600)); // Tamaño deseado
		PanelDatosJugador.add(PanelContenidoJugador, BorderLayout.CENTER);
		PanelContenidoJugador.setLayout(new MigLayout("", "[130px][96px]", "[93px][19px][19px][19px][19px][19px][19px][19px][21px][21px]"));
		
		lblNroFicha = new JLabel("Nº FICHA:");
		lblNroFicha.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNroFicha, "cell 0 1,grow");
		
		txtNumFicha = new JTextField();
		txtNumFicha.setText("prueba");
		PanelContenidoJugador.add(txtNumFicha, "cell 1 1,alignx left,aligny top");
		txtNumFicha.setColumns(10);
		
		lblNombreJug = new JLabel("NOMBRE:");
		lblNombreJug.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNombreJug, "cell 0 2,grow");
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		PanelContenidoJugador.add(txtNombre, "cell 1 2,alignx left,aligny top");
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setColumns(10);
		PanelContenidoJugador.add(txtNacionalidad, "cell 1 3,alignx left,aligny top");
		
		lblNacionalidad = new JLabel("NACIONALIDAD:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNacionalidad, "cell 0 3,grow");
		
		lblFechaNacimiento = new JLabel("F. NACIMIENTO:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblFechaNacimiento, "cell 0 4,grow");
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		PanelContenidoJugador.add(txtFechaNacimiento, "cell 1 4,alignx left,aligny top");
		
		lblAltura = new JLabel("ALTURA (MT):");
		lblAltura.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblAltura, "cell 0 5,grow");
		
		txtAltura = new JTextField();
		txtAltura.setColumns(10);
		PanelContenidoJugador.add(txtAltura, "cell 1 5,alignx left,aligny top");
		
		lblPeso = new JLabel("PESO (KG):");
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblPeso, "cell 0 6,grow");
		
		txtPeso = new JTextField();
		txtPeso.setColumns(10);
		PanelContenidoJugador.add(txtPeso, "cell 1 6,alignx left,aligny top");
		
		txtNroDorsal = new JTextField();
		txtNroDorsal.setColumns(10);
		PanelContenidoJugador.add(txtNroDorsal, "cell 1 7,alignx left,aligny top");
		
		lblNroDorsal = new JLabel("Nº DORSAL:");
		lblNroDorsal.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNroDorsal, "cell 0 7,grow");
		
		lblEquipo = new JLabel("EQUIPO:");
		lblEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblEquipo, "cell 0 9,grow");
		
		combxEquipo = new JComboBox<>();
		combxEquipo.setEditable(true);
		PanelContenidoJugador.add(combxEquipo, "cell 1 9,growx,aligny top");
		
		icon = new ImageIcon("media.jugadores/idFotodefault.png");
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(79, 93, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);
		labelImagen = new JLabel();
		PanelContenidoJugador.add(labelImagen, "cell 1 0,grow");
		labelImagen.setIcon(icon);
		
		lblPosicion = new JLabel("POSICION:");
		lblPosicion.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblPosicion, "cell 0 8,grow");
		
		combxPosicion = new JComboBox<String>();
		DefaultComboBoxModel<String> modelPosicion = new DefaultComboBoxModel<>();
		modelPosicion.addElement("Armador");
		modelPosicion.addElement("Opuesto");
		modelPosicion.addElement("Receptor1");
		modelPosicion.addElement("Receptor2");
		modelPosicion.addElement("Central");
		modelPosicion.addElement("Libero");
		combxPosicion.setModel(modelPosicion);
		PanelContenidoJugador.add(combxPosicion, "cell 1 8,growx,aligny top");
		

		
		
		PanelListJugadores = new JPanel();
		PanelContenedor.add(PanelListJugadores);
		PanelListJugadores.setLayout(new BorderLayout(0, 0));
		
		Cabecera2 = new JPanel();
		PanelListJugadores.add(Cabecera2, BorderLayout.NORTH);
		
		lblNewLabel = new JLabel("Jugador");
		Cabecera2.add(lblNewLabel);
		
		panel_1 = new JPanel();
		PanelListJugadores.add(panel_1, BorderLayout.SOUTH);
		
		btnEliminarJugador = new JButton("Eliminar Jugador");
		panel_1.add(btnEliminarJugador);
		
		btnEliminarTodos = new JButton("Eliminar Todos");
		panel_1.add(btnEliminarTodos);
		
		btnGuardarCambios = new JButton("Guardar Cambios");
		panel_1.add(btnGuardarCambios);
		btnGuardarCambios.addActionListener(this);
		
		panel_2 = new JPanel();
		PanelListJugadores.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		// Crear el modelo de tabla para jugadores
		JugadorTableModel jugadorTableModel = new JugadorTableModel(listJugadores);

		// Crear la tabla y asignar el modelo
		table = new JTable(jugadorTableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección de una sola fila
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            ReflejarSeleccionJugador(); // Llama al método cuando se selecciona una fila
		        }
		    }
		});
		
		// Configurar el ancho de las columnas
		TableColumnModel columnModel = table.getColumnModel();

		// Establecer el ancho preferido de cada columna
		columnModel.getColumn(0).setPreferredWidth(100);  // "Nº Ficha"
		columnModel.getColumn(1).setPreferredWidth(150); // "Nombre"
		columnModel.getColumn(2).setPreferredWidth(50);  // "Edad"
		columnModel.getColumn(3).setPreferredWidth(125); // "Nacionalidad"
		columnModel.getColumn(4).setPreferredWidth(50);  // "Altura"
		columnModel.getColumn(5).setPreferredWidth(50);  // "Peso"
		columnModel.getColumn(6).setPreferredWidth(75); // "Posición"
		columnModel.getColumn(7).setPreferredWidth(100); // "Equipo"
		
		
		// Formatear la tabla si tienes un método para eso (opcional)
		parentFrame.formatearTabla(table); // Si no tienes este método, puedes omitir esta línea

		// Agregar la tabla al JScrollPane
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(colorbg);

		// Añadir el JScrollPane al panel deseado
		panel_3.add(scrollPane, BorderLayout.CENTER);

		
		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		combxFiltrarTempo = new JComboBox<>();
		combxFiltrarTempo.setPreferredSize(new Dimension(150, 25)); // Establece un ancho de 150px y alto de 25px
		panel_4.add(combxFiltrarTempo);
		actualizarComboBox(combxFiltrarTempo, listTemporadas);
		combxFiltrarTempo.addActionListener(e -> {
		    Object selectedItem = combxFiltrarTempo.getSelectedItem();
		    if (selectedItem != null) {
		        actualizarComboBoxEquipo(selectedItem.toString(), listTemporadas);
		        actualizarTabla();
		    }
		});
		
		lblNewLabel_10 = new JLabel("Filtrar:");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_4.add(lblNewLabel_10);
		
		combFiltrarJugador = new JComboBox<>();
		combFiltrarJugador.setPreferredSize(new Dimension(150, 25)); // Establece un ancho de 150px y alto de 25px
		panel_4.add(combFiltrarJugador);
		combFiltrarJugador.addActionListener(e -> {
		    Object selectedItem = combFiltrarJugador.getSelectedItem();
		    if (selectedItem != null) {
		        actualizarTabla();
		    }
		});
		
		
		btnBuscarJugador = new JButton("Buscar");
		panel_4.add(btnBuscarJugador);

		JComponent labelFormat[] = { lblTituloDatosJugador, lblNewLabel, lblNroFicha, lblNombreJug, lblNacionalidad, lblFechaNacimiento, lblAltura, lblPeso, lblNroDorsal, lblPosicion, lblEquipo, lblNewLabel_10 };
		JComponent panelFormat[] = { PanelContenedor, PanelDatosJugador, Cabecera, PanelContenidoJugador, PanelListJugadores, Cabecera2, panel, panel_1, panel_2, panel_3, panel_4 };

		// Loop through labelFormat and apply properties
		for (int i = 0; i < labelFormat.length; i++) {
		    labelFormat[i].setBackground(colorbg);
		    labelFormat[i].setForeground(colortxt);
		    labelFormat[i].setFont(parentFrame.fuenteDefecto);
		}

		// Loop through panelFormat and apply properties
		for (int i = 0; i < panelFormat.length; i++) {
		    panelFormat[i].setBackground(colorbg);
		}


	    CrearJugadoresPrueba();
		
		
	}
	
	
	
    // Clase interna para el modelo de la tabla de jugadores
    class JugadorTableModel extends AbstractTableModel {
    	ArrayList<Jugador>JTablelistaJugadores;
        private static final long serialVersionUID = 1L;
        private String[] columnNames = {
            "Nº Ficha", "Nombre", "Edad", "Nacionalidad",
            "Altura", "Peso","Dorsal", "Posición", "Equipo",
        };

        public JugadorTableModel(ArrayList<Jugador> listaJugadores) {
            if (listaJugadores == null) {
                this.JTablelistaJugadores = new ArrayList<>(); // Inicializa una lista vacía
            } else {
                this.JTablelistaJugadores = listaJugadores;
            }
        }
        @Override
        public int getRowCount() {
            return JTablelistaJugadores.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
        	if (rowIndex < 0 || rowIndex >= JTablelistaJugadores.size()) {
                return null; // O lanzar una excepción personalizada
            }
        	Jugador jugador = JTablelistaJugadores.get(rowIndex);
            switch (columnIndex) {
                case 0: return jugador.getNumFicha();
                case 1: return jugador.getNombre();
                case 2: return jugador.getEdad();
                case 3: return jugador.getNacionalidad();
                //case 4: return jugador.getFechaNac(); // Devuelve el objeto Fecha
                case 4: return jugador.getAltura();
                case 5: return jugador.getPeso();
                case 6: return jugador.getDorsal();
                case 7: return jugador.getPosicion();
                case 8: return jugador.getIdEquipo();
                default: return null;
            }
        }
        
        
     // Método para actualizar la lista de jugadores
        public void setListaJugadores(ArrayList<Jugador> nuevaLista) {
            this.JTablelistaJugadores = nuevaLista;
            fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
        }
    }
    private void ReflejarSeleccionJugador() {
        // Verificar que haya temporadas y equipos en la lista
        if (listTemporadas == null || listTemporadas.isEmpty()) {
            parentFrame.mensaje("No hay temporadas disponibles", 0);
            return;
        }
        
        // Obtener el índice de la temporada seleccionada y el equipo seleccionado
        int idTemporada = combxFiltrarTempo.getSelectedIndex();  // Índice de la temporada seleccionada
        int idEquipo = combFiltrarJugador.getSelectedIndex();  // Índice del equipo seleccionado
        
        // Verificar que los índices sean válidos
        if (idTemporada == -1 || idEquipo == -1 || idTemporada >= listTemporadas.size()) {
            parentFrame.mensaje("Debe seleccionar una temporada y un equipo válidos", 0);
            return;
        }

        // Obtener la temporada y el equipo seleccionados
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipo);

        // Verificar que el equipo seleccionado tiene jugadores
        if (equipoSeleccionado.getListJugadores() == null || equipoSeleccionado.getListJugadores().isEmpty()) {
            parentFrame.mensaje("No hay jugadores disponibles para el equipo seleccionado", 0);
            return;
        }

        // Obtener el índice de la fila seleccionada en la tabla
        int rowIndex = table.getSelectedRow();  // Obtiene la fila seleccionada de la tabla

        // Verificar si se ha seleccionado una fila válida
        if (rowIndex != -1) {
            try {
                // Obtener el jugador seleccionado de la lista de jugadores del equipo seleccionado
                Jugador jugadorSeleccionado = equipoSeleccionado.getListJugadores().get(rowIndex); 

                // Asignar los valores a los campos de texto
                txtNumFicha.setText(jugadorSeleccionado.getNumFicha());
                txtNombre.setText(jugadorSeleccionado.getNombre());
                txtNacionalidad.setText(jugadorSeleccionado.getNacionalidad());
                
                // Formatear la fecha de nacimiento
                Fecha fechaNac = jugadorSeleccionado.getFechaNac(); // Obtener la fecha de nacimiento
                if (fechaNac != null) {
                    String fechaFormateada = String.format("%02d/%02d/%02d", fechaNac.getDia(), fechaNac.getMes(), fechaNac.getAno() % 100);
                    txtFechaNacimiento.setText(fechaFormateada);
                } else {
                    txtFechaNacimiento.setText(""); // Si no hay fecha de nacimiento, limpiar el campo
                }
                
                txtAltura.setText(String.valueOf(jugadorSeleccionado.getAltura()));
                txtPeso.setText(String.valueOf(jugadorSeleccionado.getPeso()));
                txtNroDorsal.setText(String.valueOf(jugadorSeleccionado.getDorsal()));

                // Asignar equipo y posición al ComboBox
                combxEquipo.setSelectedIndex(jugadorSeleccionado.getIdEquipo()); // Suponiendo que el ID del equipo está en la posición correcta
                combxPosicion.setSelectedItem(jugadorSeleccionado.getPosicion());

            } catch (IndexOutOfBoundsException e) {
                parentFrame.mensaje("No se ha seleccionado un jugador válido", 0);
            }
        }
    }


    
    // Método para generar el siguiente número de ficha
    public static String generarNumeroFicha() {
        String numeroFicha = contadorNumerico + "-" + subContadorNumerico + letraActual;

        // Incrementa la letra
        letraActual++;

        // Si la letra llega más allá de 'Z', reinicia la letra y aumenta el subcontador
        if (letraActual > 'Z') {
            letraActual = 'A'; // Reinicia la letra
            subContadorNumerico++; // Incrementa el subcontador
        }

        // Si el subcontador supera 1999, reinicia y aumenta la parte principal
        if (subContadorNumerico > 2000) {
            subContadorNumerico = 1001; // Reinicia el subcontador
            contadorNumerico++; // Incrementa la parte principal
        }

        return numeroFicha;
    }
    
    public void CrearyModificarJugador() {
        try {
            // Obtener el número de ficha y validar
            String numFicha = txtNumFicha.getText();
            if (numFicha.isEmpty()) {
                parentFrame.mensaje("El campo de número de ficha está vacío.", 0);
                return;
            }

            // Validar campo de nombre
            String nombre = txtNombre.getText();
            if (nombre.isEmpty()) {
                parentFrame.mensaje("El campo de nombre está vacío.", 0);
                return;
            }

            // Validar campo de nacionalidad
            String nacionalidad = txtNacionalidad.getText();
            if (nacionalidad.isEmpty()) {
                parentFrame.mensaje("El campo de nacionalidad está vacío.", 0);
                return;
            }

            // Validar y dividir la fecha de nacimiento
            String fechaNacimiento = txtFechaNacimiento.getText();
            if (fechaNacimiento.isEmpty()) {
                parentFrame.mensaje("El campo de fecha de nacimiento está vacío.", 0);
                return;
            }
            String[] partesFecha = fechaNacimiento.split("/");
            if (partesFecha.length != 3) {
                parentFrame.mensaje("El formato de fecha debe ser DD/MM/AAAA.", 0);
                return;
            }
            int day = Integer.parseInt(partesFecha[0]);
            int month = Integer.parseInt(partesFecha[1]);
            int year = Integer.parseInt(partesFecha[2]);

            // Validar y convertir altura
            if (txtAltura.getText().isEmpty()) {
                parentFrame.mensaje("El campo de altura está vacío.", 0);
                return;
            }
            double altura = Double.parseDouble(txtAltura.getText());

            // Validar y convertir peso
            if (txtPeso.getText().isEmpty()) {
                parentFrame.mensaje("El campo de peso está vacío.", 0);
                return;
            }
            double peso = Double.parseDouble(txtPeso.getText());

            // Validar y convertir dorsal
            if (txtNroDorsal.getText().isEmpty()) {
                parentFrame.mensaje("El campo de dorsal está vacío.", 0);
                return;
            }
            int dorsal = Integer.parseInt(txtNroDorsal.getText());

            // Validar posición seleccionada
            String posicion = (String) combxPosicion.getSelectedItem();
            if (posicion == null || posicion.isEmpty()) {
                parentFrame.mensaje("Debe seleccionar una posición.", 0);
                return;
            }

            // Validar temporada seleccionada
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
                parentFrame.mensaje("Debe seleccionar una temporada válida.", 0);
                return;
            }
            int idEquipoFiltrar = combFiltrarJugador.getSelectedIndex();
            // Validar equipo seleccionado
            int idEquipo = combxEquipo.getSelectedIndex();
            if (idEquipo == -1) {
                parentFrame.mensaje("Debe seleccionar un equipo.", 0);
                return;
            }

            // Buscar si el jugador existe en la lista
            Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
            Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipoFiltrar);
            Jugador jugadorExistente = null;

            for (Jugador jugador : equipoSeleccionado.getListJugadores()) {
                if (jugador.getNumFicha().equals(numFicha)) {
                    jugadorExistente = jugador;
                    break;
                }
            }

            if (jugadorExistente != null) {
            	// Si el jugador existe, eliminarlo de la lista y modificar sus datos
                equipoSeleccionado.getListJugadores().remove(jugadorExistente); // Eliminar jugador existente
//                jugadorExistente.setNombre(nombre);
//                jugadorExistente.setNacionalidad(nacionalidad);
//                jugadorExistente.setFechaNac(new Fecha(day, month, year));
//                jugadorExistente.setAltura(altura);
//                jugadorExistente.setPeso(peso);
//                jugadorExistente.setDorsal(dorsal);
//                jugadorExistente.setPosicion(posicion);
//                jugadorExistente.setIdEquipo(idEquipo);
//                // Agregar el jugador actualizado a la lista
                temporadaSeleccionada.getListEquipos().get(idEquipo).getListJugadores().add(new Jugador(numFicha, nombre, dorsal, posicion, nacionalidad, altura, peso, day, month, year, idEquipo));
                parentFrame.mensaje("Jugador modificado correctamente.", 2);
            } else {
                // Si el jugador no existe, crearlo
                Jugador nuevoJugador = new Jugador(numFicha, nombre, dorsal, posicion, nacionalidad, altura, peso, day, month, year, idEquipo);
                equipoSeleccionado.getListJugadores().add(nuevoJugador);
                parentFrame.mensaje("Jugador creado correctamente.", 2);
            }

            // Actualizar la tabla (el modelo de la tabla se actualizará)
            ((JugadorTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;

            // Actualizar archivo si es necesario
            actualizarArchivo();

        } catch (NumberFormatException e) {
            parentFrame.mensaje("Error al convertir un campo numérico. Verifique los datos ingresados.", 0);
        } catch (Exception e) {
            parentFrame.mensaje("Error inesperado: " + e.getMessage(), 0);
            e.printStackTrace();
        }
    }

    
    public void CrearJugador() {
        try {
            // Generar número de ficha
//            String numFicha = generarNumeroFicha();
        	String numFicha = txtNumFicha.getText();

            // Validar campo de nombre
            String nombre = txtNombre.getText();
            if (nombre.isEmpty()) {
                parentFrame.mensaje("El campo de nombre está vacío.", 0);
                return;
            }

            // Validar campo de nacionalidad
            String nacionalidad = txtNacionalidad.getText();
            if (nacionalidad.isEmpty()) {
                parentFrame.mensaje("El campo de nacionalidad está vacío.", 0);
                return;
            }

            // Validar y dividir la fecha de nacimiento
            String fechaNacimiento = txtFechaNacimiento.getText();
            if (fechaNacimiento.isEmpty()) {
                parentFrame.mensaje("El campo de fecha de nacimiento está vacío.", 0);
                return;
            }
            String[] partesFecha = fechaNacimiento.split("/");
            if (partesFecha.length != 3) {
                parentFrame.mensaje("El formato de fecha debe ser DD/MM/AAAA.", 0);
                return;
            }
            int day = Integer.parseInt(partesFecha[0]); // Día
            int month = Integer.parseInt(partesFecha[1]); // Mes
            int year = Integer.parseInt(partesFecha[2]); // Año

            // Validar y convertir altura
            if (txtAltura.getText().isEmpty()) {
                parentFrame.mensaje("El campo de altura está vacío.", 0);
                return;
            }
            double altura = Double.parseDouble(txtAltura.getText());

            // Validar y convertir peso
            if (txtPeso.getText().isEmpty()) {
                parentFrame.mensaje("El campo de peso está vacío.", 0);
                return;
            }
            double peso = Double.parseDouble(txtPeso.getText());

            // Validar y convertir dorsal
            if (txtNroDorsal.getText().isEmpty()) {
                parentFrame.mensaje("El campo de dorsal está vacío.", 0);
                return;
            }
            int dorsal = Integer.parseInt(txtNroDorsal.getText());

            // Validar posición seleccionada
            String posicion = (String) combxPosicion.getSelectedItem();
            if (posicion == null || posicion.isEmpty()) {
                parentFrame.mensaje("Debe seleccionar una posición.", 0);
                return;
            }
            // Validar temporada seleccionada
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
                parentFrame.mensaje("Debe seleccionar una temporada válida.", 0);
                return;
            }

            // Validar equipo seleccionado
            int idEquipo = combxEquipo.getSelectedIndex();
            if (idEquipo == -1) {
                parentFrame.mensaje("Debe seleccionar un equipo.", 0);
                return;
            }

            // Crear el objeto Jugador con los valores obtenidos
            Jugador jugador = new Jugador(numFicha, nombre, dorsal, posicion, nacionalidad, altura, peso, day, month, year, idEquipo);
            
         // Agregar el jugador a la lista del equipo correspondiente
            listTemporadas.get(idTemporada).getListEquipos().get(idEquipo).getListJugadores().add(jugador);
            parentFrame.changes = true;
            
            // Actualizar la tabla
            actualizarTabla();
            
            // Mostrar mensaje de éxito
            parentFrame.mensaje("Jugador creado correctamente.", 2);

            // (Opcional) Mostrar el jugador creado en la consola
            System.out.println("Jugador creado: " + jugador);
            System.out.println("Validar que esta dentro de la temporada: " + listTemporadas.get(idTemporada).getListEquipos().get(idEquipo).getListJugadores().contains(jugador));
            System.out.println("Temporada ID: " + idTemporada);
            System.out.println("Nombre Temporada: "+ listTemporadas.get(idTemporada).getNombre());
            System.out.println(listTemporadas.get(idTemporada).getListEquipos().get(idEquipo).toString());
            
        } catch (NumberFormatException e) {
            parentFrame.mensaje("Error al convertir un campo numérico. Verifique los datos ingresados.", 0);
        } catch (Exception e) {
            parentFrame.mensaje("Error inesperado: " + e.getMessage(), 0);
            e.printStackTrace();
        }
        actualizarArchivo();
    }
    
    public void CrearJugadoresPrueba() {
        // Verificar que haya temporadas y equipos disponibles
        if (listTemporadas == null || listTemporadas.isEmpty()) {
            parentFrame.mensaje("No hay temporadas disponibles", 0);
            return;
        }

        // Seleccionar la temporada y el equipo donde se agregarán los jugadores (ejemplo: temporada 0, equipo 0)
        Temporada temporadaSeleccionada = listTemporadas.get(0);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(0);

        // Crear jugadores de prueba y agregarlos a la lista del equipo seleccionado
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1001A", "Carlos Lopez", 13, "Armador", "Argentina", 1.85, 82, 12, 11, 1995, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1002B", "Javier Torres", 5, "Opuesto", "Portugal", 1.98, 84, 26, 11, 1998, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1003C", "Luis Martinez", 1, "Receptor 1", "Brasil", 1.79, 97, 7, 1, 1990, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1004D", "Juan Perez", 19, "Receptor 2", "Colombia", 2.05, 88, 10, 3, 1995, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1005E", "Antonio Gomez", 20, "Central", "México", 1.79, 94, 15, 12, 1995, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1006F", "Fernando Ruiz", 4, "Libero", "Argentina", 1.74, 83, 4, 8, 1996, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1007G", "Manuel Diaz", 18, "Armador", "Chile", 2.01, 100, 16, 5, 1986, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1008H", "Sergio Sanchez", 21, "Opuesto", "Perú", 1.74, 69, 8, 12, 1997, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1009I", "David Hernandez", 29, "Receptor 1", "Bolivia", 1.96, 72, 8, 11, 1985, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1010J", "Jorge Ramos", 22, "Receptor 2", "Paraguay", 1.99, 87, 4, 1, 1999, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1011K", "Raul Fernandez", 11, "Central", "Ecuador", 2.00, 91, 14, 8, 1999, 0));
        equipoSeleccionado.getListJugadores().add(new Jugador("1001-1012L", "Angel Gutierrez", 26, "Libero", "Venezuela", 1.85, 100, 23, 10, 1990, 0));

        parentFrame.changes = true;
        
        // Actualizar la tabla
        actualizarTabla();
        actualizarArchivo();

        // Mostrar mensaje de éxito
        parentFrame.mensaje("Jugadores creados correctamente en la temporada y equipo seleccionados.", 2);
    }

    
    private void ModificarJugador() {
        // Verificar que haya temporadas y equipos en la lista
        if (listTemporadas == null || listTemporadas.isEmpty()) {
            parentFrame.mensaje("No hay temporadas disponibles", 0);
            return;
        }
        
        // Obtener el índice de la temporada seleccionada y el equipo seleccionado
        int IDTemporada = combxFiltrarTempo.getSelectedIndex();  // Índice de la temporada seleccionada
        int IDEquipo = combFiltrarJugador.getSelectedIndex();  // Índice del equipo seleccionado
        
        // Verificar que los índices sean válidos
        if (IDTemporada == -1 || IDEquipo == -1 || IDTemporada >= listTemporadas.size()) {
            parentFrame.mensaje("Debe seleccionar una temporada y un equipo válidos", 0);
            return;
        }

        // Obtener la temporada y el equipo seleccionados
        Temporada temporadaSeleccionada = listTemporadas.get(IDTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(IDEquipo);

        // Verificar que el equipo seleccionado tiene jugadores
        if (equipoSeleccionado.getListJugadores() == null || equipoSeleccionado.getListJugadores().isEmpty()) {
            parentFrame.mensaje("No hay jugadores disponibles para el equipo seleccionado", 0);
            return;
        }

        // Obtener el índice de la fila seleccionada en la tabla
        int rowIndex = table.getSelectedRow();  // Obtiene la fila seleccionada de la tabla

        // Verificar si se ha seleccionado una fila
        if (rowIndex != -1) {
        	// Obtener el jugador seleccionado de la lista de jugadores del equipo seleccionado
            Jugador jugadorSeleccionado = equipoSeleccionado.getListJugadores().get(rowIndex); 
            
            // Validar y obtener los valores de los campos
            String numFicha = txtNumFicha.getText();

            String nombre = txtNombre.getText();
            if (nombre.isEmpty()) {
                parentFrame.mensaje("El campo de nombre está vacío.", 0);
                return;
            }

            String nacionalidad = txtNacionalidad.getText();
            if (nacionalidad.isEmpty()) {
                parentFrame.mensaje("El campo de nacionalidad está vacío.", 0);
                return;
            }

            String fechaNacimiento = txtFechaNacimiento.getText();
            if (fechaNacimiento.isEmpty()) {
                parentFrame.mensaje("El campo de fecha de nacimiento está vacío.", 0);
                return;
            }
            String[] partesFecha = fechaNacimiento.split("/");
            if (partesFecha.length != 3) {
                parentFrame.mensaje("El formato de fecha debe ser DD/MM/AAAA.", 0);
                return;
            }
            int day = Integer.parseInt(partesFecha[0]);
            int month = Integer.parseInt(partesFecha[1]);
            int year = Integer.parseInt(partesFecha[2]);

            if (txtAltura.getText().isEmpty()) {
                parentFrame.mensaje("El campo de altura está vacío.", 0);
                return;
            }
            double altura = Double.parseDouble(txtAltura.getText());

            if (txtPeso.getText().isEmpty()) {
                parentFrame.mensaje("El campo de peso está vacío.", 0);
                return;
            }
            double peso = Double.parseDouble(txtPeso.getText());

            if (txtNroDorsal.getText().isEmpty()) {
                parentFrame.mensaje("El campo de dorsal está vacío.", 0);
                return;
            }
            int dorsal = Integer.parseInt(txtNroDorsal.getText());

            String posicion = (String) combxPosicion.getSelectedItem();
            if (posicion == null || posicion.isEmpty()) {
                parentFrame.mensaje("Debe seleccionar una posición.", 0);
                return;
            }

            // Obtener el ID de la temporada seleccionada
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
                parentFrame.mensaje("Debe seleccionar una temporada válida.", 0);
                return;
            }

            // Obtener el ID del equipo seleccionado
            int idEquipo = combxEquipo.getSelectedIndex();
            if (idEquipo == -1) {
                parentFrame.mensaje("Debe seleccionar un equipo.", 0);
                return;
            }

            // Buscar el jugador y actualizar los valores
            if (jugadorSeleccionado.getNumFicha().equals(numFicha)) {
//            	equipoSeleccionado.getListJugadores().remove(jugadorSeleccionado);
                jugadorSeleccionado.setNombre(nombre);
                jugadorSeleccionado.setNacionalidad(nacionalidad);
                jugadorSeleccionado.setFechaNac(new Fecha(day, month, year));  // Usamos el setter para la fecha
                jugadorSeleccionado.setAltura(altura);
                jugadorSeleccionado.setPeso(peso);
                jugadorSeleccionado.setDorsal(dorsal);
                jugadorSeleccionado.setPosicion(posicion);
                jugadorSeleccionado.setIdEquipo(idEquipo);
//                equipoSeleccionado.getListJugadores().add(jugadorSeleccionado);
                    // Actualizar la tabla (el modelo de la tabla se actualizará)
                    ((JugadorTableModel) table.getModel()).fireTableDataChanged();

                    // Mostrar mensaje de éxito
                    parentFrame.mensaje("Jugador modificado correctamente.", 2);
                    return;
                }

            // Si llegamos aquí, es que no encontramos el jugador con el numFicha proporcionado
            parentFrame.mensaje("No se encontró un jugador con ese número de ficha.", 0);
        } else {
            parentFrame.mensaje("No se ha seleccionado un jugador.", 0);
        }
    }

    
    private void actualizarTabla() {
        // Validar temporada seleccionada
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
            parentFrame.mensaje("Debe seleccionar una temporada válida.", 0);
            return;
        }

        // Validar equipo seleccionado
        int idEquipo = combFiltrarJugador.getSelectedIndex();
        if (idEquipo == -1 || idEquipo >= listTemporadas.get(idTemporada).getListEquipos().size()) {
            parentFrame.mensaje("Debe seleccionar un equipo válido.", 0);
            return;
        }

        // Obtener la lista de jugadores del equipo seleccionado
        ArrayList<Jugador> jugadores = listTemporadas.get(idTemporada).getListEquipos().get(idEquipo).getListJugadores();

        // Actualizar el modelo de la tabla
        JugadorTableModel model = (JugadorTableModel) table.getModel();
        model.setListaJugadores(jugadores);
    }
    
    private <T> void actualizarComboBox(JComboBox<T> comboBox, java.util.List<T> listaDatos) {
        comboBox.removeAllItems(); // Eliminar elementos actuales del JComboBox
        for (T elemento : listaDatos) {
            comboBox.addItem(elemento); // Agregar cada elemento de la lista al JComboBox
        }
        // Seleccionar por defecto el primer elemento si la lista no está vacía
        if (!listaDatos.isEmpty()) {
            comboBox.setSelectedIndex(0);
        }
    }
    
    private void actualizarComboBoxEquipo(String temporadaSeleccionada, java.util.List<Temporada> listTemporadas) {
        for (Temporada temporada : listTemporadas) {
            if (temporada.getNombre().equals(temporadaSeleccionada)) {
                // Obtén la lista de equipos
                java.util.List<Equipo> listEquipos = temporada.getListEquipos();

                // Modelo para combxEquipo
                DefaultComboBoxModel<String> modeloEquipo = new DefaultComboBoxModel<>();
                // Modelo para combFiltrarJugador
                DefaultComboBoxModel<String> modeloFiltrarJugador = new DefaultComboBoxModel<>();

                // Llena los modelos con los nombres de los equipos
                for (Equipo equipo : listEquipos) {
                    String equipoNombre = equipo.getNombre();
                    modeloEquipo.addElement(equipoNombre);
                    modeloFiltrarJugador.addElement(equipoNombre);
                }

                // Actualiza ambos JComboBox
                combxEquipo.setModel(modeloEquipo);
                combFiltrarJugador.setModel(modeloFiltrarJugador);

                return; // Termina después de encontrar la temporada correspondiente
            }
        }
        // Si no se encuentra, limpia ambos JComboBox
        combxEquipo.setModel(new DefaultComboBoxModel<>());
        combFiltrarJugador.setModel(new DefaultComboBoxModel<>());
    }

    private void actualizarArchivo() {
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
	//metodo para cargar las temporadas
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

    
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

        if (o == btnCrearJugador) {
//        	CrearJugador();
        	CrearyModificarJugador();
			JOptionPane.showMessageDialog(this, "Jugador creado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (o == btnModificarJugador) {
//        	ModificarJugador();
        	CrearyModificarJugador();
			actualizarArchivo();
			JOptionPane.showMessageDialog(this, "Jugador Modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
        }
        else if (o == btnGuardarCambios) {
			actualizarArchivo();
			JOptionPane.showMessageDialog(this, "Jugadores guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
		}
		
	}
}