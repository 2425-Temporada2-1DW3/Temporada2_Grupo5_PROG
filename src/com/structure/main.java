package com.structure;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;

public class main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private int userType; 
    private String userTypeName;// No usar para comparaciones, solo para mostrar el tipo de usuario visualmente si es necesitado
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
        this.userType = userType; // coje la variable de la clase login y la pasa a una variable definida en la clase main
        
        if (userType == 0 ) {
        	userTypeName = "Usuarios";
        } else if (userType == 1) {
        	userTypeName = "Entrenadores";
        } else {
        	userTypeName = "Administradores";
        }
        
        setTitle("Portal de "+ userTypeName +" la Federacion de Voleivol");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
        // Todo este codigo de aqui es temporal y luego se tendra que eliminar

		JLabel lblNewLabel = new JLabel("Me identifico como un proyecto 100% completado");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel_1 = new JLabel("Tipo du usuareo :");
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(""+userType); 
		panel.add(lblNewLabel_2);
	}

}
