package com.structure;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private int userType;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    int userType = 0; // Default user type or retrieve it from a source
                    main frame = new main(userType); // Pass userType
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
        this.userType = userType;
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
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
