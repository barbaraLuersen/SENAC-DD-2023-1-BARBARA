package model.vo.telefonia;

public class Endereco {
	// Atributos
	private Integer id;
	private String cep;
	private String rua;
	private String numero;
	private String bairro;
	private String cidade;
	private String estado;

	// Construtores
	public Endereco(Integer id, String cep, String rua, String numero, String bairro, String cidade, String estado) {
		super();
		this.id = id;
		this.cep = cep;
		this.rua = rua;
		this.numero = numero;
		this.bairro = bairro;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Endereco() {
		super();
	}

	// Gets e Sets
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	// Metodo
	@Override
	public String toString() {
		return "ESTADO: " + this.getEstado() + "\n CIDADE: " + this.getCidade() + "\n BAIRRO: " + this.getBairro()
				+ "\n RUA: " + this.getRua() + "\n NÚMERO: " + this.getNumero();

	}
}
