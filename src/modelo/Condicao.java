package modelo;

public class Condicao 
{
	private Variavel variavel1;
	private Variavel variavel2;
	private String operacao;

	public boolean aplicaRegra(Object objPrincipal, Object objSecundario)
	{
		if (operacao.equals("=")) 
		{
			return objPrincipal == objSecundario;
		}
		if (operacao.equals("!=")) 
		{
			return objPrincipal != objSecundario;
		}
		return false;
	}
	
	public Variavel getVariavel1()
	{
		return variavel1;
	}

	public void setVariavel1(Variavel variavel1)
	{
		this.variavel1 = variavel1;
	}

	public Variavel getVariavel2()
	{
		return variavel2;
	}

	public void setVariavel2(Variavel variavel2)
	{
		this.variavel2 = variavel2;
	}

	public String getOperacao() {
		return operacao;
	}

	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

}
