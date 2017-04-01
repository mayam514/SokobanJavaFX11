package db;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import commons.Player;

public class SokobanDbManager {
	//Data member
	private SessionFactory factory;

	//Constructor
	public SokobanDbManager() {
		Logger.getLogger("org.hibernate").setLevel(Level.SEVERE); 
		Configuration config = new Configuration();
		config.configure();
		factory = config.buildSessionFactory();
	}

	/**
	 * The method adds a new player to the database
	 * @param p the player that we want to add to the database
	 */
	public void addPlayer(Player p) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(p);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}

	/**
	 * The method adds a new level to the database
	 * @param l the level that we want to add to the database
	 */
	public void addLevel(commons.Level l) {
		Session session = null;
		Transaction tx = null;

		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.save(l);
			tx.commit();
		} catch (HibernateException ex) {
			if (tx != null)
				tx.rollback();
		} finally {
			if (session != null)
				session.close();
		}
	}
	
	/**
	 * The method updates the player's details in the database
	 * @param p the player that we want to update
	 */
	public void updatePlayer(Player p) {
		//TODO
	}
	
	/**
	 * The method updates the level's details in the database
	 * @param l the level that we want to update
	 */
	public void updateLevel(commons.Level l) {
		//TODO

	}

	/**
	 * The method deletes a player from the database
	 * @param name the name of the player that we want to delete
	 */
	public void deletePlayer(String name) {
		//TODO

	}
	
	/**
	 * The method deletes a level from the database
	 * @param name the name of the level that we want to delete
	 */
	public void deleteLevel(String name) {
		//TODO

	}
}
