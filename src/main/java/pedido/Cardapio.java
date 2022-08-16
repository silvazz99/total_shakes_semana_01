package pedido;

import exceptions.ExcecaoIngrediente;
import exceptions.ExcecaoPreco;
import exceptions.ItemNotFound;
import ingredientes.Ingrediente;

import java.util.*;

public class Cardapio {
    private TreeMap<Ingrediente, Double> precos;

    public Cardapio() {
        this.precos = new TreeMap<>();
    }

    public TreeMap<Ingrediente, Double> getPrecos() {
        return this.precos;
    }

    public void adicionarIngrediente(Ingrediente ingrediente, Double preco) {
        validarPreco(preco);
        this.precos.put(ingrediente, preco);
    }

    public boolean atualizarIngrediente(Ingrediente ingrediente, Double preco) {
        validarPreco(preco);
        if (precos.containsKey(ingrediente)) {
            this.precos.put(ingrediente, preco);
        } else {
            throw new ExcecaoIngrediente();
        }
        return true;
    }

    public boolean removerIngrediente(Ingrediente ingrediente) {
        if (precos.containsKey(ingrediente)) {
            precos.remove(ingrediente);
        } else {
            throw new ExcecaoIngrediente();
        }

        return true;
    }

    public Double buscarPreco(Ingrediente ingrediente) {
        if (precos.containsKey(ingrediente)) {
            return this.precos.get(ingrediente);
        } else throw new ExcecaoIngrediente();
    }

    private void validarPreco(Double preco) throws ExcecaoPreco {
        if (preco.doubleValue() <= 0) throw new ExcecaoPreco();
    }

    @Override
    public String toString() {
        return this.precos.toString();
    }

}
