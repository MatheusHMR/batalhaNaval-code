package trabalhopoov.controllers;

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
        /*
         * RadioButton radioButtonHorizontal = new RadioButton("Horizontal");
         * RadioButton radioButtonVertical = new RadioButton("Vertical");
         */

        preparacaoTabuleiro(numLinhas, numColunas);
        labelTelaPreparacao.setTextAlignment(TextAlignment.CENTER);
        labelTelaPreparacao.setAlignment(Pos.CENTER);
        Tabuleiro tabuleiroPlayer1 = new Tabuleiro(new Player("Matheus"), matrizPecasT1);
        Tabuleiro tabuleiroPlayer2 = new Tabuleiro(new Player("Zé"), matrizPecasT2);

    }

    @FXML
    void addSubmarinoButtonClicado(ActionEvent event) {
        System.out.println("Submarino selecionado");
        if (qntdSub != 0) {
            navioSelecionado = Navio.criaSubmarino(orientacao);
        }
    }

    @FXML
    void addCouracadoButtonClicado(ActionEvent event) {
        System.out.println("Couracado selecionado");
        if (qntdCour != 0) {
            navioSelecionado = Navio.criaCouracado(orientacao);
        }
    }

    @FXML
    void addPortaAviaoButtonClicado(ActionEvent event) {
        System.out.println("Porta Aviões selecionado");
        if (qntdPA != 0) {
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

    private void preparacaoTabuleiro(int numLinhas, int numColunas) {

        EventHandler<ActionEvent> PecaButtonClickHandler = new PecaButtonClickHandler();

        addColunas(numColunas);
        addLinhas(numLinhas);

        numLinhas = numColunas = 10;

        for (int coluna = 0; coluna < numColunas; coluna++) {
            for (int linha = 0; linha < numLinhas; linha++) {
                // Setando função de ativação com clique no botão.
                // Criação e configuração do botão.

                Button button = new Button();
                button.setId(linha + ", " + coluna);
                button.setOnAction(PecaButtonClickHandler);

                Peca novaPeca = new Peca(linha, coluna, "A", button);
                matrizPecasT1[linha][coluna] = novaPeca;
                // preciso recriar porque nao se pode compartilhar referencias entre tabuleiros

                /*
                 * button = new Button();
                 * button.setId(linha + ", " + coluna);
                 * button.setOnAction(PecaButtonClickHandler);
                 */

                /*
                 * novaPeca = new Peca(linha, coluna, "A", button);
                 * matrizPecasT2[linha][coluna] = novaPeca;
                 */

                // // Configuração do botão e inserção no gridPane.
                button.setPrefSize(gridPanePreparacao.getPrefWidth(), gridPanePreparacao.getPrefHeight());
                gridPanePreparacao.add(button, linha, coluna, 1, 1);
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
                    if (linha + navioSelecionado.getTamanho() >= 10) {
                        // Alertar que tá errado
                        System.out.println("Posição Inválida!! O navio ficará para fora do tabuleiro!!");
                        erro = true;
                    } else {// não preciso verificar essa instrução dentro do FOR
                        for (int i = linha; i < (linha + navioSelecionado.getTamanho()); i++) {
                            if (matrizPecasT1[i][coluna].getIdentificador() != "A") {
                                // Alertar que tá errado
                                System.out.println("Posição Inválida!! Já existe um navio na posição");
                                erro = true;
                            }
                        }
                    }
                    if (erro == false) {// SE NÃO DEU ERRO
                        ArrayList<Parte> partesNavio = new ArrayList<>();
                        botaoClicado.setText(navioSelecionado.getIdentificador());
                        for (int i = linha; i < (linha + navioSelecionado.getTamanho()); i++) {
                            Peca pecaMae = matrizPecasT1[i][coluna];
                            Parte parte = new Parte(i, coluna, navioSelecionado.getIdentificador(),
                                    navioSelecionado, pecaMae);
                            Button botaonovo = parte.getPecaMae().getBotao();
                            partesNavio.add(parte);
                            System.out.println("Parte do navio " + navioSelecionado.getTipo().getDescricao()
                                    + " na linha:" + i + ", coluna:" + coluna);
                            botaonovo.setText(navioSelecionado.getIdentificador());
                        }
                        navioSelecionado.setPartes(partesNavio);
                    }
                } else { // HORIZONTAL
                    if (coluna + navioSelecionado.getTamanho() >= 10) {
                        // Alertar que tá errado
                        System.out.println("Posição Inválida!! O navio ficará para fora do tabuleiro!!");
                        erro = true;
                    } else {
                        for (int i = coluna; i < (coluna + navioSelecionado.getTamanho()); i++) {
                            if (matrizPecasT1[linha][i].getIdentificador() != "A") {
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
                            Parte parte = new Parte(linha, i, navioSelecionado.getIdentificador(),
                                    navioSelecionado, pecaMae);
                            partesNavio.add(parte);
                            botaoClicado = parte.getPecaMae().getBotao();
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
                            if (qntdSub != 0) {
                                qntdSub--;
                            } else {
                                addSubmarinoButton.setDisable(true);
                            }
                            break;

                        case "C": // Couraçado
                            if (qntdCour != 0) {
                                qntdCour--;
                            } else {
                                addCouracadoButton.setDisable(true);
                            }
                            break;

                        case "P": // Porta-Aviões
                            if (qntdPA != 0) {
                                qntdPA--;
                            } else {
                                addPortaAviaoButton.setDisable(true);
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