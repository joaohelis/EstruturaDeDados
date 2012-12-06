package br.ufpb.ed.praticas.pratica05.questao01;

public class ComparacaoPorValorEstrategy implements ComparacaoProdutoStrategy {

	private Produto p;
	
	public ComparacaoPorValorEstrategy(Produto p){
		this.p = p; 
	}
	
	@Override
	public float getValorDeComparacao() {
		return p.getValor();
	}
	
	@Override
	public String toString(){
		return "Comparação Valor";
	}

}
