package biblioteca.dao;

import java.util.List;
import biblioteca.model.Usuario;

/**
 * Define o contrato para operações de persistência de usuários.
 * @author Eudes
 * @version 1.0
 */
public interface UsuarioDAO {

	/**
	 * Insere um novo usuário na fonte de dados.
	 * @param usuario O usuário a ser inserido.
	 */
	void inserir(Usuario usuario);

	/**
	 * Retorna todos os usuários cadastrados.
	 * @return Lista com todos os usuários.
	 */
	List<Usuario> listarUsuarios();

	/**
	 * Atualiza os dados de um usuário existente.
	 * @param usuario O usuário com os dados atualizados.
	 */
	void atualizar(Usuario usuario);

	/**
	 * Remove um usuário pelo seu email.
	 * @param email O email do usuário a ser removido.
	 */
	void deletar(String email);

	/**
	 * Busca um usuário pelo seu email.
	 * @param email O email do usuário a ser buscado.
	 * @return O usuário encontrado ou null.
	 */
	Usuario buscarPorEmail(String email);
}