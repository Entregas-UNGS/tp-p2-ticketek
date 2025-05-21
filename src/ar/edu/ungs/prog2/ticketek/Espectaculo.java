package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;

public class Espectaculo {
	private String Nombre;
	private ArrayList <Funcion> funciones = new ArrayList<>();
	
	//CONSTRUCTOR
	public Espectaculo (String Nombre) {
		//COMPLETAR
	}
	//OPERACIONES
	public void agregarFuncion(Funcion funcion) {
		//COMPLETAR
	}
	public void ocuparAsiento(Sector sector,int fila, int asiento) {
		//COMPLETAR
	}
//GETTERS -------------------------------
	
	public String getNombre() {
		return Nombre;
	}
	public ArrayList<Funcion> getFunciones() {
		return funciones;
	}
	
}
