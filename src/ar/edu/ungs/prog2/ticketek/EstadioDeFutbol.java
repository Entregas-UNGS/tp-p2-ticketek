package ar.edu.ungs.prog2.ticketek;


public class EstadioDeFutbol extends Sede {
	//ESTA BIEN EL CONSTRUCTOR O SE PUDE USAR DIRECTAMENTE EL DE SEDE?
	public EstadioDeFutbol(String nombre, String direccion, int capacidadMaxima) {
			//Chequear que no exista una sede con el mismo nombre
		super(nombre, direccion, capacidadMaxima);
		int adicionalAlSector = 0;
		Sector campo = new Sector ("Campo", capacidadMaxima, adicionalAlSector); //Asumo que la cantidad de asientos por fila en campo es la capMax porq no hay asientos en campo
		super.sectores.add(campo);
	}

}
