package com.logic;

import java.io.Serializable;

public class Usuario implements Serializable  {
	private static final long serialVersionUID = 1L;
    private String user;
    private int type; // 0 = Usuario normal, 1 = Arbitro, 2 = Admin
    private String pass;

    // Constructor por defecto
    public Usuario() {
        this.user = "";
        this.type = 0;
        this.pass = "";
    }

    // Constructor de copia
    public Usuario(Usuario u) {
        this.user = u.user;
        this.type = u.type;
        this.pass = u.pass;
    }

    // Constructor personalizado
    public Usuario(String user, int type, String pass) {
        this.user = user;
        this.type = type;
        this.pass = pass;
    }
    
    /* Comparar usuarios para login, devuelve true si el usuario y contraseña es correcta.
     * No devuelve que esta mal porque eso es un riesgo de seguridad
     */
    public boolean checkUser(String user, String pass) {
    	if (this.user.equals(user)  && this.pass.equals(pass)) {
    		return true;
    	} else {return false;}
    }

    // Getters y setters
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		String tipo ="";
		switch(type){
		case 0:
			tipo = "Usuario";
			break;
		case 1:
			tipo = "Arbitro";
		break;
		case 2:
			tipo = "Administrador";
		
	}
		return "Nobre de usuario: "+ user + ", Privilegios de usuario: " + tipo + ", Contraseña de Usuario: " + pass;
    
    
}}
