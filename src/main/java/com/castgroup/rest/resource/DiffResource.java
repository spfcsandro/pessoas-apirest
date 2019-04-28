package com.castgroup.rest.resource;

import org.apache.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.rest.model.Diff;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/v1/diff")
@Api(value="API REST Diff Base 64")
public class DiffResource {
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/left", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="LEFT")
//	public ResponseEntity<Diff> diffLeft(@RequestBody Diff diff, @PathVariable(value="id") long id) {
	public void diffLeft(@PathVariable(value="id") long id) {
		System.out.println(id);
		
	}
	
	/*@RequestMapping(method=RequestMethod.POST, value="/send", consumes=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receiveData(@RequestBody DiffDTO) {

        System.out.println(pessoaDto);

        // ...
        return ResponseEntity.ok("Deu certo!");
    }*/
	
	@PostMapping("/{id}/right")
	public void  diffRight() {
		
	}

}
