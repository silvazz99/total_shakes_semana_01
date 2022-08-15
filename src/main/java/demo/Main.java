package demo;

import ingredientes.*;
import pedido.Cardapio;
import pedido.Cliente;
import produto.Shake;
import produto.TipoTamanho;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cardapio cardapio = new Cardapio();

        Base sorvete = new Base(TipoBase.SORVETE);
        Base iogurte = new Base(TipoBase.IORGUTE);
        Base leite = new Base(TipoBase.LEITE);
        Fruta banana = new Fruta(TipoFruta.BANANA);
        Fruta morango = new Fruta(TipoFruta.MORANGO);
        Fruta abacate = new Fruta(TipoFruta.ABACATE);
        Topping mel = new Topping(TipoTopping.MEL);
        Topping aveia = new Topping(TipoTopping.AVEIA);
        Topping choco = new Topping(TipoTopping.CHOCOLATE);

        cardapio.adicionarIngrediente(sorvete,10.0);
        cardapio.adicionarIngrediente(iogurte,8.0);
        cardapio.adicionarIngrediente(leite, 5.0);
        cardapio.adicionarIngrediente(banana,5.0);
        cardapio.adicionarIngrediente(morango,7.5);
        cardapio.adicionarIngrediente(abacate, 6.0);
        cardapio.adicionarIngrediente(mel,1.0);
        cardapio.adicionarIngrediente(aveia, 3.0);
        cardapio.adicionarIngrediente(choco, 5.0);

        Scanner sc = new Scanner(System.in);

        System.out.println("Seu nome: ");
        String nome = sc.nextLine();

        System.out.println("Seu email: ");
        String str = sc.nextLine();
        Cliente cliente =  new Cliente(1, nome, str);

        System.out.println("::::: Cardapio ShakeCIT");
        System.out.println(cardapio.getPrecos());

        System.out.println("Qual a base do seu pedido? ");
        System.out.println("Digite o número correspondente: [1 - IORGUTE; 2 - SORVETE; 3 - LEITE]");
        Base basePedido = new Base(TipoBase.values()[sc.nextInt() - 1]);

        System.out.println("Qual a fruta do seu pedido? ");
        System.out.println("Digite o número correspondente: [1 - BANANA; 2 - MORANGO; 3 - ABACATE]");
        Fruta frutaPedido = new Fruta(TipoFruta.values()[sc.nextInt() - 1]);

        System.out.println("Qual o topping do seu pedido? ");
        System.out.println("Digite o número correspondente: [1 - MEL; 2 - AVEIA; 3 - CHOCOLATE]");
        Topping toppingPedido = new Topping(TipoTopping.values()[sc.nextInt() - 1]);

        System.out.println("Qual o tamanho do seu pedido? ");
        System.out.println("Digite o número correspondente: [1 - PEQUENO; 2 - MÉDIO; 3 - GRANDE]");
        TipoTamanho tamanhoPedido = TipoTamanho.values()[sc.nextInt() - 1];

        Shake shake = new Shake(basePedido, frutaPedido, toppingPedido, tamanhoPedido);
        System.out.println(cliente);
        System.out.println(shake);
    }
}
