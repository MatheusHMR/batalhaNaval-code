package trabalhopoov.model;

public class Tabuleiro {

    private Player playerOwner;
    private Peca[][] matrizPecas;
    private boolean playable;
    private Integer[] quantidade = {3,2,1};
    //enum de matrizpartes
    //naviointacto, navioacertado, agua
    /*
     * É necessário salvar 3 variacoes para uma parte?
     * Ou basta que seja navio e agua
     * Se for navio e for acertado, marca M
     * Se o navio afundar mostra o identificador do navio
     */

     public Tabuleiro(Player playerOwner, Peca[][] matrizPecas){
        this.playerOwner = playerOwner;
        this.matrizPecas = matrizPecas;
        playable = false;
     }
    public Player getPlayerOwner() {
        return playerOwner;
    }
    public void setPlayerOwner(Player playerOwner) {
        this.playerOwner = playerOwner;
    }
    public Peca[][] getMatrizPecas() {
        return matrizPecas;
    }
    public void setMatrizPecas(Peca[][] matrizPecas) {
        this.matrizPecas = matrizPecas;
    }
    public boolean isPlayable() {
        return playable;
    }
    public void setPlayable(boolean playable) {
        this.playable = playable;
    }
    
}
