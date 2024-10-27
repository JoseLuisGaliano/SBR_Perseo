package objeto;

public abstract class Objeto {
	
	private String nombre_objeto;
	
	public Objeto(String nombre_objeto) {
		this.nombre_objeto = nombre_objeto;
	}

	public String getNombre_objeto() {
		return nombre_objeto;
	}

	public void setNombre_objeto(String nombre_objeto) {
		this.nombre_objeto = nombre_objeto;
	}
	
	
}
