package biblioteca.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import biblioteca.controller.EmprestimoController;
import biblioteca.controller.LivroController;
import biblioteca.controller.UsuarioController;
import biblioteca.model.Usuario;

/**
 * Janela principal que serve como painel de controle para um usuário comum.
 * Oferece acesso às funcionalidades básicas do sistema, como visualizar livros
 * e empréstimos.
 *
 * @author Eudes
 * @version 1.0
 */
public class TelaInicialPanelComum extends JFrame {

	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;

	/** O usuário logado e os controladores necessários para as operações. */
	private Usuario usuarioLogado;
	private LivroController livroController;
	private EmprestimoController emprestimoController;

	/**
	 * Constrói o painel principal do usuário comum.
	 *
	 * @param usuario       O usuário logado no sistema.
	 * @param liController  O controlador de livros a ser passado para as próximas
	 *                      telas.
	 * @param empController O controlador de empréstimos a ser passado para as
	 *                      próximas telas.
	 */
	public TelaInicialPanelComum(Usuario usuario, UsuarioController usrController, LivroController liController,
			EmprestimoController empController) {
		this.usuarioLogado = usuario;
		this.livroController = liController;
		this.emprestimoController = empController;

		setTitle("Menu do Usuário - Biblioteca");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(3, 1, 10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

		// Botões de navegação para as funcionalidades do usuário
		JButton btnVisualizarLivros = new JButton("Visualizar Livros Disponíveis");
		JButton btnMeusEmprestimos = new JButton("Meus Empréstimos");
		JButton btnSair = new JButton("Sair");
		JButton btnLogout = new JButton("Logout");

		painel.add(btnLogout);
		painel.add(btnVisualizarLivros);
		painel.add(btnMeusEmprestimos);
		painel.add(btnSair);

		add(painel);

		// Ação para visualizar todos os livros
		btnVisualizarLivros.addActionListener(e -> {
			new TelaVisualizarLivros(usuarioLogado, livroController, emprestimoController).setVisible(true);
		});

		// Ação para visualizar os próprios empréstimos
		btnMeusEmprestimos.addActionListener(e -> {
			new TelaEmprestimosComum(usuarioLogado, emprestimoController).setVisible(true);
		});
		
		//Ação para deslogar
		btnLogout.addActionListener(e -> {
			dispose(); // Fecha a tela atual
			new TelaLogin(usrController, liController, empController).setVisible(true); // Retorna à tela de login
		});
		
		// Ação para sair do sistema
		btnSair.addActionListener(e -> {
			this.dispose();
			System.exit(0);
		});
	}
}