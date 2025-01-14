package com.logic;

import java.util.ArrayList;
import java.util.Objects;

public class Temporada implements Comparable<Temporada> {
	
    private int id_temporada;
    private String nombre;
    
    private boolean iniciado;
    private boolean finalizado;
    
    private int cantidadEquipos;
    private int cantidadJornadas;
    
    private ArrayList <Equipo> listEquipos;
    private ArrayList <Jornada> listJornadas;


	public Temporada() {
        nombre = "Temporada";
        finalizado = false;
        iniciado = false;
        cantidadEquipos = 6;
        cantidadJornadas = 10;
        id_temporada = 0;
        listEquipos = new ArrayList<Equipo>();
        listJornadas = new ArrayList<Jornada>();
       
    }

    // Copia
    public Temporada(Temporada r) {
        nombre = r.nombre;
        finalizado = r.finalizado;
        iniciado = r.iniciado;
        cantidadEquipos = r.cantidadEquipos;
        cantidadJornadas = r.cantidadJornadas;
        id_temporada = r.id_temporada;
        listEquipos = r.listEquipos;
        listJornadas = r.listJornadas;
    }

    // Personalizado
    public Temporada(int id, String nom, int cantEq, int cantJor) {
        id_temporada = id;
        nombre = nom;
        cantidadEquipos = cantEq;
        cantidadJornadas = cantJor;
        iniciado = false;
        finalizado = false;
        listEquipos = new ArrayList<Equipo>();
        listJornadas = new ArrayList<Jornada>();
    }

	// Getters y setters

    public int getId_temporada() {
		return id_temporada;
	}

	public void setId_temporada(int id_temporada) {
		this.id_temporada = id_temporada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
	}

	public boolean isFinalizado() {
		return finalizado;
	}

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public int getCantidadEquipos() {
		return cantidadEquipos;
	}

	public void setCantidadEquipos(int cantidadEquipos) {
		this.cantidadEquipos = cantidadEquipos;
	}

	public int getCantidadJornadas() {
		return cantidadJornadas;
	}

	public void setCantidadJornadas(int cantidadJornadas) {
		this.cantidadJornadas = cantidadJornadas;
	}

	public ArrayList<Equipo> getListEquipos() {
		return listEquipos;
	}

	public void setListEquipos(ArrayList<Equipo> listEquipos) {
		this.listEquipos = listEquipos;
	}

	public ArrayList<Jornada> getListJornadas() {
		return listJornadas;
	}

	public void setListJornadas(ArrayList<Jornada> listJornadas) {
		this.listJornadas = listJornadas;
	}

	@Override
    public String toString() {
    	String string = " ";
    	
    	if (finalizado && iniciado) {
    		string = "Temporada " + nombre + ", finalizado: si"  + ", iniciado: si" 
                    + ", cantidad Equipos: " + cantidadEquipos + ", cantidadJornadas:" + cantidadJornadas;
    	} else if(!finalizado && iniciado) {
    		string = "Temporada " + nombre + ", iniciado: si"  + ", finalizado: no"  
                    + ", cantidadEquipos: " + cantidadEquipos + ", cantidadJornadas:" + cantidadJornadas;
    	}else if(!finalizado && !iniciado) {
    		string = "Temporada " + nombre + ", iniciado: no"  + ", finalizado: no"  
                    + ", cantidadEquipos: " + cantidadEquipos + ", cantidadJornadas:" + cantidadJornadas;
    	}
		 return string;
        
    }

    // Equals & hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id_temporada);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Temporada other = (Temporada) obj;
        return id_temporada == other.id_temporada;
    }

    // compareTo
    @Override
    public int compareTo(Temporada other) {
        return Integer.compare(this.id_temporada, other.id_temporada);
    }
    
}
