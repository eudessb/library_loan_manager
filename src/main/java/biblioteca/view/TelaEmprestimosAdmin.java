package biblioteca.view;

import java.awt.BorderLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import biblioteca.controller.EmprestimoController;
import biblioteca.model.Emprestimo;

/**
 * Janela da interface gráfica para administradores gerenciarem todos os
 * empréstimos do sistema.
 *  @author Eudes
 * @version 1.0
 */
public class TelaEmprestimosAdmin extends JFrame {

	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;
	
	/** Controlador para gerenciar a lógica de empréstimos. */
	private EmprestimoController emprestimoController;

	/** Tabela para exibir os dados dos empréstimos. */
	private JTable tabela;
	
	/** Modelo de dados para a JTable de empréstimos. */
	private DefaultTableModel modelo;

	/**
	 * Constrói a tela de gerenciamento de empréstimos para administradores.
	 * * @param emprestimoController O controlador para as operações de empréstimo.
	 */
	public TelaEmprestimosAdmin(EmprestimoController emprestimoController) {
		this.emprestimoController = emprestimoController;
		setTitle("Gerenciar Empréstimos");
		setSize(800, 400); 
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel painel = new JPanel(new BorderLayout(10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Define o modelo e a tabela de empréstimos
		String[] colunas = { "Usuário", "Livro", "Data Empréstimo", "Data Devolução Prevista", "Data Devolução" };
		modelo = new DefaultTableModel(colunas, 0);
		tabela = new JTable(modelo);
		painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

		// Define o painel de botões
		JPanel botoes = new JPanel();
		JButton btnNovoEmprestimo = new JButton("Novo Empréstimo");
		btnNovoEmprestimo.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento.");
		});
		JButton btnDevolver = new JButton("Devolver Livro");
		btnDevolver.addActionListener(e -> {
			JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento.");
		});
		botoes.add(btnNovoEmprestimo);
		botoes.add(btnDevolver);
		painel.add(botoes, BorderLayout.SOUTH);

		add(painel);

		// Carrega os dados iniciais na tabela
		carregarEmprestimos();
	}

	/**
	 * Carrega os dados de todos os empréstimos a partir do controller e preenche a
	 * tabela.
	 */
	private void carregarEmprestimos() {
		// Limpa a tabela antes de carregar os dados
		modelo.setRowCount(0);

		// Busca os empréstimos no controller e os adiciona na tabela
		List<Emprestimo> emprestimos = emprestimoController.listarEmprestimos(); 
		for (Emprestimo e : emprestimos) {
			modelo.addRow(new Object[] {
					e.getUsuario().getNome(),
					e.getLivro().getNome(),
					e.getDataEmprestimo(),
					e.getDataPrevistaDevolucao(),
					e.getDataDevolucao()
			});
		}
	}
}