package com.example.ecolegestion.Services;

import com.example.ecolegestion.Entities.Modules;
import com.example.ecolegestion.Repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
    @Autowired
    private ModuleRepository moduleRepository;

    public List<Modules> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Modules> getModuleById(Long id) {
        return moduleRepository.findById(id);
    }

    public Modules ajouterModule(Modules module) {return moduleRepository.save(module);}

    public void supprimerModule(Long id) {
        moduleRepository.deleteById(id);
    }
}
