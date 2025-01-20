package com.logic;

import java.util.ArrayList;
import java.util.Objects;

public class Temporada implements Comparable<Temporada> {
    private int id_temporada;
    private String nombre;
    private boolean iniciado;
    private boolean finalizado;
    private int cantidadJornadas;
    private int cantidadEquipos;
    private ArrayList<Jornada> listJornadas; // Nuevo atributo
    private ArrayList<Equipo> listEquipos; // Nuevo atributo

    public int getIdTemporada() {
		return id_temporada;
	}
    
    public String getNombre() {
		return nombre;
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
	
	// Getters y Setters para listJornadas
    public ArrayList<Jornada> getListJornadas() {
        return listJornadas;
    }

    public void setListJornadas(ArrayList<Jornada> listJornadas) {
        this.listJornadas = listJornadas;
    }
    
    public ArrayList<Equipo> getListEquipos() {
        return listEquipos;
    }

    public void setListEquipos(ArrayList<Equipo> listEquipos) {
        this.listEquipos = listEquipos;
    }
	public Temporada() {
        nombre = "Temporada Por defecto";
        finalizado = false;
        iniciado = false;
        cantidadEquipos = 6;
        cantidadJornadas = 10;
        id_temporada = 0;
        listJornadas = new ArrayList<Jornada>(); // Inicializar la lista de jornadas
    }

    // Copia
    public Temporada(Temporada r) {	
        nombre = r.nombre;
        finalizado = r.finalizado;
        iniciado = r.iniciado;
        cantidadEquipos = r.cantidadEquipos;
        cantidadJornadas = r.cantidadJornadas;
        id_temporada = r.id_temporada;
        listJornadas = new ArrayList<>(r.listJornadas); // Copiar las jornadas
    }

    // Personalizado
    public Temporada(int id, String nom, int cantEq) {
        id_temporada = id;
        nombre = nom;
        cantidadEquipos = cantEq;
        cantidadJornadas = 2 * (cantEq - 1);
        iniciado = false;
        finalizado = false;
        listJornadas = new ArrayList<>(); // Inicializar la lista de jornadas
    }

	public ArrayList<Jornada> getListJornadas() {
		return listJornadas;
	}

	public void setListJornadas(ArrayList<Jornada> listJornadas) {
		this.listJornadas = listJornadas;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== Información de la Temporada ==========\n");
        sb.append("ID Temporada: ").append(id_temporada).append("\n");
        sb.append("Nombre: ").append(nombre != null ? nombre : "No asignado").append("\n");
        sb.append("Estado: ").append(iniciado ? (finalizado ? "Finalizado" : "En curso") : "No iniciado").append("\n");
        sb.append("Cantidad de Equipos: ").append(cantidadEquipos).append("\n");
        sb.append("Cantidad de Jornadas: ").append(cantidadJornadas).append("\n");
     // Calcular el total de partidos recorriendo las jornadas
        int totalPartidos = 0;
        if (listJornadas != null) {
            for (Jornada jornada : listJornadas) {
                if (jornada.getListPartidos() != null) {
                    totalPartidos += jornada.getListPartidos().size();
                }
            }
        }
        sb.append("Cantidad de Partidos: ").append(totalPartidos).append("\n");

        sb.append("1-------------------------------------------\n");
        for (Jornada jornada : listJornadas) {
            sb.append(jornada.toString()).append("\n");
        }
        sb.append("############################################\n");
        return sb.toString();
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
        return id_temporada == other.id_temporada &&
                Objects.equals(listJornadas, other.listJornadas);
    }

    // compareTo
    @Override
    public int compareTo(Temporada other) {
        return Integer.compare(this.id_temporada, other.id_temporada);
    }
    
}
