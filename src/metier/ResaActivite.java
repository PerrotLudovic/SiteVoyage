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


public ResaActivite(int idActivite) {
	super();
	
	this.idActivite = idActivite;
}


public int getIdReservation() {
	return idReservation;
}


public void setIdReservation(int idReservation) {
	this.idReservation = idReservation;
}


public int getId() {
	return id;
}


public void setId(int id) {
	this.id = id;
}


public int getIdActivite() {
	return idActivite;
}


public void setIdActivite(int idActivite) {
	this.idActivite = idActivite;
}


@Override
public String toString() {
	return "ResaActivite [id=" + id + ", idReservation=" + idReservation + ", idActivite=" + idActivite + "]";
}



}
