package dsi;

// Clase cuya instancia representa la codición de parada a cumplimentar
public class CondicionParada {
	
	private TiposParada tipo;
	private String personaje;
	private String objetivo;
	
	public CondicionParada(TiposParada tipo, String personaje, String objetivo) {
		this.tipo = tipo;
		this.personaje = personaje;
		this.objetivo = objetivo;
	}

	public String getPersonaje() {
		return personaje;
	}

	public String getObjetivo() {
		return objetivo;
	}
	
	public TiposParada getTipo() {
		return tipo;
	}
}
