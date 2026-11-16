package Controller;

// Bibliotecas
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Classe TelaAtleticaController
public class TelaAtleticaController {
    // Cartões da Atlética
    @FXML
    private VBox cartaoModalidades;

    @FXML
    private VBox cartaoEquipes;

    @FXML
    private VBox cartaoCampeonatos;

    @FXML
    private VBox cartaoNoticias;

    // Barra inferior
    @FXML
    private VBox navInicio;

    @FXML
    private VBox navEventos;

    @FXML
    private VBox navGrupos;

    @FXML
    private VBox navPerfil;

    // Inicialização
    @FXML
    private void initialize() {
        configurarHoverCartao(cartaoModalidades);
        configurarHoverCartao(cartaoEquipes);
        configurarHoverCartao(cartaoCampeonatos);
        configurarHoverCartao(cartaoNoticias);

        configurarHoverNavegacao(navInicio);
        configurarHoverNavegacao(navEventos);
        configurarHoverNavegacao(navGrupos);
        configurarHoverNavegacao(navPerfil);
    }

    // Hover dos cartões
    private void configurarHoverCartao(VBox cartao) {
        String estiloOriginal = cartao.getStyle();

        cartao.setCursor(Cursor.HAND);

        cartao.setOnMouseEntered(event -> {
            cartao.setStyle("-fx-background-color: #E5EBF4;" + "-fx-background-radius: 12px;" + "-fx-padding: 15px;");
        });

        cartao.setOnMouseExited(event -> {
            cartao.setStyle(estiloOriginal);
        });
    }

    // Hover da navegação
    private void configurarHoverNavegacao(VBox item) {
        item.setCursor(Cursor.HAND);

        item.setOnMouseEntered(event -> {
            item.setOpacity(0.65);
        });

        item.setOnMouseExited(event -> {
            item.setOpacity(1.0);
        });
    }

    // Navegação entre telas
    private void navegarPara(String caminho, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));

            Parent tela = loader.load();

            Stage stage = (Stage) navInicio.getScene().getWindow();

            stage.setScene(new Scene(tela, 390, 844));

            stage.setTitle(titulo);

            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

            Alert alerta = new Alert(Alert.AlertType.ERROR);

            alerta.setTitle("Erro de navegação");

            alerta.setHeaderText("Não foi possível abrir a tela.");

            alerta.setContentText(e.toString());

            alerta.showAndWait();
        }
    }

    // Navegação inferior
    @FXML
    private void abrirInicio() {
        navegarPara("/View/TelaPrincipal.fxml", "CampusHub");
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
        navegarPara("/View/TelaPerfil.fxml", "CampusHub - Perfil");
    }

    // Funcionalidades da Atlética
    @FXML
    private void abrirModalidades() {
        mostrarEmDesenvolvimento("Modalidades esportivas");
    }

    @FXML
    private void abrirEquipes() {
        mostrarEmDesenvolvimento("Equipes e seletivas");
    }

    @FXML
    private void abrirCampeonatos() {
        mostrarEmDesenvolvimento("Campeonatos e competições");
    }

    @FXML
    private void abrirNoticias() {
        mostrarEmDesenvolvimento("Notícias e comunicados");
    }

    // Aviso temporário
    private void mostrarEmDesenvolvimento(String modulo) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("CampusHub - Atlética");

        alerta.setHeaderText(modulo);

        alerta.setContentText("Esta funcionalidade está em desenvolvimento.");

        alerta.showAndWait();
    }
}