package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.util.Objects;


public class Fecha {
	private LocalDate fecha;
	private final String SEPARADOR_ISO = "-";
	private final String SEPARADOR_DMY = "/";

	public Fecha(String entrada) {
		this.fecha = formatearFecha(entrada);
	}

	public static Fecha actual() {
		return new Fecha(LocalDate.now().toString());
	}

	public boolean esPosterior(Fecha otro) {
		return this.fecha.isAfter(otro.fecha);
	}

	public int diasDesde(Fecha inicio) {
		// TODO
		return 0;
	}

	private LocalDate formatearFecha(String fechaIngresada) {
		if (tieneSeparadorISO(fechaIngresada)) {
			return parsearFormatoISO(fechaIngresada);
		}

		if (tieneSeparadorDMY(fechaIngresada)) {
			return parsearFormatoDMY(fechaIngresada);
		}

		throw new RuntimeException("Fecha con formato incorrecto");
	}

	private LocalDate parsearFormatoISO(String fechaEntrada) {
		String[] fechaSeparada = fechaEntrada.split(SEPARADOR_ISO);

		if (fechaSeparada[0].length() != 4) { // Verifico que el año tenga solamente 4 digitos
			throw new RuntimeException("Fecha con formato incorrecto");
		}

		return LocalDate.parse(fechaEntrada);
	}

	private LocalDate parsearFormatoDMY(String fechaEntrada) {
		String[] fechaSeparada = fechaEntrada.split(SEPARADOR_DMY);

		if (fechaSeparada[2].length() != 2) { // Verifico que el año tenga solamente 2 digitos
			throw new RuntimeException("Fecha con formato incorrecto");
		}

		int dia = Integer.parseInt(fechaSeparada[0]);
		int mes = Integer.parseInt(fechaSeparada[1]);
		int anio = Integer.parseInt(fechaSeparada[2]);
		anio += 2000; // asumo años después del 2000

		return LocalDate.of(anio, mes, dia);
	}

	private boolean tieneSeparadorISO(String fecha) {
		return fecha.contains(SEPARADOR_ISO);
	}

	private boolean tieneSeparadorDMY(String fecha) {
		return fecha.contains(SEPARADOR_DMY);
	}

	@Override
	public String toString() {
		// Extraigo dia, mes y año de la fecha con formato (yyyy-mm-dd)
		int dia = fecha.getDayOfMonth();
		int mes = fecha.getMonthValue();
		int anio = fecha.getYear() % 100;

		return String.format("%02d/%02d/%d", dia, mes, anio);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Fecha otra = (Fecha) obj;
		return fecha.equals(otra.fecha);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha);
	}
}
