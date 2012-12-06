package br.ufpb.ed.praticas.pratica05.questao01;


public class Produto implements Comparable<Produto>{
	
	private float peso,
				  valor,
				  qtde;
	
	private ComparacaoProdutoStrategy estrategia;
	
	
	public Produto(float peso, float valor){
		this(peso, valor, 1, null);
	}
	
	public Produto(float peso, float valor, float qtde,
			ComparacaoProdutoStrategy estrategia) {
		this.peso = peso;
		this.valor = valor;
		this.qtde = qtde;
		this.estrategia = estrategia;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
	

	public ComparacaoProdutoStrategy getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(ComparacaoProdutoStrategy estrategia) {
		this.estrategia = estrategia;
	}

	public float getQtde() {
		return qtde;
	}

	public void setQtde(float qtde) {
		this.qtde = qtde;
	}

	@Override
	public int compareTo(Produto produto) {
		if (this.estrategia.getValorDeComparacao() < produto.getEstrategia().getValorDeComparacao())
			return -1;
		else if (this.estrategia.getValorDeComparacao() > produto.getEstrategia().getValorDeComparacao())
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		return "Produto [peso=" + peso + ", valor=" + valor + ", qtde=" + qtde
				+ ", estrategia=" + estrategia + "]";
	}

}
