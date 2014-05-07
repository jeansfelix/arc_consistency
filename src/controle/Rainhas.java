package controle;

import java.util.List;

import modelo.Condicao;
import modelo.Variavel;

public class Rainhas {
	public void inicializaCondicoesRainhas(List<Variavel> variaveis) 
	{
		for (Variavel var : variaveis) 
		{
			for (Variavel var2 : variaveis) 
			{
				if (!var.equals(var2)) 
				{
					var.addCondicao(new Condicao(var, var2, "!="));
					var.addCondicao(new Condicao(var, var2, "modDif"));
				}
			}
		}
	}
}
