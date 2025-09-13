package biblioteca.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import biblioteca.controller.LivroController;
import biblioteca.model.Livro;
import java.awt.*;

/**
 * Janela da interface gráfica para administradores gerenciarem os livros do acervo
 * (adicionar, editar, remover).
 * * @author Eudes
 * @version 1.0
 */
public class TelaLivrosAdmin extends JFrame {

	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;
	
	/** Tabela para exibir os dados dos livros. */
	private JTable tabela;
	
	/** Modelo de dados para a JTable de livros. */
	private DefaultTableModel modelo;
	
	/** Controlador para gerenciar a lógica de negócio dos livros. */
	private LivroController livroController;

	/**
	 * Constrói a tela de gerenciamento de livros para administradores.
	 * @param livroController O controlador para as operações de livros.
	 */
	public TelaLivrosAdmin(LivroController livroController) {
		this.livroController = livroController;
		setTitle("Gerenciar Livros");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel painel = new JPanel(new BorderLayout(10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Tabela para exibir os livros
		String[] colunas = { "Título", "Autor", "Gênero", "Ano", "ISBN", "Quantidade" };
		modelo = new DefaultTableModel(colunas, 0);
		tabela = new JTable(modelo);
		painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

		// Painel com botões de ação
		JPanel botoes = new JPanel();
		JButton btnGerenciarLivros = new JButton("Gerenciar");
		btnGerenciarLivros.addActionListener(e -> {
		    new TelaGerenciarLivros(livroController).setVisible(true);
		});
		
		botoes.add(btnGerenciarLivros);
		painel.add(botoes, BorderLayout.SOUTH);
		add(painel);
		

		carregarLivros();
	}

	/**
	 * Carrega os dados de todos os livros a partir do controller e preenche a tabela.
	 */
	private void carregarLivros() {
		// Limpa a tabela antes de carregar novos dados
		modelo.setRowCount(0); 
		
		for (Livro livro : livroController.listarLivros()) {
			modelo.addRow(new Object[] { livro.getNome(), livro.getAutor(), livro.getGenero(), livro.getAnoLancamento(),
					livro.getIsbn(), livro.getQuantidade() });
		}
	}
}