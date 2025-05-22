package ar.edu.ungs.prog2.ticketek;

public class Sector {
	private String nombre;
	private Integer filas;
	private Integer asientosPorFila;
	private Integer adicionalAlSector;
	
	//CONSTRUCTOR
	public Sector(String nombre, int asientosPorFila , int adicionalAlSector) {
	  this.nombre = nombre;
    this.asientosPorFila = asientosPorFila;
    this.adicionalAlSector = adicionalAlSector;
		
	}
	
	public int capacidad() {
		return this.asientosPorFila * this.filas;
	}
	
//GETTERS----------------------------------------------------------------------------------------
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
