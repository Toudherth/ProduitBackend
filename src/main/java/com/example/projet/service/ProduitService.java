package com.example.projet.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projet.entity.Produit;
import com.example.projet.exception.ProduitNotFoundException;
import com.example.projet.repository.ProduitRepository;

@Service
public class ProduitService {

    private ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    // All produits
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    // get produit by name(recherche)
    public List<Produit> getProduitByNom(String nom) {
        return produitRepository.findByNom(nom);
    }

    // add produit
    public Produit addProduit(Produit produit) {
        return produitRepository.save(produit);
    }

    // Update produit
    public Produit updateProduit(Long produitId, Produit updatedProduit) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ProduitNotFoundException("Le produit est introuvable "));

        if (updatedProduit.getNom() != null) {
            produit.setNom(updatedProduit.getNom());
        }

        if (updatedProduit.getPrixUnitaire() != null) {
            produit.setPrixUnitaire(updatedProduit.getPrixUnitaire());
        }

        if (updatedProduit.getQuantite() != null) {
            produit.setQuantite(updatedProduit.getQuantite());
        }

        return produitRepository.save(produit);
    }

    // Delete produit
    public void deleteProduit(Long produitId) {
        Produit produit = produitRepository.findById(produitId)
                .orElseThrow(() -> new ProduitNotFoundException("Le produit est introuvable "));

        produitRepository.delete(produit);
    }

}
