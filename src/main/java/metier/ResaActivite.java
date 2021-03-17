package metier;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ResaActivite {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	private int idReservation;
	private int idActivite;

public ResaActivite(int idReservation, int idActivite) {
	super();
	this.idReservation = idReservation;
	this.idActivite = idActivite;
}


public ResaActivite() {
}


public ResaActivite(int id, int idReservation, int idActivite) {
	super();
	this.id=id;
	this.idReservation = idReservation;
	this.idActivite = idActivite;
}


public int getIdReservation() {
	return idReservation;
}


public void setIdReservation(int idReservation) {
	this.idReservation = idReservation;
}


public int getIdActivite() {
	return idActivite;
}


public void setIdActivite(int idActivite) {
	this.idActivite = idActivite;
}



public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}

}
