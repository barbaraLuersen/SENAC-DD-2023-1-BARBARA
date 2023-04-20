package model.exceptions.telefonia;

public class TelefoneJaUtilizadoException extends Exception{
	private static final long serialVersionUID = -3983419049186701482L;

	public TelefoneJaUtilizadoException(String mensagem) {
		super(mensagem);
	}
}
