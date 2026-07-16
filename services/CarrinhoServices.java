package services;

import java.time.LocalDate;

import modelos.Cliente;
import modelos.Pedido;
import modelos.Produto;

public class CarrinhoServices {


    private Pedido pedido;


    public CarrinhoServices() {
        pedido = new Pedido();
    }


    public void adicionarProduto(Produto produto) {
        pedido.adicionarProduto(produto);
    }



}