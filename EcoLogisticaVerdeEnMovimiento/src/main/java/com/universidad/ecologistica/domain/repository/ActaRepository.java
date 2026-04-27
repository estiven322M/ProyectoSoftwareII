package com.universidad.ecologistica.domain.repository;

import com.universidad.ecologistica.domain.model.ActaConsolidacion;

import java.util.List;
import java.util.Optional;

public interface ActaRepository {
	void guardar(ActaConsolidacion acta); // para los REQ-004 y REQ-005

	Optional<ActaConsolidacion> buscarPorId(String id); // para REQ-006
	List<ActaConsolidacion> listarTodas();

}
