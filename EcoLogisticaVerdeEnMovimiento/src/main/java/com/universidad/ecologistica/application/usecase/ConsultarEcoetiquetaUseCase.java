package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.Paquete;
import com.universidad.ecologistica.domain.repository.PaqueteRepository;

@Service
public class ConsultarEcoetiquetaUseCase {

    private final PaqueteRepository repository;

    public ConsultarEcoetiquetaUseCase(PaqueteRepository repository) {
        this.repository = repository;
    }

    public Paquete ejecutar(String ecoetiqueta) {
        return repository.buscarPorEcoetiqueta(ecoetiqueta)
                .orElseThrow(() -> new RuntimeException("Ecoetiqueta no válida o paquete no encontrado en el sistema."));
    }
}
