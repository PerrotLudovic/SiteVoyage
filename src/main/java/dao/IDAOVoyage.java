package dao;

import java.util.List;

import metier.Pays;
import metier.Voyage;

public interface IDAOVoyage extends IDAO<Voyage,Integer> {

	public List<Voyage> voyageWithPays(Pays pays);
	
}