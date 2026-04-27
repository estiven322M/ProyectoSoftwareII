package com.universidad.ecologistica.domain.repository;

import com.universidad.ecologistica.domain.model.Paquete;
import java.util.Optional;

public interface PaqueteRepository {
    void guardar(Paquete paquete); 
    Optional<Paquete> buscarPorEcoetiqueta(String ecoetiqueta); // Crucial para REQ-013
}
