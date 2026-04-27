package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.Paquete;
import com.universidad.ecologistica.domain.repository.PaqueteRepository;

@Service
public class RechazarPaqueteUseCase {

    private final PaqueteRepository repository;

    public RechazarPaqueteUseCase(PaqueteRepository repository) {
        this.repository = repository;
    }

    public void ejecutar(String ecoetiqueta, String motivo) {
        Paquete paquete = repository.buscarPorEcoetiqueta(ecoetiqueta)
                .orElseThrow(() -> new RuntimeException("Paquete no encontrado con ecoetiqueta: " + ecoetiqueta));
        
        paquete.rechazar(motivo); // Regla de negocio que exige motivo
        repository.guardar(paquete);
    }
}
