package dsi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import objeto.*;
import ser.*;

public class LectorFichero {
	
	private Map<String, Ser> seres;
	private Map<String, Objeto> objetos;
	
	public LectorFichero(Map<String, Ser> seres, Map<String, Objeto> objetos) {
		this.seres = seres;
		this.objetos = objetos;
	}
	
	public void leerArchivo(String fichero) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(fichero));
		String lineaFichero;
		
		while((lineaFichero = br.readLine()) != null) {
			leerAtributos(lineaFichero.trim());
		}
		
		br.close();
	}
	
	// Para leer la entrada se asume que cada línea sigue la siguiente estructura de árbol:
	/*
	 personaje
	 -> "no"
        "tiene"
        -> objeto [fin de la frase]
        -> "el favor de"
           personaje [fin de la frase]
        -> "el enojo de"
           personaje [fin de la frase]
     -> "tiene"
        -> objeto [fin de la frase]
        -> "el favor de"
           personaje [fin de la frase]
        -> "el enojo de"
           personaje [fin de la frase]
     -> "apresa"
         personaje [fin de la frase]
	 */
	
	private void leerAtributos(String cadena) {
	    String[] palabras = cadena.split(" ");
	    
	    // palabras[0] = personaje
	    if(palabras[1].equals("no")) {
	    	// palabras[2] = tiene
	    	if(palabras[3].equals("el")) {
	    		if(palabras[4].equals("favor")) {
	    			// palabras[5] = de
	    			// palabras[6] = personaje
	    			// FIN DE FRASE
	    			// Realmente no hace falta procesar este tipo de línea ya que no se asume ningún favor...
	    		}
	    		else {
	    			// palabras[4] = enojo
	    			// palabras[5] = de
	    			// palabras[6] = personaje
	    			// FIN DE FRASE
	    			// Realmente no hace falta procesar este tipo de línea ya que no se asume ningún enojo...
	    		}
	    	}
	    	else {
	    		// palabras[3] = objeto
	    		// FIN DE FRASE
	    		procesarLineaNoTieneObjeto(palabras);
	    	}
	    }
	    else if(palabras[1].equals("tiene")) {
	    	if(palabras[2].equals("el")) {
	    		if(palabras[3].equals("favor")) {
	    			// palabras[4] = de
	    			// palabras[5] = personaje
	    			// FIN DE FRASE
	    			procesarLineaTieneFavor(palabras);
	    		}
	    		else {
	    			// palabras[3] = enojo
	    			// palabras[4] = de
	    			// palabras[5] = personaje
	    			// FIN DE FRASE
	    			procesarLineaTieneEnojo(palabras);
	    		}
	    	}
	    	else {
	    		// palabras[2] = objeto
	    		// FIN DE FRASE
	    		procesarLineaTieneObjeto(palabras);
	    	}
	    }
	    else if(palabras[1].equals("apresa")) {
	    	// palabras[2] = personaje
	    	// FIN DE FRASE
	    	procesarLineaApresa(palabras);
	    }    	
	}
	
	private void procesarLineaNoTieneObjeto(String[] palabras) {
		Ser personaje = seres.get(palabras[0]);
		Objeto objeto = objetos.get(palabras[3]);
		
		if (personaje.getTiene_objeto().contains(objeto)) {
			personaje.removeTiene_objeto(objeto);
		}
	}
	
	private void procesarLineaTieneFavor(String[] palabras) {
		Mortal mortal = (Mortal) seres.get(palabras[0]);
		Dios dios = (Dios) seres.get(palabras[5]);
		
		mortal.addTiene_favor_de(dios);
		dios.addFavorece_a(mortal);
	}
	
	private void procesarLineaTieneEnojo(String[] palabras) {
		Mortal mortal = (Mortal) seres.get(palabras[0]);
		Dios dios = (Dios) seres.get(palabras[5]);
		
		mortal.addTiene_enojo_de(dios);
		dios.addEnojado_por(mortal);
	}
	
	private void procesarLineaTieneObjeto(String[] palabras) {
		Ser personaje = seres.get(palabras[0]);
		Objeto objeto = objetos.get(palabras[2]);
		
		if (!personaje.getTiene_objeto().contains(objeto)) {
			personaje.addTiene_objeto(objeto);
		}
	}
	
	private void procesarLineaApresa(String[] palabras) {
		Ser apresador = seres.get(palabras[0]);
		Ser apresado = seres.get(palabras[2]);
		
		apresador.addApresa_a(apresado);
		apresado.setApresado_por(apresador);
	}	
}
