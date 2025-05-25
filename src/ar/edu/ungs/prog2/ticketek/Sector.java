package ar.edu.ungs.prog2.ticketek;

public class Sector {
	private String nombre;
	private Integer filas;
	private Integer asientosPorFila;
	private Integer adicionalAlSector;
	private int capacidad;
	
	//CONSTRUCTOR
	public Sector(String nombre, int capacidad, int asientosPorFila, int adicionalAlSector) {
	  this.nombre = nombre;
	  this.capacidad = capacidad;
    this.asientosPorFila = asientosPorFila;
    this.adicionalAlSector = adicionalAlSector;
	}
	
	public Sector(String nombre, int capacidad, int adicionalAlSector) {
		this.nombre = nombre;
		this.capacidad = capacidad;
   		this.adicionalAlSector = adicionalAlSector;
	}
	
	public int capacidad() {
		return this.asientosPorFila * this.filas;
	}
	
//GETTERS----------------------------------------------------------------------------------------
	public int getCapacidad() {
		return this.capacidad;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public Integer getFilas() {
		return this.filas;
	}

	public Integer getAsientosPorFila() {
		return this.asientosPorFila;
	}

	public Integer getAdicionalSector() {
		return this.adicionalAlSector;
	}
	
}
