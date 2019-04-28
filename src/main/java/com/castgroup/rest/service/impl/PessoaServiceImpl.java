package com.castgroup.rest.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castgroup.rest.exception.ValidationException;
import com.castgroup.rest.model.Pessoa;
import com.castgroup.rest.repository.PessoaRepository;
import com.castgroup.rest.service.PessoaService;
import com.castgroup.rest.util.MessageUtil;
import com.castgroup.rest.util.ObjectUtil;

@Service
public class PessoaServiceImpl implements PessoaService {

	
	@Autowired
    private PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
    
    private void validacao(Pessoa pessoa) throws ValidationException {
    	validarNomeNuloOuVazio(pessoa);
    }

    private void validarNomeNuloOuVazio(Pessoa pessoa) throws ValidationException {

        if (ObjectUtil.isObjectNull(pessoa) ||
        		!ObjectUtil.verifyNullOrEmptyObjects(pessoa.getNome())){
            throw new ValidationException(MessageUtil.PESSOA_NAO_ENCONTRADA);
        }
    }

    public Pessoa save(Pessoa pessoa) throws ValidationException {
        validacao(pessoa);
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPessoaByNome(String nome) {
        Optional<Pessoa> pessoaOptional = pessoaRepository.findByNome(nome);
        return pessoaOptional.orElse(null);
    }

    public List<Pessoa> pessoas() {
        return pessoaRepository.findAllByOrderByNomeDesc();
    }

    public Pessoa buscarPessoaById(Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);
        return pessoa.orElse(null);
    }

    public void deletePessoa(Pessoa pessoa){
        pessoaRepository.delete(pessoaRepository.findById(pessoa.getId()));
    }
}
