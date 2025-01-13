package com.structure;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
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
    private JButton btnMenuUsuarios = new JButton("USUARIOS");
    private JButton btnMenuPartidos = new JButton("PARTIDOS");
    private JButton btnMenuSalir = new JButton("CERRAR SESION");
    JButton[] buttons = {btnMenuInicio, btnMenuTemporadas, btnMenuJugadores, btnMenuUsuarios, btnMenuPartidos,btnMenuSalir};
    
	private JPanel contentPane = new JPanel();
	private JPanel LayoutPanel = new JPanel();
	private JPanel LayoutPanel_1 = new JPanel();


	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int userType = 0; // Tipo de usuario por defecto si no recibe un valor (
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
    	// Logica de texto de Titulo
        this.userType = userType; // coje la variable de la clase login y la pasa a una variable definida en la clase main
        
        if (userType == 0 ) {
        	userTypeName = "Usuarios";
        } else if (userType == 1) {
        	userTypeName = "Entrenadores";
        } else {
        	userTypeName = "Administradores";
        }
        
        setTitle("Portal de "+ userTypeName +" la Federacion de Voleivol");
        
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
		
		for (JButton button : buttons) {
		    button.setFont(new Font("SansSerif", Font.BOLD, 16));
		    button.addActionListener(this);
		    button.setForeground(colortxt);
		    button.setBackground(colorbg);
		    LayoutPanel.add(button);
		}
		

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
	@Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
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
        	
        }
	}

}
