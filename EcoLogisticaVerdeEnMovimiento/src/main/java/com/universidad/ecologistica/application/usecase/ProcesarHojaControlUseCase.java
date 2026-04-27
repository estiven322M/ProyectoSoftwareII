package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.HojaControl;
import com.universidad.ecologistica.domain.repository.HojaControlRepository;

@Service
public class ProcesarHojaControlUseCase {

    private final HojaControlRepository repository;

    public ProcesarHojaControlUseCase(HojaControlRepository repository) {
        this.repository = repository;
    }

    // Para REQ-020
    public void aceptar(String hojaId) {
        HojaControl hoja = repository.buscarPorId(hojaId)
                .orElseThrow(() -> new RuntimeException("Hoja de Control no encontrada."));
        hoja.aceptarPorCliente();
        repository.guardar(hoja);
    }

    // Para REQ-021
    public void rechazar(String hojaId, String motivo) {
        HojaControl hoja = repository.buscarPorId(hojaId)
                .orElseThrow(() -> new RuntimeException("Hoja de Control no encontrada."));
        hoja.rechazar(motivo);
        repository.guardar(hoja);
    }
}
