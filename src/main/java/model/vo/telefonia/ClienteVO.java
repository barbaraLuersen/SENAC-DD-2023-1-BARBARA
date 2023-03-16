package model.vo.telefonia;

import java.util.List;

public class ClienteVO {
	// Atributos
	private Integer idCliente;
	private String nome;
	private String cpf;
	private List<TelefoneVO> telefones;
	private boolean ativo;
	private EnderecoVO endereco;

	// Construtores
	public ClienteVO(Integer idCliente, String nome, String cpf, List<TelefoneVO> telefones, boolean ativo, EnderecoVO endereco) {
		super();
		this.idCliente = idCliente;
		this.nome = nome;
		this.cpf = cpf;
		this.telefones = telefones;
		this.ativo = ativo;
		this.endereco = endereco;
	}

	public ClienteVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Gets e Sets
	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
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

	public List<TelefoneVO> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneVO> telefones) {
		this.telefones = telefones;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public EnderecoVO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoVO endereco) {
		this.endereco = endereco;
	}

}
