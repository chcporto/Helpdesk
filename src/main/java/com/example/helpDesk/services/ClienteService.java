package com.example.helpDesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helpDesk.domain.Cliente;
import com.example.helpDesk.domain.Pessoa;
import com.example.helpDesk.domain.dtos.ClienteDTO;
import com.example.helpDesk.repositories.ClienteRepository;
import com.example.helpDesk.repositories.PessoaRepository;
import com.example.helpDesk.resources.exceptions.DataIntegrityViolationException;
import com.example.helpDesk.resources.exceptions.ObjectNotFoundException;

@Service  // Para poder injetar esse "ClienteService" no "ClienteResource"
public class ClienteService {

	@Autowired
	private ClienteRepository repository;    /// "ClienteRepository" já tem vários métodos "Save, findById, findAll,, etc...." 
	@Autowired
	private PessoaRepository pessoarepository;   

	// findById(Integer id) { // Antes da mudança para tratrar a exceção --> public
	// Cliente findById(Integer id) {
	public Cliente findById(Integer id) { // Antes da criação do tratamento de exceção ---> return obj.orElse(null); //
		System.out.printf("Passou aqui - Cliente Service findById\n");									// Se // não encontrar retorna "null"

		Optional<Cliente> obj = repository.findById(id); // Método "findById" Faz a busca no banco, // Antes da mudança
															// para tratrar a exceção --> return obj.orElse(null);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado com o ID: " + id));
		// Após a criação do "ClienteService", podemos injetar no "ClienteResource"
		// Através do "@Autowired" // usando a sequencia abaixo

		// @Autowired
		// private ClienteRepository repository;
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Cliente create(ClienteDTO objDTO) {
		System.out.printf("Passou aqui - Cliente Service create\n");									// Se // não encontrar retorna "null"
		//
		// No banco de dados não salvamos um "ClienteDTO" e sim uma entidade de
		// "Cliente" e por isso temos que
		// Converter as informações de "objDTO" para um novo "Cliente"
		// Para isso na classe "Cliente" precisamos adicionar um construtor "Cliente"
		// para isso
		objDTO.setId(null); // Para assegurar que a requisição vai estar com "id" em branco, para se caso na
							// requisição for passado um "id" colocar null para o Banco criar o "id"
		validaPorCPFEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj); // Como é uma chamada Assincrona, primeiro vai salvar no banco e depois retorna
										// com um "id"
		// Feito isso podemos continuar a implementação em "ClienteResouce"
	}

	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		System.out.printf("Passou aqui - Cliente Service update\n");									// Se // não encontrar retorna "null"
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCPFEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Cliente obj = findById(id);
		if(obj.getChamados().size()> 0) { 
		   throw new DataIntegrityViolationException("Cliente possui ordens de serviço em seu nome e não pode deletado!");
		}
			repository.deleteById(id);
	}
	
	
	//
	// Criado o método como privado para apenas o "ClienteService" ter acesso
	// Um "Cliente e um Cliente" são pessoas por isso vamos criar no
	// "PessoaRepository" os métodos para fazer a busca por "CPF e Email"
	//
    private void validaPorCPFEEmail(ClienteDTO objDTO) {
		//
		// Para funcionar preciamos fazer
		// Injeção do "PessoaRepository" "@Autowired" depois "private PessoaRepository
		// pessoaRepository"
		//
		//
		//
		// Esse método tb podera set usado para "create e update" de Cliente e por isso
		// precisamos testar o tipo de requisição
		// O "if" abaixo testa se o cpf existe e se o parametro que esta sendo passado
		// for diferente significa que não
		// É o que pesquisamos e nesse caso podemos lançar uma exceção
		// E para isso vamos criar esse tipo de exceção no pacote "Exceptions
		// DataIntegrityViolationException"
		//
		Optional<Pessoa> obj = pessoarepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no Sistema!");
		}

		//
		// Agora pecisamos criar um manipulador de exceção para esse caso de exceção
		// "DataIntegrityViolationException"
		// Em "ResourceExceptionHandler"
		//
		// Agora criar o tratamento para email
		//
		//
		obj = pessoarepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email já cadastrado no Sistema!");

		}
	}



}
