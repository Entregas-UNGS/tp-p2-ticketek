package ar.edu.ungs.prog2.ticketek;

import java.util.UUID;

public class Entrada implements IEntrada {
	private static int contadorCodigos = 1;
	private String codigo;
	private Espectaculo espectaculo;
	private Funcion funcion;
	private Ubicacion ubicacion;
	private double precio;
	private String emailUsuario;

	//CONSTRUCTORES

	public Entrada(Espectaculo espectaculo, String fecha, String email , Sector sector) { //SE USA PARA ESTADIO DE FUTBOL, YA QUE NO UTILIZA ASIENTOS
		this.espectaculo=espectaculo;
		this.funcion = espectaculo.devolverFuncion(fecha);
		this.generarCodigo();
		this.emailUsuario = email;
		this.precio=precio();
		Ubicacion ubicacion = new Ubicacion(sector);
		this.ubicacion = ubicacion;
		sumoLoRecaudado(espectaculo, fecha); //Cada vez que compro una entrada sumo lo recaudado
	}

	//Aca podemos ver como se aplica sobrecarga

	public Entrada(Espectaculo espectaculo, String fecha,String email, Sector sector, int asiento ) { //SE USA PARA TEATRO Y MINI ESTADIO, QUE USAN ASIENTOS
		this.espectaculo=espectaculo;
		this.emailUsuario = email;
		this.funcion = espectaculo.devolverFuncion(fecha);
		Ubicacion ubicacion = new Ubicacion(sector, asiento);
		this.ubicacion = ubicacion;
		this.generarCodigo();
		this.precio=precio();
		sumoLoRecaudado(espectaculo, fecha); //Cada vez que compro una entrada sumo lo recaudado
	}
	// OPERACIONES

	@Override 
	public double precio() {
		double precioTotal = funcion.getPrecioBase();
		if (funcion.getSede() instanceof EstadioDeFutbol){ //Si es EstadioDeFutbol no influye en el precio
			return precioTotal; //El preico del Estadio de futbol no tiene ninguna modificacion
		}
		precioTotal= precioTotal*(1+this.ubicacion.getSector().getAdicionalSector()/100.0); // Aumento el porcentaje del adicional Al sector 
		if (funcion.getSede() instanceof MiniEstadio){
			precioTotal+=((MiniEstadio)funcion.getSede()).getValorConsumision(); //DownCasting
			//Sumo despues de calcular el porcentaje porque si no se aplica mal el porcentaje
		}
		return precioTotal;
	}

	@Override
	public String ubicacion() {
		return this.ubicacion.toString();
	}

	public void anularEntrada(){ //Se encarga de desocupar el asiento para que lo pueda ocupar alguien mas
		if(!(this.funcion.getSede() instanceof EstadioDeFutbol)){
			Sector sector = ubicacion.getSector();
			boolean[] asientos = funcion.asientosOucpados.get(sector);
			asientos[ubicacion.getAsiento()] = false;
			Espectaculo espectaculo = this.espectaculo;
			String fecha = this.funcion.ObtenerFecha().toString();
			RestoALoRecaudado(espectaculo, fecha); //Cada ves que anulo una entrada resto lo recaudado por esa entrada
		}
	}
	// Operaciones
	public Entrada crearUnaNuevEntradaModificada(String fecha, String sector, int asiento){
		Fecha nuevaFecha = new Fecha(fecha);
		if(this.espectaculo.getFunciones().containsKey(nuevaFecha)){
			Funcion nuevaFuncion = this.espectaculo.devolverFuncion(fecha);
			if(!(nuevaFuncion.getSede() instanceof EstadioDeFutbol)){
				Sector nuevoSector = nuevaFuncion.getSede().getSector(sector);
				return new Entrada(this.espectaculo, fecha, this.emailUsuario, nuevoSector, asiento);
			}else{
				throw new RuntimeException("El estadio de futbol no usa asientos");
			}
		}
		throw new RuntimeException("No existe una funcion para el mismo espectaculo en esa fecha");
	}
	public Entrada crearUnaNuevEntradaModificada(String fecha){
		Fecha nuevaFecha = new Fecha(fecha);
		if(this.espectaculo.getFunciones().containsKey(nuevaFecha)){
			Funcion nuevaFuncion = this.espectaculo.devolverFuncion(fecha);
			if((nuevaFuncion.getSede() instanceof EstadioDeFutbol)){
				Sector nuevoSector = nuevaFuncion.getSede().getSectores().get(0);
				return new Entrada(this.espectaculo, fecha, this.emailUsuario, nuevoSector);
			}else{
				throw new RuntimeException("la sede Usa asientos usa asientos");
			}
		}
		throw new RuntimeException("No existe una funcion para el mismo espectaculo en esa fecha");
	}
	private void sumoLoRecaudado(Espectaculo espectaculo, String fecha) {
		Sede sede = espectaculo.devolverSedeDeLaFuncion(fecha);
		if(espectaculo.getRecaudado().containsKey(sede.getNombre())){
			double totalRecaudado = espectaculo.getRecaudado().get(sede.getNombre()) + precio();
			espectaculo.getRecaudado().replace(sede.getNombre(), totalRecaudado);
		}else{
			espectaculo.getRecaudado().put(sede.getNombre(), precio());
		}
	}
	private void RestoALoRecaudado(Espectaculo espectaculo, String fecha) {
		Sede sede = espectaculo.devolverSedeDeLaFuncion(fecha);
		if(espectaculo.getRecaudado().containsKey(sede.getNombre())){
			double totalRecaudado = espectaculo.getRecaudado().get(sede.getNombre()) - precio();
			espectaculo.getRecaudado().replace(sede.getNombre(), totalRecaudado);
		}else{
			throw new RuntimeException("No existe una funcion en esta sede");
		}
	}

	@Override
	public String toString() {
	//Ejemplo de lo que se espera que devuelva
	//- 7196 - Coldplay en vivo - 30/04/2025 P - La bombonera - CAMPO
	Fecha fechaActual = Fecha.actual();
	StringBuilder sb = new StringBuilder();
    sb.append("- ")
	  .append(this.codigo)
      .append(" - ")
      .append(this.espectaculo.getNombre())
      .append(" - ")
      .append(this.funcion.ObtenerFecha().toString());
	  if(funcion.ObtenerFecha().esAnterior(fechaActual)){
		 sb.append(" P - ");
	  }else{
		sb.append(" - ");
	  }
      sb.append(this.funcion.getSede().getNombre());
	  if (this.ubicacion !=null) {
		sb.append(" - ").append(this.ubicacion.toString());
	  }
    return sb.toString();
	}
	private void generarCodigo() {
    	this.codigo = "" + contadorCodigos;
		contadorCodigos++;
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
	public String getCodigo(){
		return this.codigo;
	}
}
