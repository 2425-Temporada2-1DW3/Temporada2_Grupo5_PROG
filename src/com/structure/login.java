package com.structure;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
    private JLabel lblPassword;
    private JLabel lblUser;
    private JLabel lblTitulo;
    private JLabel lblError;
    private JTextField txtUser;
    private JPasswordField passwordField;
    private JButton btnLogin;

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

        lblPassword = new JLabel("Contraseña");
        lblPassword.setForeground(new Color(220, 220, 220));
        lblPassword.setFont(new Font("SansSerif", Font.PLAIN, 15));
        panel_3.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setForeground(new Color(220, 220, 220));
        passwordField.setBackground(new Color(50, 50, 50));
        passwordField.setColumns(15);
        panel_3.add(passwordField);

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

        panel_1 = new JPanel();
        panel_1.setBackground(new Color(50, 50, 50));
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new BorderLayout(0, 0));

        lblError = new JLabel("New label");
        lblError.setForeground(new Color(255, 53, 53));
        lblError.setFont(new Font("Consolas", Font.PLAIN, 15));
        panel_1.add(lblError);

        btnLogin = new JButton("Continuar");
        btnLogin.addActionListener(this);
        btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnLogin.setForeground(new Color(220, 220, 220));
        btnLogin.setBackground(new Color(50, 50, 50));
        panel_1.add(btnLogin, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        System.out.print("te");
        
        String user = lblUser.getText();
        
        
        if (o == btnLogin) {
        	
        	
            Point location = getLocation(); // Coje poscion actual
            main mainFrame = new main(); // Crea el nuevo frame
            mainFrame.setLocation(location); // hace que el nuevo frame este en la posicion actual del frame antiguo
            mainFrame.setVisible(true); // muestra el nuevo frame

            dispose(); // destroza el frame antiguo (login)
        }
    }
}
