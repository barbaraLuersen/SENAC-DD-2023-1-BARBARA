package model.bo.telefonia;

import java.util.List;

import model.dao.telefonia.ClienteDAO;
import model.exceptions.telefonia.ClienteComTelefoneException;
import model.exceptions.telefonia.CpfAlteradoException;
import model.exceptions.telefonia.CpfJaUtilizadoException;
import model.exceptions.telefonia.EnderecoInvalidoException;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Telefone;

public class ClienteBO {
	private ClienteDAO dao = new ClienteDAO();

	/**
	 * Insere um novo cliente, mas faz validações que podem gerar exceções
	 * 
	 * @param novoCliente
	 * @return o novoCliente inserido, com a PK gerada
	 * @throws CpfJaUtilizadoException
	 * @throws EnderecoInvalidoException
	 */
	public Cliente inserir(Cliente novoCliente) throws CpfJaUtilizadoException, EnderecoInvalidoException {
		if (dao.cpfJaUtilizado(novoCliente.getCpf())) {
			throw new CpfJaUtilizadoException("CPF informado já foi utilizado");
		}

		validarEndereco(novoCliente);

		// Caso CPF não utilizado -> salvar e devolver o cliente
		return dao.inserir(novoCliente);
	}

	/**
	 * Atualiza um novo cliente, mas faz validações que podem gerar exceções
	 * 
	 * @param cliente
	 * @return o cliente atualizado
	 * @throws CpfAlteradoException
	 * @throws EnderecoInvalidoException
	 */
	public boolean atualizar(Cliente clienteAlterado) throws EnderecoInvalidoException, CpfAlteradoException {
		Cliente clienteOriginal = dao.consultarPorId(clienteAlterado.getIdCliente());

		if (clienteAlterado.getCpf() != clienteOriginal.getCpf()) {
			throw new CpfAlteradoException("CPF não pode ser alterado");
		}

		validarEndereco(clienteAlterado);

		return dao.atualizar(clienteAlterado);
	}

	/**
	 * não deixar excluir cliente que possua telefone associado 
	 * Criar exceção ClienteComTelefoneException 
	 * Caso cliente possua telefone(s): lançar ClienteComTelefoneException 
	 * Caso contrário: chamar dao.excluir(id)
	 * 
	 * @param id
	 * @return se excluiu ou não o cliente
	 */
	public boolean excluir(int id) throws ClienteComTelefoneException {
		List<Telefone> telefonesDoCliente = dao.consultarPorId(id).getTelefones();

		if (telefonesDoCliente != null && !telefonesDoCliente.isEmpty()) {
			throw new ClienteComTelefoneException("Cliente possui telefone(s) associado(s) e não pode ser excluído");
		}
		
		return dao.excluir(id);
	}

	public Cliente consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Cliente> consultarTodos() {
		return dao.consultarTodos();
	}

	private void validarEndereco(Cliente cliente) throws EnderecoInvalidoException {
		if (cliente.getEndereco() == null || cliente.getEndereco().getId() == null) {
			throw new EnderecoInvalidoException("Endereço não informado");
		}
	}
}
