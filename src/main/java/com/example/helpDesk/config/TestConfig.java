package com.example.helpDesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.helpDesk.services.DBService;

@Configuration
@Profile("test")
public class TestConfig {
	@Autowired

	private DBService dbService;
	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();  // Esse metodo vai chamar o método instanciaDB toda vez que tiver com"
		// o perfil "test" logado que vai subir as instancias e salvar no banco
		// Para tudo isso acontecer de forma automatica é necessario a anotação "@Bean"
	}
	

}
