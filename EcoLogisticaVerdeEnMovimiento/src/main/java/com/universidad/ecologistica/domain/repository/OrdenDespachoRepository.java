package com.universidad.ecologistica.domain.repository;

import com.universidad.ecologistica.domain.model.OrdenDespacho;
import java.util.Optional;

public interface OrdenDespachoRepository {
    void guardar(OrdenDespacho orden); 
    Optional<OrdenDespacho> buscarPorId(String id); 
}