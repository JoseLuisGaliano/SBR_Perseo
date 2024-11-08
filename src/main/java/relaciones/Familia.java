package relaciones;

import ser.Ser;

public class Familia {
	
	private Ser padre;
	private Ser hijo;
	
	public Familia(Ser padre, Ser hijo) {
		this.padre = padre;
		this.hijo = hijo;
	}

	public Ser getPadre() {
		return padre;
	}

	public void setPadre(Ser padre) {
		this.padre = padre;
	}

	public Ser getHijo() {
		return hijo;
	}

	public void setHijo(Ser hijo) {
		this.hijo = hijo;
	}
}
