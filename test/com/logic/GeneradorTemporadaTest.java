package com.logic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // Ordenamos las pruebas por el número de orden
class GeneradorTemporadaTest {

    private GeneradorTemporada generadorTemporada;
    private Temporada temporada;

    @BeforeEach
    void setUp() {
    	System.out.println("=============================================");
        System.out.println("INICIANDO PRUEBAS UNITARIAS PARA GENERADOR DE TEMPORADA");
        System.out.println("=============================================");
        temporada = new Temporada(1, "Temporada2024", 6);  // Inicializamos la temporada con 6 equipos
        generadorTemporada = new GeneradorTemporada();
        generadorTemporada.GenerarTemporada(temporada);
        System.out.println("Temporada generada: " + temporada.getNombre() + " con " + temporada.getCantidadEquipos() + " equipos.\n");
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void testGenerarTemporada() {
        System.out.println("Verificando la creación de la temporada...");
        assertAll(
            () -> assertEquals(1, temporada.getIdTemporada(), "El ID de la temporada no coincide."),
            () -> assertEquals("Temporada2024", temporada.getNombre(), "El nombre de la temporada no coincide."),
            () -> assertEquals(6, temporada.getCantidadEquipos(), "El número de equipos no coincide."),
            () -> assertNotNull(temporada.getListJornadas(), "Las jornadas no deberían ser nulas.")
        );
        System.out.println("Validación completada: La temporada se generó correctamente.\n");
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void testCantidadJornadas() {
        System.out.println("Verificando la cantidad de jornadas...");
        int totalEquipos = temporada.getCantidadEquipos();
        int totalJornadasEsperadas = (totalEquipos - 1) * 2; // Ida y vuelta
        int totalJornadasActuales = temporada.getListJornadas().size();

        System.out.println("Se esperaban " + totalJornadasEsperadas + " jornadas.");
        System.out.println("Se generaron " + totalJornadasActuales + " jornadas.");

        assertEquals(totalJornadasEsperadas, totalJornadasActuales, "La cantidad de jornadas no es correcta.");
        System.out.println("Validación completada: La cantidad de jornadas es correcta.\n");
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void testCantidadPartidosPorJornada() {
        System.out.println("Verificando la cantidad de partidos por jornada...");

        // Primero mostramos las fechas con los partidos
        for (int i = 0; i < temporada.getListJornadas().size(); i++) {
            int totalPartidosActuales = temporada.getListJornadas().get(i).getListPartidos().size();

            // Construir los partidos de la jornada en el formato requerido
            StringBuilder partidosFormato = new StringBuilder();
            for (int j = 0; j < totalPartidosActuales; j++) {
                Partido partido = temporada.getListJornadas().get(i).getListPartidos().get(j);
                partidosFormato.append(partido.getEquipoLoc()).append("-").append(partido.getEquipoVis());

                if (j < totalPartidosActuales - 1) {
                    partidosFormato.append("   "); // Espacio entre partidos
                }

                // Si es el final de la fila (3 partidos por jornada), agrega salto de línea
                if ((j + 1) % 4 == 0 && i == 6) {
                	partidosFormato.append("\n");
                }
            }

            // Imprimir la fecha con los partidos
            System.out.println("Fecha " + (i + 1) + ": " + partidosFormato.toString());
        }

        // Ahora mostramos los resultados de las jornadas
        for (int i = 0; i < temporada.getListJornadas().size(); i++) {
            int totalEquipos = temporada.getCantidadEquipos();
            int totalPartidosPorJornadaEsperados = totalEquipos / 2;
            int totalPartidosActuales = temporada.getListJornadas().get(i).getListPartidos().size();

            // Imprimir el resultado esperado por jornada
            System.out.println("Jornada " + (i + 1) + ": Se esperaban " + totalPartidosPorJornadaEsperados + " partidos, se generaron " + totalPartidosActuales);

            // Verifica que la cantidad de partidos por jornada sea correcta
            assertEquals(totalPartidosPorJornadaEsperados, totalPartidosActuales, "Cada jornada debe tener " + totalPartidosPorJornadaEsperados + " partidos.");
        }

        System.out.println("Validación completada: Todas las jornadas tienen la cantidad correcta de partidos.\n");
    }


    @Test
    @org.junit.jupiter.api.Order(4)
    void testEquiposUnicosPorJornada() {
        System.out.println("Verificando que los equipos no se repitan en una misma jornada...");
        for (int i = 0; i < temporada.getListJornadas().size(); i++) {
            ArrayList<Integer> equiposVistos = new ArrayList<>();
            for (Partido partido : temporada.getListJornadas().get(i).getListPartidos()) {
                // Comprobar si el equipo local ya ha jugado
                if (equiposVistos.contains(partido.getEquipoLoc())) {
                    System.out.println("Error en Jornada " + (i + 1) + ": El equipo local " + partido.getEquipoLoc() + " ya jugó en esta jornada.");
                    assertFalse(true, "El equipo local " + partido.getEquipoLoc() + " ya jugó en esta jornada.");
                }
                // Comprobar si el equipo visitante ya ha jugado
                if (equiposVistos.contains(partido.getEquipoVis())) {
                    System.out.println("Error en Jornada " + (i + 1) + ": El equipo visitante " + partido.getEquipoVis() + " ya jugó en esta jornada.");
                    assertFalse(true, "El equipo visitante " + partido.getEquipoVis() + " ya jugó en esta jornada.");
                }
                equiposVistos.add(partido.getEquipoLoc());
                equiposVistos.add(partido.getEquipoVis());
            }
            System.out.println("Jornada " + (i + 1) + ": Todos los equipos son únicos.");
        }
        System.out.println("Validación completada: No hay equipos repetidos en las jornadas.\n");
    }


    @Test
    @org.junit.jupiter.api.Order(5)
    void testNoEquiposDuplicadosEnPartidos() {
        System.out.println("Verificando que no haya equipos jugando contra sí mismos...");
        for (int i = 0; i < temporada.getListJornadas().size(); i++) {
            for (Partido partido : temporada.getListJornadas().get(i).getListPartidos()) {
                // Comprobar si un equipo está jugando contra sí mismo
                if (partido.getEquipoLoc() == (partido.getEquipoVis())) {
                    System.out.println("Error en Jornada " + (i + 1) + ": El equipo " + partido.getEquipoLoc() + " está jugando contra sí mismo.");
                    assertFalse(true, "No puede haber un equipo jugando contra sí mismo. Equipo: " + partido.getEquipoLoc());
                }
            }
            System.out.println("Jornada " + (i + 1) + ": Todos los partidos son entre equipos diferentes.");
        }
        System.out.println("Validación completada: No hay equipos enfrentándose a sí mismos.\n");
    }


    @Test
    @org.junit.jupiter.api.Order(6)
    void testGenerarTemporadaConMenosDe6Equipos() {
        System.out.println("Verificando la generación de temporada con menos de 6 equipos...");
        temporada = new Temporada(2, "Temporada2024", 4);  // Generamos temporada con 4 equipos
        generadorTemporada = new GeneradorTemporada();

        try {
            generadorTemporada.GenerarTemporada(temporada);
            fail("Se esperaba un error debido al número de equipos insuficiente (menos de 6).");
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR: El número de equipos es inferior al mínimo permitido (6). No se puede generar la temporada.");
        }
        System.out.println("Validación completada: No se pudo generar la temporada con menos de 6 equipos.\n");
    }
}
