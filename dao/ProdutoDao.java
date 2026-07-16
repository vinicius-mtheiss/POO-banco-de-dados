package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.ICRUD;
import modelos.Produto;
import utils.ConectaDB;

public class ProdutoDao implements ICRUD {

	@Override
	public Produto salvar(Produto prod) {
		String sql = "insert into produto(descricao, preco, quantidade)values(?,?,?)";
		
		Connection con = ConectaDB.conectar();
		try {
			PreparedStatement stm = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			stm.setString(1, prod.getDescricao());
			stm.setDouble(2, prod.getPreco());
			stm.setInt(3, prod.getQuantidade());
			stm.execute();
			
			ResultSet rs = stm.getGeneratedKeys();

	        if(rs.next()){
	            prod.setId(rs.getInt(1));
	        }			
			stm.close();
			con.close();
			rs.close();
			
			return prod;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public void deletar(int id) {
		    String sql = "delete from produto where id = ?";
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
	public void alterar(Produto prod) {
		String sql = "UPDATE produto SET descricao = ?, preco = ?, quantidade = ? WHERE id = ?";

		Connection con = ConectaDB.conectar();

		try {
			PreparedStatement stm = con.prepareStatement(sql);

			stm.setString(1, prod.getDescricao());
			stm.setDouble(2, prod.getPreco());
			stm.setInt(3, prod.getQuantidade());
			stm.setInt(4, prod.getId());

			stm.executeUpdate();

			stm.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Produto consultar(int id) {
		Produto produto = null;
		Connection con = ConectaDB.conectar();
		try {
			PreparedStatement stm = con.prepareStatement("select * from produto where id = ?");
			stm.setInt(1, id);
			ResultSet rs = stm.executeQuery();
			if(rs.next()) {				
				produto = new Produto(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
			}
			rs.close();
			stm.close();
			con.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return produto;
	}

	@Override
	public List<Produto> consultar() {
		List<Produto> produtos = new ArrayList<Produto>();
		Connection con = ConectaDB.conectar();
		try {
			PreparedStatement stm = con.prepareStatement("select * from produto");
			ResultSet rs = stm.executeQuery();
			while(rs.next()) {				
				Produto p = new Produto(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getInt(4));
				produtos.add(p);
			}
			rs.close();
			stm.close();
			con.close();			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return produtos;
	}

}
