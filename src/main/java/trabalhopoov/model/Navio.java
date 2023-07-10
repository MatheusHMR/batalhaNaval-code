package trabalhopoov.model;

import java.util.ArrayList;

public class Navio {

    private int tamanho;
    private Orientacao orientacao;
    private ArrayList<Parte> partes;
    private Tipo tipo;
    private String identificador;

    public Navio(int tamanho, Orientacao orientacao, Tipo tipo, String identificador) {
        this.tamanho = tamanho;
        this.orientacao = orientacao;
        this.tipo = tipo;
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

    public ArrayList<Parte> getPartes() {
        return partes;
    }

    public void setPartes(ArrayList<Parte> partes) {
        this.partes = partes;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setOrientacao(Orientacao orientacao) {
        this.orientacao = orientacao;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public static Navio criaSubmarino(Orientacao orientacao){
        Navio submarino = new Navio(2, orientacao, Tipo.SUBMARINO, "S");
        return submarino;
    }

    public static Navio criaPortaAvioes(Orientacao orientacao){
        Navio portaAvioes = new Navio(5, orientacao, Tipo.PORTA_AVIOES, "P");
        return portaAvioes;
    }

    public static Navio criaCouracado(Orientacao orientacao){
        Navio couracado = new Navio(4, orientacao, Tipo.COURACADO, "C");
        return couracado;
    }

}
