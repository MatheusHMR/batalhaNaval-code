package trabalhopoov.model;

public class Parte extends Peca{

    private Navio navio;
    private Peca pecaMae;

    public Parte(int linha, int coluna, String identificador, Navio navio, Peca pecaMae) {
        super(linha, coluna, identificador);
        this.navio = navio;
        this.pecaMae = pecaMae;
    }

    public Navio getNavio() {
        return navio;
    }

    public void setNavio(Navio navio) {
        this.navio = navio;
    }

    public Peca getPecaMae(){
        return pecaMae;
    }

    public void setPecaMae(Peca pecaMae){
        this.pecaMae = pecaMae;
    }



    
}
