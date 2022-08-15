package exceptions;

public class ExcecaoPreco extends IllegalArgumentException{
    private static final String message ="Preco invalido.";

    public ExcecaoPreco() {
        super(message);
    }
}
