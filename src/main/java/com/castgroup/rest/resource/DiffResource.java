package com.castgroup.rest.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.castgroup.rest.dto.DiffDTO;
import com.castgroup.rest.enuns.TipoDiff;
import com.castgroup.rest.exception.ValidationException;
import com.castgroup.rest.model.Diff;
import com.castgroup.rest.service.DiffService;
import com.castgroup.rest.util.ObjectUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/v1/diff")
@Api(value="API REST Diff Base 64")
public class DiffResource extends AbstractResource{
	
	
	@Autowired
	DiffService diffService;
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/left", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="LEFT")
	public ResponseEntity<DiffDTO> diffLeft(@RequestBody DiffDTO diffDTO, @PathVariable(value="id") long codigoHash, 
			HttpServletResponse response) throws ValidationException {
		
		Diff diff = new Diff();
		diff.setCodigoHash(codigoHash);
		diff.setHash(diffDTO.getHash());
		diff.setTipoDiff(TipoDiff.LEFT);
		Diff diffLeft = diffService.buscarDiffByCodigoHashAndTipoDiff(codigoHash, TipoDiff.LEFT);
		Diff entidade = diffService.save(diff);
		
		if(ObjectUtil.isObjectNull(diffLeft)){
			adcionaHeaderLocation(entidade.getId(),response,"SALVAR");
		}else {
			adcionaHeaderLocation(entidade.getId(),response,"ATUALIZAR");
		}
	    return new ResponseEntity<>(diffDTO, HttpStatus.CREATED);
		
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}/right", consumes=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value="RIGHT")
	public ResponseEntity<DiffDTO> diffRight(@RequestBody DiffDTO diffDTO, @PathVariable(value="id") long codigoHash, 
			HttpServletResponse response) throws ValidationException {
		
		Diff diff = new Diff();
		diff.setCodigoHash(codigoHash);
		diff.setHash(diffDTO.getHash());
		diff.setTipoDiff(TipoDiff.RIGHT);
		Diff diffRight = diffService.buscarDiffByCodigoHashAndTipoDiff(codigoHash, TipoDiff.RIGHT);
		Diff entidade = diffService.save(diff);
		
		if(ObjectUtil.isObjectNull(diffRight)){
			adcionaHeaderLocation(entidade.getId(),response,"SALVAR");
		}else {
			adcionaHeaderLocation(entidade.getId(),response,"ATUALIZAR");
		}
		
	    return new ResponseEntity<>(diffDTO, HttpStatus.CREATED);
		
		//return ResponseEntity.ok().body(diffDTO);
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/{id}", consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DiffDTO> diff(@PathVariable(value="id") long codigoHash) {
		
		Diff diffLeft = diffService.buscarDiffByCodigoHashAndTipoDiff(codigoHash, TipoDiff.LEFT);
		Diff diffRight = diffService.buscarDiffByCodigoHashAndTipoDiff(codigoHash, TipoDiff.RIGHT);
		DiffDTO diff = new DiffDTO();
		
		if(diffLeft.getHash().equals(diffRight.getHash())) {
			diff.setHash("São IGUAIS");
		}else {
			diff.setHash("Diferentes");
		}
		
		return ResponseEntity.ok().body(diff);
		
	}

}
