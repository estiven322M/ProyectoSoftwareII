package com.universidad.ecologistica.domain.model;
import com.universidad.ecologistica.domain.model.EstadoOrden;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class OrdenDespacho {

    @Id
    private String id;
    
    private String actaConsolidacionId; // Vinculamos la orden con la carga verificada
    private String rutaAsignada;
    
    @Enumerated(EnumType.STRING)
    private EstadoOrden estado;
    
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAprobacion;

    // Requisito de JPA
    protected OrdenDespacho() {}

    // Constructor privado (Factory)
    private OrdenDespacho(String id, String actaConsolidacionId, String rutaAsignada) {
        this.id = id;
        this.actaConsolidacionId = actaConsolidacionId;
        this.rutaAsignada = rutaAsignada;
        this.estado = EstadoOrden.CREADA;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters
    public String getId() { return id; }
    public String getActaConsolidacionId() { return actaConsolidacionId; }
    public String getRutaAsignada() { return rutaAsignada; }
    public EstadoOrden getEstado() { return estado; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public LocalDateTime getFechaAprobacion() { return fechaAprobacion; }

    // Comportamiento para REQ-008: Actualizar Estado (Aprobación del cliente)
    public void aprobarPorCliente() {
        if (this.estado != EstadoOrden.CREADA) {
            throw new IllegalStateException("La orden solo puede ser aprobada si está en estado CREADA.");
        }
        this.estado = EstadoOrden.APROBADA_POR_CLIENTE;
        this.fechaAprobacion = LocalDateTime.now();
    }

    // Comportamiento para cuando el coordinador autoriza la salida
    public void autorizarSalida() {
        if (this.estado != EstadoOrden.APROBADA_POR_CLIENTE) {
            throw new IllegalStateException("No se puede autorizar la salida sin la aprobación previa del cliente.");
        }
        this.estado = EstadoOrden.SALIDA_AUTORIZADA;
    }

    // Factory Method para REQ-007
    public static class Factory {
        public static OrdenDespacho crearNuevaOrden(String id, String actaId, String ruta) {
            if (actaId == null || actaId.isEmpty() || ruta == null || ruta.isEmpty()) {
                throw new IllegalArgumentException("El Acta y la Ruta son obligatorias para crear la Orden de Despacho.");
            }
            return new OrdenDespacho(id, actaId, ruta);
        }
    }
}