package ar.edu.ungs.prog2.ticketek;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;


public class Fecha implements Comparable<Fecha> {
	private LocalDate fecha;

	public Fecha(String entrada) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
  		LocalDate nuevoDate= LocalDate.parse(entrada, formato);
		this.fecha = nuevoDate;
	}

	public static Fecha actual() {
    	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yy");
    	String hoy = LocalDate.now().format(formato);
    	return new Fecha(hoy);
	}

	public boolean esPosterior(Fecha otro) {
		return this.fecha.isAfter(otro.fecha);
	}

	public boolean esAnterior(Fecha otro) {
		return this.fecha.isBefore(otro.fecha);
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
    @Override
    public int compareTo (Fecha otra) { //Hago un override para poder usar sort a la hora de ordenar las funciones para enlistarlas
		return this.fecha.compareTo(otra.fecha);
    }
}
