package util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import dao.IDAOActivite;
import dao.IDAOAdmin;
import dao.IDAOClient;
import dao.IDAOCompte;
import dao.IDAOPays;
import dao.IDAOResaActivite;
import dao.IDAOReservation;
import dao.IDAOVoyage;
import dao.IDAOVoyageur;
import dao.jpa.DAOActiviteJPA;
import dao.jpa.DAOAdminJPA;
import dao.jpa.DAOClientJPA;
import dao.jpa.DAOCompteJPA;
import dao.jpa.DAOPaysJPA;
import dao.jpa.DAOResaActiviteJPA;
import dao.jpa.DAOReservationJPA;
import dao.jpa.DAOVoyageJPA;
import dao.jpa.DAOVoyageurJPA;
import metier.Compte;

public class Context {

	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("voyage");
	private static Context _instance=null;
	private Compte connected=null;
	
	private IDAOActivite daoActivite=new DAOActiviteJPA();
	private IDAOAdmin daoAdmin=new DAOAdminJPA();
	private IDAOClient daoClient=new DAOClientJPA();
	private IDAOCompte daoCompte=new DAOCompteJPA();
	private IDAOPays daoPays=new DAOPaysJPA();
	private IDAOResaActivite daoResaActivite=new DAOResaActiviteJPA();
	private IDAOReservation daoReservation=new DAOReservationJPA();
	private IDAOVoyage daoVoyage=new DAOVoyageJPA();
	private IDAOVoyageur daoVoyageur = new DAOVoyageurJPA();
	
	private Context() 
	{	}
	
	public EntityManagerFactory getEmf() {
		return emf;
	}


	public void closeEmf() 
	{
		emf.close();
	}

	public static void set_instance(Context _instance) {
		Context._instance = _instance;
	}


	public IDAOActivite getDaoActivite() {
		return daoActivite;
	}


	public void setDaoActivite(IDAOActivite daoActivite) {
		this.daoActivite = daoActivite;
	}


	public IDAOAdmin getDaoAdmin() {
		return daoAdmin;
	}


	public void setDaoAdmin(IDAOAdmin daoAdmin) {
		this.daoAdmin = daoAdmin;
	}


	public IDAOClient getDaoClient() {
		return daoClient;
	}


	public void setDaoClient(IDAOClient daoClient) {
		this.daoClient = daoClient;
	}


	public IDAOCompte getDaoCompte() {
		return daoCompte;
	}


	public void setDaoCompte(IDAOCompte daoCompte) {
		this.daoCompte = daoCompte;
	}


	public IDAOPays getDaoPays() {
		return daoPays;
	}


	public void setDaoPays(IDAOPays daoPays) {
		this.daoPays = daoPays;
	}


	public IDAOResaActivite getDaoResaActivite() {
		return daoResaActivite;
	}


	public void setDaoResaActivite(IDAOResaActivite daoResaActivite) {
		this.daoResaActivite = daoResaActivite;
	}


	public IDAOReservation getDaoReservation() {
		return daoReservation;
	}


	public void setDaoReservation(IDAOReservation daoReservation) {
		this.daoReservation = daoReservation;
	}


	public IDAOVoyage getDaoVoyage() {
		return daoVoyage;
	}


	public void setDaoVoyage(IDAOVoyage daoVoyage) {
		this.daoVoyage = daoVoyage;
	}


	public IDAOVoyageur getDaoVoyageur() {
		return daoVoyageur;
	}


	public void setDaoVoyageur(IDAOVoyageur daoVoyageur) {
		this.daoVoyageur = daoVoyageur;
	}


	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}


	public static Context getInstance() 
	{
		if(_instance==null) {_instance=new Context();}
		return _instance;
	}
	
	public Compte getConnected() {
		return connected;
	}


	public void setConnected(Compte connected) {
		this.connected = connected;
	}




	
	
	
}
