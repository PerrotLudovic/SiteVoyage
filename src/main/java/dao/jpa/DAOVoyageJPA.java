package dao.jpa;

import java.util.List;

//test
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOVoyage;
import metier.Compte;
import metier.Pays;
import metier.Reservation;
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
	
	/*@Override
	public List<Voyage> findBydate(String debut) 
	{ 
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		
		Query query = em.createQuery("from Voyage v where v.debut > LocalDate.now()", Produit.class); 
		query.setParameter("lib", v.getDebut");
		//getSingleResult si un seul element
	return  query.getResultList(); */

	@Override
	public List<Voyage> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from Voyage c WHERE c.debut > NOW()",Voyage.class);
		return (List<Voyage>) myQuery.getResultList();
		
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
	
	@Override
	
	public List<Voyage> voyageWithPays(Pays destination) {
			EntityManager em = Context.getInstance().getEmf().createEntityManager();
			Query myQuery=em.createQuery("SELECT v from Voyage v WHERE v.destination.id=:id AND v.debut > NOW()",Voyage.class);
			myQuery.setParameter("id",destination.getId());
			return (List<Voyage>) myQuery.getResultList();
			
		}
}
	

