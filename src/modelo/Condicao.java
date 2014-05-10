package modelo;

public class Condicao 
{
	private Variavel variavel1;
	private Variavel variavel2;
	private String operacao;

	public boolean aplicaRegra(String objPrincipal, String objSecundario)
	{
		if (operacao.equals("=")) 
		{
			return objPrincipal.equals(objSecundario);
		}
		if (operacao.equals("!=")) 
		{
			return !objPrincipal.equals(objSecundario);
		}
		if (operacao.equals("<")) 
		{
			return Integer.parseInt(objPrincipal) < Integer.parseInt(objSecundario);
		}
		if (operacao.equals("modDif"))
		{
			int indice1 = variavel1.getId().charAt(1) - '0';
			int indice2 = variavel2.getId().charAt(1) - '0';
			
			return equalsModDif(Integer.parseInt(objPrincipal), Integer.parseInt(objSecundario), indice1, indice2);
		}
		if (operacao.equals("!modDif"))
		{
			int indice1 = variavel1.getId().charAt(1) - '0';
			int indice2 = variavel2.getId().charAt(1) - '0';
			
			return !equalsModDif(Integer.parseInt(objPrincipal), Integer.parseInt(objSecundario), indice1, indice2);
		}
		return false;
	}
	
	private boolean equalsModDif(int valor1, int valor2, int indice1, int indice2) 
	{
		return ( Math.abs(valor1 - valor2) == Math.abs(indice1 - indice2));	
	}
	
	public Condicao(Variavel v1, Variavel v2, String op)
	{
		this.variavel1=v1;
		this.variavel2=v2;
		this.operacao=op;
	}
	
	public void print()
	{
		System.out.println(variavel1.getId()+operacao+variavel2.getId());
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
	
	@Override
	public String toString() {
		return "(" +  variavel1 + " "+ operacao + " " + variavel2 + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		Condicao nova = (Condicao) obj;
		
		return nova.getVariavel1().equals(variavel1) &&
				nova.getVariavel2().equals(variavel2) &&
				nova.getOperacao().equals(operacao);
	}

}
