//INSERT
// INSERT INTO TELEFONE (ID, IDCLIENTE, DDD, NUMERO, ATIVO, MODEL)
// VALUES ('', '', '', '', '', '');

package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Banco;
import model.vo.telefonia.TelefoneVO;

public class TelefoneDAO {

	/**
	 * Insere um novo telefone no banco
	 * 
	 * @param novoTelefone o telefone a ser persistido
	 * @return o telefone inserido com a chave primária gerada
	 */
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

	/**
	 * Atualiza os dados de um telefone no banco
	 * 
	 * @param telefoneEditado
	 * @return boolean que informa se a atualização foi feita ou não
	 */
	// UPDATE TELEFONE
	// SET ID = ?, IDCLIENTE = ?, DDD = ?, NUMERO = ?, ATIVO = ?, MODEL = ?
	// WHERE ID = ?
	public boolean atualizar(TelefoneVO telefoneEditado) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE TELEFONE " + "	SET IDCLIENTE = ?, DDD = ?, NUMERO = ?, ATIVO = ?, MODEL = ? "
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

	/**
	 * Consulta um telefone no banco
	 * 
	 * @param id do telefone que se deseja consultar
	 * @return um telefone
	 */
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
				telefoneConsultado = converterDeResultSetParaEntidade(resultado);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar telefone com id: " + id + "\n Causa" + e.getMessage());
		}
		return telefoneConsultado;
	}

	/**
	 * Consulta todos os telefones contidos no banco
	 * 
	 * @param não há parâmetro
	 * 
	 * @return lista de telefones ArrayList<EnderecoVO>
	 */
	public ArrayList<TelefoneVO> consultarTodos() {
		ArrayList<TelefoneVO> listaTelefonesVO = new ArrayList<TelefoneVO>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				TelefoneVO telefoneConsultado = converterDeResultSetParaEntidade(resultado);

				listaTelefonesVO.add(telefoneConsultado);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar todos os telefones" + "\n Causa" + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		return listaTelefonesVO;
	}

	/**
	 * Consulta todos os telefones vinculados a um id de cliente 
	 * 
	 * @param id do cliente
	 * 
	 * @return lista de telefones ArrayList<EnderecoVO>
	 */
	public ArrayList<TelefoneVO> consultarPorIdCliente(Integer id) {
		ArrayList<TelefoneVO> listaTelefonesVO = new ArrayList<TelefoneVO>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM TELEFONE " + "WHERE ID_CLIENTE = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			query.setInt(1, 0);
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				TelefoneVO telefoneConsultado = converterDeResultSetParaEntidade(resultado);
				listaTelefonesVO.add(telefoneConsultado);
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao buscar todos os telefones pelo id do cliente informado" + "\n Causa" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return listaTelefonesVO;
	}

	/**
	 * Chama as entidades necessárias para a criação de um objeto endereço
	 * 
	 * Método feito para que as consultas diminuam a quantidade de linhas, visto q
	 * ambas (por id e todos) possuem este mesmo trecho de código
	 * 
	 * @param resultado
	 * @return telefoneConsultado
	 */
	private TelefoneVO converterDeResultSetParaEntidade(ResultSet resultado) throws SQLException {
		TelefoneVO telefoneConsultado = new TelefoneVO();
		telefoneConsultado.setId(resultado.getInt("id"));
		telefoneConsultado.setDdd(resultado.getString("ddd"));
		telefoneConsultado.setNumero(resultado.getString("numero"));
		telefoneConsultado.setAtivo(resultado.getBoolean("ativo"));
		telefoneConsultado.setMovel(resultado.getBoolean("movel"));
		telefoneConsultado.setIdCliente(resultado.getInt("idCliente"));
		return telefoneConsultado;
	}

	/**
	 * Deleta um telefone no banco
	 * 
	 * @param id do telefone que se deseja deletar
	 * @return boolean que informa se o telefone foi excluído
	 */
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
