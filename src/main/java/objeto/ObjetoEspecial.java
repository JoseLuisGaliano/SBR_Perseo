package objeto;

public class ObjetoEspecial extends Objeto {
	
	private Propiedad tiene_propiedad;
	
	public ObjetoEspecial(String nombre_objeto)
	{
		super(nombre_objeto);
		tiene_propiedad = null;
	}

	public Propiedad getTiene_propiedad() {
		return tiene_propiedad;
	}

	public void setTiene_propiedad(Propiedad tiene_propiedad) {
		this.tiene_propiedad = tiene_propiedad;
	}
	
	
}
