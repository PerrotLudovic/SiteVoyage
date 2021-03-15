package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import metier.Activite;
import metier.Compte;
import metier.Pays;
import metier.Reservation;
import metier.Transport;
import metier.Voyage;

public class DAOReservation implements IDAO<Reservation, Integer>{


	public Reservation findById(Integer id) {
		Reservation reservation= null;
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from reservation where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				DAOActivite daoActivite=new DAOActivite();
				DAOCompte daoCompte=new DAOCompte();
				DAOVoyage daoVoyage = new DAOVoyage();
				int idResa=rs.getInt("id");
				Compte idCompte=daoCompte.findById(rs.getInt("idCompte"));
				Voyage idVoyage=daoVoyage.findById(rs.getInt("idVoyage"));
				String date=rs.getString("date");
				double prix=rs.getDouble("prix");
				String typeTransport=rs.getString("transport");
			
		
					reservation = new Reservation(idCompte,idVoyage,prix,Transport.valueOf(typeTransport));
		
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reservation;
	}

	
	


	public List<Reservation> findAll() {
		List<Reservation> reservations= new ArrayList();
		Reservation reservation= null;
		DAOActivite daoActivite=new DAOActivite();
		DAOCompte daoCompte=new DAOCompte();
		DAOVoyage daoVoyage = new DAOVoyage();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from reservation");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				
				int idResa=rs.getInt("id");
				Compte idCompte=daoCompte.findById(rs.getInt("idCompte"));
				Voyage idVoyage=daoVoyage.findById(rs.getInt("idVoyage"));
				String date=rs.getString("date");
				double prix=rs.getDouble("prix");
				String typeTransport=rs.getString("transport");
				
		
				Reservation r = new Reservation(idCompte,idVoyage,prix,Transport.valueOf(typeTransport));
				reservations.add(r);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reservations;
	}

	public List<Reservation> findByIdCompte(Integer idCompte) {
		List<Reservation> reservations= new ArrayList();
		Reservation reservation= null;
		DAOActivite daoActivite=new DAOActivite();
		DAOCompte daoCompte=new DAOCompte();
		DAOVoyage daoVoyage = new DAOVoyage();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from reservation where idCompte=?");
			ps.setInt(1,idCompte);
			ResultSet rs = ps.executeQuery();
			
			

			while(rs.next()) 
			{

				int idResa=rs.getInt("id");
				Compte idC=daoCompte.findById(rs.getInt("idCompte"));
				Voyage idVoyage=daoVoyage.findById(rs.getInt("idVoyage"));
				String date=rs.getString("date");
				double prix=rs.getDouble("prix");
				String typeTransport=rs.getString("transport");
			
		
				Reservation r = new Reservation(idC,idVoyage,prix,Transport.valueOf(typeTransport));
				reservations.add(r);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return reservations;
	}

	
	
	public void insert(Reservation r) {
		int lastInsertId=0;
		DAOVoyage daoVoyage = new DAOVoyage();
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO reservation VALUES(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			 
			ps.setInt(1, r.getId());
			ps.setObject(2, r.getCompte().getId());
			ps.setObject(3, r.getVoyage().getId()); 
			ps.setString(4, r.getDate().toString());
			ps.setDouble(5, r.getPrix());
			ps.setString(6, r.getTransport().toString());
		
			
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) { lastInsertId=rs.getInt(1);}
			
			
			for (Activite a : r.getActivites()) {
				PreparedStatement ps2 = conn.prepareStatement("INSERT INTO reservation_activite (idReservatino,idActivite) VALUES(?,?)");
				ps2.setInt(1,lastInsertId); 
				ps2.setInt(2,a.getId());
				ps2.close();
			}

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void update(Reservation r) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("Update reservation set date=?,prix=?, idVoyage=? where id=?");
			
			
			ps.setString(1, r.getDate().toString());
			ps.setDouble(2, r.getPrix());
			ps.setObject(3, r.getVoyage()); 
			ps.setInt(4,r.getId());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void delete(Reservation r) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM reservation where id=?");
			ps.setInt(1,r.getId());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}




}
