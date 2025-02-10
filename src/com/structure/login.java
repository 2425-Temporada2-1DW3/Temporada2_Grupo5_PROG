package com.structure;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.logic.Log;
import com.logic.Usuario;

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
	private ArrayList<Usuario> users; // Cambiar a ArrayList
	private int userType;
	private JButton btnLoginAnonimo;
	private Log log = new Log();
	private File userFile;
	private File temporadasFile;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
					// Centrar la ventana en la pantalla
                    frame.setLocationRelativeTo(null);  // null indica que la ventana debe centrarse en la pantalla

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
		log.add("Aplicación iniciada", 0);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
        setResizable(false);
		setTitle("Login de la Federacion de Voleivol");

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
		txtPass.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            btnLogin.doClick(); // Simula un clic en el botón de login
		        }
		    }
		});
		
		
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
		txtUser.addKeyListener(new KeyAdapter() {
		    @Override
		    public void keyPressed(KeyEvent e) {
		        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		            btnLogin.doClick(); // Simula un clic en el botón de login
		        }
		    }
		});
		
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

		btnLogin = new JButton("Continuar");
		btnLogin.addActionListener(this);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnLogin.setForeground(new Color(220, 220, 220));
		btnLogin.setBackground(new Color(50, 50, 50));
		panel_1.add(btnLogin);

		btnLoginAnonimo = new JButton("Login Anonimo");
		btnLoginAnonimo.addActionListener(this);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnLoginAnonimo.setFont(new Font("SansSerif", Font.PLAIN, 13));
		btnLoginAnonimo.setForeground(new Color(220, 220, 220));
		btnLoginAnonimo.setBackground(new Color(50, 50, 50));
		btnLoginAnonimo.setVisible(false);
		panel_1.add(btnLoginAnonimo);

		users = new ArrayList<>(); // Inicializamos el ArrayList de usuarios
		cargarUsuarios(); // Cargamos los usuarios desde el archivo
	}

	public boolean userPassCheck(String u, String p) {
		// Recorrimos el ArrayList con un foreach o utilizando el índice
		for (Usuario user : users) {
			if (user.checkUser(u, p)) {
				userType = user.getType(); // Obtenemos el tipo de usuario
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
				log.add("Intento de login fallido : nombre de usuario y contrseña no introducida", 1);

			} else if (gui_user.isBlank()) {
				lblError.setText("Por favor, introduzca su nombre de usuario.");
				log.add("Intento de login fallido : nombre de usuario no introducido", 1);

			} else if (gui_pass.isBlank()) {
				lblError.setText("Por favor, introduzca su contraseña.");
				log.add("Intento de login fallido : contrseña no introducida", 1);

			} else if (userPassCheck(gui_user, gui_pass)) {
				Point location = getLocation();
				main mainFrame = new main(userType, gui_user, this);
			    mainFrame.setLoginInstance(this); // Pasa la instancia de login a main
				mainFrame.setLocation(location);
				// Centrar la ventana en la pantalla
                mainFrame.setLocationRelativeTo(null);  // null indica que la ventana debe centrarse en la pantalla

				mainFrame.setVisible(true);
				log.add("Intento de login exitoso, Usuario : " + gui_user + " Tipo de usuario: " + userType
						+ ". Abriendo ventana main", 1);

				dispose();
			} else {
				lblError.setText("Nombre de usuario o contraseña incorrectos.");
				log.add("Intento de login fallido, Usuario : " + gui_user
						+ ", Nombre de usuario o contraseña incorrectos", 1);

			}
		} else if (o == btnLoginAnonimo) {
			Point location = getLocation();
			main mainFrame = new main(0, "Anonimo", this);
//		    mainFrame.setLoginInstance(this); // Pasa la instancia de login a main
			mainFrame.setLocation(location);
			// Centrar la ventana en la pantalla
            mainFrame.setLocationRelativeTo(null);  // null indica que la ventana debe centrarse en la pantalla

			mainFrame.setVisible(true);
			dispose();

		}
	}
	
	public void cargarUsuarios() {
	    // Determine file paths
	    File jarDir = new File(System.getProperty("user.dir"));
	    File resourcesFolder = new File(jarDir, "resources");
	    userFile = new File(resourcesFolder, "usuario.ser");

	    // Check if usuario.ser exists
	    if (!userFile.exists()) {
 	        inicializarApp(resourcesFolder); // Create necessary files
	    }

	    // Load users from usuario.ser
	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
	        while (true) {
	            try {
	                Usuario user = (Usuario) ois.readObject();
	                users.add(user);
	            } catch (EOFException ex) {
	                break; // End of file reached
	            }
	        }
	        lblError.setText("Usuarios cargados exitosamente.");
	    } catch (IOException | ClassNotFoundException ex) {
	        lblError.setText("Error al cargar usuarios: " + ex.getMessage());
	        log.add("Error al cargar usuarios: " + ex.getMessage(),2);
	        ex.printStackTrace();
	    }
	}

	private void inicializarApp(File resourcesFolder) {
	    // Ensure resources folder exists
	    if (!resourcesFolder.exists()) {
	        if (resourcesFolder.mkdirs()) {
	        	 log.add("Carpeta 'resources' creada exitosamente.",0);
	        } else {
	        	log.add("Error al crear la carpeta 'resources'",2);
	            return;
	        }
	    }

	    // Create usuario.ser with default user
	    try (FileOutputStream fos = new FileOutputStream(userFile);
	         ObjectOutputStream oos = new ObjectOutputStream(fos)) {
	        oos.writeObject(new Usuario("director", 4, "director"));
	        log.add("Archivo usuario.ser creado exitosamente en: " + userFile.getAbsolutePath(),0);

	        
	    } catch (IOException e) {
	        lblError.setText("Error al crear usuario.ser: " + e.getMessage());
	        e.printStackTrace();
	    }

 
	}
	// Getters y Setters de lista de usuarios para la Gestion en PanelUsuarios
	public ArrayList<Usuario> getUsers(){
		return users;
	}
	public void setUsers(ArrayList<Usuario> updateUsers){
		this.users = updateUsers;
	}
	public String getLblErrorText() {
	    return lblError.getText();
	}
}
