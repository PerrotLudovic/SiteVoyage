package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOActivite;
import metier.Activite;
import util.Context;

public class DAOActiviteJPA implements IDAOActivite{

	@Override
	public Activite findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Activite activite = em.find(Activite.class, id);
		em.close();
		return activite;
	}

	@Override
	public List<Activite> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery=em.createQuery("SELECT a from Activite a",Activite.class);
		
		return myQuery.getResultList();
	
	}

	@Override
	public Activite save(Activite activite) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();

		activite=em.merge(activite);

		em.getTransaction().commit();
		em.close();
		return activite;
	}

	@Override
	public void delete(Activite activite) {

		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();

		activite=em.merge(activite);
		em.remove(activite);

		em.getTransaction().commit();
		em.close();

	}

	@Override
	public void insert(Activite d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Activite d) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Activite> findByIdPays(int id) {

	{ 
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT a from Activite a join fetch a.IdPays ",Activite.class);
		 return myQuery.getResultList();
	
	}


}}




