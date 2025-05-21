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
	
}
