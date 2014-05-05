package modelo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Variavel {
	private String id;
	private List<Condicao> listaCondicoes;
	private HashSet<String> dominio;

	public Variavel(String id, HashSet<String> dominio)
	{
		this.id=id;
		this.dominio=dominio;
		listaCondicoes=new ArrayList<Condicao>();
	}
	
	public void print()
	{
		System.out.println("ID: "+this.id+" | Dominio: "+this.dominio.toString());
	}
	
	public Variavel(HashSet<String> dominio) throws Exception
	{
		/*for (int i=0 ; i<dominio.length ; i++) 
		{
			/*TODO
			  Passar esse if pro leitor de arquivo
			 */
			
			/*if (dominio[i] == null) 
			{
				throw new Exception("A formata��o do arquivo de entrada n�o estava correta");
			}
			this.dominio.add(dominio[i]);
		}*/
	}
	
	public List<Condicao> getListaCondicoes() 
	{
		return listaCondicoes;
	}

	public void setListaCondicoes(List<Condicao> paramListaCondicoes) 
	{
		listaCondicoes = paramListaCondicoes;
	}

	public void addCondicao(Condicao condicao)
	{
		listaCondicoes.add(condicao);
	}
	
	public HashSet<String> getDominio()
	{
		return dominio;
	}

	public void setDominio(HashSet<String> paramDominio) 
	{
		dominio = paramDominio;
	}

	public String getId() 
	{
		return id;
	}

	public void setId(String id) 
	{
		this.id = id;
	}

	@Override
	public int hashCode() 
	{
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object arg0)
	{
		return hashCode() == arg0.hashCode();
	}
}
