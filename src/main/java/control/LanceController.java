package control;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.LanceDAO;
import model.Lance;

public class LanceController {
	
	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		int opcao = 0;
		do {
			System.out.print("\n-------  Lance -------");
			System.out.print(		
				"\n1. Fazer lance" +
				"\n2. Listar todos os lances" +
				"\n3. Buscar pelo ID" +
				"\n4. Buscar lances maiores que um determinado valor" +
				"\n5. Atualizar informações" +
				"\n6. Deletar lance" +
				"\nOpção (Zero p/sair): ");
			opcao = input.nextInt();
			input.nextLine();
			switch(opcao) {
				case 1:
					fazerLance();
					break;
                case 2:
                    getAll();
                    break;    
				case 3:
					getById();
					break;
				case 4:
					getGreaterThan();
					break;
				case 5:
					updateValor();
					break;
				case 6:
					delete();
					break;		
				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		} while(opcao != 0) ;
		System.out.println("\n\n!Fim da aplicação!");
		input.close();
	}

	private static void fazerLance() {
         System.out.print("\nDigite o valor do lance "); //fazer uma verificação do valor mínimo do item na classe de LanceDAO
         double valor = input.nextDouble();
         input.nextLine();
    	 System.out.print("\nDigite o id do participante ");
         int part_id = input.nextInt();
         input.nextLine();
         System.out.print("\nDigite o id do item ");
         int item_id = input.nextInt();
         input.nextLine();
         boolean situacao = true;

        try {
			if(LanceDAO.registrarLance(valor, part_id, item_id, situacao)) {
				System.out.println("\nlance realizado com sucesso!");
			} else {
				System.out.println("\nHouve um erro ao tentar realizar o lance");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void getAll() {
        System.out.println("\nLista de lances cadastrados no sistema:\n" + LanceDAO.getLances());
	}

	private static void getById() {
        System.out.print("\nDigite o id do lance ");
        Lance lance = LanceDAO.getLanceById(input.nextInt());
        input.nextLine();
        if(lance != null) {
        	System.out.println(lance);
        } else {
        	System.out.println("Nenhum lance com esse Id foi encontrado no sistema. Tente novamente.");
        }
	}

	private static void getGreaterThan() {
        System.out.print("\nDigite o valor mínimo do lance");
        System.out.println("\nLista de lances de valor superior ao informado:\n" + LanceDAO.getLanceGreaterThan(input.nextDouble()));
        input.nextLine();    
	}

	private static void updateValor() {
		System.out.println("\n++++++ Atualizar informações de um lance ++++++");
		Lance lance = null;
        int opcao = 0;
        do{
            System.out.print("\nDigite o Id do lance (Zero p/sair): ");
            int id = input.nextInt();
            input.nextLine();
            if(id == 0) {
            	opcao = 0;
            } else {
                lance = LanceDAO.getLanceById(id);
                double valor = lance.getValorLance();
                if(lance == null){
                    System.out.println("Id inválido.");
                } else{
                    System.out.println("Valor do lance : " + lance.getValorLance()); //alterar a updateLance na DAO. Só permitir atualizar o valor do lance.
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite o novo valor do lance: ");
                        valor = input.nextDouble();
                        input.nextLine();
                    }
                    if(LanceDAO.updateLance(valor, lance.getId())){
                        System.out.println("Informações alteradas com sucesso");
                    }else{
                        System.out.println("Erro ao tentar alterar o valor do lance. Tente novamente.");
                    }
                    opcao = 1;
                }

            }
        } while(opcao != 0); 
	}

	private static void delete() {
        System.out.print("\nDigite o id do lance ");
        int id = input.nextInt();
        input.nextLine();
        boolean situacao = false;
        if(LanceDAO.softDeleteLance(id, situacao)) {
        	System.out.println("lance deletado com sucesso do sistema.");
        } else {
        	System.out.println("Erro ao tentar deletar lance do sistema. Tente novamente.");
        }
	}

}