package trabalhopoov;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public static int qntdSub = 3;
    public static int qntdCour = 2;
    public static int qntdPA = 1;

    public static boolean firstPlacement = true;

    //private GridPane gridPane;

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
        scene = new Scene(loadFXML("preparacao/TelaPreparativaJogador1"));
        stage.setTitle("JOGO DE BATALHA NAVAL USANDO JAVA E JAVAFX");
        stage.setScene(scene);
        stage.show();

    }

   
}