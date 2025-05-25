package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Iterator;

public class Funcion {
	private Fecha Fecha;
	private Sede Sede;
	private double precioBase;
	HashMap <Sector, boolean[]> asientosOucpados; //Sector, TRUE = OCUPADO, FALSE = DESOCUPADO

	
	//CONSTRUCTOR
	public Funcion(String fecha, Sede sede, double precioBase) {
		this.Fecha = new Fecha(fecha);
		this.Sede = sede;
		this.precioBase=precioBase;
		this.asientosOucpados = new HashMap<>();
		for (Sector sector : sede.getSectores()) {
			int cantidadAsientos = sector.getCapacidad();
			boolean[] ocupados = new boolean[cantidadAsientos]; // Por defecto, todos los asientos están desocupados (false)
			this.asientosOucpados.put(sector, ocupados);
		}
	}
	
	//OPERACIONES
	public boolean verificarDisponibilidad(Sector sector, int asiento) {
		if (this.asientosOucpados.get(sector).length > asiento &&this.asientosOucpados.get(sector)[asiento-1] == false){ //Verifico que no este ocupado y que el arreglo permita dicho numero
			return true;
		}
		return false;
	}
	public void ocuparAsiento(Sector sector, int asiento) {
		this.asientosOucpados.get(sector)[asiento-1]=true; //Ocupo el asiento de dicho sector
	}

	public Fecha ObtenerFecha() {
		return Fecha;
	}

	public Sede getSede() {
		return Sede;
	}

	public double getPrecioBase() {
		return precioBase;
	}
	
//GETTERS --------------------------
	
}
