package relaciones;

import ser.Dios;
import ser.Mortal;

public class Favor {
	
	private Dios favorecedor;
	private Mortal favorecido;
	
	public Favor(Dios favorecedor, Mortal favorecido) {
		this.favorecedor = favorecedor;
		this.favorecido = favorecido;
	}

	public Dios getFavorecedor() {
		return favorecedor;
	}

	public void setFavorecedor(Dios favorecedor) {
		this.favorecedor = favorecedor;
	}

	public Mortal getFavorecido() {
		return favorecido;
	}

	public void setFavorecido(Mortal favorecido) {
		this.favorecido = favorecido;
	}
}
