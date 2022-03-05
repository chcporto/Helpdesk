package com.example.helpDesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helpDesk.domain.Chamado;
import com.example.helpDesk.domain.Cliente;
import com.example.helpDesk.domain.Tecnico;
import com.example.helpDesk.domain.enums.Perfil;
import com.example.helpDesk.domain.enums.Prioridade;
import com.example.helpDesk.domain.enums.Status;
import com.example.helpDesk.repositories.ChamadoRepository;
import com.example.helpDesk.repositories.PessoaRepository;


@Service
public class DBService {
    @Autowired
	private PessoaRepository pessoaRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
   public void instanciaDB() {
	   
		Tecnico tec1 = new Tecnico(null, "Felipe Porto1 ", "19409669065", "1felipe@email.com", "123");
		Tecnico tec2 = new Tecnico(null, "Felipe Porto2 ", "40379220377", "2felipe@email.com", "123");
		Tecnico tec3 = new Tecnico(null, "Felipe Porto3 ", "69073288606", "3felipe@email.com", "123");
	      tec1.addPerfil(Perfil.ADMIN);
	      tec2.addPerfil(Perfil.ADMIN);
	      tec3.addPerfil(Perfil.ADMIN);

			Cliente cli1 = new Cliente(null, "Linus Torvalds", "77860332035", " torvalds@gmail.com", "123");
			Cliente cli2 = new Cliente(null, "1-Cliente-Erick Macedo", "17234280863", "1-cliente_erick@Teste.com", "123");
			Cliente cli3 = new Cliente(null, "3-Cliente-Carlos Henrique Correa", "87331541633","3-cliente_carlos@Teste.com", "123");
	        Cliente cli4 = new Cliente(null,"4-Cliente-Carlos Henrique Correa", "68343425758", "4-cliente_carlos@Teste.com", "123");
		    Cliente cli5 = new Cliente(null,"5-Cliente-Carlos Henrique Correa", "61378138350", "5-cliente_carlos@Teste.com", "123");

	      Chamado cha1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1); 
	      Chamado cha2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 02", "Segundo Chamado", tec2, cli2); 


	      pessoaRepository.saveAll(Arrays.asList(tec1,tec2,tec3,cli1,cli2,cli3,cli4,cli5));
	      chamadoRepository.saveAll(Arrays.asList(cha1,cha2));
	      
	   /*
			Tecnico tec1 = new Tecnico(null, "Valdir Cezar", "550.482.150-95", "valdir@mail.com", encoder.encode("123"));
			tec1.addPerfil(Perfil.ADMIN);
			Tecnico tec2 = new Tecnico(null, "Richard Stallman", "903.347.070-56", "stallman@mail.com", encoder.encode("123"));
			Tecnico tec3 = new Tecnico(null, "Claude Elwood Shannon", "271.068.470-54", "shannon@mail.com", encoder.encode("123"));
			Tecnico tec4 = new Tecnico(null, "Tim Berners-Lee", "162.720.120-39", "lee@mail.com", encoder.encode("123"));
			Tecnico tec5 = new Tecnico(null, "Linus Torvalds", "778.556.170-27", "linus@mail.com", encoder.encode("123"));

			Cliente cli1 = new Cliente(null, "Albert Einstein", "111.661.890-74", "einstein@mail.com", encoder.encode("123"));
			Cliente cli2 = new Cliente(null, "Marie Curie", "322.429.140-06", "curie@mail.com", encoder.encode("123"));
			Cliente cli3 = new Cliente(null, "Charles Darwin", "792.043.830-62", "darwin@mail.com", encoder.encode("123"));
			Cliente cli4 = new Cliente(null, "Stephen Hawking", "177.409.680-30", "hawking@mail.com", encoder.encode("123"));
			Cliente cli5 = new Cliente(null, "Max Planck", "081.399.300-83", "planck@mail.com", encoder.encode("123"));
	 
			Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 1", "Teste chamado 1", tec1, cli1);
			Chamado c2 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 2", "Teste chamado 2", tec1, cli2);
			Chamado c3 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 3", "Teste chamado 3", tec2, cli3);
			Chamado c4 = new Chamado(null, Prioridade.ALTA, Status.ABERTO, "Chamado 4", "Teste chamado 4", tec3, cli3);
			Chamado c5 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 5", "Teste chamado 5", tec2, cli1);
			Chamado c6 = new Chamado(null, Prioridade.BAIXA, Status.ENCERRADO, "Chamado 7", "Teste chamado 6", tec1, cli5);

			pessoaRepository.saveAll(Arrays.asList(tec1, tec2, tec3, tec4, tec5, cli1, cli2, cli3, cli4, cli5));
			chamadoRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5, c6));
*/
   }
}
