package biblioteca.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import biblioteca.controller.EmprestimoController;
import biblioteca.model.Emprestimo;
import biblioteca.model.Multavel;
import biblioteca.model.Usuario;

/**
 * Janela da interface gráfica para um usuário comum visualizar e gerenciar
 * seus empréstimos ativos.
 * @author Eudes
 * @version 1.0
 */
public class TelaEmprestimosComum extends JFrame {

    /** ID de versão para serialização. */
    private static final long serialVersionUID = 1L;
    
    /** O usuário logado cujos empréstimos estão sendo exibidos. */
    @SuppressWarnings("unused")
	private Usuario usuario;
    
    /** Controlador para gerenciar a lógica de empréstimos. */
    @SuppressWarnings("unused")
	private EmprestimoController emprestimoController;

    /**
     * Constrói a tela de gerenciamento de empréstimos do usuário.
     *
     * @param usuario O usuário logado no sistema.
     * @param emprestimoController O controlador para as operações de empréstimo.
     */
    public TelaEmprestimosComum(Usuario usuario, EmprestimoController emprestimoController) {
        this.usuario = usuario;
        this.emprestimoController = emprestimoController;

        setTitle("Meus Empréstimos");
        setSize(600, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Cria a tabela de empréstimos
        String[] colunas = { "Título", "Autor", "Data de Empréstimo" };
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        // Cria o painel de botões de ação
        JPanel painelBotoes = new JPanel(new FlowLayout());
        JButton btnDevolver = new JButton("Devolver Livro");
        JButton btnPagarMulta = new JButton("Pagar Multa");
        JButton btnFechar = new JButton("Fechar");

        painelBotoes.add(btnDevolver);
        painelBotoes.add(btnPagarMulta);
        painelBotoes.add(btnFechar);
        add(painelBotoes, BorderLayout.SOUTH);

        // Define a lógica para atualizar a tabela de empréstimos
        Runnable atualizarTabela = () -> {
            modelo.setRowCount(0);
            List<Emprestimo> atualizados = emprestimoController.listarEmprestimosPorUsuario(usuario);
            for (Emprestimo e : atualizados) {
                modelo.addRow(new Object[] {
                        e.getLivro().getNome(),
                        e.getLivro().getAutor(),
                        e.getDataEmprestimo().toString()
                });
            }
        };

        atualizarTabela.run(); // Carrega os dados na tabela pela primeira vez

        // Ação do botão Fechar
        btnFechar.addActionListener(e -> dispose());

        // Ação do botão Devolver
        btnDevolver.addActionListener(e -> {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um empréstimo para devolver.");
                return;
            }

            String titulo = (String) modelo.getValueAt(linhaSelecionada, 0);
            String mensagem = emprestimoController.devolverLivro(usuario, titulo);
            JOptionPane.showMessageDialog(this, mensagem);
            atualizarTabela.run();

            // Verifica se há multa pendente após a devolução
            if (usuario instanceof Multavel multavel && multavel.getValorMulta() > 0) {
                JOptionPane.showMessageDialog(this,
                        "Atenção: você possui multa pendente.\n" +
                        "Valor acumulado: R$ " + String.format("%.2f", multavel.getValorMulta()),
                        "Multa Aplicada", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Ação do botão Pagar Multa
        btnPagarMulta.addActionListener(e -> {
            if (usuario instanceof Multavel multavel) {
                double valor = multavel.getValorMulta();
                if (valor <= 0) {
                    JOptionPane.showMessageDialog(this, "Você não possui multas pendentes.");
                } else {
                    // Simula o pagamento zerando o valor da multa
                    multavel.setValorMulta(0.0); 
                    JOptionPane.showMessageDialog(this, "Multa de R$ " +
                            String.format("%.2f", valor) + " paga com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Este tipo de usuário não possui multas.");
            }
        });
    }
}