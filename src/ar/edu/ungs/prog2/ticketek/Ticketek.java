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
	private Usuario usuarioAutencicado;

	public Ticketek() {
		this.sedes = new HashMap<>();
		this.espectaculos = new HashMap<>();
		this.usuarios = new HashMap<>();
		this.usuarioAutencicado = new Usuario();
	}

	// Preguntar si esta bien
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
			Entrada entradaNueva = new Entrada(espectaculos.get(nombreEspectaculo), fecha, email, sectorEstadio);
			entradasEnlistadas.add(entradaNueva);
			usuarios.get(email).comprarEntrada(entradaNueva);
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
		Sector sectorEntrada = espectaculos.get(nombreEspectaculo).devolverFuncion(fecha).getSede().getSector(sector); // Guardo
																																																										// el
																																																										// sector
																																																										// para
																																																										// la
																																																										// entrada
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
		if (!espectaculos.containsKey(nombreEspectaculo)) {
			throw new RuntimeException("El espectáculo no existe");
		}

		List<IEntrada> entradas = new ArrayList<>();

		for (Usuario usuario : usuarios.values()) {
			for (IEntrada e : usuario.obtenerTodasLasEntradas()) {
				Entrada entrada = (Entrada) e;
				String espectaculoDeLaEntrada = entrada.getEspectaculo().getNombre();

				if (espectaculoDeLaEntrada.equals(nombreEspectaculo)) {
					entradas.add(entrada);
				}
			}
		}

		return entradas;
	}

	@Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
		Usuario u = usuarios.get(email);
		if (contrasenia.equals(u.obtenerContrasenia())) {
			return u.obtenerEntradasFuturas();
		}
		throw new RuntimeException("La Contraseña es incorrecta");

	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		Usuario u = usuarios.get(email);
		if (contrasenia.equals(u.obtenerContrasenia())) {
			return u.obtenerTodasLasEntradas();
		}
		throw new RuntimeException("La Contraseña es incorrecta");
	}

	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		if (entrada != null) {
			Entrada entradaActual = (Entrada) entrada; // Casting down
			String email = entradaActual.getEmailUsuario();
			if (usuarios.containsKey(email)) {
				Usuario u = usuarios.get(email);
				entradaActual.anularEntrada();
				return u.anularEntrada(entradaActual, contrasenia);
			}
			return false;
		}
		throw new RuntimeException("La entrada ingresada es vacia");
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha, String sector, int asiento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IEntrada cambiarEntrada(IEntrada entrada, String contrasenia, String fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double costoEntrada(String nombreEspectaculo, String fecha, String sector) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalRecaudado(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede) {
		// TODO Auto-generated method stub
		return 0;
	}

	private void existeUsuario(String mail) { // Verifico si existe el usuario con dicho mail
		if (usuarios.containsKey(mail)) {
			return;
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
}
