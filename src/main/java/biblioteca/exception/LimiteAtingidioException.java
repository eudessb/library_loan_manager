package biblioteca.exception;

/**
 * Exceção lançada quando um usuário com o limite de empréstimos atingidos tenta um novo.
 */
public class LimiteAtingidioException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constrói a exceção com uma mensagem de erro específica.
	 * @param mensagem A mensagem de detalhe do erro.
	 */
	public LimiteAtingidioException(String mensagem) {
		super(mensagem);
	}
}