package View;

// Bibliotecas
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

// Classe TelaPrincipalFX
public class TelaPrincipalFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("TelaLogin.fxml")
        );

        Parent tela = loader.load();

        StackPane fundo = new StackPane(tela);
        fundo.setStyle("-fx-background-color: #E9EDF5;");

        Scene cena = new Scene(fundo, 390, 844);

        stage.setTitle("CampusHub - Login");
        stage.getIcons().add(
            new Image(
                getClass().getResourceAsStream("/imagens/CampusHub_Logo.png")
            )
        );
        stage.setScene(cena);
        stage.setResizable(false);
        stage.show();
    }

    // Método Main
    public static void main(String[] args) {
        launch(args);
    }
}