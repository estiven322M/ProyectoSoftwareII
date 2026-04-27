package com.universidad.ecologistica.infraestructure.persistence;



import org.springframework.stereotype.Component;
import com.universidad.ecologistica.domain.model.FichaAdmision;
import com.universidad.ecologistica.domain.repository.FichaAdmisionRepository;
import java.util.Optional;

@Component // crear objeto de la clase automaticamente
public class FichaAdmisionRepositoryImpl implements FichaAdmisionRepository {

    private final JpaFichaRepository jpaRepository;

    public FichaAdmisionRepositoryImpl(JpaFichaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public void guardar(FichaAdmision ficha) {
        jpaRepository.save(ficha); // guarda SpringData
    }

    @Override
    public Optional<FichaAdmision> buscarPorId(String id) {
        return jpaRepository.findById(id); // busqueda a SpringData
    }
}