package com.example.helpDesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    //
    // Criação do "endpoint" para criar um novo tecnico
    //
    // Não recebe nada como parametro na url, mas precisamos passar as informações no corpo da requisição para a criação
    // Criado abaixo o método "create"
    // No método "create", devemos adicionar a anotação "@RequestBody" que vai trazer o "TecnicoDTO", que seja chamado de objDTO
    // E depois será convertido para "Tecnico"
	//
	// Agora que o método "create" foi iniciado vamos precisar instanciar um "Tecnico" que será chamado de "newObj"
	// Ele recebera o método "service.create" que deverá http://marketplace.eclipse.org/marketplace-client-intro?mpc_install=3274405ser criado em "TecnicoService"
    // Observação..... Quando indicar o uso do método "service.create()" abaixo, o Spring vai indicar erro pq esse método ainda não existe
    // Para criar basta clicar na lâmpada vermelha de indicação de erro que ele vai sugerir criar o método"
    // Em seguida selecionar "create method", será criado o método em..... "TecnicoService"
    //
    ///  URI é o mesmo que URL
    //
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoDTO objDTO){
    Tecnico newObj = service.create(objDTO); 
    //
    // Vamos passar o path da "URL" e pegar o novo "id" e converte para uma "URI"
    //
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();	
    return ResponseEntity.created(uri).build();
    		
    	
    } 		
    	
        
}