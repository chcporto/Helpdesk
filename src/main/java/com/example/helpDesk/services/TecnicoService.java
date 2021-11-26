package com.example.helpDesk.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helpDesk.domain.Tecnico;
import com.example.helpDesk.repositories.TecnicoRepository;

import com.example.helpDesk.services.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository;

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
}
