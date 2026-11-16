package Controller;

// Bibliotecas
import Model.Grupo;
import Model.Usuario;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.text.Normalizer;

import java.util.Locale;

// Classe TelaGruposController
public class TelaGruposController {
    // Atributos
    @FXML private TextField campoBusca;
    @FXML private VBox conteudoGrupos;
    @FXML private Label mensagemSemResultados;
    @FXML private VBox navInicio;
    @FXML private VBox navEventos;
    @FXML private VBox navGrupos;
    @FXML private VBox navPerfil;

    private final GrupoController grupoController = GrupoController.getInstancia();

    @FXML
    private void initialize() {
        configurarHover(navInicio);
        configurarHover(navEventos);
        configurarHover(navGrupos);
        configurarHover(navPerfil);

        campoBusca.textProperty().addListener((observable, antigoValor, novoValor) -> atualizarListaGrupos());

        atualizarListaGrupos();
    }

    // Executa a pesquisa quando o usuário pressiona Enter.
    @FXML
    private void pesquisarGrupos() {
        atualizarListaGrupos();
    }

    // Normaliza o texto para facilitar a pesquisa.
    private String normalizarTexto(String texto) {
        return Normalizer.normalize(texto.toLowerCase(Locale.ROOT), Normalizer.Form.NFD).replaceAll("\\p{M}", "");
    }

    // Atualiza a lista de grupos e aplica a pesquisa.
    private void atualizarListaGrupos() {
        conteudoGrupos.getChildren().clear();

        String pesquisa = campoBusca.getText() == null ? "" : normalizarTexto(campoBusca.getText().trim());

        int encontrados = 0;

        for (Grupo grupo : grupoController.listarGrupos()) {
            String nome = normalizarTexto(grupo.getNome());
            String descricao = normalizarTexto(grupo.getDescricao());

            boolean corresponde = nome.contains(pesquisa) || descricao.contains(pesquisa);

            if (corresponde) {
                conteudoGrupos.getChildren().add(criarCardGrupo(grupo));

                encontrados++;
            }
        }

        boolean nenhumEncontrado = encontrados == 0;

        mensagemSemResultados.setVisible(nenhumEncontrado);
        mensagemSemResultados.setManaged(nenhumEncontrado);
    }

    // Cria visualmente um card para cada grupo.
    private VBox criarCardGrupo(Grupo grupo) {
        VBox card = new VBox(10);
        card.setMaxWidth(Double.MAX_VALUE);
        card.setFillWidth(true);
        card.setPadding(new Insets(15));

        card.setStyle("-fx-background-color: white;" + "-fx-background-radius: 12px;" + "-fx-border-color: #E1E7F0;" + "-fx-border-radius: 12px;");

        Label nome = new Label(grupo.getNome());
        nome.setMaxWidth(Double.MAX_VALUE);
        nome.setWrapText(true);
        nome.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" + "-fx-text-fill: #243B64;");

        Label descricao = new Label(grupo.getDescricao());
        descricao.setMaxWidth(Double.MAX_VALUE);
        descricao.setWrapText(true);
        descricao.setStyle("-fx-font-size: 13px;" + "-fx-text-fill: #667085;");

        Label participantes = new Label("Participantes: " + grupo.getParticipantes().size());
        participantes.setStyle("-fx-font-size: 12px;" + "-fx-text-fill: #426A9E;");

        FlowPane botoes = new FlowPane();
        botoes.setHgap(8);
        botoes.setVgap(8);
        botoes.setMaxWidth(Double.MAX_VALUE);

        Usuario usuario = TelaLoginController.getUsuarioLogado();

        Button botaoParticipacao;

        if (usuario != null && grupo.getParticipantes().contains(usuario)) {
            botaoParticipacao = new Button("Sair do grupo");

            botaoParticipacao.setOnAction(event -> {
                grupoController.sairGrupo(grupo.getId(), usuario);
                atualizarListaGrupos();
            });
        } else {
            botaoParticipacao = new Button("Participar");

            botaoParticipacao.setOnAction(event -> {
                if (usuario == null) {
                    mostrarAlerta(Alert.AlertType.WARNING, "Login necessário", "Faça login para participar de um grupo.");
                    return;
                }

                grupoController.participarGrupo(grupo.getId(), usuario);
                atualizarListaGrupos();
            });
        }

        estilizarBotaoPrincipal(botaoParticipacao);
        botoes.getChildren().add(botaoParticipacao);

        Button botaoMensagens = new Button("Mensagens");

        botaoMensagens.setOnAction(event -> abrirMensagens(grupo));

        estilizarBotaoSecundario(botaoMensagens);

        botoes.getChildren().add(botaoMensagens);

        if (usuario != null && grupo.getCriador() == usuario) {
            Button botaoEditar = new Button("Editar");
            botaoEditar.setOnAction(event -> exibirFormularioGrupo(grupo));

            Button botaoExcluir = new Button("Excluir");
            botaoExcluir.setOnAction(event -> excluirGrupo(grupo));

            estilizarBotaoSecundario(botaoEditar);
            estilizarBotaoSecundario(botaoExcluir);

            botoes.getChildren().addAll(botaoEditar, botaoExcluir);
        }

        card.getChildren().addAll(nome, descricao, participantes, botoes);

        return card;
    }

    // Abre o formulário de criação.
    @FXML
    private void abrirFormularioGrupo() {
        if (TelaLoginController.getUsuarioLogado() == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Login necessário", "Faça login para criar um grupo.");
            return;
        }

        exibirFormularioGrupo(null);
    }

