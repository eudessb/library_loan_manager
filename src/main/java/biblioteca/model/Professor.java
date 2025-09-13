package biblioteca.model;

/**
 * Representa um professor com titulação, é subclasse de Usuario. É usada para
 * registrar professores no sistema. 
 *  
 * @author Eudes
 * @version 1.0
 */
public class Professor extends Usuario {

	/** A titulação acadêmica do professor  */
	private String titulacao;

	/**
	 * Constrói uma nova instância de Professor.
	 * 
	 * @param nome      O nome do professor.
	 * @param email     O email do professor.
	 * @param senha     A senha de acesso.
	 * @param titulacao A titulação do professor.
	 */
	public Professor(String nome, String email, String senha, String titulacao) {
		super(nome, email, senha);
		this.titulacao = titulacao;
		this.tipo = TipoUsuario.COMUM;
	}

	/**
	 * @return A titulação do professor.
	 */
	public String getTitulacao() {
		return titulacao;
	}

	/**
	 * @param titulacao A nova titulação do professor.
	 */
	public void setTitulacao(String titulacao) {
		this.titulacao = titulacao;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Define o limite de empréstimos específico para professores.
	 * </p>
	 */
	@Override
	public int getLimiteEmprestimos() {
		return 10;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Define o prazo de devolução em dias específico para professores.
	 * </p>
	 */
	@Override
	public int getPrazoDevolucao() {
		return 30; 
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
	 * O tipo de um Professor é definido internamente e não pode ser alterado.
	 * </p>
	 */
	@Override
	public void setTipo(TipoUsuario tipo) {
		// O tipo de Professor é fixo, portanto o parâmetro é ignorado.
		this.tipo = TipoUsuario.COMUM;
	}
}