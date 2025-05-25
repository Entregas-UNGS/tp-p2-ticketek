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

	public void ocuparAsiento(Sector sector, int fila, int asiento) {
		// COMPLETAR
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
