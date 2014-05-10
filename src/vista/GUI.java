package vista;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Variavel;
import controle.ArcoConsistencia;
import controle.LeitorDeArquivo;

public class GUI
{
	ArcoConsistencia	algoritmo;
	String				nomeArquivoEntrada;
	String				nomeArquivoSaida;
	Scanner				scanner;
	FileWriter			arq;
	LeitorDeArquivo		leitor;

	JFrame				frame;
	JButton				botaoConfirmar;
	JPanel				fundo;
	Label				texto;
	JTextField			nomeArquivo;

	public GUI()
	{
		fundo = new JPanel();
		botaoConfirmar = new JButton();
		texto = new Label("Entre com o endereço de um arquivo e click em ok:");
		nomeArquivo = new JTextField(20);
		frame = new JFrame();

		algoritmo = new ArcoConsistencia();
		scanner = new Scanner(System.in);
		leitor = new LeitorDeArquivo();
		nomeArquivoSaida = "";
	}

	private void criarFrame(String titulo, JPanel panel, Integer largura, Integer altura)
	{
		frame.setSize(largura, altura);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
	}

	public void iniciaJanela(String titulo)
	{

		botaoConfirmar.setText("ok");
		botaoConfirmar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JPanel aviso = new JPanel();
				JLabel informativo = new JLabel(
						"O arquivo de saida se encontra em :");
				JLabel arquivo = new JLabel();

				nomeArquivoEntrada = nomeArquivo.getText();

				lerArquivo();
				executarAlg();

				arquivo.setText(nomeArquivoSaida);

				aviso.add(informativo);
				aviso.add(arquivo);

				criarFrame("Resultado", aviso, 300, 80);

				fundo.setVisible(false);

			}
		});

		fundo.add(texto);
		fundo.add(nomeArquivo);
		fundo.add(botaoConfirmar);

		criarFrame(titulo, fundo, 300, 100);

		frame.setVisible(true);
	}

	private void lerArquivo()
	{
		String[] split = nomeArquivoEntrada.split("\\\\");

		for (int i = 0; i < split.length - 1; i++)
		{
			nomeArquivoSaida += split[i] + "\\";
		}

		nomeArquivoSaida += "saida.txt";

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
					//imprimeTab(gravarArq, var);
					
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
	
	/**
	 * Metodo para imprimir uma lista formatada de rainhas.
	 * <li>
	 * 	<i>Descomente-o em escreverEmArquivo(List<List<Variavel>> conjunto)</i>
	 * 	<i>Comente gravarArq.println(var.print()); em escreverEmArquivo(List<List<Variavel>> conjunto)</i>
	 * </li>
	 * */
	@SuppressWarnings("unused")
	private void imprimeTab(PrintWriter gravarArq, Variavel var) 
	{
		List<String> linha = new ArrayList<String>(); 
		
		if (var.getId().equalsIgnoreCase("Q1")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
		
		if (var.getId().equalsIgnoreCase("Q2")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
		
		if (var.getId().equalsIgnoreCase("Q3")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
		
		if (var.getId().equalsIgnoreCase("Q4")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
		
		if (var.getId().equalsIgnoreCase("Q5")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
		
		if (var.getId().equalsIgnoreCase("Q6")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
		
		if (var.getId().equalsIgnoreCase("Q7")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
		
		if (var.getId().equalsIgnoreCase("Q8")) 
		{
			printLinhaTab(gravarArq, var, linha);
			return;
		}
	}

	private void printLinhaTab(PrintWriter gravarArq, Variavel var,
			List<String> linha)
	{
		for (int i=1; i<9; i++) 
		{
			if (i != Integer.parseInt((String) var.getDominio().toArray()[0]) ) 
			{
				linha.add("0");
			}
			else {
				linha.add("R");
			}
		}
		gravarArq.println(linha);
	}
}
