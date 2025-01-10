package com.structure;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.logic.Usuario;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.*;

public class login extends JFrame implements ActionListener {


    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel;
    private JPanel panel_2;
    private JPanel panel_3;
    private JPanel panel_4;
    private JPanel panel_5;
    private JPanel panel_6;
    private JPanel panel_1;
    private JLabel imgLogo;
    private JLabel lblPass;
    private JLabel lblUser;
    private JLabel lblTitulo;
    private JLabel lblError;
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private Usuario[] users = 	{
    		new Usuario("Usuario", 0, "Usuario"), 
    		new Usuario("Arbitro",1,"Arbitro") , 
    		new Usuario("Admin",2,"Admin")  
    };
    private int userType;
    
    
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    login frame = new login();
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
    public login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(50, 50, 50));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        panel = new JPanel();
        panel.setBackground(new Color(50, 50, 50));
        contentPane.add(panel, BorderLayout.NORTH);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        imgLogo = new JLabel("");
        imgLogo.setBackground(new Color(240, 240, 240));
        imgLogo.setIcon(new ImageIcon(login.class.getResource("/com/resources/logo_java.png")));
        panel.add(imgLogo);

        panel_2 = new JPanel();
        panel_2.setBackground(new Color(50, 50, 50));
        contentPane.add(panel_2, BorderLayout.CENTER);
        panel_2.setLayout(new BorderLayout(0, 0));

        panel_3 = new JPanel();
        panel_3.setBackground(new Color(50, 50, 50));
        panel_2.add(panel_3, BorderLayout.SOUTH);
        panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblPass = new JLabel("Contraseña");
        lblPass.setForeground(new Color(220, 220, 220));
        lblPass.setFont(new Font("SansSerif", Font.PLAIN, 15));
        panel_3.add(lblPass);

        txtPass = new JPasswordField();
        txtPass.setForeground(new Color(220, 220, 220));
        txtPass.setBackground(new Color(50, 50, 50));
        txtPass.setColumns(15);
        panel_3.add(txtPass);

        panel_4 = new JPanel();
        panel_4.setBackground(new Color(50, 50, 50));
        panel_2.add(panel_4, BorderLayout.CENTER);
        panel_4.setLayout(new BorderLayout(0, 0));

        panel_5 = new JPanel();
        panel_5.setBackground(new Color(50, 50, 50));
        panel_4.add(panel_5, BorderLayout.SOUTH);
        panel_5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        lblUser = new JLabel("Usuario     ");
        lblUser.setForeground(new Color(220, 220, 220));
        lblUser.setFont(new Font("SansSerif", Font.PLAIN, 15));
        panel_5.add(lblUser);

        txtUser = new JTextField();
        txtUser.setForeground(new Color(220, 220, 220));
        txtUser.setBackground(new Color(50, 50, 50));
        panel_5.add(txtUser);
        txtUser.setColumns(15);

        panel_6 = new JPanel();
        panel_6.setBackground(new Color(50, 50, 50));
        panel_4.add(panel_6, BorderLayout.NORTH);

        lblTitulo = new JLabel("Federacion de Voleibol");
        lblTitulo.setForeground(new Color(0, 108, 128));
        lblTitulo.setFont(new Font("Century Gothic", Font.BOLD, 24));
        panel_6.add(lblTitulo);
        
        lblError = new JLabel();
        panel_4.add(lblError, BorderLayout.CENTER);
        lblError.setForeground(new Color(255, 53, 53));
        lblError.setFont(new Font("Consolas", Font.PLAIN, 11));

        panel_1 = new JPanel();
        panel_1.setBackground(new Color(50, 50, 50));
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        btnLogin = new JButton("Continuar");
        btnLogin.addActionListener(this);
        btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnLogin.setForeground(new Color(220, 220, 220));
        btnLogin.setBackground(new Color(50, 50, 50));
        panel_1.add(btnLogin, BorderLayout.EAST);
        
        // Creacion de los tres usuarios

  

    }
    private boolean userPassCheck(String u, String p) {
    	
    	for (int i = 0; i < users.length; i++) {  
    		
        	if (u.equals(users[i].getUser()) && p.equals(users[i].getPassword()) ) {
        		userType = users[i].getType();
            	return true;
        	} 
    	}
    	return false;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        
        String gui_user = txtUser.getText();
        char[] gui_pass_char = txtPass.getPassword();
        String gui_pass = new String(gui_pass_char);

        if (o == btnLogin) {
            if (gui_user.isBlank() && gui_pass.isBlank()) { 
            	
                lblError.setText("Por favor, introduzca su nombre de usuario y contraseña.");
                
            } else if (gui_user.isBlank()) { 
            	
                lblError.setText("El campo de nombre de usuario no puede estar vacío.");

            } else if (gui_pass.isBlank()) { 
            	
                lblError.setText("El campo de contraseña no puede estar vacío.");

            } else if (userPassCheck(gui_user, gui_pass)) { 
            	
                Point location = getLocation(); // Obtener la posición actual
                main mainFrame = new main(userType); // Crear el nuevo frame
                mainFrame.setLocation(location); // Posicionar el nuevo frame en la misma ubicación
                mainFrame.setVisible(true); // Mostrar el nuevo frame
                dispose(); // Cerrar el frame actual (login)

            } else { 
            	
                lblError.setText("Nombre de usuario o contraseña incorrectos.");
                
            }
        }

        	
        }

}