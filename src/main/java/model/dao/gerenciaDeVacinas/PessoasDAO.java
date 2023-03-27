package model.dao.gerenciaDeVacinas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.vo.gerenciaDeVacinas.Pessoas;
import model.vo.gerenciaDeVacinas.TipoDePessoa;

public class PessoasDAO {
	// CRUD (Create, Read, Update, Delete)
	// CREATE --- Cadastrar
	// READ --- Consultar
	// UPDATE --- Atualizar
	// DELETE --- Excluir

	/**
	 * Insere uma nova pessoa no banco
	 * 
	 * @param novaPessoa
	 * @return a pessoa inserida com a chave primária gerada
	 */
	public Pessoas cadastrar(Pessoas novaPessoa) {
		// Conectar ao banco
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " INSERT INTO PESSOAS (ID_PESSOA, NOME, DT_NASCIMENTO, SEXO, CPF, VALOR)"
				+ " VALUES (?,?,?,?,?,?)";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatementWithPk(conexao, sql);
// PreparedStatement é passagem por parâmetro e previne sql injection

		// Executar o INSERT
		try {
			query.setInt(1, novaPessoa.getIdPessoa());
			query.setString(2, novaPessoa.getNome());
			query.setObject(3, novaPessoa.getDataNascimento());
			query.setString(4, String.valueOf(novaPessoa.isSexo()));
			query.setString(5, novaPessoa.getCpf());
			query.setInt(6, novaPessoa.getTipoDePessoa().getValor());
			query.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaPessoa.setIdPessoa(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar pessoa. \nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		// Fechar a conexão
		return novaPessoa;
	}

	/**
	 * Atualiza os dados de uma pessoa no banco
	 * 
	 * @param pessoaEditada
	 * @return boolean que informa se a atualização foi feita ou não
	 */
	public boolean atualizar(Pessoas pessoaEditada) {
		boolean atualizou = false;
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " UPDATE PESSOAS " + " SET NOME = ?, DT_NASCIMENTO = ?, SEXO = ?, CPF = ?, VALOR = ? "
				+ " WHERE ID_PESSOA = ? ";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, pessoaEditada.getNome());
			query.setObject(2, pessoaEditada.getDataNascimento());
			query.setString(3, String.valueOf(pessoaEditada.isSexo()));
			query.setString(4, pessoaEditada.getCpf());
			query.setInt(5, pessoaEditada.getTipoDePessoa().getValor());
			query.setInt(6, pessoaEditada.getIdPessoa());

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			if (quantidadeLinhasAtualizadas > 0) {
				// se fosse igual a zero isso indicaria que não houve
				// atualização em nenhuma das variaveis
				atualizou = true;
			}
		} catch (SQLException excecao) {
			System.out.println("\nErro ao atualizar pessoa. " + "\nCausa: " + excecao.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return atualizou;
	}

	/**
	 * Consulta um registro de pessoa no banco
	 * 
	 * @param id da pessoa que se deseja consultar
	 * @return uma pessoa
	 */
	public Pessoas consultarPorId(int idPessoa) {
		Pessoas pessoaConsultada = null;
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " SELECT * FROM PESSOAS " + " WHERE ID_PESSOA = ? ";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idPessoa);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				pessoaConsultada = converterDeResultSetParaEntidade(resultado);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar pessoa com id: " + idPessoa + "\nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return pessoaConsultada;
	}

	/**
	 * Consulta todos os registros da entidade pessoa contidos no banco
	 * 
	 * @param não há parâmetro
	 * @return lista de pessoas ArrayList<Pessoas>
	 */
	public ArrayList<Pessoas> consultarTodas() {
		ArrayList<Pessoas> listaPessoas = new ArrayList<Pessoas>();
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " SELECT * FROM PESSOAS ";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Pessoas pessoaConsultada = converterDeResultSetParaEntidade(resultado);

				listaPessoas.add(pessoaConsultada);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar lista de pessoas" + "\nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return listaPessoas;
	}

	/**
	 * Chama as entidades necessárias para a criação de um objeto endereço
	 * 
	 * Método feito para que as consultas diminuam a quantidade de linhas, visto q
	 * ambas (por id e todos) possuem este mesmo trecho de código
	 * 
	 * @param resultado
	 * @return pessoaConsultada
	 */
	private Pessoas converterDeResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Pessoas pessoaConsultada = new Pessoas();
		pessoaConsultada.setIdPessoa(resultado.getInt("ID_PESSOA"));
		pessoaConsultada.setNome(resultado.getString("NOME"));
		pessoaConsultada.setDataNascimento(resultado.getDate("DT_NASCIMENTO").toLocalDate().atStartOfDay());
		pessoaConsultada.setSexo(resultado.getString("SEXO").charAt(0));
		pessoaConsultada.setCpf(resultado.getString("CPF"));
		pessoaConsultada.setTipoDePessoa(TipoDePessoa.getTipoDePessoaPorValor(resultado.getInt("VALOR")));
		return pessoaConsultada;
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
			System.out.println("\nErro ao excluir pessoa com id: " + idPessoa + "\nCausa: " + e.getMessage());
		} finally {
			BancoGerenciaDeVacinas.closePreparedStatement(query);
			BancoGerenciaDeVacinas.closeConnection(conexao);
		}
		return excluiu;
	}
}
