import dao.ClienteDao;
import dao.PedidoDao;
import dao.ProdutoDao;
import modelos.Cliente;
import modelos.Pedido;
import modelos.Produto;
import services.CarrinhoServices;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int opcao;

		do {

			System.out.println("\n===== SISTEMA =====");
			System.out.println("1 - Gerenciar Produtos");
			System.out.println("2 - Gerenciar Clientes");
			System.out.println("3 - Compras");
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

				case 3:
					menuCompras();
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

	public static void menuCompras() {

		Scanner scan = new Scanner(System.in);

		ClienteDao clienteDao = new ClienteDao();
		ProdutoDao produtoDao = new ProdutoDao();
		PedidoDao pedidoDao = new PedidoDao();

		CarrinhoServices carrinho = new CarrinhoServices();


		Cliente cliente = null;

		int opcao;


		do {

			System.out.println("\n===== MENU COMPRAS =====");
			System.out.println("1 - Selecionar cliente");
			System.out.println("2 - Adicionar produto ao carrinho");
			System.out.println("3 - Remover produto do carrinho");
			System.out.println("4 - Visualizar carrinho");
			System.out.println("5 - Finalizar pedido");
			System.out.println("0 - Voltar");

			System.out.print("Escolha: ");

			opcao = scan.nextInt();


			switch(opcao) {


				case 1:

					System.out.print("Digite o ID do cliente: ");

					int idCliente = scan.nextInt();


					cliente = clienteDao.consultar(idCliente);


					if(cliente != null) {

						carrinho.definirCliente(cliente);

						System.out.println(
								"Cliente selecionado: "
										+ cliente.getNome()
						);

					}

					break;



				case 2:

					System.out.print("Digite o ID do produto: ");

					int idProduto = scan.nextInt();


					Produto produto = produtoDao.consultar(idProduto);


					if(produto != null) {

						carrinho.adicionarProduto(produto);

						System.out.println(
								"Produto adicionado ao carrinho!"
						);

					} else {

						System.out.println("Produto não encontrado!");

					}

					break;



				case 3:

					System.out.print("Digite o ID do produto para remover: ");

					int idRemover = scan.nextInt();


					Produto produtoRemover =
							produtoDao.consultar(idRemover);


					if(produtoRemover != null) {

						carrinho.removerProduto(produtoRemover);

						System.out.println(
								"Produto removido!"
						);

					} else {

						System.out.println(
								"Produto não encontrado!"
						);

					}

					break;



				case 4:

					if(cliente == null){

						System.out.println(
								"Selecione um cliente antes!"
						);

					} else {

						System.out.println("\n===== CARRINHO =====");
						System.out.println(
								carrinho.visualizarCarrinho()
						);

					}

					break;



				case 5:


					if(cliente == null) {

						System.out.println(
								"Selecione um cliente primeiro!"
						);

						break;
					}


					try {


						Pedido pedido =
								carrinho.finalizarPedido(cliente);


						pedidoDao.salvar(pedido);


						System.out.println(
								"Pedido finalizado com sucesso!"
						);


						System.out.println(
								"ID do pedido: "
										+ pedido.getId()
						);


					} catch(Exception e) {

						System.out.println(
								e.getMessage()
						);

					}


					break;



				case 0:

					System.out.println(
							"Voltando..."
					);

					break;



				default:

					System.out.println(
							"Opção inválida!"
					);

			}


		} while(opcao != 0);

	}
}