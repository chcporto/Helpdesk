package com.example.helpDesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.helpDesk.services.DBService;

@Configuration
@Profile("dev")
public class DevConfig {
	@Autowired

	private DBService dbService;
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

	@Bean
	public boolean instanciaDB() {
		if (value.equals("create")) {
			this.dbService.instanciaDB(); // Esse metodo vai chamar o método instanciaDB toda vez que tiver com"
			// o perfil "test" logado que vai subir as instancias e salvar no banco
			// Para tudo isso acontecer de forma automatica é necessario a anotação "@Bean"
		}
		return false;	
	}

}
