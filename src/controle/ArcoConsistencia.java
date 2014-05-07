package controle;

import java.util.List;

import modelo.Arco;
import modelo.Condicao;
import modelo.Variavel;

public class ArcoConsistencia 
{
	private List<Arco> listaDeArcos;
	
	public void arcoConsistencia(List<Variavel> listaVariaveis)
	{
		for (Variavel variavel : listaVariaveis) 
		{
			for (Condicao condicao : variavel.getListaCondicoes())
			{
				Arco arco = new Arco(variavel, condicao);
				listaDeArcos.add(arco);
			}
		}
		
		do 
		{
			Arco arco = listaDeArcos.get(0);
			listaDeArcos.remove(0);
			
			if (reduzirArco(arco))
			{
				if (arco.getVariavelPrincipal().getDominio().isEmpty()) 
				{
					System.out.println("Nao tem solucao: o dominio da variavel"+arco.getVariavelPrincipal().getId()+" e vazio");
					return;
				}
				else
				{
				  /*TODO
				    Criar for iterativo para cada condição que foi verificada 
				    e recolocá-las na lista de arcos.
				  */
					Arco  novoArco = new Arco(arco.retornaVariavelSecundaria(), arco.getCondicao());
					
					listaDeArcos.add(novoArco);
				}
			}
		} while (!listaDeArcos.isEmpty());
	}
		
	public Boolean reduzirArco(Arco arco) 
	{
		Boolean change = false;
		
		for ( String objPrincipal : arco.getVariavelPrincipal().getDominio() )
		{
			if (!satisfaz(arco, objPrincipal)) 
			{
				arco.getVariavelPrincipal().getDominio().remove(objPrincipal);
				change = true;
			} 
		}
		
	    return change;
	}

	private Boolean satisfaz(Arco arco, String objPrincipal) {
		
		for ( String objSecundario : arco.retornaVariavelSecundaria().getDominio() ) 
		{
			if ( arco.getCondicao().aplicaRegra(objPrincipal, objSecundario) ) 
			{
				return true;
			}
		}
		return false;
	}
}
