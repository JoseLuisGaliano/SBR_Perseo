package ser;

import java.util.ArrayList;
import java.util.List;

public abstract class Dios extends Ser {
	
	private List<Mortal> enojado_por;
	private List<Mortal> favorece_a;
	
	public Dios(String nombre_ser) {
		super(nombre_ser);
		enojado_por = new ArrayList<Mortal>();
		favorece_a = new ArrayList<Mortal>();
	}

	public List<Mortal> getEnojado_por() {
		return enojado_por;
	}

	public void setEnojado_por(List<Mortal> enojado_por) {
		this.enojado_por = enojado_por;
	}
	
	public void addEnojado_por(Mortal mortal)
	{
		enojado_por.add(mortal);
	}

	public List<Mortal> getFavorece_a() {
		return favorece_a;
	}

	public void setFavorece_a(List<Mortal> favorece_a) {
		this.favorece_a = favorece_a;
	}
	
	public void addFavorece_a(Mortal mortal)
	{
		favorece_a.add(mortal);
	}
	
	
}
