package dao;

import java.util.List;


import metier.Voyageur;

public interface IDAOVoyageur extends IDAO<Voyageur,Integer> {

	public List<Voyageur> findByReservation(int idReservation);

}
