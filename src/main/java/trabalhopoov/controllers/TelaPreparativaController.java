package trabalhopoov.controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextAlignment;
import trabalhopoov.App;
import trabalhopoov.controllers.TelaPreparativaController.PecaButtonClickHandler;
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

    // private static int qntdSub = 3;
    // private static int qntdCour = 2;
    // private static int qntdPA = 1;

    private Orientacao orientacao;

    private Navio navioSelecionado = null;

    private Peca[][] matrizPecasT1 = new Peca[10][10];
    private Peca[][] matrizPecasT2 = new Peca[10][10];

    private Scene sceneT1;
    private Scene sceneT2;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        numLinhas = 10 - gridPanePreparacao.getRowCount();
        numColunas = 10 - gridPanePreparacao.getColumnCount();
        Parent parent;
        /*
         * RadioButton radioButtonHorizontal = new RadioButton("Horizontal");
         * RadioButton radioButtonVertical = new RadioButton("Vertical");
         */

        preparacaoTabuleiro(matrizPecasT1, numLinhas, numColunas);
        labelTelaPreparacao.setTextAlignment(TextAlignment.CENTER);
        labelTelaPreparacao.setAlignment(Pos.CENTER);
        Tabuleiro tabuleiroPlayer1 = new Tabuleiro(new Player("Matheus"), matrizPecasT1);
        /*FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/preparacao/TelaPreparativaJogador2.fxml"));
        try {
            parent = fxmlLoader.load();
            preparacaoTabuleiro(matrizPecasT2, numLinhas, numColunas);
            Tabuleiro tabuleiroPlayer2 = new Tabuleiro(new Player("Zé"), matrizPecasT2);
        } catch (Exception e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERRO");
            alert.setHeaderText("Erro");
            alert.setContentText("Erro carregando a aplicação!");
            alert.showAndWait();
            Platform.exit();
        }*/

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
                        System.out.println("Posição Inválida!! O navio ficará para fora do tabuleiro!!");
                        erro = true;
                    } else {// não preciso verificar essa instrução dentro do FOR
                        for (int i = linha; i < (linha + navioSelecionado.getTamanho()); i++) {
                            if (matrizPecasT1[i][coluna].getIdentificador().compareTo("A") != 0) {
                                // Alertar que tá errado
                                System.out.println("Posição Inválida!! Já existe um navio na posição");
                                erro = true;
                            }
                        }
                    }
                    if (erro == false) {// SE NÃO DEU ERRO
                        ArrayList<Parte> partesNavio = new ArrayList<>();
                        for (int i = linha; i < (linha + navioSelecionado.getTamanho()); i++) {

                            Peca pecaMae = matrizPecasT1[i][coluna];
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
                        System.out.println("Posição Inválida!! O navio ficará para fora do tabuleiro!!");
                        erro = true;
                    } else {
                        for (int i = coluna; i < (coluna + navioSelecionado.getTamanho()); i++) {
                            if (matrizPecasT1[linha][i].getIdentificador().compareTo("A") != 0) {
                                // Alertar que tá errado
                                System.out.println("Posição Inválida!! Já existe um navio na posição");
                                erro = true;
                            }
                        }
                    }
                    if (erro == false) {
                        ArrayList<Parte> partesNavio = new ArrayList<>();
                        for (int i = coluna; i < (coluna + navioSelecionado.getTamanho()); i++) {
                            Peca pecaMae = matrizPecasT1[linha][i];
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