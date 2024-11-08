package ser;

public abstract class Ser {
	
	private String nombre_ser;
	private boolean vivo;
	
	public Ser(String nombre) {
		nombre_ser = nombre;
		vivo = true;
	}

	public String getNombre_ser() {
		return nombre_ser;
	}

	public void setNombre_ser(String nombre_ser) {
		this.nombre_ser = nombre_ser;
	}

	public boolean isVivo() {
		return vivo;
	}

	public void setVivo(boolean vivo) {
		this.vivo = vivo;
	}
}
