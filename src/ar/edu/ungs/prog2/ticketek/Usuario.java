package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Iterator;

public class Usuario {
	private final String email;
	private String contrasenia;
	private String nombre;
	private String apellido;
	private Map<String, IEntrada> entradas;

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
		this.entradas = new HashMap<>();
	}

	public void comprarEntrada(Entrada entrada) {
		entradas.put(entrada.getCodigo(), entrada); //Agrego la entrada al diccionario
	};

	public List<IEntrada> obtenerTodasLasEntradas() {
		return new ArrayList<>(entradas.values()); //Devuevlo la lista de todas las entradas del usuario
	}

	public List<IEntrada> obtenerEntradasFuturas() {
    	Fecha fechaActual = Fecha.actual();
    	List<IEntrada> entradasFuturas = new ArrayList<>();

   	 	Iterator <IEntrada> iterador = entradas.values().iterator(); //Resuelvo con iterator para recorrer
    	while (iterador.hasNext()) { //Mientras haya entradas
			IEntrada entrada = iterador.next();
			//Verifico que la entrada sea de tipo Entrada
			if (entrada instanceof Entrada) {
				Entrada e = (Entrada) entrada;
				if (e.obtenerFecha().esPosterior(fechaActual)) { //Si es posterior la agrego a la lista
					entradasFuturas.add(e);
				}
			}
    	}
    	return entradasFuturas;
	}
	public boolean anularEntrada(Entrada entrada, String c){
		if(c.equals(this.contrasenia)){ //Si al contraseña es igual
			if(entradas.containsKey(entrada.getCodigo())){ //Si la entrada existe
				entradas.remove(entrada.getCodigo(), entrada); //La saco de entradas
				entrada = null; //La hago null
				return true; //True, se pudo anular
			}
			throw new RuntimeException("La entrada no existe");
		}
		throw new RuntimeException("Contraseña incorrecta");

	}
	//Hacemos un equals en el que importa unicamente el email
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
	//Hacemos el toString con el nombre, apellido y email
	@Override
	public String toString() {
		return "nombre:" + nombre + ", apellido:" + apellido + "email:" + email;
	}
	// Getters------------------------------------------------------------------------------------------------------
	public String obtenerEmail() {
		return this.email;
	}

	public String obtenerContrasenia() {
		return this.contrasenia;
	}

}
