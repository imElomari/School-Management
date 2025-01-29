package com.example.ecolegestion.Controllers;

import com.example.ecolegestion.Entities.Professeur;
import com.example.ecolegestion.Services.ProfesseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/professeurs")
public class ProfesseurController {

    @Autowired
    private ProfesseurService professeurService;

    // Liste des professeurs
    @GetMapping
    public String listProfesseurs(Model model) {
        List<Professeur> professeurs = professeurService.getAllProfesseurs();
        model.addAttribute("professeurs", professeurs);
        return "professeurs/liste";
    }

    // Formulaire d'ajout
    @GetMapping("/ajouter")
    public String showAddForm(Model model) {
        model.addAttribute("professeur", new Professeur());
        return "professeurs/form";
    }

    // Enregistrement d'un professeur
    @PostMapping("/save")
    public String saveProfesseur(@ModelAttribute Professeur professeur) {
        professeurService.ajouterProfesseur(professeur);
        return "redirect:/professeurs";
    }

    // Formulaire de modification
    @GetMapping("/modifier/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Professeur> professeur = professeurService.getProfesseurById(id);
        if (professeur.isPresent()) {
            model.addAttribute("professeur", professeur.get());
            return "professeurs/form";
        } else {
            return "redirect:/professeurs";
        }
    }

    // Suppression d'un professeur
    @GetMapping("/supprimer/{id}")
    public String deleteProfesseur(@PathVariable Long id) {
        professeurService.supprimerProfesseur(id);
        return "redirect:/professeurs";
    }
}
