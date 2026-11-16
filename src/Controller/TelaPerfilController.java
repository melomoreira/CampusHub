package Controller;

// Bibliotecas
import Model.Usuario;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

// Classe TelaPerfilController
public class TelaPerfilController {
    // Atributos
    @FXML private TextField campoNome;
    @FXML private TextField campoEmail;
    @FXML private TextField campoUniversidade;
    @FXML private TextField campoCurso;
    @FXML private TextArea campoInteresses;
    @FXML private Label mensagem;
    @FXML private ImageView imagemPerfil;
    @FXML private Label placeholderFoto;
    @FXML private VBox navInicio;
    @FXML private VBox navEventos;
    @FXML private VBox navGrupos;
    @FXML private VBox navPerfil;

    private Usuario usuarioLogado;

    @FXML
    public void initialize() {
        usuarioLogado = TelaLoginController.getUsuarioLogado();

        configurarImagemPerfil();

        if (usuarioLogado == null) {
            mensagem.setText("Nenhum usuário está conectado.");
            return;
        }

        carregarDados();
        configurarNavegacao();
    }

    private void configurarImagemPerfil() {
        imagemPerfil.setFitWidth(110);
        imagemPerfil.setFitHeight(110);
        imagemPerfil.setPreserveRatio(false);

        imagemPerfil.setClip(new Circle(55, 55, 55));
        imagemPerfil.setVisible(false);

        placeholderFoto.setVisible(true);
    }

    private void carregarDados() {
        campoNome.setText(usuarioLogado.getNome());
        campoEmail.setText(usuarioLogado.getEmail());
        campoUniversidade.setText(usuarioLogado.getUniversidade());
        campoCurso.setText(usuarioLogado.getCurso());

        List<String> interesses = usuarioLogado.getInteresses();

        if (interesses != null) {
            campoInteresses.setText(String.join(", ", interesses));
        }

        carregarFotoPerfil();
    }

    private void carregarFotoPerfil() {
        String caminho = usuarioLogado.getFotoPerfilPath();

        if (caminho == null || caminho.isBlank()) {
            mostrarPlaceholderFoto();
            return;
        }

        File arquivo = new File(caminho);

        if (!arquivo.exists()) {
            mostrarPlaceholderFoto();
            return;
        }

        try {
            Image imagem = new Image(arquivo.toURI().toString());

            if (imagem.isError()) {
                mostrarPlaceholderFoto();
                return;
            }

            imagemPerfil.setImage(imagem);
            imagemPerfil.setVisible(true);
            placeholderFoto.setVisible(false);

        } catch (Exception e) {
            mostrarPlaceholderFoto();
        }
    }

    private void mostrarPlaceholderFoto() {
        imagemPerfil.setImage(null);
        imagemPerfil.setVisible(false);
        placeholderFoto.setVisible(true);
    }

    @FXML
    private void selecionarFoto() {
        if (usuarioLogado == null) {
            mensagem.setText("Nenhum usuário está conectado.");
            return;
        }

        FileChooser seletor = new FileChooser();

        seletor.setTitle("Selecionar foto de perfil");

        seletor.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter(
                "Imagens",
                "*.png",
                "*.jpg",
                "*.jpeg",
                "*.gif"
            )
        );

        File arquivoSelecionado = seletor.showOpenDialog(
            imagemPerfil.getScene().getWindow()
        );

        if (arquivoSelecionado == null) {
            return;
        }

