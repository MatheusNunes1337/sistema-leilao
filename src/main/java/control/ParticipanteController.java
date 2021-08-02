package control;

import java.util.Scanner;

import dao.ParticipanteDAO;
import model.Participante;

public class ParticipanteController {
	
	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		int opcao = 0;
		do {
			System.out.print("\n-------  Participante -------");
			System.out.print(		
				"\n1. Cadastrar Participante" +
				"\n2. Listar todos participantes" +
				"\n3. Buscar pelo ID" +
				"\n4. Buscar pelo Email" +
				"\n5. Atualizar informações" +
				"\n6. Deletar participante" +
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
					getByEmail();
					break;
				case 5:
					update();
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

	private static void cadastrar() {
		Participante p = new Participante();

		System.out.println("\n++++++ Cadastro de novo participante ++++++");
        System.out.print("Digite o nome do participante: ");
        p.setNome(input.nextLine());
        System.out.print("\nDigite o login do participante: ");
        p.setLogin(input.nextLine());
        System.out.print("\nDigite a senha do participante: ");
        p.setSenha(input.nextLine());
        System.out.print("\nDigite o email do participante: ");
        p.setEmail(input.nextLine());
        System.out.print("\nDigite o endereço do participante: ");
        p.setEndereco(input.nextLine());
        System.out.print("\nDigite o telefone do participante: ");
        p.setTelefone(input.nextLine());
        p.setSituacao(true);

        if(ParticipanteDAO.registrar(p)) {
        	System.out.println("\nParticipante cadastrado com sucesso!");
        } else {
        	System.out.println("\nHouve um erro ao tentar cadastrar o participante");
        }
	}

	private static void getAll() {
        System.out.println("\nLista de participantes cadastrados no sistema:\n" + ParticipanteDAO.getParticipantes());
	}

	private static void getById() {
        System.out.print("\nDigite o id do participante ");
        Participante participante = ParticipanteDAO.getParticipanteById(input.nextInt());
        input.nextLine();
        if(participante != null) {
        	System.out.println(participante);
        } else {
        	System.out.println("Nenhum participante com esse Id foi encontrado no sistema. Tente novamente.");
        }
	}

	private static void getByEmail() {
        System.out.print("\nDigite o email do participante ");
        Participante participante = ParticipanteDAO.getParticipanteByEmail(input.nextLine());
        if(participante != null) {
        	System.out.println(participante);
        } else {
        	System.out.println("Nenhum participante com esse email foi encontrado no sistema. Tente novamente.");
        }
	}

	private static void update() {
		System.out.println("\n++++++ Atualizar informações de um participante ++++++");
		Participante participante = null;
        int opcao = 0;
        do{
            System.out.print("\nDigite o Id do participante (Zero p/sair): ");
            int id = input.nextInt();
            input.nextLine();
            if(id == 0) {
            	opcao = 0;
            } else {
                participante = ParticipanteDAO.getParticipanteById(id);
                if(participante == null){
                    System.out.println("Código inválido.");
                } else{
                    System.out.println("Nome: " + participante.getNome());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.println("Digite o novo nome do participante: ");
                        participante.setNome(input.nextLine());
                    }
                    System.out.println("Login: " + participante.getLogin());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.print("Digite o novo login do participante: ");
                        participante.setLogin(input.next());
                    }
                    System.out.println("Senha: " + participante.getSenha());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.print("Digite a nova senha do participante: ");
                        participante.setSenha(input.next());
                    }
                    System.out.println("Email: " + participante.getEmail());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.print("Digite o novo email do participante: ");
                        participante.setEmail(input.next());
                    }
                    System.out.println("Endereço: " + participante.getEndereco());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.print("Digite o novo Endereço do participante: ");
                        participante.setEndereco(input.next());
                    }
                    System.out.println("Telefone: " + participante.getTelefone());
                    System.out.print("Alterar? (0-sim/1-não) ");
                    if(input.nextInt() == 0){
                    	input.nextLine();
                        System.out.print("Digite o novo telefone do participante: ");
                        participante.setTelefone(input.next());
                    }
                    participante.setSituacao(true);
                    if(ParticipanteDAO.updateParticipante(participante)){
                        System.out.println("Informações alteradas com sucesso");
                    }else{
                        System.out.println("Erro ao tentar alterar as informações do participante. Tente novamente.");
                    }
                    opcao = 1;
                }

            }
        } while(opcao != 0); 
	}

	private static void delete() {
        System.out.print("\nDigite o id do participante ");
        int id = input.nextInt();
        input.nextLine();
        boolean situacao = false;
        input.nextLine();
        if(ParticipanteDAO.softDeleteParticipante(id, situacao)) {
        	System.out.println("Participante deletado com sucesso do sistema.");
        } else {
        	System.out.println("Erro ao tentar deletar participante do sistema. Tente novamente.");
        }
	}

}