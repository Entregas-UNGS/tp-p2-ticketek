package ar.edu.ungs.prog2.ticketek;

public class Sector {
	private String nombre;
	private Integer filas;
	private Integer asientosPorFila;
	private Integer adicionalAlSector;
	
	//CONSTRUCTOR
	public Sector(String nombre, int asientosPorFila , int adicionalSector) {
		this.nombre = nombre;
		this.asientosPorFila = asientosPorFila;
		this.adicionalAlSector = adicionalSector;
		
	}
	public int capacidad() {
		//COMPLETAR
		return 0;
	}
//GETTERS----------------------------------------------------------------------------------------
	public String getNombre() {
		return nombre;
	}

	public Integer getFilas() {
		return filas;
	}

	public Integer getAsientosPorFila() {
		return asientosPorFila;
	}

	public Integer getAdicionalSector() {
		return adicionalAlSector;
	}
	
}
