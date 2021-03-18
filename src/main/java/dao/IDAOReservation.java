package dao;

import java.util.List;

import metier.Compte;
import metier.Reservation;

public interface IDAOReservation extends IDAO<Reservation,Integer> {

	public List<Reservation> findByCompte(Compte idCompte);

}
