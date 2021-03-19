package test;

import metier.Activite;
import metier.Admin;
import metier.Client;
import util.Context;

import metier.Voyage;
import util.Context;
import metier.Pays;


public class Test {

	public static void main(String[] args) {
		
		Pays p1 = new Pays("France", 35, 0);
		Pays p2 = new Pays("Russie", 25, 0);
		Pays p3 = new Pays("Tenerife", 15, 0);
		Pays p4 = new Pays("Norvege", 45, 0);
		Pays p5 = new Pays("Maroc", 40, 0);
		Pays p6 = new Pays("Japon", 45, 0);
		
		p1=Context.getInstance().getDaoPays().save(p1);
		p2=Context.getInstance().getDaoPays().save(p2);
		p3=Context.getInstance().getDaoPays().save(p3);
		p4=Context.getInstance().getDaoPays().save(p4);
		p5=Context.getInstance().getDaoPays().save(p5);
		
		System.out.println(Context.getInstance().getDaoPays().findById(5));
		Voyage voyage1 = new Voyage("2021-07-15","2021-07-30",	Context.getInstance().getDaoPays().findById(5),	Context.getInstance().getDaoPays().findById(1));
		Voyage voyage2 = new Voyage("2021-07-15","2021-07-16", Context.getInstance().getDaoPays().findById(4),	Context.getInstance().getDaoPays().findById(1));
		Voyage voyage3 = new Voyage("2021-06-30","2021-06-16", Context.getInstance().getDaoPays().findById(4),	Context.getInstance().getDaoPays().findById(1));
		
		
		voyage1=Context.getInstance().getDaoVoyage().save(voyage1);
		voyage2=Context.getInstance().getDaoVoyage().save(voyage2);
		
		Activite activite0 = new Activite("Pas d'activité",0,Context.getInstance().getDaoPays().findById(1));
		activite0=Context.getInstance().getDaoActivite().save(activite0);

		Activite activite1 = new Activite("Balade en Dromadaire",2,Context.getInstance().getDaoPays().findById(5));
		
		activite1=Context.getInstance().getDaoActivite().save(activite1);

	


		Activite activite2 = new Activite("Atelier sushi",2,Context.getInstance().getDaoPays().findById(6));

		activite2=Context.getInstance().getDaoActivite().save(activite2);

		


		Activite activite3 = new Activite("Kayak dans les Fjords",5,Context.getInstance().getDaoPays().findById(4));

		activite3=Context.getInstance().getDaoActivite().save(activite3);

	

		Activite activite4 = new Activite("Visite d'un elevage de saumon",3,Context.getInstance().getDaoPays().findById(4));

		activite4=Context.getInstance().getDaoActivite().save(activite4);

		


		Admin admin = new Admin(1,"admin","admin","admin@gmail.fr","admin");

		admin=Context.getInstance().getDaoAdmin().save(admin);

	

		Client client = new Client(2,"dupont","dupont","dupont@gmail.com","dup");

		client=Context.getInstance().getDaoClient().save(client);

		Client client2 = new Client(3,"jacques","moris","jm@gmail.com","jm");

		client2=Context.getInstance().getDaoClient().save(client2);
		



        Context.getInstance().closeEmf();
		
		

	}

}
