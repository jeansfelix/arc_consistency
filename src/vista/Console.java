package vista;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import modelo.Variavel;
import controle.ArcoConsistencia;
import controle.LeitorDeArquivo;

public class Console
{
	Scanner				scanner;
	FileWriter			arq;
	String				nomeArquivoEntrada;
	String				nomeArquivoSaida;
	LeitorDeArquivo		leitor;
	ArcoConsistencia	algoritmo;

	public Console()
	{
		algoritmo = new ArcoConsistencia();
		scanner = new Scanner(System.in);
		leitor = new LeitorDeArquivo();
		nomeArquivoSaida = "";
	}

	public void lerArquivo()
	{
		System.out.printf("Informe o nome do arquivo de entrada:\n");
		nomeArquivoEntrada = scanner.nextLine();

		String[] split = nomeArquivoEntrada.split("\\\\");

		for (int i = 0; i < split.length - 1; i++)
		{
			nomeArquivoSaida += split[i] + "\\";
		}

		nomeArquivoSaida += "saida.txt";

		System.out.println("O arquivo de saida se encontra em " + nomeArquivoSaida);

		if (!nomeArquivoEntrada.contains(".txt"))
		{
			nomeArquivoEntrada += ".txt";
		}

		leitor.carregarArquivo(nomeArquivoEntrada);
		leitor.lerLinhas();
	}

	public void executarAlg()
	{
		List<List<Variavel>> resposta = new ArrayList<List<Variavel>>();

		resposta = algoritmo.executar(leitor.getListaVariaveis());

		if (resposta.isEmpty())
		{
			return;
		}

		escreverEmArquivo(resposta);
	}

	private void escreverEmArquivo(List<List<Variavel>> conjunto)
	{
		try
		{
			arq = new FileWriter(nomeArquivoSaida);
			PrintWriter gravarArq = new PrintWriter(arq);
			int i=0;
			
			for (List<Variavel> resposta : conjunto) {
				++i;
				gravarArq.println("Resp " + i);
				
				for (Variavel var : resposta)
				{
					gravarArq.println(var.print());
				}
				gravarArq.println("//=================================//\n");
			}
			
			gravarArq.println("\n\n");

			arq.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
