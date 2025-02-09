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
		temporada = new Temporada(1, "Temporada2024", 6);
		generadorTemporada = new GeneradorTemporada();
		generadorTemporada.GenerarTemporada(temporada);
	}

	@Test
	void testGenerarTemporada() {
		assertAll(
			() -> assertEquals(1, temporada.getIdTemporada()),
			() -> assertEquals("Temporada2024", temporada.getNombre()),
			() -> assertEquals(6, temporada.getCantidadEquipos()),
			() -> assertNotNull(temporada.getListJornadas(), "Las jornadas no deberían ser nulas")
		);
	}

	@Test
	void testCantidadJornadas() {
		int totalEquipos = temporada.getCantidadEquipos();
		int totalJornadasEsperadas = (totalEquipos - 1) * 2; // Considerando ida y vuelta (CORRECTO)
		assertEquals(totalJornadasEsperadas, temporada.getListJornadas().size(), "La cantidad de jornadas no es correcta");
	}

	@Test
	void testCantidadPartidosPorJornada() {
		int totalEquipos = temporada.getCantidadEquipos();
		int totalPartidosPorJornadaEsperados = totalEquipos / 2;

		for (Jornada jornada : temporada.getListJornadas()) {
			assertEquals(totalPartidosPorJornadaEsperados, jornada.getListPartidos().size(), "Cada jornada debe tener " + totalPartidosPorJornadaEsperados + " partidos.");
		}
	}

	@Test
	void testEquiposUnicosPorJornada() {
		for (Jornada jornada : temporada.getListJornadas()) {
			ArrayList<Integer> equiposVistos = new ArrayList<>();
			for (Partido partido : jornada.getListPartidos()) {
				assertFalse(equiposVistos.contains(partido.getEquipoLoc()), "El equipo local ya jugó en esta jornada");
				assertFalse(equiposVistos.contains(partido.getEquipoVis()), "El equipo visitante ya jugó en esta jornada");
				equiposVistos.add(partido.getEquipoLoc());
				equiposVistos.add(partido.getEquipoVis());
			}
		}
	}

	@Test
	void testNoEquiposDuplicadosEnPartidos() {
		for (Jornada jornada : temporada.getListJornadas()) {
			for (Partido partido : jornada.getListPartidos()) {
				assertNotEquals(partido.getEquipoLoc(), partido.getEquipoVis(), "No puede haber un equipo jugando contra sí mismo");
			}
		}
	}
}
