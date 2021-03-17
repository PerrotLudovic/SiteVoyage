package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Activite;
import metier.Admin;
import metier.Client;
import metier.Pays;
import metier.ResaActivite;
import metier.Reservation;
import metier.Voyage;

public class DAOResaActivite implements IDAO<ResaActivite, Integer>{

	
	DAOActivite daoActivite= new DAOActivite();
	
	public ResaActivite findById(Integer id) {
		ResaActivite resaActivite= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from reservation_activite where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id1=rs.getInt("id");
				int idReservation=rs.getInt("id");
				int idActivite=rs.getInt("id");
				
				
				ResaActivite ra = new ResaActivite(idReservation,idActivite);
			
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return resaActivite;
	}


	public List<ResaActivite> findAll() {
		List<ResaActivite> ra= new ArrayList();
		ResaActivite resaActivite= null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from reservation_activite");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id1=rs.getInt("id");
				int idReservation=rs.getInt("id");
				int idActivite=rs.getInt("id");
				
				
				resaActivite = new ResaActivite(idReservation,idActivite);
				ra.add(resaActivite);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ra;
	}

	public void insert(ResaActivite ra) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO reservation_activite VALUES(?,?,?)");
			ps.setInt(1,ra.getId());
			ps.setInt(2,ra.getIdReservation());
			ps.setInt(3,ra.getIdActivite());
			
			ps.executeUpdate();

			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void update(ResaActivite d) {
		
	}


	@Override
	public void delete(ResaActivite d) {
		
	}

}


