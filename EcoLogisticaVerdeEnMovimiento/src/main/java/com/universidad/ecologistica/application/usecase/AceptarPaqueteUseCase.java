package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.Paquete;
import com.universidad.ecologistica.domain.repository.PaqueteRepository;

@Service
public class AceptarPaqueteUseCase {

    private final PaqueteRepository repository;

    public AceptarPaqueteUseCase(PaqueteRepository repository) {
        this.repository = repository;
    }

    public void ejecutar(String ecoetiqueta) {
        Paquete paquete = repository.buscarPorEcoetiqueta(ecoetiqueta)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado con ecoetiqueta: " + ecoetiqueta));
        
        paquete.aceptar(); // Regla de negocio
        repository.guardar(paquete);
    }
}
