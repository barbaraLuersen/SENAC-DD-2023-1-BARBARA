package model.vo.gerenciaDeVacinas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pessoas {
	private Integer idPessoa;
	private String nome;
	private LocalDateTime dataNascimento;
	private boolean sexo;
	private String cpf;
	private TipoDePessoa tipoDePessoa;

	public Pessoas(Integer idPessoa, String nome, LocalDateTime dataNascimento, boolean sexo, String cpf,
			TipoDePessoa tipoDePessoa) {
		super();
		this.idPessoa = idPessoa;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.cpf = cpf;
		this.tipoDePessoa = tipoDePessoa;
	}

	public Pessoas() {
		super();
	}

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean isSexo() {
		return sexo;
	}

	public void setSexo(boolean sexo) {
		this.sexo = sexo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public TipoDePessoa getTipoDePessoa() {
		return tipoDePessoa;
	}

	public void setTipoDePessoa(TipoDePessoa tipoDePessoa) {
		this.tipoDePessoa = tipoDePessoa;
	}

	private String validarData(LocalDateTime dataNascimento) {
		String resultado = "";
		if (dataNascimento != null) {
			resultado = dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return resultado;
	}

}