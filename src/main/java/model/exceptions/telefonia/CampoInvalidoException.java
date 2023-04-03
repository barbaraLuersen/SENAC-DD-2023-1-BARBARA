package model.exceptions.telefonia;

public class CampoInvalidoException extends Exception {
	private static final long serialVersionUID = -3983419049186701482L;

	public CampoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
