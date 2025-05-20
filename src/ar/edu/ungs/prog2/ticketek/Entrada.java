package ar.edu.ungs.prog2.ticketek;

public class Entrada implements IEntrada {
	private Integer codigo;
	private Espectaculo espectaculo;
	private Funcion funcion;
	private Ubicacion ubicacion;
	private double precio;
	//Constructor
	public Entrada (Espectaculo espectaculo, Funcion funcion, Ubicacion ubicacion, double precio) { //El codigo lo creariamos nosotros, no lo ingresamos
		//COMPLETAR
	}
	
	//Operaciones
	public float obtenerPrecio() {
		//COMPLETAR
		return 0;
	}
	public void cambiarSedeYFuncion(Funcion nuevaFuncion) {
		//COMPLETAR
	}
	
//GETTERS ---------------------------------------------
	public Fecha obtenerFecha() {
		return this.funcion.ObtenerFecha();
	}
	public double precio() {
		return precio;
	}
	@Override
	public String ubicacion() {
		// TODO Auto-generated method stub
		//COMPLETAR
		return null;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public Espectaculo getEspectaculo() {
		return espectaculo;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public double getPrecio() {
		return precio;
	}
	
}
