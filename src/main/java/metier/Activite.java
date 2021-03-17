package metier;

import java.util.ArrayList;
import java.util.List;

public class Activite {
	
	

	private int id;
	private String libelle;
	private int duree;
	private int idPays;
	
	
	public Activite() {
	}

	public Activite(int id, String libelle, int duree, int idPays) {
		this.id = id;
		this.libelle = libelle;
		this.duree = duree;
		this.idPays = idPays;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public int getIdPays() {
		return idPays;
	}

	public void setIdPays(int idPays) {
		this.idPays = idPays;
	}

	
	
	@Override
	public String toString() {
		return "Activite [id=" + id + ", libelle=" + libelle + ", duree=" + duree +" heures"+ "]";
	}

}
