package com.castgroup.rest.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket produtoApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.castgroup.rest"))
				.paths(regex("/rest.*"))
				.build()
				.apiInfo(metaInfo());
	}
	
	private ApiInfo metaInfo(){
		ApiInfo apiInfo = new ApiInfo(
				"Pessoas API REST",
				"API REST de Cadastro de pessoas.",
				"1.0",
				"Terms of Service",
				new Contact("Sandro Andrade", "http://github.com/spfcsandro", 
						"spfcsandro@gmail.com"),
				"Apache License Version 2.0",
				"https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
		);
		return apiInfo;
	}
}
