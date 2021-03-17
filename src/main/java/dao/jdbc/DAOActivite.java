package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Activite;
import metier.Pays;

public class DAOActivite implements IDAO<Activite, Integer>{

		public Activite findById1(Integer id) {
			Activite activite= null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection conn=DriverManager.getConnection(chemin,login,password);
				PreparedStatement ps = conn.prepareStatement("Select * from activite where id=?");
				ps.setInt(1,id);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) 
				{
					int id1=rs.getInt("id");
					String libelle=rs.getString("libelle");
					int duree=rs.getInt("duree");
					int idPays=rs.getInt("idPays");
					
					//Commentaire 
				 activite = new Activite(id1,libelle,duree,idPays);
				}
				rs.close();
				ps.close();
				conn.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return activite;
		}

		
		
		public List<Activite> findAll() {
			List<Activite> activites= new ArrayList();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection conn=DriverManager.getConnection(chemin,login,password);
				PreparedStatement ps = conn.prepareStatement("Select * from activite");
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) 
				{
					int id=rs.getInt("id");
					String libelle=rs.getString("libelle");
					int duree=rs.getInt("duree");
					int idPays=rs.getInt("idPays");
					
					Activite a1 = new Activite(id,libelle,duree,idPays);
					activites.add(a1);
				}
				rs.close();
				ps.close();
				conn.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return activites;
		}

		
		public List<Activite> findByIdPays(Integer idpays) {
			List <Activite> ac =new ArrayList();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection conn=DriverManager.getConnection(chemin,login,password);
				PreparedStatement ps = conn.prepareStatement("Select * from activite where idpays=?");
				ps.setInt(1,idpays);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next()) 
				{
					int id=rs.getInt("id");
					String libelle=rs.getString("libelle");
					int duree=rs.getInt("duree");
					int idPays=rs.getInt("idpays");
					
					Activite a2 = new Activite(id,libelle,duree,idPays);
					ac.add(a2);
				}
				rs.close();
				ps.close();
				conn.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return ac;
		}

		
		
		public void insert(Activite a) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				
				Connection conn=DriverManager.getConnection(chemin,login,password);
				
				PreparedStatement ps = conn.prepareStatement("INSERT INTO activite VALUES(?,?,?,?)");
				ps.setInt(1,a.getId());
				ps.setString(2, a.getLibelle());
				ps.setInt(3, a.getDuree());
				ps.setInt(4, a.getIdPays());
				
				ps.executeUpdate();
		
				ps.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

		public void update(Activite a) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				
				Connection conn=DriverManager.getConnection(chemin,login,password);
				
				PreparedStatement ps = conn.prepareStatement("Update activite set libelle=?,duree=?,idPays=? where id=?");
				
				ps.setInt(1,a.getId());
				ps.setString(2, a.getLibelle());
				ps.setInt(3, a.getDuree());
				ps.setInt(4, a.getIdPays());
				
				ps.executeUpdate();
		
				ps.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		public void delete(Activite a) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				
				Connection conn=DriverManager.getConnection(chemin,login,password);
				
				PreparedStatement ps = conn.prepareStatement("DELETE FROM activite where id=?");
				ps.setInt(1,a.getId());
			
				ps.executeUpdate();
		
				ps.close();
				conn.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}



		@Override
		public Activite findById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}



	

		

	
}
