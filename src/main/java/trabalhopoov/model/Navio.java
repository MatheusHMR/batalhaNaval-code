package trabalhopoov.model;

public abstract class Navio {

    private int tamanho;
    private Orientacao orientacao;
    private char identificador;

    public Navio(int tamanho, Orientacao orientacao, char identificador) {
        this.tamanho = tamanho;
        this.orientacao = orientacao;
        this.identificador = identificador;
    }

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public Orientacao getOrientacao() {
        return orientacao;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public char getIdentificador() {
        return identificador;
    }

    public void setIdentificador(char identificador) {
        this.identificador = identificador;
    }

}
