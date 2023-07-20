package com.example.projet.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.projet.entity.Produit;

public interface ProduitRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByNom(String nom);

}
