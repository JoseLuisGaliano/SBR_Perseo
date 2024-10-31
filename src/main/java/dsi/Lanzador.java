package dsi;

import java.io.IOException;
import java.util.HashMap;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import objeto.*;
import ser.*;

public class Lanzador {
	
	public static void main(String[] args) {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		
		KieSession kSession = kContainer.newKieSession("ksession-rules-dsi");
				
		// Seres
		// Mortales
		Semidios sd = new Semidios("Perseo");
		Humano h1 = new Humano("Casiopea");
		Humano h2 = new Humano("Danae");
		Humano h3 = new Humano("Cefeo");
		Humano h4 = new Humano("Andromeda");
		Criatura c1 = new Criatura("Ceto");		
		// Inmortales	
		DeidadMayor d1 = new DeidadMayor("Zeus");
		DeidadMayor d2 = new DeidadMayor("Poseidon");
		DeidadMayor d3 = new DeidadMayor("Hades");
		DeidadMayor d4 = new DeidadMayor("Hermes");
		DeidadMayor d5 = new DeidadMayor("Hefesto");
		DeidadMayor d6 = new DeidadMayor("Atenea");
		DeidadMenor d7 = new DeidadMenor("Las Grayas");
		Ninfa n1 = new Ninfa("Doris");
		Ninfa n2 = new Ninfa("Ninfas");
		Gorgona g1 = new Gorgona("Medusa");
		
		// Objetos
		// Especiales
		ObjetoEspecial o1 = new ObjetoEspecial("Cabeza de Medusa");
		o1.setTiene_propiedad(Propiedad.PETRIFICAR);
		ObjetoEspecial o2 = new ObjetoEspecial("Sandalias Aladas");
		o2.setTiene_propiedad(Propiedad.VUELO);
		ObjetoEspecial o3 = new ObjetoEspecial("Casco de Hades");
		o3.setTiene_propiedad(Propiedad.INVISIBILIDAD);
		ObjetoEspecial o4 = new ObjetoEspecial("Escudo Espejo");
		o4.setTiene_propiedad(Propiedad.REFLEJO);
		ObjetoEspecial o5 = new ObjetoEspecial("Pegaso");
		o5.setTiene_propiedad(Propiedad.VUELO);
		// Normales
		Objeto o6 = new ObjetoNormal("Ojo de Graya");
		Objeto o7 = new ObjetoNormal("Zurron Magico");
		Objeto o8 = new ObjetoNormal("Hoz de Acero");
		Objeto o9 = new ObjetoNormal("Espada Indestructible");
		Objeto o10 = new ObjetoNormal("Bridas de Oro");
		
		// PREMISAS (Cosas que, a no ser que se indique lo contrario en el fichero, se dan por ciertas)
		d3.addTiene_objeto(o3);
		d4.addTiene_objeto(o8);
		d5.addTiene_objeto(o9);
		d5.addTiene_objeto(o10);
		d6.addTiene_objeto(o4);
		d7.addTiene_objeto(o6);
		g1.addTiene_objeto(o1);
		n2.addTiene_objeto(o2);
		n2.addTiene_objeto(o7);
		
		// Añadimos a mapas para el lector de fichero
		HashMap<String, Ser> seres = new HashMap<String, Ser>();
		HashMap<String, Objeto> objetos = new HashMap<String, Objeto>();
		seres.put("Perseo", sd);
		seres.put("Casiopea", h1);
		seres.put("Danae", h2);
		seres.put("Cefeo", h3);
		seres.put("Andromeda", h4);
		seres.put("Ceto", c1);
		seres.put("Zeus", d1);
		seres.put("Poseidon", d2);
		seres.put("Hades", d3);
		seres.put("Hermes", d4);
		seres.put("Hefesto", d5);
		seres.put("Atenea", d6);
		seres.put("Las Grayas", d7);
		seres.put("Doris", n1);
		seres.put("Ninfas", n2);
		seres.put("Medusa", g1);
		objetos.put("Cabeza de Medusa", o1);
		objetos.put("Sandalias Aladas", o2);
		objetos.put("Casco de Hades", o3);
		objetos.put("Escudo Espejo", o4);
		objetos.put("Pegaso", o5);
		objetos.put("Ojo de Graya", o6);
		objetos.put("Zurron Magico", o7);
		objetos.put("Hoz de Acero", o8);
		objetos.put("Espada Indestructible", o9);
		objetos.put("Bridas de Oro", o10);
		
		// Llamamos al lector de fichero
		LectorFichero lf = new LectorFichero(seres, objetos);
		try {
			lf.leerArchivo("entrada.txt");
		} catch (IOException e) {
			e.printStackTrace();
		} // no funcional ahora mismo
		
		
		// Añadimos a la base de conocimiento
		kSession.insert(sd);
		kSession.insert(h1);
		kSession.insert(h2);
		kSession.insert(h3);
		kSession.insert(h4);
		kSession.insert(d1);
		kSession.insert(d2);
		kSession.insert(d3);
		kSession.insert(d4);
		kSession.insert(d5);
		kSession.insert(d6);
		kSession.insert(d7);
		kSession.insert(c1);
		kSession.insert(n1);
		kSession.insert(n2);
		kSession.insert(g1);
		kSession.insert(o1);
		kSession.insert(o2);
		kSession.insert(o3);
		kSession.insert(o4);
		kSession.insert(o5);
		kSession.insert(o6);
		kSession.insert(o7);
		kSession.insert(o8);
		kSession.insert(o9);
		kSession.insert(o10);
		
		kSession.fireAllRules();
		
		System.out.println("OK");
		
	}
}
