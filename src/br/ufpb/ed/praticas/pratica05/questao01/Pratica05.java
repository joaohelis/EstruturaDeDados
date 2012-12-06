package br.ufpb.ed.praticas.pratica05.questao01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Pratica05 {
	
	public enum TipoOrdenacao{
		peso, valor, valorSobrePeso;
	}
	
	public static void main(String[] args) {
		
		DadosDoArquivo dados = null;
		
		try {
			dados = lerArquivo("mochila_4.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo");
			return;
		}

		List<Produto> produtos = dados.getProdutos();
		
		Mochila mochila = new Mochila(dados.getCapacidadeMochila());
		
		System.out.println("Produtos:\n");
		for(Produto p: produtos)
			System.out.println(p);

		TipoOrdenacao estrategia = null;
				
		while(true){
			
			System.out.println("\nInforme uma estratégia para alocar os produtos na mochila");
			System.out.println("\n1 - Produto mais leve");
			System.out.println("2 - Produto mais caro");
			System.out.println("3 - Valor sobre Peso");
			int opcao;
			String msgErro = "Informe apenas dígitos!";
			
			try{
				opcao = Integer.parseInt(new Scanner(System.in).next());
				if (opcao < 1 || opcao > 3){
					msgErro = "Digite dígios entre o intervalo especificado";
					throw new Exception();
				}
			}catch(Exception e){
				System.out.println("\n>>> "+msgErro+" <<<\n");
				continue;
			}
			
			try{
				switch(opcao){
				case 1:
					estrategia = TipoOrdenacao.peso;
					break;
				case 2:
					estrategia = TipoOrdenacao.valor;
					break;
				case 3:
					estrategia = TipoOrdenacao.valorSobrePeso;
					break;
				}
				break;
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "Entrada inválida!");
			}
		}
				
		metodoGuloso(mochila, produtos, estrategia);
		
		System.out.println("\nMétodo Guloso, tendo-se como base de comparação: "+produtos.get(0).getEstrategia());
		System.out.println("Peso máximo suportado pela mochila: "+mochila.getCapacidadeMaxima());
		System.out.println("Peso existente na mochila: "+mochila.getPesoExistente());
		System.out.printf("Soma dos valores dos produtos da mochila R$ %.2f \n ",mochila.getSomaValorProdutos());
		System.out.println("\n\n>>> Produtos na mochila <<< \n");
		System.out.println(mochila.toString());
	}
	
	public static void metodoGuloso(Mochila mochila, List<Produto> produtos, TipoOrdenacao tipoOrdenacao){
		
		setarTipoDeOrdenacaoAprodutos(produtos, tipoOrdenacao);

		Collections.sort(produtos);
		if (tipoOrdenacao != TipoOrdenacao.peso)
			Collections.reverse(produtos);
	
		for(int i = 0; mochila.getPesoExistente() < mochila.getCapacidadeMaxima() && i < produtos.size(); i++){
			Produto p = produtos.get(i);
			float capacidadeDaMochila = mochila.getCapacidadeMaxima() - mochila.getPesoExistente();
			if (p.getPeso() <= capacidadeDaMochila)
				mochila.addProduto(p);
			else{
				p.setValor((p.getValor() * capacidadeDaMochila)/p.getPeso()); //capacidadeDaMochila/p.getQtde() *
				p.setQtde(capacidadeDaMochila/p.getPeso());
				p.setPeso(capacidadeDaMochila);
				mochila.addProduto(p);
				break;
			}
		}	
	}
	
	private static void setarTipoDeOrdenacaoAprodutos(List<Produto> produtos, TipoOrdenacao tipo){
		Iterator<Produto> it = produtos.iterator();
		while(it.hasNext()){
			Produto p = it.next();
			if (tipo == TipoOrdenacao.peso)p.setEstrategia(new ComparacaoPorPesoEstrategy(p));
			else if(tipo == TipoOrdenacao.valor)p.setEstrategia(new ComparacaoPorValorEstrategy(p));
			else if(tipo == TipoOrdenacao.valorSobrePeso)p.setEstrategia(new ComparacaoPelaRelacaoPesoEvalorEstrategy(p));			
		}
	}
	
	private static DadosDoArquivo lerArquivo(String nomeArquivo) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File(nomeArquivo));;
		
		int qtdeProdutos = sc.nextInt(),
			capacidadeMochila =	sc.nextInt();
		
		List<Produto> produtos = new ArrayList<Produto>(qtdeProdutos);

		for (int i = 0; i < qtdeProdutos; ++i){
			float valor = sc.nextInt(),
				peso = sc.nextInt();
			produtos.add(new Produto(peso, valor));
		}
		return new DadosDoArquivo(qtdeProdutos, capacidadeMochila, produtos);
	}
	
}
