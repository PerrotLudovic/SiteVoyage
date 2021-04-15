package eshop.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "seqClient", sequenceName = "seq_client", initialValue = 100, allocationSize = 1)
@Table(name = "client")
public class Client extends Personne {

	
	private int age;
	@Column(name="birthdate")
	private LocalDate naissance;
	
	//fetch sur une liste vaut Lazy par default, ne sert a rien de le preciser dans notre cas ! 
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinTable(name="achats",joinColumns = @JoinColumn(name="id_client"),inverseJoinColumns = @JoinColumn(name="id_produit"))
	
	
	private List<Produit> produits=new ArrayList();
	
	
	public Client() {
	}

	public Client(String nom,String prenom,int age, String naissance) {
		super(nom,prenom);
		this.age = age;
		this.naissance = LocalDate.parse(naissance);
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getNaissance() {
		return naissance;
	}

	public void setNaissance(String naissance) {
		this.naissance = LocalDate.parse(naissance);
	}
	
	

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public void setNaissance(LocalDate naissance) {
		this.naissance = naissance;
	}

	@Override
	public String toString() {
		return "Client [age=" + age + ", naissance=" + naissance + "]";
	}
	
	
}
