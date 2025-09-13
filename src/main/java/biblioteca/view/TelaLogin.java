package biblioteca.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import biblioteca.controller.EmprestimoController;
import biblioteca.controller.LivroController;
import biblioteca.controller.UsuarioController;
import biblioteca.exception.UsuarioNaoEncontradoException;
import biblioteca.model.TipoUsuario;
import biblioteca.model.Usuario;


/**
 * Janela da interface gráfica responsável pela autenticação do usuário no
 * sistema. * @author Eudes
 * 
 * @version 1.0
 */
public class TelaLogin extends JFrame {

	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;

	/** Componentes da interface gráfica para entrada de dados e ações. */
	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JButton botaoEntrar;
	private JButton botaoCadastrar;
	private JLabel mensagemErro;

	/** Controladores para gerenciar a lógica de negócio da aplicação. */
	private UsuarioController usuarioController;
	private LivroController livroController;
	private EmprestimoController emprestimoController;

	/**
	 * Constrói e inicializa a tela de login.
	 * 
	 * @param controller   O controlador de usuários para a lógica de autenticação.
	 * @param liController O controlador de livros a ser passado para as próximas
	 *                     telas.
	 * @param emController O controlador de empréstimos a ser passado para as
	 *                     próximas telas.
	 */
	public TelaLogin(UsuarioController controller, LivroController liController, EmprestimoController emController) {
		this.usuarioController = controller;
		this.livroController = liController;
		this.emprestimoController = emController;
		this.setTitle("Login - Biblioteca");
		setSize(400, 250);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Layout principal
		setLayout(new BorderLayout(10, 10));

		// Painel de campos (email e senha)
		JPanel painelCampos = new JPanel(new GridLayout(4, 1, 5, 5));
		painelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
		painelCampos.add(new JLabel("Email:"));
		campoEmail = new JTextField();
		painelCampos.add(campoEmail);
		painelCampos.add(new JLabel("Senha:"));
		campoSenha = new JPasswordField();
		painelCampos.add(campoSenha);

		add(painelCampos, BorderLayout.CENTER);

		// Painel de botões (entrar e cadastrar)
		JPanel painelBotoes = new JPanel(new FlowLayout());
		botaoEntrar = new JButton("Entrar");
		botaoCadastrar = new JButton("Cadastrar");
		painelBotoes.add(botaoEntrar);
		painelBotoes.add(botaoCadastrar);

		add(painelBotoes, BorderLayout.SOUTH);

		// Label de erro (acima dos botões)
		mensagemErro = new JLabel("", SwingConstants.CENTER);
		mensagemErro.setForeground(Color.RED);
		add(mensagemErro, BorderLayout.NORTH);

		// Ações dos botões
		botaoEntrar.addActionListener(e -> fazerLogin());
		botaoCadastrar.addActionListener(e -> abrirCadastro());

	}

	/**
	 * Tenta autenticar o usuário com os dados inseridos e, se bem-sucedido, abre a
	 * tela principal correspondente ao tipo de usuário.
	 */
	private void fazerLogin() {
		String email = campoEmail.getText();
		String senha = new String(campoSenha.getPassword());

		try {
			Usuario usuario = usuarioController.autenticar(email, senha);
			mensagemErro.setText("");
			if (usuario.getTipo() == TipoUsuario.ADMIN) {
				TelaInicialPanelAdmin menu = new TelaInicialPanelAdmin(usuario,usuarioController, livroController, emprestimoController);
				menu.setVisible(true);
			} else {
				TelaInicialPanelComum menuComum = new TelaInicialPanelComum(usuario, usuarioController,livroController,
						emprestimoController);
				menuComum.setVisible(true);
			}

			this.dispose();
		} catch (UsuarioNaoEncontradoException e) {
			mensagemErro.setText(e.getMessage());
		}
	}

	/**
	 * Abre a janela de cadastro de novos usuários.
	 */
	private void abrirCadastro() {
		new TelaCadastroUsuario(usuarioController).setVisible(true);
	}
}