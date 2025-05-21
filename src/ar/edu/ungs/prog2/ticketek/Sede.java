package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public abstract class Sede {
	private String tipo;
	private String nombre;
	private String direccion;
	private Integer capacidadMaxima;
	private ArrayList<Sector> sectores;
	
	//CONSTRUCTOR
	public Sede(String nombre, String direccion, String capacidadMaxima, ArrayList<Sector> sectores) {
		//COMPLETAR
	}
	//OPERACIONES
	public void revisarCapacidadMaxima() {
		//COMPLETAR
	}
	
	//GETTERS
	public String getTipo() {
		return tipo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public Integer getCapacidadMaxima() {
		return capacidadMaxima;
	}
	public ArrayList<Sector> getSectores() {
		return sectores;
	}
	
}
