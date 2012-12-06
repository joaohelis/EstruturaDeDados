package br.ufpb.ed.prova_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Questao_02 {
	
	public static void main(String[] args) {
				
		DadosDoArquivo dados;
		
		try {
			dados = lerArquivo("tempos.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo!");
			return;
		}
		
		int[][] tabelaMelhorTemp = new int[dados.getNumDeLinhasDeProducao()][dados.getNumDeEstacoesPorMaquina()],
				caminhoOtimo = new int[dados.getNumDeLinhasDeProducao()][dados.getNumDeEstacoesPorMaquina()-1];	
		
		imprimirCaminhoOtimo(dados, tabelaMelhorTemp, caminhoOtimo);
	}
	
	private static void imprimirCaminhoOtimo(DadosDoArquivo d, int[][] tabelaMelhorTemp, int[][]caminhoOtimo){
		int[] dados = calcularTempoOtimo(d, 0, d.getNumDeEstacoesPorMaquina()-1, tabelaMelhorTemp, caminhoOtimo);
		int menorTempo = dados[1];
		Integer idLinha = dados[0];

		System.out.println("\nO menor tempo para produção do carro, desde a entrada do chassi na linha de produção até" +
				"\na saida do carro completo, foi de: "+menorTempo);
		System.out.println("\nlinha "+(idLinha+1)+", estação "+d.getNumDeEstacoesPorMaquina());
		
		for(int j = caminhoOtimo[idLinha].length; j > 0; --j){
			idLinha = caminhoOtimo[idLinha][j-1];
			System.out.println("linha "+(idLinha+1)+", estação "+ (j));
		}
	}
	
	private static int[] calcularTempoOtimo(DadosDoArquivo d, int estacaoInicial, int estacaoFinal, int[][] tabelaMelhorTemp, int[][] caminhoOtimo){
		if(estacaoInicial <= estacaoFinal)
			if(estacaoInicial == 0){
				tabelaMelhorTemp[0][estacaoInicial] = d.getTempoDeEntradaNaMaquina()[0] + d.getTemposDeProcessamentoPorEstacao()[0][estacaoInicial];
				tabelaMelhorTemp[1][estacaoInicial] = d.tempoDeEntradaNaMaquina[1] + d.getTemposDeProcessamentoPorEstacao()[1][estacaoInicial];
			}else{													
				int tempoProcessamentoNaProximaEstacaoNaMesmaLinha = tabelaMelhorTemp[0][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[0][estacaoInicial];
				int tempoProcessamentoNaProximaEstacaoEmLinhaDiferente = tabelaMelhorTemp[1][estacaoInicial-1] + d.getTemposDeTransferenciaPorEstacao()[1][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[0][estacaoInicial];
				
				if (tempoProcessamentoNaProximaEstacaoNaMesmaLinha < tempoProcessamentoNaProximaEstacaoEmLinhaDiferente){
					tabelaMelhorTemp[0][estacaoInicial] = tempoProcessamentoNaProximaEstacaoNaMesmaLinha;
					caminhoOtimo[0][estacaoInicial-1] = 0;
				}else{
					tabelaMelhorTemp[0][estacaoInicial] = tempoProcessamentoNaProximaEstacaoEmLinhaDiferente;
					caminhoOtimo[0][estacaoInicial-1] = 1;
				}
				
				tempoProcessamentoNaProximaEstacaoNaMesmaLinha = tabelaMelhorTemp[1][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[1][estacaoInicial];
				tempoProcessamentoNaProximaEstacaoEmLinhaDiferente = tabelaMelhorTemp[0][estacaoInicial-1] + d.getTemposDeTransferenciaPorEstacao()[0][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[1][estacaoInicial];
				
				if (tempoProcessamentoNaProximaEstacaoNaMesmaLinha < tempoProcessamentoNaProximaEstacaoEmLinhaDiferente){
					tabelaMelhorTemp[1][estacaoInicial] = tempoProcessamentoNaProximaEstacaoNaMesmaLinha;
					caminhoOtimo[1][estacaoInicial-1] = 1;
				}else{
					tabelaMelhorTemp[1][estacaoInicial] = tempoProcessamentoNaProximaEstacaoEmLinhaDiferente;
					caminhoOtimo[1][estacaoInicial-1] = 0;
				}
				
				if (estacaoInicial == estacaoFinal)
					if (tabelaMelhorTemp[0][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[0] < 
							tabelaMelhorTemp[1][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[1])								
						return new int[]{0, tabelaMelhorTemp[0][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[0]};
					else
						return new int[]{1, tabelaMelhorTemp[1][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[1]};									
			}		
		return calcularTempoOtimo(d, ++estacaoInicial, estacaoFinal, tabelaMelhorTemp, caminhoOtimo);
	}
		
//	private static int[] calcularTempoOtimo(DadosDoArquivo d, int estacaoInicial, int estacaoFinal, int[][] tabelaMelhorTemp, int[][] caminhoOtimo){
//		if(estacaoInicial <= estacaoFinal){
//			if(estacaoInicial == 0){// Atribui a tabela de melhor tempo, na estação inicial da o tempo de entrada mais o tempo de processamento na estação
//				tabelaMelhorTemp[0][estacaoInicial] = d.getTempoDeEntradaNaMaquina()[0] + d.getTemposDeProcessamentoPorEstacao()[0][estacaoInicial];
//				tabelaMelhorTemp[1][estacaoInicial] = d.tempoDeEntradaNaMaquina[1] + d.getTemposDeProcessamentoPorEstacao()[1][estacaoInicial];
//			}else if(estacaoInicial <= estacaoFinal){
//				// Verifica se é melhor ir pra a próxima estação na mesma linha de montagem (linha de montagem 0)
//				// ou é melhor sair da estação da outra linha de montagem (linha de montagem 1)
//				int tempoProcessamentoNaProximaEstacaoNaMesmaLinha = tabelaMelhorTemp[0][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[0][estacaoInicial];
//				int tempoProcessamentoNaProximaEstacaoEmLinhaDiferente = tabelaMelhorTemp[1][estacaoInicial-1] + d.getTemposDeTransferenciaPorEstacao()[1][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[0][estacaoInicial];
//				
//				if (tempoProcessamentoNaProximaEstacaoNaMesmaLinha < tempoProcessamentoNaProximaEstacaoEmLinhaDiferente){
//				// Atribui a soma dos tempos gastos em estações anteriores mais o tempo de processamento na estação atual
//					tabelaMelhorTemp[0][estacaoInicial] = tempoProcessamentoNaProximaEstacaoNaMesmaLinha;
//				// Atribui ao caminho ótimo, na posição da estação atual menos um, a linha de montagem 0
//				// Representando desta forma que para que o tempo gasto até chegar a estação atual seja ótimo, é necesário
//				// que o carro seja fabricado na estação anterior na linha de montagem 0
//					caminhoOtimo[0][estacaoInicial-1] = 0;
//				}else{
//				// Atribui a soma dos tempos gastos em estações anteriores mais o tempo de processamento na estação atual
//					tabelaMelhorTemp[0][estacaoInicial] = tempoProcessamentoNaProximaEstacaoEmLinhaDiferente;
//				// Atribui ao caminho ótimo, na posição da estação atual menos um, a linha de montagem 1
//				// Representando desta forma que para que o tempo gasto até chegar a estação atual seja ótimo, é necesário
//				// que o carro seja fabricado na estação anterior na linha de montagem 1
//					caminhoOtimo[0][estacaoInicial-1] = 1;
//				}
//				// 
//				tempoProcessamentoNaProximaEstacaoNaMesmaLinha = tabelaMelhorTemp[1][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[1][estacaoInicial];
//				tempoProcessamentoNaProximaEstacaoEmLinhaDiferente = tabelaMelhorTemp[0][estacaoInicial-1] + d.getTemposDeTransferenciaPorEstacao()[0][estacaoInicial-1] + d.getTemposDeProcessamentoPorEstacao()[1][estacaoInicial];
//				
//				if (tempoProcessamentoNaProximaEstacaoNaMesmaLinha < tempoProcessamentoNaProximaEstacaoEmLinhaDiferente){
//					tabelaMelhorTemp[1][estacaoInicial] = tempoProcessamentoNaProximaEstacaoNaMesmaLinha;
//					caminhoOtimo[1][estacaoInicial-1] = 1;
//				}else{
//					tabelaMelhorTemp[1][estacaoInicial] = tempoProcessamentoNaProximaEstacaoEmLinhaDiferente;
//					caminhoOtimo[1][estacaoInicial-1] = 0;
//				}
//				
//				if (estacaoInicial == estacaoFinal){
//					// O método retorna um vetor com duas posições, onde são armazendos nessas, o valor menor tempo gasto até o final
//					// do processo de produção, e a linha de montagem onde deve sair o carro após a ultima estação
//					if (tabelaMelhorTemp[0][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[0] < 
//							tabelaMelhorTemp[1][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[1])								
//						return new int[]{0, tabelaMelhorTemp[0][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[0]};
//					else
//						return new int[]{1, tabelaMelhorTemp[1][estacaoInicial] + d.getTempoDeSaidaDaMaquina()[1]};					
//				}
//			}
//		}
//		return calcularTempoOtimo(d, ++estacaoInicial, estacaoFinal, tabelaMelhorTemp, caminhoOtimo);
//	}
	
	private static DadosDoArquivo lerArquivo(String nomeArquivo) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(nomeArquivo));;
		int qtdeLinhasProducao = sc.nextInt(),
			qtdeEstacoesPorLinha = sc.nextInt();
		
		int[][] temposDeProcessamento = new int[qtdeEstacoesPorLinha][qtdeEstacoesPorLinha];
		int[][] temposDetransferenciaDasMaquinas = new int[qtdeEstacoesPorLinha][qtdeEstacoesPorLinha];
		
		for(int i = 0; i < qtdeLinhasProducao; ++i)
			for(int j = 0; j < qtdeEstacoesPorLinha; ++j)
				temposDeProcessamento[i][j] = sc.nextInt();
		
		for(int i = 0; i < qtdeLinhasProducao; ++i)
			for(int j = 0; j < qtdeEstacoesPorLinha-1; ++j)
				temposDetransferenciaDasMaquinas[i][j] = sc.nextInt();
		
		int[] tempoDeEntradaNaMaquina = new int[qtdeLinhasProducao];
		int[] tempoDeSaidaDaMaquina = new int[qtdeLinhasProducao];
		
		for(int i = 0; i < qtdeLinhasProducao; ++i)
			tempoDeEntradaNaMaquina[i] = sc.nextInt();
		
		for(int i = 0; i < qtdeLinhasProducao; ++i)
			tempoDeSaidaDaMaquina[i] = sc.nextInt();
				
		DadosDoArquivo d = new DadosDoArquivo(qtdeLinhasProducao, qtdeEstacoesPorLinha, temposDeProcessamento, 
				temposDetransferenciaDasMaquinas, tempoDeEntradaNaMaquina, tempoDeSaidaDaMaquina);
		return d;
	}

}
