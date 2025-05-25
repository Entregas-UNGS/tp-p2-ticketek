package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;

public class Funcion {
	private Fecha fecha;
	private Sede sede;
	private double precioBase;
	HashMap<Sector, boolean[]> asientosOucpados; // Sector, TRUE = OCUPADO, FALSE = DESOCUPADO

	// CONSTRUCTOR
	public Funcion(String fecha, Sede sede, double precioBase) {
		this.fecha = new Fecha(fecha);
		this.sede = sede;
		this.precioBase = precioBase;
		this.asientosOucpados = new HashMap<>();
		for (Sector sector : sede.getSectores()) {
			int cantidadAsientos = sector.getCapacidad();
			boolean[] ocupados = new boolean[cantidadAsientos]; // Por defecto, todos los asientos están desocupados (false)
			this.asientosOucpados.put(sector, ocupados);
		}
	}

	// OPERACIONES
	public boolean verificarDisponibilidad(Sector sector, int asiento) {
		// Verifico que no este ocupado y que el arreglo permita dicho numero
		if (this.asientosOucpados.get(sector).length > asiento && this.asientosOucpados.get(sector)[asiento - 1] == false) {
			return true;
		}
		return false;
	}

	public void ocuparAsiento(Sector sector, int asiento) {
		this.asientosOucpados.get(sector)[asiento - 1] = true; // Ocupo el asiento de dicho sector
	}

	public Fecha ObtenerFecha() {
		return fecha;
	}

	public Sede getSede() {
		return sede;
	}

	public double getPrecioBase() {
		return precioBase;
	}
//GETTERS --------------------------

	//METODOS
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		return this.getSede() instanceof EstadioDeFutbol
				? sb.append("(")
						.append(fecha).append(") ")
						.append(this.sede.getNombre()).append(" - ")
						.append("100").append(" / ").append("500")
						.toString()
						
					//Platea VIP: Entradas Vendidas / Capacidad total
				: sb.append("(")
						.append(fecha).append(") ")
						.append(this.sede.getNombre()).append(" - ")
						.append("Platea VIP: ").append("30").append(" / ").append("50")
						.append(" | Platea Común: ").append("30").append(" / ").append("50")
						.append(" | Platea Baja: ").append("30").append(" / ").append("50")
						.append(" | Platea Alta: ").append("30").append(" / ").append("50")
						.toString();
	}


}
