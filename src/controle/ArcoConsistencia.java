package controle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import modelo.Arco;
import modelo.Condicao;
import modelo.Variavel;

public class ArcoConsistencia 
{
	private List<Arco> listaDeArcos;
	private List<Arco> listaDeArcosRemovidos;
	private Arco arco;

	public ArcoConsistencia() {
		listaDeArcos = new ArrayList<Arco>();
		listaDeArcosRemovidos = new ArrayList<Arco>();
		arco = null;
	}

	public List<Variavel> arcoConsistencia(List<Variavel> listaVariaveis)
	{

		do 
		{			
			listaDeArcos.clear();
			refazListaDeArcosComDominiosCorretos(listaVariaveis);
			
			for (Arco a : listaDeArcos) 
			{
				if (listaDeArcosRemovidos.contains(a)) 
				{
					listaDeArcosRemovidos.remove(a);
					listaDeArcosRemovidos.add(a);
				}
			}
			
			listaDeArcos.removeAll(listaDeArcosRemovidos);
			
			arco = listaDeArcos.get(0);
			listaDeArcosRemovidos.add(arco);
			listaDeArcos.remove(0);
			
			if (reduzirDominio())
			{			
				if (arco.getVariavelPrincipal().getDominio().isEmpty()) 
				{
					System.out.println("Nao tem solucao: o dominio da variavel "+arco.getVariavelPrincipal().getId()+" e vazio");
					return new ArrayList<Variavel>();
				}
				else
				{
					retornaArcosParaListaDeArcos(listaVariaveis);
				}
			}
			
			listaVariaveis.remove(arco.getVariavelPrincipal());
			listaVariaveis.add(arco.getVariavelPrincipal());
		} while (!listaDeArcos.isEmpty());
		
		return listaVariaveis;
	}

	private void retornaArcosParaListaDeArcos(List<Variavel> listaVariaveis)
	{
		for (Condicao c : arco.getVariavelPrincipal().getListaCondicoes())
		{
			if (!c.equals(arco.getCondicao()))
			{
				Variavel var1, var2;
				int indice1, indice2;
				
				if (arco.getVariavelPrincipal().equals(c.getVariavel1())) 
				{
					indice1 = listaVariaveis.indexOf(c.getVariavel2());
					indice2 = listaVariaveis.indexOf(c.getVariavel1());
				}
				else 
				{
					indice1 = listaVariaveis.indexOf(c.getVariavel1());
					indice2 = listaVariaveis.indexOf(c.getVariavel2());
				}
				var1 = listaVariaveis.get(indice1);
				var2 = listaVariaveis.get(indice2);
				
				Arco  novoArco = new Arco(var1,var2, c);
				
				if (!listaDeArcos.contains(novoArco)) 
				{
					listaDeArcos.add(novoArco);
					if (listaDeArcosRemovidos.contains(novoArco)) 
					{
						listaDeArcosRemovidos.remove(novoArco);
					}
				}
			}
		}
	}

	private void refazListaDeArcosComDominiosCorretos(List<Variavel> listaVariaveis)
	{
		for (Variavel variavel : listaVariaveis) 
		{
			for (Condicao condicao : variavel.getListaCondicoes())
			{
				Variavel var;
				int indice;
				
				if (variavel.equals(condicao.getVariavel1())) 
				{
					indice = listaVariaveis.indexOf(condicao.getVariavel2());
				}
				else 
				{
					indice = listaVariaveis.indexOf(condicao.getVariavel1());
				}
				
				var=listaVariaveis.get(indice);
				
				Arco arco = new Arco(variavel,var,condicao );
				listaDeArcos.add(arco);
			}
		}
	}
		
	public Boolean reduzirDominio() 
	{
		Boolean change = false;
		HashSet<String> novoDominio = new HashSet<String>();
		
		for ( String objPrincipal : arco.getVariavelPrincipal().getDominio() )
		{
			if (satisfaz(objPrincipal)) 
			{
				novoDominio.add(objPrincipal);
			}else 
			{
				change = true;
			}
		}
		
		Variavel nova = new Variavel(arco.getVariavelPrincipal().getId(), novoDominio);
		nova.setDominio(novoDominio);
		nova.setListaCondicoes(arco.getVariavelPrincipal().getListaCondicoes());
		
		arco.setVariavelPrincipal(nova);
		
	    return change;
	}

	private Boolean satisfaz(String objPrincipal) {
		
		for ( String objSecundario : arco.getVariavelSecundaria().getDominio() ) 
		{
			if (arco.getVariavelPrincipal().equals(arco.getCondicao().getVariavel1())) {				
				if ( arco.getCondicao().aplicaRegra(objPrincipal, objSecundario) ) 
				{
					return true;
				}
			}
			else
			{
				if ( arco.getCondicao().aplicaRegra(objSecundario, objPrincipal) ) 
				{
					return true;
				}
			}
		}
		return false;
	}
}
