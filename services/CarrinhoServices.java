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

    public void removerProduto(Produto produto) {

        pedido.removerProduto(produto);

    }

    public Pedido visualizarCarrinho() {
        return pedido;
    }

    public void definirCliente(Cliente cliente){

        pedido.setCliente(cliente);

    }

    public Pedido finalizarPedido(Cliente cliente) {

        if (pedido.getListaProdutos().isEmpty()) {
            throw new RuntimeException("Carrinho vazio!");
        }


        pedido.setCliente(cliente);
        pedido.setData(LocalDate.now());
        pedido.setStatus("Pendente");


        Pedido pedidoFinalizado = pedido;


        pedido = new Pedido();


        return pedidoFinalizado;
    }


}