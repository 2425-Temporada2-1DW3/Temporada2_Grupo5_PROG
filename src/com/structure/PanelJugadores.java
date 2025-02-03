package com.structure;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.imageio.ImageIO;
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
import java.awt.image.BufferedImage;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import com.logic.Jugador;
import com.logic.Temporada;
import com.logic.Equipo;
import com.logic.Fecha;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PanelJugadores extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
 
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
	JLabel lblTituloTablaJugadores;
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
    private JLabel lblFiltroTemporada;
	/**
	 * Create the panel.
	 */

	public PanelJugadores(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio
		this.parentFrame = parentFrame;
 
	    this.listTemporadas = new ArrayList<>();
	    this.listEquipos = new ArrayList<>();
	    cargarTemporadasDesdeArchivo();
	    
		// Cambia color del Jpanel
 		setLayout(new BorderLayout(0, 0));
		
		PanelContenedor = new JPanel();
		add(PanelContenedor);
		PanelContenedor.setLayout(new BoxLayout(PanelContenedor, BoxLayout.X_AXIS));
		
		PanelDatosJugador = new JPanel();
		PanelDatosJugador.setPreferredSize(new Dimension(100, 600)); // Tamaño deseado
		PanelContenedor.add(PanelDatosJugador);
		PanelDatosJugador.setLayout(new BorderLayout(0, 0));
		
		Cabecera = new JPanel();
		PanelDatosJugador.add(Cabecera, BorderLayout.NORTH);
		
		lblTituloDatosJugador = new JLabel("DATOS PERSONALES");
		Cabecera.add(lblTituloDatosJugador);
		
		panel = new JPanel();
		PanelDatosJugador.add(panel, BorderLayout.SOUTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {0, 113, 131, 0}; // Primera columna de 10px como margen izquierdo
		gbl_panel.rowHeights = new int[]{21, 21, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);

		// Botón "Modificar Jugador"
		btnModificarJugador = new JButton("Modificar Jugador");
		parentFrame.buttonCreate(btnModificarJugador, panel, parentFrame.colorBlue);
		GridBagConstraints gbc_btnModificarJugador = new GridBagConstraints();
		gbc_btnModificarJugador.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnModificarJugador.insets = new Insets(0, 10, 5, 5); // Margen izquierdo de 10px
		gbc_btnModificarJugador.gridx = 1; // Cambiado a la columna 1 (después de la separación)
		gbc_btnModificarJugador.gridy = 0;
		panel.add(btnModificarJugador, gbc_btnModificarJugador);
		
		// Botón "Añadir Jugador"
		btnCrearJugador = new JButton("Crear Jugador");
		parentFrame.buttonCreate(btnCrearJugador, panel, parentFrame.colorGreen);
		GridBagConstraints gbc_btnCrearJugador = new GridBagConstraints();
		gbc_btnCrearJugador.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnCrearJugador.insets = new Insets(0, 0, 5, 0); // Sin margen adicional
		gbc_btnCrearJugador.gridx = 2; // Cambiado a la columna 2
		gbc_btnCrearJugador.gridy = 0;
		panel.add(btnCrearJugador, gbc_btnCrearJugador);

		// Botón "Cambiar Fotografía"
		btnCambiarFoto = new JButton("Cambiar Fotografía");
		parentFrame.buttonCreate(btnCambiarFoto, panel, parentFrame.colorYellow);
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
		txtNumFicha.setText("0000-0000X");
		PanelContenidoJugador.add(txtNumFicha, "cell 1 1,alignx left,aligny top");
		txtNumFicha.setColumns(10);
		
		lblNombreJug = new JLabel("NOMBRE:");
		lblNombreJug.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNombreJug, "cell 0 2,grow");
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre y Apellido");
		txtNombre.setColumns(10);
		PanelContenidoJugador.add(txtNombre, "cell 1 2,alignx left,aligny top");
		
		txtNacionalidad = new JTextField();
		txtNacionalidad.setText("Nacionalidad");
		txtNacionalidad.setColumns(10);
		PanelContenidoJugador.add(txtNacionalidad, "cell 1 3,alignx left,aligny top");
		
		lblNacionalidad = new JLabel("NACIONALIDAD:");
		lblNacionalidad.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNacionalidad, "cell 0 3,grow");
		
		lblFechaNacimiento = new JLabel("F. NACIMIENTO:");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblFechaNacimiento, "cell 0 4,grow");
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setText("DD/MM/AAAA");
		txtFechaNacimiento.setColumns(10);
		PanelContenidoJugador.add(txtFechaNacimiento, "cell 1 4,alignx left,aligny top");
		
		lblAltura = new JLabel("ALTURA (MT):");
		lblAltura.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblAltura, "cell 0 5,grow");
		
		txtAltura = new JTextField();
		txtAltura.setText("0");
		txtAltura.setColumns(10);
		PanelContenidoJugador.add(txtAltura, "cell 1 5,alignx left,aligny top");
		
		lblPeso = new JLabel("PESO (KG):");
		lblPeso.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblPeso, "cell 0 6,grow");
		
		txtPeso = new JTextField();
		txtPeso.setText("0");
		txtPeso.setColumns(10);
		PanelContenidoJugador.add(txtPeso, "cell 1 6,alignx left,aligny top");
		
		txtNroDorsal = new JTextField();
		txtNroDorsal.setText("0");
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
		
		icon = new ImageIcon("imagenes/jugadores/idFotodefault.png");
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
		
		lblTituloTablaJugadores = new JLabel("LISTA DE JUGADORES POR TEMPORADA");
		Cabecera2.add(lblTituloTablaJugadores);
		
		panel_1 = new JPanel();
		PanelListJugadores.add(panel_1, BorderLayout.SOUTH);
		
		btnEliminarJugador = new JButton("Eliminar Jugador");
		parentFrame.buttonCreate(btnEliminarJugador, panel_1, parentFrame.colorRed);
		
		btnEliminarTodos = new JButton("Eliminar Todos");
		parentFrame.buttonCreate(btnEliminarTodos, panel_1, parentFrame.colorRed);
		
		btnGuardarCambios = new JButton("Guardar Cambios");
		parentFrame.buttonCreate(btnGuardarCambios, panel_1, parentFrame.colorBlue);
		
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
		columnModel.getColumn(1).setPreferredWidth(145); // "Nombre"
		columnModel.getColumn(2).setPreferredWidth(45);  // "Edad"
		columnModel.getColumn(3).setPreferredWidth(125); // "Nacionalidad"
		columnModel.getColumn(4).setPreferredWidth(50);  // "Altura"
		columnModel.getColumn(5).setPreferredWidth(50);  // "Peso"
		columnModel.getColumn(6).setPreferredWidth(60); // "Dorsal"
		columnModel.getColumn(7).setPreferredWidth(100); // "Posicion"
		
		
		// Formatear la tabla si tienes un método para eso (opcional)
		parentFrame.formatearTabla(table); // Si no tienes este método, puedes omitir esta línea

		// Agregar la tabla al JScrollPane
		scrollPane = new JScrollPane(table);
        parentFrame.formatearScrollPane(scrollPane);

		// Añadir el JScrollPane al panel deseado
		panel_3.add(scrollPane, BorderLayout.CENTER);

		
		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		lblFiltroTemporada = new JLabel("Temporada:");
		panel_4.add(lblFiltroTemporada);
		
		combxFiltrarTempo = new JComboBox<>();
		combxFiltrarTempo.setPreferredSize(new Dimension(150, 25)); // Establece un ancho de 150px y alto de 25px
		panel_4.add(combxFiltrarTempo);
		actualizarComboBox(combxFiltrarTempo, listTemporadas);
		combxFiltrarTempo.addActionListener(e -> {
		    Object selectedItem = combxFiltrarTempo.getSelectedItem();
		    if (selectedItem != null) {
		        actualizarComboBoxEquipo(selectedItem.toString(), listTemporadas);
		        actualizarTabla();
		        actualizarEstadoBotones();
		    }
		});
		
		lblNewLabel_10 = new JLabel("Equipo:");
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
 


	    
	    actualizarArchivo();
	}
	
	
	//BLOQUEAR BOTONES SI LA TEMPORADA ESTA INICIADA
	private void actualizarEstadoBotones() {
	    int selectedIndex = combxFiltrarTempo.getSelectedIndex();
	    
	    if (selectedIndex >= 0 && !listTemporadas.isEmpty()) {
	        Temporada temporadaSeleccionada = listTemporadas.get(selectedIndex);
	        
	        boolean temporadaIniciada = temporadaSeleccionada.isIniciado();
			btnGuardarCambios.setVisible(!temporadaIniciada);
			btnEliminarJugador.setEnabled(!temporadaIniciada);
			btnEliminarTodos.setEnabled(!temporadaIniciada);
			btnModificarJugador.setEnabled(!temporadaIniciada);
			btnCrearJugador.setEnabled(!temporadaIniciada);
			btnCambiarFoto.setEnabled(!temporadaIniciada);
	    }
	}

	
    // Clase interna para el modelo de la tabla de jugadores
    class JugadorTableModel extends AbstractTableModel {
    	ArrayList<Jugador>JTablelistaJugadores;
        private static final long serialVersionUID = 1L;
        private String[] columnNames = {
            "Nº Ficha", "Nombre", "Edad", "Nacionalidad",
            "Altura", "Peso","Dorsal", "Posición",
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
//                case 8: return jugador.getIdEquipo();
                default: return null;
            }
        }
        
        
     // Método para actualizar la lista de jugadores
        public void setListaJugadores(ArrayList<Jugador> nuevaLista) {
            this.JTablelistaJugadores = nuevaLista;
            fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
        }
    }

    public void ReflejarSeleccionJugador() {
        // Verificar que haya temporadas y equipos en la lista
        if (listTemporadas == null || listTemporadas.isEmpty()) {
            parentFrame.mensaje("No hay temporadas disponibles", 0);
            return;
        }

        // Obtener el índice de la temporada seleccionada y el equipo seleccionado
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        int idEquipo = combFiltrarJugador.getSelectedIndex();

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

             // Formatear la fecha de nacimiento en formato DD/MM/AAAA
                Fecha fechaNac = jugadorSeleccionado.getFechaNac();
                if (fechaNac != null) {
                    String fechaFormateada = String.format("%02d/%02d/%04d", fechaNac.getDia(), fechaNac.getMes(), fechaNac.getAno());
                    txtFechaNacimiento.setText(fechaFormateada);
                } else {
                    txtFechaNacimiento.setText(""); // Si no hay fecha, limpiar el campo
                }


                txtAltura.setText(String.valueOf(jugadorSeleccionado.getAltura()));
                txtPeso.setText(String.valueOf(jugadorSeleccionado.getPeso()));
                txtNroDorsal.setText(String.valueOf(jugadorSeleccionado.getDorsal()));

                // Asignar equipo y posición al ComboBox
                combxEquipo.setSelectedIndex(jugadorSeleccionado.getIdEquipo());
                combxPosicion.setSelectedItem(jugadorSeleccionado.getPosicion());

                // **Construir la ruta de la imagen basada en la carpeta externa**
                String rutaImagen = System.getProperty("user.dir") + "/imagenes/jugadores/" + jugadorSeleccionado.getIdFoto() + ".png";

                // Cargar la imagen del jugador o usar una imagen por defecto si no existe
                File archivoImagen = new File(rutaImagen);
                if (!archivoImagen.exists()) {
                    System.err.println("⚠️ Imagen no encontrada: " + rutaImagen);
                    archivoImagen = new File(System.getProperty("user.dir") + "/imagenes/jugadores/idFotodefault.png");
                }

                // Verificar si la imagen existe y cargarla
                if (archivoImagen.exists()) {
                    BufferedImage bufferedImage = ImageIO.read(archivoImagen);
                    Image newImage = bufferedImage.getScaledInstance(79, 93, Image.SCALE_SMOOTH);
                    ImageIcon icon = new ImageIcon(newImage);

                    // Asignar la nueva imagen al JLabel
                    labelImagen.setIcon(icon);
                    labelImagen.revalidate();
                    labelImagen.repaint();
                } else {
                    System.err.println("❌ ERROR: No se pudo cargar la imagen por defecto.");
                    labelImagen.setIcon(new ImageIcon()); // Icono vacío si no hay imagen
                    labelImagen.revalidate();
                    labelImagen.repaint();
                }

            } catch (IOException e) {
                System.err.println("❌ ERROR al cargar la imagen: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                parentFrame.mensaje("No se ha seleccionado un jugador válido", 0);
            }
        }
    }


    public void CambiarFotografia() {
        int rowIndex = table.getSelectedRow();

        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un jugador de la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener jugador seleccionado
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        int idEquipo = combFiltrarJugador.getSelectedIndex();
        
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipo);
        Jugador jugadorSeleccionado = equipoSeleccionado.getListJugadores().get(rowIndex);

        // **Nueva ruta fuera del JAR**
        String rutaBase = System.getProperty("user.dir") + "/imagenes/jugadores/";
        File directorio = new File(rutaBase);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea la carpeta si no existe
        }

        String idFoto = jugadorSeleccionado.getIdFoto(); // ID de la imagen

        // Abrir JFileChooser para seleccionar una nueva imagen
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Seleccionar Fotografía");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Imágenes (JPG, PNG)", "jpg", "png");
        fileChooser.setFileFilter(filter);

        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoSeleccionado = fileChooser.getSelectedFile();
            
            // **Definir la ruta de destino con extensión PNG**
            File archivoDestino = new File(rutaBase + idFoto + ".png");

            try {
                // **Leer la imagen seleccionada y convertirla a PNG**
                BufferedImage imagenOriginal = ImageIO.read(archivoSeleccionado);
                if (imagenOriginal == null) {
                    JOptionPane.showMessageDialog(this, "Error al leer la imagen.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // **Eliminar la imagen antigua antes de guardar la nueva**
                if (archivoDestino.exists()) {
                    archivoDestino.delete();
                }

                // **Guardar la imagen en formato PNG**
                ImageIO.write(imagenOriginal, "png", archivoDestino);

                // **Forzar la recarga de la imagen para evitar caché**
                BufferedImage bufferedImage = ImageIO.read(archivoDestino);
                Image newImage = bufferedImage.getScaledInstance(79, 93, Image.SCALE_SMOOTH);
                ImageIcon icon = new ImageIcon(newImage);
                labelImagen.setIcon(icon);

                // **Refrescar el JLabel para asegurar que la imagen se actualiza**
                labelImagen.revalidate();
                labelImagen.repaint();

                JOptionPane.showMessageDialog(this, "Fotografía convertida y guardada como PNG correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al convertir la imagen a PNG: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

    
    private Jugador obtenerDatosJugador() {
        try {
            // Obtener el número de ficha y validar
            String numFicha = txtNumFicha.getText();
            if (numFicha.isEmpty()) {
                parentFrame.mensaje("El campo de número de ficha está vacío.", 0);
                return null;
            }

            // Validar campos obligatorios
            String nombre = txtNombre.getText();
            String nacionalidad = txtNacionalidad.getText();
            if (nombre.isEmpty() || nacionalidad.isEmpty()) {
                parentFrame.mensaje("Los campos de nombre y nacionalidad son obligatorios.", 0);
                return null;
            }

            // Validar y dividir la fecha de nacimiento
            String fechaNacimiento = txtFechaNacimiento.getText();
            if (fechaNacimiento.isEmpty()) {
                parentFrame.mensaje("El campo de fecha de nacimiento está vacío.", 0);
                return null;
            }
            String[] partesFecha = fechaNacimiento.split("/");
            if (partesFecha.length != 3) {
                parentFrame.mensaje("El formato de fecha debe ser DD/MM/AAAA.", 0);
                return null;
            }
            int day = Integer.parseInt(partesFecha[0]);
            int month = Integer.parseInt(partesFecha[1]);
            int year = Integer.parseInt(partesFecha[2]);

            // Validar valores numéricos
            double altura = Double.parseDouble(txtAltura.getText());
            double peso = Double.parseDouble(txtPeso.getText());
            int dorsal = Integer.parseInt(txtNroDorsal.getText());

            // Validar posición seleccionada
            String posicion = (String) combxPosicion.getSelectedItem();
            if (posicion == null || posicion.isEmpty()) {
                parentFrame.mensaje("Debe seleccionar una posición.", 0);
                return null;
            }

            // Validar equipo seleccionado
            int idEquipo = combxEquipo.getSelectedIndex();
            if (idEquipo == -1) {
                parentFrame.mensaje("Debe seleccionar un equipo.", 0);
                return null;
            }

            // Obtener la foto del jugador (idFoto) o usar un valor por defecto
            String idFoto = "idFotodefault";
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            int idEquipoFiltrar = combFiltrarJugador.getSelectedIndex();

            // Buscar si el jugador ya existe y obtener su foto actual
            Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
            Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipoFiltrar);

            for (Jugador jugador : equipoSeleccionado.getListJugadores()) {
                if (jugador.getNumFicha().equals(numFicha)) {
                    idFoto = jugador.getIdFoto(); // Mantener su foto actual
                    break;
                }
            }

            // Crear y devolver el jugador con todos los datos
            return new Jugador(numFicha, nombre, dorsal, posicion, nacionalidad, altura, peso, day, month, year, idEquipo, idFoto);
        } catch (NumberFormatException e) {
            parentFrame.mensaje("Error al convertir un campo numérico. Verifique los datos ingresados.", 0);
            return null;
        }
    }

    public void CrearJugador() {
        try {
            // Obtener los datos del formulario
            Jugador nuevoJugador = obtenerDatosJugador();
            if (nuevoJugador == null) return; // Si hay un error en los datos, detener el proceso.

            // Buscar la temporada y equipo seleccionados
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            int idEquipo = combxEquipo.getSelectedIndex();
            Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
            Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipo);

            // **Verificar si ya existe un jugador con el mismo número de ficha**
            for (Jugador jugador : equipoSeleccionado.getListJugadores()) {
                if (jugador.getNumFicha().equals(nuevoJugador.getNumFicha())) {
                    parentFrame.mensaje(" Ya existe un jugador con el número de ficha '" + nuevoJugador.getNumFicha() + "'.", 0);
                    return; // Detener el proceso si el jugador ya existe
                }
            }

            // **Si el jugador no existe, agregarlo**
            equipoSeleccionado.getListJugadores().add(nuevoJugador);
            parentFrame.mensaje("✅ Jugador creado correctamente.", 2);

            // **Actualizar la tabla y archivo**
            ((JugadorTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;
            actualizarArchivo();
        } catch (Exception e) {
            parentFrame.mensaje("Error inesperado: " + e.getMessage(), 0);
            e.printStackTrace();
        }
    }

    public void ModificarJugador() {
        try {
            // Obtener los datos del formulario
            Jugador jugadorModificado = obtenerDatosJugador();
            if (jugadorModificado == null) return; // Si hay un error en los datos, detener el proceso.

            // Buscar la temporada y equipo seleccionados
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            int idEquipoFiltrar = combFiltrarJugador.getSelectedIndex();
            int idEquipo = combxEquipo.getSelectedIndex();
            Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
            Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipoFiltrar);

            // Buscar si el jugador existe en la lista
            Jugador jugadorExistente = null;
            for (Jugador jugador : equipoSeleccionado.getListJugadores()) {
                if (jugador.getNumFicha().equals(jugadorModificado.getNumFicha())) {
                    jugadorExistente = jugador;
                    break;
                }
            }

            if (jugadorExistente != null) {
                // Eliminar el jugador existente
                equipoSeleccionado.getListJugadores().remove(jugadorExistente);

                // Agregar el jugador modificado al nuevo equipo
                temporadaSeleccionada.getListEquipos().get(idEquipo).getListJugadores().add(jugadorModificado);
                parentFrame.mensaje("Jugador modificado correctamente.", 2);
            } else {
                parentFrame.mensaje("El jugador no existe en la lista.", 0);
            }

            // Actualizar la tabla
            ((JugadorTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;
            actualizarArchivo();
        } catch (Exception e) {
            parentFrame.mensaje("Error inesperado: " + e.getMessage(), 0);
            e.printStackTrace();
        }
    }

    public void EliminarJugador() {
        // Obtener la fila seleccionada en la tabla
        int rowIndex = table.getSelectedRow();
        
        if (rowIndex == -1) {
            parentFrame.mensaje("⚠️ Debe seleccionar un jugador para eliminar.", 0);
            return;
        }

        // Obtener la temporada y equipo seleccionados
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        int idEquipo = combFiltrarJugador.getSelectedIndex();
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipo);

        // Obtener el jugador seleccionado
        Jugador jugadorSeleccionado = equipoSeleccionado.getListJugadores().get(rowIndex);

        // Confirmar eliminación
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "¿Está seguro de que desea eliminar al jugador '" + jugadorSeleccionado.getNombre() + "'?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar jugador de la lista
            equipoSeleccionado.getListJugadores().remove(rowIndex);
            
            // Actualizar la tabla y archivo
            ((JugadorTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;
            actualizarArchivo();

            parentFrame.mensaje("✅ Jugador eliminado correctamente.", 2);
        }
    }
    
    public void EliminarTodos() {
        // Obtener la temporada y equipo seleccionados
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        int idEquipo = combFiltrarJugador.getSelectedIndex();
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(idEquipo);

        // Confirmar eliminación de todos los jugadores
        int confirmacion = JOptionPane.showConfirmDialog(
            this, 
            "⚠️ ¿Está seguro de que desea eliminar TODOS los jugadores del equipo '" + equipoSeleccionado.getNombre() + "'?", 
            "Confirmar Eliminación Masiva", 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.WARNING_MESSAGE
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Vaciar la lista de jugadores
            equipoSeleccionado.getListJugadores().clear();
            
            // Actualizar la tabla y archivo
            ((JugadorTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;
            actualizarArchivo();

            parentFrame.mensaje("✅ Todos los jugadores han sido eliminados correctamente.", 2);
        }
    }
    
    
    public void CrearJugadoresPrueba() {
        // Verificar que haya temporadas y equipos disponibles
        if (listTemporadas == null || listTemporadas.isEmpty()) {
            parentFrame.mensaje("No hay temporadas disponibles", 0);
            return;
        }

        Temporada temporadaSeleccionada = listTemporadas.get(0); // Seleccionamos la primera temporada

        if (temporadaSeleccionada.getListEquipos().size() < 6) { 
            parentFrame.mensaje("No hay suficientes equipos disponibles", 0);
            return;
        }

        // Lista de jugadores de prueba (puedes expandirla)
        Jugador[] jugadores = {
            new Jugador("1001-1001A", "Carlos Lopez", 13, "Armador", "Argentina", 1.85, 82, 12, 11, 1995, 0, "A1"),
            new Jugador("1001-1002B", "Javier Torres", 5, "Opuesto", "Portugal", 1.98, 84, 26, 11, 1998, 0, "A2"),
            new Jugador("1001-1003C", "Luis Martinez", 1, "Receptor 1", "Brasil", 1.79, 97, 7, 1, 1990, 0, "A3"),
            new Jugador("1001-1004D", "Juan Perez", 19, "Receptor 2", "Colombia", 2.05, 88, 10, 3, 1995, 0, "A4"),
            new Jugador("1001-1005E", "Antonio Gomez", 20, "Central", "México", 1.79, 94, 15, 12, 1995, 0, "A5"),
            new Jugador("1001-1006F", "Fernando Ruiz", 4, "Libero", "Argentina", 1.74, 83, 4, 8, 1996, 0, "A6"),
            new Jugador("1001-1007G", "Manuel Diaz", 18, "Armador", "Chile", 2.01, 100, 16, 5, 1986, 0, "A7"),
            new Jugador("1001-1008H", "Sergio Sanchez", 21, "Opuesto", "Perú", 1.74, 69, 8, 12, 1997, 0, "A8"),
            new Jugador("1001-1009I", "David Hernandez", 29, "Receptor 1", "Bolivia", 1.96, 72, 8, 11, 1985, 0, "A9"),
            new Jugador("1001-1010J", "Jorge Ramos", 22, "Receptor 2", "Paraguay", 1.99, 87, 4, 1, 1999, 0, "A10"),
            new Jugador("1001-1011K", "Raul Fernandez", 11, "Central", "Ecuador", 2.00, 91, 14, 8, 1999, 0, "A11"),
            new Jugador("1001-1012L", "Angel Gutierrez", 26, "Libero", "Venezuela", 1.85, 100, 23, 10, 1990, 0, "A12"),
            new Jugador("1002-2001A", "Mario Lopez", 2, "Armador", "Argentina", 1.87, 81, 12, 1, 1995, 1, "B1"),
            new Jugador("1002-2002B", "Pedro Garcia", 4, "Opuesto", "Brasil", 1.93, 88, 25, 5, 1998, 1, "B2"),
            new Jugador("1002-2003C", "Juan Gomez", 5, "Receptor 1", "Chile", 1.96, 85, 17, 3, 1992, 1, "B3"),
            new Jugador("1002-2004D", "Manuel Torres", 6, "Receptor 2", "Colombia", 1.98, 78, 21, 7, 1993, 1, "B4"),
            new Jugador("1002-2005E", "Pablo Sanchez", 7, "Central", "Ecuador", 2.01, 90, 2, 10, 1991, 1, "B5"),
            new Jugador("1002-2006F", "David Ruiz", 8, "Libero", "Perú", 1.75, 72, 18, 12, 1997, 1, "B6"),
            new Jugador("1002-2007G", "Alberto Herrera", 10, "Armador", "Venezuela", 1.88, 87, 9, 9, 1994, 1, "B7"),
            new Jugador("1002-2008H", "Rafael Moreno", 11, "Opuesto", "Paraguay", 1.89, 80, 6, 6, 1999, 1, "B8"),
            new Jugador("1002-2009I", "Lucas Castro", 12, "Receptor 1", "Uruguay", 1.94, 84, 13, 11, 1990, 1, "B9"),
            new Jugador("1002-2010J", "Andres Vega", 13, "Receptor 2", "Bolivia", 1.92, 79, 28, 2, 1996, 1, "B10"),
            new Jugador("1002-2011K", "Carlos Rojas", 14, "Central", "México", 2.03, 88, 14, 4, 1993, 1, "B11"),
            new Jugador("1002-2012L", "Victor Jimenez", 15, "Libero", "Argentina", 1.76, 74, 20, 8, 1995, 1, "B12"),
            new Jugador("1003-3001A", "Sergio Navarro", 1, "Armador", "Brasil", 1.86, 83, 11, 4, 1994, 2, "C1"),
            new Jugador("1003-3002B", "Adrian Perez", 3, "Opuesto", "Chile", 1.90, 86, 14, 6, 1996, 2, "C2"),
            new Jugador("1003-3003C", "Jorge Medina", 9, "Receptor 1", "Colombia", 1.95, 89, 19, 12, 1993, 2, "C3"),
            new Jugador("1003-3004D", "Luis Blanco", 17, "Receptor 2", "Ecuador", 1.97, 82, 21, 3, 1991, 2, "C4"),
            new Jugador("1003-3005E", "Antonio Ortega", 18, "Central", "Paraguay", 2.00, 91, 31, 5, 1990, 2, "C5"),
            new Jugador("1003-3006F", "Francisco Gil", 20, "Libero", "Perú", 1.73, 76, 16, 9, 1998, 2, "C6"),
            new Jugador("1003-3007G", "Angel Marin", 22, "Armador", "Venezuela", 1.84, 80, 27, 11, 1995, 2, "C7"),
            new Jugador("1003-3008H", "Miguel Serrano", 23, "Opuesto", "Bolivia", 1.92, 87, 9, 1, 1999, 2, "C8"),
            new Jugador("1003-3009I", "Ricardo Romero", 24, "Receptor 1", "México", 1.96, 85, 4, 7, 1997, 2, "C9"),
            new Jugador("1003-3010J", "Daniel Ponce", 25, "Receptor 2", "Argentina", 1.91, 83, 13, 10, 1998, 2, "C10"),
            new Jugador("1003-3011K", "Ruben Campos", 26, "Central", "Brasil", 2.02, 89, 7, 2, 1996, 2, "C11"),
            new Jugador("1003-3012L", "Ivan Alvarez", 28, "Libero", "Chile", 1.78, 78, 22, 3, 1995, 2, "C12"),
            new Jugador("1004-4001A", "Oscar Vidal", 2, "Armador", "Uruguay", 1.87, 81, 1, 7, 1993, 3, "D1"),
            new Jugador("1004-4002B", "Marcos Villa", 3, "Opuesto", "Perú", 1.93, 85, 15, 6, 1994, 3, "D2"),
            new Jugador("1004-4003C", "Alejandro Moya", 4, "Receptor 1", "Paraguay", 1.92, 83, 29, 11, 1990, 3, "D3"),
            new Jugador("1004-4004D", "Enrique Cano", 5, "Receptor 2", "Venezuela", 1.94, 86, 11, 12, 1995, 3, "D4"),
            new Jugador("1004-4005E", "Hugo Cordero", 6, "Central", "Bolivia", 1.99, 92, 10, 9, 1997, 3, "D5"),
            new Jugador("1004-4006F", "Diego Marin", 7, "Libero", "México", 1.74, 73, 18, 8, 1996, 3, "D6"),
            new Jugador("1004-4007G", "Roberto Peña", 8, "Armador", "Argentina", 1.89, 85, 25, 2, 1991, 3, "D7"),
            new Jugador("1004-4008H", "Victor Reyes", 9, "Opuesto", "Brasil", 1.91, 82, 15, 1, 1998, 3, "D8"),
            new Jugador("1004-4009I", "Fernando Bravo", 10, "Receptor 1", "Chile", 1.95, 88, 23, 10, 1993, 3, "D9"),
            new Jugador("1004-4010J", "Alfonso Sanchez", 11, "Receptor 2", "Colombia", 1.93, 87, 27, 3, 1999, 3, "D10"),
            new Jugador("1004-4011K", "Cristian Leon", 12, "Central", "Ecuador", 2.01, 90, 30, 5, 1994, 3, "D11"),
            new Jugador("1004-4012L", "Alfredo Campos", 13, "Libero", "Uruguay", 1.76, 75, 5, 8, 1992, 3, "D12"),
            new Jugador("1005-5001A", "Jose Ortega", 1, "Armador", "Perú", 1.86, 80, 2, 4, 1993, 4, "E1"),
            new Jugador("1005-5002B", "Carlos Molina", 3, "Opuesto", "Paraguay", 1.94, 88, 19, 7, 1995, 4, "E2"),
            new Jugador("1005-5003C", "Luis Paredes", 5, "Receptor 1", "Venezuela", 1.92, 83, 21, 9, 1991, 4, "E3"),
            new Jugador("1005-5004D", "Mario Velasco", 8, "Receptor 2", "Bolivia", 1.95, 86, 17, 2, 1996, 4, "E4"),
            new Jugador("1005-5005E", "Ricardo Carrasco", 10, "Central", "México", 1.99, 92, 9, 3, 1998, 4, "E5"),
            new Jugador("1005-5006F", "Manuel Vidal", 12, "Libero", "Argentina", 1.75, 74, 14, 6, 1994, 4, "E6"),
            new Jugador("1005-5007G", "Diego Navarro", 15, "Armador", "Brasil", 1.87, 81, 11, 8, 1990, 4, "E7"),
            new Jugador("1005-5008H", "Jorge Palacios", 18, "Opuesto", "Chile", 1.93, 86, 23, 11, 1997, 4, "E8"),
            new Jugador("1005-5009I", "Adrian Dominguez", 20, "Receptor 1", "Colombia", 1.94, 85, 10, 1, 1999, 4, "E9"),
            new Jugador("1005-5010J", "Victor Lopez", 23, "Receptor 2", "Ecuador", 1.96, 89, 31, 12, 1992, 4, "E10"),
            new Jugador("1005-5011K", "Angel Rios", 25, "Central", "Uruguay", 2.02, 90, 19, 5, 1991, 4, "E11"),
            new Jugador("1005-5012L", "Francisco Vargas", 27, "Libero", "Perú", 1.78, 76, 6, 10, 1996, 4, "E12"),
            new Jugador("1006-6001A", "Alberto Guzman", 2, "Armador", "Paraguay", 1.89, 83, 15, 6, 1993, 5, "F1"),
            new Jugador("1006-6002B", "Miguel Varela", 4, "Opuesto", "Venezuela", 1.92, 85, 19, 1, 1995, 5, "F2"),
            new Jugador("1006-6003C", "Oscar Romero", 6, "Receptor 1", "Bolivia", 1.91, 84, 25, 11, 1992, 5, "F3"),
            new Jugador("1006-6004D", "Ruben Nieto", 9, "Receptor 2", "México", 1.94, 87, 7, 5, 1994, 5, "F4"),
            new Jugador("1006-6005E", "Andres Castaño", 11, "Central", "Argentina", 1.98, 90, 9, 9, 1996, 5, "F5"),
            new Jugador("1006-6006F", "Hugo Santiago", 14, "Libero", "Brasil", 1.77, 78, 22, 7, 1990, 5, "F6"),
            new Jugador("1006-6007G", "Pedro Vargas", 17, "Armador", "Chile", 1.88, 82, 29, 3, 1997, 5, "F7"),
            new Jugador("1006-6008H", "Samuel Ruiz", 21, "Opuesto", "Colombia", 1.95, 88, 3, 10, 1999, 5, "F8"),
            new Jugador("1006-6009I", "Felipe Peña", 24, "Receptor 1", "Ecuador", 1.97, 86, 18, 12, 1991, 5, "F9"),
            new Jugador("1006-6010J", "Fernando Serrano", 26, "Receptor 2", "Paraguay", 1.93, 84, 27, 4, 1998, 5, "F10"),
            new Jugador("1006-6011K", "Raul Perez", 28, "Central", "Venezuela", 2.01, 91, 14, 2, 1992, 5, "F11"),
            new Jugador("1006-6012L", "Ivan Martinez", 30, "Libero", "Bolivia", 1.79, 80, 5, 8, 1995, 5, "F12")
        };
       

     // Asignar 12 jugadores a cada equipo de forma secuencial en 6 equipos
        int jugadorIndex = 0; // Índice global de jugadores

        for (int equipoIndex = 0; equipoIndex < 6; equipoIndex++) {  // 6 equipos
            Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(equipoIndex);

            for (int j = 0; j < 12 && jugadorIndex < jugadores.length; j++) {  // 12 jugadores por equipo
                equipoSeleccionado.getListJugadores().add(jugadores[jugadorIndex]);
                jugadorIndex++; // Incrementa el índice global de jugadores
            }
        }

        parentFrame.mensaje("Jugadores creados y distribuidos en 6 equipos.", 1);
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
        	CrearJugador();
        }
        else if (o == btnModificarJugador) {
        	ModificarJugador();
			actualizarArchivo();
	        
        }
        else if (o == btnGuardarCambios) {
			actualizarArchivo();
			JOptionPane.showMessageDialog(this, "Jugadores guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	        
		}
        else if (o == btnCambiarFoto) {
        	CambiarFotografia();
        }
        else if (o == btnEliminarJugador) {
        	EliminarJugador();
        } 

        else if (o == btnEliminarTodos) {
        	EliminarTodos();
        }
	}
}