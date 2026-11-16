package Controller;

// Model
import Model.Usuario;
import Model.Evento;

// JavaFX
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ParallelTransition;
import javafx.util.Duration;

// Data e coleções
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.text.Normalizer;
import java.util.Locale;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// Classe TelaPrincipalController
public class TelaPrincipalController {
    // Saudação
    @FXML
    private Label labelSaudacao;

    // Cartões de acesso rápido
    @FXML
    private VBox botaoEventos;

    @FXML
    private VBox botaoGrupos;

    @FXML
    private VBox botaoAcademico;

    @FXML
    private VBox botaoAtletica;

    @FXML
    private VBox botaoMapa;

    // Próximos eventos
    @FXML
    private VBox listaProximosEventos;

    // Carrossel de notificações
    @FXML
    private StackPane painelNotificacoes;

    @FXML
    private VBox conteudoNotificacao;

    @FXML
    private Label iconeCategoria;

    @FXML
    private Label tituloCategoria;

    @FXML
    private Label descricaoCategoria;

    @FXML
    private Label numeroCategoria;

    @FXML
    private VBox listaItensNotificacao;

    @FXML
    private HBox indicadoresNotificacao;

    // Barra inferior
    @FXML
    private VBox navInicio;

    @FXML
    private VBox navEventos;

    @FXML
    private VBox navGrupos;

    @FXML
    private VBox navPerfil;

    // Categorias do carrossel
    private final String[] iconesCategorias = {
        "📅",
        "📰",
        "👥",
        "🏆"
    };

    private final String[] nomesCategorias = {
        "Agenda do dia",
        "Notícias e publicações",
        "Grupos e mensagens",
        "Atlética"
    };

    private final String[] descricoesCategorias = {
        "Acompanhe seus compromissos e lembretes.",
        "Fique por dentro das novidades da universidade.",
        "Acompanhe suas conversas e atividades em grupo.",
        "Confira as novidades do esporte universitário."
    };

    private final String[][] itensCategorias = {
        {
            "Seus lembretes e compromissos do dia aparecerão aqui."
        },
        {
            "As notícias e publicações recentes aparecerão aqui."
        },
        {
            "Novas mensagens, convites e atividades dos grupos aparecerão aqui."
        },
        {
            "Campeonatos, equipes e comunicados aparecerão aqui."
        }
    };

    // Controle do carrossel
    private int categoriaAtual = 0;

    private double inicioArrasteX;

    private boolean animando = false;

    // Inicialização
    @FXML
    private void initialize() {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario != null) {
            labelSaudacao.setText("Olá, " + usuario.getNome() + "!");
        }

        boolean mostrarMapa = usuarioEhDaUFG(usuario);

        botaoMapa.setVisible(mostrarMapa);
        botaoMapa.setManaged(mostrarMapa);

        configurarHoverCartao(botaoEventos);
        configurarHoverCartao(botaoGrupos);
        configurarHoverCartao(botaoAcademico);
        configurarHoverCartao(botaoAtletica);
        configurarHoverCartao(botaoMapa);

        configurarHoverNavegacao(navInicio);
        configurarHoverNavegacao(navEventos);
        configurarHoverNavegacao(navGrupos);
        configurarHoverNavegacao(navPerfil);

        atualizarPainelNotificacoes();

        configurarDeslizeNotificacoes();

