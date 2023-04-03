//INSERT
// INSERT INTO TELEFONE (ID, IDCLIENTE, DDD, NUMERO, ATIVO, MODEL)
// VALUES ('', '', '', '', '', '');

package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.BancoTelefonia;
import model.vo.telefonia.Telefone;

public class TelefoneDAO {

	/**
	 * Insere um novo telefone no banco
	 * 
	 * @param novoTelefone o telefone a ser persistido
	 * @return o telefone inserido com a chave primária gerada
	 */
	// INSERIR
	public Telefone inserir(Telefone novoTelefone) {
		// CONECTAR COM BANCO
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " INSERT INTO TELEFONE (DDD, NUMERO, ATIVO, MOVEL, ID_CLIENTE)" + "	VALUES (?,?,?,?,?) ";
		PreparedStatement query = BancoTelefonia.getPreparedStatementWithPk(conexao, sql);

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
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
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
	public boolean atualizar(Telefone telefoneEditado) {
		boolean atualizou = false;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " UPDATE TELEFONE " + "	SET IDCLIENTE = ?, DDD = ?, NUMERO = ?, ATIVO = ?, MODEL = ? "
				+ "	WHERE ID = ?";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
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
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
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
	public Telefone consultarPorId(int id) {
		Telefone telefoneConsultado = null;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " SELECT * FROM TELEFONE " + " WHERE ID = ? ";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
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
	public ArrayList<Telefone> consultarTodos() {
		ArrayList<Telefone> listaTelefonesVO = new ArrayList<Telefone>();
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " SELECT * FROM TELEFONE ";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Telefone telefoneConsultado = converterDeResultSetParaEntidade(resultado);

				listaTelefonesVO.add(telefoneConsultado);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar todos os telefones" + "\n Causa" + e.getMessage());
		} finally {
			BancoTelefonia.closeStatement(query);
			BancoTelefonia.closeConnection(conexao);
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
	public ArrayList<Telefone> consultarPorIdCliente(Integer id) {
		ArrayList<Telefone> listaTelefonesVO = new ArrayList<Telefone>();
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " SELECT * FROM TELEFONE " + "WHERE ID_CLIENTE = ?";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);

		try {
			query.setInt(1, 0);
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Telefone telefoneConsultado = converterDeResultSetParaEntidade(resultado);
				listaTelefonesVO.add(telefoneConsultado);
			}
		} catch (SQLException e) {
			System.out.println(
					"Erro ao buscar todos os telefones pelo id do cliente informado" + "\n Causa" + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
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
	private Telefone converterDeResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Telefone telefoneConsultado = new Telefone();
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

		Connection conexao = BancoTelefonia.getConnection();
		String sql = " DELETE FROM TELEFONE " + " WHERE ID = ? ";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir telefone com id: " + id + "\n Causa" + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}
		return excluiu;
	}

	/**
	 * Associa e ativa uma lista de telefones a um determinado cliente.
	 * 
	 * @param dono      o cliente que possui os telefones
	 * @param telefones a lista de telefones
	 */
	public void ativarTelefones(Integer idDono, List<Telefone> telefones) {
		for (Telefone telefoneDoCliente : telefones) {
			telefoneDoCliente.setIdCliente(idDono);
			telefoneDoCliente.setAtivo(true);
			if (telefoneDoCliente.getId() > 0) {
				// UPDATE no Telefone
				this.atualizar(telefoneDoCliente);
			} else {
				// INSERT no Telefone
				this.inserir(telefoneDoCliente);
			}
		}
	}

	/**
	 * Desativa todos os telefones de um determinado cliente.
	 * 
	 * @param idCliente a chave primária do cliente
	 */
	public void desativarTelefones(int idCliente) {
		Connection conn = BancoTelefonia.getConnection();
		String sql = " UPDATE EXEMPLOS.TELEFONE " + " SET id_cliente=NULL, ativo=0 " + " WHERE ID_CLIENTE=? ";

		PreparedStatement stmt = BancoTelefonia.getPreparedStatement(conn, sql);

		try {
			stmt.setInt(1, idCliente);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao desativar telefone.");
			System.out.println("Erro: " + e.getMessage());
		}
	}
}