package com.logic;

import java.util.ArrayList;
import java.util.Scanner;


public class GeneradorTemporada {
	
//--------------VARIABLES PRINCIPALES
	
	private int TOTAL_EQUIPOS;
	private int TOTAL_JORNADAS;
	private int TOTAL_PARTIDOS_X_JORNADA;
	Scanner scan = new Scanner(System.in);
	
	
//--------------METODOS GENERAR TEMPORADA
    public void GenerarTemporada(Temporada nuevaTemporada) {
        this.TOTAL_EQUIPOS = nuevaTemporada.getCantidadEquipos();
        this.TOTAL_JORNADAS = TOTAL_EQUIPOS - 1;
        this.TOTAL_PARTIDOS_X_JORNADA = TOTAL_EQUIPOS / 2;

        // Generar equipos y asignarlos a la temporada actual
        ArrayList<Equipo> equipos = crearListaEquipos();
        nuevaTemporada.setListEquipos(equipos);

        // Generar jornadas y partidos
        ArrayList<Jornada> jornadas = crearListJornadas();
        nuevaTemporada.setListJornadas(jornadas);
        nuevaTemporada.toString();
        
        // Imprimir los partidos y detalles de la temporada
        mostrarPartidos(nuevaTemporada);
    }
	
	// Clase estática para representar un Partido con equipo local y visitante en el Sistema Round Robin
		public  class RobinPartidoTemporal{
		private int id_Partido = 0;
        private  int equipolocal = 0, equipovisitante = 0;
        private  String[] NombreEquipo = new String[TOTAL_EQUIPOS];
        
        public RobinPartidoTemporal(int idPartido, int equipoLocal) {
            this.id_Partido = idPartido;
            this.equipolocal = equipoLocal;
        }
        public void setNombreEquipo(int posicion, String nombre) {
            this.NombreEquipo[posicion] = nombre;
        }
        public void setIdPartido(int id) {
	        this.id_Partido = id;
        }
        public int getIdPartido() {
	        return id_Partido;
        }
        public void setEquipoLocal(int local) {
	        this.equipolocal = local;
        }
        public void setEquipoVisitante(int visitante) {
	        this.equipovisitante = visitante;
        }
        public int getEquipoLocal() {
	        return equipolocal;
        }
        public int getEquipoVisitante() {
	        return equipovisitante;
        }
    }
		
	// Método para introducir los nombres de los equipos y CREAR LOS OBJETOS EQUIPOS VERSION DINAMICA PARA RETO 2 POR TRABAJAR
		private ArrayList<Equipo> crearListaEquipos() {
		    ArrayList<Equipo> temporalListEquipos = new ArrayList<>();
		    System.out.println("====== DATOS DE LOS EQUIPOS ========");

		    for (int i = 0; i < TOTAL_EQUIPOS; i++) {
		        System.out.print("Nombre del Equipo " + (i + 1) + ": ");
		        String nombreEquipo = scan.nextLine();

		        // Crear y configurar el equipo
		        Equipo equipo = new Equipo(i, nombreEquipo); // Constructor directo con ID y nombre
		        temporalListEquipos.add(equipo);
		    }

		    return temporalListEquipos;
		}

