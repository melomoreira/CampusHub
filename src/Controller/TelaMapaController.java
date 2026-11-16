package Controller;

// Bibliotecas
import Model.Missao;
import Model.PontoInteresse;
import Model.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

// Classe TelaMapaController
public class TelaMapaController {
    // Atributos
    @FXML
    private ImageView imagemMapa;

    @FXML
    private Pane camadaMarcadores;

    @FXML
    private Label labelProgresso;

    @FXML
    private Label labelStatus;

    @FXML
    private StackPane areaMapa;

    private final MissaoController missaoController = MissaoController.getInstancia();

    private final ArrayList<PontoInteresse> pontos = new ArrayList<>();

    @FXML
    public void initialize() {
        carregarMapa();
        cadastrarPontos();
        atualizarProgresso();
    }

    @FXML
    private void voltarInicio() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/View/TelaPrincipal.fxml")
            );

            Parent tela = loader.load();

            Stage stage = (Stage) labelProgresso.getScene().getWindow();

            stage.setScene(new Scene(tela, 390, 844));
            stage.setTitle("CampusHub - Início");
            stage.show();

        } catch (Exception e) {
            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro de navegação",
                "Não foi possível voltar à tela inicial: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }

    private void carregarMapa() {
        String caminho = "/imagens/Mapa_Campus_Samambaia.png";

        URL recurso = getClass().getResource(caminho);

        if (recurso == null) {
            labelStatus.setText(
                "Não foi possível encontrar a imagem do mapa. " +
                "Confira a pasta imagens."
            );

            return;
        }

        Image imagem = new Image(recurso.toExternalForm());

        imagemMapa.setImage(imagem);

        double largura = imagem.getWidth();
        double altura = imagem.getHeight();

        imagemMapa.setFitWidth(largura);
        imagemMapa.setFitHeight(altura);

        camadaMarcadores.setPrefWidth(largura);
        camadaMarcadores.setPrefHeight(altura);

        areaMapaDimensionar(largura, altura);
    }

    private void areaMapaDimensionar(double largura, double altura) {
        areaMapa.setMinSize(largura, altura);
        areaMapa.setPrefSize(largura, altura);
        areaMapa.setMaxSize(largura, altura);

        camadaMarcadores.setMinSize(largura, altura);
        camadaMarcadores.setPrefSize(largura, altura);
        camadaMarcadores.setMaxSize(largura, altura);
    }

    private void cadastrarPontos() {
        // Biblioteca Central - código A2 no mapa
        pontos.add(new PontoInteresse(
            "biblioteca-central",
            "ufg-samambaia",
            "Biblioteca Central",
            "Espaço de estudo, pesquisa e conhecimento.",
            "Educação",
            "📚",
            0.4638,
            0.3387
        ));

        // Restaurante Universitário - código A21 no mapa
        pontos.add(new PontoInteresse(
            "restaurante-universitario",
            "ufg-samambaia",
            "Restaurante Universitário",
            "Espaço de alimentação e convivência.",
            "Alimentação",
            "🍽",
            0.3990,
            0.2081
        ));

        // Reitoria - código B1 no mapa
        pontos.add(new PontoInteresse(
            "reitoria",
            "ufg-samambaia",
            "Reitoria",
            "Local onde se encontram setores da administração universitária.",
            "Administração",
            "🏛",
            0.5571,
            0.3911
        ));

        // Centro de Convivência - Quadra A, código A7
        pontos.add(new PontoInteresse(
            "centro-convivencia",
            "ufg-samambaia",
            "Centro de Convivência",
            "Espaço de interação e encontro entre estudantes.",
            "Convivência",
            "👥",
            0.4912,
            0.2790
        ));

        criarMarcadores();
    }

    private void criarMarcadores() {
        camadaMarcadores.getChildren().clear();

        double largura = imagemMapa.getFitWidth();
        double altura = imagemMapa.getFitHeight();

        for (PontoInteresse ponto : pontos) {
            Button marcador = new Button(ponto.getIcone());

            marcador.setStyle(
                "-fx-background-color: #426A9E;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 16px;" +
                "-fx-background-radius: 50%;" +
                "-fx-min-width: 38px;" +
                "-fx-min-height: 38px;" +
                "-fx-max-width: 38px;" +
                "-fx-max-height: 38px;" +
                "-fx-cursor: hand;"
            );

            marcador.setLayoutX(ponto.getPosX() * largura - 19);
            marcador.setLayoutY(ponto.getPosY() * altura - 19);

            marcador.setOnAction(event -> abrirPonto(ponto));

            camadaMarcadores.getChildren().add(marcador);
        }
    }

    private void abrirPonto(PontoInteresse ponto) {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario == null) {
            mostrarAlerta(
                Alert.AlertType.WARNING,
                "Login necessário",
                "Faça login para acessar as missões."
            );

            return;
        }

        Missao missao = buscarMissaoDoPonto(ponto.getId());

        if (missao == null) {
            mostrarAlerta(
                Alert.AlertType.INFORMATION,
                ponto.getNome(),
                ponto.getDescricao()
            );

            return;
        }

        if (missaoController.missaoFoiConcluida(usuario, missao.getId())) {
            mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Missão concluída",
                missao.getTitulo() + "\n\nVocê já concluiu esta missão!"
            );

            return;
        }

        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);

        alerta.setTitle("Missão do campus");
        alerta.setHeaderText(missao.getTitulo());

        alerta.setContentText(
            ponto.getNome() + "\n\n" +
            ponto.getDescricao() + "\n\n" +
            missao.getDescricao() + "\n\n" +
            "Recompensa: " + missao.getRecompensa() + " pontos"
        );

        ButtonType concluir = new ButtonType(
            "Concluir missão",
            ButtonBar.ButtonData.OK_DONE
        );

        ButtonType cancelar = new ButtonType(
            "Voltar",
            ButtonBar.ButtonData.CANCEL_CLOSE
        );

        alerta.getButtonTypes().setAll(concluir, cancelar);

        Optional<ButtonType> resultado = alerta.showAndWait();

        if (resultado.isPresent() && resultado.get() == concluir) {
            boolean concluida = missaoController.concluirMissao(
                usuario,
                missao.getId()
            );

            if (concluida) {
                atualizarProgresso();

                mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Missão concluída!",
                    "Você recebeu " + missao.getRecompensa() +
                    " pontos."
                );
            }
        }
    }

    private Missao buscarMissaoDoPonto(String pontoId) {
        ArrayList<Missao> missoes = missaoController.listarMissoesDoCampus(
            "ufg-samambaia"
        );

        for (Missao missao : missoes) {
            if (missao.getPontoInteresseId().equals(pontoId)) {
                return missao;
            }
        }

        return null;
    }

    private void atualizarProgresso() {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario == null) {
            labelProgresso.setText(
                "Entre na sua conta para acompanhar seu progresso."
            );

            return;
        }

        int concluidas = missaoController.contarMissoesConcluidas(usuario);

        int pontos = missaoController.calcularPontos(usuario);

        labelProgresso.setText(
            "Missões concluídas: " + concluidas + " | Pontos: " + pontos
        );
    }

    private void mostrarAlerta(
        Alert.AlertType tipo,
        String titulo,
        String mensagem
    ) {
        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensagem);

        alerta.showAndWait();
    }
}