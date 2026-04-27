package com.universidad.ecologistica.infraestructure.controller;

import org.springframework.web.bind.annotation.*;
import com.universidad.ecologistica.application.usecase.*;
import com.universidad.ecologistica.domain.model.Paquete;
import com.universidad.ecologistica.domain.repository.PaqueteRepository;
import java.util.Map;

@RestController
@RequestMapping("/api/paquetes")
public class PaqueteController {

    private final ConsultarEcoetiquetaUseCase consultarUseCase;
    private final AceptarPaqueteUseCase aceptarUseCase;
    private final RechazarPaqueteUseCase rechazarUseCase;
    private final PaqueteRepository repository; 

    public PaqueteController(ConsultarEcoetiquetaUseCase consultarUseCase,
                             AceptarPaqueteUseCase aceptarUseCase,
                             RechazarPaqueteUseCase rechazarUseCase,
                             PaqueteRepository repository) {
        this.consultarUseCase = consultarUseCase;
        this.aceptarUseCase = aceptarUseCase;
        this.rechazarUseCase = rechazarUseCase;
        this.repository = repository;
    }

    // Ruta auxiliar para inyectar paquetes de prueba antes de verificarlos
    @PostMapping("/registrar-prueba")
    public Paquete registrarPrueba(@RequestBody Map<String, String> body) {
        Paquete nuevo = Paquete.Factory.registrarEnSistema(body.get("ecoetiqueta"));
        repository.guardar(nuevo);
        return nuevo;
    }

    // REQ-013: Consultar Ecoetiqueta
    @GetMapping("/{ecoetiqueta}")
    public Paquete consultar(@PathVariable String ecoetiqueta) {
        return consultarUseCase.ejecutar(ecoetiqueta);
    }

    // REQ-010: Registrar Verificación de Paquete (Aceptar)
    @PatchMapping("/{ecoetiqueta}/aceptar")
    public String aceptar(@PathVariable String ecoetiqueta) {
        aceptarUseCase.ejecutar(ecoetiqueta);
        return "Paquete con ecoetiqueta [" + ecoetiqueta + "] verificado y ACEPTADO correctamente.";
    }

    // REQ-014: Registrar Bodega Paquete (Rechazo)
    @PatchMapping("/{ecoetiqueta}/rechazar")
    public String rechazar(@PathVariable String ecoetiqueta, @RequestBody Map<String, String> body) {
        String motivo = body.get("motivo");
        rechazarUseCase.ejecutar(ecoetiqueta, motivo);
        return "Paquete con ecoetiqueta [" + ecoetiqueta + "] RECHAZADO. Motivo: " + motivo;
    }
}
