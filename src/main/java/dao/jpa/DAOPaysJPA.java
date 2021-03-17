package dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOPays;
import metier.Pays;
import util.Context;

public class DAOPaysJPA implements IDAOPays {

	@Override
	public Pays findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Pays pays = em.find(Pays.class, id);
		em.close();
		return pays;
	}

	@Override
	public List<Pays> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from Pays c",Pays.class);
		 return myQuery.getResultList(); 
	}

	@Override
	public Pays save(Pays pays) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();		
		pays=em.merge(pays);
		
		em.getTransaction().commit();
		em.close();
		return pays;
	}

	@Override
	public void delete(Pays pays) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		pays=em.merge(pays);
		em.remove(pays);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	
	@Override
	public List<Pays> findAllWithAchat() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from Pays c join fetch c.produits ",Pays.class);
		 return myQuery.getResultList(); 
	}


	@Override
	public void insert(Pays d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Pays d) {
		// TODO Auto-generated method stub
		
	}

	
	


}
