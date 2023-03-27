package model.dao.gerenciaDeVacinas;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.gerenciaDeVacinas.EstagioDaPesquisa;
import model.vo.gerenciaDeVacinas.Pessoas;
import model.vo.gerenciaDeVacinas.Vacinas;

public class VacinasDAO {
//CRUD (Create, Read, Update, Delete)
	// CREATE --- Cadastrar
	// READ --- Consultar
	// UPDATE --- Atualizar
	// DELETE --- Excluir

	/**
	 * Insere uma nova vacina no banco
	 * 
	 * @param novaVacina
	 * @return a vacina inserida com a chave primária gerada
	 */

	public Vacinas cadastrar(Vacinas novaVacina) {
		// Conectar ao banco
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " INSERT INTO VACINAS (ID_VACINA, PAIS_ORIGEM, VALOR, DT_INICIO, ID_PESSOA)"
				+ " VALUES (?,?,?,?,?)";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatementWithPk(conexao, sql);
// PreparedStatement é passagem por parâmetro e previne sql injection

		// Executar o INSERT
		try {
			query.setInt(1, novaVacina.getIdVacina());
			query.setString(2, novaVacina.getPaisDeOrigem());
			query.setInt(3, novaVacina.getEstagioDaPesquisa().getValor());
			query.setObject(4, novaVacina.getDataInicio());
			query.setInt(5, novaVacina.getPesquisador().getIdPessoa());
			query.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaVacina.setIdVacina(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar vacina. \nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		// Fechar a conexão
		return novaVacina;
	}

	/**
	 * Atualiza os dados de uma vacina no banco
	 * 
	 * @param vacinaEditada
	 * @return boolean que informa se a atualização foi feita ou não
	 */
	public boolean atualizar(Vacinas vacinaEditada) {
		boolean atualizou = false;
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " UPDATE VACINAS " + " SET PAIS_ORIGEM = ?, VALOR = ?, DT_INICIO = ?, ID_PESSOA = ? "
				+ " WHERE ID_VACINA = ? ";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, vacinaEditada.getPaisDeOrigem());
			query.setInt(2, vacinaEditada.getEstagioDaPesquisa().getValor());
			query.setObject(3, vacinaEditada.getDataInicio());
			query.setInt(4, vacinaEditada.getPesquisador().getIdPessoa());
			query.setInt(5, vacinaEditada.getIdVacina());

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			if (quantidadeLinhasAtualizadas > 0) {
				// se fosse igual a zero isso indicaria que não houve
				// atualização em nenhuma das variaveis
				atualizou = true;
			}
		} catch (SQLException excecao) {
			System.out.println("\nErro ao atualizar vacina. " + "\nCausa: " + excecao.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return atualizou;
	}

	/**
	 * Consulta um registro de vacina no banco
	 * 
	 * @param id da vacina que se deseja consultar
	 * @return uma vacina
	 */
	public Vacinas consultarPorId(int idVacina) {
		Vacinas vacinaConsultada = null;
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " SELECT * FROM VACINAS " + " WHERE ID_VACINA = ? ";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idVacina);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				vacinaConsultada = converterDeResultSetParaEntidade(resultado);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar pessoa com id: " + idVacina + "\nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return vacinaConsultada;
	}

	/**
	 * Consulta todos os registros da entidade vacina contidos no banco
	 * 
	 * @param não há parâmetro
	 * @return lista de vacinas ArrayList<Vacinas>
	 */
	public ArrayList<Vacinas> consultarTodas() {
		ArrayList<Vacinas> listaVacinas = new ArrayList<Vacinas>();
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " SELECT * FROM VACINAS ";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Vacinas vacinaConsultada = converterDeResultSetParaEntidade(resultado);

				listaVacinas.add(vacinaConsultada);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar lista de vacinas." + "\nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return listaVacinas;
	}

	/**
	 * Chama as entidades necessárias para a criação de um objeto endereço
	 * 
	 * Método feito para que as consultas diminuam a quantidade de linhas, visto q
	 * ambas (por id e todos) possuem este mesmo trecho de código
	 * 
	 * @param resultado
	 * @return vacinaConsultada
	 */
	private Vacinas converterDeResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Vacinas vacinaConsultada = new Vacinas();
		vacinaConsultada.setIdVacina(resultado.getInt("ID_VACINA"));
		vacinaConsultada.setPaisDeOrigem(resultado.getString("PAIS_ORIGEM"));
		vacinaConsultada
				.setEstagioDaPesquisa(EstagioDaPesquisa.getEstagioDaPesquisaPorValor(resultado.getInt("VALOR")));
		vacinaConsultada.setDataInicio(resultado.getDate("DT_INICIO").toLocalDate().atStartOfDay());
		// Buscar pessoa
		int idPessoa = resultado.getInt("ID_PESSOA");
		PessoasDAO pessoaDAO = new PessoasDAO();
		Pessoas pessoa = pessoaDAO.consultarPorId(idPessoa);
		vacinaConsultada.setPesquisador(pessoa);

		return vacinaConsultada;
	}

	/**
	 * Deleta um endereço no banco
	 * 
	 * @param id do endereço que se deseja deletar
	 * @return boolean que informa se o endereço foi excluído
	 */
	public boolean excluir(int idPessoa) {
		boolean excluiu = false;

		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " DELETE FROM PESSOAS " + " WHERE ID_PESSOA = ? ";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idPessoa);

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;

		} catch (SQLException e) {
			System.out.println("\nErro ao excluir vacina com id: " + idPessoa + "\nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return excluiu;
	}
}
