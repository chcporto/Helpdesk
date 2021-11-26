package com.example.helpDesk.resources;

import java.util.List;
import java.util.stream.Collectors;

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

    // Implementação de "EndPoint" para buscar todos os "tecnicos"
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
    	// Vai retornar uma lista de "TecnicoDTO"  
    	// Sempre que vir uma requisição da url, sem parametro vai executar esse método
    List<Tecnico> list = service.findAll();
     // Observação..... Quando indicar o uso do método "service.findAll()" acima, o Spring vai indicar erro pq esse método ainda não existe
     // Para criar basta clicar na lâmpada vermelha de indicação de erro que ele vai sugerir criar o método"
     // Em seguida selecionar "create method", será criado o método em..... "TecnicoService"
     // Ai basta alterar indicando para retornar "repository.findAll()
     //List<TecnicoDTO> listDTO = list.stream().map(obj -> new tecnicoDTO(obj)).collect(Collectors.toList());
     List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
     return ResponseEntity.ok().body(listDTO);
    }
}