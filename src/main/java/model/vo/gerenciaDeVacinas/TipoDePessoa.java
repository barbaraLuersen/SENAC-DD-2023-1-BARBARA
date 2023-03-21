package model.vo.gerenciaDeVacinas;

public enum TipoDePessoa {
	PESQUISADOR(1), VOLUNTARIO(2), PUBLICO_GERAL(3);

	private int valor;

	public int getValor() {
		return valor;
	}
	
	TipoDePessoa(int valor) {
		this.valor = valor;
	}

	public static TipoDePessoa getTipoDePessoaPorValor(int valor) {
		TipoDePessoa tipoDePessoa = null;
		for (TipoDePessoa elemento : TipoDePessoa.values()) {
			if (elemento.getValor() == valor) {
				tipoDePessoa = elemento;
			}
		}
		return tipoDePessoa;
	}
}
