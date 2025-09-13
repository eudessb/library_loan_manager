package biblioteca.model;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Representa um empréstimo com livro, usuario e data. É usada para registrar
 * empréstimos no sistema.
 * * @author Eudes Silva
 * @version 1.0
 */
public class Emprestimo {

    /** Identificador único do empréstimo (UUID). */
    private final String id;
    
    /** O usuário que realizou o empréstimo. */
    private Usuario usuario;

    /** O livro que foi emprestado. */
    private Livro livro;

    /** A data em que o empréstimo foi realizado. */
    private LocalDate dataEmprestimo;

    /** A data limite para a devolução do livro. */
    private LocalDate dataPrevistaDevolucao;

    /** A data em que o livro foi efetivamente devolvido. */
    private LocalDate dataDevolucao;

    /**
     * Constrói uma nova instância de Emprestimo. O ID é gerado automaticamente.
     * @param usuario O usuário que realiza o empréstimo.
     * @param livro O livro a ser emprestado.
     * @param dataEmprestimo A data de início do empréstimo.
     */
    public Emprestimo(Usuario usuario, Livro livro, LocalDate dataEmprestimo) {
        this.id = UUID.randomUUID().toString();
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
    }

    /**
     * @return O usuário do empréstimo.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @return O livro emprestado.
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * @return A data de início do empréstimo.
     */
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    /**
     * @return A data prevista para devolução.
     */
    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    /**
     * @return A data da efetiva devolução.
     */
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    /**
     * @return O ID único do empréstimo.
     */
    public String getId() {
        return id;
    }

    /**
     * Define a data de devolução efetiva do livro.
     * @param data A data em que o livro foi devolvido.
     */
    public void setDataDevolucao(LocalDate data) {
        this.dataDevolucao = data;
    }
}