package jsonb;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbConfig;
import javax.json.bind.JsonbException;
import javax.json.bind.config.PropertyNamingStrategy;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.json.bind.spi.JsonbProvider;

import actas.modelo.Acta;
import actas.modelo.Calificacion;

public class ProgramaJsonBActa {

	public static void main(String[] args) throws JsonbException, FileNotFoundException {
		
		Jsonb contexto = JsonbProvider.provider().create().build();

		
		 Acta acta = contexto.fromJson(new FileReader("json/acta.json"), Acta.class);
		 
		 for(Calificacion c:acta.getCalificaciones()) {
			 if(c.getNota()>=9.5) {
				 c.setNota(10d);
			 }
			 else {
				 c.setNota(c.getNota()+0.5);
			 }
		 }
		 
		 JsonbConfig config = new JsonbConfig()
	                .withNullValues(false)
	                .withFormatting(true)
	                .withPropertyNamingStrategy(
						PropertyNamingStrategy.IDENTITY)
	                .withPropertyOrderStrategy(
						PropertyOrderStrategy.LEXICOGRAPHICAL); 
		 Jsonb contexto2 = JsonbProvider.provider().create().withConfig(config).build();
		 contexto2.toJson(acta ,new PrintStream("json/acta-jsonb.json"));
		 System.out.println("fin");		

	}

}
