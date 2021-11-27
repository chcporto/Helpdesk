package com.example.helpDesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.helpDesk.domain.Tecnico;
import com.example.helpDesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class TecnicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	// Adicionar os campos que serão retornados no DTO
	//
	protected Integer id;
	protected String nome;
	protected String cpf;
	protected String email;
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	//
	// Criar o Construtor da super classe sem parametros
	//
	// Alt + Shift + S Depois gerar a super classe sem parametros usando o Objeto
	//
	public TecnicoDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	//
	// Criar o Construtor da super classe com parametros
	//
	// Alt + Shift + S Depois gerar a super classe sem parametros usando os
	// parametros
	//
	/*
	 * // Não é necessário receber todos esses parametros abaixo,pq queremos retorna
	 * um tecnico DTO e não apenas um tecnico // rotina do "puclic abaixo que será
	 * substituida
	 * 
	 * public TecnicoDTO(Integer id, String nome, String cpf, String email, String
	 * senha, Set<Integer> perfis, LocalDate dataCriacao) { // Irá receber um
	 * "Tecnico obj"
	 */
	public TecnicoDTO(Tecnico obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		addPerfil(Perfil.CLIENTE);
		// Devido o "Pefil ser uma lista é necessário tratar
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	//
	// Criar o Construtor para os metodos assessores e modificadores
	// Alt + Shift + S Gerar os getters e setters de todos os campos menos do
	// serialVersion
	//
	// Poderia tb usar apenas a anotação "@Getter e @Setter
	//
	// Agora tb é possível alterar a classe "TecnicoResouce" para trabalhar com o
	// TecnicoDTO
	// Trocando para ao invés de receber o "Tecnico", receber o "TecnicoDTO" que é o
	// objeto de transferencia de dados
	//
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	//
	// Antes da criação da classe "TecnicoDTO" era usando assim --> public
	// Set<Integer> getPerfis() { return perfis;
	//

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());

	}

	//
	// Antes da criação da classe "TecnicoDTO" era usando assim --> public void
	// setPerfis(Set<Integer> perfis) { this.perfis = perfis; }
	//
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
}
