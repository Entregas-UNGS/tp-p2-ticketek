package ar.edu.ungs.prog2.ticketek;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticketek implements ITicketek{
	
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
	//Preguntar si esta bien
	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima) {
		if (!sedes.containsKey(nombre)){ //Chequeo que no haya otra sede con el mimso nombre
			EstadioDeFutbol nueva = new EstadioDeFutbol(nombre, direccion, capacidadMaxima);
			sedes.put(nombre, nueva); //Creo y guardo la sede en el diccionario
		}
		
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
		if (!sedes.containsKey(nombre)){ //Chequeo que no haya otra sede con el mimso nombre
			Teatro nueva = new Teatro(nombre, direccion, capacidadMaxima, asientosPorFila, sectores, capacidad, porcentajeAdicional);
			sedes.put(nombre, nueva); //Creo y guardo la sede en el diccionario
		}
		
	}

	@Override
	public void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila,
			int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional) {
				if (!sedes.containsKey(nombre)){ //Chequeo que no haya otra sede con el mimso nombre
			MiniEstadio nueva = new MiniEstadio(nombre, direccion, capacidadMaxima, asientosPorFila,cantidadPuestos,precioConsumicion, sectores, capacidad, porcentajeAdicional);
			sedes.put(nombre, nueva); //Creo y guardo la sede en el diccionario
		}
		
	}

	@Override
	public void registrarUsuario(String email, String nombre, String apellido, String contrasenia) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registrarEspectaculo(String nombre) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			int cantidadEntradas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia,
			String sector, int[] asientos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarFunciones(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> listarEntradasFuturas(String email, String contrasenia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean anularEntrada(IEntrada entrada, String contrasenia) {
		// TODO Auto-generated method stub
		return false;
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

}
