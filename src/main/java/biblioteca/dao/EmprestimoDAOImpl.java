/*
 * Contém as implementações dos métodos declarados em EmprestimoDAO
 * 
 * @see EmprestimoDAO
 * @author Eudes Silva
 * @version 1.0
 */

package biblioteca.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.Usuario;

/**
 * Implementação em memória de EmprestimoDAO.
 * Armazena os empréstimos agrupados por usuário.
 * 
 * @author Eudes Silva
 * @version 1.0
 */
public class EmprestimoDAOImpl implements EmprestimoDAO {

    /** Armazena os empréstimos em um mapa, agrupados por usuário. */
    private Map<Usuario, List<Emprestimo>> emprestimosPorUsuario = new LinkedHashMap<>();

    /** Construtor público padrão. */
    public EmprestimoDAOImpl() {
    }

    /** {@inheritDoc} */
    @Override
    public void adicionar(Emprestimo emprestimo) {
        emprestimosPorUsuario
            .computeIfAbsent(emprestimo.getUsuario(), k -> new ArrayList<>())
            .add(emprestimo);
    }

    /** {@inheritDoc} */
    @Override
    public List<Emprestimo> listarTodos() {
        List<Emprestimo> todos = new ArrayList<>();
        for (List<Emprestimo> lista : emprestimosPorUsuario.values()) {
            todos.addAll(lista);
        }
        return todos;
    }

    /** {@inheritDoc} */
    @Override
    public List<Emprestimo> listarPorUsuario(Usuario usuario) {
        return emprestimosPorUsuario.getOrDefault(usuario, new ArrayList<>());
    }

    /** {@inheritDoc} */
    @Override
    public void remover(Emprestimo emprestimo) {
        List<Emprestimo> lista = emprestimosPorUsuario.get(emprestimo.getUsuario());
        if (lista != null) {
            lista.remove(emprestimo);
            if (lista.isEmpty()) {
                emprestimosPorUsuario.remove(emprestimo.getUsuario());
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public Emprestimo buscarEmprestimo(Usuario usuario, Livro livro) {
        List<Emprestimo> lista = emprestimosPorUsuario.get(usuario);
        if (lista != null) {
            for (Emprestimo e : lista) {
                if (e.getLivro().equals(livro)) {
                    return e;
                }
            }
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public boolean existeEmprestimo(Usuario usuario, Livro livro) {
        return buscarEmprestimo(usuario, livro) != null;
    }
}
