package relaciones;

import ser.Ser;
import objeto.Propiedad;

public class Capacidades {
	
	private Ser poseedor;
	private Propiedad capacidad;
	
	public Capacidades(Ser poseedor, Propiedad capacidad) {
		this.poseedor = poseedor;
		this.capacidad = capacidad;
	}

	public Ser getPoseedor() {
		return poseedor;
	}

	public void setPoseedor(Ser poseedor) {
		this.poseedor = poseedor;
	}

	public Propiedad getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Propiedad capacidad) {
		this.capacidad = capacidad;
	}
}
