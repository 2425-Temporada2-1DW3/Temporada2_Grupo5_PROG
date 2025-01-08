package com.logic;

public class Jugador {

	private String nombre;
	private int edad;
	private String posicion;
	private int dorsal;
	private String nacionalidad;
	private int altura;
	private int peso;
	private int idFoto;
	private int idEquipo;
	
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
	}
	//constructor personalizado 
	public Jugador (String nom, int age, int dor, String position, String nacio, int height, int weight, int id) {
		nombre= nom;
		edad = age;
		dorsal= dor;
		posicion = position;
		nacionalidad= nacio;  
		altura = height;
		peso =weight;
		idFoto= id;
	}
	//@ToDo
	public void cambiarDeEquipo() {
		
	}
	
	}