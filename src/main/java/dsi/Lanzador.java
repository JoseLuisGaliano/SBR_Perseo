package dsi;

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
		
		// SERES
		
		// Mortales
		Semidios sd = new Semidios("Perseo");
		Humano h1 = new Humano("Casiopea");
		Humano h2 = new Humano("Danae");
		Humano h3 = new Humano("Cefeo");
		Humano h4 = new Humano("Andromeda");
		Criatura c1 = new Criatura("Ceto");
		
		// Inmortales	
		// Deidades Mayores
		DeidadMayor d1 = new DeidadMayor("Zeus");
		DeidadMayor d2 = new DeidadMayor("Poseidon");
		DeidadMayor d3 = new DeidadMayor("Hades");
		DeidadMayor d4 = new DeidadMayor("Hermes");
		DeidadMayor d5 = new DeidadMayor("Hefesto");
		DeidadMayor d6 = new DeidadMayor("Atenea");
		// Deidades Menores
		DeidadMenor d7 = new DeidadMenor("Las Grayas");
		Ninfa n1 = new Ninfa("Doris");
		Ninfa n2 = new Ninfa("Nereidas");
		Ninfa n3 = new Ninfa("Ninfas");
		Gorgona g1 = new Gorgona("Medusa");
		
		// OBJETOS
		
		// Especiales
		Objeto o1 = new ObjetoEspecial("Cabeza de Medusa");
		Objeto o2 = new ObjetoEspecial("Sandalias Aladas");
		Objeto o3 = new ObjetoEspecial("Casco de Hades");
		Objeto o4 = new ObjetoEspecial("Escudo Espejo");
		Objeto o5 = new ObjetoEspecial("Pegaso");
		// Normales
		Objeto o6 = new ObjetoNormal("Ojo de Graya");
		Objeto o7 = new ObjetoNormal("Zurron Magico");
		Objeto o8 = new ObjetoNormal("Hoz de Acero");
		Objeto o9 = new ObjetoNormal("Espada Indestructible");
		Objeto o10 = new ObjetoNormal("Bridas de Oro");
		
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
		kSession.insert(n3);
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
