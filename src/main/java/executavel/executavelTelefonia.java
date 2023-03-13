package executavel;

import java.util.ArrayList;
import java.util.List;

import model.dao.telefonia.EnderecoDAO;
import model.dao.telefonia.TelefoneDAO;
import model.vo.telefonia.Cliente;
import model.vo.telefonia.Endereco;
import model.vo.telefonia.Telefone;

public class executavelTelefonia {
	public static void main(String[] args) {
		Endereco endereco1 = new Endereco(null, "88000123", "Mauro Ramos", "10", "Centro", "Florianópolis", "SC");

		EnderecoDAO salvadorDeEnderecos = new EnderecoDAO();
		salvadorDeEnderecos.inserir(endereco1);

		if (endereco1.getId() != null) {
			System.out.println("Novo endereco cadastrado");
		} else {
			System.out.println("Erro ao cadastrar endereço");
		}

		Telefone telefone1 = new Telefone(null, null, "45", "99735361", false, true);
		Telefone telefone2 = new Telefone(null, 5, "45", "99735362", false, true);

		TelefoneDAO salvadorDeTelefones = new TelefoneDAO();
		salvadorDeTelefones.inserir(telefone1);

		if (telefone1.getId() != null) {
			System.out.println("Novo telefone cadastrado");
		} else {
			System.out.println("Erro ao cadastrar telefone");
		}
		salvadorDeTelefones.inserir(telefone2);
		if (telefone2.getId() != null) {
			System.out.println("Novo telefone cadastrado");
		} else {
			System.out.println("Erro ao cadastrar telefone");
		}
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
