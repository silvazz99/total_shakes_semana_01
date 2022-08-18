package exceptions;

public class QuantidadeInvalida extends IllegalArgumentException {
    private static final String message = "Quantidade Inválida";

    public QuantidadeInvalida() {
        super(message);
    }
}
