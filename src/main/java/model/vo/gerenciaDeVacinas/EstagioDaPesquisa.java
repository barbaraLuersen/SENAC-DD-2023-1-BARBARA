package model.vo.gerenciaDeVacinas;

public enum EstagioDaPesquisa {
INICIAL(1), TESTE(2), APLICACAO_EM_MASSA(3);

	private int valor;
	
	public int getValor() {
		return valor;
	}
	
	EstagioDaPesquisa(int valor) {
		this.valor = valor;
	}
	
	public static EstagioDaPesquisa getEstagioDaPesquisaPorValor(int valor) {
		EstagioDaPesquisa estagioDaPesquisa = null;
		for (EstagioDaPesquisa elemento : EstagioDaPesquisa.values()) {
			if (elemento.getValor() == valor) {
				estagioDaPesquisa = elemento;
			}
		}
		return estagioDaPesquisa;
	}
}
