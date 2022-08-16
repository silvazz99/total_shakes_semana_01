package Armazem;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {

    public Armazem(){}

    TreeMap<Ingrediente, Integer> estoque = new TreeMap<>();

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        if(estoque.get(ingrediente) == null){
            estoque.put(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente já cadastrado.");
        }

    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        estoque.remove(ingrediente);
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        Integer qtdIngrediente = estoque.get(ingrediente);
        if(qtdIngrediente != null) {
            estoque.put(ingrediente, qtdIngrediente + quantidade);
        } else throw new IllegalArgumentException("Ingrediente não encontrado");
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        Integer qtdIngrediente = estoque.get(ingrediente);
        if(qtdIngrediente != null) {
            estoque.put(ingrediente, qtdIngrediente - quantidade);
        }
    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        if(estoque.get(ingrediente) != null) {
            return estoque.get(ingrediente);
        } else throw new IllegalArgumentException("Ingrediente não encontrado");
    }
}
