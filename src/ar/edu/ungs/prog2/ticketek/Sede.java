package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public abstract class Sede {
	private String nombre;
	private String direccion;
	private Integer capacidadMaxima;
	protected ArrayList<Sector> sectores;

	// CONSTRUCTOR
	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
		this.sectores = new ArrayList<Sector>();
	}
	// OPERACIONES--------------------------------------------------------------------------------------------------------

	// Teatro y MiniEstadio (Creo los sectores)
	public void crearSectores(String[] sectores, int[] capacidad, int asientosPorFila, int[] porcentajeAdicional) {
		if (sectores.length == capacidad.length && capacidad.length == porcentajeAdicional.length) { //Reviso que el largo de todos los arreglos sean el mismo
			for (int i = 0; i < sectores.length; i++) {
				Sector platea = new Sector(sectores[i], capacidad[i], asientosPorFila, porcentajeAdicional[i]); //Creo cada sector
				this.sectores.add(platea); //Lo agrego al diccionario
			}
		} else {
			throw new RuntimeException("No coinciden la cantidad de datos dados");
		}
	}

	public Sector getSector(String nombreSector) { //Doy el nombre del sector y me devuelve el Sector
		for (Sector sector : sectores) {
			if (sector.getNombre().equals(nombreSector)) {
				return sector;
			}
		}
		throw new RuntimeException("No Existe dicho secto");
	}

	public String NombreDelSector(int indice) { //Ingreso el indice del sector y me devuelve el Sector
		if (indice >= 0 && indice < sectores.size()) {
        	return sectores.get(indice).getNombre();
   		} else {
        	throw new RuntimeException("Indice Fuera de rango");
    	}
	}
		public Sector DevolverSector(int indice) {
		if (indice >= 0 && indice < sectores.size()) {
        	return sectores.get(indice);
   		} else {
        	throw new RuntimeException("Indice Fuera de rango");
    	}
	}
	@Override
	
	public String toString(){
		//Ejemplo de lo deseado:
		//Teatro San Martín
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName()); //Agarro unicamente el NOMBRE de la clase
		sb.append(" ").append(this.nombre); //Agrego el nombre de la sede
		return sb.toString();
	}

	// GETTERS-----------------------------------------------------------------------------------

	public String getNombre() {
		return nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getCapacidadMaxima() {
		return capacidadMaxima;
	}

	public ArrayList<Sector> getSectores() {
		return sectores;
	}
}
