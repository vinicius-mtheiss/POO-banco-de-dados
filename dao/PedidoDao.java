package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICRUD;
import modelos.Cliente;
import modelos.Pedido;
import modelos.Produto;
import utils.ConectaDB;


public class PedidoDao implements ICRUD<Pedido> {


    @Override
    public Pedido salvar(Pedido pedido) {

        Connection con = ConectaDB.conectar();

        try {

            String sql = "INSERT INTO pedido(id_cliente, data, status) VALUES (?, ?, ?)";


            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);


            stm.setInt(1, pedido.getCliente().getId());
            stm.setDate(2, Date.valueOf(pedido.getData()));
            stm.setString(3, pedido.getStatus());


            stm.execute();


            ResultSet rs = stm.getGeneratedKeys();


            if(rs.next()) {

                pedido.setId(rs.getInt(1));

            }


            // salva os produtos do pedido

            String sqlProduto =
                    "INSERT INTO pedido_produto(id_pedido, id_produto, quantidade) VALUES (?, ?, ?)";


            PreparedStatement stmProduto = con.prepareStatement(sqlProduto);


            for(Produto produto : pedido.getListaProdutos()) {


                stmProduto.setInt(1, pedido.getId());
                stmProduto.setInt(2, produto.getId());
                stmProduto.setInt(3, produto.getQuantidade());

                stmProduto.execute();

            }


            rs.close();
            stm.close();
            stmProduto.close();
            con.close();


            return pedido;


        } catch(SQLException e) {

            e.printStackTrace();
            return null;
        }

    }



    @Override
    public void deletar(int id) {


        Connection con = ConectaDB.conectar();


        try {


            // primeiro remove os produtos do pedido

            PreparedStatement stmProduto =
                    con.prepareStatement(
                            "DELETE FROM pedido_produto WHERE id_pedido = ?");


            stmProduto.setInt(1, id);

            stmProduto.execute();



            // depois remove o pedido

            PreparedStatement stm =
                    con.prepareStatement(
                            "DELETE FROM pedido WHERE id = ?");


            stm.setInt(1,id);

            stm.execute();



            stmProduto.close();
            stm.close();
            con.close();



        } catch(SQLException e){

            e.printStackTrace();

        }

    }



    @Override
    public void alterar(Pedido pedido) {


        Connection con = ConectaDB.conectar();


        try {


            String sql =
                    "UPDATE pedido SET id_cliente=?, data=?, status=? WHERE id=?";


            PreparedStatement stm =
                    con.prepareStatement(sql);


            stm.setInt(1, pedido.getCliente().getId());
            stm.setDate(2, Date.valueOf(pedido.getData()));
            stm.setString(3, pedido.getStatus());
            stm.setInt(4, pedido.getId());


            stm.executeUpdate();


            stm.close();
            con.close();



        } catch(SQLException e){

            e.printStackTrace();

        }

    }



    @Override
    public Pedido consultar(int id) {


        Pedido pedido = null;

        Connection con = ConectaDB.conectar();


        try {


            PreparedStatement stm =
                    con.prepareStatement(
                            "SELECT * FROM pedido WHERE id=?");


            stm.setInt(1,id);


            ResultSet rs = stm.executeQuery();



            if(rs.next()) {


                Cliente cliente =
                        new ClienteDao()
                                .consultar(rs.getInt("id_cliente"));



                pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setCliente(cliente);
                pedido.setData(
                        rs.getDate("data").toLocalDate()
                );
                pedido.setStatus(
                        rs.getString("status")
                );



                // busca produtos

                PreparedStatement stmProduto =
                        con.prepareStatement(
                                "SELECT p.*, pp.quantidade " +
                                        "FROM produto p " +
                                        "INNER JOIN pedido_produto pp " +
                                        "ON p.id = pp.id_produto " +
                                        "WHERE pp.id_pedido=?");


                stmProduto.setInt(1,id);


                ResultSet rsProduto =
                        stmProduto.executeQuery();



                while(rsProduto.next()) {


                    Produto produto =
                            new Produto();


                    produto.setId(
                            rsProduto.getInt("id"));


                    produto.setDescricao(
                            rsProduto.getString("descricao"));


                    produto.setPreco(
                            rsProduto.getDouble("preco"));


                    produto.setQuantidade(
                            rsProduto.getInt("quantidade"));


                    pedido.adicionarProduto(produto);

                }


                rsProduto.close();
                stmProduto.close();

            }



            rs.close();
            stm.close();
            con.close();



        }catch(SQLException e){

            e.printStackTrace();

        }


        return pedido;

    }





    @Override
    public List<Pedido> consultar() {


        List<Pedido> pedidos = new ArrayList<>();

        Connection con = ConectaDB.conectar();


        try {


            PreparedStatement stm =
                    con.prepareStatement(
                            "SELECT id FROM pedido");


            ResultSet rs =
                    stm.executeQuery();



            while(rs.next()) {


                Pedido pedido =
                        consultar(rs.getInt("id"));


                pedidos.add(pedido);

            }



            rs.close();
            stm.close();
            con.close();



        }catch(SQLException e){

            e.printStackTrace();

        }


        return pedidos;

    }

}