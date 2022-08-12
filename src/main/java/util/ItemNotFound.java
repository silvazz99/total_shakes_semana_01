package util;

public class ItemNotFound extends IllegalArgumentException {
    private static final String message = "Item nao existe no pedido.";

    public ItemNotFound() {
        super(message);
    }
}
