package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

public class Espectaculo {
	private String nombre;
	public Map<Fecha, Funcion> funciones;

	// CONSTRUCTOR
	public Espectaculo(String nombre) {
		this.nombre = nombre;
		funciones = new HashMap<Fecha, Funcion>();
	}

	// OPERACIONES
	public void agregarFuncion(String fecha, Sede sedeFuncion, double precioBase) {
		Fecha date = new Fecha(fecha);
		if (!funciones.containsKey(date)) { // Chequeo que no haya otra funcion con la misma fecha
			Funcion nuevaFuncion = new Funcion(fecha, sedeFuncion, precioBase);
			funciones.put(date, nuevaFuncion); // Guardo la funcion en el diccionario
		} else {
			throw new RuntimeException("Ya existe una funcion con esa fecha");
		}
	}

	public double consultarCostoEntrada(String fecha) {
		Fecha dateFecha = new Fecha (fecha);
		if(funciones.containsKey(dateFecha)){
			return this.funciones.get(dateFecha).getPrecioBase();
		}
		throw new RuntimeException("No existe una funcion con esa fecha");
	}
		public double consultarCostoEntrada(String fecha, String sector) {
		double precioTotal = 0;
		Fecha dateFecha = new Fecha (fecha);
		if(funciones.containsKey(dateFecha)){
			Funcion funcion = this.funciones.get(dateFecha);
			Sede sede= funcion.getSede();
			precioTotal = funcion.getPrecioBase();
			if (sede.getClass().equals(MiniEstadio.class)){
				precioTotal += ((MiniEstadio)sede).getValorConsumision();
			}
			precioTotal = precioTotal * (1+sede.getSector(sector).getAdicionalSector()/100.0);
			return precioTotal;
		}
		throw new RuntimeException("No existe una funcion con esa fecha");
	}
//GETTERS -------------------------------

	public String getNombre() {
		return nombre;
	}

	public Map<Fecha, Funcion> getFunciones() {
		return funciones;
	}

	public Sede devolverSedeDeLaFuncion(String fecha) {
		Fecha localDate = new Fecha(fecha);
		Sede sede = funciones.get(localDate).getSede();
		return sede;
	}

	public Funcion devolverFuncion(String fecha) {
		Fecha localDate = new Fecha(fecha);
		return funciones.get(localDate);
	}

	@Override
	public String toString() {
		return this.nombre.toString();
	}
}
