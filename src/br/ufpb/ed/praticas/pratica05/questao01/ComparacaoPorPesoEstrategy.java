package br.ufpb.ed.praticas.pratica05.questao01;

public class ComparacaoPorPesoEstrategy implements ComparacaoProdutoStrategy{
	
	private Produto p;
	
	public ComparacaoPorPesoEstrategy(Produto p){
		this.p = p; 
	}
	
	@Override
	public float getValorDeComparacao() {
		return p.getPeso();
	}
	
	@Override
	public String toString(){
		return "Comparação por peso";
	}

}
