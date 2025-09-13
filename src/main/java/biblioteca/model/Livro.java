package biblioteca.model;

import java.util.UUID;

/**
 * Representa um livro com id, nome, autor, genêro, ano de lançamento, código isbn e quantidade.
 * É usada para registrar livros no sistema.
 * * @author Eudes Silva
 * @version 1.0
 */
public class Livro implements Emprestavel {

	/** Identificador único do livro. */
	private final String id;

	/** O título do livro. */
	private String nome;

	/** O autor do livro. */
	private String autor;

	/** O gênero literário do livro. */
	private String genero;

	/** O ano de publicação do livro. */
	private int anoLancamento;

	/** O código ISBN do livro. */
	private String isbn;

	/** A quantidade de exemplares disponíveis em estoque. */
	private int quantidade;

	/**
	 * Constrói uma nova instância de Livro.
	 * @param nome O título do livro.
	 * @param autor O autor do livro.
	 * @param genero O gênero do livro.
	 * @param anoLancamento O ano de lançamento.
	 * @param isbn O código ISBN.
	 * @param quantidade A quantidade em estoque.
	 */
	public Livro( String nome, String autor, String genero, int anoLancamento, String isbn, int quantidade) {
		super();
        this.id = UUID.randomUUID().toString();
		this.nome = nome;
		this.autor = autor;
		this.genero = genero;
		this.anoLancamento = anoLancamento;
		this.isbn = isbn;
		this.quantidade = quantidade;
	}

	/**
	 * @return O título do livro.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome O novo título do livro.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return O autor do livro.
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * @param autor O novo autor do livro.
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * @return O gênero do livro.
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @param genero O novo gênero do livro.
	 */
	public void setGenero(String genero) {
		this.genero = genero;
	}

	/**
	 * @return O ano de lançamento do livro.
	 */
	public int getAnoLancamento() {
		return anoLancamento;
	}

	/**
	 * @param anoLancamento O novo ano de lançamento.
	 */
	public void setAnoLancamento(int anoLancamento) {
		this.anoLancamento = anoLancamento;
	}

	/**
	 * @return A quantidade de exemplares em estoque.
	 */
	public int getQuantidade() {
		return quantidade;
	}

	/**
	 * @param quantidade A nova quantidade em estoque.
	 */
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	/**
	 * @return O código ISBN do livro.
	 */
	public String getIsbn() {
		return isbn;
	}

	/**
	 * @param isbn O novo código ISBN.
	 */
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	/**
	 * @return O ID do livro.
	 */
	public String getId() {
		return id;
	}

	
	/**
	 * Decrementa em uma unidade a quantidade de exemplares em estoque.
	 */
	public void diminuirQuantidade() {
		if (getQuantidade() > 0)
			quantidade--;
	}

	/**
	 * Incrementa em uma unidade a quantidade de exemplares em estoque.
	 */
	public void aumentarQuantidade() {
		quantidade++;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean EstaDisponivel() {
		return quantidade > 0;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void Emprestar() {
		if (quantidade <= 0) { 
			throw new IllegalStateException("Livro não disponível para empréstimo");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void devolver() {
		aumentarQuantidade();
	}
}