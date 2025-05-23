package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
		this.entradas.add(entrada);
	};

	public List<IEntrada> obtenerTodasLasEntradas() {
		return new ArrayList<>(this.entradas);
	}

	public List<IEntrada> obtenerEntradasFuturas() {
		Fecha fechaActual = Fecha.actual();
		List<IEntrada> entradasFuturas = new ArrayList<>();

		for (IEntrada entrada : entradas) {
			if (!(entrada instanceof Entrada))
				throw new RuntimeException("Error de tipo: entrada");

			Entrada e = (Entrada) entrada;

			if (e.obtenerFecha().esPosterior(fechaActual)) {
				entradasFuturas.add(e);
			}
		}
		return entradasFuturas;
	}

	// Getters
	public String obtenerEmail() {
		return this.email;
	}

	public String obtenerContrasenia() {
		return this.contrasenia;
	}
//
//	public String obtenerNombre() {
//		return nombre;
//	}
//
//	public String obtenerApellido() {
//		return apellido;
//	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Usuario otro = (Usuario) obj;
		return Objects.equals(email, otro.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(email);
	}

	@Override
	public String toString() {
		return "nombre:" + nombre + ", apellido:" + apellido + "email:" + email;
	}

}
