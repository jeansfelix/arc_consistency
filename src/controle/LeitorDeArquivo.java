package controle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class LeitorDeArquivo {
	
	FileInputStream stream; 
	InputStreamReader arquivo;
	BufferedReader lerArquivo;
	
	public void carregarArquivo(String nomeArquivo) 
	{
		try 
		{
			stream = new FileInputStream(nomeArquivo); 
			arquivo = new InputStreamReader(stream); 
			lerArquivo = new BufferedReader(arquivo);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public void lerLinhas()
	{
		String linha;
		try 
		{
			linha = lerArquivo.readLine();
			while (null != linha)
			{ 
				System.out.println(linha);
				//TODO c�digo para tratar a cria��o das vari�veis;
				linha = lerArquivo.readLine();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
