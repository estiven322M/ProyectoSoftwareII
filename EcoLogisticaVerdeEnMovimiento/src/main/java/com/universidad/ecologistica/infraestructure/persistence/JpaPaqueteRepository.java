package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.universidad.ecologistica.domain.model.Paquete;

@Repository
public interface JpaPaqueteRepository extends JpaRepository<Paquete, String> {
}
