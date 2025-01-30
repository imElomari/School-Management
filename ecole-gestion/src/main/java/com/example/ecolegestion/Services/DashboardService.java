package com.example.ecolegestion.Services;

import com.example.ecolegestion.Repositories.EtudiantRepository;
import com.example.ecolegestion.Repositories.ModuleRepository;
import com.example.ecolegestion.Entities.Modules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    public long getTotalEtudiants() {
        return etudiantRepository.count();
    }

    public long getTotalModules() {
        return moduleRepository.count();
    }

    public long getTotalInscriptions() {
        return moduleRepository.findAll().stream()
                .mapToLong(module -> module.getEtudiants().size())
                .sum();
    }

    public List<Modules> getPopularModules() {
        return moduleRepository.findAll().stream()
                .sorted((m1, m2) -> Integer.compare(m2.getEtudiants().size(), m1.getEtudiants().size()))
                .limit(5)
                .collect(Collectors.toList());
    }
}
