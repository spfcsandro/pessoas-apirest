package com.castgroup.rest.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.castgroup.rest.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	Pessoa findById(long Id);
	
	@Query("select p from Pessoa p where p.nome = :nome")
	Optional<Pessoa> findByNome(@Param("nome") String nome);
	
	List<Pessoa> findAllByOrderByNomeDesc();

}
