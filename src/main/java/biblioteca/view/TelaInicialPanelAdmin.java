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
 * Janela principal que serve como painel de controle (dashboard) para o
 * administrador. Oferece acesso às principais funcionalidades de gerenciamento
 * do sistema.
 *
 * @author Eudes
 * @version 1.0
 */
public class TelaInicialPanelAdmin extends JFrame {

	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constrói o painel principal do administrador.
	 *
	 * @param usuario      O administrador logado.
	 * @param livroCtr     O controlador de livros para passar às telas de
	 *                     gerenciamento.
	 * @param emController O controlador de empréstimos para passar às telas de
	 *                     gerenciamento.
	 */
	public TelaInicialPanelAdmin(Usuario usuario, UsuarioController usuarioController, LivroController livroCtr,
			EmprestimoController emController) {
		setTitle("Menu Principal - Sistema de Biblioteca");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel painel = new JPanel();
		painel.setLayout(new GridLayout(4, 1, 10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

		// Botões de navegação para as telas de gerenciamento
		JButton btnUsuarios = new JButton("Gerenciar Usuários");
		JButton btnLivros = new JButton("Gerenciar Livros");
		JButton btnEmprestimos = new JButton("Gerenciar Empréstimos");
		JButton btnVerLivros = new JButton("Ver livros disponíveis");
		JButton btnSair = new JButton("Sair");
		JButton btnLogout = new JButton("Logout");
		
		painel.add(btnUsuarios);
		painel.add(btnLivros);
		painel.add(btnEmprestimos);
		painel.add(btnVerLivros);
		painel.add(btnSair);

		painel.add(btnLogout);
		add(painel);

		// Ação do botão para gerenciar usuários
		btnUsuarios.addActionListener(e -> {
			TelaUsuariosAdmin telaUsuarios = new TelaUsuariosAdmin();
			telaUsuarios.setVisible(true);
		});

		// Ação do botão para gerenciar livros
		btnLivros.addActionListener(e -> {
			TelaLivrosAdmin telaLivros = new TelaLivrosAdmin(livroCtr);
			telaLivros.setVisible(true);
		});

		// Ação do botão para gerenciar empréstimos
		btnEmprestimos.addActionListener(e -> {
			TelaEmprestimosAdmin telaEmprestimos = new TelaEmprestimosAdmin(emController);
			telaEmprestimos.setVisible(true);
		});

		// Ação do botão para visualizar todos os livros
		btnVerLivros.addActionListener(e -> {
			TelaVisualizarLivros telaLivros = new TelaVisualizarLivros(usuario, livroCtr, emController);
			telaLivros.setVisible(true);
		});

		btnLogout.addActionListener(e -> {
			dispose();
			new TelaLogin(usuarioController, livroCtr, emController).setVisible(true); // Retorna à tela de login
		});

		// Ação do botão para sair do sistema
		btnSair.addActionListener(e -> {
			dispose();
			System.exit(0);
		});
	}
}