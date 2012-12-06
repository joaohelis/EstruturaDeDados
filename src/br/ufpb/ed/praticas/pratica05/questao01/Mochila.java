package br.ufpb.ed.praticas.pratica05.questao01;

import java.util.List;

public class Mochila{
	
	private List<Produto> produtos;
	private float capacidadeMaxima,
				  pesoExistente,
				  somaValorProdutos;
	
	public Mochila(float capacidadeMaxima){
		this.capacidadeMaxima = capacidadeMaxima;
		this.produtos = new java.util.LinkedList<Produto>();
		this.pesoExistente = 0f;
		this.somaValorProdutos = 0f;
	}
	
	public void addProduto(Produto p){
		if (pesoExistente + p.getPeso() > capacidadeMaxima)
			throw new MochilaException("Peso limite atingido");
		this.produtos.add(p);
		//this.pesoExistente += p.getPeso() * p.getQtde();
		this.pesoExistente += p.getPeso();
		this.somaValorProdutos += p.getValor();
	}
	
	public float getCapacidadeMaxima(){
		return capacidadeMaxima;
	}
	
	public float getPesoExistente(){
		return pesoExistente;
	}
	
	public float getSomaValorProdutos(){
		return this.somaValorProdutos;
	}

	@Override
	public String toString() {
		String aux = "";
		for(Produto p: this.produtos)
			aux += p + "\n";
		return aux;
	}
	
}
