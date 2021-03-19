package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import metier.Activite;
import metier.Admin;
import metier.Client;
import metier.Compte;
import metier.Pays;
import metier.Reservation;
import metier.Transport;
import metier.Voyage;
import metier.Voyageur;
import util.Context;

public class App {


	public static String saisieString(String message) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextLine();
	}

	public static double saisieDouble(String message) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextDouble();
	}

	public static int saisieInt(String message) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println(message);
		return sc.nextInt();
	}

	private static void menuPrincipal() {

		System.out.println("Welcome, merci de faire un choix :");
		System.out.println("1 - Voir la liste de nos destinations :");
		System.out.println("2 - Se connecter");
		System.out.println("3 - Fermer l'application");
		int choix = saisieInt("");

		switch(choix) 
		{
		case 1 : listeVoyages();break;
		case 2 : seConnecter();break;
		case 3 : Context.getInstance().closeEmf();System.exit(0);;break;
		default : System.out.println("Choix impossible !\n");
		}

		menuPrincipal();
	}




	private static void seConnecter() {
		String compte1 =saisieString("Avez vous d�j� un compte client ? (oui/non)");

		if(compte1.equals("oui")) {
			String nom=saisieString("Nom de famille ");
			String password=saisieString("Saisir password ");

			Compte connected=Context.getInstance().getDaoCompte().checkConnect(nom, password);
			Context.getInstance().setConnected(connected);


			if(connected==null){ 
				System.out.println("Identifiants invalides, veuillez rentrer des Identifiants valides.");
				seConnecter();}
			else {
				System.out.println(connected);
				if(connected instanceof Admin) {
					System.out.println("Un plaisir de te revoir cher Admin :)");
					System.out.println("Saisir une action : ");
					menuAdmin();
				}

				else if(connected instanceof Compte)
				{ System.out.println("Bienvenue cher client :) \n");
				monCompte();
				}

				else {System.out.println("Error type de compte");}

			}

		}

		else if(compte1.equals("non")) {
			System.out.println("Saisir les donn�es pour cr�er votre compte.");
			creationCompte();
		}




	}

	private static void menuAdmin() {
		System.out.println("1- Supprimer un client");
		System.out.println("2- Supprimer un voyage");
		System.out.println("3- Modifier un voyage");
		System.out.println("4- Ajouter un voyage");
		System.out.println("5- Retour au menu principal");
		int choix = saisieInt("");

		switch (choix) {
		case 1: supprimerClient(); break;
		case 2: supprimerVoyage(); break;
		case 3: modifierVoyage(); break;
		case 4: ajouterVoyage(); break;
		case 5: menuPrincipal(); break;
		default : System.out.println("Choix impossible !\n");
		}
	}

	private static void ajouterVoyage() {
		//Cr�er un nouveau Pays
		System.out.println("Veuillez rentrer les donn�es du Pays de destination : ");
		int id=saisieInt("id");
		String nom=saisieString("nom");
		int restriction=saisieInt("Restriction (0 pour non et 1 pour oui)");
		int prixJours=saisieInt("prixJours");

		Pays p=new Pays(id,nom,prixJours,restriction);
		Context.getInstance().getDaoPays().save(p);

		// Cr�er le Voyage
		System.out.println("Veuillez rentrer les donn�es");
		String debut=saisieString("date de d�part");
		String fin=saisieString("date de fin");
		Pays idDepart=Context.getInstance().getDaoPays().findById(1) ;
		//idDepart=1 = FRANCE


		Voyage v=new Voyage(debut,fin,p,idDepart);
		Context.getInstance().getDaoVoyage().save(v);
		menuAdmin();

	}

	private static void modifierVoyage() {


		System.out.println("1-Modifier");
		System.out.println("2-Retour menu admin");
		int choix = saisieInt("");

		if (choix==1) {
			List <Voyage> v3=Context.getInstance().getDaoVoyage().findAll();
			for(Voyage  voyages : v3)
			{
				System.out.println(voyages);
			}

			int id = saisieInt("Saisir l'id du voyage � modifier");
			Voyage v = Context.getInstance().getDaoVoyage().findById(id);
			String newDebut= saisieString("La date de d�part est le : "+v.getDebut()+". Quel est la nouvelle date ?");
			v.setDebut(LocalDate.parse(newDebut));
			String newFin= saisieString("La date de fin est le : "+v.getFin()+". Quel est la nouvelle date ?");
			v.setFin(LocalDate.parse(newFin));
			Context.getInstance().getDaoVoyage().save(v);
			menuAdmin();

		} else if (choix==2) {
			menuAdmin();
		}else {
			System.out.println("Erreur saisie, selectionner 1 ou 2");
			menuAdmin();
		}

	}

	private static void supprimerVoyage() {

		System.out.println("1-Supprimer");
		System.out.println("2-Retour menu admin");
		int choix = saisieInt("");

		if (choix==1) {
			List <Voyage> v2=Context.getInstance().getDaoVoyage().findAll();
			for(Voyage  voyages : v2)
			{
				System.out.println(voyages);
			}

			int id = saisieInt("Saisir l'id du voyage � supprimer");
			Voyage v = Context.getInstance().getDaoVoyage().findById(id);
			Context.getInstance().getDaoVoyage().delete(v);
			menuAdmin();

		} else if (choix==2) {
			menuAdmin();
		}else {
			System.out.println("Erreur saisie, selectionner 1 ou 2");
			menuAdmin();
		}


	}

	private static void supprimerClient() {

		System.out.println("1-Supprimer");
		System.out.println("2-Retour menu admin");
		int choix = saisieInt("");

		if (choix==1) {
			List <Client> c2=Context.getInstance().getDaoClient().findAll();
			for(Compte  clients : c2)
			{
				System.out.println(clients);
			}

			int id = saisieInt("Saisir l'id du client � supprimer :");
			Compte c = Context.getInstance().getDaoCompte().findById(id);
			Context.getInstance().getDaoCompte().delete(c);
			menuAdmin();

		} else if (choix==2) {
			menuAdmin();
		}else {
			System.out.println("Erreur saisie, selectionner 1 ou 2");
			menuAdmin();
		}
	}

	private static void monCompte() {
		Compte connected=Context.getInstance().getConnected();
		Context.getInstance().setConnected(connected);

		List <Reservation> r = Context.getInstance().getDaoReservation().findByCompte(connected);

		System.out.println("1 - Mes informations");
		System.out.println("2 - Voir mes reservations");
		System.out.println("3 - Liste des destinations ");
		System.out.println("4 - Deconnexion ");
		int choix = saisieInt("");

		switch(choix) 
		{

		case 1 : System.out.println(connected);break;
	
		case 2 : if (r.size()<1) 
			{System.out.println("Vous n'avez pas de r�servations");}
		case 2 : 
			for(Reservation resa:r) {
				System.out.println(resa.getVoyage());
				System.out.println("Liste des Voyageurs : "+Context.getInstance().getDaoVoyageur().findByReservation(resa.getId()));
				
			}
			
			else {

				for(Reservation resa:r) {
					System.out.println(resa.getVoyage());
				}
			}break;

		case 3 : listeVoyages();break;
		case 4: menuPrincipal();break;
		default : System.out.println("Choix impossible !\n");
		}

		monCompte();

	}

	private static void creationCompte() {
		String nom=saisieString("nom");
		String prenom=saisieString("prenom");
		String mail=saisieString("mail");
		String password=saisieString("password");


		Compte c=new Compte(nom,prenom,mail,password);
		c =Context.getInstance().getDaoCompte().save(c);

		Compte connected=c;
		Context.getInstance().getDaoCompte().checkConnect(nom, password);
		Context.getInstance().setConnected(connected);
		monCompte();

	}

	private static void listeVoyages() {

		System.out.println("Voici la liste de nos destinations :");

		List<Pays> p=Context.getInstance().getDaoPays().findAll();
		for(Pays c:p) {
			System.out.println(c.getNom());

		}

		System.out.println();
		System.out.println("1-R�server un voyage :");
		System.out.println("2 - Retour au menu principal ");
		int choix = saisieInt("");

		switch(choix)
		{case 1 :

			Compte connected=Context.getInstance().getConnected();
			if(connected instanceof Compte)
			{ choixReservation();

			}

			else if(connected==null) { 
				System.out.println("Veuillez vous connecter pour pouvoir r�server un voyage");
				seConnecter();
			} break;

		case 2 : monCompte();break;}

	}


	private static void choixReservation() {

		
		Activite a1=null;
		
		List<Pays> p=Context.getInstance().getDaoPays().findAll();
		for(Pays c:p) {
			System.out.println("id: "+c.getId()+" Pays :"+c.getNom());

		}
		
		int choixPays=saisieInt("Saisir l'id du Pays ou vous souhaitez partir : ");
		Pays p1=Context.getInstance().getDaoPays().findById(choixPays);
		List<Voyage> voyages =Context.getInstance().getDaoVoyage().voyageWithPays(p1);
		for(Voyage v: voyages) {
			System.out.println("id : "+v.getId()+" - Pays de destination :"+v.getDestination().getNom()+" - Date d�part : "+v.getDebut()+" - Date retour : "+v.getFin()+" - Prix : "+v.getPrixVoyage()+"�");
		}
		if (voyages.size()<1) {
			System.out.println("Pas de voyage disponible � ces dates");
			choixReservation();
		}
		
		int id=saisieInt("Saisir l'id du voyage ");

        Voyage v=Context.getInstance().getDaoVoyage().findById(id);
        
        
		// Ajout activit�
        List<Activite> activites=Context.getInstance().getDaoActivite().findByIdPays(v.getDestination());
		System.out.println("Nos activit�s :");
		
		for (Activite act: activites) {
			System.out.println(act);
		}
		String choixActivites=saisieString("Voulez vous r�server des activit�s ? (oui/non)");
		List<Activite> ajoutActivite=new ArrayList();


		while(choixActivites.equals("oui")){
			List<Activite> a =Context.getInstance().getDaoActivite().findByIdPays(v.getDestination());
			int choix=saisieInt("Saisir id de l'activit� ");
			a1=Context.getInstance().getDaoActivite().findById(choix);
			ajoutActivite.add(a1);

			for (Activite act: a) {
				System.out.println(act);
			}
			choixActivites=saisieString("Voulez vous r�server une autre activit� ? (oui/non)");
		}


		if(choixActivites.equals("non")) {
			int choix=1;
			a1=Context.getInstance().getDaoActivite().findById(choix);
		}

		// Ajout du transport
		Transport choixTransport = null;
		Transport listeTransport[] = Transport.values();
		
		System.out.println("Nos transports :");
		for(Transport t : listeTransport) 
		{
			System.out.println(t);
		}
		
		String choix1 = saisieString("Voulez vous un transport (oui/non)? ");
		String choix2=null;
		if(choix1.equals("oui"))

		{
			choix2=saisieString("Choix de votre transport");

			choixTransport = Transport.valueOf(choix2);

		}

		else if(choix1.equals("non")){
			choixTransport=null;
		}
		//Ajout de l'activit� de la r�servation
		Compte connected=Context.getInstance().getConnected();
		Reservation reservation =new Reservation(connected,Context.getInstance().getDaoVoyage().findById(v.getId()),v.getPrixVoyage(),choixTransport);


		String choixV;
		Voyageur vy=new Voyageur();
		List<Voyageur> ajoutVoy=new ArrayList();


		do {
			System.out.println("Veuillez renseigner vos informations : ");
			String nom=saisieString("Saisir votre nom ");
			String prenom=saisieString("Saisir votre prenom ");
			Context.getInstance().getDaoReservation().findById(id);


			vy = new Voyageur(nom, prenom,id);
			ajoutVoy.add(vy);
			Context.getInstance().getDaoVoyageur().save(vy);
			choixV=saisieString("Voulez vous ajouter un autre voyageur (oui/non)?");
		}
		while(choixV.equals("oui")) ;



		System.out.println("R�capitulatif r�servation : ");
		System.out.println("Destination : "+reservation.getVoyage().getDestination().getNom()+" - Date d�part : "+reservation.getVoyage().getDebut()+" - Date retour : "+reservation.getVoyage().getFin()+"\n"+" - Prix du voyage par personne :"+reservation.getPrix()+"� TTC");
		for(Voyageur voy5:ajoutVoy) {System.out.println(voy5);}


		Double prixFinal=ajoutVoy.size()*reservation.getPrix();
		for(Activite act5:ajoutActivite) {System.out.println(act5.getLibelle()+" - pendant "+act5.getDuree()+"heures");}
		String sauvegarde=saisieString("Voulez vous valider la reservation de ce voyage ? (oui/non)");


		if (sauvegarde.equals("oui"))
		{
			Context.getInstance().getDaoReservation().save(reservation);
			System.out.println("Reservation confirm�e\n");
			System.out.println("D�tails de la r�servation :");
			System.out.println("Destination : "+reservation.getVoyage().getDestination().getNom()+" - Date d�part : "+reservation.getVoyage().getDebut()+" - Date retour : "+reservation.getVoyage().getFin()+"\n"+" - Prix du voyage :"+prixFinal+"� TTC \n");

		}else if (sauvegarde.equals("non")) {
			menuPrincipal();
		} else {
			System.out.println("veuillez choisir oui ou non");
			choixReservation();
		}


		monCompte();
	}




	public static void main(String[] args) {
		Context.getInstance().getEmf().createEntityManager();
		menuPrincipal();
	}
}	