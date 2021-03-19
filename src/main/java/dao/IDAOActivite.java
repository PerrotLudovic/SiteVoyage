package dao;

import java.util.List;

import metier.Activite;
import metier.Pays;
import metier.Reservation;

public interface IDAOActivite extends IDAO<Activite,Integer> {

	public List<Activite> findByIdPays(Pays pays);

}
