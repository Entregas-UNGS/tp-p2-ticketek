package ar.edu.ungs.prog2.ticketek;

public class Ubicacion {
	private Sector sector;
	private Integer fila;
	private Integer asiento;

	// CONSTRUCTOR
	public Ubicacion(Sector sector, int asiento) {
		this.sector = sector;
		this.asiento = asiento;
		calcularFila();
	}
	public Ubicacion(Sector sector) {
		this.sector = sector;
	}

//OPERACIONES
	private void calcularFila() {
		Integer AsientosPorFila = sector.getAsientosPorFila(); // Chequeo en el momento de vender la entrada si el asiento
																														// es valido
		this.fila = (asiento / AsientosPorFila) + 1;
	}
	@Override
	//Platea Común f:3 a:31
	public String toString(){
		StringBuilder sb = new StringBuilder();
		if (sector.getNombre().equals(sector.getNombre())){
			sb.append(sector.getNombre());
			return sb.toString();
		}
		sb.append(" f:").append(this.fila);
		sb.append(" a:").append(this.asiento);
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
