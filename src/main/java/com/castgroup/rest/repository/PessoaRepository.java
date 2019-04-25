package com.castgroup.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castgroup.rest.models.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{
	
	Pessoa findById(long Id);

}
