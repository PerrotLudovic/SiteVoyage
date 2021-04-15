package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Integer> {
	
}
