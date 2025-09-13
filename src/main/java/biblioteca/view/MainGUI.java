	package biblioteca.view;

import javax.swing.SwingUtilities;

import biblioteca.controller.EmprestimoController;
import biblioteca.controller.LivroController;
import biblioteca.controller.UsuarioController;

/**
 * Classe principal que inicia a aplicação da biblioteca.
 *
 * Sua única responsabilidade é configurar os controladores e exibir
 * a tela de login inicial de forma segura na thread de eventos do Swing.
 *
 * @author Eudes
 * @version 1.0
 */
public class MainGUI {

    /**
     * Ponto de entrada principal da aplicação (método main).
     *
     * @param args Argumentos de linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        // Garante que a interface gráfica seja criada na Thread de Despacho de Eventos (EDT)
        SwingUtilities.invokeLater(() -> {
            UsuarioController controller = UsuarioController.getInstance();
            LivroController liController = LivroController.getInstance();
            EmprestimoController emController = new EmprestimoController();
            
            TelaLogin telaLogin = new TelaLogin(controller, liController, emController);
            telaLogin.setVisible(true);
        });
    }
}