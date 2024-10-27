package ser;

import java.util.ArrayList;
import java.util.List;

public abstract class Mortal extends Ser {
	
	private List<Dios> tiene_enojo_de;
	private List<Dios> tiene_favor_de;
	
	public Mortal(String nombre_ser) {
		super(nombre_ser);
		tiene_enojo_de = new ArrayList<Dios>();
		tiene_favor_de = new ArrayList<Dios>();
	}

	public List<Dios> getTiene_enojo_de() {
		return tiene_enojo_de;
	}

	public void setTiene_enojo_de(List<Dios> tiene_enojo_de) {
		this.tiene_enojo_de = tiene_enojo_de;
	}
	
	public void addTiene_enojo_de(Dios dios)
	{
		tiene_enojo_de.add(dios);
	}

	public List<Dios> getTiene_favor_de() {
		return tiene_favor_de;
	}

	public void setTiene_favor_de(List<Dios> tiene_favor_de) {
		this.tiene_favor_de = tiene_favor_de;
	}
	
	public void addTiene_favor_de(Dios dios)
	{
		tiene_favor_de.add(dios);
	}
	
	
}
