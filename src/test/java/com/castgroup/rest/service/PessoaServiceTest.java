package com.castgroup.rest.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.castgroup.rest.exception.ValidationException;
import com.castgroup.rest.model.Pessoa;
import com.castgroup.rest.repository.PessoaRepository;
import com.castgroup.rest.service.PessoaService;
import com.castgroup.rest.service.impl.PessoaServiceImpl;

@RunWith(SpringRunner.class)
public class PessoaServiceTest {

	private final static String PLAYER_NAME = "Sandro";

	@MockBean
	private PessoaRepository pessoaRepository;

	private PessoaService pessoaService;

	private Pessoa pessoa;

	@Before
	public void setUp() throws Exception {
		pessoaService = new PessoaServiceImpl(pessoaRepository);

		pessoa = new Pessoa();
		pessoa.setId(1L);
		pessoa.setNome(PLAYER_NAME);

		when(pessoaRepository.findByNome(PLAYER_NAME)).thenReturn(Optional.empty());
	}

	@Test
	public void salvarPessoa() throws ValidationException {
		Pessoa newPessoa = new Pessoa();
		newPessoa.setNome("Joao");
		newPessoa.setBairro("Carajas");
		newPessoa.setCelular("8299998888");
		newPessoa.setCidade("Cataguases");
		newPessoa.setEstado("Minas Gerais");
		newPessoa.setNumero("420");
		newPessoa.setRua("Jacinto da Silva");
		newPessoa.setTelefone("8233224455");

		pessoaService.save(newPessoa);

		verify(pessoaRepository).save(newPessoa);
	}

	@Test(expected = ValidationException.class)
	public void salvarPessoaComCamposNull() throws Exception {
		Pessoa pessoa = null;
		pessoaService.save(pessoa);
	}
	
	@Test(expected = ValidationException.class)
	public void salvarPessoaComNomeVazio() throws Exception {
		Pessoa pessoa = new Pessoa();
        pessoa.setNome("");
        pessoa.setBairro("Carajas");
        pessoa.setCelular("8299998888");
        pessoa.setCidade("Cataguases");
        pessoa.setEstado("Minas Gerais");
        pessoa.setNumero("420");
        pessoa.setRua("Jacinto da Silva");
        pessoa.setTelefone("8233224455");
		pessoaService.save(pessoa);
	}

	@Test(expected = ValidationException.class)
	public void atualizarPessoaComNomeVazio() throws Exception {
		when(pessoaRepository.findById(1L)).thenReturn(pessoa);
		pessoa.setId(1);
		pessoa.setNome("");

		pessoaService.save(pessoa);
		verify(pessoaRepository).save(pessoa);
	}

}
