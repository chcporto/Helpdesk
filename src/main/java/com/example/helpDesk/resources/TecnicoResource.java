package com.example.helpDesk.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helpDesk.domain.Tecnico;
import com.example.helpDesk.domain.dtos.TecnicoDTO;
import com.example.helpDesk.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos") // Para adicionar o endPoint inicial para os servições para os endPoints das
										// classes desejadas

public class TecnicoResource {
@Autowired
private TecnicoService service;

@GetMapping(value ="/{id}")  //  Vai receber através de uma variável path para as buscas
    // Antes da criação do DTO public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id){
	// Médtodo que Representa todas as respostas HTTP como estamos trabalhando com
    // "Tecnico"
	Tecnico obj= service.findById(id);
	// Antes da criação do DTO --->	return ResponseEntity.ok().body(obj);
	return ResponseEntity.ok().body(new TecnicoDTO(obj));
	// "ok" Busca feita
	// "body" No corpo da resposta Retorna o objeto
	}
}