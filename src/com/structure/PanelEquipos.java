package com.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
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
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

import com.logic.Equipo;
import com.logic.Jugador;
import com.logic.Temporada;

import net.miginfocom.swing.MigLayout;

public class PanelEquipos extends JPanel implements ActionListener {

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
	JButton btnModificarEquipo;
	JPanel PanelContenidoJugador;
	JPanel PanelListEquipos;
	JPanel Cabecera2;
	JLabel lblTituloTablaJugadores;
	JPanel panel_1;
	JButton btnGuardarCambios;
	JTextField txtNumID;
	JLabel lblNroID;
	private JLabel lblNombreEquipo;
	private JTextField txtNombre;
	private JTextField txtEntrenador;
	private JLabel lblEntrenador;
	private JLabel lblFechaFundacion;
	private JTextField txtFechaFundacion;
	private JPanel panel_2;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JTable table;
	private JPanel panel_4;
	private JComboBox<Temporada> combxFiltrarTempo;
//	private JComboBox<String> combFiltrarJugador;
	private JButton btnBuscarJugador;
	JLabel labelImagen;
	ImageIcon icon;

    private ArrayList<Temporada> listTemporadas; // Lista de temporadas
    private ArrayList<Equipo> listEquipos; // Lista de equipos para combobox 
    private JButton btnCambiarFoto;
    private JLabel lblTituloTempo;
	/**
	 * Create the panel.
	 */


	public PanelEquipos(main parentFrame) {
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
		
		PanelContenedor = new JPanel();
		add(PanelContenedor);
		PanelContenedor.setLayout(new BoxLayout(PanelContenedor, BoxLayout.X_AXIS));
		
		PanelDatosJugador = new JPanel();
		PanelDatosJugador.setPreferredSize(new Dimension(200, 600)); // Tamaño deseado
		PanelContenedor.add(PanelDatosJugador);
		PanelDatosJugador.setLayout(new BorderLayout(0, 0));
		
		Cabecera = new JPanel();
		PanelDatosJugador.add(Cabecera, BorderLayout.NORTH);
		
		lblTituloDatosJugador = new JLabel("INFORMACIÓN DEL EQUIPO");
		Cabecera.add(lblTituloDatosJugador);
		
		panel = new JPanel();
		PanelDatosJugador.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Botón "Modificar Jugador"
		btnModificarEquipo = new JButton("Modificar Datos");
		panel.add(btnModificarEquipo);
		btnModificarEquipo.addActionListener(this);

		btnCambiarFoto = new JButton("Cambiar Fotografía");
		panel.add(btnCambiarFoto);
		btnCambiarFoto.addActionListener(this);
		
		PanelContenidoJugador = new JPanel();
		PanelContenidoJugador.setPreferredSize(new Dimension(600, 600)); // Tamaño deseado
		PanelDatosJugador.add(PanelContenidoJugador, BorderLayout.CENTER);
		PanelContenidoJugador.setLayout(new MigLayout("", "[40px][70px][85px]", "[150px][19px][19px][19px][19px]"));
		
		lblNroID = new JLabel("Nº ID:");
		lblNroID.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNroID, "cell 1 1,grow");
		
		txtNumID = new JTextField();
		txtNumID.setEditable(false);
		txtNumID.setEnabled(false);
		txtNumID.setText("0");
		PanelContenidoJugador.add(txtNumID, "cell 2 1,growx,aligny top");
		txtNumID.setColumns(10);
		
