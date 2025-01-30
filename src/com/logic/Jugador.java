package com.logic;

import java.io.Serializable;
import java.util.Objects;
import java.util.Calendar;

public class Jugador implements Serializable {

	private static final long serialVersionUID = 1L;
	private String numFicha;
	private String nombre;
	private int edad;
	private String posicion;
	private int dorsal;
	private String nacionalidad;
	private double altura;
	private double peso;
	private String idFoto;
	private int idEquipo;
	private Fecha FechaNac;
	
	public String getNumFicha() {
		return numFicha;
	}
	public void setNumFicha(String numFicha) {
		this.numFicha = numFicha;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	public String getPosicion() {
		return posicion;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public int getDorsal() {
		return dorsal;
	}
	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public double getAltura() {
		return altura;
	}
	public void setAltura(double altura) {
		this.altura = altura;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public int getIdEquipo() {
		return idEquipo;
	}
	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}
	public String getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(String idFoto) {
		this.idFoto = idFoto;
	}
	
	public Fecha getFechaNac() {
		return FechaNac;
	}
	
    public void setFechaNac(Fecha FechaNac) {
        this.FechaNac = FechaNac;
        // Recalcular la edad si se cambia la fecha de nacimiento
        this.edad = calcularEdad(FechaNac);
    }
	
	//constructor defecto 
	public Jugador () {
		numFicha= "00000000j";
		nombre= "Jugador por defecto";
		edad = 19;
		dorsal= 1;
		posicion = "indefinida";
		nacionalidad= "Española";
		altura = 180;
		peso =70;
		idFoto= "idFotodefault";
		idEquipo= 1;
		FechaNac = new Fecha(1, 1, 2000);
	}
	//constructor copia 
	public Jugador (Jugador j) {
		numFicha= j.numFicha;
		nombre= j.nombre;
		edad = j.edad;
		dorsal= j.dorsal;
		posicion= j.posicion;
		nacionalidad= j.nacionalidad;
		altura= j.altura;
		peso= j.peso;
		idFoto= j.idFoto;
		idEquipo= j.idEquipo;
		FechaNac = new Fecha(j.FechaNac);
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (this == obj) return true;  // Si son el mismo objeto
	    if (obj == null || getClass() != obj.getClass()) return false;  // Si el objeto es null o de clase diferente
	    Jugador other = (Jugador) obj;
	    return Objects.equals(numFicha, other.numFicha);  // Comparar numFicha
	}

	@Override
	public int hashCode() {
	    return Objects.hash(numFicha);  // Usar numFicha para calcular el hashCode
	}


	//constructor personalizado 
	public Jugador (String Ficha, String nom, int dor, String position, String nacio, double height, double weight, int day, int month, int year, int Equipo) {
		numFicha = Ficha;
		nombre= nom;
		nacionalidad= nacio;
		FechaNac = new Fecha(day,month,year);
		altura = height;
		peso =weight;
		dorsal= dor;
		posicion = position;
		idEquipo= Equipo;
		idFoto= "idFotodefault";

        // Cálculo de la edad basado en la fecha de nacimiento
        this.edad = calcularEdad(FechaNac);
	}	//constructor personalizado 
	
	public Jugador (String Ficha, String nom, int dor, String position, String nacio, double height, double weight, int day, int month, int year, int Equipo, String file) {
		numFicha = Ficha;
		nombre= nom;
		nacionalidad= nacio;
		FechaNac = new Fecha(day,month,year);
		altura = height;
		peso =weight;
		dorsal= dor;
		posicion = position;
		idEquipo= Equipo;
		idFoto= file;

        // Cálculo de la edad basado en la fecha de nacimiento
        this.edad = calcularEdad(FechaNac);
	}
	
    // Método para calcular la edad
    private int calcularEdad(Fecha fechaNac) {
        Calendar now = Calendar.getInstance();
        int yearNow = now.get(Calendar.YEAR);
        int monthNow = now.get(Calendar.MONTH) + 1; // Meses en Calendar van de 0 a 11
        int dayNow = now.get(Calendar.DAY_OF_MONTH);
        
        int age = yearNow - fechaNac.getAno();
        
        // Ajustar si el cumpleaños aún no ha pasado este año
        if (monthNow < fechaNac.getMes() || (monthNow == fechaNac.getMes() && dayNow < fechaNac.getDia())) {
            age--; // No ha cumplido años aún este año
        }
        
        return age;
    }
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("========== Información del Jugador ==========\n");
	    sb.append("Número de Ficha: ").append(numFicha).append("\n");
	    sb.append("Nombre: ").append(nombre).append("\n");
	    sb.append("Edad: ").append(edad).append("\n");
	    sb.append("Posición: ").append(posicion).append("\n");
	    sb.append("Dorsal: ").append(dorsal).append("\n");
	    sb.append("Nacionalidad: ").append(nacionalidad).append("\n");
	    sb.append("Altura: ").append(altura).append(" m\n");
	    sb.append("Peso: ").append(peso).append(" kg\n");
	    sb.append("ID de Foto: ").append(idFoto).append("\n");
	    sb.append("ID de Equipo: ").append(idEquipo).append("\n");
	    sb.append("Fecha de Nacimiento: ").append(FechaNac != null ? FechaNac.toString() : "No registrada").append("\n");
	    sb.append("---------------------------------------------\n");
	    return sb.toString();
	}

	//@ToDo
	public void cambiarDeEquipo() {
		
	}
	
	}