package controle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import modelo.Arco;
import modelo.Condicao;
import modelo.Variavel;

public class ArcoConsistencia 
{
	private List<Arco> listaDeArcos;
	private List<Arco> listaDeArcosRemovidos;
	private List<List<Variavel>> resultado;
	volatile private List<Variavel> variaveis;
	private Arco arco;

	public ArcoConsistencia() {
		listaDeArcos = new ArrayList<Arco>();
		listaDeArcosRemovidos = new ArrayList<Arco>();
		resultado = new ArrayList<List<Variavel>>();
		arco = null;
	}
	
	public List<List<Variavel>> executar(List<Variavel> listaVariaveis)
	{
		variaveis = listaVariaveis;
		recursiva();
		
		return resultado;
	}

	
	public void recursiva()
	{
		List<Variavel> auxiliar;
		Variavel var1;
		Variavel var2;
		Object[] dominio;
		
		if (dominioPrecisaSerParticionado(variaveis)) 
		{
			Variavel aux = variavelQuePrecisaSerParticionada(variaveis);
			var1 = new Variavel();
			var2 = new Variavel();
			auxiliar = new ArrayList<Variavel>();
			dominio = aux.getDominio().toArray();
			
			int tam = aux.getDominio().size();
			
			var2.setId(aux.getId());
			var2.setListaCondicoes(aux.getListaCondicoes());
			
			var1.setId(aux.getId());
			var1.setListaCondicoes(aux.getListaCondicoes());
			
			for (int i=0; i<tam ; i++) 
			{
				if (i < tam /2) 
				{
					var1.addElementoNoDominio((String) dominio[i]);					
				}
				else 
				{
					var2.addElementoNoDominio((String) dominio[i]);
				}
			}
			
			auxiliar.addAll(variaveis);
			
			variaveis.remove(aux);
			variaveis.add(var1);
			
			Collections.sort(variaveis);
			
			variaveis = arcoConsistencia(variaveis);
			
			chamadaRecursiva();
			
			variaveis.clear();
			variaveis.addAll(auxiliar);
			
			variaveis.remove(aux);
			variaveis.add(var2);
			
			Collections.sort(variaveis);
			
			variaveis = arcoConsistencia(variaveis);
			
			chamadaRecursiva();
			
		}
		else 
		{
			auxiliar = arcoConsistencia(variaveis);
			if (!auxiliar.isEmpty()) 
			{
				adicionaNovoResultado(variaveis);				
			}
		}
	}

	private void chamadaRecursiva()
	{
		if (!dominioPrecisaSerParticionado(variaveis))  
		{
			variaveis = arcoConsistencia(variaveis);
			if (!variaveis.isEmpty()) 
			{
				adicionaNovoResultado(variaveis);				
			}
		}else 
		{
			recursiva();
		}
	}

	private void adicionaNovoResultado(List<Variavel> listaVariaveis)
	{
		if (!listaVariaveis.isEmpty()) 
		{
			List<Variavel> lista = new ArrayList<Variavel>();
			lista.addAll(listaVariaveis);
			
			if (!resultado.containsAll(lista)) 
			{	
				resultado.add(lista);
			}
			
		}
	}	
	
	public void setVariaveis(List<Variavel> variaveis)
	{
		this.variaveis = variaveis;
	}

	public List<Variavel> arcoConsistencia(final List<Variavel> paramlistaVariaveis)
	{
		List<Variavel> listaVariaveis = paramlistaVariaveis;
		listaDeArcosRemovidos.clear();
		
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

			if (listaDeArcos.isEmpty()) {return new ArrayList<Variavel>();}
			
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
		
		Collections.sort(listaVariaveis);
		
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
		TreeSet<String> novoDominio = new TreeSet<String>();
		
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

	private Variavel variavelQuePrecisaSerParticionada(List<Variavel> lista)
	{
	
		for(Variavel variavel : lista)
		{
			if(variavel.getDominio().size()>1)
			{
				return variavel;
			}
		}
		
		return null;
	}
	
	private boolean dominioPrecisaSerParticionado(List<Variavel> lista)
	{
		
		for(Variavel variavel : lista)
		{
		  	if(variavel.getDominio().size()>1) 
		  	{
		  		return true;
		  	}
		}		
		
		return false;
	}
}
