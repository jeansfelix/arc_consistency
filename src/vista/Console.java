package vista;

import java.util.Scanner;

import controle.LeitorDeArquivo;

public class Console
{
	Scanner scanner;
	String nomeArquivo;
	LeitorDeArquivo leitor;
	
	public Console() 
	{
		scanner = new Scanner(System.in);
		leitor = new LeitorDeArquivo();
	}
	
	public void lerArquivo()
	{
			System.out.printf("Informe o nome de arquivo texto:\n");
			nomeArquivo = scanner.nextLine();
			
			if (!nomeArquivo.contains(".txt")) 
			{
				nomeArquivo += ".txt"; 
			}
			
			leitor.carregarArquivo(nomeArquivo);
			leitor.lerLinhas();
	}
	

}
