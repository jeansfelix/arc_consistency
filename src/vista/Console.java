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
		List<Variavel> resposta = new ArrayList<Variavel>();

		resposta = algoritmo.arcoConsistencia(leitor.getListaVariaveis());

		if (resposta.isEmpty())
		{
			return;
		}

		escreverEmArquivo(resposta);
	}

	private void escreverEmArquivo(List<Variavel> resposta)
	{
		try
		{
			arq = new FileWriter(nomeArquivoSaida);

			for (Variavel var : resposta)
			{
				PrintWriter gravarArq = new PrintWriter(arq);
				gravarArq.println(var.print());
			}

			arq.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
