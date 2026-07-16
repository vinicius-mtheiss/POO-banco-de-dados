import java.util.List;
import java.util.Scanner;

import dao.ProdutoDao;
import modelos.Produto;

import static utils.ConectaDB.conectar;


public class Main {
Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ProdutoDao dao = new ProdutoDao();

		int opcao;

		do {
			System.out.println("\n========== MENU ==========");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Consultar produto por ID");
			System.out.println("3 - Listar produtos");
			System.out.println("4 - Alterar produto");
			System.out.println("5 - Excluir produto");
			System.out.println("0 - Sair");
			System.out.print("Opção: ");

			opcao = sc.nextInt();
			sc.nextLine();

			switch (opcao) {

				case 1:
					Produto novo = new Produto();

					System.out.print("Descrição: ");
					novo.setDescricao(sc.nextLine());

					System.out.print("Preço: ");
					novo.setPreco(sc.nextDouble());

					dao.salvar(novo);

					System.out.println("Produto cadastrado com ID: " + novo.getId());
					break;

				case 2:

					System.out.print("Informe o ID: ");
					int id = sc.nextInt();

					Produto p = dao.consultar(id);

					if (p != null) {
						System.out.println("\nProduto encontrado:");
						System.out.println("ID: " + p.getId());
						System.out.println("Descrição: " + p.getDescricao());
						System.out.println("Preço: R$ " + p.getPreco());
					} else {
						System.out.println("Produto não encontrado.");
					}

					break;

				case 3:

					List<Produto> lista = dao.consultar();

					if (lista.isEmpty()) {
						System.out.println("Nenhum produto cadastrado.");
					} else {

						System.out.println("\nLista de Produtos");

						for (Produto prod : lista) {
							System.out.println("---------------------------");
							System.out.println("ID: " + prod.getId());
							System.out.println("Descrição: " + prod.getDescricao());
							System.out.println("Preço: R$ " + prod.getPreco());
						}
					}

					break;

				case 4:

					System.out.print("Informe o ID do produto: ");
					int idAlterar = sc.nextInt();
					sc.nextLine();

					Produto alterar = dao.consultar(idAlterar);

					if (alterar != null) {

						System.out.print("Nova descrição: ");
						alterar.setDescricao(sc.nextLine());

						System.out.print("Novo preço: ");
						alterar.setPreco(sc.nextDouble());

						dao.alterar(alterar);

						System.out.println("Produto alterado com sucesso.");

					} else {
						System.out.println("Produto não encontrado.");
					}

					break;

				case 5:

					System.out.print("Informe o ID do produto: ");
					int idExcluir = sc.nextInt();

					Produto excluir = dao.consultar(idExcluir);

					if (excluir != null) {
						dao.deletar(idExcluir);
						System.out.println("Produto excluído com sucesso.");
					} else {
						System.out.println("Produto não encontrado.");
					}

					break;

				case 0:
					System.out.println("Programa encerrado.");
					break;

				default:
					System.out.println("Opção inválida.");
			}

		} while (opcao != 0);

		sc.close();
	}
}


