package executavel;

import java.util.ArrayList;
import java.util.List;

import model.dao.telefonia.EnderecoDAO;
import model.dao.telefonia.TelefoneDAO;
import model.vo.telefonia.ClienteVO;
import model.vo.telefonia.EnderecoVO;
import model.vo.telefonia.TelefoneVO;

public class ExecutavelTelefonia {
	public static void main(String[] args) {
		
		// ----------ENDEREÇOS----------
		// Inserir endereços
		EnderecoVO endereco1 = new EnderecoVO(null, "88000123", "Mauro Ramos", "10", "Centro", "Florianópolis", "SC");

		EnderecoDAO dbaDeEnderecos = new EnderecoDAO();
		dbaDeEnderecos.inserir(endereco1);

		if (endereco1.getId() != null) {
			System.out.println("Novo endereco cadastrado");
		} else {
			System.out.println("Erro ao cadastrar endereço");
		}

		// Atualizar endereço
		boolean atualizouEnd = dbaDeEnderecos.atualizar(endereco1);
		endereco1 = dbaDeEnderecos.consultarPorId(2);
		if (atualizouEnd) {
			System.out.println("Endereço foi atualizado");
		} else {
			System.out.println("Erro ao atualizar endereço");
		}

		// Consultar endereço por id

		// Consultar todos os endereços
		//EnderecoDAO 
		
		// Deletar endereço

		// ----------TELEFONES----------
		// Criando telefones
//		TelefoneVO telefone1 = new TelefoneVO(null, null, "45", "99735361", false, true);
//		TelefoneVO telefone2 = new TelefoneVO(null, 5, "45", "99735362", false, true);
//
//		// Inserindo telefones
//		TelefoneDAO dbaDeTelefones = new TelefoneDAO();
//		dbaDeTelefones.inserir(telefone1);
//
//		if (telefone1.getId() != null) {
//			System.out.println("Novo telefone cadastrado");
//		} else {
//			System.out.println("Erro ao cadastrar telefone");
//		}
//
//		dbaDeTelefones.inserir(telefone2);
//		if (telefone2.getId() != null) {
//			System.out.println("Novo telefone cadastrado");
//		} else {
//			System.out.println("Erro ao cadastrar telefone");
//		}
//
//		// Atualizar telefones
//		boolean atualizouTel = dbaDeTelefones.atualizar(telefone1);
//		endereco1 = dbaDeEnderecos.consultarPorId(2);
//		if (atualizouTel) {
//			System.out.println("Telefone foi atualizado");
//		} else {
//			System.out.println("Erro ao atualizar telefone");
//		}

		// Consultar telefone por id

		// Deletar telefone

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
