package eshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import eshop.entity.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

}
