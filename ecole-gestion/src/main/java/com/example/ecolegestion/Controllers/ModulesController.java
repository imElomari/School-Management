package com.example.ecolegestion.Controllers;

import com.example.ecolegestion.Entities.Modules;
import com.example.ecolegestion.Entities.Professeur;
import com.example.ecolegestion.Repositories.ModuleRepository;
import com.example.ecolegestion.Repositories.ProfesseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/modules")
public class ModulesController {

    @Autowired
    private ModuleRepository modulesRepository;

    @Autowired
    private ProfesseurRepository professeurRepository;

    @GetMapping
    public String listModules(Model model) {
        List<Modules> modules = modulesRepository.findAll();
        model.addAttribute("modules", modules);
        return "modules/liste";
    }

    @GetMapping("/ajouter")
    public String showAddForm(Model model) {
        model.addAttribute("module", new Modules());
        return "modules/form";
    }

    @PostMapping("/ajouter")
    public String addModule(@ModelAttribute Modules module) {
        modulesRepository.save(module);
        return "redirect:/modules";
    }

    @GetMapping("/modifier/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Modules module = modulesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid module Id:" + id));
        model.addAttribute("module", module);
        model.addAttribute("professeurs", professeurRepository.findAll());
        return "modules/detail";
    }

    @PostMapping("/modifier/{id}")
    public String updateModule(@PathVariable("id") Long id, @ModelAttribute Modules module) {
        module.setId(id);
        modulesRepository.save(module);
        return "redirect:/modules";
    }

    @GetMapping("/associer/{id}")
    public String showAssociateForm(@PathVariable("id") Long id, Model model) {
        Modules module = modulesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid module Id:" + id));
        model.addAttribute("module", module);
        model.addAttribute("professeurs", professeurRepository.findAll());
        return "modules/associer";
    }

    @PostMapping("/associer/{id}")
    public String associateProfesseur(@PathVariable("id") Long id, @RequestParam Long professeurId) {
        Modules module = modulesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid module Id:" + id));
        Professeur professeur = professeurRepository.findById(professeurId).orElseThrow(() -> new IllegalArgumentException("Invalid professeur Id:" + professeurId));
        module.setProfesseur(professeur);
        modulesRepository.save(module);
        return "redirect:/modules";
    }

    @GetMapping("/supprimer/{id}")
    public String deleteModule(@PathVariable("id") Long id) {
        Modules module = modulesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid module Id:" + id));
        modulesRepository.delete(module);
        return "redirect:/modules";
    }

    // Afficher les etudiants inscrits dans un module
    @GetMapping("/{id}")
    public String showModule(@PathVariable("id") Long id, Model model) {
        Modules module = modulesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid module Id:" + id));
        model.addAttribute("module", module);
        return "modules/details";
    }
}
