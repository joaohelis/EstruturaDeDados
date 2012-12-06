package br.ufpb.ed.praticas.pratica05.questao01;

public class ComparacaoPelaRelacaoPesoEvalorEstrategy implements ComparacaoProdutoStrategy{
	
	private Produto p;
	
	public ComparacaoPelaRelacaoPesoEvalorEstrategy(Produto p){
		this.p = p; 
	}
	
	@Override
	public float getValorDeComparacao() {
		return p.getValor() / p.getPeso();
	}
	
	@Override
	public String toString(){
		return "Compara��o pela Rela��o entre Peso e Valor";
	}

}
