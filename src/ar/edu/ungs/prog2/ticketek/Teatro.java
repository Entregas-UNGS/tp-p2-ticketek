package ar.edu.ungs.prog2.ticketek;

public class Teatro extends Sede {
	// ESTA BIEN EL CONSTRUCTOR O SE PUDE USAR DIRECTAMENTE EL DE SEDE?
	public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores,
			int[] capacidad, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima);
		super.crearSectores(sectores, capacidad, asientosPorFila, porcentajeAdicional);

	}

}
