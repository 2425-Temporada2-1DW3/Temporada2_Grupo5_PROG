package com.structure;

import java.util.ArrayList;

import com.logic.CreadorXML;

public class TestGeneradorXML {
	
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    	ArrayList<String> xmlList = new ArrayList<String>();
		 	String nombreJugador = "little";
	        String apellidoJugador = "john";
	        int edad = 20;
	        int numeroDorsal = 69;
	        CreadorXML creador = new CreadorXML();

	        /*
	         * EXPLICACION :
	         * la funcion "FormatearXML" se usa para formatear los datos que quieras, devuelve strings
	         * 
	         * - el primer argumento es un string, puede contener lo que quiera
	         * 
	         * - el segundo argumento es un boolean:
	         * 		si es false, el string sera usado como un <header>
	         * 		si es true, el string sera usado como un dato normal 
	         * 
	         * - el tercer argumento es un integer, depende de el valor 0-n se pondran 0-n tabuladores (para indentar el xml)
	         * 
	         *  ej 1: 
	         *  	FormatearXML("hola",false,1) 
	         *  
	         *  devolvera : "	<hola>"
	         *  
	         *  ej 2: 
	         *  	int variable = 1
	         *  	FormatearXML(variable,true,1)
	         *  devolvera : "	1"
	         * 
	         * 
	         * la funcion "GenerarXML" se usa para crear un archivo xml
	         * 
	         * - el primer argumento es un ArrayList con los datos que quieras (probablemente los que hayas generado antes)
	         * 
	         * - el segundo argumento es el nombre del archivo
	         * 
	         * */
	        xmlList.add(creador.FormatearXML("jugador", false, 0));
	        xmlList.add(creador.FormatearXML("nombre", false, 1));
	        xmlList.add(creador.FormatearXML(nombreJugador, true, 4));
	        xmlList.add(creador.FormatearXML("/nombre", false, 1));
	        xmlList.add(creador.FormatearXML("apellido", false, 1));
	        xmlList.add(creador.FormatearXML(apellidoJugador, true, 2));
	        xmlList.add(creador.FormatearXML("/apellido", false, 1));
	        xmlList.add(creador.FormatearXML("/jugador", false, 0));
	        creador.GenerarXML(xmlList,"test");
	        
	}

}
