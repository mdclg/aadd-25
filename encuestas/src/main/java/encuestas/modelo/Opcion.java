package encuestas.modelo;

import java.util.LinkedList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // anotación JPA
public class Opcion {
	
	@Id // anotación JPA
	@GeneratedValue(strategy = GenerationType.TABLE) // anotación JPA
	private Integer id;

	private String texto;
	@ElementCollection // anotación JPA
	private LinkedList<String> votos = new LinkedList<>();
	
	public Opcion() { // POJO
		
	}
	
	public Opcion(String texto) {
		this.texto = texto;
	}
	
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LinkedList<String> getVotos() {
		return votos;
	}
	public void setVotos(LinkedList<String> votos) {
		this.votos = votos;
	}
	
	// Propiedad calculada
	
	public int getNumeroVotos() {		
		return this.votos.size();
	}
	
	@Override
	public String toString() {
		return "Opcion [texto=" + texto + ", votos=" + votos + "]";
	}
	
	
}
