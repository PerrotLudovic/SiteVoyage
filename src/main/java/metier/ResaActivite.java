package metier;

public class ResaActivite {

private int id;
private int idReservation;
private int idActivite;

public ResaActivite(int idReservation, int idActivite) {
	super();
	this.idReservation = idReservation;
	this.idActivite = idActivite;
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
