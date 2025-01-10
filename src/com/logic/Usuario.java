package com.logic;

public class Usuario {
    private String user;
    private int type; // 0 = Usuario normal, 1 = Arbitro, 2 = Admin
    private String password;

    // Constructor por defecto
    public Usuario() {
        this.user = "";
        this.type = 0;
        this.password = "";
    }

    // Constructor de copia
    public Usuario(Usuario u) {
        this.user = u.user;
        this.type = u.type;
        this.password = u.password;
    }

    // Constructor personalizado
    public Usuario(String user, int type, String password) {
        this.user = user;
        this.type = type;
        this.password = password;
    }

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Usuario [user=" + user + ", type=" + type + ", password=" + password + "]";
	}
    
    
}
