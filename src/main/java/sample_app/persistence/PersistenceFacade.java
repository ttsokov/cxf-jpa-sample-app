package sample_app.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public class PersistenceFacade {

	private static EntityManagerFactory emf;

	public static EntityManager getEntityManager() {
		if (emf == null) {
			initEmf();
		}

		return emf.createEntityManager();
	}

	@SuppressWarnings("unchecked")
	private static void initEmf() {
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB");

			Map properties = new HashMap();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, ds);
			emf = Persistence.createEntityManagerFactory("sample_app.backend", properties);
		} catch (NamingException e) {
			e.printStackTrace();
			throw new RuntimeException("Cannot initialize entity manager factory.");
		}
	}
}
