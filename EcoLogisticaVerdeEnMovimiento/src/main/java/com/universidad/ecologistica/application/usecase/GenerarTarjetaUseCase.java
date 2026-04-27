package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.EstadoActa;
import com.universidad.ecologistica.domain.model.TarjetaBulto;
import com.universidad.ecologistica.domain.model.ActaConsolidacion;
import com.universidad.ecologistica.domain.repository.TarjetaBultoRepository;
import com.universidad.ecologistica.domain.repository.ActaRepository;
import java.util.UUID;

@Service
public class GenerarTarjetaUseCase {

    private final TarjetaBultoRepository tarjetaRepository;
    private final ActaRepository actaRepository; // Inyectamos para consultar el Acta (REQ-015)

    public GenerarTarjetaUseCase(TarjetaBultoRepository tarjetaRepository, ActaRepository actaRepository) {
        this.tarjetaRepository = tarjetaRepository;
        this.actaRepository = actaRepository;
    }

    public TarjetaBulto ejecutar(String actaId, String paqueteEcoetiqueta) {
        // REQ-015: Consultar y validar que el Acta esté "FIRMADA"
        ActaConsolidacion acta = actaRepository.buscarPorId(actaId)
                .orElseThrow(() -> new RuntimeException("Acta no encontrada."));

        if (acta.getEstado() != EstadoActa.FIRMADA) {
            throw new IllegalStateException("ERROR (REQ-015): No se puede generar la tarjeta. El Acta de Consolidación no está FIRMADA.");
        }

        // REQ-011: Generar la etiqueta si la validación pasó
        String idGenerado = "TB-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        TarjetaBulto nuevaTarjeta = TarjetaBulto.Factory.generar(idGenerado, actaId, paqueteEcoetiqueta);
        
        tarjetaRepository.guardar(nuevaTarjeta);
        return nuevaTarjeta;
    }
}
