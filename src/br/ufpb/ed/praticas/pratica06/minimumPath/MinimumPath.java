package br.ufpb.ed.praticas.pratica06.minimumPath;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import br.ufpb.ed.praticas.pratica06.heapBinarioMinimoGeneric.MinBinaryHeap;

public class MinimumPath {
	
	public static void main(String[] args) {
		
		Vertex[] vertices;
		int[][] matrizDistancia = null;
	
		try {
			matrizDistancia = readFile("exemploMatrizDistanciaPrim.txt");
			vertices = new Vertex[matrizDistancia.length]; 
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo");
			return;
		}
	
		char aux = 'a';
		for(int i = 0; i < vertices.length; ++i)			
			vertices[i] = new Vertex(i, Character.toString(aux++));
		
		List<Vertex>[] listaAdj = new List[vertices.length];
		
		// Criando a lista de adjacencia
		for(int i = 0; i < matrizDistancia.length; ++i)
			for(int j = 0; j < matrizDistancia.length; ++j)
				if (matrizDistancia[i][j] > 0){
					if (listaAdj[i] == null)
						listaAdj[i] = new LinkedList<Vertex>();
					listaAdj[i].add(vertices[j]);
				}	

		List<Edge> edges = new LinkedList<Edge>();
		
		// Criando a lista de aresta
		for(Vertex u: vertices)
			for(Vertex v: listaAdj[u.getId()])
				edges.add(new Edge(u, v, matrizDistancia[u.getId()][v.getId()]));
			
		String label_vInit = "a",
			   label_vEnd = "h";
		
		int vInit = -1, 
			vEnd = -1;
		
		for(int i = 0; (vInit == -1 || vEnd == -1) && i < vertices.length; ++i)
			if(vertices[i].getLabel().equalsIgnoreCase(label_vInit))
				vInit = i;
			else if(vertices[i].getLabel().equalsIgnoreCase(label_vEnd))
				vEnd = i;
		
		dijkstra(vertices, vInit, matrizDistancia, listaAdj);
		System.out.println(">>> Disjktra <<<\n");
		printResultMinimumPath(vertices, vInit, vEnd);
		
		bellmanFord(vertices, edges, vInit, matrizDistancia);		
		System.out.println("\n>>> Bellman-Ford <<<\n");
		printResultMinimumPath(vertices, vInit, vEnd);
	}
	
	
	private static  void printResultMinimumPath(Vertex[] vertices, int vInit, int vEnd){
		vertices = vertices.clone();
		Vertex v = vertices[vEnd];		
		String path = "";
		while(v != null){
			path += v.getLabel();
			v = v.getParent();
		}
		System.out.println("------- Path -------");
		for(int i = path.length()-1; i >= 0; --i)			
			System.out.print(path.charAt(i) + ((i == 0)? "\n":" --> "));
		
		System.out.println("Coast: "+vertices[vEnd].getComparator());
		
	}
		
	public static void dijkstra(Vertex[] vertices, int vInit, int[][] matrizAdj, List<Vertex>[] listAdj){		
		vertices = vertices.clone();
		initializeSingleSource(vertices, vInit, Integer.MAX_VALUE);				
		MinBinaryHeap<Vertex> q = new MinBinaryHeap<Vertex>(vertices);
		while(!q.isEmpty()){			
			Vertex u = q.heapExtractMin();			
			for(Vertex v: listAdj[u.getId()])			
				if (q.conteins(v) && relax(matrizAdj, u, v)){
					int minWeigthPath_v = u.getComparator() + matrizAdj[u.getId()][v.getId()];
					q.heapDecreaseKey(q.find(v), minWeigthPath_v);
				}
		}		
	}
	
	public static boolean bellmanFord(Vertex[] vertices, List<Edge> edges, int vInit, int[][] matrizAdj){
		vertices = vertices.clone();
		int infinity = 0;
		for(Edge e: edges)
			infinity += e.weigth;
		initializeSingleSource(vertices, vInit, infinity);
		for(int i = 0; i < vertices.length -1; ++i)
			for(Edge e: edges)
				relax(matrizAdj, e.u, e.v);
		for(Edge e: edges)
			if (e.v.getComparator() > e.u.getComparator() + matrizAdj[e.u.getId()][e.v.getId()])
				return false;
		return true;
	}
	
	public static boolean relax(int[][] matrizAdj, Vertex u, Vertex v){
		if (v.getComparator() > u.getComparator() + matrizAdj[u.getId()][v.getId()]){
			int minWeigth = u.getComparator() + matrizAdj[u.getId()][v.getId()];
			v.setComparator(minWeigth);
			v.setParent(u);
			return true;
		}
		return false;
	}
	
	public static void initializeSingleSource(Vertex[] vertices, int s, int infinityValue){
		for(Vertex v: vertices){
			v.setWeight(infinityValue);
			v.setParent(null);
		}
		vertices[s].setWeight(0);
	}

	
	private static int[][] readFile(String fileName) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(fileName));;
		int qtde = sc.nextInt();
		int[][] distanceMatrix = new int[qtde][qtde]; 
		for (int i = 0; i < qtde; ++i)
			for(int j = 0; j < qtde; ++j)
				distanceMatrix[i][j] = sc.nextInt();
		return distanceMatrix;
	}

}
