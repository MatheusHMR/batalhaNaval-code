package trabalhopoov.controllers.preparacao;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import trabalhopoov.model.Navio;
import trabalhopoov.model.Orientacao;
import trabalhopoov.model.Parte;
import trabalhopoov.model.Peca;
import trabalhopoov.model.Player;
import trabalhopoov.model.Tabuleiro;

public class TelaPreparativaController implements Initializable {

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

    private int numLinhas;
    private int numColunas;

    private int qntdSub = 3;
    private int qntdCour = 2;
    private int qntdPA = 1;

    private Orientacao orientacao;

    private Navio navioSelecionado = null;

    private Peca[][] matrizPecasT1 = new Peca[10][10];
    private Peca[][] matrizPecasT2 = new Peca[10][10];

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        numLinhas = 10 - gridPanePreparacao.getColumnCount();
        numColunas = 10 - gridPanePreparacao.getRowCount();
        RadioButton radioButtonHorizontal = new RadioButton("Horizontal");
        RadioButton radioButtonVertical = new RadioButton("Vertical");

        preparacaoTabuleiro(numLinhas, numColunas);
        labelTelaPreparacao.setTextAlignment(TextAlignment.CENTER);
        labelTelaPreparacao.setAlignment(Pos.CENTER);
        Tabuleiro tabuleiroPlayer1 = new Tabuleiro(new Player("Matheus"), matrizPecasT1);
        Tabuleiro tabuleiroPlayer2 = new Tabuleiro(new Player("Zé"), matrizPecasT2);

        while (!addCouracadoButton.isDisabled() || !addPortaAviaoButton.isDisabled() || !addSubmarinoButton.isDisabled()) {
            if(qntdSub == 0){
                addSubmarinoButton.setDisable(true);
            }
            if(qntdCour == 0){
                addCouracadoButton.setDisable(true);
            }
            if(qntdPA == 0){
                addPortaAviaoButton.setDisable(true);
            }
            //trocar de tabuleiro
            switchTabuleiro();
        }

    }

    @FXML
    void addSubmarinoButtonClicado(ActionEvent event) {
        System.out.println("Submarino selecionado");
        navioSelecionado = Navio.criaSubmarino(orientacao);
        qntdSub--;

    }

    @FXML
    void addCouracadoButtonClicado(ActionEvent event) {
        System.out.println("Couracado selecionado");
        navioSelecionado = Navio.criaCouracado(orientacao);
        qntdCour--;

    }

    @FXML
    void addPortaAviaoButtonClicado(ActionEvent event) {
        System.out.println("Porta Aviões selecionado");
        navioSelecionado = Navio.criaPortaAvioes(orientacao);
        qntdPA--;
    }

    @FXML
    void isSelectedHorizontal(ActionEvent event) {
        orientacao = Orientacao.HORIZONTAL;
    }

    @FXML
    void isSelectedVertical(ActionEvent event) {
        orientacao = Orientacao.VERTICAL;
    }

    private void preparacaoTabuleiro(int numLinhas, int numColunas) {

        EventHandler<ActionEvent> ButtonClickHandler = new ButtonClickHandler();

        addColunas(numColunas);
        addLinhas(numLinhas);

        numLinhas = numColunas = 10;

        for (int linha = 0; linha < numLinhas; linha++) {
            for (int coluna = 0; coluna < numColunas; coluna++) {
                // Setando função de ativação com clique no botão.
                // Criação e configuração do botão.

                Peca novaPeca = new Peca(coluna, linha, 'A');
                matrizPecasT1[linha][coluna] = novaPeca;
                // preciso recriar porque nao se pode compartilhar referencias entre tabuleiros

                novaPeca = new Peca(coluna, linha, 'A');
                matrizPecasT2[linha][coluna] = novaPeca;

                Button button = new Button();
                button.setId(linha + ", " + coluna);
                button.setOnAction(ButtonClickHandler);

                // // Configuração do botão e inserção no gridPane.
                button.setPrefSize(gridPanePreparacao.getPrefWidth(), gridPanePreparacao.getPrefHeight());
                gridPanePreparacao.add(button, linha, coluna, 1, 1);
            }
        }
    }

    public class ButtonClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Button botaoClicado = ((Button) event.getSource());
            int linha = GridPane.getRowIndex(botaoClicado);
            int coluna = GridPane.getColumnIndex(botaoClicado);


            if (navioSelecionado != null) {
                if (orientacao == Orientacao.VERTICAL) {
                    if(linha + navioSelecionado.getTamanho() >= 10){
                        //Alertar que tá errado
                        System.out.println("Posição Inválida!! O navio ficará para fora do tabuleiro!!");
                    }
                    for(int i = linha; i < (linha + navioSelecionado.getTamanho()); i++){
                        if(matrizPecasT1[linha][coluna].getIdentificador() != 'A'){
                            //Alertar que tá errado
                           System.out.println("Posição Inválida!! Já existe um navio na posição"); 
                        }
                    }
                    ArrayList<Parte> partesNavio = new ArrayList<>();
                    for(int i = linha; i < (linha + navioSelecionado.getTamanho()); i++){
                        Parte parte = new Parte(linha, coluna, navioSelecionado.getIdentificador(), navioSelecionado);
                        partesNavio.add(parte);
                    }
                    navioSelecionado.setPartes(partesNavio);
                }  else {
                    if(coluna + navioSelecionado.getTamanho() >= 10){
                        //Alertar que tá errado
                        System.out.println("Posição Inválida!! O navio ficará para fora do tabuleiro!!");
                    }
                    for(int i = coluna; i < (coluna + navioSelecionado.getTamanho()); i++){
                        if(matrizPecasT1[linha][coluna].getIdentificador() != 'A'){
                            //Alertar que tá errado
                           System.out.println("Posição Inválida!! Já existe um navio na posição"); 
                        }
                    }
                    ArrayList<Parte> partesNavio = new ArrayList<>();
                    for(int i = coluna; i < (coluna + navioSelecionado.getTamanho()); i++){
                        Parte parte = new Parte(linha, coluna, navioSelecionado.getIdentificador(), navioSelecionado);
                        partesNavio.add(parte);
                    }
                    navioSelecionado.setPartes(partesNavio);
                    
                }
            } else {
                System.out.println("Selecione um tipo de navio para colocar no tabuleiro!!");
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