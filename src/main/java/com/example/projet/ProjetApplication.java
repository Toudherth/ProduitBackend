package com.example.projet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.projet.entity.Produit;
import com.example.projet.repository.ProduitRepository;

@SpringBootApplication
public class ProjetApplication implements CommandLineRunner {

    private ProduitRepository produitRepository;

    @Autowired
    public ProjetApplication(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(ProjetApplication.class, args);
    }

    public void run(String... args) throws Exception {

        produitRepository.save(new Produit(null, "Souris sans fil", 29.99, 10L));
        produitRepository.save(new Produit(null, "Ordinateur portable", 999.99, 5L));
        produitRepository.save(new Produit(null, "Casque audio", 149.99, 8L));
        produitRepository.save(new Produit(null, "Clavier mécanique", 79.99, 15L));
        produitRepository.save(new Produit(null, "Écran LCD", 249.99, 3L));

    }

}