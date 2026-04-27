package com.universidad.ecologistica.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class HojaControl {

    @Id
    private String id;
    
    private String paqueteEcoetiqueta;
    private double peso;
    private String dimensiones;
    
    @Enumerated(EnumType.STRING)
    private EstadoHojaControl estado;
    
    private String observaciones;
    private LocalDateTime fechaCreacion;

    protected HojaControl() {}

    private HojaControl(String id, String paqueteEcoetiqueta, double peso, String dimensiones) {
        this.id = id;
        this.paqueteEcoetiqueta = paqueteEcoetiqueta;
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.estado = EstadoHojaControl.PENDIENTE;
        this.fechaCreacion = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getPaqueteEcoetiqueta() { return paqueteEcoetiqueta; }
    public double getPeso() { return peso; }
    public String getDimensiones() { return dimensiones; }
    public EstadoHojaControl getEstado() { return estado; }
    public String getObservaciones() { return observaciones; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    // REQ-020: Aceptar Hoja de Control
    public void aceptarPorCliente() {
        if (this.estado != EstadoHojaControl.PENDIENTE) {
            throw new IllegalStateException("Solo se pueden aceptar hojas de control que estén en estado PENDIENTE.");
        }
        this.estado = EstadoHojaControl.ACEPTADA_POR_CLIENTE;
        this.observaciones = "Inspección aceptada por el cliente.";
    }

    // REQ-021: Registrar Cargas Rechazadas
    public void rechazar(String motivo) {
        if (this.estado != EstadoHojaControl.PENDIENTE) {
            throw new IllegalStateException("La hoja de control ya fue procesada.");
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            throw new IllegalArgumentException("Es OBLIGATORIO proveer un motivo (inconsistencia) para rechazar la carga.");
        }
        this.estado = EstadoHojaControl.RECHAZADA;
        this.observaciones = "CARGA RECHAZADA: " + motivo;
    }

    // Factory Method (REQ-019)
    public static class Factory {
        public static HojaControl crear(String id, String paqueteId, double peso, String dimensiones) {
            if (peso <= 0) {
                throw new IllegalArgumentException("El peso debe ser mayor a 0.");
            }
            if (dimensiones == null || dimensiones.isEmpty()) {
                throw new IllegalArgumentException("Las dimensiones son obligatorias.");
            }
            return new HojaControl(id, paqueteId, peso, dimensiones);
        }
    }
}