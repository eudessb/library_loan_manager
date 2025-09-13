package biblioteca.dao;

import java.io.IOException;
import java.util.List;

import biblioteca.exception.LivroNaoEncontradoException;
import biblioteca.model.Livro;

/**
 * Define o contrato para operações de persistência de livros.
 */
public interface LivroDAO {

	/**
	 * Adiciona ou atualiza um livro na fonte de dados.
	 * @param livro O livro a ser salvo.
	 */
	void inserirLivro(Livro livro);

	/**
	 * Busca livros de um autor específico.
	 * @param nomeAutor O nome do autor.
	 * @return Lista de livros do autor.
	 */
	List<Livro> buscarPorAutor(String nomeAutor);

	/**
	 * Busca um livro pelo seu título.
	 * @param titulo O título a ser buscado.
	 * @return O livro encontrado.
	 * @throws LivroNaoEncontradoException Se o livro não for encontrado.
	 */
	Livro buscarPorNome(String titulo) throws LivroNaoEncontradoException;

	/**
	 * Busca um livro pelo seu ID.
	 * @param id O ID do livro.
	 * @return O livro encontrado.
	 * @throws LivroNaoEncontradoException Se o livro não for encontrado.
	 */
	Livro buscarPorId(String id) throws LivroNaoEncontradoException;

	/**
	 * Retorna todos os livros cadastrados.
	 * @return Lista com todos os livros.
	 */
	List<Livro> listar();

	/**
	 * Remove um livro da fonte de dados.
	 * @param livro O livro a ser removido.
	 */
	void removerLivro(Livro livro);

	/**
	 * Remove um livro pelo seu ID.
	 * @param id O ID do livro a ser removido.
	 */
	void removerPorId(String id);

	/**
	 * Carrega a lista de livros a partir de um arquivo.
	 * @param caminhoDoArquivo O caminho do arquivo a ser lido.
	 * @throws IOException Em caso de erro na leitura do arquivo.
	 */
	void carregarLivros(String caminhoDoArquivo) throws IOException;
}