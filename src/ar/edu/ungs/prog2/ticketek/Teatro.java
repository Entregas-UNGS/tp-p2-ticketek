package ar.edu.ungs.prog2.ticketek;

public class Teatro extends Sede {
	//ESTA BIEN EL CONSTRUCTOR O SE PUDE USAR DIRECTAMENTE EL DE SEDE?
	public Teatro(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima);
		//Chequear que no exista una sede con el mismo nombre
		if(sectores.length == capacidad.length && capacidad.length == porcentajeAdicional.length){
   		 	for (int i = 0; i < sectores.length; i++) {
        		Sector platea = new Sector(sectores[i], capacidad[i], porcentajeAdicional[i]);
        		super.sectores.add(platea);
    		}
		}
		else{
			throw new RuntimeException("No coinciden la cantidad de datos dados");
		}
	}

}
