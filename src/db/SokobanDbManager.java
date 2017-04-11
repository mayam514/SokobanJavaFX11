package db;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import commons.Score;

public class SokobanDbManager {
	//Data member
	private static SessionFactory sessionFactory;

	//Constructor
	public SokobanDbManager() {
		createSessionFactory();
	}

	public static void createSessionFactory() {
	    Configuration configuration = new Configuration();
	    configuration.configure();

	    ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
	            configuration.getProperties()). buildServiceRegistry();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}


	/**
	 * The method adds a new player to the database
	 * @param p the player that we want to add to the database
	 */
	public void addScore(Score s) {
		Session session = null;
		Transaction tx = null;

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			session.save(s);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	public List<Score> addQuery(String parameter, String parameterValue, String parameterToOrderBy ,boolean descOrder){

		Session session = null;
		Transaction tx = null;
		List<Score> list = null;
		String queryString = "from Scores where "+ parameter + " = '" + parameterValue + "' order by " + parameterToOrderBy;
		if(descOrder){
			queryString += " desc";
		}

		try {
			session = sessionFactory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(queryString);
			list = query.list();

			System.out.println("***************");
			for(Object o : list){
				System.out.println(o.toString());
			}

			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
		return list;
	}

}