package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Variavel;
import controle.LeitorDeArquivo;
import controle.ArcoConsistencia;
import controle.Rainhas;

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
		List<Variavel> resposta = new ArrayList<Variavel>();
		
		if (!nomeArquivo.contains("rainhas")) 
		{
			resposta = algoritmo.arcoConsistencia(leitor.getListaVariaveis());
		}
		else
		{
			Rainhas algParaRainhas = new Rainhas();
			
			List<Variavel> variaveis = algParaRainhas.inicializaCondicoesRainhas(leitor.getListaVariaveis());
			resposta = algoritmo.arcoConsistencia(variaveis);
		}
		
		if (resposta.isEmpty()) 
		{
			return;
		}
		
		for (Variavel var : resposta) 
		{
			var.print();
		}
	}
	
}
