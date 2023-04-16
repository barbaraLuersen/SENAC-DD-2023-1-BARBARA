package controller.telefonia;

import java.util.List;

import model.bo.telefonia.ClienteBO;
import model.exceptions.telefonia.CampoInvalidoException;
import model.exceptions.telefonia.ClienteComTelefoneException;
import model.exceptions.telefonia.CpfAlteradoException;
import model.exceptions.telefonia.CpfJaUtilizadoException;
import model.exceptions.telefonia.EnderecoInvalidoException;
import model.vo.telefonia.Cliente;

public class ClienteController {
private ClienteBO bo = new ClienteBO();
	
	public Cliente inserir(Cliente novoCliente) throws CpfJaUtilizadoException, 
			EnderecoInvalidoException, CampoInvalidoException {
		this.validarCamposObrigatorios(novoCliente);
		return bo.inserir(novoCliente);
	}
	
	public boolean atualizar(Cliente clienteAlterado) throws EnderecoInvalidoException, CpfAlteradoException {
		//TODO validar o preenchimento dos campos obrigatórios
		return bo.atualizar(clienteAlterado);
	}
	
	public void validarCamposObrigatorios(Cliente c) throws CampoInvalidoException{
	
	}
	
	public boolean excluir(int id) throws ClienteComTelefoneException {
		return bo.excluir(id);
	}
	
	public Cliente consultarPorId(int id) {
		return bo.consultarPorId(id);
	}
	
	public List<Cliente> consultarTodos() {
		return bo.consultarTodos();
	}
}
