package br.ufpb.ed.prova_2;

import java.util.Arrays;

public class DadosDoArquivo {
	
	private int numDeLinhasDeProducao,
				numDeEstacoesPorMaquina;
	
	private int[][] temposDeProcessamentoPorEstacao,
					temposDeTransferenciaPorEstacao;
	
	int[] tempoDeEntradaNaMaquina,
		  tempoDeSaidaDaMaquina;

	public DadosDoArquivo(int numDeLinhasDeProducao,
			int numDeEstacoesPorMaquina,
			int[][] temposDeProcessamentoPorEstacao,
			int[][] temposDeTransferenciaPorEstacao,
			int[] tempoDeEntradaNaMaquina, int[] tempoDeSaidaDaMaquina) {
		this.numDeLinhasDeProducao = numDeLinhasDeProducao;
		this.numDeEstacoesPorMaquina = numDeEstacoesPorMaquina;
		this.temposDeProcessamentoPorEstacao = temposDeProcessamentoPorEstacao;
		this.temposDeTransferenciaPorEstacao = temposDeTransferenciaPorEstacao;
		this.tempoDeEntradaNaMaquina = tempoDeEntradaNaMaquina;
		this.tempoDeSaidaDaMaquina = tempoDeSaidaDaMaquina;
	}

	public int getNumDeLinhasDeProducao() {
		return numDeLinhasDeProducao;
	}

	public int getNumDeEstacoesPorMaquina() {
		return numDeEstacoesPorMaquina;
	}

	public int[][] getTemposDeProcessamentoPorEstacao() {
		return temposDeProcessamentoPorEstacao;
	}

	public int[][] getTemposDeTransferenciaPorEstacao() {
		return temposDeTransferenciaPorEstacao;
	}

	public int[] getTempoDeEntradaNaMaquina() {
		return tempoDeEntradaNaMaquina;
	}

	public int[] getTempoDeSaidaDaMaquina() {
		return tempoDeSaidaDaMaquina;
	}

	@Override
	public String toString() { 
		
		return "DadosDoArquivo [numDeLinhasDeProducao=" + numDeLinhasDeProducao
				+ ", numDeEstacoesPorMaquina=" + numDeEstacoesPorMaquina
				+ ", temposDeProcessamentoPorEstacao="
				+ Arrays.toString(temposDeProcessamentoPorEstacao)
				+ ", temposDeTransferenciaPorEstacao="
				+ Arrays.toString(temposDeTransferenciaPorEstacao)
				+ ", tempoDeEntradaNaMaquina="
				+ Arrays.toString(tempoDeEntradaNaMaquina)
				+ ", tempoDeSaidaDaMaquina="
				+ Arrays.toString(tempoDeSaidaDaMaquina) + "]";
	}
	
	

}
