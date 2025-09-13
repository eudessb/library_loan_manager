package biblioteca.exception;

/**
 * Exceção lançada quando um campo obrigatório não é preenchido.
 */
public class CampoVazioException extends Exception {
	
    private static final long serialVersionUID = 1L;

    /**
     * Constrói a exceção com uma mensagem de erro específica.
     * @param mensagem A mensagem de detalhe do erro.
     */
    public CampoVazioException(String mensagem) {
        super(mensagem);
    }
}