package trabalhopoov.model;

public enum Tipo {
    COURACADO("Couraçado"), 
    PORTA_AVIOES("Porta Aviões"), 
    SUBMARINO("Submarino");

    private final String descricao;

    private Tipo(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
