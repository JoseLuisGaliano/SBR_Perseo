package relaciones;

import ser.Ser;

public class Apresa {
	
	private Ser apresador;
	private Ser apresado;
	
	public Apresa(Ser apresador, Ser apresado)
	{
		this.apresador = apresador;
		this.apresado = apresado;
	}

	public Ser getApresador() {
		return apresador;
	}

	public void setApresador(Ser apresador) {
		this.apresador = apresador;
	}

	public Ser getApresado() {
		return apresado;
	}

	public void setApresado(Ser apresado) {
		this.apresado = apresado;
	}
}
