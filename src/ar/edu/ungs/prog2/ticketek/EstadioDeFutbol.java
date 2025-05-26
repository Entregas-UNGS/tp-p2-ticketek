package ar.edu.ungs.prog2.ticketek;


public class EstadioDeFutbol extends Sede {
	//ESTA BIEN EL CONSTRUCTOR O SE PUDE USAR DIRECTAMENTE EL DE SEDE?
	public EstadioDeFutbol(String nombre, String direccion, int capacidadMaxima) {
		super(nombre, direccion, capacidadMaxima);
		int adicionalAlSector = 0; //Lo agregamos de esta manera para poder modificarlo en caso de ser necesario
		String sector = "CAMPO";
		Sector campo = new Sector (sector, capacidadMaxima, adicionalAlSector); //Creo el sector
		super.sectores.add(campo); //Lo agrego al diciconario
	}

}
