package com.example.helpDesk.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.helpDesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
	
}