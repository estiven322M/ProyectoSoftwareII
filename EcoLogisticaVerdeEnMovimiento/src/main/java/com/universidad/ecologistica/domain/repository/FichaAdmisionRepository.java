package com.universidad.ecologistica.domain.repository;

import com.universidad.ecologistica.domain.model.FichaAdmision;

import java.util.List;
import java.util.Optional;

public interface FichaAdmisionRepository {
	
	void guardar(FichaAdmision ficha); // Usado en REQ-001 y REQ-003
    Optional<FichaAdmision> buscarPorId(String id); // Usado en REQ-002
    List<FichaAdmision> listarTodas();

}
