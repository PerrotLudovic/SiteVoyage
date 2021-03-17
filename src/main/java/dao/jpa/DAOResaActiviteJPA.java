package dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOResaActivite;
import metier.ResaActivite;
import util.Context;

public class DAOResaActiviteJPA implements IDAOResaActivite {

	@Override
	public ResaActivite findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		ResaActivite resaActivite = em.find(ResaActivite.class, id);
		em.close();
		return resaActivite;
	}

	@Override
	public List<ResaActivite> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from ResaActivite c",ResaActivite.class);
		 return myQuery.getResultList(); 
	}

	@Override
	public ResaActivite save(ResaActivite resaActivite) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();		
		resaActivite=em.merge(resaActivite);
		
		em.getTransaction().commit();
		em.close();
		return resaActivite;
	}

	@Override
	public void delete(ResaActivite resaActivite) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		resaActivite=em.merge(resaActivite);
		em.remove(resaActivite);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	
	@Override
	public List<ResaActivite> findAllWithAchat() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery = em.createQuery("SELECT c from ResaActivite c join fetch c.produits ",ResaActivite.class);
		 return myQuery.getResultList(); 
	}


	@Override
	public void insert(ResaActivite d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ResaActivite d) {
		// TODO Auto-generated method stub
		
	}

	
	


}
