package biblioteca.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import biblioteca.controller.LivroController;
/**
 * Janela da interface gráfica para administradores adicionarem e removerem
 * livros do acervo.
 * * @author Eudes
 * @version 1.0
 */

public class TelaGerenciarLivros extends JFrame {
	  /** ID de versão para serialização. */
    private static final long serialVersionUID = 1L;
    
    /** Controlador para gerenciar a lógica de negócio dos livros. */
    private LivroController livroController;

    /** Campos de texto para entrada de dados de cadastro e remoção de livros. */
    private JTextField campoNome;
    private JTextField campoAutor;
    private JTextField campoGenero;
    private JTextField campoAno;
    private JTextField campoISBN;
    private JTextField campoQuantidade;
    private JTextField campoRemocao;

    /**
     * Constrói a tela de gerenciamento de livros.
     * @param livroController O controlador para as operações de livros.
     */
    public TelaGerenciarLivros(LivroController livroController) {
        this.livroController = livroController;

        setTitle("Gerenciar Livros");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painelCampos = new JPanel(new GridLayout(7, 2, 5, 5));
        painelCampos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        campoNome = new JTextField();
        campoAutor = new JTextField();
        campoGenero = new JTextField();
        campoAno = new JTextField();
        campoISBN = new JTextField();
        campoQuantidade = new JTextField();

        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(campoNome);
        painelCampos.add(new JLabel("Autor:"));
        painelCampos.add(campoAutor);
        painelCampos.add(new JLabel("Gênero:"));
        painelCampos.add(campoGenero);
        painelCampos.add(new JLabel("Ano de Lançamento:"));
        painelCampos.add(campoAno);
        painelCampos.add(new JLabel("ISBN:"));
        painelCampos.add(campoISBN);
        painelCampos.add(new JLabel("Quantidade:"));
        painelCampos.add(campoQuantidade);

        JButton btnCadastrar = new JButton("Cadastrar Livro");
        btnCadastrar.addActionListener(e -> cadastrarLivro());

        JPanel painelCadastro = new JPanel(new FlowLayout());
        painelCadastro.add(btnCadastrar);

        // Seção de Remoção
        JPanel painelRemocao = new JPanel(new FlowLayout());
        campoRemocao = new JTextField(15);
        JButton btnRemover = new JButton("Remover Livro por Título");
        painelRemocao.add(new JLabel("Título para Remoção:"));
        painelRemocao.add(campoRemocao);
        painelRemocao.add(btnRemover);

        btnRemover.addActionListener(e -> removerLivro());

        // Adiciona à tela
        add(painelCampos, BorderLayout.NORTH);
        add(painelCadastro, BorderLayout.CENTER);
        add(painelRemocao, BorderLayout.SOUTH);
    }
    /**
     * Coleta os dados dos campos de cadastro, chama o controller para criar
     * um novo livro e exibe o resultado.
     */
    private void cadastrarLivro() {
        try {
            String nome = campoNome.getText();
            String autor = campoAutor.getText();
            String genero = campoGenero.getText();
            int ano = Integer.parseInt(campoAno.getText());
            String isbn = campoISBN.getText();
            int quantidade = Integer.parseInt(campoQuantidade.getText());

            livroController.cadastrarLivro(nome, autor, genero, ano, isbn, quantidade);

            JOptionPane.showMessageDialog(this, "Livro cadastrado com sucesso!");
            limparCampos();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Erro: Ano e quantidade devem ser números.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao cadastrar livro: " + ex.getMessage());
        }
    }

    private void removerLivro() {
        String titulo = campoRemocao.getText();
        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Informe o título do livro a ser removido.");
            return;
        }

        try {
            livroController.removerLivro(titulo);
            JOptionPane.showMessageDialog(this, "Livro removido com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao remover livro: " + ex.getMessage());
        }
    }

    /**
     * Limpa todos os campos de texto do formulário de cadastro.
     */
    private void limparCampos() {
        campoNome.setText("");
        campoAutor.setText("");
        campoGenero.setText("");
        campoAno.setText("");
        campoISBN.setText("");
        campoQuantidade.setText("");
    }
}
