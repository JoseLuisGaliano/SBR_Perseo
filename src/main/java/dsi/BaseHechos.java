package dsi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kie.api.runtime.KieSession;

import objeto.*;
import ser.*;
import relaciones.*;

public class BaseHechos {

	private KieSession kSession;
	private Map<String, Ser> seres;
	private Map<String, Objeto> objetos;
	private List<Posee> posesiones;
	
	public BaseHechos(KieSession kSession) {
		this.kSession = kSession;
		seres = new HashMap<String, Ser>();
		objetos = new HashMap<String, Objeto>();
		posesiones = new ArrayList<Posee>();
	}

	public void poblar() {
		
		// SERES
		
		// Mortales
		Semidios s1 = new Semidios("Perseo");
		Semidios s2 = new Semidios("Teseo");
		Semidios s3 = new Semidios("Ariadna");
		Semidios s4 = new Semidios("Heracles");
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

		// OBJETOS
		
		// Especiales
		ObjetoEspecial oe1 = new ObjetoEspecial("Cabeza de Medusa");
		oe1.setTiene_propiedad(Propiedad.PETRIFICAR);
		ObjetoEspecial oe2 = new ObjetoEspecial("Sandalias Aladas");
		oe2.setTiene_propiedad(Propiedad.VUELO);
		ObjetoEspecial oe3 = new ObjetoEspecial("Casco de Hades");
		oe3.setTiene_propiedad(Propiedad.INVISIBILIDAD);
		ObjetoEspecial oe4 = new ObjetoEspecial("Escudo de Bronce");
		oe4.setTiene_propiedad(Propiedad.REFLEJO);
		ObjetoEspecial oe5 = new ObjetoEspecial("Pegaso");
		oe5.setTiene_propiedad(Propiedad.VUELO);
		ObjetoEspecial oe6 = new ObjetoEspecial("Anillo de Invisibilidad");
		oe6.setTiene_propiedad(Propiedad.INVISIBILIDAD);
		ObjetoEspecial oe7 = new ObjetoEspecial("Conjuro de Vuelo");
		oe7.setTiene_propiedad(Propiedad.VUELO);
		ObjetoEspecial oe8 = new ObjetoEspecial("Espejo de Mano");
		oe8.setTiene_propiedad(Propiedad.REFLEJO);
		ObjetoEspecial oe9 = new ObjetoEspecial("Alas de Icaro");
		oe9.setTiene_propiedad(Propiedad.VUELO);
		
		// Normales
		Objeto o1 = new ObjetoNormal("Zurron Magico");
		Objeto o2 = new ObjetoNormal("Bridas de Oro");
		Objeto o3 = new ObjetoNormal("Hilo de Ariadna");
		
		// Armas
		Objeto a1 = new Arma("Hoz de Acero");
		Objeto a2 = new Arma("Espada Indestructible");
		Objeto a3 = new Arma("Espada de Teseo");
		Objeto a4 = new Arma("Cuerno del Minotauro");
		
		// RELACIONES
		
		// Familias (relaciones padre-hijo)
		Familia f1 = new Familia(h1, h4);
		Familia f2 = new Familia(h3, h4);
		Familia f3 = new Familia(h6, h5);
		Familia f4 = new Familia(d8, s3);
		Familia f5 = new Familia(d1, s4);

		// Posesiones
		Posee p1 = new Posee(d3, oe3);
		Posee p2 = new Posee(d4, a1);
		Posee p3 = new Posee(d5, o2);
		Posee p4 = new Posee(d5, a2);
		Posee p5 = new Posee(d6, oe4);
		Posee p6 = new Posee(g1, oe1);
		Posee p7 = new Posee(n2, oe2);
		Posee p8 = new Posee(n2, o1);
		Posee p9 = new Posee(s2, a3);
		Posee p10 = new Posee(s3, o3);
		Posee p11 = new Posee(c2, a4);

		// PREPARAMOS MAPAS PARA EL LECTOR DE FICHERO
		
		// Mapa de Seres
		seres.put("Perseo", s1);
		seres.put("Teseo", s2);
		seres.put("Ariadna", s3);
		seres.put("Heracles", s4);
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
		
		// Mapa de Objetos
		objetos.put("Cabeza de Medusa", oe1);
		objetos.put("Sandalias Aladas", oe2);
		objetos.put("Casco de Hades", oe3);
		objetos.put("Escudo de Bronce", oe4);
		objetos.put("Pegaso", oe5);
		objetos.put("Anillo de Invisibilidad", oe6);
		objetos.put("Conjuro de Vuelo", oe7);
		objetos.put("Espejo de Mano", oe8);
		objetos.put("Alas de Icaro", oe9);
		objetos.put("Zurron Magico", o1);
		objetos.put("Bridas de Oro", o2);
		objetos.put("Hilo de Ariadna", o3);
		objetos.put("Hoz de Acero", a1);
		objetos.put("Espada Indestructible", a2);
		objetos.put("Espada de Teseo", a3);
		objetos.put("Cuerno del Minotauro", a4);

		// Lista de Posesiones
		posesiones.add(p1);
		posesiones.add(p2);
		posesiones.add(p3);
		posesiones.add(p4);
		posesiones.add(p5);
		posesiones.add(p6);
		posesiones.add(p7);
		posesiones.add(p8);
		posesiones.add(p9);
		posesiones.add(p10);
		posesiones.add(p11);

		// Añadimos todo a la base de hechos
		kSession.insert(s1);
		kSession.insert(s2);
		kSession.insert(s3);
		kSession.insert(s4);
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
		kSession.insert(oe1);
		kSession.insert(oe2);
		kSession.insert(oe3);
		kSession.insert(oe4);
		kSession.insert(oe5);
		kSession.insert(oe6);
		kSession.insert(oe7);
		kSession.insert(oe8);
		kSession.insert(oe9);
		kSession.insert(o1);
		kSession.insert(o2);
		kSession.insert(o3);
		kSession.insert(a1);
		kSession.insert(a2);
		kSession.insert(a3);
		kSession.insert(a4);
		kSession.insert(f1);
		kSession.insert(f2);
		kSession.insert(f3);
		kSession.insert(f4);
		kSession.insert(f5);
		kSession.insert(p1);
		kSession.insert(p2);
		kSession.insert(p3);
		kSession.insert(p4);
		kSession.insert(p5);
		kSession.insert(p6);
		kSession.insert(p7);
		kSession.insert(p8);
		kSession.insert(p9);
		kSession.insert(p10);
		kSession.insert(p11);

	}

	public Map<String, Ser> getSeres() {
		return seres;
	}

	public Map<String, Objeto> getObjetos() {
		return objetos;
	}

	public List<Posee> getPosesiones() {
		return posesiones;
	}
}
