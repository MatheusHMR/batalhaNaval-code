package trabalhopoov.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import trabalhopoov.App;
import trabalhopoov.model.Navio;
import trabalhopoov.model.Orientacao;
import trabalhopoov.model.Parte;
import trabalhopoov.model.Peca;
import trabalhopoov.model.Player;
import trabalhopoov.model.Tabuleiro;

public class TelaPreparativaController implements Initializable {

    @FXML
    private AnchorPane AnchorPaneTela1;

    @FXML
    private AnchorPane AnchorPaneTela2;

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
    private TextField namePlayerTextField;

    @FXML
    private Label playerIdLabel;

    @FXML
    private RadioButton RadioButtonHorizontal;

    @FXML
    private RadioButton RadioButtonVertical;

    private int numLinhas;
    private int numColunas;

    // private static int qntdSub = 3;
    // private static int qntdCour = 2;
    // private static int qntdPA = 1;

    private Orientacao orientacao;

    private Navio navioSelecionado = null;

    private Peca[][] matrizPecasT1 = new Peca[10][10];
    private Peca[][] matrizPecasT2 = new Peca[10][10];

    private Peca[][] matrizPecas; //matriz que sera usada na tela atual

    private Scene sceneT1;
    private Scene sceneT2;

    private Tabuleiro tabuleiro1, tabuleiro2;

