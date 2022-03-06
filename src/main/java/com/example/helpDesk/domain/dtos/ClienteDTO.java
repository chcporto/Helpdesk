	package com.example.helpDesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.example.helpDesk.domain.Cliente;
import com.example.helpDesk.domain.enums.Perfil;
import com.fasterxml.jackson.annotation.JsonFormat;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	//
	// Adicionar os campos que serão retornados no DTO
	//
	protected Integer id;
	@NotNull(message = "O campo NOME é requerido")
	protected String nome;
	@NotNull(message = "O campo CPF é requerido")
	protected String cpf;
	@NotNull(message = "O campo EMAIL é requerido")
	protected String email;
	@NotNull(message = "O campo SENHA é requerido")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCriacao = LocalDate.now();

	//
	// Criar o Construtor da super classe sem parametros
	//
	// Alt + Shift + S Depois gerar a super classe sem parametros usando o Objeto
	//
	public ClienteDTO() {
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
	 * public ClienteDTO(Integer id, String nome, String cpf, String email, String
	 * senha, Set<Integer> perfis, LocalDate dataCriacao) { // Irá receber um
	 * "Cliente obj"
	 */
	public ClienteDTO(Cliente obj) {
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
	// Agora tb é possível alterar a classe "ClienteResouce" para trabalhar com o
	// ClienteDTO
	// Trocando para ao invés de receber o "Cliente", receber o "ClienteDTO" que é o
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
	// Antes da criação da classe "ClienteDTO" era usando assim --> public
	// Set<Integer> getPerfis() { return perfis;
	//

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());

	}

	//
	// Antes da criação da classe "ClienteDTO" era usando assim --> public void
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
