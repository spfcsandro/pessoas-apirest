package com.castgroup.rest.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.rest.models.Pessoa;
import com.castgroup.rest.repository.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/rest")
@Api(value="API REST Pessoas")
@CrossOrigin(origins="*")
public class PessoaResource {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	@GetMapping("/pessoas")
	@ApiOperation(value="Retorna lista de pessoas")
	public List<Pessoa> listaPessoas(){
		return pessoaRepository.findAll();
	}
	
	@GetMapping("/pessoas/{id}")
	@ApiOperation(value="Retorna uma pessoa")
	public Pessoa listaPessoaUnico(@PathVariable(value="id") long id ){
		return pessoaRepository.findById(id);
	}
	
	@PostMapping("/pessoa")
	@ApiOperation(value="Salva um Pessoa")
	public Pessoa salvaPessoa(@RequestBody @Valid Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}
	
	@DeleteMapping("/pessoa")
	@ApiOperation(value="Deleta um pessoa")
	public void deletePessoa(@RequestBody @Valid Pessoa pessoa) {
		pessoaRepository.delete(pessoa);
	}
	
	@PutMapping("/pessoa")
	@ApiOperation(value="Atualiza um pessoa")
	public void atualizaPessoa(@RequestBody @Valid Pessoa pessoa) {
		pessoaRepository.save(pessoa);
	}
	
	
}
