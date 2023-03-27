package executavel;

import java.time.LocalDateTime;

import model.dao.gerenciaDeVacinas.PessoasDAO;
import model.dao.gerenciaDeVacinas.VacinasDAO;
import model.vo.gerenciaDeVacinas.EstagioDaPesquisa;
import model.vo.gerenciaDeVacinas.Pessoas;
import model.vo.gerenciaDeVacinas.TipoDePessoa;
import model.vo.gerenciaDeVacinas.Vacinas;

public class ExecutavelGerenciaDeVacinas {

	public static void main(String[] args) {
		// CADASTRAR PESSOA
		Pessoas pessoa1 = new Pessoas(1, "Ullrich Ross", LocalDateTime.of(1980, 11, 06, 0, 0), 'M', "08921190965",
				TipoDePessoa.PESQUISADOR);

		PessoasDAO dbaDePessoas = new PessoasDAO();
		dbaDePessoas.cadastrar(pessoa1);

		if (pessoa1.getIdPessoa() != null) {
			System.out.println("Cadastrado de pessoa realizado");
		} else {
			System.out.println("Erro ao cadastrar nova pesssoa");
		}

		// ATUALIZAR PESSOA

		// CONSULTAR PESSOA POR ID

		// CONSULTAR TODAS AS PESSOAS

		// EXCLUIR PESSOA
		
		
		//-----------------------------------------------
		// CADASTRAR VACINA
		Vacinas vacina1 = new Vacinas(1, "Estados Unidos", EstagioDaPesquisa.APLICACAO_EM_MASSA,
				LocalDateTime.of(2000, 10, 02, 0, 0), pessoa1);

		VacinasDAO dbaDeVacinas = new VacinasDAO();
		dbaDeVacinas.cadastrar(vacina1);

		if (vacina1.getIdVacina() != null) {
			System.out.println("Cadastrado de vacina realizado");
		} else {
			System.out.println("Erro ao cadastrar nova vacina");
		}

		// ATUALIZAR VACINA

		// CONSULTAR VACINA POR ID

		// CONSULTAR TODAS AS VACINAS

		// EXCLUIR VACINA
	}

}
