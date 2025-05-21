package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Usuario {
	private String email;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private List<IEntrada> entradas;

	/**
	 * @param email
	 * @param contrasenia
	 * @param nombre
	 * @param apellido
	 * @param entradas
	 */
	public Usuario(String email, String contrasenia, String nombre, String apellido) {
		this.email = email;
		this.contrasenia = contrasenia;
		this.nombre = nombre;
		this.apellido = apellido;
		this.entradas = new ArrayList<>();
	}

	public Usuario() {
	}

	public void comprarEntrada(IEntrada entrada) {
	};

	public List<IEntrada> obtenerTodasLasEntradas() {
		return this.entradas;
	}

	public List<IEntrada> obtenerEntradasFuturas() {
		Fecha fechaActual = Fecha.actual();
		List<IEntrada> entradasFuturas = new ArrayList<>();

		for (IEntrada entrada : entradas) {
			if (entrada.obtenerFecha().esPosterior(fechaActual)) { //SaltaErrorObtenerFecha
				entradasFuturas.add(entrada);
			}
		}
		return entradasFuturas;
	}

}
