package modelo;

public class Arco {
	private Variavel variavelPrincipal;
	private Variavel variavelSecundaria;
	private Condicao condicao;

	public Arco(Variavel variavelPrincipal, Variavel variavelSecundaria, Condicao condicao)
	{
		this.variavelSecundaria = variavelSecundaria;
		this.variavelPrincipal = variavelPrincipal;
		this.condicao = condicao;
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
	
	@Override
	public String toString() {
		return "[" + variavelPrincipal + " | " + condicao + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		Arco entrada = (Arco) obj;
		
		return this.variavelPrincipal.equals(entrada.getVariavelPrincipal()) && condicao.equals(entrada.getCondicao());
	}

	public Variavel getVariavelSecundaria() {
		return variavelSecundaria;
	}

	public void setVariavelSecundaria(Variavel variavelSecundaria) {
		this.variavelSecundaria = variavelSecundaria;
	}
}
