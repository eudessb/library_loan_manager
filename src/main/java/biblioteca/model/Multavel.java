package biblioteca.model;

/**
 * Define o contrato para objetos que podem receber e gerenciar multas.
 */
public interface Multavel {

	/**
	 * Retorna o valor total da multa pendente.
	 * @return O valor da multa.
	 */
	double getValorMulta();

	/**
	 * Zera o valor da multa, simulando o pagamento integral.
	 */
	void pagarMulta();

	/**
	 * Adiciona um valor à multa total acumulada.
	 * @param valor O valor a ser acumulado.
	 */
	void acumularMulta(double valor);

	/**
	 * Define o valor total da multa, substituindo qualquer valor existente.
	 * @param valor O novo valor total da multa.
	 */
	void setValorMulta(double valor);
}