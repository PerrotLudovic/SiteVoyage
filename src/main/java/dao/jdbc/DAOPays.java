package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import metier.Pays;
import metier.Transport;

public class DAOPays implements IDAO<Pays, Integer>{


	public Pays findById(Integer id) {
		Pays pays= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from pays where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id1=rs.getInt("id");
				String nom=rs.getString("nom");
				int restriction=rs.getInt("restriction");
				int prixJours=rs.getInt("prixJours");

				pays= new Pays(id1,nom,restriction,prixJours);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pays;
	}

	
	

	//		public void findWithTransport()
	//		{Transport transport= null;
	//		try {
	//			
	//			Class.forName("com.mysql.jdbc.Driver");
	//			
	//			Connection conn=DriverManager.getConnection(chemin,login,password);
	//			PreparedStatement ps = conn.prepareStatement("Select * from transport where pays=?");
	//			ps.setInt(1,getDestination().get(id));
	//			ResultSet rs = ps.executeQuery();
	//			
	//			while(rs.next()) 
	//			{
	//				int id1=rs.getInt("id");
	//				String nom=rs.getString("nom");
	//				boolean restriction=rs.getBoolean("restriction");
	//				int prixJours=rs.getInt("prixJours");
	//				
	//			 pays= new Pays(id1,nom,restriction,prixJours);
	//			}
	//			rs.close();
	//			ps.close();
	//			conn.close();	
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//		
	//		return pays;
	//	}



	public List<Pays> findAll() {
		List<Pays> pays= new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from pays");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id=rs.getInt("id");
				String nom=rs.getString("nom");
				int restriction=rs.getInt("restriction");
				int prixJours=rs.getInt("prixJours");

				Pays p = (Pays) new Pays(id,nom,restriction,prixJours);
				pays.add(p);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pays;
	}


	public void insert(Pays p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO pays VALUES(?,?,?,?)");
			ps.setInt(1,p.getId());
			ps.setString(2, p.getNom());
			ps.setInt(3, p.isRestriction());
			ps.setInt(4,p.getPrixJours());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Pays p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("Update pays set nom=?,restriction=?,prixVoyageur=? where id=?");

			ps.setInt(1,p.getId());
			ps.setString(2, p.getNom());
			ps.setInt(3, p.isRestriction());
			ps.setInt(4,p.getPrixJours());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void delete(Pays p) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM pays where id=?");
			ps.setInt(1,p.getId());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


