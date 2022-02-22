package com.example.helpDesk.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helpDesk.domain.Pessoa;
import com.example.helpDesk.domain.Tecnico;
import com.example.helpDesk.domain.dtos.TecnicoDTO;
import com.example.helpDesk.repositories.PessoaRepository;
import com.example.helpDesk.repositories.TecnicoRepository;
import com.example.helpDesk.resources.exceptions.DataIntegrityViolationException;
import com.example.helpDesk.resources.exceptions.ObjectNotFoundException;

@Service  // Para poder injetar esse "TecnicoService" no "TecnicoResource"
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;    /// "TecnicoRepository" já tem vários métodos "Save, findById, findAll,, etc...." 
	@Autowired
	private PessoaRepository pessoarepository;   

	// findById(Integer id) { // Antes da mudança para tratrar a exceção --> public
	// Tecnico findById(Integer id) {
	public Tecnico findById(Integer id) { // Antes da criação do tratamento de exceção ---> return obj.orElse(null); //
											// Se // não encontrar retorna "null"
		Optional<Tecnico> obj = repository.findById(id); // Método "findById" Faz a busca no banco, // Antes da mudança
															// para tratrar a exceção --> return obj.orElse(null);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado com o ID: " + id));
		// Após a criação do "TecnicoService", podemos injetar no "TecnicoResource"
		// Através do "@Autowired" // usando a sequencia abaixo

		// @Autowired
		// private TecnicoRepository repository;
	}

	public List<Tecnico> findAll() {
		return repository.findAll();
	}

	public Tecnico create(TecnicoDTO objDTO) {
		//
		// No banco de dados não salvamos um "TecnicoDTO" e sim uma entidade de
		// "Tecnico" e por isso temos que
		// Converter as informações de "objDTO" para um novo "Tecnico"
		// Para isso na classe "Tecnico" precisamos adicionar um construtor "Tecnico"
		// para isso
		objDTO.setId(null); // Para assegurar que a requisição vai estar com "id" em branco, para se caso na
							// requisição for passado um "id" colocar null para o Banco criar o "id"
		validaPorCPFEEmail(objDTO);
		Tecnico newObj = new Tecnico(objDTO);
		return repository.save(newObj); // Como é uma chamada Assincrona, primeiro vai salvar no banco e depois retorna
										// com um "id"
		// Feito isso podemos continuar a implementação em "TecnicoResouce"
	}

	public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
		objDTO.setId(id);
		Tecnico oldObj = findById(id);
		validaPorCPFEEmail(objDTO);
		oldObj = new Tecnico(objDTO);
		return repository.save(oldObj);
	}

	
	
	
	//
	// Criado o método como privado para apenas o "TecnicoService" ter acesso
	// Um "Tecnico e um Cliente" são pessoas por isso vamos criar no
	// "PessoaRepository" os métodos para fazer a busca por "CPF e Email"
	//
    private void validaPorCPFEEmail(TecnicoDTO objDTO) {
		//
		// Para funcionar preciamos fazer
		// Injeção do "PessoaRepository" "@Autowired" depois "private PessoaRepository
		// pessoaRepository"
		//
		//
		//
		// Esse método tb podera set usado para "create e update" de Tecnico e por isso
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
