package dsi;

import java.util.List;

// Clase cuya instancia representa la codición de parada a cumplimentar
public class CondicionParada {
	
	private TiposParada tipo;
	private List<String> personaje;
	private String objetivo;
	
	public CondicionParada(TiposParada tipo, List<String> personaje, String objetivo) {
		this.tipo = tipo;
		this.personaje = personaje;
		this.objetivo = objetivo;
	}

	public List<String> getPersonaje() {
		return personaje;
	}

	public String getObjetivo() {
		return objetivo;
	}
	
	public TiposParada getTipo() {
		return tipo;
	}
}
