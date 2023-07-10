package trabalhopoov.model;

import javafx.scene.control.Button;

public class Peca {
    private int linha;
    private int coluna;
    private String identificador;
    private Button botao;


    public Peca(int linha, int coluna, String identificador) {
        this.linha = linha;
        this.coluna = coluna;
        this.identificador = identificador;
    }

    public Peca(int linha, int coluna, String identificador, Button botao) {
        this.linha = linha;
        this.coluna = coluna;
        this.identificador = identificador;
        this.botao = botao;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    
    public Button getBotao() {
        return botao;
    }

    public void setBotao(Button botao) {
        this.botao = botao;
    }

}
