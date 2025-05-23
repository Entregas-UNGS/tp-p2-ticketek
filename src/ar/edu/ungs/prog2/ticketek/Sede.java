package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public abstract class Sede {
	private String tipo;
	private String nombre;
	private String direccion;
	private Integer capacidadMaxima;
	protected ArrayList<Sector> sectores;
	
	//CONSTRUCTOR
	public Sede(String nombre, String direccion, int capacidadMaxima) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.capacidadMaxima = capacidadMaxima;
		this.sectores= new ArrayList<Sector>();
	}

	public Sede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {

	}

	public Sede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
	}
	//Hace falta los constructores aca siendo una clase abstracta? O aplicamos directamente polimorfimo con
	//Teatro, MiniEstadio y EstadioDeFutbol
	public void crearSectores (String[] sectores, int[] capacidad, int[] porcentajeAdicional){
				if(sectores.length == capacidad.length && capacidad.length == porcentajeAdicional.length){
   		 	for (int i = 0; i < sectores.length; i++) {
        		Sector platea = new Sector(sectores[i], capacidad[i], porcentajeAdicional[i]);
        		this.sectores.add(platea);
    		}
		}
		else{
			throw new RuntimeException("No coinciden la cantidad de datos dados");
		}
	}

	//OPERACIONES
	public void revisarCapacidadMaxima() {
		//COMPLETAR
	}
	
	//GETTERS
	public String getTipo() {
		return tipo;
	}
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
	public Sector getSector(String nombreSector) {
	for (Sector sector : sectores) {
        if (sector.getNombre().equals(nombreSector)) {
            return sector;
        }
    }
    throw new RuntimeException("No Existe dicho secto");
	}
}
