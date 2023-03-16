//INSERT
// INSERT INTO TELEFONE (ID, IDCLIENTE, DDD, NUMERO, ATIVO, MODEL)
// VALUES ('', '', '', '', '', '');

package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.Banco;
import model.vo.telefonia.EnderecoVO;
import model.vo.telefonia.TelefoneVO;

public class TelefoneDAO {
	// INSERIR
	public TelefoneVO inserir(TelefoneVO novoTelefone) {

		// CONECTAR COM BANCO
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO TELEFONE (DDD, NUMERO, ATIVO, MOVEL, ID_CLIENTE)" + "	VALUES (?,?,?,?,?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);

		// EXECUTAR O INSERT
		try {
			query.setString(1, novoTelefone.getDdd());
			query.setString(2, novoTelefone.getNumero());
			query.setBoolean(3, novoTelefone.isAtivo());
			query.setBoolean(4, novoTelefone.isMovel());
			query.setInt(5, novoTelefone.getIdCliente() != null ? novoTelefone.getIdCliente() : 0);
			// A linha acima é um IF TERNÁRIO que de forma ampliada é escrita dessa forma:
			// if (novoTelefone.getIdCliente) != null) {
			// query.setInt (5, novoTelefone.getIdCliente())
			// } else {
			// query.setInt (5, 0)
			// }
			query.execute();

			// PREENCHER O ID GERADO PELO BANCO
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novoTelefone.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir telefone. \nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		// FECHAR A CONEXAO
		return novoTelefone;
	}
	
	// UPDATE TELEFONE
		// SET ID = ?, IDCLIENTE = ?, DDD = ?, NUMERO = ?, ATIVO = ?, MODEL = ?
		// WHERE ID = ?
		public boolean atualizar(TelefoneVO telefoneEditado) {
			boolean atualizou = false;
			Connection conexao = Banco.getConnection();
			String sql = " UPDATE TELEFONE "
					+ "	SET IDCLIENTE = ?, DDD = ?, NUMERO = ?, ATIVO = ?, MODEL = ? "
					+ "	WHERE ID = ?";
			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
			try {
				query.setString(1, telefoneEditado.getDdd());
				query.setString(2, telefoneEditado.getNumero());
				query.setBoolean(3, telefoneEditado.isAtivo());
				query.setBoolean(4, telefoneEditado.isMovel());
				query.setInt(5, telefoneEditado.getIdCliente());
				
				int quantidadeLinhasAtualizadas = query.executeUpdate();
				if (quantidadeLinhasAtualizadas > 0) {
					// se fosse igual a zero isso indicaria que não houve atualização em nenhuma das
					// variaveis
					atualizou = true;
				}
			} catch (SQLException excecao) {
				System.out.println("Erro ao atualizar telefone. " + "\n Causa: " + excecao.getMessage());
			} finally {
				Banco.closePreparedStatement(query);
				Banco.closeConnection(conexao);
			}
			return atualizou;
		}

		// CONSULTAR POR ID
		public TelefoneVO consultarPorId(int id) {
			TelefoneVO telefoneConsultado = null;
			Connection conexao = Banco.getConnection();
			String sql = " SELECT * FROM TELEFONE " + " WHERE ID = ? ";
			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
			try {
				query.setInt(1, id);
				ResultSet resultado = query.executeQuery();
				if (resultado.next()) {
					telefoneConsultado = new TelefoneVO();
					telefoneConsultado.setId(resultado.getInt("id"));
					telefoneConsultado.setDdd(resultado.getString("ddd"));
					telefoneConsultado.setNumero(resultado.getString("numero"));
					telefoneConsultado.setAtivo(resultado.getBoolean("ativo"));
					telefoneConsultado.setMovel(resultado.getBoolean("movel"));
					telefoneConsultado.setIdCliente(resultado.getInt("idCliente"));
				}

			} catch (SQLException e) {
				System.out.println("Erro ao buscar telefone com id: " + id + "\n Causa" + e.getMessage());
			}
			return telefoneConsultado;
		}

		// DELETE
		public boolean excluir(int id) {
			boolean excluiu = false;

			Connection conexao = Banco.getConnection();
			String sql = " DELETE FROM TELEFONE " + " WHERE ID = ? ";
			PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
			try {
				query.setInt(1, id);

				int quantidadeLinhasAtualizadas = query.executeUpdate();
				excluiu = quantidadeLinhasAtualizadas > 0;

			} catch (SQLException e) {
				System.out.println("Erro ao excluir telefone com id: " + id + "\n Causa" + e.getMessage());
			} finally {
				Banco.closePreparedStatement(query);
				Banco.closeConnection(conexao);
			}
			return excluiu;
		}
}
