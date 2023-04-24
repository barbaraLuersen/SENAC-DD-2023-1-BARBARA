package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.dao.BancoTelefonia;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;

public class ClienteDAO {

	public Cliente inserir(Cliente novoCliente) {
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " INSERT INTO CLIENTE(NOME, CPF, ID_ENDERECO, ATIVO) " + " VALUES (?,?,?,?) ";
		PreparedStatement stmt = BancoTelefonia.getPreparedStatementWithPk(conexao, sql);
		try {
			stmt.setString(1, novoCliente.getNome());
			stmt.setString(2, novoCliente.getCpf());
			stmt.setInt(3, novoCliente.getEndereco().getId());
			stmt.setBoolean(4, novoCliente.isAtivo());
			// stmt.setDate(5, java.sql.Date.valueOf(novoCliente.getDataNascimento()));
			stmt.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = stmt.getGeneratedKeys();
			if (resultado.next()) {
				novoCliente.setIdCliente(resultado.getInt(1));
			}

			// TODO cadastrar os telefones do cliente
			if (novoCliente.getTelefones() != null && !novoCliente.getTelefones().isEmpty()) {
				TelefoneDAO telefoneDAO = new TelefoneDAO();
				telefoneDAO.ativarTelefones(novoCliente.getIdCliente(), novoCliente.getTelefones());
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(stmt);
			BancoTelefonia.closeConnection(conexao);
		}
		return novoCliente;
	}

	public boolean atualizar(Cliente cliente) {
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " UPDATE CLIENTE SET NOME=?, CPF=?, ID_ENDERECO=?, ATIVO=? " + " WHERE IDCLIENTE = ?";
		PreparedStatement stmt = BancoTelefonia.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setInt(3, cliente.getEndereco().getId());
			stmt.setBoolean(4, cliente.isAtivo());
			stmt.setInt(5, cliente.getIdCliente());
			registrosAlterados = stmt.executeUpdate();

			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.ativarTelefones(cliente.getIdCliente(), cliente.getTelefones());
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public boolean excluir(int id) {
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " DELETE FROM CLIENTE WHERE IDCLIENTE = " + id;
		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);

		int quantidadeLinhasAfetadas = 0;
		try {
			quantidadeLinhasAfetadas = query.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir cliente.");
			System.out.println("Erro: " + e.getMessage());
		}

		boolean excluiu = quantidadeLinhasAfetadas > 0;

		if (excluiu) {
			TelefoneDAO telefoneDAO = new TelefoneDAO();
			telefoneDAO.desativarTelefones(id);
		}

		return excluiu;
	}

	public Cliente consultarPorId(int id) {
		Cliente clienteBuscado = null;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " SELECT * FROM cliente " + " WHERE IDCLIENTE = ? ";

		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				clienteBuscado = montarClienteComResultadoDoBanco(resultado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar cliente com id: " + id + "\n Causa:" + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}

		return clienteBuscado;
	}

	public List<Cliente> consultarTodos() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " select * from cliente ";

		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				Cliente clienteBuscado = montarClienteComResultadoDoBanco(resultado);
				clientes.add(clienteBuscado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}
		return clientes;
	}

	private Cliente montarClienteComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Cliente clienteBuscado = new Cliente();
		clienteBuscado.setIdCliente(resultado.getInt("idCliente"));
		clienteBuscado.setNome(resultado.getString("nome"));
		clienteBuscado.setCpf(resultado.getString("cpf"));
		clienteBuscado.setAtivo(resultado.getBoolean("ativo"));

		int idEnderecoDoCliente = resultado.getInt("id_endereco");
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		Endereco endereco = enderecoDAO.consultarPorId(idEnderecoDoCliente);
		clienteBuscado.setEndereco(endereco);

		TelefoneDAO telefoneDAO = new TelefoneDAO();
		clienteBuscado.setTelefones(telefoneDAO.consultarPorIdCliente(clienteBuscado.getIdCliente()));

		return clienteBuscado;
	}

	public boolean cpfJaUtilizado(String cpfBuscado) {
		boolean cpfJaUtilizado = false;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " SELECT COUNT(*) FROM cliente " + " WHERE CPF = ? ";

		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, cpfBuscado);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar uso do CPF " + cpfBuscado + "\n Causa:" + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}

		return cpfJaUtilizado;
	}

	public int contarClientesQueResidemNoEndereco(Integer idEndereco) {
		int totalClientesDoEnderecoBuscado = 0;
		Connection conexao = BancoTelefonia.getConnection();
		String sql = " select count(idCliente) from cliente " + " where id_endereco = ? ";

		PreparedStatement query = BancoTelefonia.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idEndereco);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				totalClientesDoEnderecoBuscado = resultado.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Erro contar os clientes que residem em um endereço. \n Causa:" + e.getMessage());
		} finally {
			BancoTelefonia.closePreparedStatement(query);
			BancoTelefonia.closeConnection(conexao);
		}

		return totalClientesDoEnderecoBuscado;
	}
}
