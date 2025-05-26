package ar.edu.ungs.prog2.ticketek;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Ticketek implements ITicketek {

	private Map<String, Usuario> usuarios;
	private Map<String, Sede> sedes;
	private Map<String, Espectaculo> espectaculos;

	public Ticketek() {
		this.sedes = new HashMap<>();
		this.espectaculos = new HashMap<>();
		this.usuarios = new HashMap<>();
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
		if (!sedes.containsKey(nombre)) { // Chequeo que no haya otra sede con el mimso nombre
			EstadioDeFutbol nueva = new EstadioDeFutbol(nombre, direccion, capacidadMaxima);
			sedes.put(nombre, nueva); // Creo y guardo la sede en el diccionario
		} else {
			throw new RuntimeException("Ya existe esa sede");
		}

	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		if (!sedes.containsKey(nombre)) { // Chequeo que no haya otra sede con el mimso nombre
			Teatro nueva = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad,
					porcentajeAdicional);
			sedes.put(nombre, nueva); // Creo y guardo la sede en el diccionario
		} else {
			throw new RuntimeException("Ya existe esa sede");
		}
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		if (!sedes.containsKey(nombre)) { // Chequeo que no haya otra sede con el mimso nombre
			MiniEstadio nueva = new MiniEstadio(nombre, direccion, capacidadMaxima, asientosPorFila, cantidadPuestos,
					precioConsumicion, sectores, capacidad, porcentajeAdicional);
			sedes.put(nombre, nueva); // Creo y guardo la sede en el diccionario
		} else {
			throw new RuntimeException("Ya existe esa sede");
		}

	}

	@Override
	public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
		if (!usuarios.containsKey(email)) { // Chequeo que no haya otro usuario con el mimso mail
			Usuario nuevo = new Usuario(email, contrasenia, nombre, apellido);
			usuarios.put(email, nuevo); // Creo y guardo el usuario en el diccionario
		} else {
			throw new RuntimeException("Ya existe un usuario con este Mail");
		}

	}

	@Override
	public void registrarEspectaculo(String nombre) {
		if (!espectaculos.containsKey(nombre)) { // Chequeo que no haya otro espectaculo con el mimso nombre
			Espectaculo nuevo = new Espectaculo(nombre);
			espectaculos.put(nombre, nuevo); // Creo y guardo el espectaculo en el diccionario
		} else {
			throw new RuntimeException("Ya existe un espectaculo con este Mail");
		}

	}

	@Override
	public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
		if (espectaculos.containsKey(nombreEspectaculo) && sedes.containsKey(sede)) { // Chequeo que exista dicha sede y
																																									// espectaculo
			Sede sedeFuncion = sedes.get(sede);
			espectaculos.get(nombreEspectaculo).agregarFuncion(fecha, sedeFuncion, precioBase); // Agrego la funcion al
																																													// espectaculo
		} else {
			throw new RuntimeException("El espectaculo y/o la sede ingresada no existen");
		}

	}

	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			int cantidadEntradas) {
		// Verificaciones
		existeEspectaculo(nombreEspectaculo);
		existeUsuario(email);
		verificoUsuario(email, contrasenia);
		existeFuncion(fecha, nombreEspectaculo);
		// Creo las entradas y las guardo en la lista
		List<IEntrada> entradasEnlistadas = new ArrayList<>();
		Fecha fechaEntrada = new Fecha(fecha);
		Sector sectorEstadio = espectaculos.get(nombreEspectaculo).getFunciones().get(fechaEntrada).getSede()
				.DevolverSector(0);
		for (int i = 1; i <= cantidadEntradas; i++) {
			if(espectaculos.get(nombreEspectaculo).getFunciones().get(fechaEntrada).verificarDisponibilidad(sectorEstadio)){
			Entrada entradaNueva = new Entrada(espectaculos.get(nombreEspectaculo), fecha, email, sectorEstadio);
			entradasEnlistadas.add(entradaNueva);
			usuarios.get(email).comprarEntrada(entradaNueva);
			}else{
				throw new RuntimeException("No hay disponibilidad en campo para la entrada");
			}
		}
		return entradasEnlistadas;
	}

	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			String sector, int[] asientos) {
		// Verificaciones
		existeEspectaculo(nombreEspectaculo);
		existeUsuario(email);
		verificoUsuario(email, contrasenia);
		existeFuncion(fecha, nombreEspectaculo);
		// Creo las entradas y las guardo en la lista
		List<IEntrada> entradasEnlistadas = new ArrayList<>();
		//Guardo el sector y la funcion de la entrada
		Sector sectorEntrada = espectaculos.get(nombreEspectaculo).devolverFuncion(fecha).getSede().getSector(sector);
		Funcion funcionEntrada = espectaculos.get(nombreEspectaculo).devolverFuncion(fecha);
		for (int i = 0; i < asientos.length; i++) {
			if (funcionEntrada.verificarDisponibilidad(sectorEntrada, asientos[i])) {
				Entrada entradaNueva = new Entrada(espectaculos.get(nombreEspectaculo), fecha, email, sectorEntrada,
						asientos[i]); // Solucionar Filas
				entradasEnlistadas.add(entradaNueva);
				usuarios.get(email).comprarEntrada(entradaNueva);
				funcionEntrada.ocuparAsiento(sectorEntrada, asientos[i]);
			}
			// En caso de que una entrada no cumpla simplemente no se compra, pero sigue
			// comprando las otras del arreglo que si cumplen
			// lo requerido
		}
		return entradasEnlistadas;
	}

	@Override
	public String listarFunciones(String nombreEspectaculo) {
		if (!this.espectaculos.containsKey(nombreEspectaculo)) {
			throw new RuntimeException("Espectaculo no encontrado");
		}

		Map<Fecha, Funcion> funcionesPorEspectaculo = this.espectaculos.get(nombreEspectaculo).funciones;
		List<Fecha> fechas = new ArrayList<>(funcionesPorEspectaculo.keySet()); // Guardo las fechas para darles orden
		fechas.sort(null); // Ordeno las fechas

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < fechas.size(); i++) {
			Funcion funcion = funcionesPorEspectaculo.get(fechas.get(i)); // Llamo a la funcion en orden cronologico segun la
																																		// lista ordenada
			sb.append(funcion.toString()); // Agrego la funcion al String Builder
			if (i < fechas.size()) { // Si no es el ultimo indice hago un salto de linea
				sb.append("\n");
			}
		}
		return sb.toString(); // Returno el String con las funciones ordenadas
	}

	@Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
		if (!espectaculos.containsKey(nombreEspectaculo)) { //Verifico si existe el espectaculo
			throw new RuntimeException("El espectáculo no existe");
		}

		List<IEntrada> entradas = new ArrayList<>();

		for (Usuario usuario : usuarios.values()) {
			for (IEntrada e : usuario.obtenerTodasLasEntradas()) {
				Entrada entrada = (Entrada) e;
				String espectaculoDeLaEntrada = entrada.getEspectaculo().getNombre(); //Recorro todas las entradas

				if (espectaculoDeLaEntrada.equals(nombreEspectaculo)) { // Si es la correcta
					entradas.add(entrada); //Lo agrego a la lista
				}
			}
		}

		return entradas; //devuelvo la lista
	}

	@Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
		if(usuarios.containsKey(email)){ //Verficio que existe el usuario
			Usuario u = usuarios.get(email);
			if (contrasenia.equals(u.obtenerContrasenia())) { //Verifico contraseña
				return u.obtenerEntradasFuturas(); //Devuelvo las entradas que todavia no pasaron
			}
			throw new RuntimeException("La Contraseña es incorrecta");
		}
		throw new RuntimeException("No existe dicho usuario");
	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		if(usuarios.containsKey(email)){ //Verficio que existe el usuario
			Usuario u = usuarios.get(email); //Verifico contraseña
			if (contrasenia.equals(u.obtenerContrasenia())) {
				return u.obtenerTodasLasEntradas();//Devuelvo todas las entradas del usuario
			}
			throw new RuntimeException("La Contraseña es incorrecta");
		}
		throw new RuntimeException("No existe dicho usuario");
	}

	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		if (entrada != null) { //Verifico que la entrada no sea nula
			Entrada entradaActual = (Entrada) entrada; // DownCasting
			String email = entradaActual.getEmailUsuario();
			if (usuarios.containsKey(email)) { //Verifico que exista el Usuario
				Usuario u = usuarios.get(email);
				entradaActual.anularEntrada(); //Anulo la entrada (Desocupo asientos, esto lo recaudado)
				return u.anularEntrada(entradaActual, contrasenia); //Anulo la entrada y devuelvo true o un error
			}
			return false;
		}
		throw new RuntimeException("La entrada ingresada es vacia");
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) { //Para sedes con asientos
		if(entrada != null){ //Verifico que la entrada no sea nula
			Entrada entradaVieja = (Entrada)entrada; //DownCasting
			Usuario usuario = usuarios.get(entradaVieja.getEmailUsuario()); 
			Entrada entradaNueva = entradaVieja.crearUnaNuevEntradaModificada(fecha, sector, asiento); //Creo una nueva entrada con los datos dado
			//Elimino la anterior Entrada
			entradaVieja.anularEntrada();
			usuario.anularEntrada(entradaVieja, contrasenia); 
			//Agrego la entrada al usuario
			usuario.comprarEntrada(entradaNueva);
			return entradaNueva;
		}
		throw new RuntimeException("la entrada es nula");
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) { //Para sedes sin asientos
		if(entrada != null){//Verifico que la entrada no sea nula
			Entrada entradaVieja = (Entrada)entrada; //DownCasting
			Usuario usuario = usuarios.get(entradaVieja.getEmailUsuario());
			Entrada entradaNueva = entradaVieja.crearUnaNuevEntradaModificada(fecha); //Creo una nueva entrada con los datos dado
			//Elimino la anterior Entrada
			entradaVieja.anularEntrada();
			usuario.anularEntrada(entradaVieja, contrasenia);
			//Agrego la entrada al usuario
			usuario.comprarEntrada(entradaNueva);
			return entradaNueva;
		}
		throw new RuntimeException("la entrada es nula");
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) { //Estadio de Futbol que no modifica el costo de la entrada segun la sede
		if(espectaculos.containsKey(nombreEspectaculo)){ //Verifico que exista el espectaculo
			return espectaculos.get(nombreEspectaculo).consultarCostoEntrada(fecha); //Devuelvo el costo de la entrada
		}
		throw new RuntimeException("No existe la funcion");
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {//Teatro y Miniestadio que modifica el costo de la entrada segun la sede
		existeEspectaculo(nombreEspectaculo); //Verifico si existe el espectaculo
		return espectaculos.get(nombreEspectaculo).consultarCostoEntrada(fecha, sector); //Devuelvo el costo de la entrada
	}

	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		existeEspectaculo(nombreEspectaculo); //Verifico si existe el espectaculo
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		double total = 0.0;
   		for (double valor : espectaculo.getRecaudado().values()) {
        	total += valor; //Sumo lo recaudado de cada funcion
    	}
    	return total;
	}

	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
		existeEspectaculo(nombreEspectaculo); //Verifico si existe el espectaculo
		Espectaculo espectaculo = espectaculos.get(nombreEspectaculo);
		if (espectaculo.getRecaudado().containsKey(nombreSede)){ //Si existe una sede con ese nomber
			return espectaculo.getRecaudado().get(nombreSede); //Devuelvo lo recaudado por la sede O(1)
		}
		throw new RuntimeException("No existe una funcion con esa sede");
	}

	private void existeUsuario(String mail) { // Verifico si existe el usuario con dicho mail
		if (usuarios.containsKey(mail)) { 
		} else {
			throw new RuntimeException("No existe usuario con ese mail");
		}
	}

	private boolean verificoUsuario(String mail, String contrasenia) { // Verifico si la contraseña es correcta
		boolean usuarioValido = false;

		if (usuarios.containsKey(mail)) {
			Usuario u = this.usuarios.get(mail);

			if (!contrasenia.equals(u.obtenerContrasenia())) {
				usuarioValido = true;
			}
		}

		return usuarioValido;
	}

	private void existeEspectaculo(String nombre) { // Verifico si existe el espectaculo
		if (espectaculos.containsKey(nombre)) {
			return;
		} else {
			throw new RuntimeException("No existe espectaculo con ese nombre");
		}
	}

	private void existeFuncion(String fecha, String nombreEspectaculo) {// Verifico si existe la funcion para dicho
																																			// espectaculo
		Fecha fechaFuncion = new Fecha(fecha);
		if (espectaculos.get(nombreEspectaculo).funciones.containsKey(fechaFuncion)) {
			return;
		} else {
			throw new RuntimeException("No existe una funcion en esa fecha");
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Ticketek:\n");
		sb.append("Usuarios registrados: ").append(usuarios.size()).append("\n");
		sb.append("Espectáculos disponibles: ").append(espectaculos.size()).append("\n");
		sb.append("Sedes disponibles: ").append(sedes.size()).append("\n");
		return sb.toString();
	}

//	public static <K, V> String concatenarValores(Map<K, V> mapa) {
//		StringBuilder sb = new StringBuilder();
//		Iterator<V> it = mapa.values().iterator();
//
//		while (it.hasNext()) {
//			V valor = it.next();
//			sb.append(valor.toString()).append(",\n");
//		}
//
//		return sb.toString();
//	}
//
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//
//		sb.append("Usuarios registrados:\n");
//		sb.append(concatenarValores(this.usuarios));
//		sb.append("\nEspectáculos disponibles:\n");
//		sb.append(concatenarValores(this.espectaculos));
//		sb.append("\nSedes disponibles:\n");
//		sb.append(concatenarValores(this.sedes));
//
//		return sb.toString();
//	}

}
