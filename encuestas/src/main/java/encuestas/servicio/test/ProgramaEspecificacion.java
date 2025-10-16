package encuestas.servicio.test;

import java.util.List;

import encuestas.modelo.Encuesta;
import encuestas.reglas.ReglasEncuesta;
import encuestas.repositorio.RepositorioEncuestasAdHoc;
import especificacion.Especificacion;
import repositorio.FactoriaRepositorios;

public class ProgramaEspecificacion {

	public static void main(String[] args) throws Exception {
		
		RepositorioEncuestasAdHoc repositorioConcreto = FactoriaRepositorios.getRepositorio(Encuesta.class);
		/*
		Especificacion<Encuesta> spec = new Especificacion<Encuesta>(ReglasEncuesta.opcionesSuficientes()).and(ReglasEncuesta.sinVotos());
		
		List<Encuesta> encuestas = repositorioConcreto.getByEspecificacion(spec);
		for(Encuesta e:encuestas) {
			System.out.println(e);
		}
		*/
		
		List<Encuesta> encuestas =repositorioConcreto.getBySinVotos();
		for(Encuesta e:encuestas) {
			System.out.println(e);
		}
		
		encuestas =repositorioConcreto.getByNumeroOpcionesMayorQue(2);
		for(Encuesta e:encuestas) {
			System.out.println(e);
		}
		
		List<Object[]> resultado = repositorioConcreto.getOpcionesPorVotos();
		for(Object[] o:resultado) {
			System.out.println("En la encusta "+o[0]+":"+o[1]+" gana "+o[2]+":"+o[3]+" por "+o[4]+" votos");
		}
		
	}
}