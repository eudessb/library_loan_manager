package biblioteca.model;

/**
 * Representa um aluno com curso, semestre e situação, é subclasse de
 * {@link Usuario}. É usada para registrar alunos no sistema. 
 * 
 * @author Eudes 
 * @version 1.0
 */
public class Aluno extends Usuario implements Multavel {

	/** O curso no qual o aluno está matriculado. */
	private String curso;

	/** O semestre atual do aluno. */
	private int semestre;

	/** A situação da matrícula do aluno */
	private String situacao;

	/** O valor total da multa acumulada pelo aluno. */
	private double multa;

	/**
	 * Constrói uma nova instância de Aluno.
	 * 
	 * @param nome     O nome do aluno.
	 * @param email    O email do aluno.
	 * @param senha    A senha de acesso.
	 * @param curso    O curso do aluno.
	 * @param semestre O semestre atual.
	 * @param situacao A situação da matrícula.
	 */
	public Aluno(String nome, String email, String senha, String curso, int semestre, String situacao) {
		super(nome, email, senha);
		this.curso = curso;
		this.semestre = semestre;
		this.situacao = situacao;
		this.tipo = TipoUsuario.COMUM; // Supondo que COMUM seja o tipo para Aluno
	}

	/**
	 * @return O curso do aluno.
	 */
	public String getCurso() {
		return curso;
	}

	/**
	 * @param curso O novo curso do aluno.
	 */
	public void setCurso(String curso) {
		this.curso = curso;
	}

	/**
	 * @return O semestre do aluno.
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * @param semestre O novo semestre do aluno.
	 */
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}

	/**
	 * @return A situação da matrícula do aluno.
	 */
	public String getSituacao() {
		return situacao;
	}

	/**
	 * @param situacao A nova situação da matrícula.
	 */
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Define o limite de empréstimos específico para alunos.
	 * </p>
	 */
	@Override
	public int getLimiteEmprestimos() {
		return 5; 
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Define o prazo de devolução em dias específico para alunos.
	 * </p>
	 */
	@Override
	public int getPrazoDevolucao() {
		return 15; 
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsuario getTipo() {
		return tipo;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * O tipo de um Aluno é definido internamente e não pode ser alterado.
	 * </p>
	 */
	@Override
	public void setTipo(TipoUsuario tipo) {
		// O tipo de Aluno é fixo, portanto o parâmetro é ignorado.
		this.tipo = TipoUsuario.COMUM;
	}

	/**
	 * Adiciona um valor à multa total do aluno.
	 * 
	 * @param valor O valor a ser adicionado à multa.
	 */
	@Override
	public void setValorMulta(double valor) {
		this.multa += valor;
	}

	/**
	 * Retorna o valor total da multa pendente do aluno.
	 * 
	 * @return O valor da multa.
	 */
	@Override
	public double getValorMulta() {
		return multa;
	}

	/**
	 * Acumula um valor à multa existente.
	 * 
	 * @param valor O valor a ser adicionado à multa total.
	 */
	@Override
	public void acumularMulta(double valor) {
		if (valor > 0) {
			this.multa += valor;
		}
	}

	/**
	 * Zera o valor da multa do aluno, simulando o pagamento total.
	 */
	@Override
	public void pagarMulta() {
		this.multa = 0.0;
	}
}