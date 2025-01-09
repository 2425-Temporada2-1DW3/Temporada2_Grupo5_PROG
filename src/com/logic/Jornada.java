package com.logic;

import java.io.Serializable;
import java.util.Objects;

public class Jornada implements Comparable<Jornada>, Serializable{ /**
	 * 
	 */
	private static final long serialVersionUID = 4644242302041775382L;

	//Defino la clase Jornada
		//Propiedades o Atributos
		String NumJor;
		int ListaPartidos[] = new int [2];
		boolean jugado;
		
		
		//Constructor por defecto
		public Jornada() {
			NumJor="NULL";
			int [] ListaPartidos = {0,0,0};
			jugado = false;
		}
		
	//Contructor copia
		public Jornada(Jornada j) {
			this.NumJor= j.NumJor;
			this.jugado= j.jugado;
			this.ListaPartidos= j.ListaPartidos;
			
	}
		Jornada(String n, boolean j ) {
			NumJor = n;
		    jugado = j;
		    
	}
		
		//Getters and setters
		

		public String getNumJor() {
			return NumJor;
		}

		public void setNumJor(String numJor) {
			NumJor = numJor;
		}

		public int[] getListaPartidos() {
			return ListaPartidos;
		}

		public void setListaPartidos(int[] listaPartidos) {
			ListaPartidos = listaPartidos;
		}

		public boolean isJugado() {
			return jugado;
		}

		public void setJugado(boolean jugado) {
			this.jugado = jugado;
		}

		 //ToString
			public String toString() {
				return(NumJor + "" + ListaPartidos + "" + jugado + "" );
			}
			
		@Override
		public int hashCode() {
			return Objects.hash( jugado, ListaPartidos, NumJor );
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

		/*metodo registrar resultados (partido: Partido, marcadorLocal: int, marcadorVisitante: int): public void */
		
			public void registrarResultados( Partido partido, int marcadorLocal, int marcadorVisitante) {
			    // Actualizar los marcadores del partido
				partido.setMarcadorLocal(marcadorLocal);
				partido.setMarcadorVisitante(marcadorVisitante);

			    // Determinar el resultado y actualizar la clasificación
			    if (marcadorLocal > marcadorVisitante) {
			        // El equipo local gana
			        actualizarClasificacion(partido.getEquipoLocal(), 3); // 3 puntos para el equipo local
			        actualizarClasificacion(partido.getEquipoVisitante(), 0); // 0 puntos para el visitante
			    } else if (marcadorLocal < marcadorVisitante) {
			        // El equipo visitante gana
			        actualizarClasificacion(partido.getEquipoVisitante(), 3); // 3 puntos para el visitante
			        actualizarClasificacion(partido.getEquipoLocal(), 0); // 0 puntos para el local
			    } else {
			        // Empate
			        actualizarClasificacion(partido.getEquipoLocal(), 1); // 1 punto para ambos equipos
			        actualizarClasificacion(partido.getEquipoVisitante(), 1);
			    }
			}
}
