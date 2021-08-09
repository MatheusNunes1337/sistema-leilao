package control;

import java.util.Scanner;

import dao.ItemDAO;
import dao.LanceDAO;
import model.ItemLeilao;
import model.Lance;

public class ItemController {
	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		int opcao = 0;
		do {
			System.out.print("\n-------  Item -------");
			System.out.print(		
				"\n1. Cadastrar Item" +
				"\n2. Listar todos os itens do sistema" +
				"\n3. Buscar pelo ID" +
				"\n4. Buscar itens por leilão" +
				"\n5. Arrematar item" +
				"\n6. Atualizar informações" +
				"\n7. Deletar item" +
				"\nOpção (Zero p/sair): ");
			opcao = input.nextInt();
			input.nextLine();
			switch(opcao) {
				case 1:
					cadastrar();
					break;
                case 2:
                    getAll();
                    break;    
				case 3:
					getById();
					break;
				case 4:
					getByLeilao();
					break;
				case 5:
					arrematar();
					break;	
				case 6:
					updateItem();
					break;
				case 7:
					delete();
					break;		
				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		} while(opcao != 0) ;
		System.out.println("\n\n!Fim da aplicação!");
		input.close();
	}

	private static void cadastrar() {
		 ItemLeilao item = new ItemLeilao();
		 System.out.println("\n++++++ Cadastro de um novo item ++++++");
         System.out.print("\nDigite o id do leilão que você deseja cadastrar esse item "); 
         int leilao_id = input.nextInt();
         input.nextLine();
    	 System.out.print("\nDigite o titulo do item ");
         item.setTitulo(input.nextLine());
         System.out.print("\nDigite a descrição do item "); 
         item.setDescricao(input.nextLine());
         System.out.print("\nDigite o valor de lance mínimo do item "); 
         item.setLanceMinimo(input.nextDouble());
         input.nextLine();
         item.setStatusArremate(false);
         item.setSituacao(true);

			if(ItemDAO.registrarItem(item, leilao_id)) {
				System.out.println("\nItem cadastrado com sucesso!");
			} else {
				System.out.println("\nHouve um erro ao tentar cadastrar o item");
			}
		
	}

	private static void getAll() {
        System.out.println("\nLista de itens cadastrados no sistema:\n" + ItemDAO.getItens());
	}

	private static void getById() {
        System.out.print("\nDigite o id do item ");
        ItemLeilao item = ItemDAO.getItemById(input.nextInt());
        input.nextLine();
        if(item.getId() != 0) {
        	System.out.println(item);
        } else {
        	System.out.println("Nenhum item com esse id foi encontrado no sistema. Tente novamente.");
        }
	}

	private static void getByLeilao() {
        System.out.print("\nDigite o id do leilão");
        System.out.println("\nLista de itens pertencentes ao leilão informado:\n" + ItemDAO.getItensByLeilao(input.nextInt()));
        input.nextLine();    
	}
	
	private static void arrematar() {
        System.out.print("\nDigite o id do item");
        if(ItemDAO.arrematarItem(input.nextInt())){
            System.out.println("Item arrematado com sucesso");
        }else{
            System.out.println("Erro ao tentar arrematar esse item. Tente novamente.");
        }
        input.nextLine();    
	}

	private static void updateItem() {
		System.out.println("\n++++++ Atualizar informações de um item ++++++");
		ItemLeilao item = null;
        int opcao = 0;
        do{
            System.out.print("\nDigite o Id do item (Zero p/sair): ");
            int id = input.nextInt();
            input.nextLine();
            if(id == 0) {
            	opcao = 0;
            } else {
                item = ItemDAO.getItemById(id);
                if(item == null){
                    System.out.println("Id inválido.");
                } if(item.isArrematado()){
                	System.out.println("Só é possível alterar informações de itens que ainda não foram arrematados.");
                } else{
                    System.out.println("Titulo do item: " + item.getTitulo()); 
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite o novo titulo do item: ");
                        item.setTitulo(input.nextLine());
                        
                    }
                    System.out.println("Descrição do item: " + item.getDescricao()); 
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite a nova descrição do item: ");
                        item.setDescricao(input.nextLine());  
                    }
                    System.out.println("Valor mínimo de lance: " + item.getLanceMinimo()); 
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite o novo valor mínimo de lance desse item: ");
                        item.setLanceMinimo(input.nextDouble());
                        input.nextLine();
                        
                    }
                    item.setId(id);
                    if(ItemDAO.updateItem(item)){
                        System.out.println("Informações alteradas com sucesso");
                    }else{
                        System.out.println("Erro ao tentar alterar informações do item. Tente novamente.");
                    }
                    opcao = 1;
                }

            }
        } while(opcao != 0); 
	}

	private static void delete() {
        System.out.print("\nDigite o id do item ");
        int id = input.nextInt();
        input.nextLine();
        boolean situacao = false;
        if(ItemDAO.softDeleteItem(id, situacao)) {
        	System.out.println("Item deletado com sucesso do sistema.");
        } else {
        	System.out.println("Erro ao tentar deletar o item do sistema. Tente novamente.");
        }
	}
}
