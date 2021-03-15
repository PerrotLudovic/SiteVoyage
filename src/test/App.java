package test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.DAOActivite;
import dao.DAOAdmin;
import dao.DAOClient;
import dao.DAOCompte;
import dao.DAOPays;
import dao.DAOReservation;
import dao.DAOVoyage;
import dao.DAOVoyageur;
import metier.Activite;
import metier.Admin;
import metier.Client;
import metier.Compte;
import metier.Pays;
import metier.Reservation;
import metier.Transport;
import metier.Voyage;
import metier.Voyageur;

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
	static DAOActivite daoActivite= new DAOActivite();
	static DAOAdmin daoAdmin= new DAOAdmin();
	static DAOClient daoClient= new DAOClient();
	static DAOCompte daoCompte= new DAOCompte(); 
	static DAOReservation daoReservation= new DAOReservation();
	static DAOVoyageur daoVoyageur= new DAOVoyageur(); 
	static DAOPays daoPays=new DAOPays();
	static DAOVoyage daoVoyage= new DAOVoyage(); 
	static Compte compteConnected;
	

	private static void menuPrincipal() {

		compteConnected = null;
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
		String compte1 =saisieString("Avez vous dejà un compte client ? (oui/non)");

		if(compte1.equals("oui")) {
			String login=saisieString("Nom de famille ");
			String password=saisieString("Saisir password ");

			compteConnected=daoCompte.checkConnect(login, password);
			System.out.println(compteConnected);

			if(compteConnected instanceof Admin) {
				System.out.println("Un plaisir de te revoir cher Admin :)");
				listeVoyages();
			}

			else if(compteConnected instanceof Compte)
			{ System.out.println("Bienvenue cher client :) \n");
			monCompte();
			}

			else { 
				System.out.println("Identifiants invalides, veuillez rentrer des Identifiants valides.");
				seConnecter();
			}
		}

		else if(compte1.equals("non")) {
			System.out.println("Saisir les données pour créer votre compte.");
			creationCompte();
		}
	}
		
	private static void monCompte() {

			
			System.out.println("1 - Mes informations");
			System.out.println("2 - Voir mes reservations");
			System.out.println("3 - Retour à la liste des voyages ");
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

		List<Pays> p=daoPays.findAll();
		for(Pays c:p) {
			System.out.println(c);
		}

		System.out.println();
		System.out.println("1-Réserver un voyage :");
		System.out.println("2 - Retour au menu principal ");
		int choix = saisieInt("");

		switch(choix)
		{case 1 :

			if(compteConnected instanceof Compte)
			{ choixReservation();

			}

			else if(compteConnected==null) { 
				System.out.println("Veuillez vous connecter pour pouvoir réserver un voyage");
				seConnecter();
			} break;

		case 2 : menuPrincipal();break;}

	}

	
	private static void choixReservation() {
		List<Activite> activites=new ArrayList();
		Activite a1=null;
	
		for(Voyage v:daoVoyage.findAll()) {
			System.out.println(v);
		}

		int id=saisieInt("Saisir l'id du voyage ");

		Voyage v=daoVoyage.findById(id);
		
		String choixActivites=saisieString("Voulez vous réserver des activités ? (oui/non)");
		
		if(choixActivites.equals("oui")) { 
		
		List<Activite> a =daoActivite.findByIdPays(v.getDestination().getId());
				for (Activite ac: a) {
					System.out.println(ac);
				}

				int choix=saisieInt("Saisir id de l'activité ");
				 a1=daoActivite.findById1(choix);
				
				
		}
		 
		else if(choixActivites.equals("non")) {
			int choix=0;
			a1=daoActivite.findById1(choix);
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
                System.out.println(t+" - "+t.getPrix()+"€ ");
            }
            choix2=saisieString("Choix de votre transport");

            choixTransport = Transport.valueOf(choix2);
            
        }
        
        else if(choix1.equals("non")){
        	choixTransport=null;
        }
		//Ajout de l'activité de la réservation
	Reservation r=new Reservation(compteConnected,daoVoyage.findById(v.getId()),v.getPrixVoyage(),choixTransport);
		
		/*
		id=r.getId();

		System.out.println("Pour valider la réservation, veuillez rentrer les données suivantes : ");
		String nom=saisieString("Nom : ");
		String prenom=saisieString("Prenom : ");


		Voyageur voy=new Voyageur(id,nom,prenom);
		*/
		
		System.out.println("Récapitulatif réservation : ");
		System.out.println(daoVoyage.findById(v.getId()));
		System.out.println("Activitée choisie : "+a1.getLibelle());
		String sauvegarde=saisieString("Voulez vous sauvegarder ? (oui/non)");
		

		if (sauvegarde.equals("oui"))
		{
			daoReservation.insert(r);
			System.out.println("Reservation confirmée\n");
			System.out.println("Détails de la réservation :");
			System.out.println(r.getCompte()+" "+r.getVoyage()+" "+r.getActivites()+" - Prix du voyage :"+r.getPrix()+"€ TTC");
			
		}else if (sauvegarde.equals("non")) {
				menuPrincipal();
			} else {
				System.out.println("veuillez choisir oui ou non");
				choixReservation();
			}

		
		menuPrincipal();
	}

	
	

		public static void main(String[] args) {
	System.out.println(daoReservation.findAll());
			menuPrincipal();
		}
	}	