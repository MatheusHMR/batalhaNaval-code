package trabalhopoov.model;

public class Jogo {

    private Tabuleiro tabuleiro1, tabuleiro2;
    private Integer qntdSubmarinos;
    private Integer qntdCouracados;
    private Integer qntdPortaAvioes;
    private Integer qntdNavios[];

    public Jogo(Tabuleiro tabuleiro1, Tabuleiro tabuleiro2){
        this.tabuleiro1 = tabuleiro1;
        this.tabuleiro2 = tabuleiro2;
        qntdNavios[0] = 3;//qntdSubmarinos
        qntdNavios[1] = 2;//qntdCouracados
        qntdNavios[2] = 1;//qntdPortaAvioes
    }

    public Jogo(Tabuleiro tabuleiro1, Tabuleiro tabuleiro2, int qntdSubmarinos, int qntdCouracados, int qntdPortaAvioes){
        this.tabuleiro1 = tabuleiro1;
        this.tabuleiro2 = tabuleiro2;
        qntdNavios[0] = 3;//qntdSubmarinos
        qntdNavios[1] = 2;//qntdCouracados
        qntdNavios[2] = 1;//qntdPortaAvioes
    }

    public Tabuleiro getTabuleiro1() {
        return tabuleiro1;
    }

    public void setTabuleiro1(Tabuleiro tabuleiro1) {
        this.tabuleiro1 = tabuleiro1;
    }

    public Tabuleiro getTabuleiro2() {
        return tabuleiro2;
    }

    public void setTabuleiro2(Tabuleiro tabuleiro2) {
        this.tabuleiro2 = tabuleiro2;
    }
    
}
