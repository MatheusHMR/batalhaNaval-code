package trabalhopoov.model;

public class Peca {
    private int posicaox;
    private int poxicaoy;
    private char identificador;

    public Peca(int posicaox, int poxicaoy, char identificador) {
        this.posicaox = posicaox;
        this.poxicaoy = poxicaoy;
        this.identificador = identificador;
    }

    public int getPosicaox() {
        return posicaox;
    }

    public void setPosicaox(int posicaox) {
        this.posicaox = posicaox;
    }

    public int getPoxicaoy() {
        return poxicaoy;
    }

    public void setPoxicaoy(int poxicaoy) {
        this.poxicaoy = poxicaoy;
    }

    public char getIdentificador() {
        return identificador;
    }

    public void setIdentificador(char identificador) {
        this.identificador = identificador;
    }

    
    

}
