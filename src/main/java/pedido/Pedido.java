package pedido;

import ingredientes.Adicional;
import ingredientes.Base;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
            precoTotal += precoBase * itemPedido.getShake().getTipoTamanho().getMultiplicador() * itemPedido.getQuantidade();
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
        boolean adicionado = false;
        for (ItemPedido item : itens) {
            if(item.getShake().toString().equals(itemPedidoAdicionado.getShake().toString())) {
                item.setQuantidade(item.getQuantidade() + itemPedidoAdicionado.getQuantidade());
                adicionado = true;
                break;
            }
        }
        if(!adicionado){
            this.itens.add(itemPedidoAdicionado);
        }

    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        Optional<ItemPedido> result = itens.stream().filter(item -> item.getShake().toString().equals(itemPedidoRemovido.getShake().toString())).findFirst();

        if(!result.isEmpty()){
            int qtdShake = result.get().getQuantidade();
            if(qtdShake == 1) {
                itens.remove(result.get());
            } else {
                result.get().setQuantidade(qtdShake - 1);
            }
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
