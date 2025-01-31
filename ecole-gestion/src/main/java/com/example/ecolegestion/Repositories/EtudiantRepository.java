package com.example.ecolegestion.Repositories;

import com.example.ecolegestion.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Corrected method name to match the field name in the entity (codeApoge)
    Optional<Etudiant> findByCodeApoge(String codeApoge);

    // The search method works as expected
    @Query("SELECT e FROM Etudiant e WHERE e.nom LIKE %:keyword% OR e.prenom LIKE %:keyword% OR e.codeApoge LIKE %:keyword%")
    List<Etudiant> search(@Param("keyword") String keyword);

}
