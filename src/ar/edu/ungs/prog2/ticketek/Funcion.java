package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Funcion {
	private Fecha Fecha;
	private Sede sede;
	private double precioBase;
	HashMap <String, Ubicacion> asientosOucpados;
	
	//CONSTRUCTOR
	public Funcion(String Fecha, Sede sede, double precioBase) {
		//COMPLETAR
	}
	
	//OPERACIONES
	public boolean verificarDisponibilidad(Sector sector, int fila, int asiento) {
		//COMPLETAR
		return true;
	}
	public void ocuparAsiento(Sector sector, int fila, int asiento) {
		//COMPLETAR
	}

	public Fecha ObtenerFecha() {
		return Fecha;
	}

	public Sede getSede() {
		return sede;
	}

	public double getPrecioBase() {
		return precioBase;
	}
	
//GETTERS --------------------------
	
}
