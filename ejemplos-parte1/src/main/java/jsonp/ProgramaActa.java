package jsonp;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonPointer;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class ProgramaActa {

	public static void main(String[] args) throws IOException {
		JsonReader reader = Json.createReader(new FileReader("json/acta.json"));
		
			
		JsonObject root = reader.readObject();
		
		// Calcular media de las calificaciones
		JsonArray calificaciones = root.getJsonArray("calificaciones");
		double suma = 0d;
		for(JsonValue c:calificaciones) {
			double valor = c.asJsonObject().getJsonNumber("nota").doubleValue();
			suma+=valor;
		}
		double media = suma / calificaciones.size();
		System.out.println("media "+media);
		
		// Contar diligiencias en el mes de septiembre
		
		JsonArray diligencias = root.getJsonArray("diligencias");
		int count=0;
		for(JsonValue d:diligencias) {
			JsonObject dig =d.asJsonObject();
			String fechaStr = dig.getString("fecha");
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");

	        LocalDate fecha = LocalDate.parse(fechaStr, formatter);
	        if(fecha.getMonth()== Month.SEPTEMBER) {
	        	++count;
	        }
		}
		System.out.println("Nº de diligencias "+count);
		
		// JSON-P Pointer
		
		// Recupera la convocatoria del acta
		
		JsonPointer pointer = Json.createPointer("/convocatoria");
		String convocatoria = pointer.getValue(root).toString();
		System.out.println("convocatoria "+convocatoria);
		
		// recupera la fecha de la primera diligencia
		
		pointer = Json.createPointer("/diligencias/0/fecha");
		System.out.println("fecha "+pointer.getValue(root));
		
		pointer = Json.createPointer("/calificaciones/-");
		
		JsonObject calificacion = Json.createObjectBuilder().add("nif", "12345678Z")
				.add("nota", 7).add("nombre", "María").build();
				
		root = pointer.add(root, calificacion);
		
		// Almacenamiento en disco
	     
	    HashMap<String, Boolean> config = new HashMap<String, Boolean>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonGeneratorFactory factoriaGeneradores = Json.createGeneratorFactory(config);
	    
        JsonGenerator generador = factoriaGeneradores.createGenerator(
        		new FileWriter("json/acta-modificada.json"));
        
        generador.write(root);
        generador.close();

	    System.out.println("fin.");
	
	}

}
