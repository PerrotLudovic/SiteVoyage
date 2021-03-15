package metier;

public class Pays {
	
	

	private int id;
	private String nom;
	private boolean restriction;
	private int prixJours;
	
	public Pays() {
	}

	public Pays(int id, String nom, boolean restriction, int prixJours) {
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

	public boolean isRestriction() {
		return restriction;
	}

	public void setRestriction(boolean restriction) {
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
