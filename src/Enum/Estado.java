package Enum;

public enum Estado {
    AC("Acre", Regiao.NORTE),
    AL("Alagoas",Regiao.NORTE ),
    AP("Amapá", Regiao.NORTE),
    AM("Amazonas", Regiao.NORTE),
    BA("Bahia", Regiao.NORDESTE),
    CE("Ceará", Regiao.NORDESTE),
    DF("Distrito Federal", Regiao.CENTRO_OESTE),
    ES("Espírito Santo", Regiao.SUDESTE),
    GO("Goiás", Regiao.CENTRO_OESTE),
    MA("Maranhão", Regiao.NORDESTE),
    MT("Mato Grosso", Regiao.CENTRO_OESTE),
    MS("Mato Grosso do Sul", Regiao.CENTRO_OESTE),
    MG("Minas Gerais", Regiao.SUDESTE),
    PA("Pará", Regiao.NORTE),
    PB("Paraíba", Regiao.NORDESTE),
    PR("Paraná", Regiao.SUL),
    PE("Pernambuco", Regiao.NORDESTE),
    PI("Piauí", Regiao.NORDESTE),
    RJ("Rio de Janeiro", Regiao.SUDESTE),
    RN("Rio Grande do Norte", Regiao.NORDESTE),
    RS("Rio Grande do Sul", Regiao.SUL),
    RO("Rondônia", Regiao.NORTE),
    RR("Roraima", Regiao.NORTE),
    SC("Santa Catarina", Regiao.SUL),
    SP("São Paulo", Regiao.SUDESTE),
    SE("Sergipe", Regiao.NORDESTE),
    TO("Tocantins", Regiao.NORTE);

    private final String nome;
    private final Regiao regiao;

    Estado(String nome, Regiao regiao) {
        this.nome = nome;
        this.regiao = regiao;
    }

    public String getNome() {
        return nome;
    }  

    public  Regiao getRegiao(){
        return regiao;
    }
}
