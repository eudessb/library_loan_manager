package biblioteca.model;

/**
 * Representa um usuário com privilégios de administrador no sistema. É uma
 * subclasse de {@link Usuario}.
 */
public class Administrador extends Usuario {

	/**
	 * Constrói uma nova instância de Administrador.
	 * 
	 * @param nome  O nome do administrador.
	 * @param email O email de login do administrador.
	 * @param senha A senha de acesso.
	 */
	public Administrador(String nome, String email, String senha) {
		super(nome, email, senha);
		setTipo(TipoUsuario.ADMIN);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TipoUsuario getTipo() {
		return this.tipo;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * O tipo de um Administrador é definido internamente e não pode ser alterado.
	 * </p>
	 */
	@Override
	public void setTipo(TipoUsuario tipo) {
		this.tipo = TipoUsuario.ADMIN;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Administradores não possuem limite de empréstimo.
	 * </p>
	 */
	@Override
	public int getLimiteEmprestimos() {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Administradores não possuem prazo de devolução.
	 * </p>
	 */
	@Override
	public int getPrazoDevolucao() {
		return 0;
	}
}