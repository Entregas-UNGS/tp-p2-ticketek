package ar.edu.ungs.prog2.ticketek;

import java.util.List;

public class Principal {

	public static void main(String[] args) {
		ITicketek ticketek = new Ticketek();
		// Empresa recien creada
		printEmpresa(ticketek, "Recien creada");
		
		// 1) Registrar Sedes
		
		ticketek.registrarSede("El monumental", "calle 1", 100);
        ticketek.registrarSede("La bombonera", "calle 2", 200);
        ticketek.registrarSede("Mario Kempes", "avenida 123", 300);
        ticketek.registrarSede("Estadio Único", "boulevard 456", 400);
        
        // // Auxiliares para registrar sedes con sectores
        String[] sectores_teatro = {"Platea VIP", "Platea Común", "Platea Baja", "Platea Alta"};
        int[] capacidad_teatro = {100, 200, 300, 400};
        int[] capacidad_miniestadio = {50, 100, 150, 200};
        int[] porcentajeAdicionalTeatro = {70, 40, 50, 0};
        int asientosPorFilaTeatro = 30;
        int asientosPorFilaMiniestadio = 25;

        ticketek.registrarSede("Teatro Gran Rex", "calle 3", 1000, asientosPorFilaTeatro, sectores_teatro, capacidad_teatro, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Teatro Colón", "libertad 621", 1000, asientosPorFilaTeatro, sectores_teatro, capacidad_teatro, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Teatro San Martín", "avenida corrientes 1530", 1000, asientosPorFilaTeatro, sectores_teatro, capacidad_teatro, porcentajeAdicionalTeatro);

        ticketek.registrarSede("Estadio mini 1", "calle 4", 500, asientosPorFilaMiniestadio, 10, 15000.0, sectores_teatro, capacidad_miniestadio, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Mini Arena Norte", "pasaje 5", 500, asientosPorFilaMiniestadio, 12, 30000.0, sectores_teatro, capacidad_miniestadio, porcentajeAdicionalTeatro);
        ticketek.registrarSede("Microestadio Sur", "pje. 10", 500, asientosPorFilaMiniestadio, 20, 20000.0, sectores_teatro, capacidad_miniestadio, porcentajeAdicionalTeatro);

		
		// 2) Registrar usuarios
		
        ticketek.registrarUsuario("ant@nio.rios.com", "Antonio", "Rios", "1234");
        ticketek.registrarUsuario("leo@mattioli.com.ar", "Leonardo", "Mattioli", "1234");
        ticketek.registrarUsuario("miguel.conejito@alejandro.com.ar", "Miguel Angel", "Lunardi", "1234");
        ticketek.registrarUsuario("alcides@violeta.com", "Alcides", "Berardo", "1234");

		// 3) y 14) Registrar espectaculos y sus funciones
        ticketek.registrarEspectaculo("El Rey León");        
		ticketek.agregarFuncion("El Rey León", "26/07/25", "Teatro Gran Rex", 90000.0);
		ticketek.agregarFuncion("El Rey León", "29/07/25", "Teatro Gran Rex", 90000.0);
		ticketek.agregarFuncion("El Rey León", "31/08/25", "Teatro Colón", 90000.0);
		ticketek.agregarFuncion("El Rey León", "31/09/25", "Teatro Colón", 90000.0);
		ticketek.agregarFuncion("El Rey León", "01/10/25", "Teatro Gran Rex", 90000.0);

        
        ticketek.registrarEspectaculo("Coldplay en vivo");
        ticketek.agregarFuncion("Coldplay en vivo", "25/07/25", "La bombonera", 130000.0);
        ticketek.agregarFuncion("Coldplay en vivo", "28/07/25", "La bombonera", 130000.0);
        ticketek.agregarFuncion("Coldplay en vivo", "30/07/25", "La bombonera", 130000.0);
        ticketek.agregarFuncion("Coldplay en vivo", "31/07/25", "La bombonera", 130000.0);
        ticketek.agregarFuncion("Coldplay en vivo", "01/08/25", "La bombonera", 130000.0);

		printEmpresa(ticketek, "Sedes, Usuarios y Funciones registradas.");

        
		// 4) Vender entradas. Se guardan algunas para anular y cambiar.
        
        // // Entradas para el rey leon
        int[] asientos = { 10, 9, 1, 2 };
        ticketek.venderEntrada(
            "El Rey León", 
            "31/08/25", 
            "alcides@violeta.com", 
            "1234", 
            "Platea Común",
            asientos 
        );
        List<IEntrada> entradasConejo = ticketek.venderEntrada(
	        "El Rey León", 
	        "29/07/25", 
	        "miguel.conejito@alejandro.com.ar", 
	        "1234", 
	        "Platea VIP",
	        asientos 
        );
        System.out.println("\nEntradas Miguel Conejito Alejandro para El Rey Leon:");
        for (IEntrada e: entradasConejo)
        	System.out.println(" - " + e);
        
        // // Entradas para coldplay
        List<IEntrada> entradasAlcides = ticketek.venderEntrada(
                "Coldplay en vivo", 
                "31/07/25", 
                "alcides@violeta.com", 
                "1234", 
                10 
                );
        System.out.println("\nEntradas Alcides para Coldplay:");
        for (IEntrada e: entradasAlcides)
        	System.out.println(" - " + e);
		
        // 5) Listar funciones de un espectaculo
        System.out.println("\nFunciones de El Rey Leon:");
        System.out.println(ticketek.listarFunciones("El Rey León"));
        
        System.out.println("\nFunciones de Coldplay:");
        System.out.println(ticketek.listarFunciones("Coldplay en vivo"));

		// 7) Listar todas las entradas compradas por un usuario
		
        List<IEntrada> todasLasEntradasAlcides = ticketek
        		.listarTodasLasEntradasDelUsuario("alcides@violeta.com", "1234");
        System.out.println("\nTodas las entradas de Alcides:");
        for (IEntrada e: todasLasEntradasAlcides)
        	System.out.println(" - " + e);

        // 10) y 11) Imprimir los precios para comparar el precio 
     	// de una entrada comprada con el de una funcion.
        System.out.println("\nPrecio de entrada para El Rey Leon: $" + entradasConejo.getFirst().precio());
        System.out.println("Precio de funcion para El Rey Leon (VIP): $" + ticketek.costoEntrada("El Rey León", "29/07/25", "Platea VIP"));
        System.out.println("Precio de funcion para El Rey Leon (Común): $" + ticketek.costoEntrada("El Rey León", "29/07/25", "Platea Común"));

        System.out.println("\nPrecio de entrada para Coldplay: $" + entradasAlcides.getFirst().precio());
        System.out.println("Precio de funcion para Coldplay: $" + ticketek.costoEntrada("Coldplay en vivo", "31/07/25"));

     	
		// 8) Anular una entrada
        
        ticketek.anularEntrada(entradasAlcides.getLast(), "1234");
        
        
		// 9) Cambiar una entrada
		
        // Cambio la fecha de una entrada para estadio
        ticketek.cambiarEntrada(entradasAlcides.getFirst(), "1234","01/08/25");

        // Cambio la fecha de una entrada para teatro
        ticketek.cambiarEntrada(entradasConejo.getLast(), "1234","31/08/25", "Platea VIP", 30);


		// 12) Total recaudado por espectaculo
        double totalRecaudado = ticketek.totalRecaudado("El Rey León");
        System.out.println("\nTotal Recaudado por El Rey León: $" + totalRecaudado);

		
		// 13) Total recaudado en espectaculo por sede.
        double totalRecaudadoSede = ticketek.totalRecaudadoPorSede("El Rey León", "Teatro Gran Rex");
        System.out.println("Total Recaudado por El Rey León en Gran Rex: $" + totalRecaudadoSede);

		
		// 15) Listar entradas vendidas de un espectaculo
        List<IEntrada> entradasReyLeon = ticketek
        		.listarEntradasEspectaculo("El Rey León");
        System.out.println("\nTodas las entradas para El Rey León:");
        for (IEntrada e: entradasReyLeon)
        	System.out.println(" - " + e);
        
		printEmpresa(ticketek, "Fin de la simulación");
	}

	private static void printEmpresa(ITicketek ticketek, String msg) {
		System.out.println("\n==========================================");
		System.out.println("========= " + msg);
		System.out.println("==========================================\n");
		System.out.println(ticketek);
		System.out.println("\n==========================================\n");
	}
}
