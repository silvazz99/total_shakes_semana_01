package exceptions;

public class IngredienteNaoCadastrado extends IllegalArgumentException{
    private static final String message = "Ingrediente não encontrado";

    public IngredienteNaoCadastrado() {
        super(message);
    }
}
