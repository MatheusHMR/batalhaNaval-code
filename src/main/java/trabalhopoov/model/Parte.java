package trabalhopoov.model;

public class Parte extends Peca{

    private Navio navio;

    public Parte(int posicaox, int posicaoy, char identificador, Navio navio) {
        super(posicaox, posicaoy, identificador);
        this.navio = navio;
    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }

    
}
