package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.HojaControl;
import com.universidad.ecologistica.domain.repository.HojaControlRepository;
import java.util.UUID;

@Service
public class CrearHojaControlUseCase {

    private final HojaControlRepository repository;

    public CrearHojaControlUseCase(HojaControlRepository repository) {
        this.repository = repository;
    }

    public HojaControl ejecutar(String paqueteEcoetiqueta, double peso, String dimensiones) {
        String idGenerado = "HC-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        
        HojaControl nuevaHoja = HojaControl.Factory.crear(idGenerado, paqueteEcoetiqueta, peso, dimensiones);
        repository.guardar(nuevaHoja);
        
        return nuevaHoja;
    }
}
