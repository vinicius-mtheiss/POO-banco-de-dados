import dao.ClienteDao;
import dao.ProdutoDao;
import modelos.Cliente;
import modelos.Produto;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int opcao;

		do {

			System.out.println("\n===== SISTEMA =====");
			System.out.println("1 - Gerenciar Produtos");
			System.out.println("2 - Gerenciar Clientes");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			opcao = scan.nextInt();

			switch(opcao) {

				case 1:
					menuProduto();
					break;

				case 2:
					menuCliente();
					break;

				case 0:
					System.out.println("Sistema encerrado!");
					break;

				default:
					System.out.println("Opção inválida!");

			}

		} while(opcao != 0);


		scan.close();
	}


	public static void menuProduto() {

		Scanner scan = new Scanner(System.in);

		ProdutoDao dao = new ProdutoDao();

		int opcao;

		do {

			System.out.println("\n===== MENU PRODUTO =====");
			System.out.println("1 - Cadastrar produto");
			System.out.println("2 - Alterar produto");
			System.out.println("3 - Excluir produto");
			System.out.println("4 - Consultar produto");
			System.out.println("5 - Listar produtos");
			System.out.println("0 - Voltar");

			System.out.print("Escolha: ");

			opcao = scan.nextInt();
			scan.nextLine();


			switch(opcao) {

				case 1:
					Produto p = new Produto();

					System.out.print("Descrição: ");
					p.setDescricao(scan.nextLine());

					System.out.print("Preço: ");
					p.setPreco(scan.nextDouble());

					System.out.print("Quantidade: ");
					p.setQuantidade(scan.nextInt());


					dao.salvar(p);

					System.out.println("Produto cadastrado!");
					break;


				case 2:
					System.out.print("ID do produto: ");
					int id = scan.nextInt();

					Produto produto = dao.consultar(id);

					if(produto != null) {

						scan.nextLine();

						System.out.print("Nova descrição: ");
						produto.setDescricao(scan.nextLine());

						System.out.print("Novo preço: ");
						produto.setPreco(scan.nextDouble());

						System.out.print("Nova quantidade: ");
						produto.setQuantidade(scan.nextInt());


						dao.alterar(produto);

						System.out.println("Produto alterado!");

					} else {
						System.out.println("Produto não encontrado!");
					}

					break;


				case 3:
					System.out.print("ID do produto: ");
					int idExcluir = scan.nextInt();

					dao.deletar(idExcluir);

					System.out.println("Produto excluído!");
					break;


				case 4:
					System.out.print("ID do produto: ");
					int idConsulta = scan.nextInt();

					Produto prod = dao.consultar(idConsulta);

					if(prod != null)
						System.out.println(prod);
					else
						System.out.println("Produto não encontrado!");

					break;


				case 5:

					for(Produto produtoLista : dao.consultar()) {
						System.out.println(produtoLista);
					}

					break;


				case 0:
					break;


				default:
					System.out.println("Opção inválida!");
			}


		} while(opcao != 0);

	}



	public static void menuCliente() {

		Scanner scan = new Scanner(System.in);

		ClienteDao dao = new ClienteDao();

		int opcao;


		do {

			System.out.println("\n===== MENU CLIENTE =====");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Alterar cliente");
			System.out.println("3 - Excluir cliente");
			System.out.println("4 - Consultar cliente");
			System.out.println("5 - Listar clientes");
			System.out.println("0 - Voltar");


			System.out.print("Escolha: ");

			opcao = scan.nextInt();
			scan.nextLine();


			switch(opcao) {


				case 1:

					Cliente c = new Cliente();


					System.out.print("CPF: ");
					c.setCpf(scan.nextLine());

					System.out.print("Nome: ");
					c.setNome(scan.nextLine());

					System.out.print("Email: ");
					c.setEmail(scan.nextLine());

					System.out.print("Rua: ");
					c.setRua(scan.nextLine());

					System.out.print("Número: ");
					c.setNumero(scan.nextInt());
					scan.nextLine();

					System.out.print("Bairro: ");
					c.setBairro(scan.nextLine());

					System.out.print("CEP: ");
					c.setCep(scan.nextLine());

					System.out.print("Cidade: ");
					c.setCidade(scan.nextLine());

					System.out.print("Estado: ");
					c.setEstado(scan.nextLine());


					dao.salvar(c);

					System.out.println("Cliente cadastrado!");

					break;



				case 2:

					System.out.print("ID do cliente: ");

					int id = scan.nextInt();

					Cliente cliente = dao.consultar(id);


					if(cliente != null) {

						scan.nextLine();

						System.out.print("Nome: ");
						cliente.setNome(scan.nextLine());

						System.out.print("Email: ");
						cliente.setEmail(scan.nextLine());


						dao.alterar(cliente);

						System.out.println("Cliente alterado!");

					} else {
						System.out.println("Cliente não encontrado!");
					}

					break;



				case 3:

					System.out.print("ID do cliente: ");

					int idExcluir = scan.nextInt();

					dao.deletar(idExcluir);

					System.out.println("Cliente excluído!");

					break;



				case 4:

					System.out.print("ID do cliente: ");

					int idConsulta = scan.nextInt();

					Cliente clienteConsulta = dao.consultar(idConsulta);


					if(clienteConsulta != null)
						System.out.println(clienteConsulta);
					else
						System.out.println("Cliente não encontrado!");

					break;



				case 5:

					for(Cliente clienteLista : dao.consultar()) {
						System.out.println(clienteLista);
					}

					break;



				case 0:
					break;


				default:
					System.out.println("Opção inválida!");

			}


		} while(opcao != 0);

	}
}