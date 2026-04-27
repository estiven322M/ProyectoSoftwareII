package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.ActaConsolidacion;
import com.universidad.ecologistica.domain.repository.ActaRepository;
import java.util.UUID;

@Service
public class CrearActaUseCase {
	
	private final ActaRepository repository;

    public CrearActaUseCase(ActaRepository repository) {
        this.repository = repository;
    }

    public ActaConsolidacion ejecutar(String clienteId) {
        String idGenerado = "ACT-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        
        ActaConsolidacion nuevaActa = ActaConsolidacion.Factory.crearNuevaActa(idGenerado, clienteId);
        repository.guardar(nuevaActa);
        
        return nuevaActa;
    }

}
