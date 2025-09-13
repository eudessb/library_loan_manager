package biblioteca.model;

/**
 * Representa a base abstrata para todos os tipos de usuários do sistema.
 * * @author Eudes
 * 
 * @version 1.0
 */
public abstract class Usuario {

	/** Valor base da multa por dia de atraso. */
	public static final double VALOR_DA_MULTA = 1.25;

	/** Identificador único do usuário. */
	protected int id;

	/** Matrícula ou identificador institucional. */
	protected String matricula;

	/** Nome completo do usuário. */
	protected String nome;

	/** Endereço de email para login e contato. */
	protected String email;

	/** Senha de acesso ao sistema. */
	protected String senha;

	/** Número máximo de empréstimos simultâneos permitido. */
	protected int limiteEmprestimos;

	/** Prazo em dias para a devolução de um livro. */
	protected int prazoDevolucao;

	/** O tipo de usuário (e.g., Aluno, Professor). */
	protected TipoUsuario tipo;

	/** Valor da multa acumulada pelo usuário. */
	protected double valorMulta = VALOR_DA_MULTA;

	/**
	 * Constrói uma nova instância de usuário com dados essenciais.
	 * 
	 * @param nome  O nome do usuário.
	 * @param email O email do usuário.
	 * @param senha A senha do usuário.
	 */
	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}

	/**
	 * @return O ID do usuário.
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id O novo ID do usuário.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return O nome do usuário.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O novo nome do usuário.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return O email do usuário.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email O novo email do usuário.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return A senha do usuário.
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha A nova senha do usuário.
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * Retorna o limite de empréstimos definido para este tipo de usuário.
	 * 
	 * @return O número máximo de empréstimos.
	 */
	public abstract int getLimiteEmprestimos();

	/**
	 * Retorna o prazo de devolução em dias para este tipo de usuário.
	 * 
	 * @return O prazo em dias.
	 */
	public abstract int getPrazoDevolucao();

	/**
	 * Define o tipo do usuário.
	 * 
	 * @param tipo O tipo a ser definido.
	 */
	public abstract void setTipo(TipoUsuario tipo);

	/**
	 * Retorna o tipo do usuário.
	 * 
	 * @return O tipo de usuário.
	 */
	public abstract TipoUsuario getTipo();

}