package metier;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Activite {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	private String libelle;
	
	private int duree;
	
	@ManyToOne
	private Pays pays;
	
	@ManyToMany(mappedBy="activites")
	private List<Reservation>reservations;
	
	public Activite() {
	}

	public Activite(int id, String libelle, int duree, Pays pays) {
		this.id = id;
		this.libelle = libelle;
		this.duree = duree;
		this.pays = pays;
	}
	
	public Activite(String libelle, int duree, Pays pays) {
		this.libelle = libelle;
		this.duree = duree;
		this.pays = pays;
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

	public Pays getPays() {
		return pays;
	}

	public void setPays(Pays pays) {
		this.pays = pays;
	}

	
	
	@Override
	public String toString() {
		return "Activite [id=" + id + ", libelle=" + libelle + ", duree=" + duree +" heures"+ "]";
	}

}
