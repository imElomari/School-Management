package com.example.ecolegestion.Services;


import com.example.ecolegestion.Entities.Etudiant;
import com.example.ecolegestion.Entities.Modules;
import com.example.ecolegestion.Repositories.EtudiantRepository;
import com.example.ecolegestion.Repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;
    @Autowired
    private ModuleRepository moduleRepository;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(Long id) {
        return etudiantRepository.findById(id);
    }

    public Etudiant ajouterEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public void supprimerEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }

    public List<Etudiant> findAll() {
        return etudiantRepository.findAll();
    }

    public List<Etudiant> searchEtudiant(String keyword) {
        return etudiantRepository.search(keyword);
    }

    public void inscrireEtudiant(Long etudiantId, Long moduleId) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(etudiantId);
        Optional<Modules> module = moduleRepository.findById(moduleId);
        if (etudiant.isPresent() && module.isPresent()) {
            Etudiant etudiantEntity = etudiant.get();
            Modules moduleEntity = module.get();
            etudiantEntity.getModules().add(moduleEntity);
            moduleEntity.getEtudiants().add(etudiantEntity);
            etudiantRepository.save(etudiantEntity);
        }
    }

    public void annulerInscription(Long etudiantId, Long moduleId) {
        Optional<Etudiant> etudiant = etudiantRepository.findById(etudiantId);
        Optional<Modules> module = moduleRepository.findById(moduleId);
        if (etudiant.isPresent() && module.isPresent()) {
            Etudiant etudiantEntity = etudiant.get();
            Modules moduleEntity = module.get();
            etudiantEntity.getModules().remove(moduleEntity);
            moduleEntity.getEtudiants().remove(etudiantEntity);
            etudiantRepository.save(etudiantEntity);
        }
    }
}
