package dao;

import java.util.List;

import metier.Activite;

public interface IDAOActivite extends IDAO<Activite,Integer> {

	List<Activite> findByIdPays(int id);

}
