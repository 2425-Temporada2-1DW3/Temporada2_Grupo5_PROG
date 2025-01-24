package com.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Equipo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String Nombre;
	private ArrayList<Jugador> listJugadores ;
	private int fechaFundEq;
	private String entrenador;
	private int victorias;
	private int derrotas;
	private int totalPartidos;
	public ArrayList<Jugador> getListJugadores() {
		return listJugadores;
	}
	public void setListJugadores(ArrayList<Jugador> listJugadores) {
		this.listJugadores = listJugadores;
	}
	public int getTotalPartidos() {
		return totalPartidos;
	} 
	public void setTotalPartidos(int totalPartidos) {
		this.totalPartidos += 1;
	}
	private int puntosTotales;
	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Equipo other = (Equipo) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("========== Información del Equipo ==========\n");
	    sb.append("ID: ").append(id).append("\n");
	    sb.append("Nombre: ").append(Nombre).append("\n");
	    sb.append("Fecha de Fundación: ").append(fechaFundEq).append("\n");
	    sb.append("Entrenador: ").append(entrenador != null ? entrenador : "No asignado").append("\n");
	    sb.append("Victorias: ").append(victorias).append("\n");
	    sb.append("Derrotas: ").append(derrotas).append("\n");
	    sb.append("Puntos Totales: ").append(puntosTotales).append("\n");

	    // Mostrar jugadores si hay en la lista
	    if (listJugadores != null && !listJugadores.isEmpty()) {
	        sb.append("Lista de Jugadores:\n");
	        for (int i = 0; i < listJugadores.size(); i++) {
	            sb.append("  Jugador ").append(i + 1).append(": ").append(listJugadores.get(i).toString()).append("\n");
	        }
	    } else {
	        sb.append("Lista de Jugadores: No hay jugadores registrados.\n");
	    }

	    sb.append("-------------------------------------------\n");
	    return sb.toString();
	}

	public Equipo() {
		id= 1;
		Nombre= "Equpo por defecto";
		listJugadores = new ArrayList<Jugador>();
		fechaFundEq= 1800;
		entrenador = " ";
		victorias= 0;
		derrotas = 0;
		puntosTotales=0;
		totalPartidos= 0;
	}
	public Equipo (Equipo e) {
		id= e.id;
		Nombre= e.Nombre;
		listJugadores = new ArrayList<Jugador>(e.listJugadores);
		fechaFundEq= e.fechaFundEq;
		entrenador = e.entrenador;
		victorias= e.victorias;
		derrotas = e.derrotas;
		puntosTotales=e.puntosTotales;
		totalPartidos = e.totalPartidos;
	}
	public Equipo (int ide, String nom) {
		this.id= ide;
		this.Nombre= nom;
	}
	public Equipo (int ide, String nom, int fecha, String coach, int win, int lose, int total, int totalPartido) {
		id= ide;
		Nombre= nom;
		listJugadores = new ArrayList<Jugador>();
		fechaFundEq= fecha;
		entrenador = coach;
		victorias= win;
		derrotas = lose;
		puntosTotales=total;
		totalPartidos= totalPartido;
	}
	public void addPoints() {
		victorias += 1;
	}
	public void addDerrotas() {
		derrotas += 1;
	}
	public void addPuntosTotal(int puntos) {
		puntosTotales+= puntos;
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
		return listJugadores;
	}
	public void setListaJUgador(ArrayList<Jugador> listaJUgador) {
		this.listJugadores = listaJUgador;
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
	public void incrementarPartidosTotales() {
		totalPartidos+=1;
	} 
	
	public void incrementarPartidosGanados(){
		victorias+= 1;
	}
	public void incrementarPartidosPerdido(){
		derrotas+= 1;
	}
	public void agregarPuntostotales(int punto) {
		puntosTotales += punto;
	}

}