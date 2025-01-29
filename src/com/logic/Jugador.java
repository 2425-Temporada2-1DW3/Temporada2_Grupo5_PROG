package com.logic;

import java.io.Serializable;
import java.util.Objects;

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
	private int idFoto;
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
	public int getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	
	public Fecha getFechaNac() {
		return FechaNac;
	}
	
	public void setFechaNac(Fecha FechaNac) {
		this.FechaNac = FechaNac;
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
		idFoto= 01;
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
	public int hashCode() {
		return Objects.hash(numFicha);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jugador other = (Jugador) obj;
		return Objects.equals(numFicha, other.numFicha);
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
		//idFoto= id;
		//edad = age;
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