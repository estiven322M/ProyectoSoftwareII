package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.ActaConsolidacion;
import com.universidad.ecologistica.domain.repository.ActaRepository;

@Service
public class RegistrarFirmaActaUseCase {
	
	private final ActaRepository repository;

    public RegistrarFirmaActaUseCase(ActaRepository repository) {
        this.repository = repository;
    }

    public void ejecutar(String actaId) {
        ActaConsolidacion acta = repository.buscarPorId(actaId)
                .orElseThrow(() -> new RuntimeException("Acta no encontrada con ID: " + actaId));
        
        acta.registrarFirma(); // Delega la responsabilidad al dominio (State Pattern)
        repository.guardar(acta);
    }

}
