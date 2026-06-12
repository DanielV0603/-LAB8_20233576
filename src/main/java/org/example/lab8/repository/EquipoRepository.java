package org.example.lab8.repository;

import org.example.lab8.entity.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Integer> {

    Optional<Equipo> findByTag(String tag);
}
