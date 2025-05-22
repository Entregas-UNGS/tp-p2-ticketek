package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.Map;

public class Espectaculo {
	private String nombre;
	private Map<Fecha, Funcion> funciones;
	
	//CONSTRUCTOR
	public Espectaculo (String nombre) {
		this.nombre = nombre;
	}
	//OPERACIONES
	public void agregarFuncion(Funcion funcion) {
		if (!funciones.containsKey(funcion.ObtenerFecha())){ //Chequeo que no haya otra funcion con la misma fecha
			funciones.put(funcion.ObtenerFecha(), funcion); //Guardo la funcion en el diccionario
		}
		else{
			throw new RuntimeException("Ya existe esa funcion para esa fecha");
		}
	}
	public void ocuparAsiento(Sector sector,int fila, int asiento) {
		//COMPLETAR
	}
//GETTERS -------------------------------
	
	public String getNombre() {
		return nombre;
	}
	
}
