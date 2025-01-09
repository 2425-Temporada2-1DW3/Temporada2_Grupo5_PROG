package com.logic;

import java.util.Objects;

public class Partido {

	// ----Definición de Variables---//

	private int id = 0;
	private boolean jugado = false; //jugado
	
	private int equipoLoc = 0;
	private int equipoVis = 0;
	
	private int setsGanadorsLoc = 0;
	private int setsGanadosVis = 0;

	private int puntuajeUltimoSetLoc = 0;
	private int puntuajeUltimoSetVis = 0;
	
	private boolean resultadoCalculado = false;

	// Constructor Por Defecto
	public Partido() {
		this.id = 0;
		this.jugado = false;
		this.equipoLoc = 0;
		this.equipoVis = 0;
		this.setsGanadorsLoc = 0;
		this.setsGanadosVis = 0;
		this.puntuajeUltimoSetLoc = 0;
		this.puntuajeUltimoSetVis = 0;
		this.resultadoCalculado = false;
	}
	
	// Constructor Copia
	public Partido(Partido p) {
	    this.id = p.id;
	    this.jugado = p.jugado;
	    this.equipoLoc = p.equipoLoc;
	    this.equipoVis = p.equipoVis;
	    this.setsGanadorsLoc = p.setsGanadorsLoc;
	    this.setsGanadosVis = p.setsGanadosVis;
	    this.puntuajeUltimoSetLoc = p.puntuajeUltimoSetLoc;
	    this.puntuajeUltimoSetVis = p.puntuajeUltimoSetVis;
	    this.resultadoCalculado = p.resultadoCalculado;
	}
	
	// Constructor Custom
	public Partido(int p, int el, int ev, int sgl, int sgv, int pusl, int pusv, boolean rc, boolean j) {

		this.id = p;
		this.jugado = j;
		this.equipoLoc = el;
		this.equipoVis = ev;
		this.setsGanadorsLoc = sgl;
		this.setsGanadosVis = sgv;
		this.puntuajeUltimoSetLoc = pusl;
		this.puntuajeUltimoSetVis = pusv;
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

	public int getSetsGanadorsLoc() {
		return setsGanadorsLoc;
	}

	public void setSetsGanadorsLoc(int setsGanadorsLoc) {
		this.setsGanadorsLoc = setsGanadorsLoc;
	}

	public int getSetsGanadosVis() {
		return setsGanadosVis;
	}

	public void setSetsGanadosVis(int setsGanadosVis) {
		this.setsGanadosVis = setsGanadosVis;
	}

	public int getPuntuajeUltimoSetLoc() {
		return puntuajeUltimoSetLoc;
	}

	public void setPuntuajeUltimoSetLoc(int puntuajeUltimoSetLoc) {
		this.puntuajeUltimoSetLoc = puntuajeUltimoSetLoc;
	}

	public int getPuntuajeUltimoSetVis() {
		return puntuajeUltimoSetVis;
	}

	public void setPuntuajeUltimoSetVis(int puntuajeUltimoSetVis) {
		this.puntuajeUltimoSetVis = puntuajeUltimoSetVis;
	}

	public boolean isResultadoCalculado() {
		return resultadoCalculado;
	}

	public void setResultadoCalculado(boolean resultadoCalculado) {
		this.resultadoCalculado = resultadoCalculado;
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

	@Override
	public String toString() {
		return "Partido [id=" + id + ", jugado=" + jugado + ", equipoLoc=" + equipoLoc + ", equipoVis=" + equipoVis
				+ ", setsGanadorsLoc=" + setsGanadorsLoc + ", setsGanadosVis=" + setsGanadosVis
				+ ", puntuajeUltimoSetLoc=" + puntuajeUltimoSetLoc + ", puntuajeUltimoSetVis=" + puntuajeUltimoSetVis
				+ ", resultadoCalculado=" + resultadoCalculado + "]";
	}
	
	

}