package ar.edu.ungs.prog2.ticketek;

public class MiniEstadio extends Sede{
	double valorConsumision;
	Integer cantPuestos;
	
	public MiniEstadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima);
		this.valorConsumision=precioConsumicion;
		this.cantPuestos=cantidadPuestos;
		super.crearSectores(sectores, capacidad, asientosPorFila, porcentajeAdicional);
	}
	//GETTERS
	public double getValorConsumision() {
		return valorConsumision;
	}

	public Integer getCantPuestos() {
		return cantPuestos;
	}
}
