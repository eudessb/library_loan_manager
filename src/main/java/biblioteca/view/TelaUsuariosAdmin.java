package biblioteca.view;

import java.awt.BorderLayout;
import javax.swing.*;

/**
 * Janela da interface gráfica para administradores gerenciarem os usuários 
 * do sistema (adicionar, editar, remover).
 * * @author Eudes
 * @version 1.0
 */
public class TelaUsuariosAdmin extends JFrame {
	
	/** ID de versão para serialização. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constrói a tela de gerenciamento de usuários para administradores.
	 */
	public TelaUsuariosAdmin() {
		setTitle("Gerenciar Usuários");
		setSize(600, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		// Painel principal
		JPanel painel = new JPanel(new BorderLayout(10, 10));
		painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Tabela para listar usuários
		String[] colunas = {"Nome", "Email", "Tipo"};
		Object[][] dados = {}; // Os dados seriam carregados do controller
		JTable tabela = new JTable(dados, colunas);
		painel.add(new JScrollPane(tabela), BorderLayout.CENTER);

		// Painel com botões de ação
		JPanel botoes = new JPanel();
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(e -> {
			// Ação para abrir a tela de cadastro de novo usuário.
			JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento.");
		});
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(e -> {
			// Ação para editar um usuário selecionado.
			JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento.");
		});
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(e -> {
			// Ação para remover um usuário selecionado.
			JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento.");
		});
		
		botoes.add(btnAdicionar);
		botoes.add(btnEditar);
		botoes.add(btnRemover);

		painel.add(botoes, BorderLayout.SOUTH);

		add(painel);
	}
}