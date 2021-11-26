package com.example.helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helpDesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
	
}