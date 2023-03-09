package model.vo.telefonia;

import java.util.List;

public class Cliente {
	// Atributos
	private Integer idCliente;
	private String nome;
	private String cpf;
	private List<Telefone> telefones;
	private boolean ativo;
	private Endereco endereco;

	// Construtores
	public Cliente(Integer idCliente, String nome, String cpf, List<Telefone> telefones, boolean ativo, Endereco endereco) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.ativo = ativo;
		this.endereco = endereco;
	}

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Gets e Sets
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer id) {
		this.idCliente = idCliente;
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

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
