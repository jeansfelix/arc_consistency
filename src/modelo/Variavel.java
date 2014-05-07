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
		System.out.println("ID: "+this.id+" | Dominio: "+this.dominio.toString() +
				" | " + printListaCondicoes(listaCondicoes));
	}
	
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
	
	@Override
	public String toString() {
		return id;
	}
}
