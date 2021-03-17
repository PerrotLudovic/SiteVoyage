package metier;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("client")
public class Client extends Compte{
	
	public Client(int id, String nom, String prenom, String email, String password) {
		super(id, nom, prenom, email, password);
	}

	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	
}