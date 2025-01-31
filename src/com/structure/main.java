package com.structure; 

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.JTableHeader;

import com.logic.Log;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public int userType; 
    public String userTypeName;// No usar para comparaciones, solo para mostrar el tipo de usuario visualmente si es necesitado
	public String userName;
	public File userFile;
	public File temporadasFile;
	public File resourcesFolder;

	public boolean changes= false;
	private Log log = new Log();

    
    public Color colorbg = 		new Color(40, 45, 50);
    public Color colortxt = 	new Color(220, 225, 235);
    public Color colorRed = 	new Color(255, 102, 102); 
    public Color colorGreen = 	new Color(102, 204, 102); 
    public Color colorYellow =  new Color(255, 206, 98);
    public Color colorBlue = 	new Color(102, 178, 255); 

    
    public Font fuenteDefecto = 	new Font("SansSerif", Font.PLAIN, 15);
    public Font fuenteDefectoBold = new Font("SansSerif", Font.BOLD, 15);
    public Font fuenteHeader = 		new Font("SansSerif", Font.BOLD, 18);
    public Font fuenteBtnPequeño = new Font("SansSerif", Font.BOLD, 13);

    private JButton btnMenuInicio =		new JButton("CLASIFICACIÓN");
    private JButton btnMenuTemporadas = new JButton("TEMPORADAS");
    private JButton btnMenuJugadores =	new JButton("JUGADORES");
    private JButton btnMenuEquipos =	new JButton("EQUIPOS");
    private JButton btnMenuUsuarios = 	new JButton("GESTION USUARIOS");
    private JButton btnMenuSalir = 		new JButton("CERRAR SESION");

    private JButton[] buttons = {btnMenuInicio, btnMenuTemporadas, btnMenuJugadores,btnMenuEquipos, btnMenuUsuarios,btnMenuSalir};
	private JPanel contentPane = new JPanel(), LayoutPanel_1 = new JPanel(), LayoutPanel = new JPanel();
    public JLabel lblMensaje = new JLabel();

 
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int userType = 4; // Tipo de usuario por defecto si no recibe un valor (
                    String userName = "Anonimo"; // Nombre de usuario por defecto
                    
                    main frame = new main(userType,userName); // Pasa usertype y username a la main
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


 
    public main(int userType, String userName) {
     	// coje las variables de la clase login y la pasa a una variable definida en la clase main
        this.userType = userType;
        this.userName = userName; 
	    File jarDir = new File(System.getProperty("user.dir"));
	    this.resourcesFolder = new File(jarDir, "resources");
	    this.userFile = new File(resourcesFolder, "usuario.ser");
	    this.temporadasFile = new File(resourcesFolder, "temporada.ser");

        
        
        // Cosas por defecto del Jframe
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
		setBounds(100, 100, 1000, 562);
		
		// Paneles de orden por defecto
		contentPane.setBackground(colorbg);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		LayoutPanel.setBackground(colorbg);
		contentPane.add(LayoutPanel, BorderLayout.NORTH);
		LayoutPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	
		LayoutPanel_1.setBackground(colorbg);
		contentPane.add(LayoutPanel_1, BorderLayout.CENTER);
		LayoutPanel_1.setLayout(new BorderLayout(0, 0));
		
		// Para monstar mensajes de error
        lblMensaje.setFont(new Font("Consolas", Font.PLAIN, 13));

		
		
		
		// Generar los 5 Botones de menu
		if (userType == 2 || userType == 4) {
			for (JButton button : buttons) {
				buttonCreate(button		  ,LayoutPanel,colorbg);
			}
		} else if (userType == 1){
			buttonCreate(btnMenuInicio	  ,LayoutPanel,colorbg);
			buttonCreate(btnMenuTemporadas,LayoutPanel,colorbg);
			buttonCreate(btnMenuSalir	  ,LayoutPanel,colorbg);

		} else {
			buttonCreate(btnMenuSalir	  ,LayoutPanel,colorbg);
		}
		
		// Cambia el titulo de la pagina y carga el menu apropiado para cada tipo de usuario
        if (userType == 0 ) {
        	userTypeName = "Usuarios";
        	switchPanel(PanelInicio.class);
        	btnMenuInicio.setEnabled(false);

        } else if (userType == 1) {
        	userTypeName = "Árbitro";
        	switchPanel(PanelInicio.class);
        	btnMenuInicio.setEnabled(false);

        } else if (userType == 2){
        	userTypeName = "Gestor";
        	switchPanel(PanelUsuarios.class);
        	btnMenuUsuarios.setEnabled(false);
        	
        } else if (userType == 4) {
        	userTypeName = "Director";
        	switchPanel(PanelUsuarios.class);
        	btnMenuUsuarios.setEnabled(false);
        }
        setTitle("Federación de Voleibol - Portal de Gestión | Usuario: " + userName + " | Rol: " + userTypeName);
        if (userType == 0) {
            setTitle("Federación de Voleibol - Portal de Información | Usuario: " + userName);

        }
        mensaje("Bienvenido, "+userName,2);
        log.add("Ventana main cargada, Usuario: "+userName+" Rol: ",0);
	}
    
    // Funcion para crear todos los botones del menu
    public void buttonCreate(JButton button, JPanel panel,Color color) { 
	    button.setFont(fuenteBtnPequeño);
	    button.addActionListener(this);
	    button.setForeground(Color.WHITE);
	    button.setBackground(color);
	    panel.add(button);
    }
       
    public void switchPanel(Class<? extends JPanel> panelClass) {
        try {
            JPanel panel = panelClass.getConstructor(main.class)
                    					.newInstance(this);
            
            LayoutPanel_1.removeAll();
            LayoutPanel_1.add(lblMensaje, BorderLayout.NORTH);
            LayoutPanel_1.add(panel, BorderLayout.CENTER);
            lblMensaje.setText("");
            
            revalidate();
            repaint();
        	log.add("Panel "+panel.getClass()+" cargado",0);

            for (JButton button : buttons) {
                button.setEnabled(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.add("Error cargando panel: " + panelClass.getSimpleName(),2);
            
        }
    }
   

    private int panelDeOpcion(String msg, String titulo) {
    	formatearPanelDeOpcion();
    	log.add("Panel de opcion :"+titulo+" ha sido cargado",0);
        int result = JOptionPane.showConfirmDialog(
        		
                main.this,
                msg,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        return result;
    }
	   
    public void mensaje(String msg, int color) {
        if (color == 0) {
            lblMensaje.setForeground(colorRed);
            log.add("Usuario " +userName+": [lblMensaje] "+msg,2);
            msg = "ERROR : " + msg;
            
        } else if (color == 1) {
            lblMensaje.setForeground(colorYellow);
            log.add("Usuario " +userName+": [lblMensaje] "+msg,1);

            msg = "AVISO :" + msg;

        	
        } else if (color ==2) {
            log.add("Usuario " +userName+":  [lblMensaje] "+msg,0);

            lblMensaje.setForeground(colorGreen);

        }
        lblMensaje.setText(msg+".");

        revalidate();
        repaint();
    }
    
    public void cambios(boolean cambios) {
    	changes = cambios;
    }
    
    public void formatearPanelDeOpcion() {
    	UIManager.put("Panel.background", colorbg);
    	UIManager.put("OptionPane.background", colorbg);
    	UIManager.put("OptionPane.messageForeground", colortxt);
    	UIManager.put("Button.background", colorbg);
    	UIManager.put("Button.foreground", colortxt);
    	
    	
    }

    public void formatearTabla(JTable table) {
        // Set table background and foreground
        table.setBackground(colorbg);
        table.setForeground(colortxt);
        table.setFont(fuenteDefecto);
        // Set selection colors
        table.setSelectionBackground(colortxt); // Background for selected row(s)
        table.setSelectionForeground(colorbg); // Text color for selected row(s)

        // Set grid color
        table.setGridColor(colortxt);

        // Customize the table header
        JTableHeader tableHeader = table.getTableHeader();
        tableHeader.setBackground(colorbg);
        tableHeader.setForeground(colortxt);
        tableHeader.setFont(fuenteDefectoBold); // Change font if needed
        tableHeader.setReorderingAllowed(false); // Optional: prevent column reordering

    }
    
    
    @Override
     public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (changes && o == btnMenuInicio && o == btnMenuTemporadas && o == btnMenuJugadores && o == btnMenuUsuarios && o == btnMenuSalir ){
        	int resultado = panelDeOpcion( "Hay Datos Sin Guardar, Quieres salir?","Datos Sin Guardar");
            // Check user's choice
            if (resultado == JOptionPane.YES_OPTION) {
            	changes = false;
            }

        }
        if (!changes){
            if (o == btnMenuInicio) {
            	switchPanel(PanelInicio.class);
            	btnMenuInicio.setEnabled(false);

            } else if  (o == btnMenuTemporadas) {
            	switchPanel(PanelTemporadas.class);
            	btnMenuTemporadas.setEnabled(false);

            } else if  (o == btnMenuJugadores) {
            	switchPanel(PanelJugadores.class);
            	btnMenuJugadores.setEnabled(false);
            	
            } else if  (o == btnMenuEquipos) {
            	//switchPanel(PanelEquipos.class);
            	btnMenuEquipos.setEnabled(false);
            	
            }  else if  (o == btnMenuUsuarios) {
            	switchPanel(PanelUsuarios.class);
            	btnMenuUsuarios.setEnabled(false);
            	
            } else if (o == btnMenuSalir) {
            	int resultado = panelDeOpcion("Desea Cerrar Sesion?","Cerrar Sesion");
                // Check user's choice
                if (resultado == JOptionPane.YES_OPTION) {
                    Point location = getLocation(); // Obtener la posición actual
                    login loginFrame = new login(); // Crear el nuevo frame y pasa la variable userType para saber que funcionalidad podra usar cada usuario
                    loginFrame.setLocation(location); // Posicionar el nuevo frame en la misma ubicación
                    loginFrame.setVisible(true); // Mostrar el nuevo frame
                    dispose(); // Cerrar el frame actual (login)
                }

            	
            }

        }

	}

}
