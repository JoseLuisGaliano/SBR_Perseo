package ser;

import java.util.List;

import objeto.*;
import java.util.ArrayList;

public abstract class Ser {
	
	private String nombre_ser;
	private boolean vivo;
	private List<Ser> apresa_a;
	private Ser apresado_por;
	private List<Ser> libera_a;
	private Ser liberado_por;
	private List<Ser> mata_a;
	private List<Objeto> tiene_objeto;
	private List<Ser> hijo_de;
	private List<Ser> tiene_hijo;
	private List<Propiedad> capacidades;
	
	public Ser(String nombre) {
		nombre_ser = nombre;
		vivo = true;
		apresa_a = new ArrayList<Ser>();
		apresado_por = null;
		libera_a = new ArrayList<Ser>();
		liberado_por = null;
		mata_a = new ArrayList<Ser>();
		tiene_objeto = new ArrayList<Objeto>();
		hijo_de = new ArrayList<Ser>();
		tiene_hijo = new ArrayList<Ser>();
		capacidades = new ArrayList<Propiedad>();
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

	public List<Ser> getApresa_a() {
		return apresa_a;
	}

	public void setApresa_a(List<Ser> apresa_a) {
		this.apresa_a = apresa_a;
	}
	
	public void addApresa_a(Ser ser)
	{
		apresa_a.add(ser);
	}
	
	public void removeApresa_a(Ser ser)
	{
		apresa_a.remove(ser);
	}

	public Ser getApresado_por() {
		return apresado_por;
	}

	public void setApresado_por(Ser apresado_por) {
		this.apresado_por = apresado_por;
	}

	public List<Ser> getLibera_a() {
		return libera_a;
	}

	public void setLibera_a(List<Ser> libera_a) {
		this.libera_a = libera_a;
	}
	
	public void addLibera_a(Ser ser)
	{
		libera_a.add(ser);
	}

	public Ser getLiberado_por() {
		return liberado_por;
	}

	public void setLiberado_por(Ser liberado_por) {
		this.liberado_por = liberado_por;
	}

	public List<Ser> getMata_a() {
		return mata_a;
	}

	public void setMata_a(List<Ser> mata_a) {
		this.mata_a = mata_a;
	}
	
	public void addMata_a(Ser ser)
	{
		mata_a.add(ser);
	}

	public List<Objeto> getTiene_objeto() {
		return tiene_objeto;
	}

	public void setTiene_objeto(List<Objeto> tiene_objeto) {
		this.tiene_objeto = tiene_objeto;
	}
	
	public void addTiene_objeto(Objeto objeto)
	{
		tiene_objeto.add(objeto);
	}

	public List<Ser> getHijo_de() {
		return hijo_de;
	}

	public void setHijo_de(List<Ser> hijo_de) {
		this.hijo_de = hijo_de;
	}

	public List<Ser> getTiene_hijo() {
		return tiene_hijo;
	}

	public void setTiene_hijo(List<Ser> tiene_hijo) {
		this.tiene_hijo = tiene_hijo;
	}
	
	public List<Propiedad> getCapacidades()
	{
		return capacidades;
	}
	
	public void setCapacidades(List<Propiedad> capacidades)
	{
		this.capacidades = capacidades;
	}
	
	public void addCapacidad(Propiedad capacidad)
	{
		capacidades.add(capacidad);
	}
}
