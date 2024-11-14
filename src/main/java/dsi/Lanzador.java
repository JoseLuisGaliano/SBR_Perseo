package dsi;

import java.io.IOException;
import java.util.HashMap;
import java.util.ArrayList;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import objeto.*;
import ser.*;
import relaciones.*;

public class Lanzador {
	public static void main(String[] args) {

		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks.getKieClasspathContainer();
		
		KieSession kSession = kContainer.newKieSession("ksession-rules-dsi");
		
		// Seres
		// Mortales
		Semidios s1 = new Semidios("Perseo");
		Semidios s2 = new Semidios("Teseo");
		Semidios s3 = new Semidios("Ariadna");
		Humano h1 = new Humano("Casiopea");
		Humano h2 = new Humano("Danae");
		Humano h3 = new Humano("Cefeo");
		Humano h4 = new Humano("Andromeda");
		Humano h5 = new Humano("Icaro");
		Humano h6 = new Humano("Dedalo");
		Criatura c1 = new Criatura("Ceto");
		Criatura c2 = new Criatura("Minotauro");
		// Inmortales	
		DeidadMayor d1 = new DeidadMayor("Zeus");
		DeidadMayor d2 = new DeidadMayor("Poseidon");
		DeidadMayor d3 = new DeidadMayor("Hades");
		DeidadMayor d4 = new DeidadMayor("Hermes");
		DeidadMayor d5 = new DeidadMayor("Hefesto");
		DeidadMayor d6 = new DeidadMayor("Atenea");
		DeidadMenor d7 = new DeidadMenor("Las Grayas");
		DeidadMenor d8 = new DeidadMenor("Minos");
		Ninfa n1 = new Ninfa("Doris");
		Ninfa n2 = new Ninfa("Las Ninfas");
		Gorgona g1 = new Gorgona("Medusa");
		
		// Objetos
		// Especiales
		ObjetoEspecial o1 = new ObjetoEspecial("Cabeza de Medusa");
		o1.setTiene_propiedad(Propiedad.PETRIFICAR);
		ObjetoEspecial o2 = new ObjetoEspecial("Sandalias Aladas");
		o2.setTiene_propiedad(Propiedad.VUELO);
		ObjetoEspecial o3 = new ObjetoEspecial("Casco de Hades");
		o3.setTiene_propiedad(Propiedad.INVISIBILIDAD);
		ObjetoEspecial o4 = new ObjetoEspecial("Escudo de Bronce");
		o4.setTiene_propiedad(Propiedad.REFLEJO);
		ObjetoEspecial o5 = new ObjetoEspecial("Pegaso");
		o5.setTiene_propiedad(Propiedad.VUELO);
		ObjetoEspecial o11 = new ObjetoEspecial("Anillo de Invisibilidad");
		o11.setTiene_propiedad(Propiedad.INVISIBILIDAD);
		ObjetoEspecial o12 = new ObjetoEspecial("Conjuro de Vuelo");
		o12.setTiene_propiedad(Propiedad.VUELO);
		ObjetoEspecial o13 = new ObjetoEspecial("Espejo de Mano");
		o13.setTiene_propiedad(Propiedad.REFLEJO);
		ObjetoEspecial o17 = new ObjetoEspecial("Alas de Icaro");
		o17.setTiene_propiedad(Propiedad.VUELO);
		// Normales
		// Objeto o6 = new ObjetoNormal("Ojo de Graya");
		Objeto o7 = new ObjetoNormal("Zurron Magico");
		Objeto o10 = new ObjetoNormal("Bridas de Oro");
		Objeto o14 = new ObjetoNormal("Hilo de Ariadna");
		// Armas
		Objeto o8 = new Arma("Hoz de Acero");
		Objeto o9 = new Arma("Espada Indestructible");
		Objeto o15 = new Arma("Espada de Teseo");
		Objeto o16 = new Arma("Cuerno del Minotauro");
		
		// FAMILIAS
		Familia f1 = new Familia(h1, h4);
		Familia f2 = new Familia(h3, h4);
		Familia f3 = new Familia(h6, h5);
		Familia f4 = new Familia(d8, s3);
		
		// PREMISAS (Cosas que, a no ser que se indique lo contrario en el fichero, se dan por ciertas)
		Posee p1 = new Posee(d3, o3);
		Posee p2 = new Posee(d4, o8);
		Posee p3 = new Posee(d5, o9);
		Posee p4 = new Posee(d5, o10);
		Posee p5 = new Posee(d6, o4);
		// Posee p6 = new Posee(d7, o6);
		Posee p7 = new Posee(g1, o1);
		Posee p8 = new Posee(n2, o2);
		Posee p9 = new Posee(n2, o7);
		
		// Añadimos a mapas para el lector de fichero
		HashMap<String, Ser> seres = new HashMap<String, Ser>();
		HashMap<String, Objeto> objetos = new HashMap<String, Objeto>();
		seres.put("Perseo", s1);
		seres.put("Teseo", s2);
		seres.put("Ariadna", s3);
		seres.put("Casiopea", h1);
		seres.put("Danae", h2);
		seres.put("Cefeo", h3);
		seres.put("Andromeda", h4);
		seres.put("Ceto", c1);
		seres.put("Minotauro", c2);
		seres.put("Zeus", d1);
		seres.put("Poseidon", d2);
		seres.put("Hades", d3);
		seres.put("Hermes", d4);
		seres.put("Hefesto", d5);
		seres.put("Atenea", d6);
		seres.put("Las Grayas", d7);
		seres.put("Minos", d8);
		seres.put("Doris", n1);
		seres.put("Las Ninfas", n2);
		seres.put("Medusa", g1);
		seres.put("Icaro", h5);
		seres.put("Dedalo", h6);
		objetos.put("Cabeza de Medusa", o1);
		objetos.put("Sandalias Aladas", o2);
		objetos.put("Casco de Hades", o3);
		objetos.put("Escudo de Bronce", o4);
		objetos.put("Pegaso", o5);
		// objetos.put("Ojo de Graya", o6);
		objetos.put("Zurron Magico", o7);
		objetos.put("Hoz de Acero", o8);
		objetos.put("Espada Indestructible", o9);
		objetos.put("Bridas de Oro", o10);
		objetos.put("Anillo de Invisibilidad", o11);
		objetos.put("Conjuro de Vuelo", o12);
		objetos.put("Espejo de Mano", o13);
		objetos.put("Hilo de Ariadna", o14);
		objetos.put("Espada de Teseo", o15);
		objetos.put("Cuerno del Minotauro", o16);
		objetos.put("Alas de Icaro", o17);
		
		// Añadimos a lista de posesiones para el lector de fichero
		ArrayList<Posee> posesiones = new ArrayList<Posee>();
		posesiones.add(p1);
		posesiones.add(p2);
		posesiones.add(p3);
		posesiones.add(p4);
		posesiones.add(p5);
		// posesiones.add(p6);
		posesiones.add(p7);
		posesiones.add(p8);
		posesiones.add(p9);
		
		// Añadimos a la base de conocimiento
		kSession.insert(s1);
		kSession.insert(s2);
		kSession.insert(s3);
		kSession.insert(h1);
		kSession.insert(h2);
		kSession.insert(h3);
		kSession.insert(h4);
		kSession.insert(h5);
		kSession.insert(h6);
		kSession.insert(d1);
		kSession.insert(d2);
		kSession.insert(d3);
		kSession.insert(d4);
		kSession.insert(d5);
		kSession.insert(d6);
		kSession.insert(d7);
		kSession.insert(d8);
		kSession.insert(c1);
		kSession.insert(c2);
		kSession.insert(n1);
		kSession.insert(n2);
		kSession.insert(g1);
		kSession.insert(o1);
		kSession.insert(o2);
		kSession.insert(o3);
		kSession.insert(o4);
		kSession.insert(o5);
		// kSession.insert(o6);
		kSession.insert(o7);
		kSession.insert(o8);
		kSession.insert(o9);
		kSession.insert(o10);
		kSession.insert(o11);
		kSession.insert(o12);
		kSession.insert(o13);
		kSession.insert(o14);
		kSession.insert(o15);
		kSession.insert(o16);
		kSession.insert(o17);
		kSession.insert(f1);
		kSession.insert(f2);
		kSession.insert(f3);
		kSession.insert(p1);
		kSession.insert(p2);
		kSession.insert(p3);
		kSession.insert(p4);
		kSession.insert(p5);
		// kSession.insert(p6);
		kSession.insert(p7);
		kSession.insert(p8);
		kSession.insert(p9);
		
		// Llamamos al lector de fichero
		LectorFichero lf = new LectorFichero(seres, objetos, posesiones, kSession);
		try {
			lf.leerArchivo("entrada5.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		kSession.fireAllRules();
		
		System.out.println("OK");
		
	}
}
