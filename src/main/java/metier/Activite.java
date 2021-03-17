package metier;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
//test
@Entity
public class Activite {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	
	private String libelle;
	
	
	private int duree;
	
	@ManyToOne
	private int idPays;
	@ManyToMany(mappedBy="activites")
	private List<Reservation>reservations;
	
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
