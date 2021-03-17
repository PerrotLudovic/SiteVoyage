package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import metier.Admin;

public class DAOAdmin implements IDAO<Admin, Integer>{

	@Override
	public Admin findById(Integer id) {
		Admin admin= null;
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

				admin = new Admin(id1,nom,prenom,email,password);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return admin;
	}


	@Override
	public List<Admin> findAll() {

		List<Admin> admins= new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from compte");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				int id=rs.getInt("id");
				String nom=rs.getString("nom");
				String prenom=rs.getString("prenom");
				String email=rs.getString("email");
				String password=rs.getString("password");
				String typeCompte=rs.getString("typeCompte");

				Admin a = new Admin(id,nom,prenom,email,password);
				admins.add(a);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return admins;
	}

	@Override
	public void insert(Admin d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Admin d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Admin d) {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM client where id=?");
			ps.setInt(1,d.getId());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

