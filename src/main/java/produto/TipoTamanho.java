package produto;

public enum TipoTamanho {
    P(1), G(1.5), M(1.3);
    //IMPLEMENTE A LOGICA DO ENUM
    //TODO
    private final double multiplicador;

    TipoTamanho(final double multiplicador) {
        this.multiplicador = multiplicador;
    }

    public double getMultiplicador() {
        return multiplicador;
    }
}