        atualizarProximosEventos();
    }

    // Configurar o conteúdo inicial do carrossel
    private void atualizarPainelNotificacoes() {
        mostrarCategoria(0, 0, false);

        atualizarIndicadores();
    }

    // Mostrar uma categoria
    private void mostrarCategoria(int indice, int direcao, boolean animar) {
        if (indice < 0 || indice >= nomesCategorias.length) {
            return;
        }

        if (animando || indice == categoriaAtual && animar) {
            return;
        }

        categoriaAtual = indice;

        iconeCategoria.setText(iconesCategorias[indice]);

        tituloCategoria.setText(nomesCategorias[indice]);

        descricaoCategoria.setText(descricoesCategorias[indice]);

        numeroCategoria.setText((indice + 1) + " / " + nomesCategorias.length);

        listaItensNotificacao.getChildren().clear();

        // Criar os itens da categoria
        for (String texto : itensCategorias[indice]) {
            VBox item = new VBox();

            item.setPadding(new Insets(9, 11, 9, 11));

            item.setMaxWidth(Double.MAX_VALUE);

            item.setStyle("-fx-background-color: #36517A;" + "-fx-background-radius: 9px;");

            Label mensagem = new Label(texto);

            mensagem.setWrapText(true);

            mensagem.setMaxWidth(Double.MAX_VALUE);

            mensagem.setStyle("-fx-font-size: 12px;" + "-fx-text-fill: white;");

            item.getChildren().add(mensagem);

            listaItensNotificacao.getChildren().add(item);
        }

        atualizarIndicadores();

        // Atualizar a animação
        if (animar && direcao != 0) {
            animando = true;

            conteudoNotificacao.setTranslateX(direcao * 35);

            conteudoNotificacao.setOpacity(0);

            TranslateTransition movimento = new TranslateTransition(
                Duration.millis(220),
                conteudoNotificacao
            );

            movimento.setFromX(direcao * 35);

            movimento.setToX(0);

            FadeTransition aparecer = new FadeTransition(
                Duration.millis(220),
                conteudoNotificacao
            );

            aparecer.setFromValue(0);

            aparecer.setToValue(1);

            ParallelTransition transicao = new ParallelTransition(movimento, aparecer);

            transicao.setOnFinished(event -> {
                conteudoNotificacao.setTranslateX(0);

                conteudoNotificacao.setOpacity(1);

                animando = false;
            });

            transicao.play();
        }
    }

    // Criar os indicadores inferiores
    private void atualizarIndicadores() {
        indicadoresNotificacao.getChildren().clear();

        for (int i = 0; i < nomesCategorias.length; i++) {
            final int indice = i;

            Label indicador = new Label("●");

            indicador.setCursor(Cursor.HAND);

            indicador.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: " +
                (i == categoriaAtual ? "#FFFFFF;" : "#8FA6C8;")
            );

            indicador.setOnMouseClicked(event -> {
                int direcao = indice > categoriaAtual ? 1 : -1;

                mostrarCategoria(indice, direcao, true);
            });

            indicadoresNotificacao.getChildren().add(indicador);
        }
    }

    // Detectar deslize horizontal
    private void configurarDeslizeNotificacoes() {
        painelNotificacoes.addEventFilter(
            MouseEvent.MOUSE_PRESSED,
            event -> {
                inicioArrasteX = event.getSceneX();
            }
        );

        painelNotificacoes.addEventFilter(
            MouseEvent.MOUSE_RELEASED,
            event -> {
                double fimArrasteX = event.getSceneX();

                double diferenca = fimArrasteX - inicioArrasteX;

                // Movimento mínimo para reconhecer o deslize
                if (Math.abs(diferenca) >= 45) {
                    if (diferenca < 0) {
                        proximaNotificacao();
                    } else {
                        notificacaoAnterior();
                    }
                }
            }
        );
    }

    // Próxima categoria
    @FXML
    private void proximaNotificacao() {
        if (categoriaAtual < nomesCategorias.length - 1) {
            mostrarCategoria(categoriaAtual + 1, 1, true);
        }
    }

    // Categoria anterior
    @FXML
    private void notificacaoAnterior() {
        if (categoriaAtual > 0) {
            mostrarCategoria(categoriaAtual - 1, -1, true);
        }
    }

    // Hover dos cartões
    private void configurarHoverCartao(VBox cartao) {
        String estiloOriginal = cartao.getStyle();

        cartao.setCursor(Cursor.HAND);

        cartao.setOnMouseEntered(event -> {
            cartao.setStyle(
                "-fx-background-color: #D5E0EF;" +
                "-fx-background-radius: 12px;"
            );
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

    // Validação UFG
    private boolean usuarioEhDaUFG(Usuario usuario) {
        if (usuario == null || usuario.getUniversidade() == null) {
            return false;
        }

        String universidade = Normalizer.normalize(
            usuario.getUniversidade().trim(),
            Normalizer.Form.NFD
        ).replaceAll("\\p{M}", "").toLowerCase(Locale.ROOT);

        return universidade.equals("ufg") ||
            universidade.contains("universidade federal de goias");
    }

    // Atualizar próximos eventos
    private void atualizarProximosEventos() {
        listaProximosEventos.getChildren().clear();

        LocalDate hoje = LocalDate.now();

        DateTimeFormatter formato = DateTimeFormatter
            .ofPattern("dd/MM/uuuu")
            .withResolverStyle(ResolverStyle.STRICT);

        List<Evento> proximosEventos = EventoController
            .getInstancia()
            .listarEventos()
            .stream()
            .filter(evento -> {
                try {
                    LocalDate data = LocalDate.parse(evento.getData(), formato);

                    return !data.isBefore(hoje);
                } catch (Exception e) {
                    return false;
                }
            })
            .sorted(Comparator.comparing(
                evento -> LocalDate.parse(evento.getData(), formato)
            ))
            .limit(3)
            .collect(Collectors.toList());

        // Nenhum evento encontrado
        if (proximosEventos.isEmpty()) {
            Label mensagem = new Label("Nenhum evento próximo no momento.");

            mensagem.setWrapText(true);

            mensagem.setStyle("-fx-font-size: 12px;" + "-fx-text-fill: #667085;");

            listaProximosEventos.getChildren().add(mensagem);

            return;
        }

        // Criar cartões
        for (Evento evento : proximosEventos) {
            VBox card = new VBox(6);

            card.setPadding(new Insets(12));

            card.setStyle("-fx-background-color: white;" + "-fx-background-radius: 12px;");

            Label titulo = new Label(evento.getTitulo());

            titulo.setWrapText(true);

            titulo.setStyle(
                "-fx-font-size: 14px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #243B64;"
            );

            Label dataHorario = new Label(evento.getData() + " • " + evento.getHorario());

            dataHorario.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #426A9E;"
            );

            Label local = new Label("Local: " + evento.getLocal());

            local.setWrapText(true);

            local.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-text-fill: #667085;"
            );

            card.getChildren().addAll(titulo, dataHorario, local);

            listaProximosEventos.getChildren().add(card);
        }
    }

    // Abrir eventos
    @FXML
    private void abrirEventos() {
        navegarPara("/View/TelaEventos.fxml", "CampusHub - Eventos");
    }

    // Abrir grupos
    @FXML
    private void abrirGrupos() {
        navegarPara("/View/TelaGrupos.fxml", "CampusHub - Grupos");
    }

    // Abrir área acadêmica
    @FXML
    private void abrirAcademico() {
        navegarPara("/View/TelaAcademica.fxml", "CampusHub - Acadêmico");
    }

    // Abrir área da Atlética
    @FXML
    private void abrirAtletica() {
        navegarPara("/View/TelaAtletica.fxml", "CampusHub - Atlética");
    }

    // Abrir perfil
    @FXML
    private void abrirPerfil() {
        navegarPara("/View/TelaPerfil.fxml", "CampusHub - Perfil");
    }

    // Voltar ao início
    @FXML
    private void abrirInicio() {
        // Já está na tela principal.
    }

    // Abrir mapa
    @FXML
    private void abrirMapa() {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (!usuarioEhDaUFG(usuario)) {
            mostrarEmDesenvolvimento("Mapa disponível para estudantes da UFG");

            return;
        }

        navegarPara("/View/TelaMapa.fxml", "CampusHub - Mapa");
    }

    // Navegação
    private void navegarPara(String caminho, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));

            Parent tela = loader.load();

            Stage stage = (Stage) labelSaudacao.getScene().getWindow();

            stage.setScene(new Scene(tela, 390, 844));

            stage.setTitle(titulo);

            stage.show();

        } catch (Exception e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);

            alerta.setTitle("Erro de navegação");

            alerta.setHeaderText("Não foi possível abrir a tela.");

            alerta.setContentText(e.getMessage());

            alerta.showAndWait();

            e.printStackTrace();
        }
    }

    // Módulos em desenvolvimento
    private void mostrarEmDesenvolvimento(String modulo) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("CampusHub");

        alerta.setHeaderText(modulo);

        alerta.setContentText(
            "O módulo " + modulo +
            " está em desenvolvimento e estará disponível em breve!"
        );

        alerta.showAndWait();
    }
}