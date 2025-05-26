package ar.edu.ungs.prog2.ticketek;

public class Teatro extends Sede {
	//CONSTRUCTOR
	public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores,
			int[] capacidad, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima); 
		super.crearSectores(sectores, capacidad, asientosPorFila, porcentajeAdicional); //Creo y agrego los sectores

	}

}
