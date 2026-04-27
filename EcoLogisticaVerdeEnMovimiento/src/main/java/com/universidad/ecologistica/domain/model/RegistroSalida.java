package com.universidad.ecologistica.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class RegistroSalida {

    @Id
    private String id;
    
    private String ordenDespachoId;
    private String conductorId; // DNI o ID del conductor
    
    @Enumerated(EnumType.STRING)
    private EstadoBiometrico estado;
    
    private String observaciones; // Aquí guardaremos la incidencia si falla
    private LocalDateTime fechaRegistro;

    protected RegistroSalida() {}

    private RegistroSalida(String id, String ordenDespachoId, String conductorId, EstadoBiometrico estado, String observaciones) {
        this.id = id;
        this.ordenDespachoId = ordenDespachoId;
        this.conductorId = conductorId;
        this.estado = estado;
        this.observaciones = observaciones;
        this.fechaRegistro = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getOrdenDespachoId() { return ordenDespachoId; }
    public String getConductorId() { return conductorId; }
    public EstadoBiometrico getEstado() { return estado; }
    public String getObservaciones() { return observaciones; }
    public LocalDateTime getFechaRegistro() { return fechaRegistro; }

    // Factory Methods para manejar los dos escenarios (REQ-012 y REQ-018)
    public static class Factory {
        public static RegistroSalida registrarExito(String id, String ordenId, String conductorId) {
            return new RegistroSalida(id, ordenId, conductorId, EstadoBiometrico.EXITOSO, "Salida autorizada por biometría.");
        }

        public static RegistroSalida registrarFallo(String id, String ordenId, String conductorId) {
            return new RegistroSalida(id, ordenId, conductorId, EstadoBiometrico.FALLIDO, "INCIDENCIA: Huella no válida en control biométrico.");
        }
    }
}
