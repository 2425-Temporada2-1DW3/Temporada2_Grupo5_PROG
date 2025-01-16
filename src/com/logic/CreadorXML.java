package com.logic;

import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreadorXML {
	String indentacionString;
	
    public String FormatearXML(String contenido, boolean esDato, int indentacion) {
    	indentacionString = "	".repeat(indentacion);

        if (esDato) {
            contenido = indentacionString + contenido;
           return contenido;
        } else {
            // Add opening tags
            contenido = indentacionString+"<" + contenido + ">";
            return contenido;
        }
    }
    public void GenerarXML(ArrayList list, String archivo) {

	    String nombreArchivo = archivo+".xml";
	    
	    try (FileWriter fichero = new FileWriter(nombreArchivo);
	         BufferedWriter bw = new BufferedWriter(fichero)) {
	    	for(int i=0;i<list.size();i++) {
		    		
		        bw.write((String) list.get(i));
		        bw.newLine();

	    	}
	    	
	    } catch (IOException e) {
	        // Manejo de errores
	        System.err.println("Error al crear el archivo: " + e.getMessage());
	        e.printStackTrace();
	    }
    	
    }
}
