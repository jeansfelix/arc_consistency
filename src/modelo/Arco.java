package modelo;

public class Arco {
	private Variavel variavelPrincipal;
	private Condicao condicao;

	public Arco(Variavel variavelPrincipal, Condicao condicao)
	{
		this.variavelPrincipal = variavelPrincipal;
		this.condicao = condicao;
	}

	public Variavel retornaVariavelSecundaria()
	{
		if (condicao.getVariavel1() == variavelPrincipal) 
		{
			return condicao.getVariavel2();
		}
		else 
		{
			return condicao.getVariavel1();
		}
	}
	
	public Variavel getVariavelPrincipal() 
	{
		return variavelPrincipal;
	}
	
	public void setVariavelPrincipal(Variavel variavelPrincipal) 
	{
		this.variavelPrincipal = variavelPrincipal;
	}
	
	public Condicao getCondicao() 
	{
		return condicao;
	}
	
	public void setCondicao(Condicao condicao) 
	{
		this.condicao = condicao;
	}
	
}
