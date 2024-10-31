package dsi;

import objeto.*;
import ser.*;

public class Pruebas {

	public static void main(String[] args) {
		
		Ser perseo = new Semidios("Perseo");
		
		ObjetoEspecial obj = new ObjetoEspecial("Cabeza de Medusa");
		obj.setTiene_propiedad(Propiedad.PETRIFICAR);
		
		perseo.addTiene_objeto(obj);		
		perseo.addCapacidad(obj.getTiene_propiedad());
		
		Propiedad p = Propiedad.PETRIFICAR;
		
		if(perseo.getCapacidades().contains(p)) System.out.println("OK");
	}

}
