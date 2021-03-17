package dao;

import java.util.List;

public interface IDAO <T,K> {
	String login="root";
	String password="";
	String chemin="jdbc:mysql://localhost:3306/sitevoyage?characterEncoding=utf8";
	
	public T findById(K id);
	public List<T> findAll();
	public void insert(T d);
	public void update(T d);
	public T save(T Object);
	public void delete(T d);
	
}
