package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Funcion {
	private Fecha Fecha;
	private String nombreSede;
	private double precioBase;
	HashMap <String, Ubicacion> asientosOucpados;
	
	//CONSTRUCTOR
	public Funcion(String Fecha, String nombreSede, double precioBase) {
		this.Fecha.parsearFormatoDMY(Fecha);
		this.nombreSede=nombreSede;
		this.precioBase=precioBase;
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

	public String getNombreSede() {
		return nombreSede;
	}

	public double getPrecioBase() {
		return precioBase;
	}
	
//GETTERS --------------------------
	
}
