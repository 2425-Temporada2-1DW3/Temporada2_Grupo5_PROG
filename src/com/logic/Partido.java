package com.logic;

import java.io.Serializable;
import java.util.Objects;

public class Partido implements Serializable{
	private static final long serialVersionUID = 1L;
	// ----Definición de Variables---//

	private int id = 0;
	private boolean jugado = false; //jugado
	
	private int equipoLoc = 0;
	private int equipoVis = 0;
	
	private boolean ganadorLoc = false;
	private boolean ganadorVis = false;

	private int puntuajeLoc = 0; /*Representan los goles/puntos anotados por equipo local*/
	private int puntuajeVis = 0;
	
	
	private boolean resultadoCalculado = false;

	// Constructor Por Defecto
	public Partido() {
		this.id = 0;
		this.jugado = false;
		this.equipoLoc = 0;
		this.equipoVis = 0;
		this.ganadorLoc = false;
		this.ganadorVis = false;
		this.puntuajeLoc = 0;
		this.puntuajeVis = 0;
		this.resultadoCalculado = false;
	}
	
	// Constructor Copia
	public Partido(Partido p) {
	    this.id = p.id;
	    this.jugado = p.jugado;
	    this.equipoLoc = p.equipoLoc;
	    this.equipoVis = p.equipoVis;
	    this.ganadorLoc = p.ganadorLoc;
	    this.ganadorVis = p.ganadorVis;
	    this.puntuajeLoc = p.puntuajeLoc;
	    this.puntuajeVis = p.puntuajeVis;
	    this.resultadoCalculado = p.resultadoCalculado;
	}
	
	//Constructor para GeneradorTemporada
	public void CrearPartido(int idPart, int idLoc, int idVis) {
		this.id = idPart;
		this.equipoLoc = idLoc;
		this.equipoVis = idVis;
	}
	
	// Constructor Custom
	public Partido(int p, int el, int ev, boolean sgl, boolean sgv, int pusl, int pusv, boolean rc, boolean j) {

		this.id = p;
		this.jugado = j;
		this.equipoLoc = el;
		this.equipoVis = ev;
		this.ganadorLoc = sgl;
		this.ganadorVis = sgv;
		this.puntuajeLoc = pusl;
		this.puntuajeVis = pusv;
		this.resultadoCalculado = rc;

	}
	
	// Getters y Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isJugado() {
		return jugado;
	}

	public void setJugado(boolean jugado) {
		this.jugado = jugado;
	}

	public int getEquipoLoc() {
		return equipoLoc;
	}

	public void setEquipoLoc(int equipoLoc) {
		this.equipoLoc = equipoLoc;
	}

	public int getEquipoVis() {
		return equipoVis;
	}

	public void setEquipoVis(int equipoVis) {
		this.equipoVis = equipoVis;
	}

	
	public void setGanadorLoc(boolean ganadorLocal) {
		this.ganadorLoc=ganadorLocal;
	}
	public void setGanadorVis(boolean ganadorVisitante) {
		this.ganadorVis=ganadorVisitante;
	}

	public int getpuntuajeLoc() {
		return puntuajeLoc;
	}

	public void setpuntuajeLoc(int puntuajeLoc) {
		this.puntuajeLoc = puntuajeLoc;
	}

	public int getpuntuajeVis() {
		return puntuajeVis;
	}

	public void setpuntuajeVis(int puntuajeVis) {
		this.puntuajeVis = puntuajeVis;
	}

	public boolean isResultadoCalculado() {
		return resultadoCalculado;
	}

	public void setResultadoCalculado(boolean resultadoCalculado) {
		this.resultadoCalculado = resultadoCalculado;
	}
	
	public void MostrarPartido() {
		System.out.println("PartidoID: " + id + " " + equipoLoc + " - " + equipoVis);
	}
	
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
		Partido other = (Partido) obj;
		return id == other.id;
	}

//	@Override
//	public String toString() {
//		return "Partido [id=" + id + ", jugado=" + jugado + ", equipoLoc=" + equipoLoc + ", equipoVis=" + equipoVis
//				+ ", ganadorLoc=" + ganadorLoc + ", ganadorVis=" + ganadorVis
//				+ ", puntuajeLoc=" + puntuajeLoc + ", puntuajeVis=" + puntuajeVis
//				+ ", resultadoCalculado=" + resultadoCalculado + "]";
//	}
	
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    sb.append("ID Partido: ").append(id).append("\n");
	    sb.append("Jugado: ").append(jugado ? "Sí" : "No").append("\n");
	    sb.append("Equipo Local: ").append(equipoLoc+1).append("\n");
	    sb.append("Equipo Visitante: ").append(equipoVis+1).append("\n");
	    sb.append("Sets Ganados (Local): ").append(ganadorLoc).append("\n");
	    sb.append("Sets Ganados (Visitante): ").append(ganadorVis).append("\n");
	    sb.append("Puntuaje Último Set (Local): ").append(puntuajeLoc).append("\n");
	    sb.append("Puntuaje Último Set (Visitante): ").append(puntuajeVis).append("\n");
	    sb.append("Resultado Calculado: ").append(resultadoCalculado ? "Sí" : "No").append("\n");
	    sb.append("-------------------------------------------\n");
	    return sb.toString();
	}
	

}