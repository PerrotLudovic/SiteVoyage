package metier;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@OneToOne
	private Voyage voyage;
	@ManyToOne
	private Compte compte;
	private LocalDate date;
	private double prix;
	
	@Enumerated(EnumType.STRING)
	private Transport transport;
	@ManyToMany 
	private List<Activite> activites;
	
	

	public Reservation() {
	}



	public Reservation(Double Prix, Voyage v, Transport transport) {
		this.voyage= v;
		this.transport = transport;
		this.date = LocalDate.now();
		this.prix = v.getPrixVoyage()+transport.getPrix();
		
		
	}


	public Reservation(Compte idCompte, Voyage idVoyage, Double prix,Transport transport) {
		this.compte=idCompte;
		this.voyage = idVoyage;
		this.date = LocalDate.now();
		this.transport=transport;
		
		this.prix = prix;
	}


	public Voyage getVoyage() {
		return voyage;
	}

	public void setVoyage(Voyage idVoyage) {
		this.voyage = idVoyage;
	}


	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}



	public double prix() {
        return prix=voyage.getPrixVoyage()+transport.getPrix();
      
    }



	public Transport getTransport() {
		return transport;
	}


	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Activite> getActivites() {
		return activites;
	}


	public void setActivites(List<Activite> activites) {
		this.activites = activites;
	}



	public Compte getCompte() {
		return compte;
	}



	public void setCompte(Compte compte) {
		this.compte = compte;
	}



	@Override
	public String toString() {
		return "Reservation [voyage=" + voyage + ", compte=" + compte + ", date=" + date + ", prix=" + prix + ", id="
				+ id + ", transport=" + transport + ", activites=" + activites + "]";
	}




	
	


	

	
	

	


	

	


}
