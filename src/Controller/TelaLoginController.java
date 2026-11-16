package Controller;

// Bibliotecas utilizadas
import Model.Usuario;
import View.TelaPrincipalFX;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// Classe responsável pelo controle da tela de login
public class TelaLoginController {
    // Atributo que armazena o usuário atualmente logado
    private static Usuario usuarioLogado;

    // Campos da tela de login
    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private Label mensagemErro;

    // Controller compartilhado para manter os usuários cadastrados
    private static UsuarioController usuarioController = new UsuarioController();

    // Método responsável por realizar o login
    @FXML
    private void realizarLogin() {
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            mensagemErro.setText("Preencha todos os campos.");
            return;
        }

        Usuario usuario = usuarioController.autenticarUsuario(email, senha);

        if (usuario != null) {
            usuarioLogado = usuario;

            mensagemErro.setText("");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TelaPrincipal.fxml"));

                Parent tela = loader.load();

                Stage stage = (Stage) campoEmail.getScene().getWindow();

                stage.setScene(new Scene(tela, 390, 844));
                stage.setTitle("CampusHub");
                stage.show();
            } catch (Exception e) {
                mensagemErro.setText("Erro ao abrir a tela principal.");
                e.printStackTrace();
            }
        } else {
            mensagemErro.setText("E-mail ou senha incorretos.");
        }
    }

    // Método responsável por abrir a tela de cadastro
    @FXML
    private void abrirCadastro() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/TelaCadastro.fxml"));

            Parent tela = loader.load();

            Stage stage = (Stage) campoEmail.getScene().getWindow();

            stage.setScene(new Scene(tela, 390, 844));
            stage.setTitle("Cadastro - CampusHub");
            stage.show();
        } catch (Exception e) {
            mensagemErro.setText("Não foi possível abrir o cadastro.");
            e.printStackTrace();
        }
    }

    // Retorna o controller responsável pelos usuários cadastrados
    public static UsuarioController getUsuarioController() {
        return usuarioController;
    }

    // Retorna o usuário que está logado atualmente
    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    // Encerra a sessão do usuário atual
    public static void encerrarSessao() {
        usuarioLogado = null;
    }
}