package Controller;

// Bibliotecas
import Model.Evento;
import Model.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import javafx.scene.control.DatePicker;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

// Classe TelaEventosController
public class TelaEventosController {
    // Atributos
    @FXML
    private VBox navInicio;

    @FXML
    private VBox navEventos;

    @FXML
    private VBox navGrupos;

    @FXML
    private VBox navPerfil;

    @FXML
    private VBox listaEventos;

    private final EventoController eventoController = EventoController.getInstancia();

    // Inicialização
    @FXML
    private void initialize() {
        configurarHover(navInicio);
        configurarHover(navEventos);
        configurarHover(navGrupos);
        configurarHover(navPerfil);

        atualizarListaEventos();
    }

    // Configurar efeito ao passar o mouse
    private void configurarHover(VBox item) {
        item.setCursor(Cursor.HAND);

        item.setOnMouseEntered(event -> item.setOpacity(0.65));

        item.setOnMouseExited(event -> item.setOpacity(1.0));
    }

    // =========================
    // LISTAGEM DOS EVENTOS
    // =========================

    private void atualizarListaEventos() {
        listaEventos.getChildren().clear();

        if (eventoController.listarEventos().isEmpty()) {
            Label vazio = new Label(
                "Nenhum evento cadastrado ainda. Que tal criar o primeiro?"
            );

            vazio.setWrapText(true);

            vazio.setStyle(
                "-fx-font-size: 13px;" +
                "-fx-text-fill: #667085;"
            );

            listaEventos.getChildren().add(vazio);

            return;
        }

        for (Evento evento : eventoController.listarEventos()) {
            listaEventos.getChildren().add(criarCardEvento(evento));
        }
    }

    private VBox criarCardEvento(Evento evento) {
        VBox card = new VBox(8);

        card.setPadding(new Insets(15));

        card.setStyle(
            "-fx-background-color: white;" +
            "-fx-background-radius: 12px;"
        );

        Label titulo = new Label(evento.getTitulo());

        titulo.setWrapText(true);

        titulo.setStyle(
            "-fx-font-size: 16px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #243B64;"
        );

        Label dataHorario = new Label(
            evento.getData() + " • " + evento.getHorario()
        );

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

        Label descricao = new Label(evento.getDescricao());

        descricao.setWrapText(true);

        descricao.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #667085;"
        );

        Label participantes = new Label(
            "Participantes: " + evento.getParticipantes().size()
        );

        participantes.setStyle(
            "-fx-font-size: 12px;" +
            "-fx-text-fill: #667085;"
        );

        HBox botoes = new HBox(8);

