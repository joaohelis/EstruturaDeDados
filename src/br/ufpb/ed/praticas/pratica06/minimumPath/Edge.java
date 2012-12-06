package br.ufpb.ed.praticas.pratica06.minimumPath;

public class Edge {
	
	public Vertex u,
				   v;
	public int weigth;
	
	public Edge(Vertex u, Vertex v, int weigth) {
		this.u = u;
		this.v = v;
		this.weigth = weigth;
	}

}
