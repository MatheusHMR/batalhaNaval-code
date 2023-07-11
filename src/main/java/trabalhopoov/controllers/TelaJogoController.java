package trabalhopoov.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import trabalhopoov.model.Tabuleiro;

public class TelaJogoController implements Initializable{

    @FXML
    private GridPane gridPanePreparacao;

    @FXML
    private Label labelTelaPreparacao;

    @FXML
    private TextField namePlayerTextField;

    @FXML
    private Label playerIdLabel;

    private TelaPreparativaController telaPreparativaController;

    private Tabuleiro tabuleiro1, tabuleiro2;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
    }

}
