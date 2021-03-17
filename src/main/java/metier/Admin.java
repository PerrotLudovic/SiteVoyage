package metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("admin")
public class Admin extends Compte{

	public Admin(int id, String nom, String prenom, String email, String password) {
		super(id, nom, prenom, email, password);
	}
	public Admin() {
		// TODO Auto-generated constructor stub
	}
}
