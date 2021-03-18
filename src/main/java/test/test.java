package test;

import metier.Activite;
import metier.Admin;
import metier.Client;
import util.Context;

public class test {

	public static void main(String[] args) {

		Activite activite1 = new Activite("Balade en Dromadaire",2,Context.getInstance().getDaoPays().findById(5));
		
		activite1=Context.getInstance().getDaoActivite().save(activite1);

		Context.getInstance().closeEmf();


		Activite activite2 = new Activite("Atelier shushi",2,Context.getInstance().getDaoPays().findById(6));

		activite2=Context.getInstance().getDaoActivite().save(activite2);

		Context.getInstance().closeEmf();


		Activite activite3 = new Activite("Kayak dans les Fjords",5,Context.getInstance().getDaoPays().findById(4));

		activite3=Context.getInstance().getDaoActivite().save(activite3);

		Context.getInstance().closeEmf();


		Activite activite4 = new Activite("Visite d'un élevage de saumon",3,Context.getInstance().getDaoPays().findById(4));

		activite4=Context.getInstance().getDaoActivite().save(activite4);

		Context.getInstance().closeEmf();


		Admin admin = new Admin(1,"admin","admin","admin@gmail.fr","admin");

		admin=Context.getInstance().getDaoAdmin().save(admin);

		Context.getInstance().closeEmf();

		Client client = new Client(2,"dupont","dupont","dupont@gmail.com","dup");

		client=Context.getInstance().getDaoClient().save(client);

		Context.getInstance().closeEmf();



	}

}
