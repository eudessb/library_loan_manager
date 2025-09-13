package biblioteca.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import biblioteca.model.Usuario;

/**
 * Implementação em memória de UsuarioDAO usando o padrão Singleton.
 */
public class UsuarioDAOImpl implements UsuarioDAO {

	/**
	 * Mapa para armazenamento dos usuários em memória, usando o email como chave.
	 */
	private Map<String, Usuario> usuarios = new LinkedHashMap<>();

	/** Instância única (Singleton) da classe. */
	private static UsuarioDAOImpl instancia;

	/**
	 * Construtor privado para garantir o padrão Singleton.
	 */
	private UsuarioDAOImpl() {
	}

	/**
	 * Retorna a instância única (Singleton) desta classe.
	 * 
	 * @return A instância do DAO.
	 */

	public static UsuarioDAOImpl getInstance() {
		if (instancia == null) {
			instancia = new UsuarioDAOImpl();
		}
		return instancia;
	}

	/** {@inheritDoc} */

	@Override
	public void inserir(Usuario usuario) {
		if (usuario != null)
			usuarios.put(usuario.getEmail(), usuario);
		System.out.println("Usuário cadastrado: " + usuario.getEmail());
	}

	/** {@inheritDoc} */

	@Override
	public Usuario buscarPorEmail(String email) {
		return usuarios.get(email);
	}

	/** {@inheritDoc} */

	@Override
	public List<Usuario> listarUsuarios() {
		return new ArrayList<>(usuarios.values());

	}

	/** {@inheritDoc} */

	@Override
	public void atualizar(Usuario usuario) {
		if (usuario != null && usuarios.containsKey(usuario.getEmail())) {
			usuarios.put(usuario.getEmail(), usuario); // substitui o antigo pelo novo
		} else {
			throw new IllegalArgumentException("Usuário não encontrado para atualização.");
		}
	}

	/** {@inheritDoc} */

	@Override
	public void deletar(String email) {
		usuarios.remove(email);
	}

}
