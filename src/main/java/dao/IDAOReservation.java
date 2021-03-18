package dao;

import java.util.List;

import metier.Compte;
import metier.Reservation;

public interface IDAOReservation extends IDAO<Reservation,Integer> {

	List<Reservation> findByCompte(Compte id);

}
