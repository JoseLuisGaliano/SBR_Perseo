package dsi;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import org.kie.api.runtime.rule.FactHandle;
import org.kie.api.runtime.KieSession;

import java.util.List;

import objeto.*;
import ser.*;
import relaciones.*;

public class LectorFichero {
	
	private Map<String, Ser> seres;
	private Map<String, Objeto> objetos;
	private List<Posee> posesiones;
	private KieSession ksession;
	
	public LectorFichero(Map<String, Ser> seres, Map<String, Objeto> objetos, List<Posee> posesiones, KieSession ksession) {
		this.seres = seres;
		this.objetos = objetos;
		this.posesiones = posesiones;
		this.ksession = ksession;
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
	    
	    // Caso especial: el personaje coge palabras[0] y también palabras[1]
	    if (palabras[0].equals("Las")) {
	        String[] nuevoPalabras = new String[palabras.length - 1];
	    	// Juntamos las dos primeras palabras como si fuesen una
	        palabras[0] = palabras[0] + " " + palabras[1];
	        nuevoPalabras[0] = palabras[0];
	        // Desplazamos el resto de palabras
	        for(int i = 2; i < palabras.length; i++) {
	        	nuevoPalabras[i-1] = palabras[i];
	        }
	        palabras = nuevoPalabras;
	    }
	    
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
	    		// palabras[3] - palabras[n] = objeto
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
	    		// palabras[2] - palabras[n] = objeto
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
		Objeto objeto = objetos.get(obtenerNombreObjeto(palabras, 3));
		
		Posee borrar = null;
		for(Posee p : posesiones) {
			if(p.getPoseedor().equals(personaje) && p.getObjeto().equals(objeto)) {
				borrar = p; 
				break;
			}
		}		
		posesiones.remove(borrar);
		
		FactHandle f = ksession.getFactHandle(borrar);
		if(f != null) ksession.delete(f);
	}
	
	private void procesarLineaTieneFavor(String[] palabras) {
		Mortal mortal = (Mortal) seres.get(palabras[0]);
		Dios dios = (Dios) seres.get(palabras[5]);
		
		Favor f = new Favor(dios, mortal);
		ksession.insert(f);
	}
	
	private void procesarLineaTieneEnojo(String[] palabras) {
		Mortal mortal = (Mortal) seres.get(palabras[0]);
		Dios dios = (Dios) seres.get(palabras[5]);
		
		Enojo e = new Enojo(mortal, dios);
		ksession.insert(e);
	}
	
	private void procesarLineaTieneObjeto(String[] palabras) {
		Ser personaje = seres.get(palabras[0]);
		Objeto objeto = objetos.get(obtenerNombreObjeto(palabras, 2));
		
		Posee p = new Posee(personaje, objeto);
		ksession.insert(p);
	}
	
	private void procesarLineaApresa(String[] palabras) {
		Ser apresador = seres.get(palabras[0]);
		Ser apresado = seres.get(palabras[2]);
		
		Apresa a = new Apresa(apresador, apresado);
		ksession.insert(a);
	}	
	
	private String obtenerNombreObjeto(String[] palabras, int inicio) {
		StringBuilder objetoBuilder = new StringBuilder();
        for (int i = inicio; i < palabras.length; i++) {
            if (i > inicio) {
                objetoBuilder.append(" ");
            }
            objetoBuilder.append(palabras[i]);
        }
        return objetoBuilder.toString();
	}
}
