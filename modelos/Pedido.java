package modelos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido {

    private int id;
    private Cliente cliente;
    private LocalDate data;
    private String status;
    private List<Produto> listaProdutos;


    public Pedido() {
        listaProdutos = new ArrayList<>();
    }


    public Pedido(int id, Cliente cliente, LocalDate data, String status) {
        this.id = id;
        this.cliente = cliente;
        this.data = data;
        this.status = status;
        this.listaProdutos = new ArrayList<>();
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public Cliente getCliente() {
        return cliente;
    }


    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public LocalDate getData() {
        return data;
    }


    public void setData(LocalDate data) {
        this.data = data;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }


    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }


    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }


    public void removerProduto(Produto produto) {
        listaProdutos.remove(produto);
    }


    @Override
    public String toString() {

        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente.getNome() +
                ", data=" + data +
                ", status='" + status + '\'' +
                ", produtos=" + listaProdutos +
                '}';
    }
}