    // Método para calcular el fixture de la liga si el número de equipos es par
    private RobinPartidoTemporal[][] generarJornadasFixture() {  
        RobinPartidoTemporal[][] fixture = new RobinPartidoTemporal[TOTAL_JORNADAS][TOTAL_PARTIDOS_X_JORNADA];

        // Asignar equipos locales a todos los partidos
        for (int ronda = 0, idEquipoLocal = 0, idPartido = 0; ronda < TOTAL_JORNADAS; ronda++) {
            for (int partido = 0; partido < TOTAL_PARTIDOS_X_JORNADA; partido++) {    
            	fixture[ronda][partido] = new RobinPartidoTemporal(idPartido++, idEquipoLocal++);
                if (idEquipoLocal == TOTAL_JORNADAS) {
                    idEquipoLocal = 0;
                }
            }
        }

        // Asignar equipos visitantes al primer partido de cada jornada
        for (int ronda = 0; ronda < TOTAL_JORNADAS; ronda++) {
            if (ronda % 2 == 0) {
                fixture[ronda][0].setEquipoVisitante(TOTAL_EQUIPOS - 1);
            } else {
                fixture[ronda][0].setEquipoVisitante(fixture[ronda][0].getEquipoLocal());
                fixture[ronda][0].setEquipoLocal(TOTAL_EQUIPOS - 1);
            }
        }

        // Asignar equipos visitantes al resto de partidos
        int idEquipoVisitante = TOTAL_EQUIPOS - 2;
        for (int ronda = 0; ronda < TOTAL_JORNADAS; ronda++) {
            for (int partido = 1; partido < TOTAL_PARTIDOS_X_JORNADA; partido++) {
                fixture[ronda][partido].setEquipoVisitante(idEquipoVisitante--);
                if (idEquipoVisitante < 0) {
                    idEquipoVisitante = TOTAL_EQUIPOS - 2;
                }
            }
        }

        return fixture;
    }

    
    //Metodo para guardar los partidos por jornada en la clase PARTIDO
    
    private ArrayList<Jornada> crearListJornadas() {
        ArrayList<Jornada> RobinListJornadas = new ArrayList<>();
        RobinPartidoTemporal[][] partido = generarJornadasFixture();
        int idPartido = 0;
        int idJornada = 0;

        // Método genérico para crear jornadas
        for (int mitad = 0; mitad < 2; mitad++) { // 0: primera mitad, 1: segunda mitad
            for (int i = 0; i < partido.length; i++) {
                ArrayList<Partido> RobinAuxListPartidos = new ArrayList<>();

                for (int j = 0; j < partido[i].length; j++) {
                    int equipoLocal = (mitad == 0) ? partido[i][j].getEquipoLocal() : partido[i][j].getEquipoVisitante();
                    int equipoVisitante = (mitad == 0) ? partido[i][j].getEquipoVisitante() : partido[i][j].getEquipoLocal();

                    // Crear partido y agregarlo a la lista
                    Partido RobinPartido = new Partido();
                    RobinPartido.CrearPartido(idPartido++, equipoLocal, equipoVisitante);
                    RobinAuxListPartidos.add(RobinPartido);
                }

                // Crear jornada con los partidos
                RobinListJornadas.add(new Jornada(idJornada++, RobinAuxListPartidos));
            }
        }

        return RobinListJornadas;
    }


    // Método toString para mostrar los partidos de todas las jornadas
    public void mostrarPartidos(Temporada temporada) {
        RobinPartidoTemporal[][] partidos = generarJornadasFixture();

        // Mostrar la fase de "IDA"
        System.out.println("=== Fase de IDA ===");
        imprimirFase(partidos, false);

        // Mostrar la fase de "VUELTA"
        System.out.println("=== Fase de VUELTA ===");
        imprimirFase(partidos, true);

        // Mostrar detalles de la temporada
        mostrarDetallesTemporada(temporada);
    }

    // Método auxiliar para imprimir una fase (ida o vuelta)
    private void imprimirFase(RobinPartidoTemporal[][] partidos, boolean esVuelta) {
        for (int ronda = 0; ronda < partidos.length; ronda++) {
            System.out.print("Ronda " + (ronda + 1) + ": ");
            for (int partido = 0; partido < partidos[ronda].length; partido++) {
                int local = esVuelta ? partidos[ronda][partido].getEquipoVisitante() : partidos[ronda][partido].getEquipoLocal();
                int visitante = esVuelta ? partidos[ronda][partido].getEquipoLocal() : partidos[ronda][partido].getEquipoVisitante();
                System.out.print("(" + (local + 1) + "-" + (visitante + 1) + ") "); // +1 para que los equipos se muestren desde 1
            }
            System.out.println();
        }
    }

    // Método auxiliar para mostrar los detalles de la temporada
    private void mostrarDetallesTemporada(Temporada temporada) {
        if (temporada != null) {
            System.out.println("\n=== Detalles de la Temporada ===");
            System.out.println(temporada);
        } else {
            System.out.println("No se proporcionó una temporada válida.");
        }
    }
	
}