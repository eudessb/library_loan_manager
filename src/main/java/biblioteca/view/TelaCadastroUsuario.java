package biblioteca.view;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import biblioteca.controller.UsuarioController;
import biblioteca.exception.EmailJaCadastradoException;

/**
 * Janela da interface gráfica para o cadastro de novos usuários no sistema.
 * Permite a criação de Alunos, Professores e Administradores.  
 * 
 * @author Eudes
 * @version 1.0
 */
public class TelaCadastroUsuario extends JFrame {

	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;

	/** Componentes da interface gráfica para entrada de dados do usuário. */
	private JTextField campoNome;
	private JTextField campoEmail;
	private JPasswordField campoSenha;
	private JComboBox<String> comboTipoUsuario;
	private JTextField campoCurso; // para aluno
	private JTextField campoSemestre; // para aluno
	private JTextField campoSituacao; // para aluno
	private JTextField campoTitulacao; // para professor

	/** Controlador para gerenciar a lógica de cadastro de usuários. */
	private UsuarioController usuarioController;

	/**
	 * Constrói e inicializa a tela de cadastro de usuário.
	 * 
	 * @param controller O controlador de usuários para a lógica de cadastro.
	 */
	public TelaCadastroUsuario(UsuarioController controller) {
		this.usuarioController = controller;

		setTitle("Cadastro de Usuário");
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(9, 2, 10, 10));

		add(new JLabel("Nome:"));
		campoNome = new JTextField();
		add(campoNome);

		add(new JLabel("Email:"));
		campoEmail = new JTextField();
		add(campoEmail);

		add(new JLabel("Senha:"));
		campoSenha = new JPasswordField();
		add(campoSenha);

		add(new JLabel("Tipo de usuário:"));
		comboTipoUsuario = new JComboBox<>(new String[] { "Aluno", "Professor", "Administrador" });
		add(comboTipoUsuario);

		// Campos exclusivos para aluno
		add(new JLabel("Curso (Aluno):"));
		campoCurso = new JTextField();
		add(campoCurso);

		add(new JLabel("Semestre (Aluno):"));
		campoSemestre = new JTextField();
		add(campoSemestre);

		add(new JLabel("Situação (Aluno):"));
		campoSituacao = new JTextField();
		add(campoSituacao);

		// Campo exclusivo para professor
		add(new JLabel("Titulação (Professor):"));
		campoTitulacao = new JTextField();
		add(campoTitulacao);

		JButton btnCadastrar = new JButton("Cadastrar");
		add(btnCadastrar);

		JButton btnCancelar = new JButton("Cancelar");
		add(btnCancelar);

		// Listeners de eventos
		comboTipoUsuario.addActionListener(e -> ajustarCampos());
		btnCadastrar.addActionListener(e -> cadastrarUsuario());
		btnCancelar.addActionListener(e -> this.dispose());

		ajustarCampos(); // Ajusta a visibilidade inicial dos campos
	}

	/**
	 * Ajusta a visibilidade e o estado dos campos do formulário com base no tipo de
	 * usuário selecionado no JComboBox.
	 */
	private void ajustarCampos() {
		String tipo = (String) comboTipoUsuario.getSelectedItem();

		if ("Aluno".equals(tipo)) {
			campoCurso.setEnabled(true);
			campoSemestre.setEnabled(true);
			campoSituacao.setEnabled(true);
			campoTitulacao.setEnabled(false);
		} else if ("Administrador".equals(tipo)) {
			campoCurso.setEnabled(false);
			campoSemestre.setEnabled(false);
			campoSituacao.setEnabled(false);
			campoTitulacao.setEnabled(false);
		} else if ("Professor".equals(tipo)) {
			campoCurso.setEnabled(false);
			campoSemestre.setEnabled(false);
			campoSituacao.setEnabled(false);
			campoTitulacao.setEnabled(true);
		}
	}

	/**
	 * Coleta os dados dos campos, valida o tipo de usuário e chama o método de
	 * cadastro correspondente no controller. Trata exceções exibindo diálogos de
	 * erro.
	 */
	private void cadastrarUsuario() {
		String nome = campoNome.getText();
		String email = campoEmail.getText();
		String senha = new String(campoSenha.getPassword());
		String tipo = (String) comboTipoUsuario.getSelectedItem();

		try {
			if (tipo.equals("Aluno")) {
				String curso = campoCurso.getText();
				int semestre = Integer.parseInt(campoSemestre.getText());
				String situacao = campoSituacao.getText();

				usuarioController.cadastrarAluno(nome, email, senha, curso, semestre, situacao);
			} else if (tipo.equals("Professor")) {
				String titulacao = campoTitulacao.getText();
				usuarioController.cadastrarProfessor(nome, email, senha, titulacao);
			} else if (tipo.equals("Administrador")) {
				usuarioController.cadastrarAdministrador(nome, email, senha);
			}
			JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
			this.dispose();
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Semestre deve ser um número válido.", "Erro",
					JOptionPane.ERROR_MESSAGE);
		} catch (EmailJaCadastradoException ex) {
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage(), "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}