package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.jdbc.DAOActivite;
import dao.jdbc.DAOAdmin;
import dao.jdbc.DAOClient;
import dao.jdbc.DAOCompte;
import dao.jdbc.DAOPays;
import dao.jdbc.DAOReservation;
import dao.jdbc.DAOVoyage;
import dao.jdbc.DAOVoyageur;
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
//test
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
		System.out.println("1 - Voir la liste de nos voyages :");
		System.out.println("2 - Se connecter");
		int choix = saisieInt("");

		switch(choix) 
		{
		case 1 : listeVoyages();break;
		case 2 : seConnecter();break;
		default : System.out.println("Choix impossible !\n");
		}

		menuPrincipal();
	}

	
	
	
	private static void seConnecter() {
		String compte1 =saisieString("Avez vous dej� un compte client ? (oui/non)");

		if(compte1.equals("oui")) {
			String nom=saisieString("Nom de famille ");
			String password=saisieString("Saisir password ");

			Compte connected=Context.getInstance().getDaoCompte().checkConnect(nom, password);
			Context.getInstance().setConnected(connected);
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

			else { 
				System.out.println("Identifiants invalides, veuillez rentrer des Identifiants valides.");
				seConnecter();
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
		//case 3: modifierVoyage(); break;
		case 4: ajouterVoyage(); break;
		case 5: menuPrincipal(); break;
		default : System.out.println("Choix impossible !\n");
		}
	}

	private static void ajouterVoyage() {
		//Cr�er un nouveau Pays
		System.out.println("Veuillez rentrer les donn�es");
		int id=saisieInt("id");
		String nom=saisieString("nom");
		int restriction=saisieInt("Restriction (0 pour non et 1 pour oui)");
		int prixJours=saisieInt("prixJours");

		Pays p=new Pays(id,nom,restriction,prixJours);
		Context.getInstance().getDaoPays().save(p);

		// Cr�er le Voyage
		System.out.println("Veuillez rentrer les donn�es");
		String debut=saisieString("date de d�part");
		String fin=saisieString("date de fin");
		Pays idDestination=Context.getInstance().getDaoPays().findById(p.getId()) ;
		Pays idDepart=Context.getInstance().getDaoPays().findById(0) ;
					//idDepart=0 = FRANCE

		Voyage v=new Voyage(debut,fin,idDestination,idDepart);
		Context.getInstance().getDaoVoyage().save(v);
		
	}
	
	/*private static void modifierVoyage() {
		
		
		System.out.println("1-Modifier");
		System.out.println("2-Retour menu admin");
		int choix = saisieInt("");

		if (choix==1) {
			listeVoyages();
			int id = saisieInt("Saisir l'id du voyage � modifier");
			Voyage v = daoVoyage.findById(id);
			String newDebut= saisieString("La date de d�part est le : "+v.getDebut()+". Quel est la nouvelle date ?");
			v.setDebut(newDebut);
			String newFin= saisieString("La date de fin est le : "+v.getFin()+". Quel est la nouvelle date ?");
			v.setFin(LocalDate.parse(newFin));
			
			daoVoyage.update(v);
			
		} else if (choix==2) {
			menuPrincipal();
		}else {
			System.out.println("Erreur saisie, selectionner 1 ou 2");
		}

	}*/

	private static void supprimerVoyage() {
		listeVoyages();
		System.out.println("1-Supprimer");
		System.out.println("2-Retour menu admin");
		int choix = saisieInt("");

		if (choix==1) {
			int id = saisieInt("Saisir l'id du voyage � supprimer");
			Voyage v = daoVoyage.findById(id);
			daoVoyage.delete(v);
		} else if (choix==2) {
			menuPrincipal();
		}else {
			System.out.println("Erreur saisie, selectionner 1 ou 2");
		}


	}

	private static void supprimerClient() {

		System.out.println("1-Supprimer");
		System.out.println("2-Retour menu admin");
		int choix = saisieInt("");

		if (choix==1) {
			int id = saisieInt("Saisir l'id du client � supprimer :");
			for(Compte  c : daoCompte.findAll())
			{
				System.out.println(c);
			}

			Compte c = daoCompte.findById(id);
			daoCompte.delete(c);

		} else if (choix==2) {
			menuPrincipal();
		}else {
			System.out.println("Erreur saisie, selectionner 1 ou 2");
		}
	}
		
	private static void monCompte() {

			
			System.out.println("1 - Mes informations");
			System.out.println("2 - Voir mes reservations");
			System.out.println("3 - Retour � la liste des voyages ");
			System.out.println("4 - Deconnexion ");
			int choix = saisieInt("");

			switch(choix) 
			{
			
			case 1 : System.out.println(compteConnected);break;
			case 2 : System.out.println(daoReservation.findByIdCompte(compteConnected.getId())); break;
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
		String typeCompte="Client";

		Compte c=new Compte(nom,prenom,mail,password,typeCompte);

		daoCompte.insert(c);
		compteConnected=daoCompte.checkConnect(nom, password);
		monCompte();

	}

	private static void listeVoyages() {

		System.out.println("Voici la liste de nos destinations :");

		List<Pays> p=Context.getInstance().getDaoPays().findAll();
		for(Pays c:p) {
			System.out.println(c);
			
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

		case 2 : menuPrincipal();break;}

	}

	
	private static void choixReservation() {
		List<Activite> activites=new ArrayList();
		Activite a1=null;
		
		for(Voyage v:Context.getInstance().getDaoVoyage().findAll() ) {
			System.out.println(v);
		}

		int id=saisieInt("Saisir l'id du voyage ");

		Voyage v=Context.getInstance().getDaoVoyage().findById(id);
		
		String choixActivites=saisieString("Voulez vous r�server des activit�s ? (oui/non)");
		
		if(choixActivites.equals("oui")) { 
	
		List<Activite> a =Context.getInstance().getDaoActivite().findByIdPays(v.getDestination().getId());
				for (Activite ac: a) {
					System.out.println(ac);
				}

				int choix=saisieInt("Saisir id de l'activit� ");
				 a1=Context.getInstance().getDaoActivite().findById(choix);
				
				
		}
		 
		else if(choixActivites.equals("non")) {
			int choix=0;
			a1=Context.getInstance().getDaoActivite().findById(choix);
		}
			
// Ajout du transport
		Transport choixTransport = null;
        Transport listeTransport[] = Transport.values();

        String choix1 = saisieString("Voulez vous un transport (oui/non)? ");
        String choix2=null;
        if(choix1.equals("oui")) 
        {
            for(Transport t : listeTransport) 
            {
                System.out.println(t+" - "+t.getPrix()+"� ");
            }
            choix2=saisieString("Choix de votre transport");

            choixTransport = Transport.valueOf(choix2);
            
        }
        
        else if(choix1.equals("non")){
        	choixTransport=null;
        }
		//Ajout de l'activit� de la r�servation
    Compte connected=Context.getInstance().getConnected();
	Reservation r=new Reservation(connected,Context.getInstance().getDaoVoyage().findById(v.getId()),v.getPrixVoyage(),choixTransport);
		
		/*
		id=r.getId();

		System.out.println("Pour valider la r�servation, veuillez rentrer les donn�es suivantes : ");
		String nom=saisieString("Nom : ");
		String prenom=saisieString("Prenom : ");


		Voyageur voy=new Voyageur(id,nom,prenom);
		*/
	// Ajout du voyageur

			String choixV = saisieString("Voulez vous ajouter un voyageur (oui/non)? ");
			DAOVoyageur daoVoyageur=new DAOVoyageur();
			
				if(choixV.equals("oui")) {

				String nom=saisieString("Saisir nom voyageur");
				String prenom=saisieString("Saisir prenom voyageur");
				Context.getInstance().getDaoReservation().findById(id);
				
				Voyageur vy =new Voyageur(nom, prenom,id);
				vy = new Voyageur(nom, prenom,id);
				daoVoyageur.insert(vy);}

			else if(choixActivites.equals("non")) {

			}

		
		System.out.println("R�capitulatif r�servation : ");
		System.out.println(Context.getInstance().getDaoVoyage().findById(v.getId()));
		System.out.println("Activit�e choisie : "+a1.getLibelle());
		String sauvegarde=saisieString("Voulez vous sauvegarder ? (oui/non)");
		

		if (sauvegarde.equals("oui"))
		{
			Context.getInstance().getDaoReservation().insert(r);
			System.out.println("Reservation confirm�e\n");
			System.out.println("D�tails de la r�servation :");
			System.out.println(r.getCompte()+" "+r.getVoyage()+" "+r.getActivites()+" - Prix du voyage :"+r.getPrix()+"� TTC");
			
		}else if (sauvegarde.equals("non")) {
				menuPrincipal();
			} else {
				System.out.println("veuillez choisir oui ou non");
				choixReservation();
			}

		
		menuPrincipal();
	}

	
	

		public static void main(String[] args) {
	System.out.println(Context.getInstance().getDaoReservation().findAll());
			menuPrincipal();
		}
	}	