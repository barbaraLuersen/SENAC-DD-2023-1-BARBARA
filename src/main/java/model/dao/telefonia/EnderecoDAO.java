package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.BancoTelefonia;
import model.vo.telefonia.Endereco;

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
	public Endereco inserir(Endereco novoEndereco) {
		// Conectar ao banco
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " INSERT INTO ENDERECO (RUA, NUMERO, CEP, BAIRRO, CIDADE, ESTADO)" + " VALUES (?,?,?,?,?,?)";
		PreparedStatement query = BancoTelefonia.getPreparedStatementWithPk(conexao, sql);
// PreparedStatement é passagem por parâmetro e previne sql injection

		// Executar o INSERT
		try {
			query.setString(1, novoEndereco.getRua());
			query.setString(2, novoEndereco.getNumero());
			query.setString(3, novoEndereco.getCep());
			query.setString(4, novoEndereco.getBairro());
			query.setString(5, novoEndereco.getCidade());
			query.setString(6, novoEndereco.getEstado());
			query.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novoEndereco.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao inserir endereço. \nCausa: " + e.getMessage());
		} finally {
			// Fechar a conexão
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}
		return novoEndereco;
	}

	/**
	 * Atualiza os dados de um endereço no banco
	 * 
	 * @param enderecoEditado
	 * @return boolean que informa se a atualização foi feita ou não
	 */
	public boolean atualizar(Endereco enderecoEditado) {
		boolean atualizou = false;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " UPDATE ENDERECO " + " RUA = ?, NUMERO = ?, SET CEP = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ? "
				+ " WHERE ID = ? ";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, enderecoEditado.getRua());
			query.setString(2, enderecoEditado.getNumero());
			query.setString(3, enderecoEditado.getCep());
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
			System.out.println("\nErro ao atualizar endereço. " + "\n Causa: " + excecao.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}
		return atualizou;
	}

	/**
	 * Consulta um endereço no banco
	 * 
	 * @param id do endereço que se deseja consultar
	 * @return um endereço
	 */
	public Endereco consultarPorId(int id) {
		Endereco enderecoConsultado = null;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " SELECT * FROM ENDERECO " + " WHERE ID = ? ";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				enderecoConsultado = converterDeResultSetParaEntidade(resultado);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar endereço com id: " + id + "\n Causa" + e.getMessage());
		} finally {
			BancoTelefonia.closeStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}
		return enderecoConsultado;
	}

	/**
	 * Consulta todos os endereço contidos no banco
	 * 
	 * @param não há parâmetro
	 * @return lista de endereços ArrayList<EnderecoVO>
	 */
	public List<Endereco> consultarTodos() {
		Connection conexao = BancoTelefonia.getConnection();
		List<Endereco> listaEnderecosVO = new ArrayList<Endereco>();
		String sql = " SELECT * FROM ENDERECO ";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Endereco enderecoConsultado = converterDeResultSetParaEntidade(resultado);

				listaEnderecosVO.add(enderecoConsultado);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar endereços" + "\n Causa" + e.getMessage());
		} finally {
			BancoTelefonia.closeStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}
		return listaEnderecosVO;
	}

	/**
	 * Chama as entidades necessárias para a criação de um objeto endereço
	 * 
	 * Método feito para que as consultas diminuam a quantidade de linhas, visto q
	 * ambas (por id e todos) possuem este mesmo trecho de código
	 * 
	 * @param resultado
	 * @return enderecoConsultado
	 */
	private Endereco converterDeResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Endereco enderecoConsultado = new Endereco();
		enderecoConsultado.setId(resultado.getInt("id"));
		enderecoConsultado.setCep(resultado.getString("cep"));
		enderecoConsultado.setRua(resultado.getString("rua"));
		enderecoConsultado.setBairro(resultado.getString("bairro"));
		enderecoConsultado.setNumero(resultado.getString("numero"));
		enderecoConsultado.setCidade(resultado.getString("cidade"));
		enderecoConsultado.setEstado(resultado.getString("estado"));
		return enderecoConsultado;
	}

	/**
	 * Deleta um endereço no banco
	 * 
	 * @param id do endereço que se deseja deletar
	 * @return boolean que informa se o endereço foi excluído
	 */
	public boolean excluir(int id) {
		boolean excluiu = false;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " DELETE FROM ENDERECO " + " WHERE ID = ? ";
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir endereço com id: " + id + "\n Causa" + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}
		return excluiu;
	}
}
