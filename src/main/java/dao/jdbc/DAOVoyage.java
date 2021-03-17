package dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.IDAO;
import metier.Pays;
import metier.Voyage;

public class DAOVoyage implements IDAO<Voyage, Integer>{


	public Voyage findById(Integer id) {
		
		Voyage voyage = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from voyage where id=?");
			ps.setInt(1,id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				DAOPays daoPays = new DAOPays();
				int id1=rs.getInt("id");
				String debut=rs.getString("debut");
				String fin=rs.getString("fin");
				Pays destination=daoPays.findById (rs.getInt("destination"));
				Pays depart=daoPays.findById (rs.getInt("depart")); 
				

				voyage = new Voyage(id1,debut,fin,destination,depart);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voyage;
	}



	public List<Voyage> findAll() {
		List<Voyage> voyages= new ArrayList();
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);
			PreparedStatement ps = conn.prepareStatement("Select * from voyage");
			ResultSet rs = ps.executeQuery();

			while(rs.next()) 
			{
				DAOPays daoPays = new DAOPays();
				int id=rs.getInt("id");
				String debut=rs.getString("debut");
				String fin=rs.getString("fin");
				Pays destination=daoPays.findById (rs.getInt("destination"));
				Pays depart=daoPays.findById (rs.getInt("depart")); 
			

				Voyage vs = new Voyage(id,debut,fin,destination,depart);
				voyages.add(vs);
			}
			rs.close();
			ps.close();
			conn.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

		return voyages;
	}


	public void insert(Voyage v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("INSERT INTO voyage VALUES(?,?,?,?,?)");
			ps.setInt(1,v.getId());
			ps.setString(2, v.getDebut().toString());
			ps.setString(3, v.getFin().toString());
			ps.setInt(4, v.getDepart().getId());
			ps.setInt(5, v.getDestination().getId());
			


			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update(Voyage v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");


			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("Update voyage set debut=?,fin=?,destination=?, depart=? where id=?");

			ps.setInt(1,v.getId());
			ps.setString(2, v.getDebut().toString());
			ps.setString(3, v.getFin().toString());
			ps.setString(2, v.getDebut().toString());
			ps.setString(3, v.getFin().toString());
			

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void delete(Voyage v) {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection conn=DriverManager.getConnection(chemin,login,password);

			PreparedStatement ps = conn.prepareStatement("DELETE FROM voyage where id=?");
			ps.setInt(1,v.getId());

			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
