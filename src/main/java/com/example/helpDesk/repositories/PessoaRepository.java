package com.example.helpDesk.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	//
	// Criar os metodos de pesquisa por CPF e Email
	// O gerador vai sugerir criar como um "list, mas temos que trocar para "Optional" pq a pesquisa poder encontrar ou não 
	//
	//
	Optional<Pessoa> findByCpf(String cpf);
	Optional<Pessoa> findByEmail(String email);
	//
	// Apos a criação dos métodos podemos usar no metodo "validaPorCpfEEmail" em "ResourceExceptionHandler"
	//
	// 
}