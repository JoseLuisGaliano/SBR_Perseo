package relaciones;

import objeto.Objeto;
import ser.Ser;

public class Posee {
	
	private Ser poseedor;
	private Objeto objeto;
	
	public Posee(Ser poseedor, Objeto objeto) {
		this.poseedor = poseedor;
		this.objeto = objeto;
	}

	public Ser getPoseedor() {
		return poseedor;
	}

	public void setPoseedor(Ser poseedor) {
		this.poseedor = poseedor;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
}
