package com.structure;

import com.logic.CreadorXML;

public class testXML {
	
    public static void main(String[] args) {
        CreadorXML XML = new CreadorXML();
        
        /*
         * add(String contenido, boolean esDato,int indentacion
         * si esDato, se tratara como si fuera un dato, sino se tratara como un <elemento>
         * la indentacion es cuantas tabulaciones pones en el la linea
         * 
         * 
         * 
         */
        
        XML.add("jornadas", false, 0);
        	XML.add("partido", false, 1);
        		XML.add("partido 1", true, 2);
        	XML.add("partido", false, 1);
        	XML.add("partido", false, 1);
    			XML.add("partido 2", true, 2);
    		XML.add("partido", false, 1);
        XML.add("jornadas", false, 0);

        
        XML.file("test"); // solo se necesita el nombre no la extension
        XML.clear(); // resetea los arraylists internos
    }
}