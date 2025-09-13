package biblioteca.controller;

import java.util.List;

import biblioteca.dao.UsuarioDAO;
import biblioteca.dao.UsuarioDAOImpl;
import biblioteca.exception.CampoVazioException;
import biblioteca.exception.EmailJaCadastradoException;
import biblioteca.exception.UsuarioNaoEncontradoException;
import biblioteca.model.Administrador;
import biblioteca.model.Aluno;
import biblioteca.model.Professor;
import biblioteca.model.TipoUsuario;
import biblioteca.model.Usuario;

/**
 * Controlador responsável pela lógica de negócio de usuários.
 * Utiliza o padrão Singleton para garantir uma única instância.
 * * @author Eudes Silva
 */
public class UsuarioController {
	
	/** Instância única (Singleton) da classe. */
	private static UsuarioController instancia;
	
	/** Dependência para a camada de acesso a dados de usuário. */
	private UsuarioDAO usuarioDAO;

	/**
	 * Construtor privado para garantir o padrão Singleton.
	 */
	private UsuarioController() {
		this.usuarioDAO = UsuarioDAOImpl.getInstance();
	}

	/**
	 * Retorna a instância única (Singleton) do controlador.
	 * @return A instância de UsuarioController.
	 */
	public static UsuarioController getInstance() {
		if (instancia == null) {
			instancia = new UsuarioController();
		}
		return instancia;
	}

	/**
	 * Cadastra um novo professor no sistema.
	 * @param nome O nome do professor.
	 * @param email O email do professor.
	 * @param senha A senha de acesso.
	 * @param titulacao A titulação acadêmica.
	 * @throws EmailJaCadastradoException Se o email já estiver em uso.
	 */
	public void cadastrarProfessor(String nome, String email, String senha, String titulacao)
			throws EmailJaCadastradoException {
		email = email.toLowerCase().trim();

		if (usuarioDAO.buscarPorEmail(email) != null) {
			throw new EmailJaCadastradoException("Email já cadastrado: " + email);
		}
		Professor professor = new Professor(nome, email, senha, titulacao);
		usuarioDAO.inserir(professor);
	}

	/**
	 * Cadastra um novo aluno no sistema.
	 * @param nome O nome do aluno.
	 * @param email O email do aluno.
	 * @param senha A senha de acesso.
	 * @param curso O curso do aluno.
	 * @param semestre O semestre atual.
	 * @param situacao A situação da matrícula.
	 * @throws EmailJaCadastradoException Se o email já estiver em uso.
	 * @throws CampoVazioException Se campos essenciais estiverem vazios.
	 */
	public void cadastrarAluno(String nome, String email, String senha, String curso, int semestre, String situacao)
			throws EmailJaCadastradoException, CampoVazioException {
		email = email.toLowerCase().trim();
		if (email.isBlank() || email == null || senha.isBlank() || senha == null) {
			throw new CampoVazioException("Campos 'email' e 'senha mão podem ficar vazios!");
		}
		if (usuarioDAO.buscarPorEmail(email) != null) {
			throw new EmailJaCadastradoException("Email já cadastrado: " + email);
		}
		Aluno aluno = new Aluno(nome, email, senha, curso, semestre, situacao);
		System.out.println("email cadastrado: " + email);
		usuarioDAO.inserir(aluno);
	}

	/**
	 * Cadastra um novo administrador no sistema.
	 * @param nome O nome do administrador.
	 * @param email O email do administrador.
	 * @param senha A senha de acesso.
	 * @throws EmailJaCadastradoException Se o email já estiver em uso.
	 */
	public void cadastrarAdministrador(String nome, String email, String senha) throws EmailJaCadastradoException {
		email = email.toLowerCase().trim();
		Administrador admin = new Administrador(nome, email, senha);
		admin.setTipo(TipoUsuario.ADMIN);

		if (usuarioDAO.buscarPorEmail(email) != null) {
			throw new EmailJaCadastradoException("Email já cadastrado: " + email);
		}
		usuarioDAO.inserir(admin);
	}

	/**
	 * Retorna uma lista com todos os usuários cadastrados.
	 * @return A lista de usuários.
	 */
	public List<Usuario> listarUsuarios() {
		return usuarioDAO.listarUsuarios();
	}

	/**
	 * Remove um usuário do sistema com base no email.
	 * @param email O email do usuário a ser removido.
	 * @return true se o usuário foi removido, false caso contrário.
	 */
	public Boolean remover(String email) {
		Usuario usuario = usuarioDAO.buscarPorEmail(email);
		if (usuario != null) {
			usuarioDAO.deletar(email);
			return true;
		}
		return false;
	}

	/**
	 * Autentica um usuário com base no email e senha.
	 * @param email O email para autenticação.
	 * @param senha A senha para autenticação.
	 * @return O objeto Usuario se a autenticação for bem-sucedida.
	 * @throws UsuarioNaoEncontradoException Se o email não for encontrado ou a senha estiver incorreta.
	 */
	public Usuario autenticar(String email, String senha) throws UsuarioNaoEncontradoException {
		email = email.toLowerCase().trim();

		Usuario usuario = usuarioDAO.buscarPorEmail(email);
		System.out.println("Tentando autenticar: " + email);
		System.out.println("Usuário encontrado: " + (usuario != null ? usuario.getEmail() : "null"));
		if (usuario == null) {
			throw new UsuarioNaoEncontradoException("Usuário não encontrado: " + email);
		}
		if (!usuario.getSenha().equals(senha)) {
			throw new UsuarioNaoEncontradoException("Senha incorreta para o email " + email);
		}
		return usuario;
	}
}