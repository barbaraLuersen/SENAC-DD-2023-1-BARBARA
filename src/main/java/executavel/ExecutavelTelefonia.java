package executavel;

import java.util.List;

import model.dao.telefonia.EnderecoDAO;
import model.dao.telefonia.TelefoneDAO;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class ExecutavelTelefonia {
	public static void main(String[] args) {

		// ----------ENDEREÇOS----------
		// Inserir endereços
		Endereco endereco1 = new Endereco(null, "88000123", "Mauro Ramos", "10", "Centro", "Florianópolis", "SC");

		EnderecoDAO dbaDeEnderecos = new EnderecoDAO();
		dbaDeEnderecos.inserir(endereco1);

		if (endereco1.getId() != null) {
			System.out.println("Novo endereco cadastrado");
		} else {
			System.out.println("Erro ao cadastrar endereço");
		}

		// Atualizar endereço
		boolean atualizouEnd = dbaDeEnderecos.atualizar(endereco1);
		endereco1 = dbaDeEnderecos.consultarPorId(1);
		if (atualizouEnd) {
			System.out.println("Endereço foi atualizado");
		} else {
			System.out.println("Erro ao atualizar endereço");
		}

		// Consultar endereço por id
		Endereco endereco = dbaDeEnderecos.consultarPorId(1);
		
		if (endereco.getId()>0 && endereco.getId() != null) {
			System.out.println(endereco);
		} else {
			System.out.println("Erro ao consultar endereço por id");
		}
		
		// Consultar todos os endereços
		List<Endereco> enderecos = dbaDeEnderecos.consultarTodos();

		System.out.println("=============== Todos os endereços ===============");
		for (Endereco e : enderecos) {
			System.out.println(e);
		}

		// Deletar endereço

		// ----------TELEFONES----------
		// Criando telefones
		Telefone telefone1 = new Telefone(null, null, "45", "99735361", false, true);
		Telefone telefone2 = new Telefone(null, 5, "45", "99735362", false, true);

		// Inserindo telefones
		TelefoneDAO dbaDeTelefones = new TelefoneDAO();
		dbaDeTelefones.inserir(telefone1);

		if (telefone1.getId() != null) {
			System.out.println("Novo telefone cadastrado");
		} else {
			System.out.println("Erro ao cadastrar telefone");
		}

		dbaDeTelefones.inserir(telefone2);
		if (telefone2.getId() != null) {
			System.out.println("Novo telefone cadastrado");
		} else {
			System.out.println("Erro ao cadastrar telefone");
		}

		// Atualizando telefones
		boolean atualizouTel = dbaDeTelefones.atualizar(telefone1);
		telefone1 = dbaDeTelefones.consultarPorId(2);
		if (atualizouTel) {
			System.out.println("Telefone foi atualizado");
		} else {
			System.out.println("Erro ao atualizar telefone");
		}

		// Consultando telefone por id
		
		
		// Consultando todos os telefones
		List<Telefone> telefones = dbaDeTelefones.consultarTodos();

		System.out.println("=============== Todos os endereços ===============");
		for (Telefone e : telefones) {
			System.out.println(e);
		}

		// Deletando telefone

//------------------------------------------------------------------------
//		List<Telefone> telefonesDoSocrates = new ArrayList<Telefone>();
//		Telefone telefone1 = new Telefone("48", "32328888", true, false);
//		telefonesDoSocrates.add(telefone1);
//		telefonesDoSocrates.add(new Telefone("48", "98881234", true, true));
//
//		Cliente pele = new Cliente("Edson Arantes", "111222333", null, true, endereco1);
//		Cliente socrates = new Cliente("Sócrates Brasileiro", "333444555", telefonesDoSocrates, true, endereco1);
//
//		List<Cliente> clientes = new ArrayList<Cliente>();
//		clientes.add(pele);
//		clientes.add(socrates);
//
//		System.out.print("----------Clientes da firma----------");
//
//		for (Cliente c : clientes) {
//			System.out.println(c.toString());
//		}
	}
}
