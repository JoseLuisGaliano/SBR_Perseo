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
		
		// Parseamos la condición de parada
		lineaFichero = br.readLine(); // Primera línea: Condición de parada
		leerCondicionParada(lineaFichero.trim());
		lineaFichero = br.readLine(); // Segunda línea: "Condiciones:", la pasamos ya que no se parsea
				
		// Parseamos las condiciones adicionales
		while((lineaFichero = br.readLine()) != null) {
			leerCondiciones(lineaFichero.trim());
		}
		
		br.close();
	}
	
	// Para extraer la condición de parada, se asume que la estructura de la primera línea sigue este árbol:
	/* 
	 * "¿Puede"
	 * personaje
	 * -> "liberar a"
	 *    personaje [fin de la frase]
	 * -> "tener"
	 *    -> "Capacidad"
	 *       propiedad [fin de la frase]
	 *    -> objeto [fin de la frase]
	 *   
	 * IMPORTANTE: La frase siempre acaba con ?, carácter que hay que eliminar
	 */
	private void leerCondicionParada(String cadena) {
		String[] palabras = cadena.split(" ");
		
		// palabras[0] = ¿Puede
		
		// Caso especial: el personaje coge palabras[1] y también palabras[2]
	    if (palabras[1].equals("Las")) {
	        String[] nuevoPalabras = new String[palabras.length - 1];
	        nuevoPalabras[0] = palabras[0];
	    	// Juntamos las dos primeras palabras como si fuesen una
	        palabras[1] = palabras[1] + " " + palabras[2];
	        nuevoPalabras[1] = palabras[1];
	        // Desplazamos el resto de palabras
	        for(int i = 3; i < palabras.length; i++) {
	        	nuevoPalabras[i-1] = palabras[i];
	        }
	        palabras = nuevoPalabras;
	    }
	    
	    // palabras[1] = personaje
	    // Caso especial: Si el personaje es "alguno", se pregunta por que cualquier semidios pueda liberar a Andromeda (escenario 3-7)
	    if(palabras[1].equals("alguno")) {
	    	procesarLineaObjetivoLiberarTodos(palabras);
	    	return;
	    }
	    
	    // Casos comunes
	    if(palabras[2].equals("liberar")) {
	    	// palabras[3] = a
	    	// palabras[4] = personaje
	    	if(palabras[4].equals("Las")) palabras[4] = palabras[4] + " " + palabras[5];
	    	// FIN DE FRASE
	    	procesarLineaObjetivoLiberar(palabras);
	    }
	    else {
	    	// palabras[2] = tener
	    	if(palabras[3].equals("Capacidad")) {
	    		// palabras[4] = propiedad
	    		// FIN DE FRASE
	    		procesarLineaObjetivoTenerCapacidad(palabras);
	    	}
	    	else {
	    		// palabras[3] - palabras[n] = objeto
	    		// FIN DE FRASE
	    		procesarLineaObjetivoTenerObjeto(palabras);
	    	}
	    }
	    
	}
	
	
	// Una vez pasadas las dos primeras líneas, se asume que cada línea sigue la siguiente estructura de árbol:
	/*
	 * personaje
	 * -> "no"
     *    "tiene"
     *    -> objeto [fin de la frase]
     *    -> "el favor de"
     *       personaje [fin de la frase]
     *    -> "el enojo de"
     *       personaje [fin de la frase]
     * -> "tiene"
     *    -> objeto [fin de la frase]
     *    -> "el favor de"
     *       personaje [fin de la frase]
     *    -> "el enojo de"
     *       personaje [fin de la frase]
     * -> "apresa"
     *     personaje [fin de la frase]
	 */
	
	private void leerCondiciones(String cadena) {
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
	    			if(palabras[5].equals("Las")) palabras[5] = palabras[5] + " " + palabras[6];
	    			// FIN DE FRASE
	    			procesarLineaTieneFavor(palabras);
	    		}
	    		else {
	    			// palabras[3] = enojo
	    			// palabras[4] = de
	    			// palabras[5] = personaje
	    			if(palabras[5].equals("Las")) palabras[5] = palabras[5] + " " + palabras[6];
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
	
	
	// LINEAS DE CONDICION DE PARADA
	
	private void procesarLineaObjetivoLiberar(String[] palabras) {
		Ser personaje_liberador = seres.get(palabras[1]);
		Ser personaje_liberado = seres.get(quitarInterrogacion(palabras[4]));
		
		List<String> personajes = new ArrayList<String>();
		personajes.add(personaje_liberador.getNombre_ser());
		CondicionParada cp = new CondicionParada(TiposParada.LIBERAR_PERSONAJE, personajes, personaje_liberado.getNombre_ser());
		ksession.setGlobal("condicionParada", cp);
	}
	
	private void procesarLineaObjetivoLiberarTodos(String[] palabras) {
		Ser personaje_liberador1 = seres.get("Perseo");
		Ser personaje_liberador2 = seres.get("Teseo");
		Ser personaje_liberador3 = seres.get("Heracles");
		Ser personaje_liberado = seres.get(quitarInterrogacion(palabras[4]));
		
		List<String> personajes = new ArrayList<String>();
		personajes.add(personaje_liberador1.getNombre_ser());
		personajes.add(personaje_liberador2.getNombre_ser());
		personajes.add(personaje_liberador3.getNombre_ser());
		CondicionParada cp = new CondicionParada(TiposParada.LIBERAR_PERSONAJE, personajes, personaje_liberado.getNombre_ser());
		ksession.setGlobal("condicionParada", cp);
	}
	
	private void procesarLineaObjetivoTenerCapacidad(String[] palabras) {
		Ser personaje = seres.get(palabras[1]);
		Propiedad propiedad = Propiedad.valueOf(quitarInterrogacion(palabras[4]).toUpperCase());
		
		List<String> personajes = new ArrayList<String>();
		personajes.add(personaje.getNombre_ser());
		CondicionParada cp = new CondicionParada(TiposParada.OBTENER_CAPACIDAD, personajes, propiedad.toString());
		ksession.setGlobal("condicionParada", cp);
	}
	
	private void procesarLineaObjetivoTenerObjeto(String[] palabras) {
		Ser personaje = seres.get(palabras[1]);
		Objeto objeto = objetos.get(quitarInterrogacion(obtenerNombreObjeto(palabras, 3)));
		
		List<String> personajes = new ArrayList<String>();
		personajes.add(personaje.getNombre_ser());
		CondicionParada cp = new CondicionParada(TiposParada.OBTENER_OBJETO, personajes, objeto.getNombre_objeto());
		ksession.setGlobal("condicionParada", cp);
	}
	
	// LINEAS DE CONDICIONES ADICIONALES 

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
	
	// FUNCIONES AUXILIARES
	
	private String quitarInterrogacion(String palabra) {
		return palabra.substring(0, palabra.length() - 1);
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
