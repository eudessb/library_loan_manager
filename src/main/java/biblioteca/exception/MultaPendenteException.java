package biblioteca.exception;

/**
 * Exceção lançada quando um usuário tenta realizar uma operação não permitida 
 * devido a uma multa pendente.
 */
public class MultaPendenteException extends Exception {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constrói a exceção com uma mensagem de erro específica.
	 * @param mensagem A mensagem de detalhe do erro.
	 */
	public MultaPendenteException(String mensagem) {
		super(mensagem);
	}
}