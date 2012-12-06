package br.ufpb.ed.praticas.pratica06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import br.ufpb.ed.praticas.pratica06.heapBinarioMinimo.MinBinaryHeap;

public class MST{
	
	public static void main(String[] args) {
		
		Vertice[] vertices = new Vertice[9];
		int[][] matrizDistancia = null;
	
		try {
			matrizDistancia = lerArquivo("exemploMatrizDistanciaPrim.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo");
			return;
		}
		
		char aux = 'a';
		for(int i = 0; i < vertices.length; ++i)			
			vertices[i] = new Vertice(i, Character.toString(aux++));		
		
		List<Vertice>[] listaAdj = new List[vertices.length];
		
		for(int i = 0; i < matrizDistancia.length; ++i)
			for(int j = 0; j < matrizDistancia.length; ++j)
				if (matrizDistancia[i][j] > 0){
					if (listaAdj[i] == null)
						listaAdj[i] = new LinkedList<Vertice>();
					listaAdj[i].add(vertices[j]);
				}	
		
		MST_Prim(vertices, 0, matrizDistancia, listaAdj);
		
		int soma = 0;
		for(Vertice v: vertices){
			System.out.print("Label " + v.getLable());
			System.out.print(" Chave: "+v.getChave());
			String pai = (v.getPai() == null)? "null": v.getPai().getLable();
			System.out.println(" Pai: "+ pai);
			soma += v.getChave();
		}

		System.out.println("Soma: "+soma);
	}
	
	public static void MST_Prim(Vertice[] vertices, int raiz, int[][] matrizAdj, List<Vertice>[] listAdj){		
		vertices = vertices.clone();
		for(int i = 0; i < vertices.length; ++i){
			vertices[i].setChave(Integer.MAX_VALUE);
			vertices[i].setPai(null);			
		}
		vertices[raiz].setChave(0);
		MinBinaryHeap q = new MinBinaryHeap(vertices);
		while(!q.isEmpty()){			
			Vertice u = q.heapExtractMin();			
			for(Vertice v: listAdj[u.getId()]){			
				if (q.conteins(v) && matrizAdj[u.getId()][v.getId()] < v.getChave()){
					v.setPai(u);
					int minPeso = matrizAdj[u.getId()][v.getId()];									
					v.setChave(minPeso);
					q.heapDecreaseKey(q.find(v), minPeso);					
				}				
			}	
		}		
	}
	
	private static int[][] lerArquivo(String nomeArquivo) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(nomeArquivo));;
		int qtde = sc.nextInt();
		int[][] matrizDistancia = new int[qtde][qtde]; 
		for (int i = 0; i < qtde; ++i)
			for(int j = 0; j < qtde; ++j)
				matrizDistancia[i][j] = sc.nextInt();
		return matrizDistancia;
	}

}
