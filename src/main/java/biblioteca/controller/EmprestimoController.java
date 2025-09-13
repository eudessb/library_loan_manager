package biblioteca.controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import biblioteca.dao.EmprestimoDAOImpl;
import biblioteca.dao.LivroDAOImpl;
import biblioteca.exception.LimiteAtingidioException;
import biblioteca.exception.LivroNaoEncontradoException;
import biblioteca.exception.MultaPendenteException;
import biblioteca.model.Emprestimo;
import biblioteca.model.Livro;
import biblioteca.model.Multavel;
import biblioteca.model.Usuario;

/**
 * Controlador responsável por orquestrar as operações de empréstimo e devolução
 * de livros.
 *
 * <p>
 * Esta classe centraliza toda a lógica de negócio para o gerenciamento de
 * empréstimos. Ela processa as solicitações vindas da camada de visualização
 * (View), valida as regras para a realização de um novo empréstimo (como a
 * disponibilidade do livro e a situação do usuário) e coordena a persistência
 * dos dados através da interface {@code EmprestimoDAO}.
 * </p>
 *
 *
 * 
 * @author Eudes
 * @version 1.0
 * @since 1.0
 */
public class EmprestimoController {

	private LivroDAOImpl livroDAO;
	private EmprestimoDAOImpl emprestimoDAO;

	/**
	 * Construtor padrão que inicializa as dependências de acesso a dados (DAO).
	 */
	public EmprestimoController() {
		 this.livroDAO = LivroDAOImpl.getInstance();
	      this.emprestimoDAO = new EmprestimoDAOImpl();
	}
	/**
	 * Realiza o empréstimo de um livro para um usuário específico.
	 *
	 * @param usuario O usuário que está solicitando o empréstimo.
	 * @param titulo  O título do livro a ser emprestado.
	 * @return Uma mensagem de status indicando o sucesso ou a falha da operação.
	 * @throws IllegalStateException    se o usuário não puder pegar o livro ou não
	 *                                  houver cópias.
	 * @throws MultaPendenteException   se houver uma multa pendente.
	 * @throws LimiteAtingidioException se não puder pegar mais livros emprestados.
	 */
	public String realizarEmprestimo(Usuario usuario, String titulo)
			throws MultaPendenteException, LimiteAtingidioException {
		try {
			if (listarEmprestimosPorUsuario(usuario).size() >= usuario.getLimiteEmprestimos()) {
				throw new LimiteAtingidioException("Você atingiu seu limite de empréstimos");
			}
			if (usuario instanceof Multavel multavel) {
				if (multavel.getValorMulta() > 0) {
					throw new MultaPendenteException("Multa pendente de R$:" + multavel.getValorMulta());
				}
			}

			Livro livro = livroDAO.buscarPorNome(titulo);

			if (livro.getQuantidade() <= 0) {
				throw new IllegalStateException("Não há livros disponíveis com este título");
			}

			livro.diminuirQuantidade();
			emprestimoDAO.adicionar(new Emprestimo(usuario, livro, LocalDate.now()));

			return "Empréstimo realizado com sucesso";
		} catch (LivroNaoEncontradoException e) {
			return "Erro: " + e.getMessage();
		}
	}

