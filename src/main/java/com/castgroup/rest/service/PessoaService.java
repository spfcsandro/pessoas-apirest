package com.castgroup.rest.service;

import java.util.List;

import com.castgroup.rest.exception.ValidationException;
import com.castgroup.rest.model.Pessoa;



public interface PessoaService {

	Pessoa save(Pessoa pessoa) throws ValidationException;

    Pessoa buscarPessoaByNome(String nome);

    List<Pessoa> pessoas();

    Pessoa buscarPessoaById(Long id);

    void deletePessoa(Pessoa pessoa);
}