        botoes.setPadding(new Insets(5, 0, 0, 0));

        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario != null) {
            boolean estaParticipando = evento.getParticipantes().contains(usuario);

            Button botaoParticipacao = new Button(
                estaParticipando ? "Sair do evento" : "Participar"
            );

            botaoParticipacao.setOnAction(e -> {
                if (estaParticipando) {
                    eventoController.sairEvento(evento.getId(), usuario);
                } else {
                    eventoController.participarEvento(evento.getId(), usuario);
                }

                atualizarListaEventos();
            });

            botaoParticipacao.setStyle(
                "-fx-background-color: #426A9E;" +
                "-fx-text-fill: white;" +
                "-fx-background-radius: 8px;" +
                "-fx-cursor: hand;"
            );

            botoes.getChildren().add(botaoParticipacao);

            // Somente o organizador pode editar ou excluir.
            if (evento.getOrganizador() == usuario) {
                Button editar = new Button("Editar");

                editar.setOnAction(e -> editarEvento(evento));

                Button excluir = new Button("Excluir");

                excluir.setOnAction(e -> excluirEvento(evento));

                botoes.getChildren().addAll(editar, excluir);
            }
        }

        card.getChildren().addAll(
            titulo,
            dataHorario,
            local,
            descricao,
            participantes,
            botoes
        );

        return card;
    }

    // =========================
    // CRIAR EVENTO
    // =========================

    @FXML
    private void abrirFormularioEvento() {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario == null) {
            mostrarAlerta(
                Alert.AlertType.WARNING,
                "Login necessário",
                "Você precisa estar logado para criar um evento."
            );

            return;
        }

        exibirFormulario(null);
    }

    // =========================
    // EDITAR EVENTO
    // =========================

    private void editarEvento(Evento evento) {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario == null || evento.getOrganizador() != usuario) {
            mostrarAlerta(
                Alert.AlertType.WARNING,
                "Acesso negado",
                "Somente o organizador pode editar este evento."
            );

            return;
        }

        exibirFormulario(evento);
    }

    // =========================
    // FORMULÁRIO
    // =========================

    private void exibirFormulario(Evento evento) {
        boolean criando = evento == null;

        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle(criando ? "Criar evento" : "Editar evento");

        dialog.setHeaderText(
            criando ? "Preencha os dados do evento." : "Altere os dados do evento."
        );

        ButtonType salvar = new ButtonType(
            "Salvar",
            ButtonBar.ButtonData.OK_DONE
        );

        dialog.getDialogPane().getButtonTypes().addAll(
            salvar,
            ButtonType.CANCEL
        );

        TextField titulo = new TextField(criando ? "" : evento.getTitulo());

        titulo.setPromptText("Título do evento");

        TextField data = new TextField(criando ? "" : evento.getData());

        data.setPromptText("DD/MM/AAAA");

        TextField horario = new TextField(criando ? "" : evento.getHorario());

        horario.setPromptText("HH:MM");

        TextField local = new TextField(criando ? "" : evento.getLocal());

        local.setPromptText("Local do evento");

        TextArea descricao = new TextArea(criando ? "" : evento.getDescricao());

        descricao.setPromptText("Descrição do evento");

        descricao.setPrefRowCount(3);

        descricao.setWrapText(true);

        GridPane formulario = new GridPane();

        formulario.setHgap(10);

        formulario.setVgap(10);

        formulario.setPadding(new Insets(15));

        formulario.addRow(0, new Label("Título:"), titulo);

        formulario.addRow(1, new Label("Data:"), data);

        formulario.addRow(2, new Label("Horário:"), horario);

        formulario.addRow(3, new Label("Local:"), local);

        formulario.addRow(4, new Label("Descrição:"), descricao);

        dialog.getDialogPane().setContent(formulario);

        dialog.getDialogPane().setPrefWidth(420);

        dialog.showAndWait().ifPresent(resposta -> {
            if (resposta != salvar) {
                return;
            }

            if (titulo.getText().trim().isEmpty()
                    || data.getText().trim().isEmpty()
                    || horario.getText().trim().isEmpty()
                    || local.getText().trim().isEmpty()
                    || descricao.getText().trim().isEmpty()) {

                mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos obrigatórios",
                    "Preencha todos os campos."
                );

                return;
            }

            if (!dataValida(data.getText().trim())) {
                mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Data inválida",
                    "Informe uma data válida, no formato DD/MM/AAAA, que não esteja no passado."
                );

                return;
            }

            if (!horarioValido(horario.getText().trim())) {
                mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Horário inválido",
                    "Informe um horário válido no formato HH:MM."
                );

                return;
            }

            if (criando) {
                Usuario organizador = TelaLoginController.getUsuarioLogado();

                Evento novoEvento = new Evento(
                    titulo.getText().trim(),
                    data.getText().trim(),
                    horario.getText().trim(),
                    local.getText().trim(),
                    descricao.getText().trim(),
                    organizador
                );

                eventoController.cadastrarEvento(novoEvento);
            } else {
                Evento novosDados = new Evento(
                    titulo.getText().trim(),
                    data.getText().trim(),
                    horario.getText().trim(),
                    local.getText().trim(),
                    descricao.getText().trim(),
                    evento.getOrganizador()
                );

                eventoController.atualizarEvento(evento.getId(), novosDados);
            }

            atualizarListaEventos();
        });
    }

    // Validar data
    private boolean dataValida(String valor) {
        try {
            DateTimeFormatter formato = DateTimeFormatter
                .ofPattern("dd/MM/uuuu")
                .withResolverStyle(ResolverStyle.STRICT);

            LocalDate dataEvento = LocalDate.parse(valor, formato);

            // Impede datas anteriores ao dia atual.
            return !dataEvento.isBefore(LocalDate.now());

        } catch (Exception e) {
            return false;
        }
    }

    // Validar horário
    private boolean horarioValido(String valor) {
        try {
            DateTimeFormatter formato = DateTimeFormatter
                .ofPattern("HH:mm")
                .withResolverStyle(ResolverStyle.STRICT);

            LocalTime.parse(valor, formato);

            return true;

        } catch (Exception e) {
            return false;
        }
    }

    // =========================
    // EXCLUIR EVENTO
    // =========================

    private void excluirEvento(Evento evento) {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario == null || evento.getOrganizador() != usuario) {
            mostrarAlerta(
                Alert.AlertType.WARNING,
                "Acesso negado",
                "Somente o organizador pode excluir este evento."
            );

            return;
        }

        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacao.setTitle("Excluir evento");

        confirmacao.setHeaderText("Deseja excluir este evento?");

        confirmacao.setContentText(evento.getTitulo());

        confirmacao.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                eventoController.excluirEvento(evento.getId());

                atualizarListaEventos();
            }
        });
    }

    // =========================
    // ALERTAS
    // =========================

    private void mostrarAlerta(
        Alert.AlertType tipo,
        String titulo,
        String mensagem
    ) {
        Alert alerta = new Alert(tipo);

        alerta.setTitle("CampusHub");

        alerta.setHeaderText(titulo);

        alerta.setContentText(mensagem);

        alerta.showAndWait();
    }

    // =========================
    // NAVEGAÇÃO
    // =========================

    @FXML
    private void abrirInicio() {
        navegarPara("/View/TelaPrincipal.fxml", "CampusHub");
    }

    @FXML
    private void abrirEventos() {
        atualizarListaEventos();
    }

    @FXML
    private void abrirGrupos() {
        navegarPara("/View/TelaGrupos.fxml", "CampusHub - Grupos");
    }

    @FXML
    private void abrirPerfil() {
        navegarPara("/View/TelaPerfil.fxml", "CampusHub - Perfil");
    }

    private void navegarPara(String caminho, String titulo) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(caminho));

            Parent tela = loader.load();

            Stage stage = (Stage) navInicio.getScene().getWindow();

            stage.setScene(new Scene(tela, 390, 844));

            stage.setTitle(titulo);

            stage.show();

        } catch (Exception e) {
            mostrarAlerta(
                Alert.AlertType.ERROR,
                "Erro de navegação",
                "Não foi possível abrir a tela: " + e.getMessage()
            );

            e.printStackTrace();
        }
    }
}