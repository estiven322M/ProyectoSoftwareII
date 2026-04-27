package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.stereotype.Component;
import com.universidad.ecologistica.domain.model.RegistroSalida;
import com.universidad.ecologistica.domain.repository.RegistroSalidaRepository;

@Component
public class RegistroSalidaRepositoryImpl implements RegistroSalidaRepository {

    private final JpaRegistroSalidaRepository jpaRepository;

    public RegistroSalidaRepositoryImpl(JpaRegistroSalidaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(RegistroSalida registro) {
        jpaRepository.save(registro);
    }
}