package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.TarjetaBulto;
import com.universidad.ecologistica.domain.repository.TarjetaBultoRepository;

@Service
public class ConfirmarEntregaTarjetaUseCase {

    private final TarjetaBultoRepository repository;

    public ConfirmarEntregaTarjetaUseCase(TarjetaBultoRepository repository) {
        this.repository = repository;
    }

    public void ejecutar(String tarjetaId) {
        TarjetaBulto tarjeta = repository.buscarPorId(tarjetaId)
                .orElseThrow(() -> new RuntimeException("Tarjeta de bulto no encontrada con ID: " + tarjetaId));
        
        tarjeta.confirmarEntrega();
        repository.guardar(tarjeta);
    }
}
