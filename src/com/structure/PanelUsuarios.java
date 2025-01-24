package com.structure;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import com.structure.main;
import java.io.*;
import java.util.ArrayList;

import com.logic.Usuario;

public class PanelUsuarios extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private DefaultListModel<Usuario> listModel;
    private JList<Usuario> userList;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbUserType;
    private JButton btnSaveChanges;
    private JButton btnDeleteAll;
    private JButton btnDelete ;
    private JButton btnCreate;
    private JButton btnModify;
    
    private JLabel lblTitle = new JLabel("Gestión de Usuarios");
    private JLabel lblUsername = new JLabel("Usuario:"); 

	private int userType;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	private main parentFrame;

	/**
	 * Create the panel.
	 */
	public PanelUsuarios(main parentFrame) {
		// guarda los valores fuera de esta funcion por si se necesitan acceder en otro sitio

		this.parentFrame = parentFrame;
	    userType = parentFrame.userType;
	    colorbg = parentFrame.colorbg;
	    colortxt = parentFrame.colortxt;
	    userName = parentFrame.userName;
	    parentFrame.changes = true;


        // Configura el panel principal
        setBackground(colorbg);

        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Crear título principal
       
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitle.setForeground(colortxt);
        add(lblTitle, BorderLayout.NORTH);

        // Panel de creación de usuarios
        add(createUserCreationPanel(), BorderLayout.WEST);

        // Panel de lista de usuarios
        add(createUserListPanel(), BorderLayout.CENTER);
    }

    private JPanel createUserCreationPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(colorbg);
        panel.setLayout(new GridLayout(9, 1, 10, 10));

        lblUsername.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUsername.setForeground(colortxt);
        panel.add(lblUsername);

        txtUsername = new JTextField();
        txtUsername.setFont(new Font("Arial", Font.PLAIN, 14));
        txtUsername.setBackground(colorbg);
        txtUsername.setForeground(colortxt);
        panel.add(txtUsername);

        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 16));
        lblPassword.setForeground(colortxt);
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        txtPassword.setFont(new Font("Arial", Font.PLAIN, 14));
        txtPassword.setBackground(colorbg);
        txtPassword.setForeground(colortxt);
        panel.add(txtPassword);

        JLabel lblUserType = new JLabel("Tipo de Usuario:");
        lblUserType.setFont(new Font("Arial", Font.PLAIN, 16));
        lblUserType.setForeground(colortxt);
        panel.add(lblUserType);

        cmbUserType = new JComboBox<>(new String[]{"Usuario", "Árbitro"});
        // para que los admins solo puedan crear admins
        if (userType == 2) {
            cmbUserType.setModel(new DefaultComboBoxModel<>(new String[]{"Usuario", "Árbitro"}));
        } else if (userType == 4) {
            cmbUserType.setModel(new DefaultComboBoxModel<>(new String[]{"Usuario", "Árbitro", "Gestor"}));
        }

        cmbUserType.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbUserType.setBackground(colorbg);
        cmbUserType.setForeground(colortxt);
        panel.add(cmbUserType);

         btnCreate = new JButton("Crear Usuario");
        btnCreate.setFont(new Font("Arial", Font.BOLD, 14));
        btnCreate.setBackground(new Color(34, 139, 34));
        btnCreate.setForeground(Color.WHITE);

        // Acción del botón "Crear Usuario"
        btnCreate.addActionListener(this);
        
         btnModify = new JButton("Modificar Usuario");
        btnModify.setFont(new Font("Arial", Font.BOLD, 14));
        btnModify.setBackground(new Color(255, 215, 0));  // Color de fondo amarillo
        btnModify.setForeground(Color.WHITE);
        btnModify.addActionListener(this);  // Acción del botón "Modificar Usuario"
        panel.add(btnModify);
        panel.add(btnCreate);
        return panel;
    }

    private JPanel createUserListPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(colorbg);

        JLabel lblListTitle = new JLabel("Lista de Usuarios");
        lblListTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblListTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblListTitle.setForeground(colortxt);
        panel.add(lblListTitle, BorderLayout.NORTH);

        // Crear lista de usuarios
        listModel = new DefaultListModel<>();
        userList = new JList<>(listModel);
        userList.setFont(new Font("Arial", Font.PLAIN, 17));
        userList.setBackground(colorbg);
        userList.setForeground(colortxt);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        cargarUsuarios();
        userList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                // Solo se ejecuta si el usuario realmente cambia
                if (!e.getValueIsAdjusting()) {
                    Usuario selectedUser = userList.getSelectedValue();
                    if (selectedUser != null) {
                        // Cargar los datos del usuario en los campos
	                	if (selectedUser.getType() ==4 && userType == 4) {
	                		// Seleciono superusuario y soy superusuario
	                    	btnModify.setEnabled(true);                        	
	                        txtUsername.setText(selectedUser.getUser());
	                        txtPassword.setText(selectedUser.getPass());                            
	                    	cmbUserType.setEnabled(false);
	                        cmbUserType.setSelectedIndex(0);
	                    	
	                    } else if (selectedUser.getType() == 4) {
	                    	// Seleciono superusuario y no soy superusuario
	                    	btnModify.setEnabled(false);                        	
	                        txtUsername.setText("");
	                        txtPassword.setText("");                            
	                        cmbUserType.setSelectedIndex(0);
	                    	cmbUserType.setEnabled(false);
	
	
	                    } else if (selectedUser.getType() == 2 && userType == 2) {
	                    	// Seleciono admin siendo admin
	                    	btnModify.setEnabled(false);                        	
	                        txtUsername.setText("");
	                        txtPassword.setText("");                            
	                        cmbUserType.setSelectedIndex(0);
	                    	cmbUserType.setEnabled(false);
	
	                    } else {
	                    	// Seleciono cualquier otra cosa
	                    	btnModify.setEnabled(true);    
	                        txtUsername.setText(selectedUser.getUser());
	                        txtPassword.setText(selectedUser.getPass());                        	
	                    	cmbUserType.setEnabled(true);
	                        cmbUserType.setSelectedIndex(selectedUser.getType());
	
	                    }
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(userList);
        scrollPane.setBorder(BorderFactory.createLineBorder(colortxt, 2));
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel para botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(colorbg);

        // Botón para eliminar usuario seleccionado (más ancho)
        btnDelete = new JButton("Eliminar Usuario");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
        btnDelete.setBackground(new Color(220, 20, 60));
        btnDelete.setForeground(Color.WHITE);

        // Acción del botón "Eliminar Usuario"
        btnDelete.addActionListener(this);

        // Botón para eliminar todos los usuarios (menos ancho)
        btnDeleteAll = new JButton("Eliminar Todos");
        btnDeleteAll.setFont(new Font("Arial", Font.BOLD, 12));
        btnDeleteAll.setBackground(new Color(255, 69, 0));
        btnDeleteAll.setForeground(Color.WHITE);
        btnDeleteAll.setPreferredSize(new Dimension(120, 30));

        // Acción del botón "Eliminar Todos"
        btnDeleteAll.addActionListener(this);

        // Botón para guardar cambios
        btnSaveChanges = new JButton("Guardar Cambios");
        btnSaveChanges.setFont(new Font("Arial", Font.BOLD, 14));
        btnSaveChanges.setBackground(new Color(70, 130, 180));
        btnSaveChanges.setForeground(Color.WHITE);
        btnSaveChanges.setPreferredSize(new Dimension(160, 30));

        // Acción del botón "Guardar Cambios"
        btnSaveChanges.addActionListener(this);;

        buttonPanel.add(btnDelete); // Botón grande
        buttonPanel.add(btnDeleteAll); // Botón pequeño
        buttonPanel.add(btnSaveChanges); // Botón guardar cambios

        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }


    

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if (o == btnSaveChanges) {
			actualizarArchivo();
		}
		else if(o == btnDeleteAll) {
			eliminarTodosUsuarios();
		}else if(o==btnDelete) {
			eliminarUsuario();
		}else if(o==btnCreate) {
			crearUsuario();
		}else if(o==btnModify) {
			modificarUsuario();
		}
		
	}
	private void crearUsuario() {
		
        String username = txtUsername.getText().trim();
        int userType = cmbUserType.getSelectedIndex();
        char[] passwordChars = txtPassword.getPassword();
        String pass = new String(passwordChars);
        
        if (username.isEmpty() || pass.isEmpty()) {
            parentFrame.mensaje("Por favor, ingrese un nombre de usuario válido.",0);
            return;
        }

        // Check if the user already exists
        boolean userExists = false;
        for (int i = 0; i < listModel.size(); i++) {
            Usuario existingUser = listModel.getElementAt(i);
            if (existingUser.getUser().equalsIgnoreCase(username)) {
                userExists = true;
                break;
            }
        }

        if (userExists) {
            parentFrame.mensaje("El usuario ya existe. Por favor, elija otro nombre de usuario.",0);
        } else {
            // Create a new user
            Usuario user = new Usuario(username, userType, pass);
            listModel.addElement(user);
            txtUsername.setText("");
            txtPassword.setText("");
            parentFrame.changes = true;
            parentFrame.mensaje("Usuario creado exitosamente.",2);
        }
        
	}
	private void eliminarUsuario() {
		 int selectedIndex = userList.getSelectedIndex();
		 if(listModel.elementAt(selectedIndex).getType() == 4) {
			 parentFrame.mensaje("El usuario del Director no se puede eliminar.",1);
 
		 } else if (selectedIndex != -1) {
             listModel.remove(selectedIndex);
             parentFrame.changes = true;
         } else {
        	 parentFrame.mensaje("Por favor, seleccione un usuario para eliminar.",2);
         }
	}
	
	private void eliminarTodosUsuarios() {
		if (!listModel.isEmpty()) {
            int confirmation = JOptionPane.showConfirmDialog(
                    null,
                    "¿Está seguro de que desea eliminar todos los usuarios?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                Usuario superuser = null;
                
                // Iterate through the list and find the superuser
                for (int i = 0; i < listModel.size(); i++) {
                    if (listModel.get(i).getType() == 4) {
                        superuser = listModel.get(i);
                        break;  // Exit the loop as we found the superuser
                    }else {
                    	parentFrame.changes = true; 
                	}
                }
                
                // Clear the list
                listModel.removeAllElements();
                
                // If there was a superuser, add it back
                if (superuser != null) {
                    listModel.addElement(superuser);
                
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "No hay usuarios para eliminar.");
        }
            }
	}
	
	private void actualizarArchivo() {
    	if (parentFrame.changes == true) {
    		try (FileOutputStream fos = new FileOutputStream("usuario.ser");
                    ObjectOutputStream oos = new ObjectOutputStream(fos)){
        		int length =listModel.getSize();
        		int counter = 0;
        		while (counter <length) {
        			oos.writeObject(listModel.getElementAt(counter));
        			counter ++;
        		}
        		parentFrame.mensaje("Cambios guardados.",2);
        		parentFrame.changes = false;
    			
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	else {
    		parentFrame.mensaje("No hay cambios",1);
    	}
    	
    }
	
	private void cargarUsuarios() {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuario.ser"))) {
            while (true) {
                try {
                    Usuario user = (Usuario) ois.readObject();
                    listModel.addElement(user);
                } catch (EOFException ex) {
                    break; // Fin del archivo
                }
            }
        } catch (FileNotFoundException ex) {
    		parentFrame.mensaje("No se encontró el archivo de usuarios. Se creará uno nuevo al guardar cambios.",1);
        } catch (IOException | ClassNotFoundException ex) {
        	parentFrame.mensaje("Error al cargar usuarios: " + ex.getMessage(),0);
        }
	}
	
	private void modificarUsuario() {
	    // Obtener el índice del usuario seleccionado en la lista
	    int selectedIndex = userList.getSelectedIndex();
	
	    // Verificar si hay un usuario seleccionado
	    if (selectedIndex == -1) {
	        parentFrame.mensaje("Por favor, seleccione un usuario para modificar.",0);
	        return;
	    }
	
	    // Obtener el usuario seleccionado
	    Usuario selectedUser = listModel.getElementAt(selectedIndex);
	
	    // Obtener los nuevos valores desde los campos de texto
	    String newUsername = txtUsername.getText().trim();
	    char[] newPasswordChars = txtPassword.getPassword();
	    String newPass = new String(newPasswordChars);
	    int newUserType = cmbUserType.getSelectedIndex();
	    
	    // Validar los nuevos valores ingresados
	    if (newUsername.isEmpty() && newPass.isEmpty()) {
	        parentFrame.mensaje("Por favor, ingrese un nombre de usuario y contraseña válidos.",0);
	        return;
	    } else if (newUsername.isEmpty()) {
	        parentFrame.mensaje("Por favor, ingrese un nombre de usuario válido.",0);
	        return;
	    } else if (newPass.isEmpty()) {
	        parentFrame.mensaje("Por favor, ingrese una contraseña válida.",0);
	        return;
	    }
	    Usuario selectedUserChanges = new Usuario (newUsername,newUserType,newPass);
	    
	    if (selectedUser.equals(selectedUserChanges)) {
	        parentFrame.mensaje("No hay cambios.",1);
	        return;	
	    }
	    // Modificar los atributos del usuario seleccionado
	    selectedUser.setUser(newUsername); 
	    selectedUser.setPass(newPass);
	    selectedUser.setType(newUserType);
	
	    // Actualizar el modelo de la lista con el usuario modificado
	    listModel.setElementAt(selectedUser, selectedIndex);
	
	    // Opcional: actualizar la vista
	    userList.repaint();
	
	    // Marcar cambios como realizados
	    parentFrame.changes = true;
	
	    // Actualizar archivo persistente, si corresponde
	    actualizarArchivo();
	
	    // Mostrar un mensaje de éxito
	    parentFrame.mensaje("Usuario modificado correctamente.",2);
	}

}

