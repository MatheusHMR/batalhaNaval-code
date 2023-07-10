package trabalhopoov.controllers.preparacao;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;

public class TelaInicialController implements Initializable{

    @FXML
    private Button addCouracadoButton;

    @FXML
    private Button addPortaAviaoButton;

    @FXML
    private Button addSubmarinoButton;

    @FXML
    private GridPane gridPanePreparacao;

    @FXML
    private Label labelTelaPreparacao;

    @FXML
    private RadioButton RadioButtonHorizontal;

    @FXML
    private RadioButton RadioButtonVertical;

    @FXML
    void addCouracadoButtonClicado(ActionEvent event) {
        System.out.println("Couracado selecionado");
    }

    @FXML
    void addPortaAviaoButtonClicado(ActionEvent event) {
        System.out.println("Porta Aviões selecionado");
    }

    @FXML
    void addSubmarinoButtonClicado(ActionEvent event) {
        System.out.println("Submarino selecionado");
    }

    private int numLinhas;
    private int numColunas;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        numLinhas = 10 - gridPanePreparacao.getColumnCount();
        numColunas = 10 - gridPanePreparacao.getRowCount();
        preparacaoTabuleiro(numLinhas, numColunas);
        //gridPanePreparacao.get
        labelTelaPreparacao.setTextAlignment(TextAlignment.CENTER);
        labelTelaPreparacao.setAlignment(Pos.CENTER);

        ToggleGroup toggleGroup = new ToggleGroup();
        RadioButton radioButtonHorizontal = new RadioButton("Horizontal");

        // EventHandler<ActionEvent> ButtonClickHandler = new ButtonClickHandler();
        // Setando função de ativação com clique no botão.
        // button.setOnAction(ButtonClickHandler);
    }

    private void preparacaoTabuleiro(int numLinhas, int numColunas) {

        addColunas(numColunas);
        addLinhas(numLinhas);

        numLinhas = numColunas = 10;

        for (int linha = 0; linha < numLinhas; linha++) {
            for (int coluna = 0; coluna < numColunas; coluna++) {
                // Criação e configuração do botão.
                Button button = new Button();
                button.setId("botao" + linha + " " + coluna);
                GridPane.setHalignment(button, HPos.CENTER);

                // Setando função de ativação com clique no botão.
                // button.setOnAction(ButtonClickHandler);

                // // Configuração do botão e inserção no gridPane.
                button.setPrefSize(gridPanePreparacao.getPrefWidth(), gridPanePreparacao.getPrefHeight());
                //gridPanePreparacao.setVgap(5);
                //gridPanePreparacao.setHgap(5);
                gridPanePreparacao.add(button, linha, coluna, 1, 1);
                gridPanePreparacao.setPadding(Insets.EMPTY);
                gridPanePreparacao.setPadding(new Insets(0, 0, 0, 0));
                //gridPanePreparacao.setAlignment(Pos.CENTER);
            }
        }
    }

    private void addLinhas(int numLinhas) {
        for (int i = 0; i <= numLinhas; i++) {
            RowConstraints rowConstraints = gridPanePreparacao.getRowConstraints().get(0);
            // double larguraCelula =
            // gridPanePreparacao.getRowConstraints().get(0).getPrefWidth();
            gridPanePreparacao.getRowConstraints().add(rowConstraints);
        }
    }

    private void addColunas(int numColunas) {
        for (int i = 0; i <= numColunas; i++) {
            ColumnConstraints columnConstraints = gridPanePreparacao.getColumnConstraints().get(0);
            gridPanePreparacao.getColumnConstraints().add(columnConstraints);
        }
    }

}