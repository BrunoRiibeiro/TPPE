package Enum;

public enum Regiao {
    CENTRO_OESTE("Centro-Oeste"),
    NORTE("Norte"),
    NORDESTE("Nordeste"),
    SUDESTE("Sudeste"),
    SUL("Sul");

    private final String nome;

    Regiao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
