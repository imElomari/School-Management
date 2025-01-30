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
    Optional<Etudiant> findByCodeEpoge(String codeEpoge);

    @Query("SELECT e FROM Etudiant e WHERE e.nom LIKE %:keyword% OR e.prenom LIKE %:keyword% OR e.codeEpoge LIKE %:keyword%")
    List<Etudiant> search(@Param("keyword") String keyword);

}
