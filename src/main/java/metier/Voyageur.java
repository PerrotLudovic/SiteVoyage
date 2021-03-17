package metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Voyageur {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int idReservation;
	private String nom;
	private String prenom;
	
	public Voyageur() {
	}
	
	public Voyageur( String nom, String prenom, int idReservation) {
		this.nom = nom;
		this.prenom = prenom;
		this.idReservation = idReservation;
	}
	

	


	public Voyageur(int id, int idReservation, String nom, String prenom) {
		this.id = id;
		this.idReservation = idReservation;
		this.nom = nom;
		this.prenom = prenom;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	
	
	

}
