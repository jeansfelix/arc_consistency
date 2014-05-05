package controle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import modelo.Condicao;
import modelo.Variavel;

public class LeitorDeArquivo {
	
	FileInputStream stream; 
	InputStreamReader arquivo;
	BufferedReader lerArquivo;
	List<Variavel> listaVariaveis;
	List<Condicao> listaCondicoes;
	
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
			this.listaVariaveis=new ArrayList<Variavel>();
			this.listaCondicoes=new ArrayList<Condicao>();
			linha = lerArquivo.readLine();
			while (null != linha)
			{ 
				if(linha.contains("{")) criaVariavel(linha);
				else if(linha.contains("=") || linha.contains("<") || linha.contains(">")) criaCondicao(linha);
				
				linha = lerArquivo.readLine();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	private void criaVariavel(String linha)
	{
		int indiceInicio=linha.indexOf('{')+1;
		int indiceVirgula=linha.indexOf(',');
		int indiceFim=linha.indexOf('}');
		
		String idVariavel,valor;
		
		HashSet<String> dominio=new HashSet<String>();
		
		Variavel variavelNova;
		
		if((indiceVirgula==-1) && ((indiceInicio)<indiceFim)) dominio.add(linha.substring(indiceInicio, indiceFim));
		else if(indiceInicio==indiceFim){}
		else
		{
			while(indiceVirgula>0)
			{
			  valor=linha.substring(indiceInicio, indiceVirgula);
			  dominio.add(valor);
			  indiceInicio=indiceVirgula+1;
			  indiceVirgula=linha.indexOf(',',indiceVirgula+1);
			}
			valor=linha.substring(indiceInicio,indiceFim);
			dominio.add(valor);
		}
		
		idVariavel=linha.substring(0, linha.indexOf(' '));
		
		variavelNova=new Variavel(idVariavel,dominio);
		
		variavelNova.print();
		this.listaVariaveis.add(variavelNova);
	}
	
	private Variavel getVariavel(String id)
	{
		Variavel result=null; 
		
		for(Variavel variavel: listaVariaveis) 
		{
			if(variavel.getId().equals(id))
			{
				result=variavel;
				break;
			}
		}
		
		return result;
	}

	private void print(String s){System.out.println(s);}
	private void print(char c){System.out.println(c);}
	
	private void criaCondicao(String linha)
	{
		int indiceCondicao;
		Condicao novaCondicao;
		Variavel variavel1,variavel2;
		
		String operacao,string;
		StringBuffer auxString = new StringBuffer("");
		
		for(int i=0;i<linha.length();i++)
		{
			if(linha.charAt(i)!=' ') auxString.append(linha.charAt(i));
		}
		
		string=auxString.toString();
		auxString.delete(0, auxString.length());
		
        if(string.contains("<")) indiceCondicao=string.indexOf('<');
        else if(string.contains(">")) indiceCondicao=string.indexOf('>');
        else if(string.contains("!")) indiceCondicao=string.indexOf('!');
        else indiceCondicao=string.indexOf('=');
		
		
		auxString.append(string.substring(0, indiceCondicao));
		variavel1=getVariavel(auxString.toString());
		
		auxString.delete(0, auxString.length());
		auxString.append(string.charAt(indiceCondicao));
		
		if(string.indexOf('=', indiceCondicao+1)>0)
		{   
			indiceCondicao=string.indexOf('=', indiceCondicao+1);
			auxString.append(string.charAt(indiceCondicao));
		}
		
		operacao=auxString.toString();
		
		auxString.delete(0, auxString.length());		
		auxString.append(string.substring(indiceCondicao+1));
		
		variavel2=getVariavel(auxString.toString());
		
		novaCondicao=new Condicao(variavel1,variavel2,operacao);
		
		novaCondicao.print();
		
		listaCondicoes.add(novaCondicao);
	}
}
