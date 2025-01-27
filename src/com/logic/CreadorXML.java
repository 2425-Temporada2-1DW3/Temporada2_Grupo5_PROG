package com.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CreadorXML {
	
    private ArrayList<String> elementosXML; // List to store the XML content
    private ArrayList<String> contenidoXMLFormateado; // List to store the XML content

    public CreadorXML() {
    	elementosXML = new ArrayList<>(); // Initialize the list
        contenidoXMLFormateado = new ArrayList<>(); // Initialize the list

    }
    
    public void add(String contenido, boolean esDato, int indentacion) {
    	String indentacionString = "	".repeat(indentacion);
    	String contenidoFormateado = null;
    	
        if (esDato) {
        	contenidoFormateado = indentacionString + contenido;
        	
        } else {
            // Add opening tags
            int count = Collections.frequency(elementosXML, contenido);
            
            if (count % 2 != 0) {
            	contenidoFormateado = indentacionString+"</" + contenido + ">";
            } else {
            	contenidoFormateado = indentacionString+"<" + contenido + ">";
            }
            elementosXML.add(contenido);
            
        }
        
    	contenidoXMLFormateado.add(contenidoFormateado);
    	
    }

    public void file(String archivo) {
	    String nombreArchivo = archivo+".xml";

	    try (FileWriter fichero = new FileWriter(nombreArchivo);
	         BufferedWriter bw = new BufferedWriter(fichero)) {
	    	for(int i=0;i<contenidoXMLFormateado.size();i++) {

		        bw.write((String) contenidoXMLFormateado.get(i));
		        bw.newLine();

	    	}

	    } catch (IOException e) {
	        // Manejo de errores
	        System.err.println("Error al crear el archivo: " + e.getMessage());
	        e.printStackTrace();
	    }

    }

    public void clear() {
    	elementosXML.clear();
    	contenidoXMLFormateado.clear();
    }

	public void addElement(String string, boolean b, int i) {
		// TODO Auto-generated method stub
		
	}
    
}
