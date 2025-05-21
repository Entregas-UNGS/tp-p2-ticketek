package ar.edu.ungs.prog2.ticketek;

public class MiniEstadio extends Sede{
	double valorConsumision;
	Integer cantPuestos;
	
	public MiniEstadio(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		super(nombre, direccion, capacidadMaxima);
		//Chequear que no exista una sede con el mismo nombre
		this.valorConsumision=precioConsumicion;
		this.cantPuestos=cantidadPuestos;
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
