package ar.edu.ungs.prog2.ticketek;

public class Entrada implements IEntrada {
	private final String codigo;
	private Espectaculo espectaculo;
	private Funcion funcion;
	private Ubicacion ubicacion;
	private Double precio;

	public Entrada() {
		this.codigo = Codigo.generar();
	}

	@Override
	public double precio() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String ubicacion() {
		// TODO Auto-generated method stub
		return null;
	}

	// Operaciones
	public float obtenerPrecio() {
		// COMPLETAR
		return 0;
	}

	public void cambiarSedeYFuncion(Funcion nuevaFuncion) {
		// COMPLETAR
	}

	// GETTERS ---------------------------------------------
	public Fecha getFecha() {
		return this.funcion.ObtenerFecha();
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
