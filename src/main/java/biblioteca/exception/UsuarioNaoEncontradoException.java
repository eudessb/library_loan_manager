package biblioteca.exception;

/**
 * Exceção lançada quando uma busca por um usuário específico não o encontra no sistema.
 */
public class UsuarioNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constrói a exceção com uma mensagem de erro específica.
	 * @param mensagem A mensagem de detalhe do erro.
	 */
	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}