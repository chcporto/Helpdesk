package com.example.helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helpDesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {
	
}