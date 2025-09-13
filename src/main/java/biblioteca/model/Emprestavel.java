package biblioteca.model;

/**
 * Define o contrato para itens que podem ser emprestados e devolvidos.
 */
public interface Emprestavel {

	/**
	 * Verifica se o item está disponível para empréstimo.
	 * 
	 * @return true se estiver disponível, caso contrário false.
	 */
	boolean EstaDisponivel();

	/**
	 * Executa a lógica de empréstimo do item (e.g., diminuir estoque).
	 */
	void Emprestar();

	/**
	 * Executa a lógica de devolução do item (e.g., aumentar estoque).
	 */
	void devolver();
}