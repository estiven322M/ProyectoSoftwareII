package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.stereotype.Component;
import com.universidad.ecologistica.domain.model.Paquete;
import com.universidad.ecologistica.domain.repository.PaqueteRepository;
import java.util.Optional;

@Component
public class PaqueteRepositoryImpl implements PaqueteRepository {

    private final JpaPaqueteRepository jpaRepository;

    public PaqueteRepositoryImpl(JpaPaqueteRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(Paquete paquete) {
        jpaRepository.save(paquete);
    }

    @Override
    public Optional<Paquete> buscarPorEcoetiqueta(String ecoetiqueta) {
        return jpaRepository.findById(ecoetiqueta);
    }
}