		lblNombreEquipo = new JLabel("NOMBRE:");
		lblNombreEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblNombreEquipo, "cell 1 2,grow");
		
		txtNombre = new JTextField();
		txtNombre.setText("Nombre Equipo");
		txtNombre.setColumns(10);
		PanelContenidoJugador.add(txtNombre, "cell 2 2,growx,aligny top");
		
		txtEntrenador = new JTextField();
		txtEntrenador.setText("Entrenador");
		txtEntrenador.setColumns(10);
		PanelContenidoJugador.add(txtEntrenador, "cell 2 3,growx,aligny top");
		
		lblEntrenador = new JLabel("ENTRENADOR:");
		lblEntrenador.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblEntrenador, "cell 1 3,grow");
		
		lblFechaFundacion = new JLabel("F. FUNDACIÓN:");
		lblFechaFundacion.setHorizontalAlignment(SwingConstants.RIGHT);
		PanelContenidoJugador.add(lblFechaFundacion, "cell 1 4,alignx left,growy");
		
		txtFechaFundacion = new JTextField();
		txtFechaFundacion.setText("AAAA");
		txtFechaFundacion.setColumns(10);
		PanelContenidoJugador.add(txtFechaFundacion, "cell 2 4,growx,aligny top");
		
		icon = new ImageIcon("media.jugadores/idFotodefault.png");
		Image image = icon.getImage();
		Image newImage = image.getScaledInstance(79, 93, Image.SCALE_SMOOTH);
		icon = new ImageIcon(newImage);
		labelImagen = new JLabel();
		PanelContenidoJugador.add(labelImagen, "cell 2 0,grow");
		labelImagen.setIcon(icon);
		DefaultComboBoxModel<String> modelPosicion = new DefaultComboBoxModel<>();
		modelPosicion.addElement("Armador");
		modelPosicion.addElement("Opuesto");
		modelPosicion.addElement("Receptor1");
		modelPosicion.addElement("Receptor2");
		modelPosicion.addElement("Central");
		modelPosicion.addElement("Libero");
		
		PanelListEquipos = new JPanel();
		PanelContenedor.add(PanelListEquipos);
		PanelListEquipos.setLayout(new BorderLayout(0, 0));
		
		Cabecera2 = new JPanel();
		PanelListEquipos.add(Cabecera2, BorderLayout.NORTH);
		
		lblTituloTablaJugadores = new JLabel("LISTA DE EQUIPOS POR TEMPORADA");
		Cabecera2.add(lblTituloTablaJugadores);
		
		panel_1 = new JPanel();
		PanelListEquipos.add(panel_1, BorderLayout.SOUTH);
		
		btnGuardarCambios = new JButton("Guardar Cambios");
		panel_1.add(btnGuardarCambios);
		btnGuardarCambios.addActionListener(this);
		
		panel_2 = new JPanel();
		PanelListEquipos.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		panel_3 = new JPanel();
		panel_2.add(panel_3, BorderLayout.CENTER);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
		
		// Crear el modelo de tabla para jugadores
		EquiposTableModel equipoTableModel = new EquiposTableModel(listEquipos);

		// Crear la tabla y asignar el modelo
		table = new JTable(equipoTableModel);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Selección de una sola fila
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            ReflejarSeleccionEquipo(); // Llama al método cuando se selecciona una fila
		        }
		    }
		});
		
		// Configurar el ancho de las columnas
		TableColumnModel columnModel = table.getColumnModel();

		// Establecer el ancho preferido de cada columna
		columnModel.getColumn(0).setPreferredWidth(25);  // "Nº ID"
		columnModel.getColumn(1).setPreferredWidth(150); // "Nombre"
		columnModel.getColumn(2).setPreferredWidth(80);  // "F. Fundacion"
		columnModel.getColumn(3).setPreferredWidth(150); // "Entrenador"
		columnModel.getColumn(4).setPreferredWidth(75);  // "Total Jugadores"
		
		
		// Formatear la tabla si tienes un método para eso (opcional)
		parentFrame.formatearTabla(table); // Si no tienes este método, puedes omitir esta línea

		// Agregar la tabla al JScrollPane
		scrollPane = new JScrollPane(table);
		scrollPane.getViewport().setBackground(colorbg);

		// Añadir el JScrollPane al panel deseado
		panel_3.add(scrollPane, BorderLayout.CENTER);

		
		panel_4 = new JPanel();
		panel_2.add(panel_4, BorderLayout.NORTH);
		
		lblTituloTempo = new JLabel("Temporada:");
		panel_4.add(lblTituloTempo);
		
		combxFiltrarTempo = new JComboBox<>();
		combxFiltrarTempo.setPreferredSize(new Dimension(150, 25)); // Establece un ancho de 150px y alto de 25px
		panel_4.add(combxFiltrarTempo);
		actualizarComboBox(combxFiltrarTempo, listTemporadas);
		combxFiltrarTempo.addActionListener(e -> {
		    Object selectedItem = combxFiltrarTempo.getSelectedItem();
		    if (selectedItem != null) {
		        actualizarTabla();
		    }
		});
		
		JComponent labelFormat[] = { lblTituloDatosJugador, lblTituloTablaJugadores, lblNroID, lblNombreEquipo, lblEntrenador, lblFechaFundacion };
		JComponent panelFormat[] = { PanelContenedor, PanelDatosJugador, Cabecera, PanelContenidoJugador, PanelListEquipos, Cabecera2, panel, panel_1, panel_2, panel_3, panel_4 };

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
	
	
    // Clase interna para el modelo de la tabla de EQUIPOS
    class EquiposTableModel extends AbstractTableModel {
    	ArrayList<Equipo>JTablelistaEquipos;
        private static final long serialVersionUID = 1L;
        private String[] columnNames = {
            "Nº ID", "Nombre", "Fecha Fund.", "Entrenador",
            "Cantid. Jugadores",
        };

        public EquiposTableModel(ArrayList<Equipo> listaEquipos) {
            if (listaEquipos == null) {
                this.JTablelistaEquipos = new ArrayList<>(); // Inicializa una lista vacía
            } else {
                this.JTablelistaEquipos = listaEquipos;
            }
        }
        @Override
        public int getRowCount() {
            return JTablelistaEquipos.size();
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
        	if (rowIndex < 0 || rowIndex >= JTablelistaEquipos.size()) {
                return null; // O lanzar una excepción personalizada
            }
        	Equipo equipo = JTablelistaEquipos.get(rowIndex);
            switch (columnIndex) {
                case 0: return equipo.getId();
                case 1: return equipo.getNombre();
                case 2: return equipo.getFechaFundEq();
                case 3: return equipo.getEntrenador();
                case 4: return equipo.getListJugadores() != null ? equipo.getListJugadores().size() : 0;
                default: return null;
            }
        }
        
        
     // Método para actualizar la lista de equipos
        public void setListaEquipos(ArrayList<Equipo> nuevaLista) {
            this.JTablelistaEquipos = nuevaLista;
            fireTableDataChanged(); // Notifica a la tabla que los datos han cambiado
        }
    }
    
    
    private void ReflejarSeleccionEquipo() {
        // Verificar que haya temporadas y equipos en la lista
        if (listTemporadas == null || listTemporadas.isEmpty()) {
            parentFrame.mensaje("No hay temporadas disponibles", 0);
            return;
        }
        
        // Obtener el índice de la temporada seleccionada y el equipo seleccionado
        int idTemporada = combxFiltrarTempo.getSelectedIndex();  // Índice de la temporada seleccionada
        
        // Verificar que los índices sean válidos
        if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
            parentFrame.mensaje("Debe seleccionar una temporada y un equipo válidos", 0);
            return;
        }

        // Obtener la temporada y el equipo seleccionados
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);

        // Obtener el índice de la fila seleccionada en la tabla
        int rowIndex = table.getSelectedRow();  // Obtiene la fila seleccionada de la tabla

        // Verificar si se ha seleccionado una fila válida
        if (rowIndex != -1) {
            try {
                // Obtener el jugador seleccionado de la lista de jugadores del equipo seleccionado
                Equipo EquipoSeleccionado = temporadaSeleccionada.getListEquipos().get(rowIndex); 

                // Asignar los valores a los campos de texto
                
                txtNumID.setText(Integer.toString(EquipoSeleccionado.getId()));
                txtNombre.setText(EquipoSeleccionado.getNombre());
                txtEntrenador.setText(EquipoSeleccionado.getEntrenador());
                txtFechaFundacion.setText(Integer.toString(EquipoSeleccionado.getFechaFundEq()));


             // **Construir la ruta de la imagen basada en la carpeta externa**
                String rutaImagen = System.getProperty("user.dir") + "/equipos/" + EquipoSeleccionado.getIdFoto() + ".png";

                // Cargar la imagen del jugador o usar una imagen por defecto si no existe
                File archivoImagen = new File(rutaImagen);
                if (!archivoImagen.exists()) {
                    System.err.println("⚠️ Imagen no encontrada: " + rutaImagen);
                    archivoImagen = new File(System.getProperty("user.dir") + "/equipos/idFotodefault.png");
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
                }


            } catch (IOException e) {
                System.err.println("❌ ERROR al cargar la imagen: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                parentFrame.mensaje("No se ha seleccionado un jugador válido", 0);
            }
        }
    }


    public void ModificarDatosEquipo() {
        try {
            // Obtener el ID del equipo y validar
            String idEquipoTexto = txtNumID.getText();
            if (idEquipoTexto.isEmpty()) {
                parentFrame.mensaje("❌ El campo de ID del equipo está vacío.", 0);
                return;
            }
            int idEquipo = Integer.parseInt(idEquipoTexto);

            // Validar el nombre del equipo
            String nombre = txtNombre.getText();
            if (nombre.isEmpty()) {
                parentFrame.mensaje("❌ El campo de nombre está vacío.", 0);
                return;
            }

            // Validar el entrenador
            String entrenador = txtEntrenador.getText();
            if (entrenador.isEmpty()) {
                parentFrame.mensaje("❌ El campo de entrenador está vacío.", 0);
                return;
            }

            // Validar y obtener el año de fundación
            String fechaFundacionTexto = txtFechaFundacion.getText();
            if (fechaFundacionTexto.isEmpty()) {
                parentFrame.mensaje("❌ El campo de año de fundación está vacío.", 0);
                return;
            }

            // Verificar que solo sea un número de 4 dígitos (AAAA)
            if (!fechaFundacionTexto.matches("\\d{4}")) {
                parentFrame.mensaje("❌ El año de fundación debe tener el formato AAAA.", 0);
                return;
            }

            int anioFundacion = Integer.parseInt(fechaFundacionTexto);

            // Validar la temporada seleccionada
            int idTemporada = combxFiltrarTempo.getSelectedIndex();
            if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
                parentFrame.mensaje("❌ Debe seleccionar una temporada válida.", 0);
                return;
            }

            // Buscar la temporada y verificar si el equipo existe
            Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
            Equipo equipoExistente = null;

            for (Equipo equipo : temporadaSeleccionada.getListEquipos()) {
                if (equipo.getId() == idEquipo) {
                    equipoExistente = equipo;
                    break;
                }
            }

            // Si el equipo existe, modificarlo
            if (equipoExistente != null) {
                equipoExistente.setNombre(nombre); // Modificar nombre
                equipoExistente.setEntrenador(entrenador); // Modificar entrenador
                equipoExistente.setFechaFundEq(anioFundacion); // Modificar año de fundación

                parentFrame.mensaje("✅ Equipo modificado correctamente.", 2);
            } else {
                parentFrame.mensaje("❌ No se encontró un equipo con el ID ingresado.", 0);
                return;
            }

            // Actualizar la tabla y guardar cambios
            ((EquiposTableModel) table.getModel()).fireTableDataChanged();
            parentFrame.changes = true;
            actualizarArchivo();

        } catch (NumberFormatException e) {
            parentFrame.mensaje("❌ Error: Verifique que los campos numéricos sean correctos.", 0);
        } catch (Exception e) {
            parentFrame.mensaje("❌ Error inesperado: " + e.getMessage(), 0);
            e.printStackTrace();
        }
    }

    public void CambiarFotografia() {
        int rowIndex = table.getSelectedRow();

        if (rowIndex == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un equipo de la lista.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Obtener equipo seleccionado
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        Temporada temporadaSeleccionada = listTemporadas.get(idTemporada);
        Equipo equipoSeleccionado = temporadaSeleccionada.getListEquipos().get(rowIndex);

        // **Nueva ruta para equipos fuera del JAR**
        String rutaBase = System.getProperty("user.dir") + "/equipos/";
        File directorio = new File(rutaBase);
        if (!directorio.exists()) {
            directorio.mkdirs(); // Crea la carpeta si no existe
        }

        String idFoto = equipoSeleccionado.getIdFoto(); // ID de la imagen basado en el equipo

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

                JOptionPane.showMessageDialog(this, "Fotografía del equipo guardada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar la imagen: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    
    private void actualizarTabla() {
        // Validar temporada seleccionada
        int idTemporada = combxFiltrarTempo.getSelectedIndex();
        if (idTemporada == -1 || idTemporada >= listTemporadas.size()) {
            parentFrame.mensaje("Debe seleccionar una temporada válida.", 0);
            return;
        }

        // Obtener la lista de equipos de la Temporada seleccionada
        ArrayList<Equipo> equipos = listTemporadas.get(idTemporada).getListEquipos();
        // Actualizar el modelo de la tabla
        EquiposTableModel model = (EquiposTableModel) table.getModel();
        model.setListaEquipos(equipos);
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
    
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		 if (o == btnModificarEquipo) {
	        	ModificarDatosEquipo();
	        }
		 else if (o == btnCambiarFoto) {
			 	CambiarFotografia();
		 }
		 else if (o == btnGuardarCambios){
				actualizarArchivo();
				JOptionPane.showMessageDialog(this, "Equipos guardados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
	 
		 }
	}
}