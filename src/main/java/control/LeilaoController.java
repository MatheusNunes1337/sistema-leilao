package control;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.LeilaoDAO;
import model.Leilao;

public class LeilaoController {
	
	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		int opcao = 0;
		do {
			System.out.print("\n-------  Leilão -------");
			System.out.print(		
				"\n1. Iniciar leilão" +
				"\n2. Finalizar leilão" +
				"\n3. Listar todos leilões finalizados" +
				"\n4. Listar todos leilões em andamento" +
				"\n5. Buscar pelo ID" +
				"\n6. Buscar pela data de início" +
				"\n7. Atualizar informações" +
				"\n8. Deletar leilão" +
				"\nOpção (Zero p/sair): ");
			opcao = input.nextInt();
			input.nextLine();
			switch(opcao) {
				case 1:
					iniciar();
					break;
				case 2:
                    finalizar();
					break;
                case 3:
                    getAll();
                    break;
                case 4:
					getLeiloesEmAndamento();
					break;    
				case 5:
					getById();
					break;
				case 6:
					getByDate();
					break;
				case 7:
					//update();
					break;
				case 8:
					delete();
					break;		
				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		} while(opcao != 0) ;
		System.out.println("\n\n!Fim da aplicação!");
		input.close();
	}

	private static void iniciar() {
		Leilao l = new Leilao();

        l.setDataInicio(LocalDate.now());
        l.setHoraInicio(LocalTime.now());
        l.setSituacao(true);

        if(LeilaoDAO.iniciarLeilao(l)) {
        	System.out.println("\nleilão iniciado com sucesso!");
        } else {
        	System.out.println("\nHouve um erro ao tentar iniciar o leilão");
        }
	}

    private static void finalizar() {
        System.out.print("\nDigite o id do leilão ");
        int id = input.nextInt();
        input.nextLine();
        LocalDate dataFinal = LocalDate.now();
        LocalTime horaFinal = LocalTime.now();
        
        if(LeilaoDAO.finalizarLeilao(dataFinal, horaFinal, id)) {
            System.out.println("\nleilão finalizado com sucesso!");
        } else {
            System.out.println("\nHouve um erro ao tentar finalizar o leilão");
        }
    }

	private static void getAll() {
        System.out.println("\nLista de leilãos cadastrados no sistema:\n" + LeilaoDAO.getLeiloesFinalizados());
	}

	private static void getById() {
        System.out.print("\nDigite o id do leilão ");
        Leilao leilão = LeilaoDAO.getLeilaoById(input.nextInt());
        input.nextLine();
        if(leilão != null) {
        	System.out.println(leilão);
        } else {
        	System.out.println("Nenhum leilão com esse Id foi encontrado no sistema. Tente novamente.");
        }
	}
	
	private static void getLeiloesEmAndamento() {
		System.out.println("\nLista de leilões em andamento:\n" + LeilaoDAO.getLeiloesEmAndamento());
	}

	private static void getByDate() {
        System.out.print("\nDigite a data de início do leilão no formato YYYY-MM-DD");
        System.out.println("\nLista de leilões conforme a data informada:\n" + LeilaoDAO.getLeilaoByDate(input.nextLine()));
	}

	private static void update() {
		System.out.println("\n++++++ Atualizar informações de um leilão ++++++");
		Leilao leilao = null;
        int opcao = 0;
        do{
            System.out.print("\nDigite o Id do leilão (Zero p/sair): ");
            int id = input.nextInt();
            input.nextLine();
            if(id == 0) {
            	opcao = 0;
            } else {
                leilao = LeilaoDAO.getLeilaoById(id);
                String dataInicial = leilao.getDataInicio().toString();
                String horaInicial = leilao.getHoraInicio().toString();
                String dataFinal = leilao.getDataFinal().toString();
                String horaFinal = leilao.getHoraFinal().toString();
                
                if(leilao == null){
                    System.out.println("Id inválido.");
                } else{
                	System.out.println("Data inicial: " + leilao.getDataInicio());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite a nova data de inicio do leilão: ");
                        dataInicial = input.nextLine();
                    }
                    System.out.println("Hora inicial: " + leilao.getHoraInicio());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite a nova hora inicial do leilão: ");
                        horaInicial = input.nextLine();
                    }
                    System.out.println("Data final: " + leilao.getDataFinal());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite a nova data final do leilão: ");
                        dataFinal = input.nextLine();
                    }
                    System.out.println("Hora final: " + leilao.getHoraFinal());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite a nova hora final do leilão: ");
                        horaFinal = input.nextLine();
                    }
                   
                    leilao.setSituacao(true);
                    if(LeilaoDAO.updateLeilao(leilao)){
                        System.out.println("Informações alteradas com sucesso");
                    }else{
                        System.out.println("Erro ao tentar alterar as informações do leilão. Tente novamente.");
                    }
                    opcao = 1;
                }

            }
        } while(opcao != 0); 
	}

	private static void delete() {
        System.out.print("\nDigite o id do leilão ");
        int id = input.nextInt();
        input.nextLine();
        boolean situacao = false;
        if(LeilaoDAO.softDeleteLeilao(id, situacao)) {
        	System.out.println("leilão deletado com sucesso do sistema.");
        } else {
        	System.out.println("Erro ao tentar deletar leilão do sistema. Tente novamente.");
        }
	}

}