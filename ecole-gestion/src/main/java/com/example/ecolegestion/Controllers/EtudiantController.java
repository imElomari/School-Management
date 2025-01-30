package com.example.ecolegestion.Controllers;


import com.example.ecolegestion.Entities.Etudiant;
import com.example.ecolegestion.Entities.Modules;
import com.example.ecolegestion.Repositories.ModuleRepository;
import com.example.ecolegestion.Services.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/etudiants")
public class EtudiantController {

    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private ModuleRepository modulesRepository;

    // Liste des étudiants
    @GetMapping
    public String listEtudiants(Model model) {
        List<Etudiant> etudiants = etudiantService.getAllEtudiants();
        model.addAttribute("etudiants", etudiants);
        return "etudiants/liste";
    }

    // Formulaire d'ajout
    @GetMapping("/ajouter")
    public String showAddForm(Model model) {
        model.addAttribute("etudiant", new Etudiant());
        return "etudiants/form";
    }

    // Enregistrement d'un étudiant
    @PostMapping("/save")
    public String saveEtudiant(@ModelAttribute Etudiant etudiant) {
        etudiantService.ajouterEtudiant(etudiant);
        return "redirect:/etudiants";
    }

    // Formulaire de modification
    @GetMapping("/modifier/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
        if (etudiant.isPresent()) {
            model.addAttribute("etudiant", etudiant.get());
            return "etudiants/form";
        } else {
            return "redirect:/etudiants";
        }
    }

    // Suppression d'un étudiant
    @GetMapping("/supprimer/{id}")
    public String deleteEtudiant(@PathVariable Long id) {
        etudiantService.supprimerEtudiant(id);
        return "redirect:/etudiants";
    }

    // Détails d'un étudiant
    @GetMapping("/{id}")
    public String detailEtudiant(@PathVariable Long id, Model model) {
        Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
        if (etudiant.isPresent()) {
            model.addAttribute("etudiant", etudiant.get());
            return "etudiants/detail";
        } else {
            return "redirect:/etudiants";
        }
    }

    // Recherche un etudiant par son nom ou son code Apogee
    @GetMapping("/search")
    public String searchEtudiant(@RequestParam String keyword, Model model) {
        List<Etudiant> etudiants = etudiantService.searchEtudiant(keyword);
        model.addAttribute("etudiants", etudiants);
        return "etudiants/liste";
    }

    // Afficher toutes les details sur un etudiant aussi les modules inscrits par cet etudiant
    @GetMapping("/details/{id}")
    public String detailsEtudiant(@PathVariable Long id, Model model) {
        Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
        if (etudiant.isPresent()) {
            model.addAttribute("etudiant", etudiant.get());
            return "etudiants/details";
        } else {
            return "redirect:/etudiants";
        }
    }

    // Inscrire un etudiant dans un module
    @GetMapping("/inscrire/{id}")
    public String showInscrireForm(@PathVariable Long id, Model model) {
        Optional<Etudiant> etudiant = etudiantService.getEtudiantById(id);
        if (etudiant.isPresent()) {
            model.addAttribute("etudiant", etudiant.get());
            List<Modules> modules = modulesRepository.findAll();
            model.addAttribute("modules", modules);
            return "etudiants/inscrire";
        } else {
            return "redirect:/etudiants";
        }
    }
    @PostMapping("/inscrire/{etudiantId}")
    public String inscrireEtudiant(@PathVariable Long etudiantId, @RequestParam Long moduleId) {
        etudiantService.inscrireEtudiant(etudiantId, moduleId);
        return "redirect:/etudiants/details/" + etudiantId;
    }

    @PostMapping("/annulerInscription/{etudiantId}/{moduleId}")
    public String annulerInscription(@PathVariable Long etudiantId, @PathVariable Long moduleId) {
        etudiantService.annulerInscription(etudiantId, moduleId);
        return "redirect:/etudiants/details/" + etudiantId;
    }

}
