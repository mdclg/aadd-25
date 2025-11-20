package jsonp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class ProgramaCrearActa {
	
	private static JsonObject crearCalificacion(String nif, double nota, String nombre) {
		JsonObjectBuilder builder = Json.createObjectBuilder().add("nif", nif)
		.add("nota", nota);
		if(nombre != null) {
			builder.add("nombre", nombre);
			
		}
		return builder.build();
	}
	
	private static JsonObject crearDiligencia(String nif, double nota, String nombre, boolean extraordinaria, String fecha) {
		JsonObjectBuilder builder = Json.createObjectBuilder().add("fecha", fecha)
				.add("extraordinaria", extraordinaria)
				.add("nif", nif)
		.add("nota", nota);
		if(nombre != null) {
			builder.add("nombre", nombre);
			
		}
		return builder.build();
	}
	
	public static void main(String[] args) throws IOException {
		
		JsonObject c1 = crearCalificacion("12345678Z",8.5,null);
		JsonObject c2 = crearCalificacion("33333333P",9,"Juan");
		JsonObject d1 = crearDiligencia("12345678Z", 9, null, false, "2025-11-19");
		
		JsonArray calificaciones = Json.createArrayBuilder().add(c1).add(c2).build();
		JsonArray diligencias = Json.createArrayBuilder().add(d1).build();
		
		JsonObject acta = Json.createObjectBuilder().add("convocatoria", "febrero")
		.add("curso", "2025")
		.add("asingatura", "1234")
		.add("calificaciones", calificaciones)
		.add("diligencias", diligencias).build();
		
		// Almacenamiento en disco
	     
	    HashMap<String, Boolean> config = new HashMap<String, Boolean>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);
	    
        JsonGenerator generador = factoriaGeneradores.createGenerator(
        		new FileWriter("json/acta-creada.json"));
        
        generador.write(acta);
        generador.close();

	    System.out.println("fin.");
		
	}

}
