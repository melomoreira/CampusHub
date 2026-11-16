package Controller;

// Bibliotecas
import Model.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

// Classe TelaCadastroController
public class TelaCadastroController {
    // Atributos
    @FXML
    private TextField campoNome;

    @FXML
    private TextField campoEmail;

    @FXML
    private PasswordField campoSenha;

    @FXML
    private TextField campoUniversidade;

    @FXML
    private TextField campoCurso;

    @FXML
    private Label mensagemErro;

    // Métodos
    @FXML
    private void realizarCadastro() {
        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        String senha = campoSenha.getText();
        String universidade = campoUniversidade.getText().trim();
        String curso = campoCurso.getText().trim();

        // Validar campos
        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() ||
            universidade.isEmpty() || curso.isEmpty()) {

            mensagemErro.setText("Preencha todos os campos.");
            return;
        }

        // Validar formato básico do e-mail
        if (!email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            mensagemErro.setText("Digite um e-mail válido.");
            return;
        }

        // Validar senha
        if (senha.length() < 6) {
            mensagemErro.setText("A senha deve ter pelo menos 6 caracteres.");
            return;
        }

        Usuario usuario = new Usuario(
            nome,
            email,
            senha,
            universidade,
            curso,
            new ArrayList<String>()
        );

        boolean cadastrado = TelaLoginController.getUsuarioController()
            .cadastrarUsuarioSeguro(usuario);

        if (cadastrado) {
            voltarLogin();
        } else {
            mensagemErro.setText("Este e-mail já está cadastrado.");
        }
    }

    @FXML
    private void voltarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/View/TelaLogin.fxml")
            );

            Parent tela = loader.load();

            Stage stage = (Stage) campoNome.getScene().getWindow();

            stage.setScene(new Scene(tela, 390, 844));
            stage.setTitle("Login - CampusHub");
            stage.show();

        } catch (Exception e) {
            mensagemErro.setText("Não foi possível voltar ao login.");
            e.printStackTrace();
        }
    }
}