package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.Banco;
import model.vo.telefonia.EnderecoVO;

public class EnderecoDAO {
//INSERT
	// INSERT INTO ENDERECO (RUA, CEP, BAIRRO, CIDADE, ESTADO, NUMERO)
	// VALUES ('', '', '', '', 'SC', '');

	/**
	 * Insere um novo endereco no banco
	 * 
	 * @param novoEndereco o endereco a ser persistido
	 * @return o endereco inserido com a chave primária gerada
	 */
	// INSERT
	public EnderecoVO inserir(EnderecoVO novoEndereco) {
		// Conectar ao banco
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO (RUA, CEP, BAIRRO, CIDADE, ESTADO, NUMERO)" + " VALUES (?,?,?,?,?,?)";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
// PreparedStatement é passagem por parâmetro e previne sql injection

		// Executar o INSERT
		try {
			query.setString(1, novoEndereco.getRua());
			query.setString(2, novoEndereco.getCep());
			query.setString(3, novoEndereco.getBairro());
			query.setString(4, novoEndereco.getCidade());
			query.setString(5, novoEndereco.getEstado());
			query.setString(6, novoEndereco.getNumero());
			query.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novoEndereco.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir endereço. \nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		// Fechar a conexão
		return novoEndereco;
	}

	// UPDATE ENDERECO
	// SET CEP=?, RUA=?, NUMERO=?, BAIRRO=?, CIDADE=?, ESTADO=?
	// WHERE ID = ?
	public boolean atualizar(EnderecoVO enderecoEditado) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ENDERECO " + " SET CEP = ?, RUA = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ? "
				+ " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, enderecoEditado.getCep());
			query.setString(2, enderecoEditado.getRua());
			query.setString(3, enderecoEditado.getNumero());
			query.setString(4, enderecoEditado.getBairro());
			query.setString(5, enderecoEditado.getCidade());
			query.setString(6, enderecoEditado.getEstado());
			query.setInt(7, enderecoEditado.getId());

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			if (quantidadeLinhasAtualizadas > 0) {
				// se fosse igual a zero isso indicaria que não houve atualização em nenhuma das
				// variaveis
				atualizou = true;
			}
		} catch (SQLException excecao) {
			System.out.println("Erro ao atualizar endereço. " + "\n Causa: " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return atualizou;
	}

	// CONSULTAR POR ID
	public EnderecoVO consultarPorId(int id) {
		EnderecoVO enderecoConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ENDERECO " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				enderecoConsultado = new EnderecoVO();
				enderecoConsultado.setId(resultado.getInt("id"));
				enderecoConsultado.setCep(resultado.getString("cep"));
				enderecoConsultado.setRua(resultado.getString("rua"));
				enderecoConsultado.setBairro(resultado.getString("bairro"));
				enderecoConsultado.setNumero(resultado.getString("numero"));
				enderecoConsultado.setCidade(resultado.getString("cidade"));
				enderecoConsultado.setEstado(resultado.getString("estado"));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar endereço com id: " + id + "\n Causa" + e.getMessage());
		}
		return enderecoConsultado;
	}
	
	// CONSULTAR TODOS
//		public List EnderecoVO consultarTodos(int id) {
//			EnderecoVO enderecoConsultado = null;
//			Connection conexao = Banco.getConnection();
//			String sql = " SELECT * FROM ENDERECO ";
//			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
//			try {
//				query.setInt(1, id);
//				ResultSet resultado = query.executeQuery();
//				if (resultado.next()) {
//					do {
//						enderecoConsultado = new EnderecoVO();
//					enderecoConsultado.setId(resultado.getInt("id"));
//					enderecoConsultado.setCep(resultado.getString("cep"));
//					enderecoConsultado.setRua(resultado.getString("rua"));
//					enderecoConsultado.setBairro(resultado.getString("bairro"));
//					enderecoConsultado.setNumero(resultado.getString("numero"));
//					enderecoConsultado.setCidade(resultado.getString("cidade"));
//					enderecoConsultado.setEstado(resultado.getString("estado"));
//				} while()
//
//			} catch (SQLException e) {
//				System.out.println("Erro ao buscar endereços" + "\n Causa" + e.getMessage());
//			}
//			return enderecoConsultado;
//		}

	// DELETE
	public boolean excluir(int id) {
		boolean excluiu = false;

		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM ENDERECO " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir endereço com id: " + id + "\n Causa" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return excluiu;
	}
}
