package Armazem;

import ingredientes.Ingrediente;

import java.util.TreeMap;

public class Armazem {

    public Armazem(){}

    TreeMap<Ingrediente, Integer> estoque = new TreeMap<>();

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        verificaSeIngredienteJaExiste(ingrediente);
        estoque.put(ingrediente, 0);
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        verificaSeIngredienteNaoExiste(ingrediente);
        estoque.remove(ingrediente);
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        verificaSeIngredienteNaoExiste(ingrediente);
        estoque.put(ingrediente, estoque.get(ingrediente) + quantidade);
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        verificaSeIngredienteNaoExiste(ingrediente);
        Integer qtdIngrediente = estoque.get(ingrediente);
        verificaQuantidade(qtdIngrediente, quantidade);

        estoque.put(ingrediente, qtdIngrediente - quantidade);
    }

    private void verificaQuantidade(Integer qtdIngrediente, Integer quantidade) {
        if(quantidade < 0 || qtdIngrediente < quantidade) throw new IllegalArgumentException("Quantidade Inválida");
    }

    public Integer consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        verificaSeIngredienteNaoExiste(ingrediente);
        return estoque.get(ingrediente);
    }

    private void verificaSeIngredienteJaExiste(Ingrediente ingrediente) {
        if(estoque.get(ingrediente) != null) throw new IllegalArgumentException("Ingrediente já cadastrado.");
    }

    private void verificaSeIngredienteNaoExiste(Ingrediente ingrediente) {
        if(estoque.get(ingrediente) == null) throw new IllegalArgumentException("Ingrediente não encontrado");
    }
}
