package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {
	String email;
	String contraseña;
	String nombre;
	String apellido;
	ArrayList<Entrada> entradas; 
	Date FechaActual;
	//Constructor
	public Usuario(String email, String contraseña, String nombre, String apellido) {
		this.email = email;
		this.contraseña = contraseña;
		this.nombre = nombre;
		this.apellido = apellido;
		this.entradas = new ArrayList<>();
	}
	public void ComprarEntrada (Entrada e) {
		//COMPLETAR
	}
	public ArrayList<Entrada> todasLasEntradas (){
		ArrayList<Entrada> entradas = new ArrayList<>();
		//COMPLETAR
		return entradas;
	}
	public ArrayList<Entrada> entradasFuturas (){ 
		Fecha fechaActual = Fecha.actual();
		ArrayList<Entrada> entradasFuturas = new ArrayList<>();

		for (Entrada entrada : entradas) { //Me saltaba error con IEntrada y lo cambie, pero hay que chequear si funciona igual
			if (entrada.obtenerFecha().esPosterior(fechaActual)) {
				entradasFuturas.add(entrada);
			}
		}
		return entradasFuturas;
	}

}