	/**
	 * Processa a devolução de um livro por um usuário, atualizando o estoque e, se
	 * aplicável, calculando e aplicando multas por atraso.
	 *
	 * <p>
	 * O método busca o empréstimo ativo correspondente ao usuário e ao título do
	 * livro. Se o empréstimo for encontrado, ele registra a data de devolução. Caso
	 * a devolução esteja atrasada, uma multa é calculada e aplicada ao usuário, se
	 * ele for do tipo {@code Multavel}. Por fim, o registro do empréstimo é
	 * removido e a quantidade do livro em estoque é incrementada.
	 * </p>
	 *
	 * @param usuario O usuário que está realizando a devolução.
	 * @param titulo  O título do livro que está sendo devolvido.
	 * @return Uma String contendo uma mensagem de status, que pode ser:
	 *         <ul>
	 *         <li>"Livro devolvido com sucesso!" em caso de sucesso.
	 *         <li>"Empréstimo não encontrado" se não houver registro do empréstimo.
	 *         <li>"Erro: ..." se o livro com o título informado não for encontrado.
	 *         </ul>
	 */
	public String devolverLivro(Usuario usuario, String titulo) {
		try {
			Livro livro = livroDAO.buscarPorNome(titulo);
			Emprestimo emprestimo = buscarEmprestimo(usuario, livro);
			if (emprestimo != null) {

				emprestimo.setDataDevolucao(LocalDate.now());
				if (emprestimo.getDataPrevistaDevolucao() != null
						&& emprestimo.getDataDevolucao().isAfter(emprestimo.getDataPrevistaDevolucao())) {
					long diasAtraso = ChronoUnit.DAYS.between(emprestimo.getDataPrevistaDevolucao(),
							emprestimo.getDataDevolucao());

					double valorMultaPorDia = 2.0;
					double valorMulta = valorMultaPorDia * diasAtraso;

					if (usuario instanceof Multavel multavel) {
						multavel.setValorMulta(valorMulta);
						System.out.println("Multa de R$" + multavel.getValorMulta() + " aplicada ao usuário");
						multavel.acumularMulta(valorMulta);

					}
				}

				emprestimoDAO.remover(emprestimo);
				livro.aumentarQuantidade();
				return "Livro devolvido com sucesso!";
			} else {
				return "Empréstimo não encontrado";
			}
		} catch (LivroNaoEncontradoException e) {
			return "Erro: " + e.getMessage();
		}

	}

	/**
	 * Retorna uma lista com todos os empréstimos registrados no sistema.
	 *
	 * @return uma lista de objetos {@code Emprestimo}. Retorna uma lista vazia se
	 *         nenhum empréstimo for encontrado.
	 */
	public List<Emprestimo> listarEmprestimos() {
		return emprestimoDAO.listarTodos();

	}

	/**
	 * Busca e retorna todos os empréstimos ativos de um usuário específico.
	 *
	 * @param usuario O usuário cujos empréstimos serão listados.
	 * @return uma lista de objetos {@code Emprestimo}. Retorna uma lista vazia se o
	 *         usuário não possuir empréstimos ativos.
	 */
	public List<Emprestimo> listarEmprestimosPorUsuario(Usuario usuario) {

		List<Emprestimo> usuarioEmprestimos = new ArrayList<Emprestimo>();
		for (Emprestimo e : emprestimoDAO.listarTodos()) {
			if (e.getUsuario().equals(usuario)) {
				usuarioEmprestimos.add(e);
			}
		}
		return usuarioEmprestimos;
	}

	/**
	 * Verifica se um usuário já possui um empréstimo ativo para um determinado
	 * livro.
	 *
	 * @param usuario O usuário a ser verificado.
	 * @param livro   O livro a ser verificado no empréstimo.
	 * @return {@code true} se um empréstimo ativo for encontrado, caso contrário
	 *         {@code false}.
	 */
	public boolean usuarioJaTemLivro(Usuario usuario, Livro livro) {
		for (Emprestimo e : listarEmprestimosPorUsuario(usuario)) {
			if (e.getLivro().equals(livro))
				return true;
		}
		return false;
	}

	/**
	 * Busca e retorna o empréstimo ativo associado a um usuário e um livro
	 * específico.
	 *
	 * @param usuario O usuário associado ao empréstimo.
	 * @param livro   O livro associado ao empréstimo.
	 * @return O objeto {@code Emprestimo} se um registro ativo for encontrado, ou
	 *         {@code null} caso contrário.
	 */
	public Emprestimo buscarEmprestimo(Usuario usuario, Livro livro) {

		for (Emprestimo e : emprestimoDAO.listarPorUsuario(usuario)) {
			if (e.getLivro().equals(livro))
				return e;

		}
		return null;
	}
}
