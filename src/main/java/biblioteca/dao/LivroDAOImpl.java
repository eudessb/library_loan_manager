package biblioteca.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import biblioteca.exception.LivroNaoEncontradoException;
import biblioteca.model.Livro;
/**
 * Implementação em memória de LivroDAO usando o padrão Singleton.
 * Carrega os dados iniciais de um arquivo CSV.
 */
public class LivroDAOImpl implements LivroDAO {

	/** Instância única (Singleton) da classe. */
	private static LivroDAOImpl instancia;

	/** Mapa para armazenamento dos livros em memória, usando o título como chave. */
	private Map<String, Livro> livros = new LinkedHashMap<>();

	/**
	 * Construtor privado que carrega os livros do arquivo CSV ao ser instanciado.
	 */
	private LivroDAOImpl() {
		try {
			carregarLivros("src/main/resources/livros.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static LivroDAOImpl getInstance() {
		if (instancia == null) {
			instancia = new LivroDAOImpl();
		}
		return instancia;
	}

	/** {@inheritDoc} */
	@Override
	public void inserirLivro(Livro livro) {
		livros.put(livro.getIsbn(), livro);
	}

	/** {@inheritDoc} */
	@Override
	public List<Livro> buscarPorAutor(String nomeAutor) {
		List<Livro> livrosAutor = new ArrayList<>();
		for (Livro livro : livros.values()) {
			if (livro.getAutor().equalsIgnoreCase(nomeAutor)) {
				livrosAutor.add(livro);
			}
		}
		return livrosAutor;
	}

	/** {@inheritDoc} */
	@Override
	public Livro buscarPorNome(String string) throws LivroNaoEncontradoException {
		for (Livro livro : livros.values()) {
			if (livro.getNome().equalsIgnoreCase(string)) {
				return livro;
			}
		}

		throw new LivroNaoEncontradoException("Livro com titulo '" + string + "' não encontrado");
	}

	/** {@inheritDoc} */
	@Override
	public List<Livro> listar() {
		return new ArrayList<>(livros.values());
	}

	/** {@inheritDoc} */
	@Override
	public void removerLivro(Livro livro) {
		livros.remove(livro.getIsbn());
	}

	/** {@inheritDoc} */
	@Override
	public void removerPorId(String id) {
		for (Livro l : livros.values()) {
			if (l.getId() == id) {
				livros.remove(l.getIsbn());
			}
		}

	}

	/** {@inheritDoc} */
	@Override
	public void carregarLivros(String caminhoDoArquivo) throws IOException {
		BufferedReader leitor = new BufferedReader(new FileReader(caminhoDoArquivo));
		String linha;

		while ((linha = leitor.readLine()) != null) {
			String[] partes = linha.split(",");
			if (partes.length >= 6) {
				try {
					String nome = partes[0].trim();
					String autor = partes[1].trim();
					String genero = partes[2].trim();
					int anoLancamento = Integer.parseInt(partes[3].trim());
					String isbn = partes[4].trim();
					int quantidade = Integer.parseInt(partes[5].trim());

					Livro livro = new Livro(nome, autor, genero, anoLancamento, isbn, quantidade);
					livros.put(isbn, livro);

				} catch (NumberFormatException e) {
					System.err.println("Erro ao converter linha" + linha);

				}
			}
		}
		leitor.close();

	}

	/** {@inheritDoc} */
	@Override
	public Livro buscarPorId(String id) throws LivroNaoEncontradoException {
		for (Livro l : livros.values()) {
			if (l.getId() == id) {
				return l;
			}
		}
		return null;
	}

}