    // Formulário utilizado tanto para criar quanto para editar.
    private void exibirFormularioGrupo(Grupo grupo) {
        boolean editando = grupo != null;

        Dialog<ButtonType> dialog = new Dialog<>();

        dialog.setTitle(editando ? "Editar grupo" : "Criar grupo");
        dialog.setHeaderText(editando ? "Atualize as informações do grupo" : "Crie uma nova comunidade");

        ButtonType botaoSalvar = new ButtonType("Salvar", ButtonBar.ButtonData.OK_DONE);

        dialog.getDialogPane().getButtonTypes().addAll(botaoSalvar, ButtonType.CANCEL);

        TextField campoNome = new TextField();
        campoNome.setPromptText("Nome do grupo");

        TextArea campoDescricao = new TextArea();
        campoDescricao.setPromptText("Descrição do grupo");
        campoDescricao.setWrapText(true);
        campoDescricao.setPrefRowCount(4);

        if (editando) {
            campoNome.setText(grupo.getNome());
            campoDescricao.setText(grupo.getDescricao());
        }

        VBox formulario = new VBox(10);
        formulario.setPadding(new Insets(10));

        formulario.getChildren().addAll(new Label("Nome"), campoNome, new Label("Descrição"), campoDescricao);

        dialog.getDialogPane().setContent(formulario);

        dialog.getDialogPane().setPrefWidth(350);

        dialog.showAndWait().ifPresent(resposta -> {
            if (resposta != botaoSalvar) {
                return;
            }

            String nome = campoNome.getText().trim();
            String descricao = campoDescricao.getText().trim();

            if (nome.isEmpty() || descricao.isEmpty()) {
                mostrarAlerta(Alert.AlertType.WARNING, "Campos obrigatórios", "Preencha o nome e a descrição do grupo.");
                return;
            }

            Usuario usuario = TelaLoginController.getUsuarioLogado();

            if (editando) {
                Grupo novosDados = new Grupo(nome, descricao, grupo.getCriador());

                grupoController.atualizarGrupo(grupo.getId(), novosDados);

                mostrarAlerta(Alert.AlertType.INFORMATION, "Grupo atualizado", "As informações foram atualizadas.");
            } else {
                Grupo novoGrupo = new Grupo(nome, descricao, usuario);

                grupoController.cadastrarGrupo(novoGrupo);

                mostrarAlerta(Alert.AlertType.INFORMATION, "Grupo criado", "Seu grupo foi criado com sucesso.");
            }

            atualizarListaGrupos();
        });
    }

    // Exclui um grupo após confirmação.
    private void excluirGrupo(Grupo grupo) {
        Alert confirmacao = new Alert(Alert.AlertType.CONFIRMATION);

        confirmacao.setTitle("Excluir grupo");
        confirmacao.setHeaderText("Deseja excluir o grupo " + grupo.getNome() + "?");
        confirmacao.setContentText("Essa ação não poderá ser desfeita.");

        confirmacao.showAndWait().ifPresent(resposta -> {
            if (resposta == ButtonType.OK) {
                grupoController.excluirGrupo(grupo.getId());

                atualizarListaGrupos();
            }
        });
    }

    private void estilizarBotaoPrincipal(Button botao) {
        botao.setStyle(
            "-fx-background-color: #426A9E;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;"
        );
    }

    private void estilizarBotaoSecundario(Button botao) {
        botao.setStyle(
            "-fx-background-color: #E8EEF7;" +
            "-fx-text-fill: #243B64;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8px;" +
            "-fx-cursor: hand;"
        );
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensagem) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("CampusHub");
        alerta.setHeaderText(titulo);
        alerta.setContentText(mensagem);
        alerta.showAndWait();
    }

    private void configurarHover(VBox item) {
        item.setCursor(Cursor.HAND);
        item.setOnMouseEntered(event -> item.setOpacity(0.65));
        item.setOnMouseExited(event -> item.setOpacity(1.0));
    }

    private void abrirMensagens(Grupo grupo) {
        Usuario usuario = TelaLoginController.getUsuarioLogado();

        if (usuario == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Login necessário", "Faça login para acessar as mensagens.");
            return;
        }

        if (!grupo.getParticipantes().contains(usuario)) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Participação necessária", "Você precisa participar do grupo para acessar suas mensagens.");
            return;
        }

        mostrarAlerta(Alert.AlertType.INFORMATION, "Mensagens - " + grupo.getNome(), "A funcionalidade de mensagens deste grupo está em desenvolvimento.");
    }

    // Navegação entre telas.
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
        atualizarListaGrupos();
    }

    @FXML
    private void abrirPerfil() {
        navegarPara("/View/TelaPerfil.fxml", "CampusHub - Perfil");
    }

    private void navegarPara(String caminho, String titulo) {
        try {
            java.net.URL recurso = getClass().getResource(caminho);

            if (recurso == null) {
                throw new IllegalStateException("FXML não encontrado: " + caminho);
            }

            FXMLLoader loader = new FXMLLoader(recurso);
            Parent tela = loader.load();

            Stage stage = (Stage) navGrupos.getScene().getWindow();

            stage.setScene(new Scene(tela, 390, 844));
            stage.setTitle(titulo);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();

            mostrarAlerta(Alert.AlertType.ERROR, "Erro de navegação", e.toString());
        }
    }
}