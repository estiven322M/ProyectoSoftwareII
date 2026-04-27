package com.universidad.ecologistica.infraestructure.controller;

import org.springframework.web.bind.annotation.*;
import com.universidad.ecologistica.application.usecase.ProcesarControlBiometricoUseCase;
import com.universidad.ecologistica.domain.model.RegistroSalida;

@RestController
@RequestMapping("/api/control-salida")
public class ControlSalidaController {

    private final ProcesarControlBiometricoUseCase controlBiometricoUseCase;

    public ControlSalidaController(ProcesarControlBiometricoUseCase controlBiometricoUseCase) {
        this.controlBiometricoUseCase = controlBiometricoUseCase;
    }

    // Este endpoint recibe los datos y un booleano simulando si el hardware biométrico aprobó o rechazó la huella
    @PostMapping("/biometrico")
    public RegistroSalida procesarHuella(@RequestBody DatosControlRequest request) {
        return controlBiometricoUseCase.ejecutar(request.ordenId, request.conductorId, request.huellaValida);
    }

    // Clase auxiliar interna para mapear el JSON que se envía desde el cliente
    public static class DatosControlRequest {
        public String ordenId;
        public String conductorId;
        public boolean huellaValida;
    }
}
