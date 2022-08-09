package pedido;

import ingredientes.Adicional;
import ingredientes.Base;

import java.util.ArrayList;
import java.util.List;

public class Pedido{

    private int id;
    private  ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public double calcularTotal(Cardapio cardapio){
        double precoTotal = 0;
        for (ItemPedido itemPedido : this.itens){
            Base basePedido = itemPedido.getShake().getBase();
            Double precoBase = cardapio.buscarPreco(basePedido);

            switch (itemPedido.getShake().getTipoTamanho()) {
                case P:
                    precoTotal += precoBase * itemPedido.getQuantidade();
                    break;
                case M:
                    precoTotal += precoBase * 1.3 * itemPedido.getQuantidade();
                    break;
                case G:
                    precoTotal += precoBase * 1.5 * itemPedido.getQuantidade();
                    break;
            }
        };

        precoTotal += this.calcularAdicionais(cardapio);

        return precoTotal;
    }

    private double calcularAdicionais(Cardapio cardapio) {
        double precoTotalAdicionais = 0;
        for(ItemPedido itemPedido : this.itens ) {
            double precoAdicionais = 0;
            List<Adicional> adicionais = itemPedido.getShake().getAdicionais();

            for(Adicional adicional : adicionais) {
                precoAdicionais += cardapio.buscarPreco(adicional);
            }

            precoTotalAdicionais += precoAdicionais * itemPedido.getQuantidade();
        }

        return precoTotalAdicionais;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){
        this.itens.add(itemPedidoAdicionado);
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        //substitua o true por uma condição
        if (this.itens.contains(itemPedidoRemovido)) {
            this.itens.remove(itemPedidoRemovido);
        } else {
            throw new IllegalArgumentException("Item nao existe no pedido.");
        }
        return false;
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
