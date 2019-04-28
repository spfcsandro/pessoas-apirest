package com.castgroup.rest.resource;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public abstract class AbstractResource {

	protected void adcionaHeaderLocation(long id, HttpServletResponse response, String metodo){
        URI uri = null;
        
        if(metodo.equals("SALVAR")) {
        	uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
        				.buildAndExpand(id).toUri();
        }else {
        	uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
    				.buildAndExpand(id).toUri();
        }
        		
        response.setHeader("Location", uri.toASCIIString());
    }
}
