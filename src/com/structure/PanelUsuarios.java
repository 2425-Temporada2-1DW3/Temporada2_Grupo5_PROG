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
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PanelUsuarios extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private DefaultListModel<Usuario> listModel;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JComboBox<String> cmbUserType;
    private JButton btnSaveChanges = new JButton("Guardar Cambios");
    private JButton btnDeleteAll = new JButton("Eliminar Todos");
    private JButton btnDelete = new JButton("Eliminar Usuario");
    private JButton  btnCreate = new JButton("Crear Usuario");
    private JButton btnModify = new JButton("Modificar Usuario");
    private JLabel lblTitle = new JLabel("GESTION DE USUARIOS");
    private JLabel lblUsername = new JLabel("Usuario:"); 
	private int userType;
	private main parentFrame;
	private Color colorbg;
	private Color colortxt;
	private String userName;
	
	private ArrayList<Usuario> listUsers;
	private JTable userTable;
	private UserTableModel tableModel;
	
	public class UserTableModel extends AbstractTableModel {
	    private final String[] columnNames = { "Usuario", "Contraseña", "Tipo de Usuario" };
	    private ArrayList<Usuario> users;

	    public UserTableModel(ArrayList<Usuario> users) {
	        this.users = users;
	    }

	    @Override
	    public int getRowCount() {
	        return users.size();
	    }

	    @Override
	    public int getColumnCount() {
	        return columnNames.length;
	    }

	    @Override
	    public Object getValueAt(int rowIndex, int columnIndex) {
	        Usuario user = users.get(rowIndex);
	        switch (columnIndex) {
	            case 0: return user.getUser();
	            case 1: return "********"; // Para ocultar la contraseña en la tabla
	            case 2: 
	                switch (user.getType()) {
	                    case 0: return "Usuario";
	                    case 1: return "Árbitro";
	                    case 2: return "Gestor";
	                    case 4: return "Director";
	                    default: return "Desconocido";
	                }
	            default: return null;
	        }
	    }

	    @Override
	    public String getColumnName(int column) {
	        return columnNames[column];
	    }

	    @Override
	    public boolean isCellEditable(int rowIndex, int columnIndex) {
	        return false; // No editable desde la tabla
	    }

	    public Usuario getUserAt(int rowIndex) {
	        return users.get(rowIndex);
	    }

	    public void addUser(Usuario user) {
	        users.add(user);
	        fireTableRowsInserted(users.size() - 1, users.size() - 1);
	    }

	    public void removeUser(int rowIndex) {
	        users.remove(rowIndex);
	        fireTableRowsDeleted(rowIndex, rowIndex);
	    }

	    public void updateUser(int rowIndex, Usuario user) {
	        users.set(rowIndex, user);
	        fireTableRowsUpdated(rowIndex, rowIndex);
	    }
	    
	    public void setUsers(ArrayList<Usuario> users) {
	        this.users = users;
	        fireTableDataChanged();
	    }
	    public void removeAllUsers() {
	        users.clear();
	        fireTableDataChanged();
	    }
	}
	
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
	    
		// Cambia color del Jpanel
		setBackground(colorbg);
 
	    //LISTA USERS
	    this.listUsers = parentFrame.getUsers(); // Ahora obtiene la lista desde login a través de main


        // Configura el panel principal
 
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Crear título principal
       
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(parentFrame.fuenteHeader);
         add(lblTitle, BorderLayout.NORTH);

        // Panel de creación de usuarios
        add(createUserCreationPanel(), BorderLayout.WEST);

        // Panel de lista de usuarios
        add(createUserListPanel(), BorderLayout.CENTER);
    }

    private JPanel createUserCreationPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 1, 10, 10));

        panel.add(lblUsername);

        txtUsername = new JTextField();
        panel.add(txtUsername);

        JLabel lblPassword = new JLabel("Contraseña:");
        panel.add(lblPassword);

        txtPassword = new JPasswordField();
        panel.add(txtPassword);

        JLabel lblUserType = new JLabel("Tipo de Usuario:");
        panel.add(lblUserType);

        cmbUserType = new JComboBox<>(new String[]{"Usuario", "Árbitro"});
        // para que los admins solo puedan crear admins
        if (userType == 2) {
            cmbUserType.setModel(new DefaultComboBoxModel<>(new String[]{"Usuario", "Árbitro"}));
        } else if (userType == 4) {
            cmbUserType.setModel(new DefaultComboBoxModel<>(new String[]{"Usuario", "Árbitro", "Gestor"}));
        }


        panel.add(cmbUserType);

        parentFrame.buttonCreate(btnCreate, panel, parentFrame.colorGreen);
        parentFrame.buttonCreate(btnModify, panel, parentFrame.colorYellow);

        btnCreate.addActionListener(this);
        btnModify.addActionListener(this);  


        
        btnModify.setForeground(Color.WHITE);
        panel.add(btnModify);
        panel.add(btnCreate);
        return panel;
    }

    private JPanel createUserListPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JLabel lblListTitle = new JLabel("Lista de Usuarios");
        lblListTitle.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblListTitle, BorderLayout.NORTH);

        // Obtener la lista de usuarios desde login.java
        listUsers = parentFrame.getUsers();

        // Crear el modelo de la tabla
        tableModel = new UserTableModel(listUsers);
        userTable = new JTable(tableModel);
        userTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Formatear la tabla si tienes un método para eso (opcional)
		parentFrame.formatearTabla(userTable); 


        // Agregar listener para seleccionar usuario
        userTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = userTable.getSelectedRow();
                    if (selectedRow != -1) {
                        Usuario selectedUser = tableModel.getUserAt(selectedRow);
                        txtUsername.setText(selectedUser.getUser());
                        txtPassword.setText(selectedUser.getPass());

                     // Convertir el tipo de usuario en un índice válido en el JComboBox
                        int selectedIndex = 0; // Por defecto "Usuario"

                        if (selectedUser.getType() == 1) {
                            selectedIndex = 1; // "Árbitro"
                        } else if (selectedUser.getType() == 2 && userType == 4) {
                            selectedIndex = 2; // "Gestor" (solo visible para el Director)
                        }

                        // Evitar IndexOutOfBoundsException
                        if (selectedIndex < cmbUserType.getItemCount()) {
                            cmbUserType.setSelectedIndex(selectedIndex);
                        } else {
                            cmbUserType.setSelectedIndex(0); // Valor por defecto
                        }
                        // Deshabilitar el JComboBox si el usuario seleccionado es de tipo 4
                        cmbUserType.setEnabled(selectedUser.getType() != 4);



                        btnModify.setEnabled(true);
                        btnDelete.setEnabled(true);
                    }
                }
            }
        });
        
        
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.getViewport().setBackground(colorbg);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        userTable.getParent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userTable.clearSelection();
            }
        });

        
        // Panel para botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        parentFrame.buttonCreate(btnDelete, buttonPanel, parentFrame.colorRed);
        parentFrame.buttonCreate(btnDeleteAll, buttonPanel, parentFrame.colorRed);
        parentFrame.buttonCreate(btnSaveChanges, buttonPanel, parentFrame.colorBlue);

        btnDelete.addActionListener(this);
        btnDeleteAll.addActionListener(this);
        btnSaveChanges.addActionListener(this);

        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    
    private boolean validarPassword(String password) {
        for (Usuario user : listUsers) {
            if (user.checkUser(userName, password)) {
                return true; // Contraseña correcta
            }
        }
        return false; // Contraseña incorrecta
    }

    private boolean validarCredenciales(String u, String p) {
        // Recorrer la lista de usuarios para verificar credenciales
        for (Usuario user : listUsers) {
            if (user.checkUser(u, p)) {
                return true; // Contraseña correcta
            }
        }
        return false; // Contraseña incorrecta
    }

    
    private void confirmarGuardado() {
        // Pedir nombre de usuario y contraseña
        JTextField txtUser = new JTextField();
        JPasswordField txtPass = new JPasswordField();
        Object[] message = {
            "Usuario:", txtUser,
            "Contraseña:", txtPass
        };

        int option = JOptionPane.showConfirmDialog(
            this, 
            message, 
            "Confirmar Guardado", 
            JOptionPane.OK_CANCEL_OPTION
        );

        if (option == JOptionPane.OK_OPTION) {
            String inputUser = txtUser.getText().trim();
            String inputPass = new String(txtPass.getPassword());

            // Verificar usuario y contraseña
            if (validarCredenciales(inputUser, inputPass)) {
        	    parentFrame.changes = true;
                actualizarArchivo();  // Solo guarda si las credenciales son correctas
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



	@Override
	public void actionPerformed(ActionEvent e) {
		Object o =e.getSource();
		if (o == btnSaveChanges) {
			if(parentFrame.changes == true) {
				confirmarGuardado();
			}
			else {
				parentFrame.mensaje("No hay cambios registrados", 1);
			}
//			actualizarArchivo();
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
	        parentFrame.mensaje("Por favor, ingrese un nombre de usuario válido", 0);
	        return;
	    }

	    // Verificar si el usuario ya existe
	    for (Usuario user : listUsers) {
	        if (user.getUser().equalsIgnoreCase(username)) {
	            parentFrame.mensaje("El usuario ya existe. Por favor, elija otro nombre de usuario", 0);
	            return;
	        }
	    }

	    // Crear y agregar usuario
	    Usuario newUser = new Usuario(username, userType, pass);
	    listUsers.add(newUser);
//	    tableModel.addUser(newUser);
	    parentFrame.setUsers(listUsers); // ACTUALIZA LA LISTA GLOBAL EN LOGIN
	    parentFrame.changes = true;
	    actualizarArchivo();
	    actualizarUsuarios();
	    parentFrame.mensaje("Usuario creado correctamente", 2);
	}
	
	private void actualizarUsuarios() {
	    // Refrescar el modelo de la tabla con la nueva lista de usuarios
	    tableModel.setUsers(listUsers);
	    tableModel.fireTableDataChanged();
	}
	
	private void eliminarUsuario() {
	    int selectedRow = userTable.getSelectedRow();
	    if (selectedRow != -1) {
	        Usuario userToDelete = tableModel.getUserAt(selectedRow);
	        if (userToDelete.getType() == 4) {
	            parentFrame.mensaje("No se puede eliminar al Director", 1);
	            return;
	        }

	        tableModel.removeUser(selectedRow);
	        actualizarUsuarios();
	        parentFrame.mensaje("Usuario eliminado correctamente", 2);
	    } else {
	        parentFrame.mensaje("Seleccione un usuario para eliminar", 0);
	    }
	}

	private void eliminarTodosUsuarios() {
	    int confirmation = JOptionPane.showConfirmDialog(
	            null,
	            "¿Está seguro de que desea eliminar todos los usuarios?",
	            "Confirmación",
	            JOptionPane.YES_NO_OPTION
	    );

	    if (confirmation == JOptionPane.YES_OPTION) {
	    	listUsers.removeIf(user -> user.getType() != 4);
	        tableModel.removeAllUsers();

	        // Agregar de nuevo el Director
	        for (Usuario user : listUsers) {
	            if (user.getType() == 4) {
	                tableModel.addUser(user);
	                break;
	            }
	        }

	        actualizarUsuarios();
	        parentFrame.mensaje("Todos los usuarios eliminados excepto el Director", 2);
	    }
	}

	private void modificarUsuario() {
	    int selectedRow = userTable.getSelectedRow();
	    if (selectedRow == -1) {
	        parentFrame.mensaje("Seleccione un usuario para modificar", 0);
	        return;
	    }

	    Usuario selectedUser = tableModel.getUserAt(selectedRow);
	    String newUsername = txtUsername.getText().trim();
	    char[] newPasswordChars = txtPassword.getPassword();
	    String newPass = new String(newPasswordChars);
	    int newUserType = cmbUserType.getSelectedIndex();

	    if (newUsername.isEmpty() || newPass.isEmpty()) {
	        parentFrame.mensaje("Ingrese un usuario y contraseña válidos", 0);
	        return;
	    }

	    selectedUser.setUser(newUsername);
	    selectedUser.setPass(newPass);
	    selectedUser.setType(newUserType);

	    tableModel.updateUser(selectedRow, selectedUser);
	    actualizarUsuarios();
	    parentFrame.mensaje("Usuario modificado correctamente", 2);
	}

	
	private void actualizarArchivo() {
	    if (parentFrame.changes) {
	        try (FileOutputStream fos = new FileOutputStream(parentFrame.userFile);
	             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
	            
	            for (Usuario user : listUsers) {
	                oos.writeObject(user);
	            }

	            parentFrame.mensaje("Cambios guardados", 2);
	            parentFrame.changes = false;
	        } catch (IOException e) {
	            e.printStackTrace();
	            parentFrame.mensaje("Error al guardar los usuarios", 0);
	        }
	    } else {
	        parentFrame.mensaje("No hay cambios", 1);
	    }
	}

	
	private void cargarUsuarios() {

		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(parentFrame.userFile))) {
            while (true) {
                try {
                    Usuario user = (Usuario) ois.readObject();
                    listModel.addElement(user);
                } catch (EOFException ex) {
                    break; // Fin del archivo
                }
            }
        } catch (FileNotFoundException ex) {
    		parentFrame.mensaje("No se encontró el archivo de usuarios. Se creará uno nuevo al guardar cambios",1);
    		
        } catch (IOException | ClassNotFoundException ex) {
        	parentFrame.mensaje("Error al cargar usuarios: " + ex.getMessage(),0);
        }
	}

}

