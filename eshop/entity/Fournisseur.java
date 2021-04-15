package eshop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="supplier")
public class Fournisseur extends Personne{

	@Column(name="company")
	private String societe;
	
	public Fournisseur() {
	}
	
	public Fournisseur(String nom,String prenom,String societe) {
		super(nom,prenom);
		this.societe = societe;
	}

	public String getSociete() {
		return societe;
	}

	public void setSociete(String societe) {
		this.societe = societe;
	}

	@Override
	public String toString() {
		return "Fournisseur [societe=" + societe + "]";
	}
	
	
}
