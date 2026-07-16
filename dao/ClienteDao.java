package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICRUD;
import modelos.Cliente;
import utils.ConectaDB;


public class ClienteDao implements ICRUD<Cliente> {

    @Override
    public Cliente salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente(cpf, nome, email, rua, numero, bairro, cep, cidade, estado) VALUES (?,?,?,?,?,?,?,?,?)";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, cliente.getCpf());
            stm.setString(2, cliente.getNome());
            stm.setString(3, cliente.getEmail());
            stm.setString(4, cliente.getRua());
            stm.setInt(5, cliente.getNumero());
            stm.setString(6, cliente.getBairro());
            stm.setString(7, cliente.getCep());
            stm.setString(8, cliente.getCidade());
            stm.setString(9, cliente.getEstado());

            stm.execute();

            ResultSet rs = stm.getGeneratedKeys();

            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }

            rs.close();
            stm.close();
            con.close();

            return cliente;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setInt(1, id);

            stm.execute();

            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        String sql = "UPDATE cliente SET cpf = ?, nome = ?, email = ?, rua = ?, numero = ?, bairro = ?, cep = ?, cidade = ?, estado = ? WHERE id = ?";

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stm = con.prepareStatement(sql);

            stm.setString(1, cliente.getCpf());
            stm.setString(2, cliente.getNome());
            stm.setString(3, cliente.getEmail());
            stm.setString(4, cliente.getRua());
            stm.setInt(5, cliente.getNumero());
            stm.setString(6, cliente.getBairro());
            stm.setString(7, cliente.getCep());
            stm.setString(8, cliente.getCidade());
            stm.setString(9, cliente.getEstado());
            stm.setInt(10, cliente.getId());

            stm.executeUpdate();

            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente consultar(int id) {
        Cliente cliente = null;

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM cliente WHERE id = ?");

            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );
            }

            rs.close();
            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public List<Cliente> consultar() {
        List<Cliente> clientes = new ArrayList<>();

        Connection con = ConectaDB.conectar();

        try {
            PreparedStatement stm = con.prepareStatement("SELECT * FROM cliente");

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("rua"),
                        rs.getInt("numero"),
                        rs.getString("bairro"),
                        rs.getString("cep"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );

                clientes.add(cliente);
            }

            rs.close();
            stm.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }
}