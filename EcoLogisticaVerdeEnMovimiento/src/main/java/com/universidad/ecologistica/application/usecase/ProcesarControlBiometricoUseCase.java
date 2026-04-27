package com.universidad.ecologistica.application.usecase;

import org.springframework.stereotype.Service;
import com.universidad.ecologistica.domain.model.EstadoOrden;
import com.universidad.ecologistica.domain.model.OrdenDespacho;
import com.universidad.ecologistica.domain.model.RegistroSalida;
import com.universidad.ecologistica.domain.repository.OrdenDespachoRepository;
import com.universidad.ecologistica.domain.repository.RegistroSalidaRepository;
import java.util.UUID;

@Service
public class ProcesarControlBiometricoUseCase {

    private final RegistroSalidaRepository registroRepository;
    private final OrdenDespachoRepository ordenRepository;

    public ProcesarControlBiometricoUseCase(RegistroSalidaRepository registroRepository, OrdenDespachoRepository ordenRepository) {
        this.registroRepository = registroRepository;
        this.ordenRepository = ordenRepository;
    }

    // El parámetro huellaValida simula la respuesta física de la máquina biométrica
    public RegistroSalida ejecutar(String ordenId, String conductorId, boolean huellaValida) {
        
        // REQ-017: Buscar y validar que la Orden esté "Aprobada"
        OrdenDespacho orden = ordenRepository.buscarPorId(ordenId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada."));

        if (orden.getEstado() == EstadoOrden.CREADA) {
            throw new IllegalStateException("ERROR (REQ-017): La orden no ha sido aprobada. No se puede procesar la salida.");
        }

        String idGenerado = "REG-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
        RegistroSalida registro;

        // Simulamos la lógica del lector de huellas
        if (huellaValida) {
            // REQ-012: Crear Registro Oficial (Biométrico exitoso)
            registro = RegistroSalida.Factory.registrarExito(idGenerado, ordenId, conductorId);
            
            // Opcional: Cambiar estado de la orden a "SALIDA_AUTORIZADA"
            orden.autorizarSalida();
            ordenRepository.guardar(orden);
        } else {
            // REQ-018: Registrar Falla Biométrica en la bitácora
            registro = RegistroSalida.Factory.registrarFallo(idGenerado, ordenId, conductorId);
        }

        registroRepository.guardar(registro);
        return registro;
    }
}
