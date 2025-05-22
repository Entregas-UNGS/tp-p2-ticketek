package ar.edu.ungs.prog2.ticketek;

import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TicketekTest{

    private ITicketek ticketek;
    private String[] sectores_teatro = {"VIP", "Comun", "Baja", "Alta"};
    private int[] capacidad_teatro = {100, 200, 300, 400};
    private int[] capacidad_miniestadio = {50, 100, 150, 200};
    private int[] porcentajeAdicionalTeatro = {70, 40, 50, 0};
    private int asientosPorFilaTeatro = 30;

    private List<IEntrada> entradasJavierColdplay;
    @Before
    public void init() {
    	ticketek = new Ticketek();

        ticketek.registrarUsuario("nores@campus.ungs.edu.ar", "Jose", "Nores", "1234");
        ticketek.registrarUsuario("javierm@campus.ungs.edu.ar", "Javier", "Marenco", "1234");

        ticketek.registrarSede("El monumental", "calle 1", 100);
        ticketek.registrarSede("La bombonera", "calle 2", 200);
        ticketek.registrarSede("Mario Kempes", "avenida 123", 300);
        ticketek.registrarSede("Estadio Único", "boulevard 456", 400);

        ticketek.registrarSede("Teatro Gran Rex", "calle 3", 1000, asientosPorFilaTeatro, sectores_teatro, capacidad_teatro, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Teatro Colón", "libertad 621", 1000, asientosPorFilaTeatro, sectores_teatro, capacidad_teatro, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Teatro San Martín", "avenida corrientes 1530", 1000, asientosPorFilaTeatro, sectores_teatro, capacidad_teatro, porcentajeAdicionalTeatro);

        ticketek.registrarSede("Estadio mini 1", "calle 4", 500, asientosPorFilaTeatro, 30, 25000.0, sectores_teatro, capacidad_miniestadio, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Mini Arena Norte", "pasaje 5", 500, asientosPorFilaTeatro, 30, 20000.0, sectores_teatro, capacidad_miniestadio, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Microestadio Sur", "pje. 10", 500, asientosPorFilaTeatro, 30, 15000.0, sectores_teatro, capacidad_miniestadio, porcentajeAdicionalTeatro);

        String[] fechasSirenita = {"25/07/25", "28/07/25", "30/07/25", "31/07/25", "01/08/25"};
        String[] sedesSirenita = {"Teatro Gran Rex", "Teatro Gran Rex", "Teatro Colón", "Teatro Colón", "Teatro Gran Rex"};
        String[] fechasReyLeon = {"26/07/25", "29/07/25", "31/08/25", "31/09/25", "01/10/25"};
        String[] sedesReyLeon = {"Teatro Gran Rex", "Teatro Gran Rex", "Teatro Colón", "Teatro Colón", "Teatro Gran Rex"};
        String[] fechasColdplay = {"25/07/25", "28/07/25", "30/07/25", "31/07/25", "01/08/25"};
        String[] sedesColdplay = {"La bombonera","La bombonera","La bombonera","La bombonera","La bombonera"};
        String[] fechasStandUp = {"10/04/25","25/07/25", "28/07/25", "30/07/25", "31/07/25", "01/08/25"};
        String[] sedesStandUp = {"Mini Arena Norte","Mini Arena Norte","Mini Arena Norte","Microestadio Sur","Mini Arena Norte","Mini Arena Norte"};
        String[] fechasBallet = {"01/03/25","25/07/25"};
        String[] sedesBallet = {"Microestadio Sur", "Microestadio Sur"};

        registrarEspectaculo("La sirenita", fechasSirenita, sedesSirenita, 50000.0);
        registrarEspectaculo("El Rey Leon", fechasReyLeon, sedesReyLeon, 60000.0);
        registrarEspectaculo("Coldplay en vivo", fechasColdplay, sedesColdplay, 90000.0);
        registrarEspectaculo("Stand up Comedy", fechasStandUp, sedesStandUp, 110000.0);
        registrarEspectaculo("Ballet Clásico", fechasBallet, sedesBallet, 180000.0);
        
        // Vender entradas para el usuario Javier
        entradasJavierColdplay =  ticketek.venderEntrada("Coldplay en vivo", "30/07/25", "javierm@campus.ungs.edu.ar", "1234", 3);
        ticketek.venderEntrada("Coldplay en vivo", "01/08/25", "nores@campus.ungs.edu.ar", "1234", 3);
        ticketek.venderEntrada("La sirenita", "28/07/25", "javierm@campus.ungs.edu.ar", "1234", "Comun", new int[]{1, 2, 3, 4});
        ticketek.venderEntrada("La sirenita", "25/07/25", "nores@campus.ungs.edu.ar", "1234", "Baja", new int[]{1, 2, 3, 4});
        ticketek.venderEntrada("Stand up Comedy", "30/07/25", "nores@campus.ungs.edu.ar", "1234", "Alta", new int[]{1, 2, 3, 4});

        // Vender entradas en el pasado para verificar
        ticketek.venderEntrada("Stand up Comedy", "10/04/25", "javierm@campus.ungs.edu.ar", "1234", "VIP", new int[]{1, 2, 3, 4});
        ticketek.venderEntrada("Stand up Comedy", "10/04/25", "nores@campus.ungs.edu.ar", "1234", "VIP", new int[]{11, 12, 13, 14});
        ticketek.venderEntrada("Ballet Clásico", "01/03/25", "nores@campus.ungs.edu.ar", "1234", "Baja", new int[]{1, 2, 3, 4});
    }

   
    private void registrarEspectaculo(String nombre, String[] fechas, String[] sedes, double precioBase) {
		ticketek.registrarEspectaculo(nombre);
		for (int i=0; i<fechas.length; i++) {
			ticketek.agregarFuncion(nombre, fechas[i], sedes[i], precioBase);
		}
	}


	@Test(expected = RuntimeException.class)
    public void ej01_registrarSedeRegistrada() {
        ticketek.registrarSede("El monumental", "calle 1", 100);
    }

    //el email del usuario no puede repetirse.
    @Test(expected = RuntimeException.class)
    public void ej02_registrarUsuarioRegistrado() {
        ticketek.registrarUsuario("nores@campus.ungs.edu.ar", "Jose2", "Nores2", "1234");
    }

    @Test(expected = RuntimeException.class)
    public void ej03_registrarEspectaculoConNombreRepetido() {
        ticketek.registrarEspectaculo("Ballet Clásico");
    }
    
    @Test(expected = RuntimeException.class)
    public void ej14_agregarFuncionesConFechasRepetidas() {
    	String nombre="Power rangers";
        ticketek.registrarEspectaculo(nombre);
        ticketek.agregarFuncion(nombre, "15/07/25", "Teatro Gran Rex", 25000.0);
        ticketek.agregarFuncion(nombre, "15/07/25", "Teatro Colón", 30000.0);
    }

   
    @Test(expected = RuntimeException.class)
    public void ej04_venderEntradaTeatroContraseniaInvalida() {
        int[] asientos = { 10, 9, 1, 2 };
        ticketek.venderEntrada(
	        "El Rey León", 
	        "29/07/25", 
	        "nores@campus.ungs.edu.ar", 
	        "12345", 
	        "VIP",
	        asientos 
        );
    }

    
    @Test(expected = RuntimeException.class)
    public void ej04_venderEntradaUsuarioInvalido() {
        int[] asientos = { 10, 9, 1, 2 };
        ticketek.venderEntrada(
        "El Rey León", 
        "29/07/25", 
        "falsoMail@campus.ungs.edu.ar", 
        "12345", 
        "VIP",
        asientos 
        );
    }

   
    @Test
    public void ej04_venderEntradaEstadio() {
        List<IEntrada> entradas = ticketek.venderEntrada(
        "Coldplay en vivo", 
        "28/07/25", 
        "nores@campus.ungs.edu.ar", 
        "1234", 
        3);
        //no deberia lanzar nada

        assertNotNull(entradas);
        assertEquals(3, entradas.size());
        for (int i = 0; i < entradas.size(); i++) {
            assertNotNull(entradas.get(i));
            assertEquals(90000.0, entradas.get(i).precio(),0.01);   
        }
    }
    
    @Test
    public void ej05_listarFuncionesDeEspectaculo() {
       String espectaculo = "Ballet Clásico";
       
       String listado = ticketek.listarFunciones(espectaculo);
       // - (31/07/2025) Teatro Colón - Platea VIP: 30/50 | Platea Común: 60/70 | Platea Baja: 0/70 | Platea Alta: 50/50

       String esperado = " - (01/03/25) Microestadio Sur - VIP: 0/50 | Comun: 0/100 | Baja: 4/150 | Alta: 0/200\n";
       esperado += " - (25/07/25) Microestadio Sur - VIP: 0/50 | Comun: 0/100 | Baja: 0/150 | Alta: 0/200\n";
       assertEquals(esperado, listado);
    }

    // Refactorizar en base a los nuevos datos 
    // "Coldplay en vivo", "01/08/25", "nores@campus.ungs.edu.ar", "1234", 3
    // "La sirenita", "25/07/25", "nores@campus.ungs.edu.ar", "1234", "Baja", new int[]{1, 2, 3, 4}
    @Test
    public void ej06_listarEntradasFuturas() {

        List<IEntrada> entradasFuturas = ticketek.listarEntradasFuturas("nores@campus.ungs.edu.ar", "1234");
        assertNotNull(entradasFuturas);
        assertEquals(11, entradasFuturas.size()); 

        for (IEntrada entrada : entradasFuturas) {
            assertFalse(entrada.toString().isEmpty());
        }
    }

    @Test
    public void ej07_listarTodasEntradasUsuario() {
    	// Para Jose hay 8 entradas con fechas en el pasado
        List<IEntrada> entradasFuturas = ticketek.listarTodasLasEntradasDelUsuario("nores@campus.ungs.edu.ar", "1234");
        assertNotNull(entradasFuturas);
        assertEquals(19, entradasFuturas.size()); 
        int cont=0;
        for (IEntrada entrada : entradasFuturas) {
        	if( entrada.toString().contains(" P - "))
        		cont++;
        }
        
        assertEquals(8,cont);
    }

    
    @Test
    public void ej08_anularEntrada() {
        assertTrue(ticketek.anularEntrada(entradasJavierColdplay.get(0), "1234"));
    }

    @Test(expected = RuntimeException.class)
    public void ej08_anularEntradaConContraseniaIncorrecta() {
        ticketek.anularEntrada(entradasJavierColdplay.get(0), "ClaveFalsa");
    }

    
    @Test(expected = RuntimeException.class)
    public void ej08_anularEntradaIndefinida() {
        ticketek.anularEntrada(null, "1234");
    }
    
    @Test(expected = RuntimeException.class)
    public void ej08_anularEntradaInexistente() {
    	// 1ro quita OK
    	// 2do lanza exception porque la entrada ya no existe.
        ticketek.anularEntrada(entradasJavierColdplay.get(0), "1234");
        ticketek.anularEntrada(entradasJavierColdplay.get(0), "1234");

    }

    @Test
    public void ej09_cambiarEntrada() {
        IEntrada nuevaEntrada = ticketek.cambiarEntrada(entradasJavierColdplay.get(0), "1234", "25/07/25");
        assertNotNull(nuevaEntrada);
        String strEntrada = nuevaEntrada.toString();
        // Tambien se valida el formato del string esperado de las entradas.
        assertTrue(strEntrada.contains("- Coldplay en vivo - 25/07/25 - La bombonera - CAMPO"));
        
    }

    
    @Test
    public void ej10_calcularCostoTotalEntradaEstadio() {
        // Queremos saber el de una entrada ya comprada.
    	// en este caso es la entrada para coldplay con 
    	// costo de $ 90000.0
        double costoTotal = entradasJavierColdplay.get(0).precio();

        assertTrue(costoTotal > 0);
        assertEquals(90000.0, costoTotal, 0.01);
    }

    
    @Test
    public void ej11_consultarValorEntrada() {
        // Queremos saber el costo de una entrada para una funcion en teatro. 
        // Tomemos el espectaculo "La sirenita" en la fecha "28/07/25"
        // y el sector "Comun" de un 40% de aumento al costo base 
        // 50000.0 * 0.40 = 20000.0 entonces 50000.0 + 20000.0 = 70000.0
        String nombreEspectaculo = "La sirenita";
        String fecha = "28/07/25";
        String sector = "Comun";

        double costoTotal = ticketek.costoEntrada(nombreEspectaculo, fecha, sector);
        assertEquals(70000.0, costoTotal, 0.01);
    }

    
    @Test
    public void ej12_calcularTotalRecaudadoEspectaculo() {
        // Recaudacion total de "Stand up Comedy" en miniestadios.
    	// 4 en mini con adicional de 15.000
    	// 8 con Adicional de 20.000
        // 4 plateas altas del 30/07/25 que compro jose + 8 vips del 10/04/25 
        // precio base: 110000.0 + 70% es 77000.0 = 187000.0 para cada entrada vip
        // precio base: 110000.0 para cada entrada platea alta
        // 187000 * 8 + 110000 * 4 + 15000*4 + 20000*8 = 2156000
        // que compraron jose y javier

        double totalRecaudado = ticketek.totalRecaudado("Stand up Comedy");
        assertEquals(2156000.0, totalRecaudado, 0.01);
    }


    @Test
    public void ej13_calcularTotalRecaudadoEspectaculoEnSede() {
        // Recaudacion total de "Stand up Comedy"
        // 4 plateas altas del 30/07/25 que compro jose en "Microestadio Sur"
        // precio base: 110000.0 para cada entrada platea alta
    	// 110000 * 4 + 15.000*4 = 500000
        double totalRecaudado = ticketek.totalRecaudadoPorSede("Stand up Comedy","Microestadio Sur");
        assertEquals(500000.0, totalRecaudado, 0.01);
    }

    @Test
    public void ej15_ConsultarEntradasVendidasParaEspectaculo() {
        // Para "Stand up Comedy" se vendieron 12 entradas
    	// y para "Coldplay en vivo" se vendieron 6
    	
        assertEquals(12, ticketek.listarEntradasEspectaculo("Stand up Comedy").size());
        assertEquals(6, ticketek.listarEntradasEspectaculo("Coldplay en vivo").size());
    }

    
   

}
