package com.structure;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class main extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
;
    private int userType; 
    private String userTypeName;// No usar para comparaciones, solo para mostrar el tipo de usuario visualmente si es necesitado
    private Color colorbg = new Color(50, 50, 50);
    private Color colortxt = new Color(220, 220, 220);
    
    private JButton btnMenuInicio = new JButton("INICIO");
    private JButton btnMenuTemporadas = new JButton("TEMPORADAS");
    private JButton btnMenuJugadores = new JButton("JUGADORES");
    private JButton btnMenuUsuarios = new JButton("GESTION USUARIOS");
    private JButton btnMenuPartidos = new JButton("PARTIDOS");
    private JButton btnMenuSalir = new JButton("CERRAR SESION");
    JButton[] buttons = {btnMenuInicio, btnMenuTemporadas, btnMenuJugadores, btnMenuUsuarios, btnMenuPartidos,btnMenuSalir};
    
	private JPanel contentPane = new JPanel();
	private JPanel LayoutPanel = new JPanel();
	private JPanel LayoutPanel_1 = new JPanel();
	public static boolean changes= false;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int userType = 2; // Tipo de usuario por defecto si no recibe un valor (
                    main frame = new main(userType); // Pasa usertype a la main
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


	/**
	 * Create the frame. 
	 */
    public main(int userType) {
        this.userType = userType; // coje la variable de la clase login y la pasa a una variable definida en la clase main
        

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
		JLabel lblError = new JLabel();
		lblError.setForeground(colortxt);
		lblError.setBackground(colorbg);
		LayoutPanel_1.add(lblError, BorderLayout.NORTH);
		
		
		
		// Generar los 5 Botones de menu
		if (userType == 2) {
			for (JButton button : buttons) {
				buttonCreate(button);
			}
		} else if (userType == 1){
			buttonCreate(btnMenuInicio);
			buttonCreate(btnMenuTemporadas);
			buttonCreate(btnMenuPartidos);
			buttonCreate(btnMenuSalir);

		} else {
			buttonCreate(btnMenuSalir);
		}
		
		// Cambia el titulo de la pagina y carga el menu apropiado para cada tipo de usuario
        if (userType == 0 ) {
        	userTypeName = "Usuarios";
        	switchPanel(new PanelInicio(colorbg,colortxt,userType));
        	btnMenuInicio.setEnabled(false);

        } else if (userType == 1) {
        	userTypeName = "Entrenadores";
        	switchPanel(new PanelPartidos(colorbg,colortxt,userType));
        	btnMenuPartidos.setEnabled(false);
        	
        } else {
        	userTypeName = "Administradores";
        	switchPanel(new PanelUsuarios(colorbg,colortxt,userType));
        	btnMenuUsuarios.setEnabled(false);
        	
        }
        setTitle("Portal de "+ userTypeName +" la Federacion de Voleivol");
	}
    
    // Funcion para crear todos los botones del menu
    public void buttonCreate(JButton button) { 
	    button.setFont(new Font("SansSerif", Font.BOLD, 16));
	    button.addActionListener(this);
	    button.setForeground(colortxt);
	    button.setBackground(colorbg);
	    LayoutPanel.add(button);
    }
    
    
    public void switchPanel(JPanel panel) {
    	LayoutPanel_1.removeAll();
    	LayoutPanel_1.add(panel, BorderLayout.CENTER);
        revalidate();
        repaint();
        
		for (JButton button : buttons) {
		    button.setEnabled(true);
		}
    }
    
    public int panelDeOpcion(String mensaje, String titulo) {
    	UIManager.put("Panel.background", colorbg);
    	UIManager.put("OptionPane.background", colorbg);
    	UIManager.put("OptionPane.messageForeground", colortxt);
    	UIManager.put("Button.background", colorbg);
    	UIManager.put("Button.foreground", colortxt);

        int result = JOptionPane.showConfirmDialog(
        		
                main.this,
                mensaje,
                titulo,
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        return result;
    }
	@Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        if (changes) {
        	int resultado =panelDeOpcion( "Hay Datos Sin Guardar, Quieres salir?","Datos Sin Guardar");
            // Check user's choice
            if (resultado == JOptionPane.YES_OPTION) {
            	changes = false;
            }

        }
        if (!changes){
            if (o == btnMenuInicio) {
            	switchPanel(new PanelInicio(colorbg,colortxt,userType));
            	btnMenuInicio.setEnabled(false);

            } else if  (o == btnMenuTemporadas) {
            	switchPanel(new PanelTemporadas(colorbg,colortxt,userType));
            	btnMenuTemporadas.setEnabled(false);

            } else if  (o == btnMenuJugadores) {
            	switchPanel(new PanelJugadores(colorbg,colortxt,userType));
            	btnMenuJugadores.setEnabled(false);
            	
            } else if  (o == btnMenuUsuarios) {
            	switchPanel(new PanelUsuarios(colorbg,colortxt,userType));
            	btnMenuUsuarios.setEnabled(false);
            	
            } else if  (o == btnMenuPartidos) {
            	switchPanel(new PanelPartidos(colorbg,colortxt,userType));
            	btnMenuPartidos.setEnabled(false);        	
            	
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
