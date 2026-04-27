package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.stereotype.Component;
import com.universidad.ecologistica.domain.model.OrdenDespacho;
import com.universidad.ecologistica.domain.repository.OrdenDespachoRepository;
import java.util.Optional;

@Component
public class OrdenDespachoRepositoryImpl implements OrdenDespachoRepository {

    private final JpaOrdenDespachoRepository jpaRepository;

    public OrdenDespachoRepositoryImpl(JpaOrdenDespachoRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(OrdenDespacho orden) {
        jpaRepository.save(orden);
    }

    @Override
    public Optional<OrdenDespacho> buscarPorId(String id) {
        return jpaRepository.findById(id);
    }
}
