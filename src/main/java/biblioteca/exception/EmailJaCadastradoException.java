package biblioteca.exception;

/**
 * Exceção lançada ao tentar cadastrar um email que já existe no sistema.
 */
public class EmailJaCadastradoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constrói a exceção com uma mensagem de erro específica.
	 * @param mensagem A mensagem de detalhe do erro.
	 */
	public EmailJaCadastradoException(String mensagem) {
		super(mensagem);
	}
}