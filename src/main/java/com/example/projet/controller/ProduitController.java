package com.example.projet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projet.entity.Produit;
import com.example.projet.exception.ProduitException;
import com.example.projet.exception.ProduitNotFoundException;
import com.example.projet.exception.ProduitsNotFoundException;
import com.example.projet.service.ProduitService;

@RestController
@RequestMapping("/produits")
@CrossOrigin("http://localhost:8080/")
public class ProduitController {

    private ProduitService produitService;

    @Autowired
    public ProduitController(ProduitService produitService) {
        this.produitService = produitService;
    }

    // get all
    @GetMapping
    public ResponseEntity<List<Produit>> getAll() {
        try {
            List<Produit> produits = produitService.getAllProduits();
            return ResponseEntity.ok(produits);
        } catch (ProduitsNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // add
    @PostMapping
    public ResponseEntity<?> addProduit(@RequestBody Produit produit) {
        try {
            Produit produittoadd = produitService.addProduit(produit);
            return ResponseEntity.ok(produittoadd);
        } catch (ProduitException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur interne s'est produite");
        }
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable("id") Long produitId,
            @RequestBody Produit updatedProduit) {
        try {
            Produit produit = produitService.updateProduit(produitId, updatedProduit);
            return ResponseEntity.ok(produit);
        } catch (ProduitNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable("id") Long produitId) {
        try {
            produitService.deleteProduit(produitId);
            return ResponseEntity.noContent().build();
        } catch (ProduitNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // search
    @GetMapping("/recherche")
    public ResponseEntity<List<Produit>> getByTitle(@RequestParam(required = false) String nom) {
        try {
            List<Produit> produits = produitService.getProduitByNom(nom);
            return ResponseEntity.ok(produits);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
