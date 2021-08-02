package control;

import java.util.Scanner;

public class HomeController {
	
	private static final Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		int opcao = 0;
		do {
			System.out.print("\n-------  Home -------");
			System.out.print(		
				"\n1. Manter Participantes" +
				"\n2. Manter Leilão" +
				"\n3. Manter Itens" +
				"\n4. Manter Lances" +
				"\nOpção (Zero p/sair): ");
			opcao = input.nextInt();
			input.nextLine();
			switch(opcao) {
				case 1:
					ParticipanteController.main(null);
					break;
				case 2:
                    //LeilaoController.main(null);
					break;
				case 3:
					//ItemController.main(null);
					break;
				case 4:
					//ItemController.main(null);
					break;
				case 5:
					//LanceController.main(null);
					break;	
				default:
					if(opcao != 0) System.out.println("Opção inválida.");
			}
		} while(opcao != 0) ;
		System.out.println("\n\n!Fim da aplicação!");
		input.close();
	}

}