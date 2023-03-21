package model.dao.gerenciaDeVacinas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.gerenciaDeVacinas.Pessoas;

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
		String sql = " INSERT INTO PESSOA (ID_PESSOA, NOME, DT_NASCIMENTO, SEXO, CPF, TIPO)" + " VALUES (?,?,?,?,?,?)";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatementWithPk(conexao, sql);
// PreparedStatement é passagem por parâmetro e previne sql injection

		// Executar o INSERT
		try {
			query.setInt(1, novaPessoa.getIdPessoa());
			query.setString(2, novaPessoa.getNome());
			// query.setInt(3, novaPessoa.getDataNascimento());
			query.setBoolean(4, novaPessoa.isSexo());
			query.setString(5, novaPessoa.getCpf());
			// query.setString(6, novaPessoa.getTipoDePessoa());
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
}
