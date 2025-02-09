package com.logic;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GeneradorTemporadaTest {

	private GeneradorTemporada generadorTemporada;
	private Temporada temporada;

	@BeforeEach
	void setUp() {
		System.out.println("🔹 INICIANDO PRUEBAS UNITARIAS PARA GENERADOR DE TEMPORADA 🔹");
		temporada = new Temporada(1, "Temporada2024", 6);
		generadorTemporada = new GeneradorTemporada();
		generadorTemporada.GenerarTemporada(temporada);
		System.out.println("✅ Temporada generada: " + temporada.getNombre() + " con " + temporada.getCantidadEquipos() + " equipos.\n");
	}

	@Test
	void testGenerarTemporada() {
		System.out.println("🔍 Verificando la creación de la temporada...");
		assertAll(
			() -> assertEquals(1, temporada.getIdTemporada(), "El ID de la temporada no coincide."),
			() -> assertEquals("Temporada2024", temporada.getNombre(), "El nombre de la temporada no coincide."),
			() -> assertEquals(6, temporada.getCantidadEquipos(), "El número de equipos no coincide."),
			() -> assertNotNull(temporada.getListJornadas(), "Las jornadas no deberían ser nulas.")
		);
		System.out.println("✅ Validación completada: La temporada se generó correctamente.\n");
	}

	@Test
	void testCantidadJornadas() {
		System.out.println("🔍 Verificando la cantidad de jornadas...");
		int totalEquipos = temporada.getCantidadEquipos();
		int totalJornadasEsperadas = (totalEquipos - 1) * 2; // Ida y vuelta
		int totalJornadasActuales = temporada.getListJornadas().size();

		System.out.println("➡️ Se esperaban " + totalJornadasEsperadas + " jornadas.");
		System.out.println("➡️ Se generaron " + totalJornadasActuales + " jornadas.");

		assertEquals(totalJornadasEsperadas, totalJornadasActuales, "La cantidad de jornadas no es correcta.");
		System.out.println("✅ Validación completada: La cantidad de jornadas es correcta.\n");
	}

	@Test
	void testCantidadPartidosPorJornada() {
		System.out.println("🔍 Verificando la cantidad de partidos por jornada...");
		int totalEquipos = temporada.getCantidadEquipos();
		int totalPartidosPorJornadaEsperados = totalEquipos / 2;

		for (int i = 0; i < temporada.getListJornadas().size(); i++) {
			int totalPartidosActuales = temporada.getListJornadas().get(i).getListPartidos().size();
			System.out.println("➡️ Jornada " + (i + 1) + ": Se esperaban " + totalPartidosPorJornadaEsperados + " partidos, se generaron " + totalPartidosActuales);
			assertEquals(totalPartidosPorJornadaEsperados, totalPartidosActuales, "Cada jornada debe tener " + totalPartidosPorJornadaEsperados + " partidos.");
		}
		System.out.println("✅ Validación completada: Todas las jornadas tienen la cantidad correcta de partidos.\n");
	}

	@Test
	void testEquiposUnicosPorJornada() {
		System.out.println("🔍 Verificando que los equipos no se repitan en una misma jornada...");
		for (int i = 0; i < temporada.getListJornadas().size(); i++) {
			ArrayList<Integer> equiposVistos = new ArrayList<>();
			for (Partido partido : temporada.getListJornadas().get(i).getListPartidos()) {
				assertFalse(equiposVistos.contains(partido.getEquipoLoc()), "El equipo local ya jugó en esta jornada");
				assertFalse(equiposVistos.contains(partido.getEquipoVis()), "El equipo visitante ya jugó en esta jornada");
				equiposVistos.add(partido.getEquipoLoc());
				equiposVistos.add(partido.getEquipoVis());
			}
			System.out.println("✅ Jornada " + (i + 1) + ": Todos los equipos son únicos.");
		}
		System.out.println("✅ Validación completada: No hay equipos repetidos en las jornadas.\n");
	}

	@Test
	void testNoEquiposDuplicadosEnPartidos() {
		System.out.println("🔍 Verificando que no haya equipos jugando contra sí mismos...");
		for (int i = 0; i < temporada.getListJornadas().size(); i++) {
			for (Partido partido : temporada.getListJornadas().get(i).getListPartidos()) {
				assertNotEquals(partido.getEquipoLoc(), partido.getEquipoVis(), "No puede haber un equipo jugando contra sí mismo");
			}
			System.out.println("✅ Jornada " + (i + 1) + ": Todos los partidos son entre equipos diferentes.");
		}
		System.out.println("✅ Validación completada: No hay equipos enfrentándose a sí mismos.\n");
	}
}
