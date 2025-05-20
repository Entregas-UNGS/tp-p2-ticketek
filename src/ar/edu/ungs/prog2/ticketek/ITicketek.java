package ar.edu.ungs.prog2.ticketek;

import java.util.List;


public interface ITicketek {
	
    /**
     * 1) Registra las sedes que no tienen asientos, como los estadios.
     * Estas tienen un unico sector llamado campo.
     * 
     * Si el nombre ya está registrado, se debe lanzar una excepcion.
     * Si algun dato no es aceptable, se debe lanzar una excepcion.
     * 
     * @param nombre
     * @param direccion
     * @param capacidadMaxima
     */
    void registrarSede(String nombre, String direccion, int capacidadMaxima);
    
    /**
     * 1) Registra las sedes con asientos y sin puestos de venta, teatros.
     * Estos reciben la informacion de los sectores como parámetros.
     * 
     * Si el nombre ya está registrado, se debe lanzar una excepcion.
     * Si algun dato no es aceptable, se debe lanzar una excepcion.
     * 
     * @param nombre
     * @param direccion
     * @param capacidadMaxima
     * @param asientosPorFila
     * @param sectores
     * @param capacidad
     * @param porcentajeAdicional
     */
    void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, String[] sectores, int[] capacidad, int[] porcentajeAdicional);
    
    /**
     * 1) Registra las sedes con asientos y puestos de venta, miniestadios.
     * Estos reciben la informacion de los sectores como parámetros.
     * tambien el adicional por consumición y tambien la cantidad de puestos.
     * 
     * Si el nombre ya está registrado, se debe lanzar una excepcion.
     * Si algun dato no es aceptable, se debe lanzar una excepcion.
     * 
     * @param nombre
     * @param direccion
     * @param capacidadMaxima
     * @param asientosPorFila
     * @param cantidadPuestos
     * @param precioConsumicion
     * @param sectores
     * @param capacidad
     * @param porcentajeAdicional
     */
    void registrarSede(String nombre, String direccion, int capacidadMaxima, int asientosPorFila, int cantidadPuestos, double precioConsumicion, String[] sectores, int[] capacidad, int[] porcentajeAdicional);
  
  
    /**
     * 2) Registrar un nuevo usuario en el sistema
     * 
     * Si el email ya está registrado, se debe lanzar una excepcion
     * Si algun dato no es aceptable, se debe lanzar una excepcion.
     * 
     * @param email
     * @param nombre
     * @param apellido
     * @param contrasenia
     */
    void registrarUsuario(String email, String nombre, String apellido, String contrasenia);

    
    /**
     * 3) Registra un nuevo espectaculo en el sistema.
     * 
     * Si el nombre del espectáculo ya está registrado, 
     * lanza una excepcion.
     * 
     * @param nombre
     */
    void registrarEspectaculo(String nombre);
    
    
    /**
     * 14) Agrega una funcion nueva a un espectaculo ya registrado.
     * 
     * Si el espectaculo no está registrado o la sede o algun campo 
     * no es valido, se lanza una excepcion.
     * Si ya hay una funcion para esa fecha, lanza excepcion.
     * 
     * @param nombreEspectaculo
     * @param fecha en formato: dd/mm/YY
     * @param sede
     * @param precioBase
     */
    void agregarFuncion(String nombreEspectaculo, String fecha, String sede, double precioBase);
   
    
    /**
     * 4) Vende una o varias entradas a un usuario para funciones
     * en sedes no numeradas
     * 
     * Devuelve una lista con las entradas vendidas (Ver interfaz IEntrada).
     *  
     * Se debe lanzar una excepcion si:
     *  - Si la sede de la funcion está numerada
     *  - si el usuario no está registrado
     *  - si el espectaculo no está registrado
     *  - si la contraseña no es valida
     *  - etc...
     * 
     * @param nombreEspectaculo
     * @param fecha en formato: dd/mm/YY
     * @param email
     * @param contrasenia
     * @param cantidadEntradas
     * @return
     */
    List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, int cantidadEntradas);
    
    /**
     * 4) Vende una o varias entradas a un usuario para funciones 
     * con sedes numeradas.
     * 
     * Devuelve una lista con las entradas vendidas (Ver interfaz IEntrada).
     * 
     * Se debe lanzar una excepcion si:
     *  - Si la sede de la funcion no es numerada
     *  - si el usuario no está registrado
     *  - si el espectaculo no está registrado
     *  - si la contraseña no es valida
     *  - etc...
     *  
     * @param nombreEspectaculo
     * @param fecha en formato: dd/mm/YY
     * @param email
     * @param contrasenia
     * @param sector
     * @param asientos
     * @return
     */
    List<IEntrada> venderEntrada(String nombreEspectaculo, String fecha, String email, String contrasenia, String sector, int[] asientos);
    
    
    /**
     * 5) Devuelve un string donde cada fila representa una funcion 
     * y se detalla con el siguiente formato:
     * 	- Si es estadio: " - ({FECHA}) {NOMBRE SEDE} - {ENTRADAS VENDIDAS} / {CAPACIDAD SEDE}"
     *  - si no es estadio: " - ({FECHA}) {NOMBRE SEDE} - {NOMBRE SECTOR1}: {ENTRADAS VENDIDAS 1} / {CAPACIDAD SECTOR} | {NOMBRE SECTOR 2}: {ENTRADAS VENDIDAS 2} / {CAPACIDAD SECTOR 2} ..."
     * 
     * Por ejemplo:
     *  - (24/07/2025) El Monumental - 200/500
     *  - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50
     * 
     * @return un string con la lista de funciones del espectaculo.
     */
    String listarFunciones(String nombreEspectaculo);

    
    /**
     * 15) Busca todas las entradas vendidas para un espectaculo,
     * es decir, las entradas para todas sus funciones.
     * 
     * Ver interfaz IEntrada.
     * 
     * @param nombreEspectaculo 
     * @return
     */
    List<IEntrada> listarEntradasEspectaculo(String nombreEspectaculo);
    
    
    /**
     * 6) Devuelve una lista con todas las entradas que compró 
     * un usuario y aún no se han usado (según la fecha de la entrada)
     * Se debe autenticar al usuario.
     * 
     * Si el usuario no está registrado o la contraseña no es valida,
     * se lanza una excepcion
     * 
     * Ver interfaz IEntrada.
     * 
     * @return
     */
    List<IEntrada> listarEntradasFuturas(String email, String contrasenia);
    
    
    /**
     * 7) Devuelve una lista con todas las entradas que compró 
     * un usuario desde que se registró en el sistema.
     * Se debe autenticar al usuario.
     * 
     * Si el usuario no está registrado o la contraseña no es valida,
     * se lanza una excepcion
     * 
     * Ver interfaz IEntrada.
     * 
     * @param email
     * @param contrasenia
     * @return
     */
    List<IEntrada> listarTodasLasEntradasDelUsuario(String email, String contrasenia);

    
    /**
     * 8) Cancela una entrada comprada por el usuario. Se debe resolver en O(1)
     * 
     * Al cancelarla, el lugar asignado deberá volver a estar disponible.
     * 
     * Se deben validar los datos y lanzar una excepcion en caso de que 
     * algo sea invalido.
     * 
     * Si los datos son validos pero la fecha de la entrada ya pasó,
     * se debe devolver falso
     * 
     * Ver interfaz IEntrada.
     * 
     * 
     * @param Entrada
     * @param contrasenia
     * @return
     *  
     */
    boolean anularEntrada(IEntrada entrada, String contrasenia);

    
    /**
     * 9) Si puede asignar la entrada en la nueva fecha, anula la 
     * entrada anterior.
     * Devuelve una entrada nueva (Ver interfaz IEntrada).
     * 
     * Lanza excepcion si:
     *  - Si la entrada original está en el pasado.
     *  - Si la contraseña no es valida.
     *  - etc...
     * 
     * 
     * @param Entrada
     * @param contrasenia
     * @param fecha en formato: dd/mm/YY
     * @param sector
     * @param asiento
     * @return
     */
    IEntrada cambiarEntrada( IEntrada entrada, String contrasenia, String fecha, String sector, int asiento);
    
    
    /**
     * 9) Si puede asignar la ebtrada en la nueva fecha, anula la 
     * entrada anterior.
     * Devuelve una entrada nueva (Ver interfaz IEntrada).
     * 
     * Lanza excepcion si:
     *  - la entrada original está en el pasado
     *  - Si la contraseña no es valida.
     *  - etc...
     *  
     * 
     * @param Entrada
     * @param contrasenia
     * @param fecha en formato: dd/mm/YY
     * @return
     */
    IEntrada cambiarEntrada( IEntrada entrada, String contrasenia, String fecha);
    
    
    /**
     * 11) devuelve el precio de la entrada para una funcion de un 
     * espectaculo en estadio.
     * 
     * @param nombreEspectaculo
     * @param fecha en formato: dd/mm/YY
     * @return
     */
    double costoEntrada(String nombreEspectaculo, String fecha);

    
    /**
     * 11) devuelve el precio de una entrada para una funcion de un 
     * espectaculo en una sede con asientos.
     * 
     * @param nombreEspectaculo
     * @param fecha en formato: dd/mm/YY
     * @param sector
     * @return
     */
    double costoEntrada(String nombreEspectaculo, String fecha, String sector);

    
    /**
     * 12) Devuelve el total recaudado hasta el momento por un espectaculo.
     *  
     * @param nombreEspectaculo
     * @return
     */
    double totalRecaudado(String nombreEspectaculo);
    
    
    /**
     * 13) Devuelve el total recaudado por una sede para un espectaculo en particular.
     * 
     * @param nombreEspectaculo
     * @param nombreSede
     * @return
     */
    double totalRecaudadoPorSede(String nombreEspectaculo, String nombreSede);
}
