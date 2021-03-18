package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOClient;
import metier.Client;
import metier.Client;
import util.Context;

public class DAOClientJPA implements IDAOClient {
	
	@Override
	public Client findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Client client = em.find(Client.class, id);
		em.close();
		return client;
	}

	@Override
	public List<Client> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery=em.createQuery("SELECT a from Client a",Client.class);
		return myQuery.getResultList(); 
	
	}

	@Override
	public Client save(Client client) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		client=em.merge(client);
		
		em.getTransaction().commit();
		em.close();
		return client;
	}

	@Override
	public void delete(Client client) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		client=em.merge(client);
		em.remove(client);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void insert(Client d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Client d) {
		// TODO Auto-generated method stub
		
	}


}
