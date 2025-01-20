package com.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Jornada implements Comparable<Jornada>, Serializable {
	
	private static final long serialVersionUID = 4644242302041775382L;

	// Defino la clase Jornada
	// Propiedades o Atributos
	private int id_jornada; // Número de la jornada
	private ArrayList<Partido> listPartidos; // Lista de partidos

	// Constructor por defecto
	public Jornada() {
		this.id_jornada = 0;
		this.listPartidos = new ArrayList<>();
	}

	// Contructor copia
	public Jornada(Jornada j) {
		this.id_jornada = j.id_jornada;
		this.listPartidos = j.listPartidos;
	}

	// Constructor con parámetros
	public Jornada(int id, ArrayList<Partido> listPartidos) {
		this.id_jornada = id;
		this.listPartidos = listPartidos != null ? listPartidos : new ArrayList<>();
	}
	
	
	// Getters y Setters
    public int getid_jornada() {
        return id_jornada;
    }

    public void setid_jornada(int id) {
        this.id_jornada = id;
    }

    public ArrayList<Partido> getListPartidos() {
        return listPartidos;
    }

    public void setListPartidos(ArrayList<Partido> listPartidos) {
        this.listPartidos = listPartidos;
    }

    
    
    
    // toString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Encabezado de la jornada
        sb.append("========== Jornada ").append(id_jornada).append(" ==========\n");
        sb.append("Cantidad de Partidos: ").append(listPartidos.size()).append("\n\n");

        // Mostrar información de cada partido
        for (int i = 0; i < listPartidos.size(); i++) {
            Partido partido = listPartidos.get(i);
            sb.append("Partido ").append(i + 1).append(":\n");
            sb.append(partido.toString()).append("\n"); // Utiliza el formato detallado del partido
        }

        return sb.toString();
    }



    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id_jornada, listPartidos);
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Jornada jornada = (Jornada) obj;
        return Objects.equals(id_jornada, jornada.id_jornada) &&
                Objects.equals(listPartidos, jornada.listPartidos);
    }

    // compareTo
    @Override
    public int compareTo(Jornada other) {
        // Comparar usando el número de jornada (id_jornada)
        return Integer.compare(this.id_jornada, other.id_jornada);
    }
	
	
	
	
}