    private Stage tab2;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        numLinhas = 10 - gridPanePreparacao.getRowCount();
        numColunas = 10 - gridPanePreparacao.getColumnCount();
        Parent parent;
        /*
         * RadioButton radioButtonHorizontal = new RadioButton("Horizontal");
         * RadioButton radioButtonVertical = new RadioButton("Vertical");
         */
        if (App.firstPlacement == true) {
            preparacaoTabuleiro(matrizPecasT1, numLinhas, numColunas);
            labelTelaPreparacao.setTextAlignment(TextAlignment.CENTER);
            labelTelaPreparacao.setAlignment(Pos.CENTER);
            Player player1 = new Player("Matheus");
            Tabuleiro tabuleiro = new Tabuleiro(player1, matrizPecasT1);
            playerIdLabel.setText(player1.getClass().getSimpleName() + "1");
            namePlayerTextField.setText(player1.getName());
            matrizPecas = matrizPecasT1;
            tabuleiro1 = tabuleiro;
        } else {
            preparacaoTabuleiro(matrizPecasT2, numLinhas, numColunas);
            Player player2 = new Player("Tiago");
            Tabuleiro tabuleiro = new Tabuleiro(new Player("Tiago"), matrizPecasT2);
            playerIdLabel.setText(player2.getClass().getSimpleName() + "1");
            namePlayerTextField.setText(player2.getName());
            matrizPecas = matrizPecasT2;
            tabuleiro2 = tabuleiro;
        }

    }

    @FXML
    void addSubmarinoButtonClicado(ActionEvent event) {
        System.out.println("Submarino selecionado");
        if (App.qntdSub != 0) {
            navioSelecionado = Navio.criaSubmarino(orientacao);
        }
    }

    @FXML
    void addCouracadoButtonClicado(ActionEvent event) {
        System.out.println("Couracado selecionado");
        if (App.qntdCour != 0) {
            navioSelecionado = Navio.criaCouracado(orientacao);
        }
    }

    @FXML
    void addPortaAviaoButtonClicado(ActionEvent event) {
        System.out.println("Porta Aviões selecionado");
        if (App.qntdPA != 0) {
            navioSelecionado = Navio.criaPortaAvioes(orientacao);
        }
    }

    @FXML
    void isSelectedHorizontal(ActionEvent event) {
        orientacao = Orientacao.HORIZONTAL;
    }

    @FXML
    void isSelectedVertical(ActionEvent event) {
        orientacao = Orientacao.VERTICAL;
    }

    private void preparacaoTabuleiro(Peca[][] matrizPecas, int numLinhas, int numColunas) {

        EventHandler<ActionEvent> PecaButtonClickHandler = new PecaButtonClickHandler();

        addLinhas(numLinhas);
        addColunas(numColunas);

        numLinhas = numColunas = 10;

        for (int coluna = 0; coluna < numColunas; coluna++) {
            for (int linha = 0; linha < numLinhas; linha++) {
                // Setando função de ativação com clique no botão.
                // Criação e configuração do botão.

                Button button = new Button();
                button.setId(linha + ", " + coluna);
                button.setOnAction(PecaButtonClickHandler);
                // button.setText(button.getId().toString());

                Peca novaPeca = new Peca(linha, coluna, "A", button);
                matrizPecas[linha][coluna] = novaPeca;

                // // Configuração do botão e inserção no gridPane.
                button.setPrefSize(gridPanePreparacao.getPrefWidth(), gridPanePreparacao.getPrefHeight());
                gridPanePreparacao.add(button, coluna, linha, 1, 1);
            }
        }
    }
    public class PecaButtonClickHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            Button botaoClicado = ((Button) event.getSource());
            int linha = GridPane.getRowIndex(botaoClicado);
            int coluna = GridPane.getColumnIndex(botaoClicado);
            boolean erro = false;

            if (navioSelecionado != null) {
                if (orientacao == Orientacao.VERTICAL) { // VERTICAL
                    if (linha + navioSelecionado.getTamanho() > 10) {
                        // Alertar que tá errado
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERRO");
                        alert.setHeaderText("VISH! Navio pra fora do tabuleiro!");
                        alert.setContentText("Posição Inválida!! O navio desejado ficaria para fora do tabuleiro!!");
                        alert.showAndWait();
                        erro = true;
                    } else {// não preciso verificar essa instrução dentro do FOR
                        for (int i = linha; i < (linha + navioSelecionado.getTamanho()); i++) {
                            if (matrizPecas[i][coluna].getIdentificador().compareTo("A") != 0) {
                                // Está errado
                                erro = true;
                            }
                        }
                        if (erro == true) {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERRO");
                            alert.setHeaderText("OPA! Navio no caminho!");
                            alert.setContentText("Posição Inválida!! Já existe um navio na posição!");
                            alert.showAndWait();
                        }
                    }
                    if (erro == false) {// SE NÃO DEU ERRO
                        ArrayList<Parte> partesNavio = new ArrayList<>();
                        for (int i = linha; i < (linha + navioSelecionado.getTamanho()); i++) {

                            Peca pecaMae = matrizPecas[i][coluna];
                            pecaMae.setIdentificador(navioSelecionado.getIdentificador());

                            Parte parte = new Parte(i, coluna, navioSelecionado.getIdentificador(),
                                    navioSelecionado, pecaMae);

                            botaoClicado = parte.getPecaMae().getBotao();
                            partesNavio.add(parte);
                            /*
                             * System.out.println("Parte do navio " +
                             * navioSelecionado.getTipo().getDescricao()
                             * + " na linha:" + i + ", coluna:" + coluna);
                             */
                            botaoClicado.setText(navioSelecionado.getIdentificador());
                        }
                        navioSelecionado.setPartes(partesNavio);
                    }
                } else { // HORIZONTAL
                    if (coluna + navioSelecionado.getTamanho() > 10) {
                        // Alertar que tá errado
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("ERRO");
                        alert.setHeaderText("VISH! Navio pra fora do tabuleiro!");
                        alert.setContentText("Posição Inválida!! O navio desejado ficaria para fora do tabuleiro!!");
                        alert.showAndWait();
                        erro = true;
                    } else {
                        for (int i = coluna; i < (coluna + navioSelecionado.getTamanho()); i++) {
                            if (matrizPecas[linha][i].getIdentificador().compareTo("A") != 0) {
                                // Está errado
                                erro = true;
                            }
                        }
                        if (erro == true) {
                            // Alertar que está errado
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setTitle("ERRO");
                            alert.setHeaderText("OPA! Navio no caminho!");
                            alert.setContentText("Posição Inválida!! Já existe um navio na posição!");
                            alert.showAndWait();
                        }
                    }
                    if (erro == false) {
                        ArrayList<Parte> partesNavio = new ArrayList<>();
                        for (int i = coluna; i < (coluna + navioSelecionado.getTamanho()); i++) {
                            Peca pecaMae = matrizPecas[linha][i];
                            pecaMae.setIdentificador(navioSelecionado.getIdentificador());
                            Parte parte = new Parte(linha, i, navioSelecionado.getIdentificador(),
                                    navioSelecionado, pecaMae);
                            botaoClicado = parte.getPecaMae().getBotao();
                            partesNavio.add(parte);
                            System.out.println(
                                    "Parte do navio " + navioSelecionado.getTipo().getDescricao() + " na linha:"
                                            + linha + ", coluna:" + i);
                            System.out
                                    .println("Parte - Coluna: " + parte.getColuna() + " e Linha: " + parte.getLinha());
                            /*
                             * System.out.println("Id do botao preenchido: " + botaoClicado.getId()
                             * + "e Navio Selecionado: " + navioSelecionado.getIdentificador());
                             */
                            botaoClicado.setText(navioSelecionado.getIdentificador());
                        }
                        navioSelecionado.setPartes(partesNavio);
                    }
                }
                if (erro == false) {
                    String identificador = navioSelecionado.getIdentificador();
                    System.out.println("Navio Selecionado: " + navioSelecionado.getIdentificador());
                    switch (identificador) {

                        case "S": // Submarino
                            if (App.qntdSub > 1) {
                                App.qntdSub--;
                            } else {
                                addSubmarinoButton.setDisable(true);
                                navioSelecionado = null;
                            }
                            break;

                        case "C": // Couraçado
                            if (App.qntdCour > 1) {
                                App.qntdCour--;
                            } else {
                                addCouracadoButton.setDisable(true);
                                navioSelecionado = null;
                            }
                            break;

                        case "P": // Porta-Aviões
                            if (App.qntdPA > 1) {
                                App.qntdPA--;
                            } else {
                                addPortaAviaoButton.setDisable(true);
                                navioSelecionado = null;
                            }
                            break;
                    }

                }
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("ERRO");
                alert.setHeaderText("Presta atenção, moço!");
                alert.setContentText("Primeiro você precisa selecionar um navio pra posicionar");
                alert.showAndWait();
                erro = true;
            }
            if (addCouracadoButton.isDisabled() && addPortaAviaoButton.isDisabled()
                    && addSubmarinoButton.isDisabled()) {
                if(App.firstPlacement == true){
                    tabuleiro1.setMatrizPecas(matrizPecas);//matrizPecasT1
                    App.qntdSub = 3;
                    App.qntdCour = 2;
                    App.qntdPA = 1;
                }
                else{
                    tabuleiro2.setMatrizPecas(matrizPecas);//matrizPecasT2
                }
                System.out.println("Trocar de tela");
                App.firstPlacement = false;
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(
                        "/trabalhopoov/preparacao/TelaPreparativaJogador2.fxml"));
                tab2 = new Stage();
                tab2 = (Stage)botaoClicado.getScene().getWindow();
                try {
                    Parent parent = fxmlLoader.load();
                    sceneT2 = new Scene(parent);
                    tab2.setScene(sceneT2);
                    tab2.show();

                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERRO");
                    alert.setHeaderText("Erro");
                    alert.setContentText("Erro carregando a aplicação!");
                    alert.showAndWait();
                    Platform.exit();
                }
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

    public Tabuleiro getTabuleiro1(){
        return tabuleiro1;
    }

    public Tabuleiro getTabuleiro2(){
        return tabuleiro2;
    }

}