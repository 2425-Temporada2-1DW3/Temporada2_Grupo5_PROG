package com.logic;

public class Temporada {
	private String nombre;
	private boolean finalizado;
	private boolean iniciado;
	private int cantidadEquipos;
	private int cantidadJornadas;
	private int id_temporada;
	
	public Temporada() {
	nombre= "Temporada Por defecto";
	finalizado= false;
	iniciado= false;
	cantidadEquipos= 6;
	cantidadJornadas= 10;
	id_temporada= 0;
	}
	//copia
	public Temporada(Temporada r) {
		nombre= r.nombre;
		finalizado= r.finalizado;
		iniciado= r.iniciado;
		cantidadEquipos= r.cantidadEquipos;
		cantidadJornadas= r.cantidadJornadas;
		id_temporada= r.id_temporada;
	}
	//personalizado
	public Temporada (int id, String nom,  int cantEq, int cantJor, boolean fin, boolean inic) {
		
		id_temporada= id;
		nombre= nom;
		cantidadEquipos= cantEq;
		cantidadJornadas= cantJor;
		iniciado= inic;
		finalizado= fin;
		
		
	}
	
	public void finalizarTemporada() {
		if (!finalizado && !iniciado ) {
			System.out.println("la temporada no esta iniciada");
		} else if (iniciado && !finalizado) {
			finalizado= true;
			System.out.println("la temporada se ha finalizado");
		}
	}
	
	public void iniciarTemporada() {
		if(iniciado) {
			System.out.println("la temporada  esta iniciada");
		}else {
			iniciado= true;
		}
	}
	
	@Override
	public String toString() {
		return "Temporada [nombre=" + nombre + ", finalizado=" + finalizado + ", iniciado=" + iniciado
				+ ", cantidadEquipos=" + cantidadEquipos + ", cantidadJornadas=" + cantidadJornadas + ", id_temporada="
				+ id_temporada + "]";
	}
	
}
