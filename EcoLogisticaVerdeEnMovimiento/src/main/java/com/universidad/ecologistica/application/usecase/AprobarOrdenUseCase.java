package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.OrdenDespacho;
import com.universidad.ecologistica.domain.repository.OrdenDespachoRepository;

@Service
public class AprobarOrdenUseCase {

    private final OrdenDespachoRepository repository;

    public AprobarOrdenUseCase(OrdenDespachoRepository repository) {
        this.repository = repository;
    }

    public void ejecutar(String ordenId) {
        OrdenDespacho orden = repository.buscarPorId(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada con ID: " + ordenId));
        
        orden.aprobarPorCliente(); // Se aplica la regla de negocio del Dominio
        repository.guardar(orden);
    }
}
