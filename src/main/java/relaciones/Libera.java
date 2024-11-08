package relaciones;

import ser.Ser;

public class Libera {
	
	private Ser liberador;
	private Ser liberado;
	
	public Libera(Ser liberador, Ser liberado) {
		this.liberador = liberador;
		this.liberado = liberado;
	}

	public Ser getLiberador() {
		return liberador;
	}

	public void setLiberador(Ser liberador) {
		this.liberador = liberador;
	}

	public Ser getLiberado() {
		return liberado;
	}

	public void setLiberado(Ser liberado) {
		this.liberado = liberado;
	}
}
