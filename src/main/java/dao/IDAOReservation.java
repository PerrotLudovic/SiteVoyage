package dao;

import java.util.List;

import metier.Reservation;

public interface IDAOReservation extends IDAO<Reservation,Integer> {

	List<Reservation> findByCompte(int id);

}
