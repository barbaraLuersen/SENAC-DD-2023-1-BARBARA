package model.vo.gerenciaDeVacinas;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vacinas {

	private Integer idVacina;
	private String paisDeOrigem;
	private EstagioDaPesquisa estagioDaPesquisa;
	private LocalDateTime dataInicio;
	private Pessoas pesquisador;

	public Vacinas(Integer idVacina, String paisDeOrigem, EstagioDaPesquisa estagioDaPesquisa, LocalDateTime dataInicio,
			Pessoas pesquisador) {
		super();
		this.idVacina = idVacina;
		this.paisDeOrigem = paisDeOrigem;
		this.estagioDaPesquisa = estagioDaPesquisa;
		this.dataInicio = dataInicio;
		this.pesquisador = pesquisador;
	}

	public Vacinas() {
		super();
	}

	public Integer getIdVacina() {
		return idVacina;
	}

	public void setIdVacina(Integer idVacina) {
		this.idVacina = idVacina;
	}

	public String getPaisDeOrigem() {
		return paisDeOrigem;
	}

	public void setPaisDeOrigem(String paisDeOrigem) {
		this.paisDeOrigem = paisDeOrigem;
	}

	public EstagioDaPesquisa getEstagioDaPesquisa() {
		return estagioDaPesquisa;
	}

	public void setEstagioDaPesquisa(EstagioDaPesquisa estagioDaPesquisa) {
		this.estagioDaPesquisa = estagioDaPesquisa;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Pessoas getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pessoas pesquisador) {
		this.pesquisador = pesquisador;
	}
	private String validarData(LocalDateTime dataInicio) {
		String resultado = "";
		if (dataInicio != null) {
			resultado = dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}
		return resultado;
	}
	
	public void imprimir() {
		System.out.printf("\n%3s  %-13s  %-20s  %-7s  %-24s  %-24s  ", 
				this.idVacina.byteValue(),
				this.paisDeOrigem,
				this.estagioDaPesquisa,
				this.validarData(this.getDataInicio()),
				this.pesquisador.getIdPessoa());
	}
}
