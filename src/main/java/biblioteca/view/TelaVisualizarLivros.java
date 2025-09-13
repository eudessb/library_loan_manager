package biblioteca.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import biblioteca.controller.EmprestimoController;
import biblioteca.controller.LivroController;
import biblioteca.model.Livro;
import biblioteca.model.Multavel;
import biblioteca.model.Usuario;

/**
 * Janela da interface gráfica para visualização e interação com a lista de
 * livros.  
 * 
 * @author Eudes
 * @version 1.0
 */
public class TelaVisualizarLivros extends JFrame {

	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;

	/** O usuário atualmente logado no sistema. */
	@SuppressWarnings("unused")
	private Usuario usuarioLogado;

	/** Tabela para exibir os dados dos livros. */
	private JTable tabelaLivros;

	/** Controlador para gerenciar as operações de livros. */
	private LivroController livroController;

	/** Modelo de dados para a JTable de livros. */
	private DefaultTableModel modelo;

	/**
	 * Constrói a tela de visualização de livros.
	 *
	 * @param usuario         O usuário logado, para controle de permissões.
	 * @param livroController O controlador de livros para obter os dados.
	 * @param empController   O controlador de empréstimos para realizar ações na
	 *                        tela.
	 */
	public TelaVisualizarLivros(Usuario usuario, LivroController livroController, EmprestimoController empController) {
		this.usuarioLogado = usuario;
		this.livroController = livroController;

		setTitle("Livros Disponíveis");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());

		// Modelo da tabela
		modelo = new DefaultTableModel(new String[] { "Título", "Autor", "Ano", "Disponível" }, 0);
		tabelaLivros = new JTable(modelo);
		JScrollPane scrollPane = new JScrollPane(tabelaLivros);
		add(scrollPane, BorderLayout.CENTER);

		// Painel inferior com botão de atualizar
		JPanel painelInferior = new JPanel();
		JButton btnAtualizar = new JButton("Atualizar");
		painelInferior.add(btnAtualizar);
		add(painelInferior, BorderLayout.SOUTH);

		btnAtualizar.addActionListener(e -> carregarLivros());

		carregarLivros();

		JButton btnEmprestar = new JButton("Pedir Empréstimo");
		painelInferior.add(btnEmprestar);

		btnEmprestar.addActionListener(e -> {
			int linhaSelecionada = tabelaLivros.getSelectedRow();
			if (linhaSelecionada == -1) {
				JOptionPane.showMessageDialog(this, "Selecione um livro para empréstimo.");
				return;
			}

			// VERIFICAÇÃO DE MULTA
			if (usuario instanceof Multavel multavel && multavel.getValorMulta() > 0) {
				JOptionPane.showMessageDialog(this,
						"Empréstimo bloqueado.\nMulta pendente de R$ "
								+ String.format("%.2f", multavel.getValorMulta()),
						"Multa Pendente", JOptionPane.WARNING_MESSAGE);
				return;
			}

			String titulo = (String) modelo.getValueAt(linhaSelecionada, 0);
			try {
				empController.realizarEmprestimo(usuario, titulo);
				JOptionPane.showMessageDialog(this, "Empréstimo realizado com sucesso!");
				carregarLivros();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
			}
		});

	}

	/**
	 * Carregar livros.
	 */
	private void carregarLivros() {
		modelo.setRowCount(0); // limpa a tabela
		for (Livro livro : livroController.listarLivros()) {
			modelo.addRow(new Object[] { livro.getNome(), livro.getAutor(), livro.getAnoLancamento(),
					livro.EstaDisponivel() ? "Sim" : "Não" });
		}
	}
}
