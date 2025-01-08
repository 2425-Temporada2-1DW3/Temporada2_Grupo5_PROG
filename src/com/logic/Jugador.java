package com.logic;

import java.util.Objects;

public class Jugador  {

	
	private String nombre;
	private int edad;
	private String posicion;
	private int dorsal;
	private String nacionalidad;
	private int altura;
	private int peso;
	private int idFoto;
	private int idEquipo;
	private Fecha FechaNac;
	
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
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public int getPeso() {
		return peso;
	}
	public void setPeso(int peso) {
		this.peso = peso;
	}
	public int getIdFoto() {
		return idFoto;
	}
	public void setIdFoto(int idFoto) {
		this.idFoto = idFoto;
	}
	//constructor defecto 
	public Jugador () {
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
		return Objects.hash(dorsal, edad, nombre);
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
		return dorsal == other.dorsal && edad == other.edad && Objects.equals(nombre, other.nombre);
	}
	//constructor personalizado 
	public Jugador (String nom, int age, int dor, String position, String nacio, int height, int weight, int id, int day, int month, int year) {
		nombre= nom;
		edad = age;
		dorsal= dor;
		posicion = position;
		nacionalidad= nacio;  
		altura = height;
		peso =weight;
		idFoto= id;
		FechaNac = new Fecha(day,month,year);
	}
	 @Override
	    public String toString() {
	        return "Jugador [nombre=" + nombre + ", edad=" + edad + ", posicion=" + posicion + ", dorsal=" + dorsal
	                + ", nacionalidad=" + nacionalidad + ", altura=" + altura + ", peso=" + peso + ", idFoto=" + idFoto
	                + ", idEquipo=" + idEquipo + ", fechaNac=" + FechaNac + "]";
	    }
	//@ToDo
	public void cambiarDeEquipo() {
		
	}
	
	}