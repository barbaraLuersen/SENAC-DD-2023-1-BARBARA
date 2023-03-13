//INSERT
// INSERT INTO TELEFONE (ID, IDCLIENTE, DDD, NUMERO, ATIVO, MODEL)
// VALUES ('', '', '', '', '', '');

package model.dao.telefonia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.dao.Banco;
import model.vo.telefonia.Telefone;

public class TelefoneDAO {
	// INSERIR
	public Telefone inserir(Telefone novoTelefone) {

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
}
