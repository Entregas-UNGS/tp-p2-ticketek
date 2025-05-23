package ar.edu.ungs.prog2.ticketek;

public class Ubicacion {
 private Sector sector;
 private Integer fila;
 private Integer asiento;
 //CONSTRUCTOR
 public Ubicacion (Sector sector, int fila, int asiento){
	this.sector = sector;
	this.asiento = asiento;
 }
//OPERACIONES
 
 
//GETTERS------------------------------------------------------------
public Sector getSector() {
	return sector;
}

public Integer getFila() {
	return fila;
}

public Integer getAsiento() {
	return asiento;
}
 
}
