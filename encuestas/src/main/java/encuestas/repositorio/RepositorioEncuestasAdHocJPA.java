package encuestas.repositorio;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

import encuestas.modelo.Encuesta;
import repositorio.EntidadNoEncontrada;
import repositorio.RepositorioException;
import utils.EntityManagerHelper;

public class RepositorioEncuestasAdHocJPA extends RepositorioEncuestasJPA implements RepositorioEncuestasAdHoc {

	private List<Encuesta> getByIds(List<String> identifiers) throws RepositorioException {

		try {

			EntityManager em = EntityManagerHelper.getEntityManager();
			Query query = em.createNamedQuery("Encuesta.getByIds");
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);
			query.setParameter("ids", identifiers);
			return query.getResultList();

		} catch (RuntimeException e) {
			throw new RepositorioException("Error buscando todas las entidades por id", e);
		}
		finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<Encuesta> getBySinVotos() throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			String queryString = "SELECT e "
					+ "FROM Encuesta e "
					+ "WHERE e.cierre > CURRENT_DATE "
					+ "AND e.id NOT IN (SELECT e.id "
					+ "                 FROM Encuesta e "
					+ "                 INNER JOIN e.opciones o "
					+ "                 INNER JOIN o.votos v)";

			TypedQuery<Encuesta> query = em.createQuery(queryString, Encuesta.class);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);

			return query.getResultList();

		} catch (RuntimeException e) {
			throw new RepositorioException("Error buscando by sin votos", e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<Encuesta> getByNumeroOpcionesMayorQue(int numero) throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			String queryString = "SELECT e " 
			+ "	FROM Encuesta e "
			+ " WHERE SIZE(e.opciones) >= :num " ;
			;

			TypedQuery<Encuesta> query = em.createQuery(queryString, Encuesta.class);
			query.setParameter("num", numero);
			query.setHint(QueryHints.REFRESH, HintValues.TRUE);

			return query.getResultList();

		} catch (RuntimeException e) {
			throw new RepositorioException("Error buscando by sin votos", e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<Encuesta> getByVotante(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getOpcionesPorVotos() throws RepositorioException {
		try {
			EntityManager em = EntityManagerHelper.getEntityManager();

			String queryString = "SELECT e.ID ,e.TITULO , o.ID , o.TEXTO , COUNT(ov.VOTOS) AS total_votos "
					+ "FROM encuesta e "
					+ "INNER JOIN opcion o ON o.encuesta_fk = e.ID "
					+ "LEFT JOIN opcion_votos ov ON ov.Opcion_ID = o.ID "
					+ "GROUP BY e.ID, e.TITULO, o.ID, o.TEXTO "
					+ "HAVING COUNT(ov.VOTOS) = ( "
					+ "    SELECT MAX(sub.total_votos) "
					+ "    FROM (\r\n"
					+ "        SELECT o2.ID, COUNT(ov2.VOTOS) AS total_votos "
					+ "        FROM opcion o2 "
					+ "        LEFT JOIN opcion_votos ov2 ON ov2.Opcion_ID = o2.ID "
					+ "        WHERE o2.encuesta_fk = e.ID "
					+ "        GROUP BY o2.ID) sub "
					+ ")" ;
			;

			Query query = em.createNativeQuery(queryString);

			return query.getResultList();

		} catch (RuntimeException e) {
			throw new RepositorioException("Error buscando by sin votos", e);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}

	@Override
	public List<Encuesta> getByActivas() throws RepositorioException {
		// TODO Auto-generated method stub
		return RepositorioEncuestasAdHoc.super.getByActivas();
	}

}
