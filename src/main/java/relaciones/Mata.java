package relaciones;

import ser.Ser;

public class Mata {
	
	private Ser matador;
	private Ser matado;
	
	public Mata(Ser matador, Ser matado) {
		this.matador = matador;
		this.matado = matado;
	}

	public Ser getMatador() {
		return matador;
	}

	public void setMatador(Ser matador) {
		this.matador = matador;
	}

	public Ser getMatado() {
		return matado;
	}

	public void setMatado(Ser matado) {
		this.matado = matado;
	}
}
