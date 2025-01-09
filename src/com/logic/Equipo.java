package com.logic;

import java.util.ArrayList;

public class Equipo {
	private int id;
	private String Nombre;
	private ArrayList<Jugador> listaJugador ;
	private int fechaFundEq;
	private String entrenador;
	private int victorias;
	private int derrotas;
	private int puntosTotales;
	
	
	
	
	public Equipo() {
		id= 1;
		Nombre= "Equpo por defecto";
		listaJugador = new ArrayList<Jugador>();
		fechaFundEq= 1800;
		entrenador = " ";
		victorias= 0;
		derrotas = 0;
		puntosTotales=0;
	}
	public Equipo (Equipo e) {
		id= e.id;
		Nombre= e.Nombre;
		listaJugador = new ArrayList<Jugador>(e.listaJugador);
		fechaFundEq= e.fechaFundEq;
		entrenador = e.entrenador;
		victorias= e.victorias;
		derrotas = e.derrotas;
		puntosTotales=e.puntosTotales;
	}
	public Equipo (int ide, String nom, int fecha, String coach, int win, int lose, int total) {
		id= ide;
		Nombre= nom;
		listaJugador = new ArrayList<Jugador>();
		fechaFundEq= fecha;
		entrenador = coach;
		victorias= win;
		derrotas = lose;
		puntosTotales=total;
	}
	//getters-Setters 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public ArrayList<Jugador> getListaJUgador() {
		return listaJugador;
	}
	public void setListaJUgador(ArrayList<Jugador> listaJUgador) {
		this.listaJugador = listaJUgador;
	}
	public int getFechaFundEq() {
		return fechaFundEq;
	}
	public void setFechaFundEq(int fechaFundEq) {
		this.fechaFundEq = fechaFundEq;
	}
	public String getEntrenador() {
		return entrenador;
	}
	public void setEntrenador(String entrenador) {
		this.entrenador = entrenador;
	}
	public int getVictorias() {
		return victorias;
	}
	public void setVictorias(int victorias) {
		this.victorias = victorias;
	}
	public int getDerrotas() {
		return derrotas;
	}
	public void setDerrotas(int derrotas) {
		this.derrotas = derrotas;
	}
	public int getPuntosTotales() {
		return puntosTotales;
	}
	public void setPuntosTotales(int puntosTotales) {
		this.puntosTotales = puntosTotales;
	}
	

}
