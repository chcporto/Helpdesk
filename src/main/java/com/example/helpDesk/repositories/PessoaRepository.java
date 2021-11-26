package com.example.helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helpDesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
	
}