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
import com.example.helpDesk.repositories.ClienteRepository;
import com.example.helpDesk.repositories.TecnicoRepository;


@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
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


	      tecnicoRepository.saveAll(Arrays.asList(tec1,tec2,tec3));
	      clienteRepository.saveAll(Arrays.asList(cli1,cli2,cli3,cli4,cli5));
	      chamadoRepository.saveAll(Arrays.asList(cha1,cha2));
	      
   }
}
