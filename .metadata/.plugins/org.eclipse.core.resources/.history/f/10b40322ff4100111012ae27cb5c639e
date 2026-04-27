package com.universidad.ecologistica.infraestructure.persistence;

import org.springframework.stereotype.Component;
import com.universidad.ecologistica.domain.model.ActaConsolidacion;
import com.universidad.ecologistica.domain.repository.ActaRepository;
import java.util.Optional;

@Component
public class ActaRepositoryImpl implements ActaRepository {
	
	private final JpaActaRepository jpaRepository;

    public ActaRepositoryImpl(JpaActaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(ActaConsolidacion acta) {
        jpaRepository.save(acta);
    }

    @Override
    public Optional<ActaConsolidacion> buscarPorId(String id) {
        return jpaRepository.findById(id);
    }

}
