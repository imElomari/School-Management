package com.example.ecolegestion.Repositories;

import com.example.ecolegestion.Entities.Modules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ModuleRepository extends JpaRepository<Modules, Long> {
}
