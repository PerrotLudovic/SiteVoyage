package metier;

import java.time.LocalDate;

public class Voyage {
	private int id;
	private LocalDate debut;
	private LocalDate fin;
	private Pays destination;
	private Pays depart;
	private double prixVoyage;
	
	public Voyage() {
	}

	
	
	public Voyage(int id, String debut, String fin, Pays destination, Pays depart) {
		this.id = id;
		this.debut = LocalDate.parse(debut);
		this.fin = LocalDate.parse(fin);
		this.destination = destination;
		this.depart = depart;
		this.prixVoyage = destination.getPrixJours()*(this.fin.compareTo(this.debut));
	}

	public Voyage(String debut, String fin, Pays destination, Pays depart) {
		
		this.debut = LocalDate.parse(debut);
		this.fin = LocalDate.parse(fin);
		this.destination = destination;
		this.depart = depart;
		this.prixVoyage = destination.getPrixJours()*(this.fin.compareTo(this.debut));
	}


	



	public LocalDate getFin() {
		return fin;
	}



	public void setFin(LocalDate fin) {
		this.fin = fin;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDebut() {
		return debut;
	}

	public void setDebut(LocalDate debut) {
		this.debut = debut;
	}


	public Pays getDestination() {
		return destination;
	}



	public void setDestination(Pays destination) {
		this.destination = destination;
	}



	public Pays getDepart() {
		return depart;
	}



	public void setDepart(Pays depart) {
		this.depart = depart;
	}



	public double getPrixVoyage() {
		return prixVoyage;
	}


	public void setPrixVoyage(double prixVoyage) {
		this.prixVoyage = prixVoyage;
	}


	@Override
	public String toString() {
		return "Voyage [id=" + id +", debut="+ debut +", destination=" + destination + ", d?part=" + depart +"]";
	}
	
	
}
