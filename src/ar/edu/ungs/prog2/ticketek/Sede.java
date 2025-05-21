package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public abstract class Sede {
	private String tipo;
	private String nombre;
	private String direccion;
	private Integer capacidadMaxima;
	protected ArrayList<Sector> sectores;
	
	//CONSTRUCTOR
	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
	}

	public Sede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {

	}

	public Sede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
	}
	//Hace falta los constructores aca siendo una clase abstracta? O aplicamos directamente polimorfimo con
	//Teatro, MiniEstadio y EstadioDeFutbol


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
