package com.logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Jornada implements Comparable<Jornada>, Serializable{ /**
	 * 
	 */
	private static final long serialVersionUID = 4644242302041775382L;

	//Defino la clase Jornada
		//Propiedades o Atributos
		String NumJor;
		Partido partido;
		ArrayList<Partido> listPartidos = new ArrayList<>();

		
		
		//Constructor por defecto
		public Jornada() {
			NumJor="NULL";
			partido= new Partido();
			listPartidos.add(partido);
		}
		
	//Contructor copia
		public Jornada(Jornada j) {
			this.NumJor= j.NumJor;
			this.partido= j.partido;
			partido = new Partido(j.partido);
			
	}
		Jornada(String n) {
			NumJor = n;
		    
	}
		
		//Getters and setters
		
		 public String getNumJor() {
			return NumJor;
		}

		public void setNumJor(String numJor) {
			NumJor = numJor;
		}

		public Partido getPartido() {
			return partido;
		}

		public void setPartido(Partido partido) {
			this.partido = partido;
		}

			//ToString
			public String toString() {
				return(NumJor + "" + partido );
			}
			
		@Override
		public int hashCode() {
			return Objects.hash( partido, NumJor );
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				//si es el mismo objeto
				return true;
			if (obj == null)
				// si el objeto no esta creado
				return false;
			if (getClass() != obj.getClass())
				return false;
			//Comparo las propiedades
			Jornada other = (Jornada) obj;
			return Objects.equals(NumJor, other.NumJor);
		}

		@Override
		public int compareTo(Jornada o) {
			// TODO Auto-generated method stub
			return 0;
		}
