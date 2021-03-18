package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOCompte;
import metier.Compte;
import util.Context;

public class DAOCompteJPA implements IDAOCompte {

	@Override
	public Compte findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Compte compte = em.find(Compte.class, id);
		em.close();
		return compte;
	}

	@Override
	public List<Compte> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from Compte c",Compte.class);
		 return myQuery.getResultList(); 
	}

	@Override
	public Compte save(Compte compte) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();		
		compte=em.merge(compte);
		
		em.getTransaction().commit();
		em.close();
		return compte;
	}

	@Override
	public void delete(Compte compte) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		compte=em.merge(compte);
		em.remove(compte);
		
		em.getTransaction().commit();
		em.close();
		
	}
	


	@Override
	public void insert(Compte d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Compte d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Compte checkConnect(String nom, String password) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery=em.createQuery("SELECT c from Compte c WHERE c.nom=:nom AND c.password=:pass",Compte.class);
		myQuery.setParameter("nom",nom);
		myQuery.setParameter("pass",password);
		return (Compte) myQuery.getSingleResult();
		
	}

	
	


}
