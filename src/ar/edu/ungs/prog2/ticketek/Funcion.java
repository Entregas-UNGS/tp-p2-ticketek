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
 //" - (25/07/25) Microestadio Sur - VIP: 0/50 | Comun: 0/100 | Baja: 0/150 | Alta: 0/200\n"
	//METODOS
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
			sb.append(" - (");
			sb.append(fecha.toString());
			sb.append(") ").append(sede.getNombre());
			sb.append((" - "));
		for(int i = 0; i<this.sede.sectores.size(); i++){
			sb.append(this.sede.NombreDelSector(i)).append(": ");
			sb.append(calcularCapacidadOcupada(this.sede.DevolverSector(i)));
			sb.append("/").append(this.sede.DevolverSector(i).getCapacidad());
			if(!(this.sede instanceof EstadioDeFutbol) && i != this.sede.sectores.size()-1){
				sb.append(" | ");
			}
		}
		return sb.toString();
	}
	public String calcularCapacidadOcupada (Sector s){
		int cantidadOcupada = 0;
		boolean[] array = asientosOucpados.get(s);
		for(int i =0 ; i<array.length ; i++ ){
			if (array[i] == true){
				cantidadOcupada++;
			}
		}
		return ""+cantidadOcupada;
	}


}
