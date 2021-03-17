package dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import dao.IDAOAdmin;
import metier.Admin;
import util.Context;

public class DAOAdminJPA implements IDAOAdmin {
	
	@Override
	public Admin findById(Integer id) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Admin admin = em.find(Admin.class, id);
		em.close();
		return admin;
	}

	@Override
	public List<Admin> findAll() {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		Query myQuery=em.createQuery("SELECT a from Admin a",Admin.class);
		
		return null;
	
	}

	@Override
	public Admin save(Admin admin) {
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		admin=em.merge(admin);
		
		em.getTransaction().commit();
		em.close();
		return admin;
	}

	@Override
	public void delete(Admin admin) {
		
		EntityManager em = Context.getInstance().getEmf().createEntityManager();
		em.getTransaction().begin();
		
		admin=em.merge(admin);
		em.remove(admin);
		
		em.getTransaction().commit();
		em.close();
		
	}

	@Override
	public void insert(Admin d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Admin d) {
		// TODO Auto-generated method stub
		
	}

}
