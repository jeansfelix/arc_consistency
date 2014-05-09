package modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Variavel implements Comparable<Variavel>{
	private String id;
	private List<Condicao> listaCondicoes;
	private TreeSet<String> dominio;

	public Variavel(String id, TreeSet<String> dominio)
	{
		this.id=id;
		this.dominio=dominio;
		listaCondicoes=new ArrayList<Condicao>();
	}
	
	public Variavel()
	{
		this.id="";
		this.dominio=new TreeSet<String>();
		listaCondicoes=new ArrayList<Condicao>();
	}

	
	public String print()
	{
		String print;
		
		print = this.id+" = ";
		
		for (String dominio : this.dominio) 
		{
			print += dominio + " ";
		}
		
		return print;
	}
	
	/**
	 * Exibir lista de condições da variavel.
	 * */
	@SuppressWarnings("unused")
	private String printListaCondicoes(List<Condicao> listaCondicoes) 
	{
		String condicoes = "[ ";
		
		for (Condicao c : listaCondicoes) 
		{
			condicoes += c.getVariavel1().getId() + c.getOperacao()
					+ c.getVariavel2().getId() + " ";
		}
		return condicoes +="]";
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
	
	public TreeSet<String> getDominio()
	{
		return dominio;
	}

	public void setDominio(TreeSet<String> paramDominio) 
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
	
	@Override
	public String toString() {
		return id;
	}
	
	public void addElementoNoDominio(String elemento)
	{
		this.dominio.add(elemento);
	}

	@Override
	public int compareTo(Variavel o)
	{
		return id.compareTo(o.getId());
	}
}
