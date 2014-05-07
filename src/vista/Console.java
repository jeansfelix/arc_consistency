package vista;

import java.util.List;
import java.util.Scanner;

import modelo.Variavel;
import controle.LeitorDeArquivo;
import controle.ArcoConsistencia;

public class Console
{
	Scanner scanner;
	String nomeArquivo;
	LeitorDeArquivo leitor;
	ArcoConsistencia algoritmo;
	
	public Console() 
	{
		algoritmo = new ArcoConsistencia();
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
			
			System.out.println("\n");
			
			for ( Variavel var : leitor.getListaVariaveis() )
			{
				var.print();
			}
			System.out.println("\n");
	}
	
	public void executarAlg() 
	{
		List<Variavel> variaveis = algoritmo.arcoConsistencia(leitor.getListaVariaveis());
		
		if (variaveis.isEmpty()) 
		{
			return;
		}
		
		for (Variavel var : variaveis) 
		{
			var.print();
		}
	}
	
	public void executarAlgRainhas()
	{
		
	}
	
}
