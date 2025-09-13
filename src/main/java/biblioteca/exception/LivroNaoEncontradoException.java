package biblioteca.exception;

/**
 * Exceção lançada quando uma busca por um livro específico não retorna resultados.
 */
public class LivroNaoEncontradoException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constrói a exceção com uma mensagem de erro específica.
	 * @param mensagem A mensagem de detalhe do erro.
	 */
	public LivroNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
}