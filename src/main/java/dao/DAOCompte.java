package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Admin;
import metier.Client;
import metier.Compte;


public class DAOCompte implements IDAO<Compte, Integer>{


	public Compte findById(Integer id) {
		Compte compte= null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from compte where id=?");
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

				if(typeCompte.equals("Admin")) {
					compte=new Admin(rs.getInt("id"),nom, prenom, email, password);
				}

				else {

					compte=new Client(rs.getInt("id"),nom, prenom, email,password);

				}
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return compte;
	}

	public List<Compte> findAll() {
		List<Compte> comptes= new ArrayList();
		Compte compte=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from compte");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id=rs.getInt("id");
				String nom=rs.getString("nom");
				String prenom=rs.getString("nom");
				String email=rs.getString("email");
				String password=rs.getString("password");
				String typeCompte=rs.getString("typeCompte");

				if(typeCompte.equals("Admin")) {
					compte=new Admin(rs.getInt("id"),nom, prenom, email, password);
				}

				else {

					compte=new Client(rs.getInt("id"),nom, prenom, email,password);
				comptes.add(compte);
				}
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return comptes;
	}


	public void insert(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO compte VALUES(?,?,?,?,?,?)");
			ps.setInt(1, c.getId());
			ps.setString(2, c.getNom());
			ps.setString(3, c.getPrenom());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getPassword());
			ps.setString(6, c.getTypeCompte());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("Update compte set nom=?, prenom=?, email=?,password=? where id=?");

			ps.setInt(1,c.getId());
			ps.setString(2, c.getNom());
			ps.setString(2, c.getPrenom());
			ps.setString(3, c.getEmail());
			ps.setString(4, c.getPassword());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void delete(Compte c) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM compte where id=?");
			ps.setInt(1,c.getId());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Compte checkConnect(String log,String pass) {
		Compte c=null;


		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from compte where nom=? and password=?");
			ps.setString(1,log);
			ps.setString(2,pass);
			ResultSet rs = ps.executeQuery();



			while(rs.next()) 
			{ 
				int id= rs.getInt("id");
				String nom=rs.getString("nom");
				String prenom=rs.getString("Prenom");
				String email=rs.getString("email");
				String pass1=rs.getString("password");
				String typeCompte=rs.getString("typeCompte");

				if(typeCompte.equals("Admin")) {
					c=new Admin(id,nom,prenom,email,pass1);
				}

				else {

					c=new Compte(id,nom,prenom,email,pass1);

				}

			}


			rs.close();
			ps.close();
			conn.close();	

		} 
		catch (Exception e) {
			e.printStackTrace();
		}

		return c;
	}
}