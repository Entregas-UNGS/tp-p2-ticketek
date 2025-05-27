package ar.edu.ungs.prog2.ticketek;

public class Sector {
	private String nombre;
	private Integer asientosPorFila;
	private Integer adicionalAlSector;
	private int capacidad;
	
	//CONSTRUCTOR

	//Sectores con asinetos
	public Sector(String nombre, int capacidad, int asientosPorFila, int adicionalAlSector) {
	  this.nombre = nombre;
	  this.capacidad = capacidad;
    this.asientosPorFila = asientosPorFila;
    this.adicionalAlSector = adicionalAlSector;
	}
	
	//Sectores sin asientos
	public Sector(String nombre, int capacidad, int adicionalAlSector) {
		this.nombre = nombre;
		this.capacidad = capacidad;
   		this.adicionalAlSector = adicionalAlSector;
	}
//GETTERS----------------------------------------------------------------------------------------
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Integer getAsientosPorFila() {
		return this.asientosPorFila;
	}

	public Integer getAdicionalSector() {
		return this.adicionalAlSector;
	}
	
}
