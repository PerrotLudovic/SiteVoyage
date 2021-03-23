package dao;

import java.util.List;

import metier.Activite;
import metier.Compte;
import metier.Pays;
import metier.Reservation;
import metier.Voyageur;

public interface IDAOActivite extends IDAO<Activite,Integer> {

	
	public List<Activite> findByIdPays(Pays pays);
	public List<Activite> findByReservation(Reservation reservation);

}
