package ar.edu.ungs.prog2.ticketek;


import java.time.LocalDate;
import java.util.Objects;

public class Fecha {
	private LocalDate fecha;

	Fecha(String entrada) {

		this.fecha = formatearFecha(entrada);
	}
	
	public static String fechaActual() {
		return LocalDate.now().toString();
	}

	public boolean esPosterior(Fecha otro) {
		return this.fecha.isAfter(otro.fecha);
	}

	public int diasDesde(Fecha inicio) {
		// TODO
		return 0;
	}

	public LocalDate formatearFecha(String entrada) {
		// formato de entrada 31/08/25 o 2025-08-31
		// Asumo que las fechas son posteriores al año 2000
		StringBuilder sb = new StringBuilder(entrada);
		int dia;
		int mes;
		int anio;
		
		if(sb.charAt(2) == '/') { // dd/mm/yy
			dia = Integer.parseInt(sb.substring(0, 2));
			mes = Integer.parseInt(sb.substring(3, 5));
			anio = Integer.parseInt("20" + sb.substring(6, 8));
			
		} else { //yyyy-mm-dd
			dia = Integer.parseInt(sb.substring(8, 10));
			mes = Integer.parseInt(sb.substring(5, 7));
			anio = Integer.parseInt(sb.substring(0, 4));
		}
		
		/*
		System.out.println(dia);
		System.out.println(mes);
		System.out.println(anio);
		*/

		return LocalDate.of(anio, mes, dia);
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
