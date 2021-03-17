package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Client;

public class DAOClient implements IDAO<Client, Integer>{


	public Client findById(Integer id) {
		Client client= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from client where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id1=rs.getInt("id");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				String email=rs.getString("email");
				String password=rs.getString("password");
				String typeCompte=rs.getString("typeCompte");
				
				client = new Client(id1,nom,prenom,email,password);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return client;
	}



	public List<Client> findAll() {
		List<Client> clients= new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from client");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id=rs.getInt("id");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				String email=rs.getString("email");
				String password=rs.getString("password");
				String typeCompte=rs.getString("typeCompte");
				
				Client c1 = new Client(id,nom,prenom,email,password);
				clients.add(c1);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return clients;
	}


	public void insert(Client cl) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO activite VALUES(?,?,?,?,?,?)");
			ps.setInt(1,cl.getId());
			ps.setString(2, cl.getNom());
			ps.setString(3, cl.getPrenom());
			ps.setString(4, cl.getEmail());
			ps.setString(5, cl.getPassword());
			
			
			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Client cl) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("Update activite set nom=?, prenom=?, email=?,password=? where id=?");

			ps.setInt(1,cl.getId());
			ps.setString(2, cl.getNom());
			ps.setString(3, cl.getPrenom());
			ps.setString(4, cl.getEmail());
			ps.setString(5, cl.getPassword());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void delete(Client cl) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM client where id=?");
			ps.setInt(1,cl.getId());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
