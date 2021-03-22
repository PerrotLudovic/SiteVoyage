package dao.jpa;

import java.util.List;

//Test
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOVoyageur;
import metier.Voyageur;
import util.Context;

public class DAOVoyageurJPA implements IDAOVoyageur {

	@Override
	public Voyageur findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Voyageur voyageur = em.find(Voyageur.class, id);
		em.close();
		return voyageur;
	}

	@Override
	public List<Voyageur> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from Voyageur c",Voyageur.class);
		em.close();
		return myQuery.getResultList(); 
	}

	@Override
	public Voyageur save(Voyageur voyageur) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();		
		voyageur=em.merge(voyageur);
		
		em.getTransaction().commit();
		em.close();
		return voyageur;
	}

	@Override
	public void delete(Voyageur voyageur) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		voyageur=em.merge(voyageur);
		em.remove(voyageur);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void insert(Voyageur d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Voyageur d) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public List<Voyageur> findByReservation(int idReservation) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery=em.createQuery("SELECT voy from Voyageur voy WHERE voy.idReservation=:id",Voyageur.class);
		myQuery.setParameter("id",idReservation);
		return (List<Voyageur>) myQuery.getResultList();
		

}
	
	
		
	}
	
	
	

