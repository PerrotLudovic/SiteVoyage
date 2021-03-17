package metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Pays {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private int restriction;
	private int prixJours;
	
	public Pays() {
	}

	public Pays(int id, String nom, int restriction, int prixJours) {
		this.id = id;
		this.nom = nom;
		this.restriction = restriction;
		this.prixJours=prixJours;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int isRestriction() {
		return restriction;
	}

	public void setRestriction(int restriction) {
		this.restriction = restriction;
	}

	public int getPrixJours() {
		return prixJours;
	}

	public void setPrixJours(int prixJours) {
		this.prixJours = prixJours;
	}
	
	@Override
	public String toString() {
		return "Pays [id=" + id + ", nom=" + nom + ", restriction=" + restriction + ", prixJours=" + prixJours + "]";
	}

}
