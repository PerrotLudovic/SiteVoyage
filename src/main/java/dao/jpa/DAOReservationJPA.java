package dao.jpa;

import java.util.List;

//test
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOReservation;
import metier.Compte;
import metier.Reservation;
import util.Context;

public class DAOReservationJPA implements IDAOReservation {

	@Override
	public Reservation findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Reservation reservation = em.find(Reservation.class, id);
		em.close();
		return reservation;
	}

	@Override
	public List<Reservation> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from Reservation c WHERE c.debut > NOW()",Reservation.class); 
		return myQuery.getResultList(); 
	}

	@Override
	public Reservation save(Reservation reservation) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();		
		reservation=em.merge(reservation);
		em.getTransaction().commit();
		em.close();
		return reservation;
	}

	@Override
	public void delete(Reservation reservation) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		reservation=em.merge(reservation);
		em.remove(reservation);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void insert(Reservation d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Reservation d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Reservation> findByCompte(Compte compte) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery=em.createQuery("SELECT r from Reservation r WHERE r.compte.id=:id",Reservation.class);
		myQuery.setParameter("id",compte.getId());
		return (List<Reservation>) myQuery.getResultList();
		
	}
	
	

}
