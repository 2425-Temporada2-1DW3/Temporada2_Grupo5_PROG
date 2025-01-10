package com.logic;

import java.util.Objects;

public class Temporada implements Comparable<Temporada> {
    private String nombre;
    private boolean finalizado;
    private boolean iniciado;
    private int cantidadEquipos;
    private int cantidadJornadas;
    private int id_temporada;

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isFinalizado() {
		return finalizado;
	}
	

	public void setFinalizado(boolean finalizado) {
		this.finalizado = finalizado;
	}

	public boolean isIniciado() {
		return iniciado;
	}

	public void setIniciado(boolean iniciado) {
		this.iniciado = iniciado;
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

	public Temporada() {
        nombre = "Temporada Por defecto";
        finalizado = false;
        iniciado = false;
        cantidadEquipos = 6;
        cantidadJornadas = 10;
        id_temporada = 0;
    }

    // Copia
    public Temporada(Temporada r) {
        nombre = r.nombre;
        finalizado = r.finalizado;
        iniciado = r.iniciado;
        cantidadEquipos = r.cantidadEquipos;
        cantidadJornadas = r.cantidadJornadas;
        id_temporada = r.id_temporada;
    }

    // Personalizado
    public Temporada(int id, String nom, int cantEq, int cantJor) {
        id_temporada = id;
        nombre = nom;
        cantidadEquipos = cantEq;
        cantidadJornadas = cantJor;
        iniciado = false;
        finalizado = false;
    }

    public void finalizarTemporada() {
        if (!finalizado && !iniciado) {
            System.out.println("La temporada no está iniciada");
        } else if (iniciado && !finalizado) {
            finalizado = true;
            System.out.println("La temporada se ha finalizado");
        }
    }

    public void iniciarTemporada() {
        if (iniciado) {
            System.out.println("La temporada está iniciada");
        } else {
            iniciado = true;
        }
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
