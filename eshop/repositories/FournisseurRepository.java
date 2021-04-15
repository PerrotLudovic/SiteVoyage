package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Fournisseur;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Integer> {
	
}
