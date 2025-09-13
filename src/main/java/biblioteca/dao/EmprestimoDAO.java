package biblioteca.dao;

import java.util.List;

import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.Usuario;

/**
 * Define o contrato para operações de persistência de empréstimos.
 * @version 1.0
 * @author Eudes
 */
public interface EmprestimoDAO {
	
	/**
	 * Salva ou atualiza um empréstimo.
	 * @param e O empréstimo a ser salvo.
	 */
	public void adicionar(Emprestimo e);

	/**
	 * Retorna todos os empréstimos registrados.
	 * @return Lista com todos os empréstimos.
	 */
	public List<Emprestimo> listarTodos();

	/**
	 * Retorna os empréstimos de um usuário específico.
	 * @param u O usuário a ser consultado.
	 * @return Lista de empréstimos do usuário.
	 */
	public List<Emprestimo> listarPorUsuario(Usuario u);

	/**
	 * Remove um empréstimo da fonte de dados.
	 * @param emprestimo O empréstimo a ser removido.
	 */
    public void remover(Emprestimo emprestimo);
    
    /**
     * Busca um empréstimo por usuário e livro.
     * @param usuario O usuário do empréstimo.
     * @param livro O livro do empréstimo.
     * @return O empréstimo encontrado ou null.
     */
    public Emprestimo buscarEmprestimo(Usuario usuario, Livro livro);

    /**
     * Verifica se um empréstimo existe.
     * @param usuario O usuário a ser verificado.
     * @param livro O livro a ser verificado.
     * @return true se o empréstimo existir, caso contrário false.
     */
    public boolean existeEmprestimo(Usuario usuario, Livro livro);
}