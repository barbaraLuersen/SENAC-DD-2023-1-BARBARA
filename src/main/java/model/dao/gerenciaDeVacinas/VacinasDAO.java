package model.dao.gerenciaDeVacinas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.gerenciaDeVacinas.Vacinas;
import model.vo.telefonia.EnderecoVO;

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

	public Vacinas cadasttrar(Vacinas novaVacina) {
		// Conectar ao banco
		Connection conexao = BancoGerenciaDeVacinas.getConnection();
		String sql = " INSERT INTO VACINA (ID_VACINA, PAIS_ORIGEM, ESTAGIO, DT_INICIO, NOME_PESQUISADOR)"
				+ " VALUES (?,?,?,?,?)";
		PreparedStatement query = BancoGerenciaDeVacinas.getPreparedStatementWithPk(conexao, sql);
// PreparedStatement é passagem por parâmetro e previne sql injection

		// Executar o INSERT
		try {
			query.setInt(1, novaVacina.getIdVacina());
			query.setString(2, novaVacina.getPaisDeOrigem());
			query.setInt(3, novaVacina.getEstagioDaPesquisa().getValor());
			query.setObject(4, novaVacina.getDataInicio());
			query.setString(5, novaVacina.getNomePesquisador());
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

}
