package metier;

public class Compte {
	protected int id;
	protected String nom;
	protected String prenom;
	protected String email;
	protected String password;
	protected String typeCompte;

	
	public Compte() {
	}

	public Compte(int id, String nom, String prenom, String email, String password) {
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.password = password;
		this.prenom=prenom;
		
	}

	public Compte(String nom, String prenom, String email, String password, String typeCompte) {
		this.nom = nom;
		this.prenom=prenom;
		this.email = email;
		this.password = password;
		this.typeCompte=typeCompte;
	}
	
	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Compte [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + "]";
	}


	
	
	

	

}
