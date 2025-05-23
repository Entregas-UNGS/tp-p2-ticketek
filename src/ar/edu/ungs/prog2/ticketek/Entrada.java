package ar.edu.ungs.prog2.ticketek;


public class Entrada implements IEntrada {
	private final String codigo;
	private Espectaculo espectaculo;
	private Funcion funcion;
	private Ubicacion ubicacion;
	private double precio;
	private String emailUsuario;

	public Entrada(Espectaculo espectaculo, String fecha, String email) {
		this.espectaculo=espectaculo;
		this.funcion = espectaculo.devolverFuncion(fecha);
		this.codigo = Codigo.generar();
		this.emailUsuario = email;
		this.precio=precio();
	}

	public Entrada(Espectaculo espectaculo, String fecha,String email, Sector sector, int fila, int asiento ) { //SobreCarga
		this.espectaculo=espectaculo;
		this.funcion = espectaculo.devolverFuncion(fecha);
		Ubicacion ubicacion = new Ubicacion(sector, fila, asiento);
		this.ubicacion = ubicacion;
		this.codigo = Codigo.generar();
		this.precio=precio();
	}

	@Override
	public double precio() {
		double precioTotal = funcion.getPrecioBase();
		if (funcion.getSede() instanceof EstadioDeFutbol){ //Si es EstadioDeFutbol no influye en el precio
			return precioTotal;
		}
		if (funcion.getSede() instanceof MiniEstadio){
			precioTotal+=((MiniEstadio)funcion.getSede()).getValorConsumision(); //DownCasting
		}
		precioTotal= precioTotal*(1+this.ubicacion.getSector().getAdicionalSector()/100.0); // Aumento el porcentaje del adicional Al sector 
		return precioTotal;
	}

	@Override
	public String ubicacion() {
		// TODO Auto-generated method stub
		return null;
	}

	// Operaciones

	public void cambiarSedeYFuncion(Funcion nuevaFuncion) {
		// COMPLETAR
	}

	// GETTERS ---------------------------------------------
	public Fecha obtenerFecha() {
		return this.funcion.ObtenerFecha();
	}

	public Espectaculo getEspectaculo() {
		return espectaculo;
	}

	public Funcion getFuncion() {
		return funcion;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public double getPrecio() {
		return precio;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

}
