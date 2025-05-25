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

//OPERACIONES
	private void calcularFila() {
		Integer AsientosPorFila = sector.getAsientosPorFila(); // Chequeo en el momento de vender la entrada si el asiento
																														// es valido
		this.fila = (asiento / AsientosPorFila) + 1;
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
