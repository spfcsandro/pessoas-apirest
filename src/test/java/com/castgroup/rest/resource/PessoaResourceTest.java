package com.castgroup.rest.resource;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.castgroup.rest.RestApplicationTests;
import com.castgroup.rest.model.Pessoa;

import io.restassured.http.ContentType;

public class PessoaResourceTest extends RestApplicationTests {

	@Test
    public void listaPessoas() throws Exception {
        given()
                .get("/rest/pessoas")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value());
    }
	
	
	@Test
    public void buscarPessoaPorNome () throws Exception {
        given()
                .pathParam("nome", "Sandro")
                .get("/rest/pessoa/nome/{nome}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())
                .body("nome", equalTo("Sandro"));
    }


    @Test
    public void salvaPessoa() throws Exception {

        Pessoa pessoa = new Pessoa();
        pessoa.setId(0);
        pessoa.setNome("Antonio");
        pessoa.setBairro("Carajas");
        pessoa.setCelular("8299998888");
        pessoa.setCidade("Cataguases");
        pessoa.setEstado("Minas Gerais");
        pessoa.setNumero("420");
        pessoa.setRua("Jacinto da Silva");
        pessoa.setTelefone("8233224455");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(pessoa)
                .when()
                .post("/rest/pessoa/save")
                .then()
                .log().headers()
                .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.CREATED.value())
                .header("Location", equalTo("http://localhost:"+port+"/rest/pessoa/save/4"))
                .body("nome",       equalTo("Antonio"),
                	  "bairro",     equalTo("Carajas"),
                	  "celular",    equalTo("8299998888"),
                	  "cidade",     equalTo("Cataguases"),
                	  "estado",     equalTo("Minas Gerais"),
                	  "numero",     equalTo("420"),
                	  "rua",        equalTo("Jacinto da Silva"),
                	  "telefone",   equalTo("8233224455"));
    }
    
   @Test
    public void atualizaPessoa() throws Exception {
    	
    	Pessoa pessoa = new Pessoa();
    	pessoa.setId(1);
    	pessoa.setNome("Fabricio2");
    	pessoa.setBairro("Carajas");
    	pessoa.setCelular("8299998888");
    	pessoa.setCidade("Cataguases");
    	pessoa.setEstado("Minas Gerais");
    	pessoa.setNumero("420");
    	pessoa.setRua("Jacinto da Silva");
    	pessoa.setTelefone("8233224455");
    	
    	given()
    	.request()
    	.header("Accept", ContentType.ANY)
    	.header("Content-type", ContentType.JSON)
    	.body(pessoa)
    	.when()
    	.put("/rest/pessoa/save/1")
    	.then()
    	.log().headers()
    	.and()
    	.log().body()
    	.and()
    	.statusCode(HttpStatus.CREATED.value())
    	.header("Location", equalTo("http://localhost:"+port+"/rest/pessoa/save/1"))
    	.body(  "nome",       equalTo("Fabricio2"),
    			"bairro",     equalTo("Carajas"),
    			"celular",    equalTo("8299998888"),
    			"cidade",     equalTo("Cataguases"),
    			"estado",     equalTo("Minas Gerais"),
    			"numero",     equalTo("420"),
    			"rua",        equalTo("Jacinto da Silva"),
    			"telefone",   equalTo("8233224455"));
    }

    @Test
    public void salvaPessoaSemNome() throws Exception {

    	Pessoa pessoa = new Pessoa();
        pessoa.setNome("");
        pessoa.setBairro("Carajas");
        pessoa.setCelular("8299998888");
        pessoa.setCidade("Cataguases");
        pessoa.setEstado("Minas Gerais");
        pessoa.setNumero("420");
        pessoa.setRua("Jacinto da Silva");
        pessoa.setTelefone("8233224455");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .body(pessoa)
                .when()
                .post("/rest/pessoa/save")
                .then()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("error", equalTo("Bad Request"));
        	//	.body("errors.defaultMessage", equalTo("Por favor, digite um nome!"));
    }

    @Test
    public void deletePessoa() throws Exception {

        given() 
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-type", ContentType.JSON)
                .pathParam("id", 1)
                .when()
                .delete("/rest/pessoa/remove/{id}")
                .then()
                .log().headers()
                .and()
                .log().body()
                .statusCode(HttpStatus.OK.value());
    }

}
