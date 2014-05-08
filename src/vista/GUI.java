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
import controle.Rainhas;

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

	private void criarFrame(String titulo, JPanel panel, Integer largura,Integer altura )
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
				JLabel informativo = new JLabel("O arquivo de saida se encontra em :");
				JLabel arquivo = new JLabel();
				
				nomeArquivoEntrada = nomeArquivo.getText();

				lerArquivo();
				executarAlg();
				
				arquivo.setText(nomeArquivoSaida);
				
				aviso.add(informativo);
				aviso.add(arquivo);
				
				criarFrame("Resultado", aviso, 300, 100);
				
				fundo.setVisible(false);
				

			}
		});

		fundo.add(texto);
		fundo.add(nomeArquivo);
		fundo.add(botaoConfirmar);

		criarFrame(titulo, fundo, 300, 200);
		
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

	private void executarAlg()
	{
		List<Variavel> resposta = new ArrayList<Variavel>();

		if (!nomeArquivoEntrada.contains("rainhas"))
		{
			resposta = algoritmo.arcoConsistencia(leitor.getListaVariaveis());
		}
		else
		{
			Rainhas algParaRainhas = new Rainhas();

			List<Variavel> variaveis = algParaRainhas
					.inicializaCondicoesRainhas(leitor.getListaVariaveis());
			resposta = algoritmo.arcoConsistencia(variaveis);
		}

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
