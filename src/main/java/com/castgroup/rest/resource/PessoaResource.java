package com.castgroup.rest.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.rest.exception.ValidationException;
import com.castgroup.rest.model.Pessoa;
import com.castgroup.rest.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/rest")
@Api(value="API REST Pessoas")
@CrossOrigin(origins="*")
public class PessoaResource extends AbstractResource {
	
	@Autowired
	PessoaService pessoaService;
	
	@GetMapping("/pessoas")
	@ApiOperation(value="Retorna lista de pessoas")
	public ResponseEntity<List<Pessoa>> listaPessoas(){
        return new ResponseEntity<>(pessoaService.pessoas(),HttpStatus.OK);
	}
	
	@GetMapping("/pessoa/{id}")
	@ApiOperation(value="Retorna uma pessoa por Id")
	public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable(value="id") long id ){
        return new ResponseEntity<>(pessoaService.buscarPessoaById(id), HttpStatus.OK);
	}
	
	@GetMapping("/pessoa/nome/{nome}")
	@ApiOperation(value="Retorna uma pessoa por Nome")
    public ResponseEntity<Pessoa> buscarPessoaPorNome(@PathVariable("nome") String nome) {
        return new ResponseEntity<>(pessoaService.buscarPessoaByNome(nome), HttpStatus.OK);
    }
	
	@PostMapping("/pessoa/save")
	@ApiOperation(value="Salva um Pessoa")
	public ResponseEntity<Pessoa> salvaPessoa(@RequestBody @Valid Pessoa pessoa, HttpServletResponse response) throws ValidationException{
		Pessoa entidade = pessoaService.save(pessoa);
		adcionaHeaderLocation(entidade.getId(),response,"SALVAR");
	    return new ResponseEntity<>(entidade, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/pessoa/remove/{id}")
	@ApiOperation(value="Deleta um pessoa")
	public void deletePessoa(@PathVariable(value="id") long id ) {
		pessoaService.deletePessoa(pessoaService.buscarPessoaById(id));
	}
	
	@PutMapping("/pessoa/save/{id}")
	@ApiOperation(value="Atualiza um pessoa")
	public ResponseEntity<Pessoa> atualizaPessoa(@RequestBody @Valid Pessoa pessoa, @PathVariable(value="id") long id, 
			HttpServletResponse response) throws ValidationException {
		pessoa.setId(id);
		Pessoa entidade = pessoaService.save(pessoa);
		adcionaHeaderLocation(entidade.getId(),response,"ATUALIZAR");
        return new ResponseEntity<>(entidade, HttpStatus.CREATED);
	}
		
}
