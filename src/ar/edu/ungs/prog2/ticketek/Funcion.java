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

		public boolean verificarDisponibilidad(Sector sector) {
		// Verifico que no este ocupado y que el arreglo permita dicho numero
		for(int i = 0; i < asientosOucpados.get(sector).length ; i++){
			if(asientosOucpados.get(sector)[i] == false){
				asientosOucpados.get(sector)[i] = true; //Ocupo el asiento
				return true;
			}
		}
		return false;
	}

	public void ocuparAsiento(Sector sector, int asiento) {
		this.asientosOucpados.get(sector)[asiento - 1] = true; // Ocupo el asiento de dicho sector
	}

	@Override
	public String toString(){
		//Ejemplo de lo deseado:
		//" - (25/07/25) Microestadio Sur - VIP: 0/50 | Comun: 0/100 | Baja: 0/150 | Alta: 0/200\n"
		StringBuilder sb = new StringBuilder();
			sb.append(" - (");
			sb.append(fecha.toString()); //Ponemos la fecha
			sb.append(") ").append(sede.getNombre()); //El nombre de la sede
			sb.append((" - "));
		for(int i = 0; i<this.sede.sectores.size(); i++){ //Recorro los sectores
			sb.append(this.sede.NombreDelSector(i)).append(": "); //Nombre del sector
			sb.append(calcularCapacidadOcupada(this.sede.DevolverSector(i))); // Capacidad ocupada del sector
			sb.append("/").append(this.sede.DevolverSector(i).getCapacidad()); //Capacidad total del sector
			if(!(this.sede instanceof EstadioDeFutbol) && i != this.sede.sectores.size()-1){ // Si no es estadio de futbol
				sb.append(" | "); //Agrego | entre sectores
			}
		}
		return sb.toString();
	}
	public String calcularCapacidadOcupada (Sector s){
		int cantidadOcupada = 0;
		boolean[] array = asientosOucpados.get(s);
		for(int i =0 ; i<array.length ; i++ ){
			if (array[i] == true){ //Cada true es un asiento ocupado
				cantidadOcupada++;
			}
		}
		return ""+cantidadOcupada; //Lo devuevlo en String para el toString
	}

//GETTERS --------------------------
		public Fecha ObtenerFecha() {
		return fecha;
	}

	public Sede getSede() {
		return sede;
	}

	public double getPrecioBase() {
		return precioBase;
	}
}
