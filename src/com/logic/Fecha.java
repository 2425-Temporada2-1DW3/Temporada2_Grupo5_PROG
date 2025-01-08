package com.logic;

import java.io.Serializable;
import java.util.Objects;

public class Fecha implements Comparable<Fecha>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3143473867896687124L;
	private int dia;
	private int mes;
	private int ano;

	public Fecha() {
		dia = 1;
		mes = 1;
		ano = 2024;
	}

	// personalizado
	public Fecha(int d, int m, int a) {

		if (d > 31 || d < 1) {
			this.dia = 1; // Día fuera de rango
		} else {
			this.dia = d;
		}

		if (m > 12 || m < 1) {
			this.mes = 1; // Mes fuera de rango
		} else {
			this.mes = m;
		}

		if (a == 0) {
			this.ano = 2024; // Año inválido (puedes definir una lógica distinta si 0 es permitido)
		} else {
			this.ano = a;

		}

	}

	// constructor copia
	public Fecha(Fecha f) {
		this.dia = f.dia;
		this.mes = f.mes;
		this.ano = f.ano;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int d) {
		if (d < 31 || d >= 1) {
			this.dia = d;
		} else {
			dia = 1;
		}

	}

	public int getMes() {
		return mes;
	}

	public void setMes(int m) {
		if (m < 12 || m >= 1) {
			this.mes = m;
		} else {
			mes = m;
		}

	}

	public int getAno() {
		return ano;
	}

	public void setAno(int a) {
		if (a == 0) {
			this.ano = 2024; // Año inválido (puedes definir una lógica distinta si 0 es permitido)
		} else {
			this.ano = a;

		}
	}

	@Override
	public String toString() {
		return (dia + "/" + mes + "/" + ano);
	}

	@Override
	public int hashCode() {
		return Objects.hash(ano, dia, mes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fecha other = (Fecha) obj;
		return ano == other.ano && dia == other.dia && mes == other.mes;
	}

	@Override
	public int compareTo(Fecha other) {
		if (this.ano > other.ano) {
			return 1;
		} else if (this.ano < other.ano) {
			return -1;
		} else {
			if (this.mes > other.mes) {
				return 1;
			} else if (this.mes < other.mes) {
				return -1;
			} else {
				if (this.dia > other.mes) {
					return 1;
				} else if (this.dia < other.mes) {
					return -1;
				}
			}
		}
		return 0;
	}

}
