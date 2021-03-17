package dao.jpa;

import java.util.List;
//test
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOVoyage;
import metier.Voyage;
import util.Context;

public class DAOVoyageJPA implements IDAOVoyage {

	@Override
	public Voyage findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Voyage voyage = em.find(Voyage.class, id);
		em.close();
		return voyage;
	}

	@Override
	public List<Voyage> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from Voyage c",Voyage.class);
		 return myQuery.getResultList(); 
	}

	@Override
	public Voyage save(Voyage voyage) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();		
		voyage=em.merge(voyage);
		
		em.getTransaction().commit();
		em.close();
		return voyage;
	}

	@Override
	public void delete(Voyage voyage) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		voyage=em.merge(voyage);
		em.remove(voyage);
		
		em.getTransaction().commit();
		em.close();
		
	}{

}

	@Override
	public void insert(Voyage d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Voyage d) {
		// TODO Auto-generated method stub
		
	}