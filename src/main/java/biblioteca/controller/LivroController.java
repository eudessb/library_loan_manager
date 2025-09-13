package biblioteca.controller;

import java.util.ArrayList;
import java.util.List;

import biblioteca.dao.LivroDAO;
import biblioteca.dao.LivroDAOImpl;
import biblioteca.exception.LivroNaoEncontradoException;
import biblioteca.model.Livro;

/**
 * 
 * Controlador responsável por gerenciar as operações de negócio relacionadas aos livros.
 *
 * <p>
 * Esta classe atua como uma ponte entre a camada de visualização (View) e a camada
 * de acesso a dados (DAO). Ela recebe as requisições da interface do usuário
 * para adicionar, editar, remover, listar e buscar livros, aplicando as regras
 * de negócio necessárias e coordenando a persistência dos dados através do LivroDAO.
 * </p>
 *
 *
 * @author Eudes
 * @version 1.0
 * @since 1.0
 */
public class LivroController {

	/** O LivroDAO usado pelo controller. */
	private LivroDAO livroDAO;

	/** Uma instância do controller, característica do padrão Singleton */
	private static LivroController instancia;

	/**
	 * Cria uma nova instância de Livro controller.
	 */
	private LivroController() {
	    this.livroDAO = LivroDAOImpl.getInstance(); 
	}

	/**
	 * Retorna uma única instância de LivroController.
	 *
	 * @return Uma única instância de LivroController
	 */
	public static LivroController getInstance() {
		if (instancia == null) {
			instancia = new LivroController();
		}
		return instancia;
	}

	/**
	 * Cadastra um novo livro no sistema com os dados fornecidos.
	 *
	 * @param id            O ID único do livro.
	 * @param titulo        O título do livro.
	 * @param autor         O autor do livro.
	 * @param genero        O gênero do livro.
	 * @param anoLancamento O ano de lançamento.
	 * @param isbn          O código ISBN do livro.
	 * @param quantidade    A quantidade inicial em estoque.
	 * @throws IllegalArgumentException Se algum parâmetro for inválido.
	 */
	public void cadastrarLivro(String titulo, String autor, String genero, int anoLancamento, String isbn,
			int quantidade) throws IllegalArgumentException {
		if (titulo == null || titulo.isBlank() || autor == null || autor.isBlank() || genero.isBlank()
				|| genero == null) {
			throw new IllegalArgumentException("Dados inválidos para cadastro de livro");
		}
		Livro livro = new Livro(titulo, autor, genero, anoLancamento, isbn, quantidade);
		livroDAO.inserirLivro(livro);
	}

	
	/**
	 * Remove um livro do sistema com base no seu título.
	 *
	 * @param titulo O título do livro a ser removido.
	 * @throws LivroNaoEncontradoException Se nenhum livro for encontrado com o
	 * título especificado.
	 */
	public void removerLivro(String titulo) throws LivroNaoEncontradoException {
	    Livro livro = livroDAO.buscarPorNome(titulo);
	    if (livro == null)
	        throw new LivroNaoEncontradoException("O Livro com o título '" + titulo + "' não foi encontrado");

	    livroDAO.removerLivro(livro);
	}

	/**
	 * Busca e retorna um livro específico com base em seu código (ID) único.
	 *
	 * <p>
	 * Este método consulta a camada de acesso a dados (DAO) para encontrar um livro.
	 * Se o livro for encontrado, o objeto correspondente é retornado. Caso contrário,
	 * uma exceção customizada é lançada para indicar a falha na busca.
	 * </p>
	 *
	 * @param codigo O ID único do livro que se deseja encontrar.
	 * @return O objeto {@code Livro} correspondente ao código informado.
	 * @throws LivroNaoEncontradoException Lançada se não existir um livro com o
	 * código (ID) especificado no sistema.
	 */
	public Livro buscarLivroPorCodigo(String codigo) throws LivroNaoEncontradoException {
		Livro livro = livroDAO.buscarPorId(codigo);
		if (livro == null)
			throw new LivroNaoEncontradoException("O Livro com código " + codigo + " não foi encontrado");
		return livro;
	}

	/**
	 * <p>
	 * Listar livros.
	 *</p>
	 * @return uma lista com todos os livros.
	 */
	public List<Livro> listarLivros() {
		return livroDAO.listar();
	}

	/**
	 * Listar livros disponiveis.
	 *
	 * @return the list
	 */
	public List<Livro> listarLivrosDisponiveis() {
		List<Livro> disponiveis = new ArrayList<Livro>();
		for (Livro l : livroDAO.listar()) {
			if (l.getQuantidade() > 0)
				disponiveis.add(l);
		}
		return disponiveis;
	}

}
