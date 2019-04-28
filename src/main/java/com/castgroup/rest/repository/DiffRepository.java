package com.castgroup.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castgroup.rest.enuns.TipoDiff;
import com.castgroup.rest.model.Diff;

public interface DiffRepository extends JpaRepository<Diff, Long>{
	
	Diff findById(long Id);
	
	Optional<Diff> findByCodigoHashAndTipoDiff(long codigoHash, TipoDiff tipoDiff);
	
}
