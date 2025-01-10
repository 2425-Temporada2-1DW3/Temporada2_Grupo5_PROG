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
<<<<<<< Updated upstream
    private Usuario[] users = 	{
    		new Usuario("Usuario", 0, "Usuario"), 
    		new Usuario("Arbitro",1,"Arbitro") , 
    		new Usuario("Admin",2,"Admin")  
    };
    private int userType;
=======
    
    // Los usuarios usados para el login
    private Usuario[] users = 	{
    		new Usuario("Unai2006", 0, "sapo"), 
    		new Usuario("TxemaDeMiguel",2,"alturron") , 
    		new Usuario("MikelResa",1,"soyunpro2006")  
    };
    
    private int userType;// no se usa aqui, solo esta definida para pasar el dato a la clase main
>>>>>>> Stashed changes
    
    
    
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
        
<<<<<<< Updated upstream
        lblError = new JLabel();
=======
        lblError = new JLabel(); // Creo el label sin nada porque solo se mostrara texto dentro de ello si hay un error
>>>>>>> Stashed changes
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
        
<<<<<<< Updated upstream
        // Creacion de los tres usuarios

=======
        //  Esto Solo Sirve para mostrar los nombres y contraseñas del login, luego borraremos esto
        String listaDeUsuarios = "";
        JFrame secondFrame = new JFrame("Usuarios");  
        secondFrame.setSize(1200, 60);  
        secondFrame.setLocationRelativeTo(null);  
    	for (int i = 0; i < users.length; i++) {  
    		listaDeUsuarios = listaDeUsuarios + "  |  " + users[i];
    	}
        JLabel label = new JLabel(listaDeUsuarios);  
        secondFrame.add(label);  

        secondFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
        secondFrame.setVisible(true);
>>>>>>> Stashed changes
  

    }
    private boolean userPassCheck(String u, String p) {
<<<<<<< Updated upstream
    	
    	for (int i = 0; i < users.length; i++) {  
    		
        	if (u.equals(users[i].getUser()) && p.equals(users[i].getPassword()) ) {
        		userType = users[i].getType();
            	return true;
        	} 
    	}
    	return false;
=======
    	/* Una funcion que Checkea todo el array users[] y comprueva si el usuario y contraseña son correctos 
    	 *  Si el check es valido devuelve true, si no es, devuelve false*/
    	for (int i = 0; i < users.length; i++) {  
    		
        	if (users[i].checkUser(u, p) ) {
        		userType = users[i].getType(); // coje el tipo de usuario para luego usarse en la clase main
            	return true;
        	} 

    	}
		System.out.println("falsificacion");

    	return false;

>>>>>>> Stashed changes
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Object o = ae.getSource();
        
<<<<<<< Updated upstream
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
=======
        /*gui == Graphical user interface
        * uso gui porque estas variables son valores de la interfaz
        * uso este nombre para no confundirlo con otras variables con nombres parecidos*/
        String gui_user = txtUser.getText();
        
		/*txtPass.getText() esta obsoleta y se recomienda usar .getPassword()
		*pero devuelve un array de caracteres por lo que tengo que convertirlo a un String de nuevo*/
        char[] gui_pass_char = txtPass.getPassword(); 
        String gui_pass = new String(gui_pass_char);

        if (o == btnLogin) {
        	// Uso un if anidado para no repetir la condicion  o == btnLogin y si se necesitaran mas actionlisteners se entendera mas facilmente
        	
            if 		  (gui_user.isBlank() && gui_pass.isBlank()) { // 2 Campos Vacios
                lblError.setText("Por favor, introduzca su nombre de usuario y contraseña.");
                
            } else if (gui_user.isBlank()) { // Usuario Vacio      	
                lblError.setText("El campo de nombre de usuario no puede estar vacío.");

            } else if (gui_pass.isBlank()) {  // Contraseña Vacia
            	lblError.setText("El campo de contraseña no puede estar vacío.");

            } else if (userPassCheck(gui_user, gui_pass)) { // Si usr y pass esta correcta
            	
                Point location = getLocation(); // Obtener la posición actual
                main mainFrame = new main(userType); // Crear el nuevo frame y pasa la variable userType para saber que funcionalidad podra usar cada usuario
>>>>>>> Stashed changes
                mainFrame.setLocation(location); // Posicionar el nuevo frame en la misma ubicación
                mainFrame.setVisible(true); // Mostrar el nuevo frame
                dispose(); // Cerrar el frame actual (login)

<<<<<<< Updated upstream
            } else { 
            	
=======
            } else { // Si usr y/o pass es incorrecta            	
>>>>>>> Stashed changes
                lblError.setText("Nombre de usuario o contraseña incorrectos.");
                
            }
        }

        	
        }

}
