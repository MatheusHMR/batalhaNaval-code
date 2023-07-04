package trabalhopoov;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private GridPane gridPane;

    // @Override
    // public void start(Stage stage) throws IOException {
    // scene = new Scene(loadFXML("primary"));
    // stage.setScene(scene);
    // stage.show();
    // }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        //carregar o endereco do arquivo
        scene = new Scene(loadFXML("preparacao/TelaInicial"), 400, 300);
        stage.setTitle("GRID PANE, FAMÍLIA");
        stage.setScene(scene);
        stage.show();

    }

   
}