        try {
            Path pastaDestino = Paths.get("fotos_perfil");
            Files.createDirectories(pastaDestino);

            String extensao = obterExtensao(arquivoSelecionado.getName());

            String identificador = usuarioLogado.getEmail()
                .replaceAll("[^A-Za-z0-9]", "_");

            Path destino = pastaDestino.resolve(identificador + extensao);

            Files.copy(
                arquivoSelecionado.toPath(),
                destino,
                StandardCopyOption.REPLACE_EXISTING
            );

            usuarioLogado.setFotoPerfilPath(
                destino.toAbsolutePath().toString()
            );

            carregarFotoPerfil();

            mensagem.setText("Foto de perfil atualizada!");

        } catch (IOException e) {
            mensagem.setText("Não foi possível salvar a foto.");
            e.printStackTrace();
        }
    }

    private String obterExtensao(String nomeArquivo) {
        int ponto = nomeArquivo.lastIndexOf(".");

        if (ponto == -1) {
            return ".png";
        }

        return nomeArquivo.substring(ponto).toLowerCase();
    }

    @FXML
    private void salvarAlteracoes() {
        if (usuarioLogado == null) {
            mensagem.setText("Não foi possível identificar o usuário.");
            return;
        }

        String nome = campoNome.getText().trim();
        String email = campoEmail.getText().trim();
        String universidade = campoUniversidade.getText().trim();
        String curso = campoCurso.getText().trim();
        String textoInteresses = campoInteresses.getText();

        if (nome.isEmpty() || email.isEmpty() || universidade.isEmpty() || curso.isEmpty()) {
            mensagem.setText("Preencha todos os campos obrigatórios.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            mensagem.setText("Digite um endereço de e-mail válido.");
            return;
        }

        Usuario usuarioEncontrado = TelaLoginController
            .getUsuarioController()
            .buscarUsuarioPorEmail(email);

        if (usuarioEncontrado != null && usuarioEncontrado != usuarioLogado) {
            mensagem.setText("Este e-mail já está cadastrado.");
            return;
        }

        List<String> interesses = new ArrayList<>();

        if (textoInteresses != null && !textoInteresses.isBlank()) {
            interesses = Arrays.stream(textoInteresses.split(","))
                .map(String::trim)
                .filter(interesse -> !interesse.isEmpty())
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
        }

        usuarioLogado.setNome(nome);
        usuarioLogado.setEmail(email);
        usuarioLogado.setUniversidade(universidade);
        usuarioLogado.setCurso(curso);
        usuarioLogado.setInteresses(interesses);

        mensagem.setText("Perfil atualizado com sucesso!");
    }

    private void configurarNavegacao() {
        configurarItem(navInicio);
        configurarItem(navEventos);
        configurarItem(navGrupos);
        configurarItem(navPerfil);
    }

    private void configurarItem(VBox item) {
        item.setOnMouseEntered(e -> item.setOpacity(0.65));
        item.setOnMouseExited(e -> item.setOpacity(1.0));
        item.setStyle("-fx-cursor: hand;");
    }

    @FXML
    private void abrirInicio() {
        navegarPara("/View/TelaPrincipal.fxml", "CampusHub - Início");
    }

    @FXML
    private void abrirEventos() {
        navegarPara("/View/TelaEventos.fxml", "CampusHub - Eventos");
    }

    @FXML
    private void abrirGrupos() {
        navegarPara("/View/TelaGrupos.fxml", "CampusHub - Grupos");
    }

    @FXML
    private void abrirPerfil() {
        // Já estamos na tela de perfil.
    }

    @FXML
    private void sairDaConta() {
        TelaLoginController.encerrarSessao();
        navegarPara("/View/TelaLogin.fxml", "CampusHub - Login");
    }

    private void navegarPara(String caminho, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));

            Parent root = loader.load();

            Stage stage = (Stage) navInicio.getScene().getWindow();

            stage.setTitle(titulo);
            stage.setScene(new Scene(root, 390, 844));
            stage.show();

        } catch (IOException e) {
            mostrarErro("Não foi possível abrir a tela: " + titulo);
            e.printStackTrace();
        }
    }

    private void mostrarErro(String texto) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);

        alerta.setTitle("Erro");
        alerta.setHeaderText(null);
        alerta.setContentText(texto);

        alerta.showAndWait();
    }
}