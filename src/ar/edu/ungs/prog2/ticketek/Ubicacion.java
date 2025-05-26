package ar.edu.ungs.prog2.ticketek;

public class Ubicacion {
	private Sector sector;
	private Integer fila;
	private Integer asiento;

	// CONSTRUCTOR
	public Ubicacion(Sector sector, int asiento) { //Ubicacion Con asientos
		this.sector = sector;
		this.asiento = asiento;
		calcularFila();
	}
	public Ubicacion(Sector sector) {//Ubicacion Sin asientos
		this.sector = sector;
	}

//OPERACIONES
	private void calcularFila() {
		Integer AsientosPorFila = sector.getAsientosPorFila(); 
		// Chequeo en el momento de vender la entrada, sí el asiento es valido
		this.fila = (asiento / AsientosPorFila) + 1;
	}
	@Override
	public String toString(){
		//Ejemplo de lo deseado:
		//Platea Común f:3 a:31
		StringBuilder sb = new StringBuilder();
		sb.append(sector.getNombre());
		if (sector.getNombre().equals("CAMPO")){
			return sb.toString();
		}
		else{
			sb.append(" f:").append(this.fila);
			sb.append(" a:").append(this.asiento);
		}
		return sb.toString();
	}

//GETTERS------------------------------------------------------------
	public Sector getSector() {
		return sector;
	}

	public Integer getFila() {
		return fila;
	}

	public Integer getAsiento() {
		return asiento;
	}

}
