package ar.edu.ungs.prog2.ticketek;

import java.util.UUID;

public class Codigo {
	public static String generar() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}

//https://stackoverflow.com/questions/3804591/eficient-method-to-generate-uuid-string-in-java-uuid-randomuuid-tostring-w
//https://www.uuidgenerator.net/dev-corner/java