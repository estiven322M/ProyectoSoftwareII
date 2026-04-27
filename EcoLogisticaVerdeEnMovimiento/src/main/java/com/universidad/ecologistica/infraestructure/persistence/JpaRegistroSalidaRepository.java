package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.universidad.ecologistica.domain.model.RegistroSalida;

@Repository
public interface JpaRegistroSalidaRepository extends JpaRepository<RegistroSalida, String> {
}
