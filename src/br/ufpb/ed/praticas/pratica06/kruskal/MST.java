package br.ufpb.ed.praticas.pratica06.kruskal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MST {
	
public static void main(String[] args) {
		
		Vertice[] vertices;
		int[][] matrizDistancia = null;
	
		try {
			matrizDistancia = lerArquivo("exemploMatrizDistanciaPrim.txt");
			vertices = new Vertice[matrizDistancia.length]; 
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo");
			return;
		}
	
		char aux = 'a';
		for(int i = 0; i < vertices.length; ++i)			
			vertices[i] = new Vertice(i, Character.toString(aux++));
		
		List<Aresta> arestas = new java.util.LinkedList<Aresta>();
		
		for(int i = 0; i < matrizDistancia.length; ++i)
			for(int j = i + 1; j < matrizDistancia.length; ++j)
				if(matrizDistancia[i][j] > 0){
					Vertice u = vertices[i],
							v = vertices[j];
					arestas.add(new Aresta(u, v, matrizDistancia[i][j]));
					
				}		
		
		MST mst = new MST();
		
		List<Aresta> arvEspMin = mst.MST_Kruskal(matrizDistancia, vertices, arestas);
		
		int soma = 0;
		for(Aresta a: arvEspMin){
			soma += a.getPeso();
			System.out.println(a);
		}
		
		System.out.println("Soma: "+soma);
				
	}	
	
	private int controlConj = 1;

	public List<Aresta> MST_Kruskal(int[][] matrizDistancia, Vertice[] vertices, List<Aresta> arestas ){
		
		Map<Integer, List<Vertice>> conjuntos = new HashMap<Integer, List<Vertice>>();
		
		List<Aresta> arvEspMin = new java.util.LinkedList<Aresta>();

		for(Vertice v: vertices)
			make_set(v, conjuntos);
						
		Collections.sort(arestas);
						
		for(Aresta a: arestas){
			if(find_set(a.getU()) != find_set(a.getV())){
				arvEspMin.add(a);
				union(a.getU(), a.getV(), conjuntos);
			}
		}
		return arvEspMin;
	}
	
	private void make_set(Vertice v, Map<Integer, List<Vertice>>conjuntos){
		v.setIdConjuntoAssociado(this.controlConj);
		List<Vertice> aux = new LinkedList<Vertice>();
		aux.add(v);
		conjuntos.put(controlConj, aux);
		controlConj++;
	}
	
	private int find_set(Vertice v){
		return v.getIdConjuntoAssociado();
	}

	private void union(Vertice u, Vertice v, Map<Integer, List<Vertice>> conjuntos){
		List<Vertice> conjuntoAssociadoU = conjuntos.get(u.getIdConjuntoAssociado()),
				      conjuntoAssociadoV = conjuntos.get(v.getIdConjuntoAssociado());
		
		int idConjuntoU = u.getIdConjuntoAssociado(),
			idConjuntoV = v.getIdConjuntoAssociado();
		
		if (conjuntoAssociadoU.size() < conjuntoAssociadoV.size()){			
			for(Vertice u1: conjuntoAssociadoU){
				u1.setIdConjuntoAssociado(idConjuntoV);
				conjuntoAssociadoV.add(u1);
			}
			conjuntos.remove(idConjuntoU);
		}else{
			for(Vertice v1: conjuntoAssociadoV){
				v1.setIdConjuntoAssociado(idConjuntoU);
				conjuntoAssociadoU.add(v1);
			}
			conjuntos.remove(idConjuntoV);
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
