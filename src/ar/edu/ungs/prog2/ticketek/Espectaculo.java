package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.Map;

public class Espectaculo {
	private String nombre;
	public Map<Fecha, Funcion> funciones;
	private Map<String, Double> recaudado;

	// CONSTRUCTOR
	public Espectaculo(String nombre) {
		this.nombre = nombre;
		funciones = new HashMap<Fecha, Funcion>();
		recaudado = new HashMap<String, Double>();
	}

	// OPERACIONES
	public void agregarFuncion(String fecha, Sede sedeFuncion, double precioBase) {
		Fecha date = new Fecha(fecha);
		if (!funciones.containsKey(date)) { // Chequeo que no haya otra funcion con la misma fecha
			Funcion nuevaFuncion = new Funcion(fecha, sedeFuncion, precioBase);
			funciones.put(date, nuevaFuncion); // Guardo la funcion en el diccionario
			if(!(recaudado.containsKey(sedeFuncion.getNombre()))){
				recaudado.put(sedeFuncion.getNombre(), 0.0);
			}
		} else {
			throw new RuntimeException("Ya existe una funcion con esa fecha");
		}
	}
	//Consulto el precio de la entrada en el Estadio
	public double consultarCostoEntrada(String fecha) {
		Fecha dateFecha = new Fecha (fecha);
		if(funciones.containsKey(dateFecha)){
			return this.funciones.get(dateFecha).getPrecioBase(); //No sufre modificaciones el precio de los estadios
		}else{
			throw new RuntimeException("No existe una funcion con esa fecha");
		}
	}
	//Consulto el precio de la entrada en el MiniEstadio o Teatro
		public double consultarCostoEntrada(String fecha, String sector) {
		double precioTotal = 0;
		Fecha dateFecha = new Fecha (fecha);
		if(funciones.containsKey(dateFecha)){
			Funcion funcion = this.funciones.get(dateFecha); //Tomo la funcion
			Sede sede= funcion.getSede();
			precioTotal = funcion.getPrecioBase();
			if (sede instanceof MiniEstadio){ //Si es MiniEstadio o una instancia del mimso entra en el if
				precioTotal += ((MiniEstadio)sede).getValorConsumision();
			}
			precioTotal = precioTotal * (1+sede.getSector(sector).getAdicionalSector()/100.0); //Calculo el agregado por sector
			return precioTotal;
		}else{
			throw new RuntimeException("No existe una funcion con esa fecha");
		}
	}
//GETTERS -------------------------------

	public String getNombre() {
		return nombre;
	}

	public Map<Fecha, Funcion> getFunciones() {
		return funciones;
	}
		public Map<String, Double> getRecaudado() {
		return recaudado;
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
