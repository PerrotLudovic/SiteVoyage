package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Voyageur;

public class DAOVoyageur implements IDAO<Voyageur, Integer>{

	
	public Voyageur findById(Integer id) {
		Voyageur voyageur= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from voyageur where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				int id1=rs.getInt("id");
				int idReservation=rs.getInt("idReservation");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				
				
			 voyageur = new Voyageur(id1,idReservation,nom,prenom);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return voyageur;
	}

	
	
	public List<Voyageur> findAll() {
		List<Voyageur> voyageurs= new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from voyageur");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) 
			{
				int id1=rs.getInt("id");
				int idReservation=rs.getInt("idReservation");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				
				Voyageur vs = new Voyageur(id1,idReservation, nom,prenom);
				voyageurs.add(vs);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return voyageurs;
	}

	
	public void insert(Voyageur v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			Connection conn=DriverManager.getConnection(chemin,login,password);
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO voyageur VALUES(?,?,?,?)");
			ps.setInt(1,v.getId());
			ps.setString(2, v.getNom());
			ps.setString(3, v.getPrenom());
			ps.setInt(4, v.getIdReservation());
			
			
			ps.executeUpdate();
	
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void update(Voyageur vs) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			
			Connection conn=DriverManager.getConnection(chemin,login,password);
			
			PreparedStatement ps = conn.prepareStatement("Update voyageur set nom=?,prenom=?,idReservation=? where id=?");
			
			ps.setInt(1,vs.getId());
			ps.setString(2, vs.getNom());
			ps.setString(3, vs.getPrenom());
			ps.setInt(4, vs.getIdReservation());
			
			ps.executeUpdate();
	
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void delete(Voyageur vs) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn=DriverManager.getConnection(chemin,login,password);
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM voyageur where id=?");
			ps.setInt(1,vs.getId());
		
			ps.executeUpdate();
	
			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
	
