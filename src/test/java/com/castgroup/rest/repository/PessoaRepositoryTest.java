package com.castgroup.rest.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.castgroup.rest.model.Pessoa;
import com.castgroup.rest.repository.PessoaRepository;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void buscarPessoaPorNome() throws  Exception {
        Optional<Pessoa> optional = pessoaRepository.findByNome("Sandro");

        Pessoa pessoa = optional.get();

        assertThat(pessoa.getId()).isEqualTo(1L);
        assertThat(pessoa.getNome()).isEqualTo("Sandro");
    }

    @Test
    public void listaPessoas() throws Exception {
        List<Pessoa> pessoas = pessoaRepository.findAllByOrderByNomeDesc();
        pessoas.forEach(p -> System.out.println("Pessoa " + p.getNome()));
        assertThat(pessoas.size()).isEqualTo(3);
    }

}
