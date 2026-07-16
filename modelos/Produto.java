package modelos;

public class Produto {
	private int id;
	private String descricao;
	private double preco;
	private int quantidade;
	
	public Produto() {
	}
	
	public Produto(String descricao, double preco) {
		setDescricao(descricao);
		setPreco(preco); 
	}
	
	public Produto(int id, String descricao, double preco, int quantidade) {
		setId(id);
		setDescricao(descricao);
		setPreco(preco);
		setQuantidade(quantidade);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Produto{" +
				"id=" + id +
				", descricao='" + descricao + '\'' +
				", preco=" + preco +
				", quantidade=" + quantidade +
				'}';
	}

}
