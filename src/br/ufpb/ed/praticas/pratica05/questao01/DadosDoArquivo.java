package br.ufpb.ed.praticas.pratica05.questao01;

import java.util.List;

public class DadosDoArquivo {
	
	private int qtdeProdutos;
	private float capacidadeMochila;
	private List<Produto> produtos;
	
	public DadosDoArquivo(int qtdeProdutos, float capacidadeMochila,
			List<Produto> produtos) {
		this.qtdeProdutos = qtdeProdutos;
		this.capacidadeMochila = capacidadeMochila;
		this.produtos = produtos;
	}

	public int getQtdeProdutos() {
		return qtdeProdutos;
	}

	public void setQtdeProdutos(int qtdeProdutos) {
		this.qtdeProdutos = qtdeProdutos;
	}

	public float getCapacidadeMochila() {
		return capacidadeMochila;
	}

	public void setCapacidadeMochila(float capacidadeMochila) {
		this.capacidadeMochila = capacidadeMochila;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
}
