package executavel;

import java.time.LocalDateTime;
import java.util.List;

import model.dao.gerenciaDeVacinas.PessoasDAO;
import model.dao.gerenciaDeVacinas.VacinasDAO;
import model.vo.gerenciaDeVacinas.EstagioDaPesquisa;
import model.vo.gerenciaDeVacinas.Pessoas;
import model.vo.gerenciaDeVacinas.TipoDePessoa;
import model.vo.gerenciaDeVacinas.Vacinas;

public class ExecutavelGerenciaDeVacinas {

	public static void main(String[] args) {
		// INSERT
		Pessoas pessoa1 = new Pessoas(1, "Ullrich Ross", LocalDateTime.of(1980, 11, 06, 0, 0), 'M', "08921190765",
				TipoDePessoa.PESQUISADOR);
		Pessoas pessoa2 = new Pessoas(2, "Gunther Luersen", LocalDateTime.of(2013, 5, 06, 0, 0), 'M', "08921190987",
				TipoDePessoa.PUBLICO_GERAL);
		Pessoas pessoa3 = new Pessoas(3, "Eduardo Araujo", LocalDateTime.of(2001, 8, 20, 0, 0), 'M', "08921190632",
				TipoDePessoa.VOLUNTARIO);
		Pessoas pessoa4 = new Pessoas(4, "Lucas Cassio", LocalDateTime.of(1990, 4, 10, 0, 0), 'M', "08921190632",
				TipoDePessoa.PUBLICO_GERAL);
		Pessoas pessoa5 = new Pessoas(5, "Isabella Arantes", LocalDateTime.of(2000, 3, 30, 0, 0), 'M', "08921190632",
				TipoDePessoa.PESQUISADOR);

		PessoasDAO dbaDePessoas = new PessoasDAO();

		// CADASTRAR PESSOA
		dbaDePessoas.cadastrar(pessoa1);
		if (pessoa1.getIdPessoa() != null) {
			System.out.println("Cadastrado de pessoa com id: " + pessoa1.getIdPessoa() + " realizado");
		} else {
			System.out.println("Erro ao cadastrar pesssoa com id: " + pessoa1.getIdPessoa());
		}

		dbaDePessoas.cadastrar(pessoa2);
		if (pessoa1.getIdPessoa() != null) {
			System.out.println("Cadastrado de pessoa com id: " + pessoa2.getIdPessoa() + " realizado");
		} else {
			System.out.println("Erro ao cadastrar pesssoa com id: " + pessoa2.getIdPessoa());
		}

		dbaDePessoas.cadastrar(pessoa3);
		if (pessoa1.getIdPessoa() != null) {
			System.out.println("Cadastrado de pessoa com id: " + pessoa3.getIdPessoa() + " realizado");
		} else {
			System.out.println("Erro ao cadastrar pesssoa com id: " + pessoa3.getIdPessoa());
		}

		dbaDePessoas.cadastrar(pessoa4);
		if (pessoa1.getIdPessoa() != null) {
			System.out.println("Cadastrado de pessoa com id: " + pessoa4.getIdPessoa() + " realizado");
		} else {
			System.out.println("Erro ao cadastrar pesssoa com id: " + pessoa4.getIdPessoa());
		}

		dbaDePessoas.cadastrar(pessoa5);
		if (pessoa1.getIdPessoa() != null) {
			System.out.println("Cadastrado de pessoa com id: " + pessoa5.getIdPessoa() + " realizado");
		} else {
			System.out.println("Erro ao cadastrar pesssoa com id: " + pessoa5.getIdPessoa());
		}

		// ATUALIZAR PESSOA
		Pessoas pessoaEditada = dbaDePessoas.consultarPorId(5);
		pessoaEditada.setSexo('F');
		boolean atualizouPessoa = dbaDePessoas.atualizar(pessoaEditada);

		if (atualizouPessoa) {
			System.out.println("Pessoa foi atualizada");
		} else {
			System.out.println("Erro ao atualizar pessoa");
		}
		 pessoaEditada.imprimir();

		// CONSULTAR PESSOA POR ID
		Pessoas pessoaConsultada = dbaDePessoas.consultarPorId(3);
		pessoaConsultada.imprimir();

		// CONSULTAR TODAS AS PESSOAS
		List<Pessoas> pessoas = dbaDePessoas.consultarTodas();

		System.out.println("\n=============== Todos as Pessoas ===============");
		for (Pessoas e : pessoas) {
			e.imprimir();
	}

		// EXCLUIR PESSOA
		boolean pessoaExcluida = dbaDePessoas.excluir(2);
		if (pessoaExcluida) {
			System.out.println("Pessoa excluida!");
		} else {
			System.out.println("Erro ao excluir pessoa!");
		}

		// -----------------------------------------------
		// CADASTRAR VACINA
		Vacinas vacina1 = new Vacinas(1, "Estados Unidos", EstagioDaPesquisa.APLICACAO_EM_MASSA,
				LocalDateTime.of(2000, 10, 02, 0, 0), pessoa1);
		Vacinas vacina2 = new Vacinas(2, "Estados Unidos", EstagioDaPesquisa.TESTE,
				LocalDateTime.of(2020, 12, 15, 0, 0), pessoa5);
		
		VacinasDAO dbaDeVacinas = new VacinasDAO();
		dbaDeVacinas.cadastrar(vacina1);
		dbaDeVacinas.cadastrar(vacina2);

		if (vacina1.getIdVacina() != null) {
			System.out.println("Cadastrado de vacina realizado");
		} else {
			System.out.println("Erro ao cadastrar nova vacina");
		}

		// ATUALIZAR VACINA
		Vacinas vacinaEditada = dbaDeVacinas.consultarPorId(1);
		vacinaEditada.setPaisDeOrigem("Argentina");
		boolean atualizouVacina = dbaDeVacinas.atualizar(vacinaEditada);

		if (atualizouVacina) {
			System.out.println("Registro de vacina atualizado");
		} else {
			System.out.println("Erro ao atualizar vacina");
		}
		vacinaEditada.imprimir();

		// CONSULTAR VACINA POR ID
		Vacinas vacinaConsultada = dbaDeVacinas.consultarPorId(1);
		vacinaConsultada.imprimir();

		// CONSULTAR TODAS AS VACINAS
		List<Vacinas> vacinas = dbaDeVacinas.consultarTodas();
		System.out.println("\n=============== Todos as Vacinas ===============");
		for (Vacinas e : vacinas) {
			e.imprimir();
		}

		// EXCLUIR VACINA
		boolean vacinaExcluida = dbaDeVacinas.excluir(1);
		if (vacinaExcluida) {
			System.out.println("Vacina excluida!");
		} else {
			System.out.println("Erro ao excluir vacina!");
		}
	}
}
