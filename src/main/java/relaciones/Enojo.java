package relaciones;

import ser.Mortal;
import ser.Dios;

public class Enojo {
	
	private Mortal enojador;
	private Dios enojado;
	
	public Enojo(Mortal enojador, Dios enojado) {
		this.enojador = enojador;
		this.enojado = enojado;
	}

	public Mortal getEnojador() {
		return enojador;
	}

	public void setEnojador(Mortal enojador) {
		this.enojador = enojador;
	}

	public Dios getEnojado() {
		return enojado;
	}

	public void setEnojado(Dios enojado) {
		this.enojado = enojado;
	}
}
